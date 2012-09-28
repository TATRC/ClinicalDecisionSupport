/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gov.hhs.fha.nhinc.kmr.dao;

import gov.hhs.fha.nhinc.kmr.kmtypes.ExportResponseListType;
import gov.hhs.fha.nhinc.kmr.kmtypes.FindKmIdsResponseListType;
import gov.hhs.fha.nhinc.kmr.kmtypes.ImportResponseListType;
import gov.hhs.fha.nhinc.kmr.kmtypes.KmFullResponse;
import gov.hhs.fha.nhinc.kmr.kmtypes.KmIdSearchRequestType;
import gov.hhs.fha.nhinc.kmr.kmtypes.KmImportRequestType;
import gov.hhs.fha.nhinc.kmr.kmtypes.KmLatestLogicResponseListType;
import gov.hhs.fha.nhinc.kmr.kmtypes.KmMetaResponseListType;
import gov.hhs.fha.nhinc.kmr.kmtypes.KmRequestType;
import gov.hhs.fha.nhinc.kmr.kmtypes.ReferenceDataRefRequestType;
import gov.hhs.fha.nhinc.kmr.kmtypes.ReferenceDataResponseType;
import gov.hhs.fha.nhinc.kmr.kmtypes.ResponseListType;
import gov.hhs.fha.nhinc.kmr.model.KMVersion;
import gov.hhs.fha.nhinc.kmr.model.KnowledgeModule;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.apache.log4j.Logger;

/**
 *
 * @author tmn
 */
public class KnowledgeModuleDAO implements KnowledgeModuleManager {

   public final static String KNOWLEDGEMODULE_MANAGER_PU = "KnowledgeModuleEJBPU";
   private static PersistentServiceFactory factory = null;
   private final static Logger log = Logger.getLogger(KnowledgeModuleDAO.class.getName());
   private static KnowledgeModuleManager dao = null;

   private static ResponseListBuilder respBuilder = new ResponseListBuilder();
   private static RefResponseListBuilder refRespBuilder = new RefResponseListBuilder();

   protected KnowledgeModuleDAO(String pUnit) {
      factory = PersistentServiceFactory.getInstance(pUnit);
   }

   public static KnowledgeModuleManager getInstance() {
      synchronized (KnowledgeModuleDAO.class) {
         if (dao == null) {
            dao = new KnowledgeModuleDAO(KNOWLEDGEMODULE_MANAGER_PU);
         }
      }
      return dao;
   }

   /**
    * buildFoundKmvIdsList - From a List<Vector> extract and build
    *                        a list of KMVersion objects that
    *                        will only have KMV_ID and KM_ID populated.
    *
    * @param results
    * @return List<KMVersion>
    */
   private List<KMVersion> buildFoundKmvIdsList (List<Vector> results) {

      List<KMVersion> foundKmvIds = new ArrayList<KMVersion>();

      for (Vector res : results) {
         System.out.println(
                 "---> ONE=" + new Integer((Integer) res.get(0)) +
                 " TWO=" + new Integer((Integer) res.get(1)));

         // add id-couple to a working KMVersion object.
         KMVersion aKMV = new KMVersion();
         aKMV.setKmvId(new Integer((Integer) res.get(0)));

         KnowledgeModule tempkm = new KnowledgeModule();
         tempkm.setKmId(new Integer((Integer) res.get(1)));

         aKMV.setKmId(tempkm);

         foundKmvIds.add(aKMV);
      }
      return foundKmvIds;
   }
/**
    * buildFoundKmIdsList - From a List<Vector> containing only KM_IDs,
    *                       extract and build a list of KnowledgeModule objects that
    *                       will only have KM_ID populated.
    *
    * @param results
    * @return List<KnowledgeModule
    */
   private List<KnowledgeModule> buildFoundKmIdsList (List<Vector> results) {

      List<KnowledgeModule> foundKmIds = new ArrayList<KnowledgeModule>();

      for (Vector res : results) {
         System.out.println(
                 "---> ONE=" + new Integer((Integer) res.get(0)) );

         // add id to a working KMVersion object.
         KnowledgeModule aKM = new KnowledgeModule();
         aKM.setKmId(new Integer((Integer) res.get(0)));

         foundKmIds.add(aKM);
      }
      return foundKmIds;
   }

