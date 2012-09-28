package gov.hhs.fha.nhinc.kmr.dao;

import gov.hhs.fha.nhinc.kmr.kmtypes.ACLPermissionType;
import gov.hhs.fha.nhinc.kmr.kmtypes.ACLSimpleType;
import gov.hhs.fha.nhinc.kmr.kmtypes.ExportResponseListType;
import gov.hhs.fha.nhinc.kmr.kmtypes.FindKmIdsResponseListType;
import gov.hhs.fha.nhinc.kmr.kmtypes.ImportAckType;
import gov.hhs.fha.nhinc.kmr.kmtypes.ImportResponseListType;
import gov.hhs.fha.nhinc.kmr.kmtypes.InferenceListType;
import gov.hhs.fha.nhinc.kmr.kmtypes.KMSpecialtyType;
import gov.hhs.fha.nhinc.kmr.kmtypes.KMVFactDependencyType;
import gov.hhs.fha.nhinc.kmr.kmtypes.KMVInferenceEngineDependencyType;
import gov.hhs.fha.nhinc.kmr.kmtypes.KMVPopulationDependencyType;
import gov.hhs.fha.nhinc.kmr.kmtypes.KMVTaskDependencyType;
import gov.hhs.fha.nhinc.kmr.kmtypes.KMVersionType;
import gov.hhs.fha.nhinc.kmr.kmtypes.KmByIdRequestListType;
import gov.hhs.fha.nhinc.kmr.kmtypes.KmByParamsType;
import gov.hhs.fha.nhinc.kmr.kmtypes.KmFullResponse;
import gov.hhs.fha.nhinc.kmr.kmtypes.KmIdType;
import gov.hhs.fha.nhinc.kmr.kmtypes.KmLatestLogicResponse;
import gov.hhs.fha.nhinc.kmr.kmtypes.KmLatestLogicResponseListType;
import gov.hhs.fha.nhinc.kmr.kmtypes.KmMetaResponse;
import gov.hhs.fha.nhinc.kmr.kmtypes.KmMetaResponseListType;
import gov.hhs.fha.nhinc.kmr.kmtypes.KmVersionListType;
import gov.hhs.fha.nhinc.kmr.kmtypes.ResponseListType;
import gov.hhs.fha.nhinc.kmr.model.ACLPermission;
import gov.hhs.fha.nhinc.kmr.model.KMSpecialty;
import gov.hhs.fha.nhinc.kmr.model.KMSpecialtyPK;
import gov.hhs.fha.nhinc.kmr.model.KMVAccessControlList;
import gov.hhs.fha.nhinc.kmr.model.KMVFactDependency;
import gov.hhs.fha.nhinc.kmr.model.KMVInferenceEngineDependency;
import gov.hhs.fha.nhinc.kmr.model.KMVPopulationDependency;
import gov.hhs.fha.nhinc.kmr.model.KMVTaskDependency;
import gov.hhs.fha.nhinc.kmr.model.KMVersion;
import gov.hhs.fha.nhinc.kmr.model.KnowledgeModule;
import gov.hhs.fha.nhinc.kmr.model.UserRole;
import gov.hhs.fha.nhinc.kmr.util.DateUtils;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import javax.xml.datatype.DatatypeConfigurationException;
import org.apache.log4j.Logger;

public class ResponseListBuilder {

   private final static Logger log = Logger.getLogger(ResponseListBuilder.class.getName());
   //--------------------------------------------------------------------------
   //  private vars and contants
   //--------------------------------------------------------------------------
   private static final String SELECT_KM_ID = "SELECT distinct KM_Version.KM_ID";
   private static final String SELECT_KMV_ID = "SELECT distinct KM_Version.KMV_ID, KM_Version.KM_ID";
   private static final String FROM_CLAUSE_BYID = " FROM KM_Version KM_Version" + " LEFT JOIN KnowledgeModule USING (KM_ID)" + " LEFT JOIN KMV_AccessControlList ON (KM_Version.ACL_ID = KMV_AccessControlList.ACL_ID)" + " LEFT JOIN ACL_Permission ON (KMV_AccessControlList.ACL_ID = ACL_Permission.ACL_ID)" + " LEFT JOIN UserRole ON (ACL_Permission.UR_ID = UserRole.UR_ID) ";
   private static final String FROM_CLAUSE = " FROM KM_Version KM_Version" + " LEFT JOIN KM_Specialty USING (KM_ID)" + " LEFT JOIN KnowledgeModule USING (KM_ID)" + " LEFT JOIN KMV_FactDependency USING (KMV_ID)" + " LEFT JOIN FactSpecification ON (KMV_FactDependency.FD_ID = FactSpecification.FD_ID)" + " LEFT JOIN KMV_PopulationDependency USING (KMV_ID)" + " LEFT JOIN PopulationSpecification ON (KMV_PopulationDependency.BP_ID = PopulationSpecification.BP_ID)" + " LEFT JOIN KMV_TaskDependency USING (KMV_ID)" + " LEFT JOIN TT_Specification ON (KMV_TaskDependency.TT_ID = TT_Specification.TT_ID)" + " LEFT JOIN KMV_InferenceEngineDependency  USING (KMV_ID)" + " LEFT JOIN KMV_AccessControlList ON (KM_Version.ACL_ID = KMV_AccessControlList.ACL_ID)" + " LEFT JOIN ACL_Permission ON (KMV_AccessControlList.ACL_ID = ACL_Permission.ACL_ID)" + " LEFT JOIN UserRole ON (ACL_Permission.UR_ID = UserRole.UR_ID)";
   private static final String WHERE_CLAUSE = " WHERE ";
   private static final String ORDER_BY = " ORDER BY KM_Version.KM_ID ASC";
   private static KMVBuilder kmvBuilder = null;
   private static FactsBuilder factBuilder = null;
   private static SpecialtyBuilder spBuilder = null;
   private static PopulationBuilder popBuilder = null;
   private static InferenceBuilder inferenceBuilder = null;
   private static TaskBuilder taskBuilder = null;
   private static ACLBuilder aclBuilder = null;
   private static DateUtils dateHelper = new DateUtils();

   //--------------------------------------------------------------------------
   //  Constructor
   //--------------------------------------------------------------------------
   public ResponseListBuilder() {
      // initialize all builer helper classes
      if (kmvBuilder == null) {
         kmvBuilder = new KMVBuilder();
      }
      if (factBuilder == null) {
         factBuilder = new FactsBuilder();
      }
      if (spBuilder == null) {
         spBuilder = new SpecialtyBuilder();
      }
      if (popBuilder == null) {
         popBuilder = new PopulationBuilder();
      }
      if (inferenceBuilder == null) {
         inferenceBuilder = new InferenceBuilder();
      }
      if (taskBuilder == null) {
         taskBuilder = new TaskBuilder();
      }
      if (aclBuilder == null) {
         aclBuilder = new ACLBuilder();
      }
   }

