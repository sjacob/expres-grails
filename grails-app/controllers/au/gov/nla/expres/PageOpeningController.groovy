package au.gov.nla.expres

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class PageOpeningController {

    PageOpeningService pageOpeningService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond pageOpeningService.list(params), model:[pageOpeningCount: pageOpeningService.count()]
    }

    def show(Long id) {
        respond pageOpeningService.get(id)
    }

    def create() {
        respond new PageOpening(params)
    }

    def save(PageOpening pageOpening) {
        if (pageOpening == null) {
            notFound()
            return
        }

        try {
            pageOpeningService.save(pageOpening)
        } catch (ValidationException e) {
            respond pageOpening.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'pageOpening.label', default: 'PageOpening'), pageOpening.id])
                redirect pageOpening
            }
            '*' { respond pageOpening, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond pageOpeningService.get(id)
    }

    def update(PageOpening pageOpening) {
        if (pageOpening == null) {
            notFound()
            return
        }

        try {
            pageOpeningService.save(pageOpening)
        } catch (ValidationException e) {
            respond pageOpening.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'pageOpening.label', default: 'PageOpening'), pageOpening.id])
                redirect pageOpening
            }
            '*'{ respond pageOpening, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        pageOpeningService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'pageOpening.label', default: 'PageOpening'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'pageOpening.label', default: 'PageOpening'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
