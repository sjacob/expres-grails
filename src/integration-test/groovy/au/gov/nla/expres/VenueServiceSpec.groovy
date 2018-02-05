package au.gov.nla.expres

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class VenueServiceSpec extends Specification {

    VenueService venueService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new Venue(...).save(flush: true, failOnError: true)
        //new Venue(...).save(flush: true, failOnError: true)
        //Venue venue = new Venue(...).save(flush: true, failOnError: true)
        //new Venue(...).save(flush: true, failOnError: true)
        //new Venue(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //venue.id
    }

    void "test get"() {
        setupData()

        expect:
        venueService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<Venue> venueList = venueService.list(max: 2, offset: 2)

        then:
        venueList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        venueService.count() == 5
    }

    void "test delete"() {
        Long venueId = setupData()

        expect:
        venueService.count() == 5

        when:
        venueService.delete(venueId)
        sessionFactory.currentSession.flush()

        then:
        venueService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        Venue venue = new Venue()
        venueService.save(venue)

        then:
        venue.id != null
    }
}