   //--------------------------------------------------------------------------
   //  Methods
   // ====================================================
   // buildFiltersXXXXX -
   // See if we can isolate to a list of KM_ID first.
   // Then locate details of all other objects per KMV_ID to send.
   // QUERY
   // Using a NATIVE sql because of the complex nature of the JOIN.
   // 1) Gather the filters into a WHERE clause.
   // 2) Build main sql and execute to get a filtered list of KMV_ID
   // THEN
   // Query DB for all KMV_ID contraint by user's filter(s)
   // If no filters are given, then will be an open query.
   // Dangerous, but ... garbage in, garbage out.
   // Then, for every KMV_ID,
   //    * create a KMVersionType object and add to it
   //       ** KM_Version data
   //       ** retrieve all dependencies into a KmVersionListType object
   //    * create a single KmFullResponse object and add to it
   //       ** KnowledgeModule data
   //       ** SpecialtyList
   // ====================================================
   //--------------------------------------------------------------------------
   /**
    * buildFilterStr - build a Native SQL out of all given filters
    * @param req
    * @param getKMID -  true, if want to do SELECT DISTINCT KM_ID
    *                  false, if want to do SELECT DISTINCT KMV_ID, KM_ID
    * @return String containing the native SELECT stmt
    */
   public String buildFilterStr(KmByParamsType req, boolean getKMID) {
      String sql = null;
      String filters = "";

      if (req != null) {
         filters = kmvBuilder.getFilters(req, filters);
         filters = spBuilder.getFilters(req, filters);

         filters = factBuilder.getDependencyFilters(req, filters);
         filters = factBuilder.getSpecificationFilters(req, filters);

         filters = popBuilder.getDependencyFilters(req, filters);
         filters = popBuilder.getSpecificationFilters(req, filters);

         filters = taskBuilder.getDependencyFilters(req, filters);
         filters = taskBuilder.getSpecificationFilters(req, filters);
         filters = inferenceBuilder.getFilters(req, filters);

         if (req.getACL() != null) {
            filters = aclBuilder.getFilters(req.getACL().getURName(), filters);
         }
      }

      if (getKMID) {
         sql = SELECT_KM_ID + FROM_CLAUSE;
      } else {
         sql = SELECT_KMV_ID + FROM_CLAUSE;
      }

      if (!filters.equals("")) {
         sql = sql + WHERE_CLAUSE + filters;
      }
      sql = sql + ORDER_BY;

      System.out.println("SELECT SQL= " + sql);

      return sql;
   }

   /**
    * buildFilterStrWithKmIdList - build a Native SQL with IN string
    *                              from given KM_IDs.
    * @param req
    * @return String containing the native SELECT stmt with IN clause of KM_IDs
    */
   public String buildFilterStrWithKmIdList(KmByIdRequestListType req) {
      String sql = null;
      String filters = "";

      // ----------------------------------
      // build an IN clause for all KM_ID
      // and an AND clause for ACL UR_NAME
      // ----------------------------------
      filters = kmvBuilder.getFiltersKmIdList(req.getKms().getKmId(), filters);
      filters = aclBuilder.getFilters(req.getACL().getURName(), filters);

      sql = SELECT_KMV_ID + FROM_CLAUSE_BYID;
      if (!filters.equals("")) {
         sql = sql + WHERE_CLAUSE + filters;
      }
      sql = sql + ORDER_BY;

      System.out.println("SELECT_KMV_ID SQL= " + sql);

      return sql;
   }

   /**
    * createSimpleKm
    * 
    * @param kmid
    * @param kmvBuilder
    * @param kmVersionList
    * @param em
    * @return KmFullResponse
    *
   private KmFullResponse createSimpleKm(Integer kmid,
   KMVBuilder kmvBuilder,
   KmVersionListType kmVersionList,
   EntityManager em) {
   KmFullResponse km = null;     //start off with an empty <km>

   log.debug("Building <SpecialtyList> and <KmVersionList> for KM_ID=" + kmid);

   km = kmvBuilder.buildSimpleElems(em, kmid);
   km.setKmVersionList(kmVersionList);

   return km;
   }*/
   public KmMetaResponseListType buildKmMetaResponseList(List<Integer> kmIds, EntityManager em) throws DatatypeConfigurationException {
      KmMetaResponseListType responseList = new KmMetaResponseListType();

      Query query = null;
      Integer total_kmIds = kmIds.size();
      KnowledgeModule km;
      Integer currKmId;

      for (int i = 0; i < total_kmIds; i++) {

         currKmId = kmIds.get(i);

         System.out.println("***GET KNOWLEDGEMODULE KM_ID=" + currKmId);

         query = em.createNamedQuery("KnowledgeModule.findByKmId");
         query.setParameter("kmId", currKmId);

         km = (KnowledgeModule) query.getSingleResult();

         KmMetaResponse aKmMetaResp = new KmMetaResponse();
         aKmMetaResp.setId(km.getKmId());
         aKmMetaResp.setTitle(km.getKMName());
         aKmMetaResp.setSummary(km.getKMDescription());
         aKmMetaResp.setCreated(dateHelper.Date2XMLDate(km.getCreatedTimestamp()).toString());
         aKmMetaResp.setUpdated(dateHelper.Date2XMLDate(km.getLastModifiedTimestamp()).toString());

         //TEMPORARY constant only..need new field in table before true impl.
         aKmMetaResp.setType("http://b3mn.org/stencilset/bpmn2.0#");

         //Thumbnails
         // Will have to pull from the latest KM_VERSION record.

         responseList.getKm().add(aKmMetaResp);
      }
      return responseList;
   }

   //
   public KmLatestLogicResponseListType buildKmLastLogicResponseList(List<Integer> kmIds, EntityManager em) throws DatatypeConfigurationException {

      KmLatestLogicResponseListType responseList = new KmLatestLogicResponseListType();
      Query query = null;
      Integer total_kmIds = kmIds.size();
      KnowledgeModule km;
      Integer currKmId;

      KMVersion kmv;
      Integer latestKMVersionNumber;

      Iterator<KMVersion> kmvIter = null;
      String latestIntermediateLogic = null;
      String latestNativeLogic = null;
      byte[] latestBinaryLogic = null;
      Integer latestKmvId = null;

      //-------------------------------------------------
      //For each KM_ID, get the LatestVersionNum (KMV_ID) 
      //and get the KM_VERSION record of that KMV_ID.
      //Then save the KM_VERSION.Logic_NativeForm to the return object.
      //-------------------------------------------------
      for (int i = 0; i < total_kmIds; i++) {

         currKmId = kmIds.get(i);

         System.out.println("***GET KNOWLEDGEMODULE KM_ID=" + currKmId);

         query = null;
         query = em.createNamedQuery("KnowledgeModule.findByKmId");
         query.setParameter("kmId", currKmId);

         km = (KnowledgeModule) query.getSingleResult();
         latestKMVersionNumber = km.getLatestVersionNum();

         System.out.println("\n\n============>LATEST latestKMVersionNumber=" + latestKMVersionNumber);

         kmvIter = km.getKMVersionCollection().iterator();
         latestIntermediateLogic = null;
         latestNativeLogic = null;
         latestBinaryLogic = null;
         latestKmvId = null;
         while (kmvIter.hasNext() && (latestKmvId == null)) {
            kmv = kmvIter.next();
            if (kmv.getKmId().getKmId().equals(currKmId) &&
                    (kmv.getKMVersionNum() == latestKMVersionNumber.intValue())) {
               //-------------------------------------------------------
               //FOUND the latest KMV version number of KM_ID = currKmId,
               //so can get the Native Logic of this KMV record.
               //-------------------------------------------------------
               latestIntermediateLogic = kmv.getLogicIntermediateForm();
               latestBinaryLogic = kmv.getLogicBinaryForm();
               latestNativeLogic = kmv.getLogicNativeForm();
               latestKmvId = kmv.getKmvId();
               System.out.println("\n\n============>LATEST lastest KMV_ID=" + latestKmvId);
            }
         }
         //--------------- Fill out return object ---------------------
         KmLatestLogicResponse aKmLatestLogicResp = new KmLatestLogicResponse();
         aKmLatestLogicResp.setId(latestKmvId.intValue());
         aKmLatestLogicResp.setLogicNativeForm(latestNativeLogic);
         aKmLatestLogicResp.setLogicIntermediateForm(latestIntermediateLogic);
         aKmLatestLogicResp.setLogicBinaryForm(latestBinaryLogic);

         responseList.getKm().add(aKmLatestLogicResp);
      }
      return responseList;
   }

