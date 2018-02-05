package au.gov.nla.expres

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class CollectionItemController {

    CollectionItemService collectionItemService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond collectionItemService.list(params), model:[collectionItemCount: collectionItemService.count()]
    }

    def show(Long id) {
        respond collectionItemService.get(id)
    }

    def create() {
        respond new CollectionItem(params)
    }

    def save(CollectionItem collectionItem) {
        if (collectionItem == null) {
            notFound()
            return
        }

        try {
            collectionItemService.save(collectionItem)
        } catch (ValidationException e) {
            respond collectionItem.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'collectionItem.label', default: 'CollectionItem'), collectionItem.id])
                redirect collectionItem
            }
            '*' { respond collectionItem, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond collectionItemService.get(id)
    }

    def update(CollectionItem collectionItem) {
        if (collectionItem == null) {
            notFound()
            return
        }

        try {
            collectionItemService.save(collectionItem)
        } catch (ValidationException e) {
            respond collectionItem.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'collectionItem.label', default: 'CollectionItem'), collectionItem.id])
                redirect collectionItem
            }
            '*'{ respond collectionItem, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        collectionItemService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'collectionItem.label', default: 'CollectionItem'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'collectionItem.label', default: 'CollectionItem'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
