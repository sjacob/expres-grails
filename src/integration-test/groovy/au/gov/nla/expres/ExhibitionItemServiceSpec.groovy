package au.gov.nla.expres

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class ExhibitionItemServiceSpec extends Specification {

    ExhibitionItemService exhibitionItemService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new ExhibitionItem(...).save(flush: true, failOnError: true)
        //new ExhibitionItem(...).save(flush: true, failOnError: true)
        //ExhibitionItem exhibitionItem = new ExhibitionItem(...).save(flush: true, failOnError: true)
        //new ExhibitionItem(...).save(flush: true, failOnError: true)
        //new ExhibitionItem(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //exhibitionItem.id
    }

    void "test get"() {
        setupData()

        expect:
        exhibitionItemService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<ExhibitionItem> exhibitionItemList = exhibitionItemService.list(max: 2, offset: 2)

        then:
        exhibitionItemList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        exhibitionItemService.count() == 5
    }

    void "test delete"() {
        Long exhibitionItemId = setupData()

        expect:
        exhibitionItemService.count() == 5

        when:
        exhibitionItemService.delete(exhibitionItemId)
        sessionFactory.currentSession.flush()

        then:
        exhibitionItemService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        ExhibitionItem exhibitionItem = new ExhibitionItem()
        exhibitionItemService.save(exhibitionItem)

        then:
        exhibitionItem.id != null
    }
}
