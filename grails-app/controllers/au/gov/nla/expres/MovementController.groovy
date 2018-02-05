package au.gov.nla.expres

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class MovementController {

    MovementService movementService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond movementService.list(params), model:[movementCount: movementService.count()]
    }

    def show(Long id) {
        respond movementService.get(id)
    }

    def create() {
        respond new Movement(params)
    }

    def save(Movement movement) {
        if (movement == null) {
            notFound()
            return
        }

        try {
            movementService.save(movement)
        } catch (ValidationException e) {
            respond movement.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'movement.label', default: 'Movement'), movement.id])
                redirect movement
            }
            '*' { respond movement, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond movementService.get(id)
    }

    def update(Movement movement) {
        if (movement == null) {
            notFound()
            return
        }

        try {
            movementService.save(movement)
        } catch (ValidationException e) {
            respond movement.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'movement.label', default: 'Movement'), movement.id])
                redirect movement
            }
            '*'{ respond movement, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        movementService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'movement.label', default: 'Movement'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'movement.label', default: 'Movement'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
