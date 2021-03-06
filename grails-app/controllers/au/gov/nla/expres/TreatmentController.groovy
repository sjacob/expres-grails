package au.gov.nla.expres

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class TreatmentController {

    TreatmentService treatmentService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond treatmentService.list(params), model:[treatmentCount: treatmentService.count()]
    }

    def show(Long id) {
        respond treatmentService.get(id)
    }

    def create() {
        respond new Treatment(params)
    }

    def save(Treatment treatment) {
        if (treatment == null) {
            notFound()
            return
        }

        try {
            treatmentService.save(treatment)
        } catch (ValidationException e) {
            respond treatment.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'treatment.label', default: 'Treatment'), treatment.id])
                redirect treatment
            }
            '*' { respond treatment, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond treatmentService.get(id)
    }

    def update(Treatment treatment) {
        if (treatment == null) {
            notFound()
            return
        }

        try {
            treatmentService.save(treatment)
        } catch (ValidationException e) {
            respond treatment.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'treatment.label', default: 'Treatment'), treatment.id])
                redirect treatment
            }
            '*'{ respond treatment, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        treatmentService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'treatment.label', default: 'Treatment'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'treatment.label', default: 'Treatment'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
