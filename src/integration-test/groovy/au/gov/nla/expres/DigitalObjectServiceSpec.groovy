package au.gov.nla.expres

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class DigitalObjectServiceSpec extends Specification {

    DigitalObjectService digitalObjectService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new DigitalObject(...).save(flush: true, failOnError: true)
        //new DigitalObject(...).save(flush: true, failOnError: true)
        //DigitalObject digitalObject = new DigitalObject(...).save(flush: true, failOnError: true)
        //new DigitalObject(...).save(flush: true, failOnError: true)
        //new DigitalObject(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //digitalObject.id
    }

    void "test get"() {
        setupData()

        expect:
        digitalObjectService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<DigitalObject> digitalObjectList = digitalObjectService.list(max: 2, offset: 2)

        then:
        digitalObjectList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        digitalObjectService.count() == 5
    }

    void "test delete"() {
        Long digitalObjectId = setupData()

        expect:
        digitalObjectService.count() == 5

        when:
        digitalObjectService.delete(digitalObjectId)
        sessionFactory.currentSession.flush()

        then:
        digitalObjectService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        DigitalObject digitalObject = new DigitalObject()
        digitalObjectService.save(digitalObject)

        then:
        digitalObject.id != null
    }
}
