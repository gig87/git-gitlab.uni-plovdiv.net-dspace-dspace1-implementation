package bg.pu.fmi

import static org.springframework.http.HttpStatus.CREATED
import static org.springframework.http.HttpStatus.NOT_FOUND
import static org.springframework.http.HttpStatus.NO_CONTENT
import static org.springframework.http.HttpStatus.OK

class ItemController {

    def itemService

    def index() { }

    def show(){
        Item item
        if (params.id != null){
            item = itemService.getItem(params.id)
            session.item = item
        }else{
            item = session.item
        }

        def allList = item.metadatas
        if (params.sort)
            allList = allList.sort {it."${params.sort}"}
        if (params.order == "desc")
            allList = allList.reverse()

        def streamList = item.bitstreams
        if (params.sort)
            streamList = streamList.sort {it."${params.sort}"}
        if (params.order == "desc")
            streamList = streamList.reverse()

        respond item, model:[metadatasList: allList, bitstreamsList: streamList]
    }

    def create(){
        respond new Item()
    }

    def edit(){
        respond itemService.getItem(params.id)
    }

    def update(Item item){
        if (item == null) {
            notFound()
            return
        }

        if (item.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond item.errors, view:'edit'
            return
        }

        itemService.updateItem(item)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'item.label', default: 'Item'), item.name])
                redirect(action: "show", id: item.id)
            }
            '*'{ respond item, [status: OK] }
        }
    }

    def save(Item item) {
        if (item == null) {
            notFound()
            return
        }

        def createdItemId = itemService.createItem(session.collection.id, item.name, item.author, item.theme)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'item.label', default: 'Item'), item.name])
                redirect(action: "show", id: createdItemId)
            }
            '*' { respond item, [status: CREATED] }
        }
    }

    def delete(){
        if (params.id == null) {
            notFound()
            return
        }

        itemService.deleteItem(params.id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'item.label', default: 'Item'), params.id])
                redirect(controller: "collection", action: "show", id: session.collection.id)
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'item.label', default: 'Item'), params.id])
                redirect(controller: "collection", action: "show", id: session.collection.id)
            }
            '*'{ render status: NOT_FOUND }
        }
    }

    def upload() {
        def file = request.getFile('file')
        if(file.empty) {
            flash.message = "File cannot be empty"
        } else {
            System.out.println("File name is --- " + file.originalFilename);
            System.out.println("File size is --- " + file.inputStream.bytes.size());
        }
        redirect(action: "show", id: session.item.id)
    }
}