   /**
    * buildResponseList (WORKBENCH)
    *    Given a list of KMV_IDs, query each one and
    *    gather all related metadata and store into ResponseListType object for return.
    * 
    * @param kmvIds
    * @param em
    * @return
    */
   public ResponseListType buildResponseList(List<KMVersion> kmvIds, EntityManager em) {

      ResponseListType responseList = new ResponseListType();
      Query query = null;

      // ----------------------------------------------------------
      // Iterate thru every KMV_ID and build
      // ----------------------------------------------------------
      Integer oldKmId = new Integer(0);
      Integer kmvFound_size = kmvIds.size();
      KmVersionListType kmVersionList = new KmVersionListType();

      KMVersion kmv = null;

      for (int i = 0; i < kmvFound_size; i++) {

         Integer currKmvId = kmvIds.get(i).getKmvId();
         Integer currKmId = kmvIds.get(i).getKmId().getKmId();

         System.out.println("KMV=" + currKmvId + " KM=" + currKmId + " old KM=" + oldKmId);

         // ----------------------------------------------------
         //   (NOT first KM_ID) AND (new KM_ID set)
         //
         // Check to see if KmId for this KMV_ID is diff then last one,
         // BUT is not the very first KMV_ID being processed.
         // If diff, then <km> has not yet been built for
         // these KMV_ID, so can build it now.
         // Then add <KmVersionList> object to it, thereby giving a
         // complete <km> object.
         // Then add it to the <responseList> .. ResponseListType object.
         // ----------------------------------------------------
         if ((oldKmId != 0) && (!currKmId.equals(oldKmId))) {

            System.out.println("---> Building <km> for KM_ID=" + oldKmId);

            KmFullResponse km = null;     //start off with an empty <km>
            km = kmvBuilder.buildElems(em, oldKmId);

            //TODO replace spBuilder.buildElems() with embedded km Specialty Collection
            // but will need to do it within kmvBuilder.buildElems()

            km.setSpecialtyList(spBuilder.buildElems(em, oldKmId));
            km.setKmVersionList(kmVersionList);
            responseList.getKm().add(km);

            // Create an empty <kmVersionList> for next KM_ID
            kmVersionList = new KmVersionListType();
         }

         // =================================================
         // Build KM_VERSION - expecting a single rec since we
         // know the unique KMV_ID
         //
         // NOTE:  KMVersion.toKmTypes() will initialize
         //        the KmFullResponse object.
         // NOTE:  Query returns a MODEL class so always have to
         //        remap to KMTYPES class.
         // -----------------------------------------------
         System.out.println("***GET KM_VERSION KMV_ID=" + currKmvId);

         query = em.createNamedQuery("KMVersion.findByKmvId");
         query.setParameter("kmvId", currKmvId);

         kmv = (KMVersion) query.getSingleResult();
         KMVersionType kmVersion = kmv.toKmTypes();

         // -----------------------------------------------
         // Build FACT DEPENDENCY - possible multi-recs
         //
         // kmtypesFdCollection --> kmtype container to store/map found items
         // facts --> found collection of items tied to curr KMV_ID
         // fact  --> a single found item.
         //
         // 1) remap all indiv fact to kmtype-fact
         // 2) add newly filled kmtype-facts to kmtype-kmVersion collection.
         // -----------------------------------------------
         System.out.println("FACTDEP SIZE=" + kmv.getKMVFactDependencyCollection().size());
         if (kmv.getKMVFactDependencyCollection().size() > 0) {
            kmVersion.setFactSpecificationList(
                    factBuilder.buildElems(kmv.getKMVFactDependencyCollection()));
         }
         // -----------------------------------------------
         // Build POPULATION DEPENDENCY - possible multi-recs
         // -----------------------------------------------
         log.debug("***GET POPULATION with kmvid = " + currKmvId);
         if (kmv.getKMVPopulationDependencyCollection().size() > 0) {
            kmVersion.setPopulationDependencyList(
                    popBuilder.buildElems(kmv.getKMVPopulationDependencyCollection()));
         }
         // -----------------------------------------------
         // TASK Build DEPENDENCY - possible multi-recs
         // -----------------------------------------------
         log.debug("***GET TASK with kmvid = " + currKmvId);
         if (kmv.getKMVTaskDependencyCollection().size() > 0) {
            kmVersion.setTaskList(
                    taskBuilder.buildElems(kmv.getKMVTaskDependencyCollection()));
         }
         // -----------------------------------------------
         // Build INFERENCE DEPENDENCY - possible multi-recs
         // -----------------------------------------------
         log.debug("***GET INFERENCE with kmvid = " + currKmvId);
         InferenceListType inferenceEngines =
                 inferenceBuilder.buildElems(kmv.getKMVInferenceEngineDependency());
         kmVersion.setInferenceList(inferenceEngines);

         // ----------------------------------
         // Add created kmVersion to KmVersionList
         // ----------------------------------
         System.out.println("[findByParams]ADDing <kmVersion> to <KmVersionList> ");
         kmVersionList.getKmVersion().add(kmVersion);

         // ----------------------------------
         // If at the very LAST KMV_ID,
         // then build <km> for the current KM_ID.
         // ----------------------------------
         if (i == (kmvFound_size - 1)) {
            System.out.println("---> Building LAST <km> for KM_ID=" + currKmId);

            KmFullResponse km = null;     //start off with an empty <km>
            km = kmvBuilder.buildElems(em, currKmId);
            km.setSpecialtyList(spBuilder.buildElems(em, currKmId));
            km.setKmVersionList(kmVersionList);

            responseList.getKm().add(km);
         }

         // set oldKmId to just processed KM_ID
         oldKmId = currKmId;

      }//end-for-kmvFound_size

      return responseList;
   }

