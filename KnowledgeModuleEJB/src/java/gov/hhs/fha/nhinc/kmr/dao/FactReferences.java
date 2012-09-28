package gov.hhs.fha.nhinc.kmr.dao;

import gov.hhs.fha.nhinc.kmr.kmtypes.ReferenceDataLookupType;
import gov.hhs.fha.nhinc.kmr.kmtypes.ReferenceDataRefDataType;
import gov.hhs.fha.nhinc.kmr.model.RefCodeSimple;
import gov.hhs.fha.nhinc.kmr.model.RefFactCode;
import gov.hhs.fha.nhinc.kmr.model.RefFactScheme;
import gov.hhs.fha.nhinc.kmr.model.RefFactType;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import javax.xml.datatype.DatatypeConfigurationException;
import org.apache.log4j.Logger;

public class FactReferences {

   private final static Logger log = Logger.getLogger(FactReferences.class.getName());

   protected EntityManager em;
   protected ReferenceDataLookupType refDetails;

   //--------------------------------------------------------------------------
   //  Constructor
   //--------------------------------------------------------------------------
   //public FactReferences() {
   //}

   public FactReferences(EntityManager em, ReferenceDataLookupType refDetails) {
     this.em = em;
     this.refDetails = refDetails;
   }

   public ArrayList<ReferenceDataRefDataType> getRefs() throws DatatypeConfigurationException
   {
      ArrayList<ReferenceDataRefDataType> references = null;
      String ref = refDetails.getReference();

      if (ref.equalsIgnoreCase("facttype")) {
         references = this.getRefFactTypes();
      } else if (ref.equalsIgnoreCase("factscheme")) {
         references = this.getRefFactSchemes();
      } else if (ref.equalsIgnoreCase("factcode")) {
         references = this.getRefFactCodes();
      }
      return references;
   }

/*********************************************************************
 **                     FACT REFERENCES
 *********************************************************************/
   private ArrayList<ReferenceDataRefDataType> getRefFactTypes()
   {
      System.out.println("\n========> Getting facttype references");

      ArrayList<ReferenceDataRefDataType> refColl = new ArrayList<ReferenceDataRefDataType>();
      Query query = null;
      int startRec = refDetails.getStartingRecNumber();
      int maxDisplay = refDetails.getMaxRecordsToShow();

      if ((refDetails.getLookupType().getCodeType() == null) ||
              (refDetails.getLookupType().getCodeType().isEmpty())) {
         query = em.createNamedQuery("RefFactType.findAll");
      } else {
         query = em.createNamedQuery("RefFactType.findAllByWildcard");
         query.setParameter("facttype", refDetails.getLookupType().getCodeType().toUpperCase() + "%");
      }
      query.setFirstResult(startRec);
      query.setMaxResults(maxDisplay);
      List<RefFactType> foundList = (List<RefFactType>) query.getResultList();

      for (int r = 0; r < foundList.size(); r++) {
         ReferenceDataRefDataType aRefData = new ReferenceDataRefDataType();
         aRefData.setId(foundList.get(r).getFacttypeid());
         aRefData.setName(foundList.get(r).getFacttype());

         refColl.add(aRefData);
      }
      return refColl;
   }

   private ArrayList<ReferenceDataRefDataType> getRefFactSchemes()
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
      System.out.println("\n========> Getting factscheme reference");
      //--------------------------------------------------
      //Get Code based on schemeTypeId
      //or based on partial schemename
      //--------------------------------------------------
      //NATIVE - works but slow.
      q = "SELECT s.* "
              + " FROM ref_fact_scheme_type st, ref_fact_type t, ref_fact_scheme s"
              + " WHERE st.facttypeid = t.facttypeid"
              + " AND st.factschemeid = s.factschemeid";

      if ((refDetails.getLookupSystem().getCodeType() != null) &&
              !(refDetails.getLookupSystem().getCodeType().equals(""))) {
         q = q + " AND  t.facttype = '" + refDetails.getLookupSystem().getCodeType() + "'";
      }
      if ((refDetails.getLookupSystem().getCodeSystem() != null) &&
              !(refDetails.getLookupSystem().getCodeSystem().equals(""))) {
         q = q + " AND s.schemename = '" + refDetails.getLookupSystem().getCodeSystem() + "%'";
      }
      System.out.println("\n====> SQL=" + q);