   /**
    * findKmIds (EXPORT Pass 1)
    *    Given a set of params, filter on it and locate all matching KM_IDs.
    *
    * @param params
    * @return FindKmIdsResponseListType
    */
   @Override
   public FindKmIdsResponseListType findKmIds(KmRequestType params) {
      FindKmIdsResponseListType kms = new FindKmIdsResponseListType();
      EntityManager em = null;
      PersistentServiceFactory psf = null;
      Query query = null;

      System.out.println("[findKmIds] SEARCHING by Params...");
      try {

/*TMN - Disregard ACL for now until design clarification about ACL at KM level (not KM_Version level)
         // ----------------------------------------------------------
         // CHECKING for reuired ACL Role name.
         // ----------------------------------------------------------
         System.out.println("******ACL=" + params.getRequest().getKm().getACL().getURName());
         String aclRoleName = params.getRequest().getKm().getACL().getURName();
         if ((aclRoleName == null) || (aclRoleName.isEmpty())) {
            throw new NoResultException();
         }
*/
         // ----------------------------------------------------------
         // Instantiate Persistent and Entity Manager.
         // ----------------------------------------------------------
         psf = PersistentServiceFactory.getInstance(KNOWLEDGEMODULE_MANAGER_PU);
         em = psf.getEntityManager();

         // ----------------------------------------------------------
         // Create and execute request as Native query.
         // Flagging true for DISTINCT on KM_ID (instead of KMV_ID).
         // ----------------------------------------------------------
         query = em.createNativeQuery(respBuilder.buildFilterStr(params.getRequest().getKm(), true));
         List<Vector> results = (List<Vector>) query.getResultList();

         if (results != null && !results.isEmpty()) {
            // ----------------------------------------------------------
            // Transfer all queried results to a working array: foundKmvIds
            // ----------------------------------------------------------
            List<KnowledgeModule> foundKmIds = null;
            foundKmIds = this.buildFoundKmIdsList(results);
            System.out.println("--> Total KM_IDs found=" + foundKmIds.size());

            // ----------------------------------------------------------
            // build the REsponseList
            // ----------------------------------------------------------

            kms = respBuilder.buildResponseListOfKmIdOnly(
                    foundKmIds, em,
                    params.getRequest().getRequestReference().getRequestId());

         }//end-if-results

      } catch (Exception e) {
         System.out.println("FAIL RUNTIME="+e.getMessage());
         e.printStackTrace();
      } finally {
         em.close();
         return kms;
      }

   }

   /**
    * FOR TESTING ONLY
    * @param params
    * @return
    *
   public KnowledgeModule findByKmId(int params) {

      KnowledgeModule results = null;

      EntityManager em = null;
      PersistentServiceFactory psf = null;
      Query query = null;

      System.out.println("[findByKmId] SEARCHING by KM_ID ...");
      System.out.println("[findByKmId] TOTAL KM_IDs looking for="+params);

      // Given a list of KM_IDs,
      // we need to Native SQL to get the related ACl-accessible KMV_IDs.

      try {
         // ----------------------------------------------------------
         // Instantiate Persistent and Entity Manager.
         // ----------------------------------------------------------
         psf = PersistentServiceFactory.getInstance(KNOWLEDGEMODULE_MANAGER_PU);
         em = psf.getEntityManager();

         // ----------------------------------------------------------
         // Create and execute request as Native query to get all KMV_IDs
         // from the given KM_ID and ACL.UR_NAME
         // results =
         // ----------------------------------------------------------
         query = em.createNamedQuery("KnowledgeModule.findByKmId");
         query.setParameter("kmId", params);
         results = (KnowledgeModule) query.getSingleResult();


      } catch (Exception e) {
         System.out.println("FAIL RUNTIME="+e.getMessage());
         e.printStackTrace();
      } finally {
         em.close();
         return results;
      }
   }*/

