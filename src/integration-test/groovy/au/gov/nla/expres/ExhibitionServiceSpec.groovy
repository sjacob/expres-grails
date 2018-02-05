package au.gov.nla.expres

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class ExhibitionServiceSpec extends Specification {

    ExhibitionService exhibitionService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new Exhibition(...).save(flush: true, failOnError: true)
        //new Exhibition(...).save(flush: true, failOnError: true)
        //Exhibition exhibition = new Exhibition(...).save(flush: true, failOnError: true)
        //new Exhibition(...).save(flush: true, failOnError: true)
        //new Exhibition(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //exhibition.id
    }

    void "test get"() {
        setupData()

        expect:
        exhibitionService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<Exhibition> exhibitionList = exhibitionService.list(max: 2, offset: 2)

        then:
        exhibitionList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        exhibitionService.count() == 5
    }

    void "test delete"() {
        Long exhibitionId = setupData()

        expect:
        exhibitionService.count() == 5

        when:
        exhibitionService.delete(exhibitionId)
        sessionFactory.currentSession.flush()

        then:
        exhibitionService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        Exhibition exhibition = new Exhibition()
        exhibitionService.save(exhibition)

        then:
        exhibition.id != null
    }
}
