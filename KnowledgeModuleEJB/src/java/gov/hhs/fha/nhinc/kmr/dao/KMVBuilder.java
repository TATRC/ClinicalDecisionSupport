package gov.hhs.fha.nhinc.kmr.dao;

import gov.hhs.fha.nhinc.kmr.kmtypes.KmByParamsType;
import gov.hhs.fha.nhinc.kmr.kmtypes.KmFullResponse;
import gov.hhs.fha.nhinc.kmr.model.KnowledgeModule;
import java.util.List;
import javax.persistence.Query;
import javax.persistence.EntityManager;

/**
 * Support build class for filters and table retrieval for both KnowledgeModule
 * and KM_Version tables.
 * 
 * @author tmn
 */
public class KMVBuilder extends queryHelper{
   //--------------------------------------------------------------------------
   //  Constructors
   //--------------------------------------------------------------------------
   public KMVBuilder() {
   }

   /**
    *
    * @param kmidList - a list of KM_IDs ,  as Integer
    * @param prefilters
    * @return an IN clause for all incoming KM_ID
    */
   public String getFiltersKmIdList(List<Integer> kmidList, String preFilters) {
      String filters = preFilters;

      if (!kmidList.isEmpty()) {
         if (!filters.equals("")) {
            filters = filters + " AND ";
         }
         filters = filters + "KnowledgeModule.KM_ID" + this.createIntegerINClause(kmidList);
      }
      return filters;

   }

   /**
    * getFilters() - Used to build a NATIVE sql.
    *
    */
   public String getFilters(KmByParamsType req, String preFilters) {
      String filters = preFilters;

      // KNOWLEDGEMODULE params
      if (req.getKmId() != null) {
         if (!filters.equals("")) filters = filters + " AND ";
         filters = filters + "KnowledgeModule.KM_ID = " + req.getKmId();
      }
      if (req.getKmName() != null) {
         if (!filters.equals("")) filters = filters + " AND ";
         filters = filters + "KnowledgeModule.KM_Name LIKE '%" + req.getKmName() + "%'";
      }
      if (req.getCreatedByAuthorID() != null) {
         if (!filters.equals("")) filters = filters + " AND ";
         filters = filters + "KnowledgeModule.CreatedBy_AuthorID = '" + req.getCreatedByAuthorID() + "'";
      }
      if (req.getCreatedByAuthorName() != null) {
         if (!filters.equals("")) filters = filters + " AND ";
         filters = filters + "KnowledgeModule.CreatedBy_AuthorName LIKE '%" + req.getCreatedByAuthorName() + "%'";
      }
      // NOTE:  Description implements a LIKE 
      if (req.getKmDescription() != null) {
         if (!filters.equals("")) filters = filters + " AND ";
         filters = filters + "KnowledgeModule.KM_Description LIKE '%" + req.getKmDescription() + "%'";
      }
      if (req.getKmOrganizationLevel() != null) {
         if (!filters.equals("")) filters = filters + " AND ";
         filters = filters + "KnowledgeModule.KM_OrganizationLevel = '" + req.getKmOrganizationLevel() + "'";
      }
      if (req.getKmType() != null) {
         if (!filters.equals("")) filters = filters + " AND ";
         filters = filters + "KnowledgeModule.KM_Type = '" + req.getKmType() + "'";
      }
      if (req.getLastModifiedByAuthorID() != null) {
         if (!filters.equals("")) filters = filters + " AND ";
         filters = filters + "KnowledgeModule.LastModifiedBy_AuthorID = '" + req.getLastModifiedByAuthorID() + "'";
      }
      if (req.getLastModifiedByAuthorName() != null) {
         if (!filters.equals("")) filters = filters + " AND ";
         filters = filters + "KnowledgeModule.LastModifiedBy_AuthorName = '" + req.getLastModifiedByAuthorName() + "'";
      }
      if (req.getLatestVersionNum() != null) {
         if (!filters.equals("")) filters = filters + " AND ";
         filters = filters + "KnowledgeModule.LatestVersionNum = " + req.getLatestVersionNum();
      }
      if (req.getOriginInstitution() != null) {
         if (!filters.equals("")) filters = filters + " AND ";
         filters = filters + "KnowledgeModule.OriginInstitution = '%" + req.getOriginInstitution() + "%'";
      }
      if (req.getProductionVersionNum() != null) {
         if (!filters.equals("")) filters = filters + " AND ";
         filters = filters + "KnowledgeModule.ProductionVersionNum = " + req.getProductionVersionNum();
      }

      // KM_VERSION params
      if (req.getCreatedByAuthorID() != null) {
         if (!filters.equals("")) filters = filters + " AND ";
         filters = filters + "KM_Version.CreatedBy_AuthorID = '" + req.getCreatedByAuthorID() + "'";
      }
      if (req.getValidationStatus() != null) {
         if (!filters.equals("")) filters = filters + " AND ";
         filters = filters + "KM_Version.ValidationStatus = '" + req.getValidationStatus() + "'";
      }
      return filters;
   }

    /**
    * buildElems() - Query KNOWLEDGEMODULE for metadata. There should be only one record
    * because KM_ID is unique.  KnowledgeModule.toKmTypes will take care of
    * initializing the return object.
    * @param kmid
    * @return KmFullResponse
    */
   public KmFullResponse buildElems(EntityManager em, Integer kmid) {
      KmFullResponse km = null;

      // Query for KnowledgeModule table
      Query query = em.createNamedQuery("KnowledgeModule.findByKmId");
      query.setParameter("kmId", kmid);
      KnowledgeModule foundKm = (KnowledgeModule) query.getSingleResult();

      System.out.println("found KnowledgeModule: " + foundKm.getKMName());

      km = foundKm.toKmTypes();
      

//TODO      foundKm.getKMSpecialtyCollection().size();

      return km;
   }

    /**
    * buildElems() - Query KNOWLEDGEMODULE for metadata. There should be only one record
    * because KM_ID is unique.  KnowledgeModule.toKmTypes will take care of
    * initializing the return object.
    * @param kmid
    * @return KmFullResponse
    */
   public KmFullResponse buildSimpleElems(EntityManager em, Integer kmid) {
      KmFullResponse km = null;

      // Query for KnowledgeModule table
      Query query = em.createNamedQuery("KnowledgeModule.findByKmId");
      query.setParameter("kmId", kmid);
      KnowledgeModule foundKm = (KnowledgeModule) query.getSingleResult();

      System.out.println("found KnowledgeModule: " + foundKm.getKMName());

      // getting a limited set of KnowledgeModule record metadata.
      km = foundKm.toKmTypes_simple();

      return km;
   }


}
