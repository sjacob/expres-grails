package au.gov.nla.expres



class Loan {
    
    String loanNo
    
    String hprmFileNumber
    
    String loanType
    
    String reasonForLoan
    
    Date startDate
    
    Date endDate
    
    String loanFees
    
    String sfrReceived
    
    String otherLoanInfo
    
    static hasMany = [
        loanItems: LoanItem,
        contacts: Contact
    ]

    static constraints = {
    }
}