   /**
    * buildSimpleResponseList (RUNTIME)
    *    Given a list of KMV_IDs, query each one and
    *    gather a MINIMAL set of related metadata and store into ResponseListType object for return.
    * Metadata to be returned are:
   <ns2:kmId>1</ns2:kmId>
   <ns2:kmName>myKM</ns2:kmName>
   <ns2:latestVersionNum>0</ns2:latestVersionNum>
   <ns2:validationStatus>UNDER REVIEW</ns2:validationStatus>
    * and 
   <kmVersion>
   <KM_VersionNum>1</KM_VersionNum>
   <KMV_Name>myKM - v1</KMV_Name>
   <ValidationStatus>DRAFT</ValidationStatus>
   <LMT_ID>0</LMT_ID>
   <Logic_IntermediateForm>intermediate form - A</Logic_IntermediateForm>
   <Logic_NativeForm>bmF0aXZlIGZvcm0gLSBEUk9PTFMgLSBB</Logic_NativeForm>
   </kmVersion>

    * @param kmvIds
    * @param em
    * @return ResponseListType
    */
   public ResponseListType buildResponseListForRuntime(List<KMVersion> kmvIds, EntityManager em) {

      ResponseListType responseList = new ResponseListType();
      Query query = null;

      // ----------------------------------------------------------
      // Iterate thru every KMV_ID and build
      // ----------------------------------------------------------
      Integer oldKmId = new Integer(0);
      Integer kmvFound_size = kmvIds.size();
      KmVersionListType kmVersionList = new KmVersionListType();

      KMVersion kmv = null;

      for (int i = 0; i < kmvFound_size; i++) {

         Integer currKmvId = kmvIds.get(i).getKmvId();
         Integer currKmId = kmvIds.get(i).getKmId().getKmId();

         System.out.println("KMV=" + currKmvId + " KM=" + currKmId + " old KM=" + oldKmId);

         // ----------------------------------------------------
         //   (NOT first KM_ID) AND (new KM_ID set)
         //
         // Check to see if KmId for this KMV_ID is diff then last one,
         // BUT is not the very first KMV_ID being processed.
         // If diff, then <km> has not yet been built for
         // these KMV_ID, so can build it now.
         // Then add <KmVersionList> object to it, thereby giving a
         // complete <km> object.
         // Then add it to the <responseList> .. ResponseListType object.
         // ----------------------------------------------------
         if ((oldKmId != 0) && (!currKmId.equals(oldKmId))) {

            System.out.println("---> Building <km> for KM_ID=" + oldKmId);

            //responseList.getKm().add(this.createSimpleKm(oldKmId, kmvBuilder, kmVersionList, em));
            KmFullResponse km = null;
            km = kmvBuilder.buildSimpleElems(em, oldKmId);
            km.setKmVersionList(kmVersionList);
            responseList.getKm().add(km);

            // Create an empty <kmVersionList> for next KM_ID
            kmVersionList = new KmVersionListType();
         }

         // =================================================
         // Build KM_VERSION - expecting a single rec since we
         // know the unique KMV_ID
         //
         // NOTE:  KMVersion.toKmTypes() will initialize
         //        the KmFullResponse object.
         // NOTE:  Query returns a MODEL class so always have to
         //        remap to KMTYPES class.
         // -----------------------------------------------
         System.out.println("***GET KM_VERSION KMV_ID=" + currKmvId);

         query = em.createNamedQuery("KMVersion.findByKmvId");
         query.setParameter("kmvId", currKmvId);

         kmv = (KMVersion) query.getSingleResult();
         KMVersionType kmVersion = kmv.toKmTypes_simple();
         // ===================================================

         // ----------------------------------
         // Add created kmVersion to KmVersionList
         // ----------------------------------
         System.out.println("[findByParams]ADDing <kmVersion> to <KmVersionList> ");
         kmVersionList.getKmVersion().add(kmVersion);

         // ----------------------------------
         // If at the very LAST KMV_ID,
         // then build <km> for the current KM_ID.
         // ----------------------------------
         if (i == (kmvFound_size - 1)) {
            System.out.println("---> Building LAST <km> for KM_ID=" + currKmId);

            //responseList.getKm().add(this.createSimpleKm(currKmId, kmvBuilder, kmVersionList, em));
            KmFullResponse km = null;
            km = kmvBuilder.buildSimpleElems(em, currKmId);
            km.setKmVersionList(kmVersionList);
            responseList.getKm().add(km);
         }

         // set oldKmId to just processed KM_ID
         oldKmId = currKmId;

      }//end-for-kmvFound_size      

      return responseList;
   }

   /**
    * buildResponseListOfKmIdOnly (EXPORT Pass 1)
    *    Given a list of KM_IDs, 
    *    rewrap into FindKmIdsResponseListType (a list of KM_IDs).
    *
    * @param kmIds
    * @param em
    * @return FindKmIdsResponseListType
    */
   public FindKmIdsResponseListType buildResponseListOfKmIdOnly(
           List<KnowledgeModule> kmIds, EntityManager em, String reqId) {
      FindKmIdsResponseListType responseList = new FindKmIdsResponseListType();

      // ----------------------------------------------------------
      // Iterate thru every KMV_ID and build
      // ----------------------------------------------------------

      Integer kmFound_size = kmIds.size();
      KmIdType kmIdList = new KmIdType();

      for (int i = 0; i < kmFound_size; i++) {
         Integer currKmId = kmIds.get(i).getKmId();
         kmIdList.getKmId().add(currKmId);
      }

      responseList.setKms(kmIdList);
      return responseList;
   }

