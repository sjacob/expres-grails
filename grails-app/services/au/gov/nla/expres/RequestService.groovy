package au.gov.nla.expres

import grails.gorm.services.Service

@Service(Request)
interface RequestService {

    Request get(Serializable id)

    List<Request> list(Map args)

    Long count()

    void delete(Serializable id)

    Request save(Request request)

}