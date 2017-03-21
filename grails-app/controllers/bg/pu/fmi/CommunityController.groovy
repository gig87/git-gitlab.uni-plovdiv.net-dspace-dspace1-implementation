package bg.pu.fmi

import grails.converters.JSON
import grails.rest.RestfulController
import groovy.json.JsonSlurper

import static org.springframework.http.HttpStatus.*

class CommunityController extends RestfulController {

    def communityService
    private static final CONTENT_TYPE = "application/json";
    private static final ENCODING = "UTF-8";

    CommunityController() {
        super(CommunityController)
    }

    def list() {
        if (session.user == null){
            redirect(controller: "user", action : "login")
        }

//        def allList = communityService.listCommunities()
//        if (params.sort)
//            allList = allList.sort {it."${params.sort}"}
//        if (params.order == "desc")
//            allList = allList.reverse()
//        int from = params.offset ? new Long(params.offset).intValue(): 0
//        int to = from + (params.max ? new Long(params.max).intValue(): 10)
//        if (to > allList.size()){
//            to = allList.size()
//        }
//        def myList = allList.subList(from, to)

        //respond communityService.listCommunities(), model:[communitiesList: myList, communitiesCount: allList.size()]

        render(text: new Wrapper().wrapVoid({ communityService.listCommunities() }) as JSON, contentType: CONTENT_TYPE, encoding: ENCODING);
    }

    def show(String id){
//        Community community
//        if (params.id != null){
//            community = communityService.getCommunity(params.id)
//            session.community = community
//        }else{
//            community = session.community
//        }
//
//        def allList = community.collections
//        if (params.sort)
//            allList = allList.sort {it."${params.sort}"}
//        if (params.order == "desc")
//            allList = allList.reverse()
//        int from = params.offset ? new Long(params.offset).intValue(): 0
//        int to = from + (params.max ? new Long(params.max).intValue(): 10)
//        if (to > allList.size()){
//            to = allList.size()
//        }
//        def myList = allList.subList(from, to)

        //respond community, model:[collectionsList: myList, collectionsCount: allList.size()]

        render(text: new Wrapper().wrap(id, { communityService.getCommunity(it) }) as JSON, contentType: CONTENT_TYPE, encoding: ENCODING);

    }

    def create(){
        //respond new Community();
        render(text: new Wrapper().wrapVoid({ new Community() }) as JSON, contentType: CONTENT_TYPE, encoding: ENCODING);
    }

    def edit(String id){
        //respond communityService.getCommunity(params.id)
        render(text: new Wrapper().wrap(id, { communityService.getCommunity(it) }) as JSON, contentType: CONTENT_TYPE, encoding: ENCODING);
    }

    def update(String community){

        def comm = new JsonSlurper().parseText(community);

//        if (community == null) {
//            notFound()
//            return
//        }
//
//        if (community.hasErrors()) {
//            transactionStatus.setRollbackOnly()
//            respond community.errors, view:'edit'
//            return
//        }

        communityService.updateCommunity(comm)

//        request.withFormat {
//            form multipartForm {
//                flash.message = message(code: 'default.updated.message', args: [message(code: 'community.label', default: 'Общност'), community.name])
//                redirect(action: "show", id: community.id)
//            }
//            '*'{ respond community, [status: OK] }
//        }

        render(text: new Wrapper().wrap(comm, { communityService.updateCommunity(it) }) as JSON, contentType: CONTENT_TYPE, encoding: ENCODING);
    }

    def save(String community) {
        def comm = new JsonSlurper().parseText(community);

        if (community == null) {
            notFound()
            return
        }



        //def createdCommunityId = communityService.createCommunity(comm.name, comm.shortDescription)

//        request.withFormat {
//            form multipartForm {
//                flash.message = message(code: 'default.created.message', args: [message(code: 'community.label', default: 'Общност'), community.name])
//                redirect(action: "show", id: createdCommunityId)
//            }
//            '*' { respond community, [status: CREATED] }
//        }

        render(text: new Wrapper().wrapVoid({ communityService.createCommunity(comm.name, comm.shortDescription) }) as JSON, contentType: CONTENT_TYPE, encoding: ENCODING);
    }

    def delete(){
        if (params.id == null) {
            notFound()
            return
        }

        communityService.deleteCommunity(params.id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'community.label', default: 'Общност'), params.id])
                redirect(controller: "community", action: "index")
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'community.label', default: 'Общност'), params.id])
                redirect(controller: "community", action: "index")
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
