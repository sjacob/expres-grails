package au.gov.nla.expres

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class RequestController {

    RequestService requestService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond requestService.list(params), model:[requestCount: requestService.count()]
    }

    def show(Long id) {
        respond requestService.get(id)
    }

    def create() {
        respond new Request(params)
    }

    def save(Request request) {
        if (request == null) {
            notFound()
            return
        }

        try {
            requestService.save(request)
        } catch (ValidationException e) {
            respond request.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'request.label', default: 'Request'), request.id])
                redirect request
            }
            '*' { respond request, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond requestService.get(id)
    }

    def update(Request request) {
        if (request == null) {
            notFound()
            return
        }

        try {
            requestService.save(request)
        } catch (ValidationException e) {
            respond request.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'request.label', default: 'Request'), request.id])
                redirect request
            }
            '*'{ respond request, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        requestService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'request.label', default: 'Request'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'request.label', default: 'Request'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
