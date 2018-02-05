package au.gov.nla.expres

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class LoanItemServiceSpec extends Specification {

    LoanItemService loanItemService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new LoanItem(...).save(flush: true, failOnError: true)
        //new LoanItem(...).save(flush: true, failOnError: true)
        //LoanItem loanItem = new LoanItem(...).save(flush: true, failOnError: true)
        //new LoanItem(...).save(flush: true, failOnError: true)
        //new LoanItem(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //loanItem.id
    }

    void "test get"() {
        setupData()

        expect:
        loanItemService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<LoanItem> loanItemList = loanItemService.list(max: 2, offset: 2)

        then:
        loanItemList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        loanItemService.count() == 5
    }

    void "test delete"() {
        Long loanItemId = setupData()

        expect:
        loanItemService.count() == 5

        when:
        loanItemService.delete(loanItemId)
        sessionFactory.currentSession.flush()

        then:
        loanItemService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        LoanItem loanItem = new LoanItem()
        loanItemService.save(loanItem)

        then:
        loanItem.id != null
    }
}
