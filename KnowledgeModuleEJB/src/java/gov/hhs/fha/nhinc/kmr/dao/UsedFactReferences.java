package gov.hhs.fha.nhinc.kmr.dao;

import gov.hhs.fha.nhinc.kmr.kmtypes.ReferenceDataLookupType;
import gov.hhs.fha.nhinc.kmr.kmtypes.ReferenceDataRefDataType;
import gov.hhs.fha.nhinc.kmr.model.RefCodeSimple;
import java.util.ArrayList;
import java.util.List;

import java.util.Vector;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import javax.xml.datatype.DatatypeConfigurationException;
import org.apache.log4j.Logger;


public class UsedFactReferences {

   private final static Logger log = Logger.getLogger(UsedFactReferences.class.getName());

   protected EntityManager em;
   protected ReferenceDataLookupType refDetails;

   //--------------------------------------------------------------------------
   //  Constructor
   //--------------------------------------------------------------------------
   //public UsedFactReferences() {
   //}

   public UsedFactReferences(EntityManager em, ReferenceDataLookupType refDetails) {
     this.em = em;
     this.refDetails = refDetails;
   }

   public ArrayList<ReferenceDataRefDataType> getRefs() throws DatatypeConfigurationException
   {
      ArrayList<ReferenceDataRefDataType> references = null;
      String ref = refDetails.getReference();

      if (ref.equalsIgnoreCase("usedfacttype")) {
         references = this.getUsedRefFactTypes();
      } else if (ref.equalsIgnoreCase("usedfactscheme")) {
         references = this.getUsedRefFactSchemes();
      } else if (ref.equalsIgnoreCase("usedfactcode")) {
         references = this.getUsedRefFactCodes();
      }
      return references;
   }

/*********************************************************************
 **                     USED FACT REFERENCES
 * @return The Type name, not it (LAB, ALLERGY, ...)
 *********************************************************************/
   private ArrayList<ReferenceDataRefDataType> getUsedRefFactTypes()
   {
      System.out.println("\n========> Getting Used facttype references");

      ArrayList<ReferenceDataRefDataType> refColl = new ArrayList<ReferenceDataRefDataType>();
      Query query = null;
      int startRec = refDetails.getStartingRecNumber();
      int maxDisplay = refDetails.getMaxRecordsToShow();

      if ((refDetails.getLookupType().getCodeType() == null) ||
              (refDetails.getLookupType().getCodeType().isEmpty())) {
         query = em.createNamedQuery("KMVFactDependency.findAllDistinctType");
      } else {
         query = em.createNamedQuery("KMVFactDependency.findAllDistinctTypeByWildcard");
         query.setParameter("facttype", refDetails.getLookupType().getCodeType().toUpperCase() + "%");
      }
      query.setFirstResult(startRec);
      query.setMaxResults(maxDisplay);            
      List<String> foundList = (List<String>) query.getResultList();

      for (int r = 0; r < foundList.size(); r++) {
         ReferenceDataRefDataType aRefData = new ReferenceDataRefDataType();
         aRefData.setId(0); //TODO Need to pull the ID for each one of this type from ref_fact_type ..another call
         aRefData.setName(foundList.get(r));

         refColl.add(aRefData);
      }
      return refColl;
   }
   private ArrayList<ReferenceDataRefDataType> getUsedRefFactSchemes()
   {
      ArrayList<ReferenceDataRefDataType> refColl = new ArrayList<ReferenceDataRefDataType>();
      Query query = null;
      int startRec = refDetails.getStartingRecNumber();
      int maxDisplay = refDetails.getMaxRecordsToShow();
      String q = null;
      //---------------------------------
      //retrieve all Schemes (code system) based on
      //   1) type (i.e. LAB, ALLERGY, ...)
      //or 2) type (i.e. LAB, ALLERGY, ...) + partial scheme name
      //---------------------------------
      System.out.println("\n========> Getting Used factscheme reference");
      //--------------------------------------------------
      //Get Code based on schemeTypeId
      //or based on partial scheme name
      //--------------------------------------------------
      //NATIVE - works but slow.
      q = "SELECT DISTINCT  fs.terminologyscheme"
              + " FROM kmv_factdependency fd, factspecification fs"
              + " WHERE fd.fd_id = fs.fd_id";

      if ((refDetails.getLookupSystem().getCodeType() != null) &&
              !(refDetails.getLookupSystem().getCodeType().equals(""))) {
         q = q + " AND  fd.type = '" + refDetails.getLookupSystem().getCodeType() + "'";
      }
      if ((refDetails.getLookupSystem().getCodeSystem() != null) &&
              !(refDetails.getLookupSystem().getCodeSystem().equals(""))) {
         q = q + " AND fs.terminologyscheme LIKE '" + refDetails.getLookupSystem().getCodeSystem() + "%'";
      }
      System.out.println("\n====> SQL=" + q);

      query = em.createNativeQuery(q);
      query.setFirstResult(startRec);
      query.setMaxResults(maxDisplay);
      List<Vector> foundList = (List<Vector>) query.getResultList();

      for (Vector res : foundList) {
         System.out.println("---> SCHEME=" + (String) res.get(0));

         ReferenceDataRefDataType aRefData = new ReferenceDataRefDataType();
         aRefData.setId(0); //TODO Need to pull the ID for each one of this type from ref_fact_scheme ..another call
         aRefData.setName((String) res.get(0));

         refColl.add(aRefData);
      }
      return refColl;
   }
   private ArrayList<ReferenceDataRefDataType> getUsedRefFactCodes()
   {
      ArrayList<ReferenceDataRefDataType> refColl = new ArrayList<ReferenceDataRefDataType>();
      Query query = null;
      int startRec = refDetails.getStartingRecNumber();
      int maxDisplay = refDetails.getMaxRecordsToShow();
      String q = null;

      System.out.println("\n========> Getting Used factcode reference");
      //--------------------------------------------------
      //Get Code based on schemeTypeId
      //or based on schemeTypeId AND partial Content Code.
      //--------------------------------------------------
      q = "SELECT NEW gov.hhs.fha.nhinc.kmr.model.RefCodeSimple(c.preferredConceptName, c.refTaxonomyCodePK.conceptCode)"
              + " FROM KMVFactDependency fd, FactSpecification fs, RefTaxonomyCode c, RefDictionary s"
              + " WHERE fd.fdId = fs.factSpecificationPK.fdId"
              + " AND fd.type = s.conceptName"
              + " AND s.dictionaryId = c.refTaxonomyCodePK.dictionaryId"
              + " AND c.refTaxonomyCodePK.codeSystemOid = s.activeCodeSystemOid"
              + " AND c.refTaxonomyCodePK.conceptCode = fs.factSpecificationPK.terminologyCode";

      if ((refDetails.getLookupCode().getCodeType() != null) &&
              !(refDetails.getLookupCode().getCodeType().equals(""))) {
         q = q + " AND  fd.type = '" + refDetails.getLookupCode().getCodeType() + "'";
      }
      if ((refDetails.getLookupCode().getCodeSystem() != null) &&
              !(refDetails.getLookupCode().getCodeSystem().equals(""))) {
         q = q + " AND fs.factSpecificationPK.terminologyScheme = '" + refDetails.getLookupCode().getCodeSystem() + "'";
      }
      if ((refDetails.getLookupCode().getContentCode() != null) &&
              !(refDetails.getLookupCode().getContentCode().equals(""))) {
         q = q + " AND UPPER(c.preferredConceptName) LIKE '%"
                 + refDetails.getLookupCode().getContentCode().toUpperCase() + "%'";
      }
      q = q + " GROUP BY c.preferredConceptName, c.refTaxonomyCodePK.conceptCode";
      query = em.createQuery(q);
      //------------------------------------------------------------

      query.setFirstResult(startRec);
      query.setMaxResults(maxDisplay);
      List<RefCodeSimple> foundList = query.getResultList();

      for (RefCodeSimple c : foundList) {

         //System.out.println("---> NAME=" + c.getName());
         //System.out.println("---> NAME=" + c.getDescription());

         ReferenceDataRefDataType aRefData = new ReferenceDataRefDataType();
         aRefData.setId(0);
         aRefData.setName(c.getName());
         aRefData.setDescr(c.getDescription());
         refColl.add(aRefData);
      }
      return refColl;
   }


}