   /**
    * findByKmId (EXPORT Pass 2)
    *    Given a list of KM_ID(s), a search will be done to locate and return the matching
    *    record from table KnowledgeModule.  The record will be packaged in the
    *    KmFullResponse type before being returned.
    *
    * @param kmId
    * @return KmFullResponse
    */
   @Override
   public ExportResponseListType findByKmId(KmIdSearchRequestType params) {

      ExportResponseListType responseList = new ExportResponseListType();
      KmFullResponse km = null; //KmFullResponse object to store found data.

      EntityManager em = null;
      PersistentServiceFactory psf = null;
      Query query = null;
 
      System.out.println("[findByKmId] SEARCHING by KM_ID ...");
      System.out.println("[findByKmId] TOTAL KM_IDs looking for="+params.getRequest().getKms().getKmId().size());

      // Given a list of KM_IDs,
      // we need to Native SQL to get the related ACl-accessible KMV_IDs.

      try {
         // ----------------------------------------------------------
         // CHECKING for required ACL Role name.
         // ----------------------------------------------------------
         System.out.println("******ACL=" + params.getRequest().getACL().getURName());
         String aclRoleName = params.getRequest().getACL().getURName();
         if ((aclRoleName == null) || (aclRoleName.isEmpty())) {
            throw new NoResultException();
         }

         // ----------------------------------------------------------
         // Instantiate Persistent and Entity Manager.
         // ----------------------------------------------------------
         psf = PersistentServiceFactory.getInstance(KNOWLEDGEMODULE_MANAGER_PU);
         em = psf.getEntityManager();

         // ----------------------------------------------------------
         // Create and execute request as Native query to get all KMV_IDs
         // from the given KM_ID and ACL.UR_NAME
         // results =
         // ----------------------------------------------------------
         query = em.createNativeQuery(respBuilder.buildFilterStrWithKmIdList(params.getRequest()));
         List<Vector> results = (List<Vector>) query.getResultList();

         if (results != null && !results.isEmpty()) {

            // ----------------------------------------------------------
            // Transfer all queried results to a working array: foundKmvIds
            // ----------------------------------------------------------
            List<KMVersion> foundKmvIds = null;
            foundKmvIds = this.buildFoundKmvIdsList(results);
            System.out.println("--> Total KMV_IDs found=" + foundKmvIds.size());

            // ----------------------------------------------------------
            // build the REsponseList
            // ----------------------------------------------------------
            responseList = respBuilder.buildResponseListById(foundKmvIds, em);
         }//end-if-results

      } catch (Exception e) {
         System.out.println("FAIL RUNTIME="+e.getMessage());
         e.printStackTrace();
      } finally {
         em.close();
         return responseList;
      }
   }

