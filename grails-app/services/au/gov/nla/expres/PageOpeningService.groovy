package au.gov.nla.expres

import grails.gorm.services.Service

@Service(PageOpening)
interface PageOpeningService {

    PageOpening get(Serializable id)

    List<PageOpening> list(Map args)

    Long count()

    void delete(Serializable id)

    PageOpening save(PageOpening pageOpening)

}