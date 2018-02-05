package au.gov.nla.expres

import grails.gorm.services.Service

@Service(Movement)
interface MovementService {

    Movement get(Serializable id)

    List<Movement> list(Map args)

    Long count()

    void delete(Serializable id)

    Movement save(Movement movement)

}