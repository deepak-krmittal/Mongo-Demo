class BootStrap {

    def populateDataService

    def init = { servletContext ->
        populateDataService.populateAuthors()
    }

    def destroy = {
    }
}
