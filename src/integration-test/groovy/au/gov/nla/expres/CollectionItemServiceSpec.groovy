package au.gov.nla.expres

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class CollectionItemServiceSpec extends Specification {

    CollectionItemService collectionItemService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new CollectionItem(...).save(flush: true, failOnError: true)
        //new CollectionItem(...).save(flush: true, failOnError: true)
        //CollectionItem collectionItem = new CollectionItem(...).save(flush: true, failOnError: true)
        //new CollectionItem(...).save(flush: true, failOnError: true)
        //new CollectionItem(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //collectionItem.id
    }

    void "test get"() {
        setupData()

        expect:
        collectionItemService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<CollectionItem> collectionItemList = collectionItemService.list(max: 2, offset: 2)

        then:
        collectionItemList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        collectionItemService.count() == 5
    }

    void "test delete"() {
        Long collectionItemId = setupData()

        expect:
        collectionItemService.count() == 5

        when:
        collectionItemService.delete(collectionItemId)
        sessionFactory.currentSession.flush()

        then:
        collectionItemService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        CollectionItem collectionItem = new CollectionItem()
        collectionItemService.save(collectionItem)

        then:
        collectionItem.id != null
    }
}
