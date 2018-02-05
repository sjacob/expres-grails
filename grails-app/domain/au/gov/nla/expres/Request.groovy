package au.gov.nla.expres


class Request {
    
    String requestor
    
    String requestorPhone
    
    String requestorSection
    
    String event
    
    boolean charged
    
    String priority
    
    Date dateCompleted
    
    String treatmentDays
    
    String completingOfficer
    
    Date dateDispatched
    
    String conservationFile
    
    static hasMany = [requestItems: TreatmentItem]

    static constraints = {
    }
}
