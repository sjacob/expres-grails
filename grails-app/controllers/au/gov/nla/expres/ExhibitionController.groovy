package au.gov.nla.expres

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class ExhibitionController {

    ExhibitionService exhibitionService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond exhibitionService.list(params), model:[exhibitionCount: exhibitionService.count()]
    }

    def show(Long id) {
        respond exhibitionService.get(id)
    }

    def create() {
        respond new Exhibition(params)
    }

    def save(Exhibition exhibition) {
        if (exhibition == null) {
            notFound()
            return
        }

        try {
            exhibitionService.save(exhibition)
        } catch (ValidationException e) {
            respond exhibition.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'exhibition.label', default: 'Exhibition'), exhibition.id])
                redirect exhibition
            }
            '*' { respond exhibition, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond exhibitionService.get(id)
    }

    def update(Exhibition exhibition) {
        if (exhibition == null) {
            notFound()
            return
        }

        try {
            exhibitionService.save(exhibition)
        } catch (ValidationException e) {
            respond exhibition.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'exhibition.label', default: 'Exhibition'), exhibition.id])
                redirect exhibition
            }
            '*'{ respond exhibition, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        exhibitionService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'exhibition.label', default: 'Exhibition'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'exhibition.label', default: 'Exhibition'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
