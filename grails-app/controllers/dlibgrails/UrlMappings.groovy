package dlibgrails

class UrlMappings {

    static mappings = {
        "/$controller/$action?//$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

        "/$controller/$action?/"{
            constraints {
                // apply constraints here
            }
        }

        //"/"(view:"/index")
        //"/" ( controller: "user", aciton: "index")
        "/" ( controller: "user", aciton: "index")
        "/layout" (view: "index")
        "/login" ( controller: "user", aciton: "index")
        "500"(view:'/error')
        "404"(view:'/notFound')
    }
}
