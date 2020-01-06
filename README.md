# Grails 4 URLMapping problen with keepParamsWhenRedirect

[grails/grails-core#11451](https://github.com/grails/grails-core/issues/11451) URLMappings - keepParamsWhenRedirect mixes params from multiple requests

Given an `ImageController` as: 
```
class ImagesController {
    static namespace = "v2"

	static responseFormats = ['json', 'xml']
	
    def index() {
        String imageUrl = grailsLinkGenerator.link([uri: request.requestURI, params: params, absolute: true])
        respond(imageUrl: imageUrl)
    }
}
```
and the `URLMappings.groovy` with following mappings:
```
...
 '/images'(redirect:[uri:'/v2/images', permanent:true, keepParamsWhenRedirect: true])
 "/v2/$controller"(namespace: "v2")
```

When I request using CURL with query param `ids` as:

```
curl -L localhost:8080/images?ids=1,2,3 -H "Content-Type: application/json"
curl -L localhost:8080/images?ids=3,4,5 -H "Content-Type: application/json"
```
The response is correct each time. However, when change the query parameter to `foo=bar` as:
```
curl -L localhost:8080/images?foo=bar -H "Content-Type: application/json"
```
The response URL also contains previous parameter i.e. `ids=1%2C2%2C3` i.e. `http://localhost:8080/v2/images?ids=1%2C2%2C3&foo=bar` 
