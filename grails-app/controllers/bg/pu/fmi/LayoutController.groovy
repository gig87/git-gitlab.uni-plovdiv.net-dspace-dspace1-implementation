package bg.pu.fmi

/**
 * Created by Emo on 3/19/2017.
 */
class LayoutController {
    def index(){
        if(session.user == null){
            redirect(controller: "user", action: "index")
        }
    }
}
