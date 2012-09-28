package gov.hhs.fha.nhinc.kmr.dao;

import gov.hhs.fha.nhinc.kmr.kmtypes.ReferenceDataLookupType;
import gov.hhs.fha.nhinc.kmr.kmtypes.ReferenceDataRefDataType;
import gov.hhs.fha.nhinc.kmr.model.RefCodeSimple;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import javax.xml.datatype.DatatypeConfigurationException;
import org.apache.log4j.Logger;

public class UsedKmReferences {

   private final static Logger log = Logger.getLogger(UsedKmReferences.class.getName());

   protected EntityManager em;
   protected ReferenceDataLookupType refDetails;

   //--------------------------------------------------------------------------
   //  Constructor
   //--------------------------------------------------------------------------
   //public UsedSpecialtyReferences() {
   //}

   public UsedKmReferences(EntityManager em, ReferenceDataLookupType refDetails) {
     this.em = em;
     this.refDetails = refDetails;
   }

   public ArrayList<ReferenceDataRefDataType> getRefs() throws DatatypeConfigurationException
   {
      ArrayList<ReferenceDataRefDataType> references = null;
      String ref = refDetails.getReference();

      if (ref.equalsIgnoreCase("usedkmtype")) {
         references = this.getUsedRefKmTypes();
      }
      else if (ref.equalsIgnoreCase("usedorglevel")) {
         references = this.getUsedRefOrgLevel();
      }
      else if (ref.equalsIgnoreCase("usedkmstatus")) {
         references = this.getUsedRefKmStatus();
      }
      return references;
   }

   /**
    * 
    * @return
    */
   private ArrayList<ReferenceDataRefDataType> getUsedRefKmTypes()
   {
      System.out.println("\n========> Getting Used KM Type references");

      ArrayList<ReferenceDataRefDataType> refColl = new ArrayList<ReferenceDataRefDataType>();
      Query query = null;
      int startRec = refDetails.getStartingRecNumber();
      int maxDisplay = refDetails.getMaxRecordsToShow();
      String q = null;

      q = "SELECT NEW gov.hhs.fha.nhinc.kmr.model.RefCodeSimple(c.preferredConceptName, c.refTaxonomyCodePK.conceptCode)"
              + " FROM KnowledgeModule fs, RefTaxonomyCode c, RefDictionary s"
              + " WHERE "
              + "     s.conceptName = 'KMTYPE'"
              + " AND s.dictionaryId = c.refTaxonomyCodePK.dictionaryId"
              + " AND c.refTaxonomyCodePK.codeSystemOid = s.activeCodeSystemOid"
              + " AND c.refTaxonomyCodePK.conceptCode = fs.kMType";

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

   /**
    *
    * @return
    */
   private ArrayList<ReferenceDataRefDataType> getUsedRefOrgLevel()
   {
      System.out.println("\n========> Getting Used KM ORG Level references");

      ArrayList<ReferenceDataRefDataType> refColl = new ArrayList<ReferenceDataRefDataType>();
      Query query = null;
      int startRec = refDetails.getStartingRecNumber();
      int maxDisplay = refDetails.getMaxRecordsToShow();
      String q = null;

      q = "SELECT NEW gov.hhs.fha.nhinc.kmr.model.RefCodeSimple(c.preferredConceptName, c.refTaxonomyCodePK.conceptCode)"
              + " FROM KnowledgeModule fs, RefTaxonomyCode c, RefDictionary s"
              + " WHERE "
              + "     s.conceptName = 'ORGLEVEL'"
              + " AND s.dictionaryId = c.refTaxonomyCodePK.dictionaryId"
              + " AND c.refTaxonomyCodePK.codeSystemOid = s.activeCodeSystemOid"
              + " AND c.refTaxonomyCodePK.conceptCode = fs.kMOrganizationLevel";

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

   /**
    *
    * @return
    */
   private ArrayList<ReferenceDataRefDataType> getUsedRefKmStatus()
   {
      System.out.println("\n========> Getting Used KM Validation Status references");

      ArrayList<ReferenceDataRefDataType> refColl = new ArrayList<ReferenceDataRefDataType>();
      Query query = null;
      int startRec = refDetails.getStartingRecNumber();
      int maxDisplay = refDetails.getMaxRecordsToShow();
      String q = null;

      q = "SELECT NEW gov.hhs.fha.nhinc.kmr.model.RefCodeSimple(c.preferredConceptName, c.refTaxonomyCodePK.conceptCode)"
              + " FROM KnowledgeModule fs, RefTaxonomyCode c, RefDictionary s"
              + " WHERE "
              + "     s.conceptName = 'KMSTATUS'"
              + " AND s.dictionaryId = c.refTaxonomyCodePK.dictionaryId"
              + " AND c.refTaxonomyCodePK.codeSystemOid = s.activeCodeSystemOid"
              + " AND c.refTaxonomyCodePK.conceptCode = fs.validationStatus";

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
