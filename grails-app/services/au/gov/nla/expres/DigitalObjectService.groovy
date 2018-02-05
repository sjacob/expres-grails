package au.gov.nla.expres

import grails.gorm.services.Service

@Service(DigitalObject)
interface DigitalObjectService {

    DigitalObject get(Serializable id)

    List<DigitalObject> list(Map args)

    Long count()

    void delete(Serializable id)

    DigitalObject save(DigitalObject digitalObject)

}