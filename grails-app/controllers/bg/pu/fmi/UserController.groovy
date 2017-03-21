package bg.pu.fmi
import grails.converters.JSON
import grails.rest.RestfulController

class UserController extends RestfulController {

    private static final CONTENT_TYPE = "application/json";
    private static final ENCODING = "UTF-8";

    def userService

    UserController() {
        super(UserController)
    }

    def index() {
        session.user = null;
    }

    def login() {

        def user = request.JSON.user;

        def res = session.user != null || userService.login(user.username, user.password);

        if (res){
            session.user = "admin"
            //redirect(controller: "layout", action: "index");
            render(text: new Wrapper().wrapVoid({ return true }) as JSON, contentType: CONTENT_TYPE, encoding: ENCODING);
        }else{
            def check = [:];
            check.success = false;
            check.exception = "Wrong enter";

            render(text: check as JSON, contentType: CONTENT_TYPE, encoding: ENCODING);
        }


    }

    def logout() {
        session.user = null
        redirect(action : "index")
    }
}
