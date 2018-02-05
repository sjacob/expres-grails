package au.gov.nla.expres

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class ResearchDocumentServiceSpec extends Specification {

    ResearchDocumentService researchDocumentService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new ResearchDocument(...).save(flush: true, failOnError: true)
        //new ResearchDocument(...).save(flush: true, failOnError: true)
        //ResearchDocument researchDocument = new ResearchDocument(...).save(flush: true, failOnError: true)
        //new ResearchDocument(...).save(flush: true, failOnError: true)
        //new ResearchDocument(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //researchDocument.id
    }

    void "test get"() {
        setupData()

        expect:
        researchDocumentService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<ResearchDocument> researchDocumentList = researchDocumentService.list(max: 2, offset: 2)

        then:
        researchDocumentList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        researchDocumentService.count() == 5
    }

    void "test delete"() {
        Long researchDocumentId = setupData()

        expect:
        researchDocumentService.count() == 5

        when:
        researchDocumentService.delete(researchDocumentId)
        sessionFactory.currentSession.flush()

        then:
        researchDocumentService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        ResearchDocument researchDocument = new ResearchDocument()
        researchDocumentService.save(researchDocument)

        then:
        researchDocument.id != null
    }
}