   /**
    * buildResponseListById (EXPORT Pass 2)
    *    Given a list of KMV_IDs, query each one and
    *    gather all related metadata and store into ExportResponseListType object for return.
    * @param kmvIds
    * @param em
    * @return
    */
   public ExportResponseListType buildResponseListById(List<KMVersion> kmvIds, EntityManager em) {

      ExportResponseListType responseList = new ExportResponseListType();
      Query query = null;

      // ----------------------------------------------------------
      // Iterate thru every KMV_ID and build
      // ----------------------------------------------------------
      Integer oldKmId = new Integer(0);
      Integer kmvFound_size = kmvIds.size();
      KmVersionListType kmVersionList = new KmVersionListType();

      KMVersion kmv = null;

      for (int i = 0; i < kmvFound_size; i++) {

         Integer currKmvId = kmvIds.get(i).getKmvId();
         Integer currKmId = kmvIds.get(i).getKmId().getKmId();

         System.out.println("KMV=" + currKmvId + " KM=" + currKmId + " old KM=" + oldKmId);

         // ----------------------------------------------------
         //   (NOT first KM_ID) AND (new KM_ID set)
         //
         // Check to see if KmId for this KMV_ID is diff then last one,
         // BUT is not the very first KMV_ID being processed.
         // If diff, then <km> has not yet been built for
         // these KMV_ID, so can build it now.
         // Then add <KmVersionList> object to it, thereby giving a
         // complete <km> object.
         // Then add it to the <responseList> .. ResponseListType object.
         // ----------------------------------------------------
         if ((oldKmId != 0) && (!currKmId.equals(oldKmId))) {

            System.out.println("---> Building <km> for KM_ID=" + oldKmId);

            KmFullResponse km = null;     //start off with an empty <km>
            km = kmvBuilder.buildElems(em, oldKmId);

            //TODO replace spBuilder.buildElems() with embedded km Specialty Collection
            // but will need to do it within kmvBuilder.buildElems()

            km.setSpecialtyList(spBuilder.buildElems(em, oldKmId));
            km.setKmVersionList(kmVersionList);
            responseList.getKm().add(km);

            // Create an empty <kmVersionList> for next KM_ID
            kmVersionList = new KmVersionListType();
         }

         // =================================================
         // Build KM_VERSION - expecting a single rec since we
         // know the unique KMV_ID
         //
         // NOTE:  KMVersion.toKmTypes() will initialize
         //        the KmFullResponse object.
         // NOTE:  Query returns a MODEL class so always have to
         //        remap to KMTYPES class.
         // -----------------------------------------------
         System.out.println("***GET KM_VERSION KMV_ID=" + currKmvId);

         query = em.createNamedQuery("KMVersion.findByKmvId");
         query.setParameter("kmvId", currKmvId);

         kmv = (KMVersion) query.getSingleResult();
         KMVersionType kmVersion = kmv.toKmTypes();

         // -----------------------------------------------
         // Build FACT DEPENDENCY - possible multi-recs
         //
         // kmtypesFdCollection --> kmtype container to store/map found items
         // facts --> found collection of items tied to curr KMV_ID
         // fact  --> a single found item.
         //
         // 1) remap all indiv fact to kmtype-fact
         // 2) add newly filled kmtype-facts to kmtype-kmVersion collection.
         // -----------------------------------------------
         System.out.println("FACTDEP SIZE=" + kmv.getKMVFactDependencyCollection().size());
         if (kmv.getKMVFactDependencyCollection().size() > 0) {
            kmVersion.setFactSpecificationList(
                    factBuilder.buildElems(kmv.getKMVFactDependencyCollection()));
         }
         // -----------------------------------------------
         // Build POPULATION DEPENDENCY - possible multi-recs
         // -----------------------------------------------
         log.debug("***GET POPULATION with kmvid = " + currKmvId);
         if (kmv.getKMVPopulationDependencyCollection().size() > 0) {
            kmVersion.setPopulationDependencyList(
                    popBuilder.buildElems(kmv.getKMVPopulationDependencyCollection()));
         }
         // -----------------------------------------------
         // TASK Build DEPENDENCY - possible multi-recs
         // -----------------------------------------------
         log.debug("***GET TASK with kmvid = " + currKmvId);
         if (kmv.getKMVTaskDependencyCollection().size() > 0) {
            kmVersion.setTaskList(
                    taskBuilder.buildElems(kmv.getKMVTaskDependencyCollection()));
         }
         // -----------------------------------------------
         // Build INFERENCE DEPENDENCY - possible multi-recs
         // -----------------------------------------------
         log.debug("***GET INFERENCE with kmvid = " + currKmvId);
         InferenceListType inferenceEngines =
                 inferenceBuilder.buildElems(kmv.getKMVInferenceEngineDependency());
         kmVersion.setInferenceList(inferenceEngines);

         // ----------------------------------
         // Add created kmVersion to KmVersionList
         // ----------------------------------
         System.out.println("[findByParams]ADDing <kmVersion> to <KmVersionList> ");
         kmVersionList.getKmVersion().add(kmVersion);

         // ----------------------------------
         // If at the very LAST KMV_ID,
         // then build <km> for the current KM_ID.
         // ----------------------------------
         if (i == (kmvFound_size - 1)) {
            System.out.println("---> Building LAST <km> for KM_ID=" + currKmId);

            KmFullResponse km = null;     //start off with an empty <km>
            km = kmvBuilder.buildElems(em, currKmId);
            km.setSpecialtyList(spBuilder.buildElems(em, currKmId));
            km.setKmVersionList(kmVersionList);

            responseList.getKm().add(km);
         }

         // set oldKmId to just processed KM_ID
         oldKmId = currKmId;

      }//end-for-kmvFound_size

      return responseList;
   }

   /**
    * 
    * @param kms
    * @param acl
    * @param em
    * @return
    * @throws DatatypeConfigurationException
    */
   public ImportResponseListType insertAndBuildAck(
           List<KmFullResponse> kms, ACLSimpleType acl, EntityManager em) throws DatatypeConfigurationException {
      ImportResponseListType responseList = new ImportResponseListType();
      KmFullResponse kmrec = null;
      KnowledgeModule km = null;
      ImportAckType ack = null;
      int total = 0;

      // loop through all KMs annd build the JPA objects.
      for (int i = 0; i < kms.size(); i++) {

         kmrec = kms.get(i);
         try {
            em.getTransaction().begin();

            //=================================
            // SETTING KNOWLEDGEMODULE
            // NOTE: Since this is a first insert, we will set following defaults:
            //    KM_OrganizationLevel = PERSONAL
            //    ValidationStatus = "DRAFT"
            //    ProductionVersionNum = null
            //    CreatedBy_AuthorID = incoming User ID (in ACL)
            //    CreatedBy_AusthorName = incoming Username (in ACL)
            //    CreatedTimestamp = today's date
            //=================================
            System.out.println("KM_NAME=" + kmrec.getKmName() + "  KM_TYPE=" + kmrec.getKmType());
            km = new KnowledgeModule(kmrec);
            //------------SETTING DEFAULTS---------------           
            km.setKMOrganizationLevel("PERSONAL");
            km.setValidationStatus("DRAFT");
            km.setCreatedByAuthorID(acl.getAuthorId());
            km.setCreatedTimestamp(dateHelper.getCurrDateTimeAsDate());
            km.setLatestVersionNum(1);
            //-----------PERSIST to GET KM_ID------------
            //need to do so that dependencies won't FK error out.
            em.persist(km);
            em.flush();
            System.out.println("new KM_ID=" + km.getKmId());

            //=================================
            //SETTING KM SPECIALTYs
            //=================================
            //------------INSTANTIATE---------------
            KMSpecialtyType kmrecSP = null;
            total = kmrec.getSpecialtyList().getKMSpecialty().size();
            List<KMSpecialty> specialties = new ArrayList<KMSpecialty>(total);
            //------------BUILD model FACTs---------------
            for (int depid = 0; depid < total; depid++) {
               kmrecSP = kmrec.getSpecialtyList().getKMSpecialty().get(depid);
               specialties.add(spBuilder.buildDatabaseObject(km.getKmId(), kmrecSP, em));
               km.setKMSpecialtyCollection(specialties);
            }

            //=================================
            //SETTING KM_VERSION .. inserting can only have 1 KMV
            //Create and Insert KMV and dependencies.
            //=================================
            int newVersionNumber = 1;
            KMVersion newKmv = this.createKmvWithDepedencies(
                    km.getKmId(), newVersionNumber,
                    kmrec.getKmVersionList().getKmVersion().get(0),
                    em, kmrec.getCreatedByAuthorID(), kmrec.getCreatedByAuthorName());

            //----------------------------------
            //ADd newKmv to kmvs list.
            //----------------------------------
            List<KMVersion> kmvs = new ArrayList<KMVersion>(1);
            kmvs.add(newKmv);
            //----------------------------------
            // Add KMVs list into final km list.
            //----------------------------------
            km.setKMVersionCollection(kmvs);

            //===============================================
            //INSERT and COMMIT
            // ----------------------------------------------
            System.out.println("INSERT & COMMIT");
            em.persist(km);
            em.getTransaction().commit();
            //em.flush();

            //===============================================
            //Build SUCCESS ACK for KM
            // ----------------------------------------------
            System.out.println("Build Success ACK for KM=" + km.getKMName());
            ack = new ImportAckType();
            ack.setKmId(km.getKmId());
            ack.setKmName(km.getKMName());
            ack.setImportStatus("SUCCESS");
            ack.setDescription("Successful Import");

         } catch (Exception e) {
            e.printStackTrace();

            //===============================================
            //Build FAILed ACK for KM
            // ----------------------------------------------
            System.out.println("Build Failed ACK for KM=" + km.getKMName());
            ack = new ImportAckType();
            ack.setKmId(km.getKmId());
            ack.setKmName(km.getKMName());
            ack.setImportStatus("FAIL");
            ack.setDescription("Failed Import: " + e.toString());
            em.getTransaction().rollback();
         }
         //em.close();

         responseList.getKm().add(ack);

      }//end-kmvs-loop

      return responseList;
   }