      query = em.createNativeQuery(q, RefFactScheme.class);
      query.setFirstResult(startRec);
      query.setMaxResults(maxDisplay);
      List<RefFactScheme> foundList = (List<RefFactScheme>) query.getResultList();

      for (int r = 0; r < foundList.size(); r++) {
         ReferenceDataRefDataType aRefData = new ReferenceDataRefDataType();
         aRefData.setId(foundList.get(r).getFactschemeid());
         aRefData.setName(foundList.get(r).getSchemename());
         aRefData.setDescr(foundList.get(r).getDescr());
         refColl.add(aRefData);
      }
      return refColl;
   }

   private ArrayList<ReferenceDataRefDataType> getRefFactCodes()
   {
      ArrayList<ReferenceDataRefDataType> refColl = new ArrayList<ReferenceDataRefDataType>();
      Query query = null;
      int startRec = refDetails.getStartingRecNumber();
      int maxDisplay = refDetails.getMaxRecordsToShow();
      String q = null;

      System.out.println("\n========> Getting factcode reference");

      q = "SELECT NEW gov.hhs.fha.nhinc.kmr.model.RefCodeSimple(c.preferredConceptName, c.refTaxonomyCodePK.conceptCode)"
              + " FROM RefTaxonomyCode c, RefDictionary s"
              + " WHERE "
              + " s.dictionaryId = c.refTaxonomyCodePK.dictionaryId"
              + " AND c.refTaxonomyCodePK.codeSystemOid = s.activeCodeSystemOid";

      if ((refDetails.getLookupCode().getCodeType() != null) &&
              !(refDetails.getLookupCode().getCodeType().equals(""))) {
         q = q + " AND  s.conceptName = '" + refDetails.getLookupCode().getCodeType() + "'";
      }
//      if ((refDetails.getLookupCode().getCodeSystem() != null) &&
//              !(refDetails.getLookupCode().getCodeSystem().equals(""))) {
//         q = q + " AND fs.factSpecificationPK.terminologyScheme = '" + refDetails.getLookupCode().getCodeSystem() + "'";
//      }
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

      /*
      //--------------------------------------------------
      //Get Code based on schemeTypeId
      //or based on schemeTypeId AND partial Content Code.
      //--------------------------------------------------
      //NATIVE - works but slow.
      q = "SELECT c.* " + " FROM ref_fact_scheme_type st, ref_fact_type t, ref_fact_scheme s, ref_fact_code c" + " WHERE st.facttypeid = t.facttypeid" + " AND st.factschemeid = s.factschemeid" + " AND c.schemetypeid = st.schemetypeid";

      if ((refDetails.getLookupCode().getCodeType() != null) &&
              !(refDetails.getLookupCode().getCodeType().equals(""))) {
         q = q + " AND  t.facttype = '" + refDetails.getLookupCode().getCodeType() + "'";
      }
      if ((refDetails.getLookupCode().getCodeSystem() != null) &&
              !(refDetails.getLookupCode().getCodeSystem().equals(""))) {
         q = q + " AND s.schemename = '" + refDetails.getLookupCode().getCodeSystem() + "'";
      }
      if ((refDetails.getLookupCode().getContentCode() != null) &&
              !(refDetails.getLookupCode().getContentCode().equals(""))) {
         q = q + " AND c.concept_code LIKE '" + refDetails.getLookupCode().getContentCode() + "%'";
      }
      System.out.println("\n====> SQL=" + q);

      query = em.createNativeQuery(q, RefFactCode.class);
      query.setFirstResult(startRec);
      query.setMaxResults(maxDisplay);
      List<RefFactCode> foundList = (List<RefFactCode>) query.getResultList();

      for (int r = 0; r < foundList.size(); r++) {
         ReferenceDataRefDataType aRefData = new ReferenceDataRefDataType();
         aRefData.setId(foundList.get(r).getRefFactCodePK().getSchemetypeid());
         aRefData.setName(foundList.get(r).getRefFactCodePK().getConceptCode());
         aRefData.setDescr(foundList.get(r).getPreferredConceptName());

         refColl.add(aRefData);
      }
      return refColl;
      */
   }

}
