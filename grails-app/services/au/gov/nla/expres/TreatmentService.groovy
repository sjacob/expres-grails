package au.gov.nla.expres

import grails.gorm.services.Service

@Service(Treatment)
interface TreatmentService {

    Treatment get(Serializable id)

    List<Treatment> list(Map args)

    Long count()

    void delete(Serializable id)

    Treatment save(Treatment treatment)

}