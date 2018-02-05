package au.gov.nla.expres

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class ExhibitionItemController {

    ExhibitionItemService exhibitionItemService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond exhibitionItemService.list(params), model:[exhibitionItemCount: exhibitionItemService.count()]
    }

    def show(Long id) {
        respond exhibitionItemService.get(id)
    }

    def create() {
        respond new ExhibitionItem(params)
    }

    def save(ExhibitionItem exhibitionItem) {
        if (exhibitionItem == null) {
            notFound()
            return
        }

        try {
            exhibitionItemService.save(exhibitionItem)
        } catch (ValidationException e) {
            respond exhibitionItem.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'exhibitionItem.label', default: 'ExhibitionItem'), exhibitionItem.id])
                redirect exhibitionItem
            }
            '*' { respond exhibitionItem, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond exhibitionItemService.get(id)
    }

    def update(ExhibitionItem exhibitionItem) {
        if (exhibitionItem == null) {
            notFound()
            return
        }

        try {
            exhibitionItemService.save(exhibitionItem)
        } catch (ValidationException e) {
            respond exhibitionItem.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'exhibitionItem.label', default: 'ExhibitionItem'), exhibitionItem.id])
                redirect exhibitionItem
            }
            '*'{ respond exhibitionItem, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        exhibitionItemService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'exhibitionItem.label', default: 'ExhibitionItem'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'exhibitionItem.label', default: 'ExhibitionItem'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