   /**
    * 
    * @param kmIdToProcess
    * @param kmvToProcess
    * @param em
    * @return
    */
   public KMVersion createKmvWithDepedencies(
           int kmIdToProcess, int newVersionNumber,
           KMVersionType kmvToProcess, EntityManager em,
           String authorId, String authorName) throws Exception
   {
      Query query = null;
      int total = 0;

      System.out.println("\n\n=================>Creating KVMNAME=" + kmvToProcess.getKMVName());

      try {
         //------------CREATE MODEL---------------
         KMVersion newKmv = new KMVersion(kmvToProcess);
         //------------SETTING DEFAULTS------------
         //kmID is stored as a KnowledgeModule object in KMV model, so have to do following to store it.
         KnowledgeModule k = new KnowledgeModule();
         k.setKmId(kmIdToProcess);
         newKmv.setKmId(k);
         newKmv.setKMVersionNum(newVersionNumber);
         newKmv.setValidationStatus("DRAFT");

         newKmv.setLogicNativeForm(kmvToProcess.getLogicNativeForm());
         newKmv.setLogicIntermediateForm(kmvToProcess.getLogicIntermediateForm());
         newKmv.setLogicBinaryForm(kmvToProcess.getLogicBinaryForm());

         newKmv.setAuthorComments(kmvToProcess.getAuthorComments());
         newKmv.setCreatedByAuthorID(authorId);
         newKmv.setCreatedByAuthorName(authorName);
         if (kmvToProcess.getCreatedTimestamp() == null) {
            newKmv.setCreatedTimestamp(dateHelper.getCurrDateTimeAsDate());
         } else {
            newKmv.setCreatedTimestamp(dateHelper.XMLGregorian2Date(kmvToProcess.getCreatedTimestamp()));
         }
         //Last Modified Date should be updated as per given OR
         //stamped as sysdate if changes are made to KM.
         if (kmvToProcess.getLastModifiedTimestamp() == null) {
            newKmv.setLastModifiedTimestamp(dateHelper.getCurrDateTimeAsDate());
         } else {
            newKmv.setLastModifiedTimestamp(dateHelper.XMLGregorian2Date(kmvToProcess.getLastModifiedTimestamp()));
         }

         //------------PERSIST----------------------
         em.persist(newKmv);
         em.flush();
         //------------GET FK----------------------
         //It should have been persisted via the JPA
         int newKMV_ID = newKmv.getKmvId();
         System.out.println("\n\n=================>new KMV_ID=" + newKMV_ID);
         System.out.println("\n\n=================>new KM_VERSIONNUM=" + newVersionNumber);

         //------------BUILD DEPEDENCIES for KMV-------------
         //=================================
         //SETTING KMV ACL
         // Will always create one new KMV_AccessControlList entry.
         //=================================
         //------------INSTANTIATE---------------
         KMVAccessControlList newKmvACL = new KMVAccessControlList(kmvToProcess.getKMVName() + "_ACL");
         //------------SETTING DEFAULTS------------ NA
         //------------PERSIST----------------------
         em.persist(newKmvACL);
         em.flush();
         //------------GET FK----------------------
         int newACLid = newKmvACL.getAclId();
         System.out.println("new ACL_ID=" + newACLid);

         newKmv.setAclId(newKmvACL);

         //------------BUILD DEPEDENCIES-------------
         //Lookup UR_ID that matches incoming UR_NAME.
         //  If does not exists, then error out.
         //Use the located UR_ID to insert a new ACL_PERMISSION record
         //  using the given UR_PERMISSION value.
         //-----------------------------------
         ACLPermissionType kmrecACL = null;
         total = kmvToProcess.getACLPermissionList().getACLPermission().size();
         List<ACLPermission> acls = new ArrayList<ACLPermission>(total);
         for (int depid = 0; depid < total; depid++) {
            // --------------------------------------------
            // FIND UR_ID for each matching UR_NAME (role).
            // If does not exist then insert an entry for now (LATER: stop import for this KM in error)
            // Else, get the UR_ID for later use.
            // --------------------------------------------
            kmrecACL = kmvToProcess.getACLPermissionList().getACLPermission().get(depid);
            System.out.println("UR Lookup for " + kmrecACL.getUserRole().getURName());

            int newURid = 0;
            query = em.createNamedQuery("UserRole.findByURName");
            query.setParameter("uRName", kmrecACL.getUserRole().getURName());
            UserRole ur = (UserRole) query.getSingleResult();

            if (ur != null) {
               System.out.println("FOUND UR_ID=" + ur.getUrId());
               newURid = ur.getUrId();
            } else {
               System.out.println("UR_ID not found for UR_NAME=" + kmrecACL.getUserRole().getURName());
               //add an entry into UserRole table.
               UserRole newUR = new UserRole(kmrecACL.getUserRole().getURName());
               em.persist(newUR);
               em.flush();
               newURid = newUR.getUrId();
               System.out.println("new UR_ID=" + newURid);
            }
            // --------------------------------------------
            // create ACL_PERMISSION record with UR_ID and ACL_ID
            // --------------------------------------------
            acls.add(aclBuilder.buildDatabaseObject(newURid, newACLid, kmrecACL.getURPermission(), em));
            newKmvACL.setACLPermissionCollection(acls);
         }

         //=================================
         //SETTING KM_FACTs
         //=================================
         if ((kmvToProcess.getFactSpecificationList() != null) &&
                 (kmvToProcess.getFactSpecificationList().getKMVFactDependency() != null) &&
                 (kmvToProcess.getFactSpecificationList().getKMVFactDependency().size() > 0)) {
            //------------INSTANTIATE---------------
            KMVFactDependencyType kmrecFD = null;
            total = kmvToProcess.getFactSpecificationList().getKMVFactDependency().size();
            List<KMVFactDependency> facts = new ArrayList<KMVFactDependency>(total);
            //------------BUILD model FACTs---------------
            for (int depid = 0; depid < total; depid++) {
               kmrecFD = kmvToProcess.getFactSpecificationList().getKMVFactDependency().get(depid);
               facts.add(factBuilder.buildDatabaseObject(newKMV_ID, kmrecFD, em));
               newKmv.setKMVFactDependencyCollection(facts);
            }
         }
         //=================================
         //SETTING KMV POPULATION DEPENDENCIES
         //=================================
         if ((kmvToProcess.getPopulationDependencyList() != null) &&
                 (kmvToProcess.getPopulationDependencyList().getKMVPopulationDependency() != null) &&
                 (kmvToProcess.getPopulationDependencyList().getKMVPopulationDependency().size() > 0)) {
            //------------INSTANTIATE---------------
            KMVPopulationDependencyType kmrecPD = null;
            total = kmvToProcess.getPopulationDependencyList().getKMVPopulationDependency().size();
            List<KMVPopulationDependency> populations = new ArrayList<KMVPopulationDependency>(total);
            //------------BUILD model FACTs---------------
            for (int depid = 0; depid < total; depid++) {
               kmrecPD = kmvToProcess.getPopulationDependencyList().getKMVPopulationDependency().get(depid);
               populations.add(popBuilder.buildDatabaseObject(newKMV_ID, kmrecPD, em));
               newKmv.setKMVPopulationDependencyCollection(populations);
            }
         }
         //=================================
         //SETTING KMV TASKS
         //=================================
         if ((kmvToProcess.getTaskList() != null) &&
                 (kmvToProcess.getTaskList().getKMVTaskDependency() != null) &&
                 (kmvToProcess.getTaskList().getKMVTaskDependency().size() > 0)) {
            //------------INSTANTIATE---------------
            KMVTaskDependencyType kmrecTD = null;
            total = kmvToProcess.getTaskList().getKMVTaskDependency().size();
            List<KMVTaskDependency> tasks = new ArrayList<KMVTaskDependency>(total);
            //------------BUILD model TASKs---------------
            for (int depid = 0; depid < total; depid++) {
               kmrecTD = kmvToProcess.getTaskList().getKMVTaskDependency().get(depid);
               tasks.add(taskBuilder.buildDatabaseObject(newKMV_ID, kmrecTD, em));
               newKmv.setKMVTaskDependencyCollection(tasks);
            }
         }
         //=================================
         //SETTING KMV INFERENCES
         // NOTE:  DB has one-to-one , xsd has one-to-many  ... FIX xsd
         //=================================
         if ((kmvToProcess.getInferenceList() != null) &&
                 (kmvToProcess.getInferenceList().getKMVInferenceEngineDependency() != null) &&
                 (kmvToProcess.getInferenceList().getKMVInferenceEngineDependency().size() > 0)) {
            //------------INSTANTIATE---------------
            KMVInferenceEngineDependencyType kmrecIED = null;
            total = kmvToProcess.getInferenceList().getKMVInferenceEngineDependency().size();
            List<KMVInferenceEngineDependency> infs = new ArrayList<KMVInferenceEngineDependency>(total);
            //------------BUILD model TASKs---------------
            for (int depid = 0; depid < total; depid++) {
               kmrecIED = kmvToProcess.getInferenceList().getKMVInferenceEngineDependency().get(depid);
               infs.add(inferenceBuilder.buildDatabaseObject(newKMV_ID, kmrecIED, em));
               newKmv.setKMVInferenceEngineDependency(infs.get(0));
            }
         }

         return newKmv;

      } catch (Exception e) {
         e.printStackTrace();
         System.out.println("createKmvWithDepedencies FAILED");

         throw e;
      }
   }

