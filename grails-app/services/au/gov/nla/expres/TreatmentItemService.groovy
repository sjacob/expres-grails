package au.gov.nla.expres

import grails.gorm.services.Service

@Service(TreatmentItem)
interface TreatmentItemService {

    TreatmentItem get(Serializable id)

    List<TreatmentItem> list(Map args)

    Long count()

    void delete(Serializable id)

    TreatmentItem save(TreatmentItem treatmentItem)

}