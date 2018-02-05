package au.gov.nla.expres

import grails.gorm.services.Service

@Service(CollectionItem)
interface CollectionItemService {

    CollectionItem get(Serializable id)

    List<CollectionItem> list(Map args)

    Long count()

    void delete(Serializable id)

    CollectionItem save(CollectionItem collectionItem)

}