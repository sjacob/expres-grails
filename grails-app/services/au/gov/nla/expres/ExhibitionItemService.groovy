package au.gov.nla.expres

import grails.gorm.services.Service

@Service(ExhibitionItem)
interface ExhibitionItemService {

    ExhibitionItem get(Serializable id)

    List<ExhibitionItem> list(Map args)

    Long count()

    void delete(Serializable id)

    ExhibitionItem save(ExhibitionItem exhibitionItem)

}