   /**
    * findByParams()
    *    based
    * @param params
    * @return
    */
   @Override
   public ResponseListType findByParams(KmRequestType params) {

      ResponseListType responseList = null; //new ResponseListType();
      EntityManager em = null;
      PersistentServiceFactory psf = null;
      Query query = null;

      try {
         // ----------------------------------------------------------
         // CHECKING for reuired ACL Role name.
         // ----------------------------------------------------------
         System.out.println("******ACL=" + params.getRequest().getKm().getACL().getURName());
         String aclRoleName = params.getRequest().getKm().getACL().getURName();
         if ((aclRoleName == null) || (aclRoleName.isEmpty())) {
            throw new NoResultException();
         }

         // ----------------------------------------------------------
         // Instantiate Persistent and Entity Manager.
         // ----------------------------------------------------------
         psf = PersistentServiceFactory.getInstance(KNOWLEDGEMODULE_MANAGER_PU);
         em = psf.getEntityManager();

         // ----------------------------------------------------------
         // Create and execute request as Native query.
         // ----------------------------------------------------------
         query = em.createNativeQuery(respBuilder.buildFilterStr(params.getRequest().getKm(), false));
         List<Vector> results = (List<Vector>) query.getResultList();

         if (results != null && !results.isEmpty()) {
            // ----------------------------------------------------------
            // Transfer all queried results to a working array: foundKmvIds
            // ----------------------------------------------------------
            List<KMVersion> foundKmvIds = null;
            foundKmvIds = this.buildFoundKmvIdsList(results);
            System.out.println("--> Total KMV_IDs found=" + foundKmvIds.size());

            /*
            // DBG test loop to build an IN clause
            String kmvIdStrList = "";
            for (int i = 0; i < foundKmvIds.size(); i++) {
               if (kmvIdStrList.equals("")) {
                  kmvIdStrList = foundKmvIds.get(i).getKmvId().toString();
               } else {
                  kmvIdStrList = kmvIdStrList + "," + foundKmvIds.get(i).getKmvId().toString();
               }               
            }
            System.out.println("kmvIdStrList=" + kmvIdStrList);
            */

            // ----------------------------------------------------------
            // build the REsponseList
            // ----------------------------------------------------------
            responseList = respBuilder.buildResponseList(foundKmvIds, em);
         }//end-if-results
         
      } catch (Exception e) {
         System.out.println("FAIL RUNTIME="+e.getMessage());
         e.printStackTrace();
      } finally {
         em.close();
         return responseList;
      }
   }

   /**
    * findByParamsForRuntime()
    *    
    * @param params
    * @return ResponseListType - containing only th minimal params for each
    * KM/KMV
    * 
    */
   @Override
   public ResponseListType findByParamsForRuntime(KmRequestType params) {

      ResponseListType responseList = null; //new ResponseListType();
      EntityManager em = null;
      PersistentServiceFactory psf = null;
      Query query = null;

      System.out.println("[RUNTIME] SEARCHING by Params...");
      
      try {
         // ----------------------------------------------------------
         // CHECKING for required ACL Role name.
         // ----------------------------------------------------------
         System.out.println("ACL=" + params.getRequest().getKm().getACL().getURName());
         String aclRoleName = params.getRequest().getKm().getACL().getURName();
         if ((aclRoleName == null) || (aclRoleName.isEmpty())) {
            throw new NoResultException();
         }
         // ----------------------------------------------------------
         // Instantiate Persistent and Entity Manager.
         // ----------------------------------------------------------
         psf = PersistentServiceFactory.getInstance(KNOWLEDGEMODULE_MANAGER_PU);
         em = psf.getEntityManager();

         // ----------------------------------------------------------
         // Create and execute request as Native query."
         // ----------------------------------------------------------
         query = em.createNativeQuery(respBuilder.buildFilterStr(params.getRequest().getKm(), false));
         List<Vector> results = (List<Vector>) query.getResultList();

         if (results != null && !results.isEmpty()) {
            // ----------------------------------------------------------
            // Transfer all queried results to a working array.
            // ----------------------------------------------------------
            List<KMVersion> foundKmvIds = null;
            foundKmvIds = this.buildFoundKmvIdsList(results);
            System.out.println("--> Total KMV_IDs found=" + foundKmvIds.size());

            // ----------------------------------------------------------
            // build the REsponseList
            // ----------------------------------------------------------
            responseList = respBuilder.buildResponseListForRuntime(foundKmvIds, em);

         }//end-if-results

      } catch (Exception e) {
         System.out.println("FAIL RUNTIME="+e.getMessage());
         e.printStackTrace();
      } finally {
         em.close();
         return responseList;
      }
   }