   /**
    *
    * @param kms
    * @param acl
    * @param em
    * @return an ACK listing status of UPDATE trxn.
    * @throws DatatypeConfigurationException
    */
   public ImportResponseListType updateAndBuildAck(
           List<KmFullResponse> kms, ACLSimpleType acl, EntityManager em)
           throws DatatypeConfigurationException {
      ImportResponseListType responseList = new ImportResponseListType();

      KmFullResponse km = null;      //record of incoming data to be updated to KM.
      KnowledgeModule foundKm = null; //record of existing base KM
      ImportAckType ack = null;

      Query query = null;

      //----------------------------------------------------------------------
      // loop through all incoming KMs and build the JPA objects for UPDATE
      //    km = record of each incoming KM and its dependencies.
      //    foundKm = will be newly populated each time the km_id of the
      //             in-process kmrec has been located in the DB.  If the
      //             in-process kmrec has not been located, then an 
      //             ACK of "ERROR" will be sent for that km_id.
      //----------------------------------------------------------------------
      for (int i = 0; i < kms.size(); i++) {

         km = kms.get(i);
         try {
            em.getTransaction().begin();

            //==================================================
            //[1] LOCATE the KM and dependencies data first.
            //[2] Check if there are KM level changes, then UPDATE that KM.
            //[3] Check if there are KMV level changes, then INSERT new KMV
            //          for that pre-existing KM
            //==================================================
            System.out.println("KM_NAME=" + km.getKmName() + "  KM_TYPE=" + km.getKmType());

            //----------------------------------------------------------
            //[1] LOCATE (by km_id) the KM and dependencies data first.
            //----------------------------------------------------------
            query = em.createNamedQuery("KnowledgeModule.findByKmId");
            query.setParameter("kmId", km.getKmId());
            foundKm = (KnowledgeModule) query.getSingleResult();

            debug_printKM(foundKm); //DBG

            //            ======================
            //            KNOWLEDGEMODULE UPDATE
            //            ======================

            //----------------------------------------------------------
            //[2] Check if there are KM level changes, then update KnowledgeModule table.
            // updateKm --> KM record to be updated base on incoming KM data.
            //----------------------------------------------------------
            //KnowledgeModule updateKm = new KnowledgeModule();

            if (km.getKmDescription() != null) {
               foundKm.setKMDescription(km.getKmDescription());
            }

            if (km.getKmKeywords() != null) {
               foundKm.setKMKeywords(km.getKmKeywords());
            }

            if (km.getKmName() != null) {
               foundKm.setKMName(km.getKmName());
            }

            if (km.getOriginInstitution() != null) {
               foundKm.setOriginInstitution(km.getOriginInstitution());
            }

            if (km.getKmOrganizationLevel() != null) {
               foundKm.setKMOrganizationLevel(km.getKmOrganizationLevel());
            }

            if (km.getLastModifiedByAuthorID() != null) {
               foundKm.setLastModifiedByAuthorID(km.getLastModifiedByAuthorID());
            }

            if (km.getLastModifiedByAuthorName() != null) {
               foundKm.setLastModifiedByAuthorName(km.getLastModifiedByAuthorName());
            }

            //if (km.getParentKMId() != null)
            //   updateKm.setParentKMID(km.getParentKMId());  //TODO parentID?

            if (km.getKmType() != null) {
               foundKm.setKMType(km.getKmType());
            }

/*****  SPECIALTY NOT COMING THRU !!! ******/
            //check to see if there are any incoming Specialties that needs to be process.
            if ((km.getSpecialtyList() != null) &&
                (km.getSpecialtyList().getKMSpecialty() != null) &&
                (km.getSpecialtyList().getKMSpecialty().size() > 0))
            {
System.out.println("\n\n======>HAVE SPECIALTY to process.");
               //-----------------------------------------------------------
               //loop thru each given Specialty rec and see if need to add or not
               //Add only if rec (scheme+code combo) does NOT already exist.
               //-----------------------------------------------------------

               List<KMSpecialty> specList = new ArrayList<KMSpecialty>();
               
               KMSpecialtyType s;
               boolean add;
               Iterator<KMSpecialtyType> specToAddIter = km.getSpecialtyList().getKMSpecialty().iterator();
               while (specToAddIter.hasNext()) {

                  s = specToAddIter.next();
                  //--------------------------------------------------------
                  //Automatically add any incoming specialty if KM currently
                  //do not have any.
                  //ELSE, ONLY add incoming specialty if that km_id+scheme+code
                  //combo does not yet exist for this KM
                  //--------------------------------------------------------
                  if (foundKm.getKMSpecialtyCollection() == null) {
                     add = true;
                  } else {
                     //check to see if scheme+code combo already exist in SPECIALTY via SQL
                     query = em.createNamedQuery("KMSpecialty.findExistSchemeCodeCombo");
                     query.setParameter("kmId", km.getKmId());
                     query.setParameter("terminologyScheme", s.getTerminologyScheme());
                     query.setParameter("terminologyCode", s.getTerminologyCode());
                     KMSpecialty foundSpecialty = (KMSpecialty) query.getSingleResult();

                     if (foundSpecialty != null) {                        
                        add = false;   //an exact match was found, no need to add again.
System.out.println("\n\n======> MATCH FOUND");
                     } else {
                        add = true;
                     }
                  }

                  if (add) {
                     //---------------------------------------------
                     KMSpecialtyPK spk = new KMSpecialtyPK();
                     spk.setKmId(km.getKmId());
                     spk.setTerminologyScheme(s.getTerminologyScheme());
                     spk.setTerminologyCode(s.getTerminologyCode());
                     //---------------------------------------------
                     KMSpecialty newSpec = new KMSpecialty();
                     newSpec.setKMSpecialtyPK(spk);
                     //---------------------------------------------
                     specList.add(newSpec);

System.out.println("\n\n======>ADDING new SPECIALTY "+ newSpec.getKMSpecialtyPK().getTerminologyCode());

                  }
               }//end-while

               //add newly created Specialty rec to main KM for later update.
               foundKm.setKMSpecialtyCollection(specList);
            }

            //Last Modified Date should be updated as per given OR
            //stamped as sysdate if changes are made to KM.
            if ((km.getLastModifiedTimestamp() != null) && !km.getLastModifiedTimestamp().isEmpty()) {
               foundKm.setLastModifiedTimestamp(dateHelper.getCurrDateTimeAsDate());
            } else {
               foundKm.setLastModifiedTimestamp(dateHelper.XMLString2Date(km.getLastModifiedTimestamp()));
            }

            //Update the KNOWLEDGEMODULE
            em.merge(foundKm);


            //            ==================================
            //            KM_VERSION and DEPENDENCIES INSERT
            //            ==================================

            //-----------------------------------------------------------
            //[3] Check if there are KMV level changes, then INSERT new KMV
            //          for that pre-existing KM
            //NOTE: There will only be one KMV to be inserted because an UPDATE 
            //      should only be generating a single new KMV for incoming data.
            //NOTE: The new KMV should be wholly created from the incoming data
            //      and NOT from any previous kmv version for this KM.
            //------------------------------------------------------------

            if ((km.getKmVersionList() != null) &&
                    (km.getKmVersionList().getKmVersion() != null) &&
                    (km.getKmVersionList().getKmVersion().size() > 0)) {
               System.out.println("\n\n===========>LASTVERS=" + foundKm.getLatestVersionNum());
               int newVersionNumber = foundKm.getLatestVersionNum() + 1;

               //----------------------------------------------------
               //create new KVM and any depedencies based on the
               //incoming kmv data.
               //----------------------------------------------------
               KMVersionType inKmv = km.getKmVersionList().getKmVersion().get(0);
               KMVersion newKmv = 
                       this.createKmvWithDepedencies(  km.getKmId()
                                                      ,newVersionNumber
                                                      ,inKmv
                                                      ,em
                                                      ,km.getLastModifiedByAuthorID()
                                                      ,km.getLastModifiedByAuthorName());

               //----------------------------------------------------
               //Add newly created KMV and nested elems to a KMV list elem.
               //----------------------------------------------------
               List<KMVersion> kmvs = new ArrayList<KMVersion>(1);
               kmvs.add(newKmv);

               //----------------------------------------------------
               //Set the latest KMV version number AFTER successfully
               //inserting any new KMV for this KM.
               //----------------------------------------------------
               foundKm.setLatestVersionNum(newVersionNumber);
               foundKm.setKMVersionCollection(kmvs);

               //===============================================
               //INSERT new KMV for this KM
               // ----------------------------------------------
               System.out.println("\n\n===========>UPDATE knowledgemodule & INSERT kmv & COMMIT");
               em.persist(foundKm);
            }


            //=======================================================
            //COMMIT (both KNOWLEDGEMODULE update and any KMV insert
            // ------------------------------------------------------
            em.getTransaction().commit();
            //em.flush();

            //===============================================
            //Build SUCCESS ACK for KM
            // ----------------------------------------------
            System.out.println("Build Success ACK for KM=" + km.getKmName());
            ack = new ImportAckType();
            ack.setKmId(km.getKmId());
            ack.setKmName(km.getKmName());
            ack.setImportStatus("SUCCESS");
            ack.setDescription("Successful Update");

         } catch (Exception e) {
            e.printStackTrace();

            //===============================================
            //Build FAILed ACK for KM
            // ----------------------------------------------            
            System.out.println("Build Failed ACK for KM=" + km.getKmName());
            ack = new ImportAckType();
            ack.setKmId(km.getKmId());
            ack.setKmName(km.getKmName());
            ack.setImportStatus("FAIL");
            ack.setDescription("Failed Update: " + e.toString());
            em.getTransaction().rollback();
         }

         responseList.getKm().add(ack);

      }//end-kmvs-loop

      return responseList;
   }

