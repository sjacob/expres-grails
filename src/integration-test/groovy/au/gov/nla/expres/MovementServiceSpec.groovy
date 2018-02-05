package au.gov.nla.expres

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class MovementServiceSpec extends Specification {

    MovementService movementService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new Movement(...).save(flush: true, failOnError: true)
        //new Movement(...).save(flush: true, failOnError: true)
        //Movement movement = new Movement(...).save(flush: true, failOnError: true)
        //new Movement(...).save(flush: true, failOnError: true)
        //new Movement(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //movement.id
    }

    void "test get"() {
        setupData()

        expect:
        movementService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<Movement> movementList = movementService.list(max: 2, offset: 2)

        then:
        movementList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        movementService.count() == 5
    }

    void "test delete"() {
        Long movementId = setupData()

        expect:
        movementService.count() == 5

        when:
        movementService.delete(movementId)
        sessionFactory.currentSession.flush()

        then:
        movementService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        Movement movement = new Movement()
        movementService.save(movement)

        then:
        movement.id != null
    }
}