   @Override
   public ImportResponseListType insertKms(KmImportRequestType params) {
      
      ImportResponseListType responseList = null; 
      EntityManager em = null;
      PersistentServiceFactory psf = null;
      Query query = null;

      System.out.println("[INSERTING] ..");

      try {
         // ----------------------------------------------------------
         // CHECKING for required ACL Role name.
         // NA - anyone should be able to insert a Rule.
         //
         // (1) All Rules will be stamped as Personal/Draft.
         // ----------------------------------------------------------

         // ----------------------------------------------------------
         // Instantiate Persistent and Entity Manager.
         // ----------------------------------------------------------
         psf = PersistentServiceFactory.getInstance(KNOWLEDGEMODULE_MANAGER_PU);
         em = psf.getEntityManager();

         // ----------------------------------------------------------
         // IMPL
         // Call Helper class to do the insert and build the ack.
         // ----------------------------------------------------------
         if (params.getRequest().getKms().getKm() != null
                 && !params.getRequest().getKms().getKm().isEmpty()) {
                 responseList = respBuilder.insertAndBuildAck(
                                            params.getRequest().getKms().getKm()
                                           ,params.getRequest().getACL()
                                           ,em);
         }
         
      } catch (Exception e) {
         System.out.println("FAIL RUNTIME="+e.getMessage());
         e.printStackTrace();
      } finally {
         em.close();
         return responseList;
      }
      
   }

   
   /**
    * getKmMeta
    *    Given a list of KM_ID(s), a search will be done to locate and return the matching
    *    record from table KnowledgeModule.  The record will be packaged in the
    *    KmMetaResponse type, and added to a KmMetaResponseListType before being returned.
    *
    * @param kmId
    * @return KmFullResponse
    */
   @Override
   public KmMetaResponseListType getKmMeta(KmIdSearchRequestType params) {

      KmMetaResponseListType responseList = null;

      EntityManager em = null;
      PersistentServiceFactory psf = null;

      System.out.println("[getKmMeta] SEARCHING by KM_ID ...");
      System.out.println("[getKmMeta] TOTAL KM_IDs looking for="+params.getRequest().getKms().getKmId().size());

      // Given a list of KM_IDs,
      // we need to Native SQL to get the related ACl-accessible KMV_IDs.

      try {
/*TMN - Disregard ACL for now until design clarification about ACL at KM level (not KM_Version level)
         // ----------------------------------------------------------
         // CHECKING for required ACL Role name.
         // ----------------------------------------------------------
         System.out.println("******ACL=" + params.getRequest().getACL().getURName());
         String aclRoleName = params.getRequest().getACL().getURName();
         if ((aclRoleName == null) || (aclRoleName.isEmpty())) {
            throw new NoResultException();
         }
*/
         // ----------------------------------------------------------
         // Instantiate Persistent and Entity Manager.
         // ----------------------------------------------------------
         psf = PersistentServiceFactory.getInstance(KNOWLEDGEMODULE_MANAGER_PU);
         em = psf.getEntityManager();

         if (   params.getRequest().getKms().getKmId() != null
             && !params.getRequest().getKms().getKmId().isEmpty())
         {
            responseList = respBuilder.buildKmMetaResponseList(params.getRequest().getKms().getKmId(), em);            
         }//end-if-results

      } catch (Exception e) {
         System.out.println("FAIL RUNTIME="+e.getMessage());
         e.printStackTrace();
      } finally {
         em.close();
         return responseList;
      }
   }