   /**
    *
    * @param km
    */
   protected void debug_printKM(KnowledgeModule km) {
      //KNOWLEDGEMODULE
      System.out.println("===FOUND===================");
      System.out.println("\nkmid=" + km.toString() + "\nkmname=" + km.getKMName() + "\nkmtype=" + km.getKMType() + "\ncreatedby=" + km.getCreatedByAuthorID());

      /*CHECK MAKE SURE DON'T BREAK SEARCH
      System.out.println("\nSPECIALTY SIZE=" + km.getKMSpecialtyCollection().size());
      Iterator<KMSpecialty> specialtyIter = km.getKMSpecialtyCollection().iterator();
      while (specialtyIter.hasNext()) {
      System.out.println("\nSPECIALTY: " + specialtyIter.next().toString());
      }
      
      Iterator<KMVersion> kmvIter = km.getKMVersionCollection().iterator();
      while (kmvIter.hasNext()) {
      KMVersion kmv = kmvIter.next();
      System.out.println("\nKMV: " + kmv.toString());

      kmv.getKMVFactDependencyCollection().iterator();
      System.out.println("FACT SIZE=" + kmv.getKMVFactDependencyCollection().size());
      Iterator<KMVFactDependency> factIter = kmv.getKMVFactDependencyCollection().iterator();
      if (kmv.getKMVFactDependencyCollection().size() > 0) {
      System.out.println("\nFACT: " + factIter.next().toString());
      }
      }
       */
   }
}
