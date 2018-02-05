package au.gov.nla.expres

import grails.gorm.services.Service

@Service(ResearchDocument)
interface ResearchDocumentService {

    ResearchDocument get(Serializable id)

    List<ResearchDocument> list(Map args)

    Long count()

    void delete(Serializable id)

    ResearchDocument save(ResearchDocument researchDocument)

}