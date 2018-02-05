package au.gov.nla.expres

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class DigitalObjectController {

    DigitalObjectService digitalObjectService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond digitalObjectService.list(params), model:[digitalObjectCount: digitalObjectService.count()]
    }

    def show(Long id) {
        respond digitalObjectService.get(id)
    }

    def create() {
        respond new DigitalObject(params)
    }

    def save(DigitalObject digitalObject) {
        if (digitalObject == null) {
            notFound()
            return
        }

        try {
            digitalObjectService.save(digitalObject)
        } catch (ValidationException e) {
            respond digitalObject.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'digitalObject.label', default: 'DigitalObject'), digitalObject.id])
                redirect digitalObject
            }
            '*' { respond digitalObject, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond digitalObjectService.get(id)
    }

    def update(DigitalObject digitalObject) {
        if (digitalObject == null) {
            notFound()
            return
        }

        try {
            digitalObjectService.save(digitalObject)
        } catch (ValidationException e) {
            respond digitalObject.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'digitalObject.label', default: 'DigitalObject'), digitalObject.id])
                redirect digitalObject
            }
            '*'{ respond digitalObject, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        digitalObjectService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'digitalObject.label', default: 'DigitalObject'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'digitalObject.label', default: 'DigitalObject'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
