package bg.pu.fmi

/**
 * Created by Emo on 3/18/2017.
 */
class Wrapper {

    def wrap(args, closure){
        def map =[:];
        try{
            def res = closure(args);
            map["success"] = true;
            map["result"] = res;
        }catch (ex){
            map["success"] = false;
            map["exception"] = ex.message;
        }

        return map;
    }

    def wrapVoid(closure){
        def map =[:];
        try{
            def res = closure();
            map["success"] = true;
            map["result"] = res;
        }catch (ex){
            map["success"] = false;
            map["exception"] = ex.message;
        }

        return map;
    }
}