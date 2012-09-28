/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gov.hhs.fha.nhinc.kmr.knowledgemodule;

import javax.ejb.Stateless;
import javax.jws.WebService;

/**
 *
 * @author tmn
 */
@WebService(serviceName = "KnowledgeModuleService", portName = "KnowledgeModulePort", endpointInterface = "gov.hhs.fha.nhinc.kmr.knowledgemodule.KnowledgeModulePortType", targetNamespace = "urn:gov:hhs:fha:nhinc:kmr:knowledgemodule", wsdlLocation = "META-INF/wsdl/KmrKnowledgeModule/KmrKnowledgeModuleService.wsdl")
@Stateless
public class KmrKnowledgeModule {

   public gov.hhs.fha.nhinc.kmr.kmtypes.ReferenceDataRefResponseType getKmRefData(gov.hhs.fha.nhinc.kmr.kmtypes.ReferenceDataRefRequestType param0) {
      return KmrKnowledgeModuleImpl.getInstance().getReferenceData(param0);
   }
   
   public gov.hhs.fha.nhinc.kmr.kmtypes.KmResponseType getKmByParams(gov.hhs.fha.nhinc.kmr.kmtypes.KmRequestType param0) {
      return KmrKnowledgeModuleImpl.getInstance().getKmByParams(param0);
   }

   public gov.hhs.fha.nhinc.kmr.kmtypes.KmMetaResponseType getKmMeta(gov.hhs.fha.nhinc.kmr.kmtypes.KmIdSearchRequestType param0) {
      return KmrKnowledgeModuleImpl.getInstance().getKmMeta(param0);
   }

   public gov.hhs.fha.nhinc.kmr.kmtypes.KmLatestLogicResponseType getKmLatestLogic(gov.hhs.fha.nhinc.kmr.kmtypes.KmIdSearchRequestType param0) {
      return KmrKnowledgeModuleImpl.getInstance().getKmLatestLogic(param0);
   }

   public gov.hhs.fha.nhinc.kmr.kmtypes.FindKmIdsResponseType findKmId(gov.hhs.fha.nhinc.kmr.kmtypes.KmRequestType param0) {
      return KmrKnowledgeModuleImpl.getInstance().findKmIds(param0);
   }

   public gov.hhs.fha.nhinc.kmr.kmtypes.KmIdSearchResponseType getKmById(gov.hhs.fha.nhinc.kmr.kmtypes.KmIdSearchRequestType param0) {
     return KmrKnowledgeModuleImpl.getInstance().getKmById(param0);
   }

   public gov.hhs.fha.nhinc.kmr.kmtypes.KmResponseType getKmByParamsRuntime(gov.hhs.fha.nhinc.kmr.kmtypes.KmRequestType param0) {
      return KmrKnowledgeModuleImpl.getInstance().getKmByParams_runtime(param0);
   }

   public gov.hhs.fha.nhinc.kmr.kmtypes.KmResponseTypeImportAck insertKms(gov.hhs.fha.nhinc.kmr.kmtypes.KmImportRequestType param0) {
      return KmrKnowledgeModuleImpl.getInstance().insertKms(param0);
   }

   public gov.hhs.fha.nhinc.kmr.kmtypes.KmResponseTypeImportAck updateKms(gov.hhs.fha.nhinc.kmr.kmtypes.KmImportRequestType param0) {
      return KmrKnowledgeModuleImpl.getInstance().updateKms(param0);
   }

}
