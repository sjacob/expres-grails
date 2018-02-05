package au.gov.nla.expres

import grails.gorm.services.Service

@Service(Exhibition)
interface ExhibitionService {

    Exhibition get(Serializable id)

    List<Exhibition> list(Map args)

    Long count()

    void delete(Serializable id)

    Exhibition save(Exhibition exhibition)

}