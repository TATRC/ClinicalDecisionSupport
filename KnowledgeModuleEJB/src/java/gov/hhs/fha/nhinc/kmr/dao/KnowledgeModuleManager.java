/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gov.hhs.fha.nhinc.kmr.dao;

import gov.hhs.fha.nhinc.kmr.kmtypes.ExportResponseListType;
import gov.hhs.fha.nhinc.kmr.kmtypes.FindKmIdsResponseListType;
import gov.hhs.fha.nhinc.kmr.kmtypes.ImportResponseListType;
import gov.hhs.fha.nhinc.kmr.kmtypes.KmIdSearchRequestType;
import gov.hhs.fha.nhinc.kmr.kmtypes.KmImportRequestType;
import gov.hhs.fha.nhinc.kmr.kmtypes.KmLatestLogicResponseListType;
import gov.hhs.fha.nhinc.kmr.kmtypes.KmMetaResponseListType;
import gov.hhs.fha.nhinc.kmr.kmtypes.KmRequestType;
import gov.hhs.fha.nhinc.kmr.kmtypes.ReferenceDataRefRequestType;
import gov.hhs.fha.nhinc.kmr.kmtypes.ReferenceDataResponseType;
import gov.hhs.fha.nhinc.kmr.kmtypes.ResponseListType;


/**
 *
 * @author tmn
 */
public interface KnowledgeModuleManager {
   public ReferenceDataResponseType getRefData(ReferenceDataRefRequestType params);
   public KmMetaResponseListType getKmMeta(KmIdSearchRequestType params);
   public KmLatestLogicResponseListType getKmLatestLogic(KmIdSearchRequestType params);
   
   public FindKmIdsResponseListType findKmIds(KmRequestType params);
   public ExportResponseListType findByKmId(KmIdSearchRequestType params);
   public ResponseListType findByParams(KmRequestType params);
   public ResponseListType findByParamsForRuntime(KmRequestType params);
   public ImportResponseListType insertKms(KmImportRequestType params);
   public ImportResponseListType updateKms(KmImportRequestType params);
}
