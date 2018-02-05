package au.gov.nla.expres

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class LoanController {

    LoanService loanService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond loanService.list(params), model:[loanCount: loanService.count()]
    }

    def show(Long id) {
        respond loanService.get(id)
    }

    def create() {
        respond new Loan(params)
    }

    def save(Loan loan) {
        if (loan == null) {
            notFound()
            return
        }

        try {
            loanService.save(loan)
        } catch (ValidationException e) {
            respond loan.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'loan.label', default: 'Loan'), loan.id])
                redirect loan
            }
            '*' { respond loan, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond loanService.get(id)
    }

    def update(Loan loan) {
        if (loan == null) {
            notFound()
            return
        }

        try {
            loanService.save(loan)
        } catch (ValidationException e) {
            respond loan.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'loan.label', default: 'Loan'), loan.id])
                redirect loan
            }
            '*'{ respond loan, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        loanService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'loan.label', default: 'Loan'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'loan.label', default: 'Loan'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
