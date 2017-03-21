package bg.pu.fmi

import grails.transaction.Transactional

@Transactional
class UserService {

    def login(String username, String password){
        if (username.equals("admin") && password.equals("admin")){
            return true;
        }
        return false;
    }
}
