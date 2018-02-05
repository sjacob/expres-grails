package au.gov.nla.expres

import grails.gorm.services.Service

@Service(LoanItem)
interface LoanItemService {

    LoanItem get(Serializable id)

    List<LoanItem> list(Map args)

    Long count()

    void delete(Serializable id)

    LoanItem save(LoanItem loanItem)

}