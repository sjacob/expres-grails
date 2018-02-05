package au.gov.nla.expres

class CollectionItem {
    
    String bibId
    
    String callNumber
    
    String copy
    
    String archiveSpaceNumber
    
    String specialCollectionsPapers
    
    String hprmNumber
    
    String barcodeNumber
    
    String persistentIdentifier
    
    String collection
    
    String title
    
    String creator
    
    String dateOfItem
    
    String itemType
    
    String inscription
    
    String originalLanguage
    
    String publisher
    
    String acquisitionDetails
    
    String copyrightStatus
    
    String researchNotes
    
    String itemSize
    
    String medium
    
    String descriptionPhysical
    
    boolean originalForDisplay
    
    boolean boundWithPageTurns
    
    String maximumHeight
    
    String maximumAnglePageOpening
    
    String displayRecommendation
    
    String externalFrameSize
    
    String internalFrameSize
    
    String framingOrientation
    
    String frameSize
    
    String displayRequestsForPreservation
    
    String mattColour
    
    String framingNotes
    
    String supportType
    
    String supportNotes
    
    String supportNumberCode
    
    static hasMany = [
        loans: LoanItem,
        treatments: TreatmentItem,
        exhibitions: ExhibitionItem,
        movements: Movement,
        researchDocuments: ResearchDocument,
        photos: DigitalObject
    ]

    static constraints = {
    }
}
