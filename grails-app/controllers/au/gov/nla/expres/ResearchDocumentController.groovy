package au.gov.nla.expres

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class ResearchDocumentController {

    ResearchDocumentService researchDocumentService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond researchDocumentService.list(params), model:[researchDocumentCount: researchDocumentService.count()]
    }

    def show(Long id) {
        respond researchDocumentService.get(id)
    }

    def create() {
        respond new ResearchDocument(params)
    }

    def save(ResearchDocument researchDocument) {
        if (researchDocument == null) {
            notFound()
            return
        }

        try {
            researchDocumentService.save(researchDocument)
        } catch (ValidationException e) {
            respond researchDocument.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'researchDocument.label', default: 'ResearchDocument'), researchDocument.id])
                redirect researchDocument
            }
            '*' { respond researchDocument, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond researchDocumentService.get(id)
    }

    def update(ResearchDocument researchDocument) {
        if (researchDocument == null) {
            notFound()
            return
        }

        try {
            researchDocumentService.save(researchDocument)
        } catch (ValidationException e) {
            respond researchDocument.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'researchDocument.label', default: 'ResearchDocument'), researchDocument.id])
                redirect researchDocument
            }
            '*'{ respond researchDocument, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        researchDocumentService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'researchDocument.label', default: 'ResearchDocument'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'researchDocument.label', default: 'ResearchDocument'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
