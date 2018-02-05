package au.gov.nla.expres


class TreatmentItem {
    
    String priority
    
    String status
    
    String requestScale
    
    String totalTimeSpent
    
    boolean atRisk
    
    boolean treatmentSchedule
    
    Date nextScheduleDate
    
    static hasMany = [treatments: Treatment]

    static constraints = {
    }
}
