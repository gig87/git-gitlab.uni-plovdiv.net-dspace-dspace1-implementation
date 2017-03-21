package bg.pu.fmi

import static org.springframework.http.HttpStatus.CREATED
import static org.springframework.http.HttpStatus.NOT_FOUND
import static org.springframework.http.HttpStatus.NO_CONTENT
import static org.springframework.http.HttpStatus.OK

class CollectionController {

    def collectionService

    def index() { }

    def show(){
        Collection collection
        if (params.id != null){
            collection = collectionService.getCollection(params.id)
            session.collection = collection
        }else{
            collection = session.collection
        }

        def allList = collection.items
        if (params.sort)
            allList = allList.sort {it."${params.sort}"}
        if (params.order == "desc")
            allList = allList.reverse()
        int from = params.offset ? new Long(params.offset).intValue(): 0
        int to = from + (params.max ? new Long(params.max).intValue(): 10)
        if (to > allList.size()){
            to = allList.size()
        }
        def myList = allList.subList(from, to)

        respond collection, model:[itemsList: myList, itemsCount: allList.size()]
    }

    def create(){
        respond new Collection()
    }

    def edit(){
        respond collectionService.getCollection(params.id)
    }

    def update(Collection collection){
        if (collection == null) {
            notFound()
            return
        }

        if (collection.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond collection.errors, view:'edit'
            return
        }

        collectionService.updateCollection(collection)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'collection.label', default: 'Collection'), collection.name])
                redirect(action: "show", id: collection.id)
            }
            '*'{ respond collection, [status: OK] }
        }
    }

    def save(Collection collection) {
        if (collection == null) {
            notFound()
            return
        }

        def createdCollectionId = collectionService.createCollection(session.community.id, collection.name, collection.shortDescription)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'collection.label', default: 'Collection'), collection.name])
                redirect(action: "show", id: createdCollectionId)
            }
            '*' { respond collection, [status: CREATED] }
        }
    }

    def delete(){
        if (params.id == null) {
            notFound()
            return
        }

        collectionService.deleteCollection(params.id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'collection.label', default: 'Collection'), params.id])
                redirect(controller: "community", action: "show", id: session.community.id)
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'collection.label', default: 'Collection'), params.id])
                redirect(controller: "community", action: "show", id: session.community.id)
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
