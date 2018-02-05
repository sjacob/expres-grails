package au.gov.nla.expres


class ExhibitionItem {
    
    String display
    
    String galleryPlacement
    
    String galleryReferenceNumber
    
    String relatedItems
    
    String requestBarcodeNumber
    
    boolean ecvm
    
    String ecvmDate
    
    String releasedBy
    
    String requestedBy
    
    String measuementForDisplay
    
    String treasuresGalleryIteration
    
    Date installedOn
    
    Date deinstalledOn
    
    String theme
    
    String story
    
    String captionShort
    
    String captionLong
    
    boolean permissionRequested
    
    String permissionGrantedFor
    
    boolean pinkCompleted
    
    boolean isItemDigitised
    
    boolean digitisationRequired
    
    String digitisationDetails
    
    String digitisationNotes
    
    String dlcJobNumber
    
    String reproductionMedium
    
    String reproductionDetails
    
    static hasMany = [
        pageOpenings: PageOpening
    ]

    static constraints = {
    }
}
