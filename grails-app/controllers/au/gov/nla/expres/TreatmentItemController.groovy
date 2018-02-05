package au.gov.nla.expres

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class TreatmentItemController {

    TreatmentItemService treatmentItemService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond treatmentItemService.list(params), model:[treatmentItemCount: treatmentItemService.count()]
    }

    def show(Long id) {
        respond treatmentItemService.get(id)
    }

    def create() {
        respond new TreatmentItem(params)
    }

    def save(TreatmentItem treatmentItem) {
        if (treatmentItem == null) {
            notFound()
            return
        }

        try {
            treatmentItemService.save(treatmentItem)
        } catch (ValidationException e) {
            respond treatmentItem.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'treatmentItem.label', default: 'TreatmentItem'), treatmentItem.id])
                redirect treatmentItem
            }
            '*' { respond treatmentItem, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond treatmentItemService.get(id)
    }

    def update(TreatmentItem treatmentItem) {
        if (treatmentItem == null) {
            notFound()
            return
        }

        try {
            treatmentItemService.save(treatmentItem)
        } catch (ValidationException e) {
            respond treatmentItem.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'treatmentItem.label', default: 'TreatmentItem'), treatmentItem.id])
                redirect treatmentItem
            }
            '*'{ respond treatmentItem, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        treatmentItemService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'treatmentItem.label', default: 'TreatmentItem'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'treatmentItem.label', default: 'TreatmentItem'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
