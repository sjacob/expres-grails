package au.gov.nla.expres

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class LoanItemController {

    LoanItemService loanItemService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond loanItemService.list(params), model:[loanItemCount: loanItemService.count()]
    }

    def show(Long id) {
        respond loanItemService.get(id)
    }

    def create() {
        respond new LoanItem(params)
    }

    def save(LoanItem loanItem) {
        if (loanItem == null) {
            notFound()
            return
        }

        try {
            loanItemService.save(loanItem)
        } catch (ValidationException e) {
            respond loanItem.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'loanItem.label', default: 'LoanItem'), loanItem.id])
                redirect loanItem
            }
            '*' { respond loanItem, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond loanItemService.get(id)
    }

    def update(LoanItem loanItem) {
        if (loanItem == null) {
            notFound()
            return
        }

        try {
            loanItemService.save(loanItem)
        } catch (ValidationException e) {
            respond loanItem.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'loanItem.label', default: 'LoanItem'), loanItem.id])
                redirect loanItem
            }
            '*'{ respond loanItem, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        loanItemService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'loanItem.label', default: 'LoanItem'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'loanItem.label', default: 'LoanItem'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
