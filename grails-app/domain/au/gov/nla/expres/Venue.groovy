package au.gov.nla.expres


class Venue {
    
    String name
    
    Date startDate
    
    Date endDate
    
    static hasMany = [
        contacts: Contact
    ]

    static constraints = {
    }
}
