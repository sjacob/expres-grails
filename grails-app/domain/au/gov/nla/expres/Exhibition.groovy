package au.gov.nla.expres

class Exhibition {
    
    String title
    
    String curator
    
    String fileNumber
    
    Date startDate
    
    Date endDate
    
    static hasMany = [
        exhibitionItems: ExhibitionItem,
        researchDocuments: ResearchDocument,
        venues: Venue
    ]
    
    static constraints = {
        title()
        curator()
        fileNumber()
        startDate()
        endDate()
    }
}
