package au.gov.nla.expres

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class VenueController {

    VenueService venueService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond venueService.list(params), model:[venueCount: venueService.count()]
    }

    def show(Long id) {
        respond venueService.get(id)
    }

    def create() {
        respond new Venue(params)
    }

    def save(Venue venue) {
        if (venue == null) {
            notFound()
            return
        }

        try {
            venueService.save(venue)
        } catch (ValidationException e) {
            respond venue.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'venue.label', default: 'Venue'), venue.id])
                redirect venue
            }
            '*' { respond venue, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond venueService.get(id)
    }

    def update(Venue venue) {
        if (venue == null) {
            notFound()
            return
        }

        try {
            venueService.save(venue)
        } catch (ValidationException e) {
            respond venue.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'venue.label', default: 'Venue'), venue.id])
                redirect venue
            }
            '*'{ respond venue, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        venueService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'venue.label', default: 'Venue'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'venue.label', default: 'Venue'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
