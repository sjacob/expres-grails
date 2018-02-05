package au.gov.nla.expres

import grails.gorm.services.Service

@Service(Loan)
interface LoanService {

    Loan get(Serializable id)

    List<Loan> list(Map args)

    Long count()

    void delete(Serializable id)

    Loan save(Loan loan)

}