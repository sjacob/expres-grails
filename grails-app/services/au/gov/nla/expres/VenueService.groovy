package au.gov.nla.expres

import grails.gorm.services.Service

@Service(Venue)
interface VenueService {

    Venue get(Serializable id)

    List<Venue> list(Map args)

    Long count()

    void delete(Serializable id)

    Venue save(Venue venue)

}