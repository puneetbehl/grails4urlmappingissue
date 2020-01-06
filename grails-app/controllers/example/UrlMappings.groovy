package example

class UrlMappings {

    static mappings = {
        "/"(controller: 'application', action:'index')
        '/images'(redirect:[uri:'/v2/images', permanent:true, keepParamsWhenRedirect: true])
        "/v2/$controller"(namespace: "v2")
        "500"(view: '/error')
        "404"(view: '/notFound')
    }
}
