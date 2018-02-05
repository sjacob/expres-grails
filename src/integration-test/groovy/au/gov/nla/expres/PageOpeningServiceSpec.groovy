package au.gov.nla.expres

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class PageOpeningServiceSpec extends Specification {

    PageOpeningService pageOpeningService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new PageOpening(...).save(flush: true, failOnError: true)
        //new PageOpening(...).save(flush: true, failOnError: true)
        //PageOpening pageOpening = new PageOpening(...).save(flush: true, failOnError: true)
        //new PageOpening(...).save(flush: true, failOnError: true)
        //new PageOpening(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //pageOpening.id
    }

    void "test get"() {
        setupData()

        expect:
        pageOpeningService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<PageOpening> pageOpeningList = pageOpeningService.list(max: 2, offset: 2)

        then:
        pageOpeningList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        pageOpeningService.count() == 5
    }

    void "test delete"() {
        Long pageOpeningId = setupData()

        expect:
        pageOpeningService.count() == 5

        when:
        pageOpeningService.delete(pageOpeningId)
        sessionFactory.currentSession.flush()

        then:
        pageOpeningService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        PageOpening pageOpening = new PageOpening()
        pageOpeningService.save(pageOpening)

        then:
        pageOpening.id != null
    }
}