   /**
    * getKmLatestLogic
    *    Given a list of KM_ID(s), a search will be done to locate and return
    *    the latest Native Logic Form for that KM_ID.  The record will be packaged in the
    *    KmLatestLogicResponseType type, and added to
    *    a KmLatestLogicResponseListType before being returned.
    *
    * @param kmId
    * @return KmFullResponse
    */
   @Override
   public KmLatestLogicResponseListType getKmLatestLogic(KmIdSearchRequestType params) {

      KmLatestLogicResponseListType responseList = null;

      EntityManager em = null;
      PersistentServiceFactory psf = null;

      System.out.println("[getKmLatestLogic] SEARCHING by KM_ID ...");
      System.out.println("[getKmLatestLogic] TOTAL KM_IDs looking for="+params.getRequest().getKms().getKmId().size());

      // Given a list of KM_IDs,
      // we need to Native SQL to get the related ACl-accessible KMV_IDs.

      try {
/*TMN - Disregard ACL for now until design clarification about ACL at KM level (not KM_Version level)
         // ----------------------------------------------------------
         // CHECKING for required ACL Role name.
         // ----------------------------------------------------------
         System.out.println("******ACL=" + params.getRequest().getACL().getURName());
         String aclRoleName = params.getRequest().getACL().getURName();
         if ((aclRoleName == null) || (aclRoleName.isEmpty())) {
            throw new NoResultException();
         }
*/
         // ----------------------------------------------------------
         // Instantiate Persistent and Entity Manager.
         // ----------------------------------------------------------
         psf = PersistentServiceFactory.getInstance(KNOWLEDGEMODULE_MANAGER_PU);
         em = psf.getEntityManager();

         if (   params.getRequest().getKms().getKmId() != null
             && !params.getRequest().getKms().getKmId().isEmpty())
         {
            responseList = respBuilder.buildKmLastLogicResponseList(params.getRequest().getKms().getKmId(), em);
         }//end-if-results

      } catch (Exception e) {
         System.out.println("FAIL RUNTIME="+e.getMessage());
         e.printStackTrace();
      } finally {
         em.close();
         return responseList;
      }
   }

   @Override
   public ReferenceDataResponseType getRefData(ReferenceDataRefRequestType params) {

      ReferenceDataResponseType response = null;

      EntityManager em = null;
      PersistentServiceFactory psf = null;

      //DBG
      System.out.println("\n========> [getRefData] ref    = "+ params.getLookup().getReference());
      if (params.getLookup().getLookupType() != null)
         System.out.println("\n========> [getRefData] Getting Type");
      if (params.getLookup().getLookupSystem() != null)
         System.out.println("\n========> [getRefData] Getting System");
      if (params.getLookup().getLookupCode() != null)
         System.out.println("\n========> getRefData] Getting Code");
      
      try {
         // ----------------------------------------------------------
         // Instantiate Persistent and Entity Manager.
         // ----------------------------------------------------------
         psf = PersistentServiceFactory.getInstance(KNOWLEDGEMODULE_MANAGER_PU);
         em = psf.getEntityManager();

         response = refRespBuilder.buildRefDataResponseList(params.getLookup(),em);

      } catch (Exception e) {
         System.out.println("FAIL RUNTIME="+e.getMessage());
         e.printStackTrace();
      } finally {
         em.close();
         return response;
      }
   }

   @Override
   public ImportResponseListType updateKms(KmImportRequestType params) {

      ImportResponseListType responseList = null; //new ResponseListType();
      EntityManager em = null;
      PersistentServiceFactory psf = null;
      Query query = null;

      System.out.println("[UPDATING] ..");

      try {
         // ----------------------------------------------------------
         // CHECKING for required ACL Role name.
         //    * the owner of this rule should be able to update this rule.
         //    * ....
         //
         // (1) All Rules will be stamped as Personal/Draft.
         // ----------------------------------------------------------

         // ----------------------------------------------------------
         // Instantiate Persistent and Entity Manager.
         // ----------------------------------------------------------
         psf = PersistentServiceFactory.getInstance(KNOWLEDGEMODULE_MANAGER_PU);
         em = psf.getEntityManager();

         // ----------------------------------------------------------
         // IMPL
         // Call Helper class to do the insert and build the ack.
         // ----------------------------------------------------------
         if (params.getRequest().getKms().getKm() != null
                 && !params.getRequest().getKms().getKm().isEmpty()) {
                 responseList = respBuilder.updateAndBuildAck(
                                            params.getRequest().getKms().getKm()
                                           ,params.getRequest().getACL()
                                           ,em);
         }

      } catch (Exception e) {
         System.out.println("FAIL RUNTIME="+e.getMessage());
         e.printStackTrace();
      } finally {
         em.close();
         return responseList;
      }

   }

}
