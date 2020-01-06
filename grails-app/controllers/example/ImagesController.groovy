package example

class ImagesController {
    static namespace = "v2"

	static responseFormats = ['json', 'xml']
	
    def index() {
        String imageUrl = grailsLinkGenerator.link([uri: request.requestURI, params: params, absolute: true])
        respond(url: imageUrl)
    }
}
