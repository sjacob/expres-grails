package au.gov.nla.expres

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class TreatmentItemServiceSpec extends Specification {

    TreatmentItemService treatmentItemService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new TreatmentItem(...).save(flush: true, failOnError: true)
        //new TreatmentItem(...).save(flush: true, failOnError: true)
        //TreatmentItem treatmentItem = new TreatmentItem(...).save(flush: true, failOnError: true)
        //new TreatmentItem(...).save(flush: true, failOnError: true)
        //new TreatmentItem(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //treatmentItem.id
    }

    void "test get"() {
        setupData()

        expect:
        treatmentItemService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<TreatmentItem> treatmentItemList = treatmentItemService.list(max: 2, offset: 2)

        then:
        treatmentItemList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        treatmentItemService.count() == 5
    }

    void "test delete"() {
        Long treatmentItemId = setupData()

        expect:
        treatmentItemService.count() == 5

        when:
        treatmentItemService.delete(treatmentItemId)
        sessionFactory.currentSession.flush()

        then:
        treatmentItemService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        TreatmentItem treatmentItem = new TreatmentItem()
        treatmentItemService.save(treatmentItem)

        then:
        treatmentItem.id != null
    }
}
