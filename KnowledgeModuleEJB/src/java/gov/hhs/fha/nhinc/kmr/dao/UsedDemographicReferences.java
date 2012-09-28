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

public class UsedDemographicReferences {

   private final static Logger log = Logger.getLogger(UsedDemographicReferences.class.getName());

   protected EntityManager em;
   protected ReferenceDataLookupType refDetails;

   //--------------------------------------------------------------------------
   //  Constructor
   //--------------------------------------------------------------------------
   //public UsedDemogReferences() {
   //}

   public UsedDemographicReferences(EntityManager em, ReferenceDataLookupType refDetails) {
     this.em = em;
     this.refDetails = refDetails;
   }

   public ArrayList<ReferenceDataRefDataType> getRefs() throws DatatypeConfigurationException
   {
      ArrayList<ReferenceDataRefDataType> references = null;
      String ref = refDetails.getReference();

      if (ref.equalsIgnoreCase("useddemogscheme")) {
         references = this.getUsedRefDemogSchemes();
      } else if (ref.equalsIgnoreCase("useddemogcode")) {
         references = this.getUsedRefDemogCodes();
      } else if (ref.equalsIgnoreCase("useddemogtype")) {
         references = this.getUsedRefDemogType();
      } else if (ref.equalsIgnoreCase("useddemogvalue")) {
         references = this.getUsedRefDemogValues();
      } else if (ref.equalsIgnoreCase("useddemogstatus")) {
         references = this.getUsedRefDemogStatus();
      } else if (ref.equalsIgnoreCase("useddemogacuity")) {
         references = this.getUsedRefDemogAcuity();
      }
      return references;
   }



   /**
    * Patient SCOPE (actuity)
    * @return
    */
   private ArrayList<ReferenceDataRefDataType> getUsedRefDemogType()
   {
      System.out.println("\n========> Getting Used demogtype references");

      ArrayList<ReferenceDataRefDataType> refColl = new ArrayList<ReferenceDataRefDataType>();
      Query query = null;
      int startRec = refDetails.getStartingRecNumber();
      int maxDisplay = refDetails.getMaxRecordsToShow();

      String lookupValue = null;
      if ( (refDetails.getLookupType().getCodeType() == null) ||
           refDetails.getLookupType().getCodeType().isEmpty() )
      {
         lookupValue = "%";
      } else {
         lookupValue = refDetails.getLookupType().getCodeType().toUpperCase() + "%";
      }
      query = em.createNamedQuery("PopulationSpecification.findAllDistinctTypeByWildcard");
      query.setParameter("type", lookupValue);
      query.setFirstResult(startRec);
      query.setMaxResults(maxDisplay);
      List<String> foundList = (List<String>) query.getResultList();

      for (int r = 0; r < foundList.size(); r++) {
         ReferenceDataRefDataType aRefData = new ReferenceDataRefDataType();
         aRefData.setName(foundList.get(r));

         //TODO Need to pull the ID adn Descr for each one of this  from ref_demog_ .. need another call
         aRefData.setId(0);
         aRefData.setDescr(null);

         refColl.add(aRefData);
      }
      return refColl;
   }

   /**
    * Patient SCOPE (actuity)
    * @return
    */
   private ArrayList<ReferenceDataRefDataType> getUsedRefDemogAcuity()
   {
      System.out.println("\n========> Getting Used demogacuity references");
ArrayList<ReferenceDataRefDataType> refColl = new ArrayList<ReferenceDataRefDataType>();
      Query query = null;
      int startRec = refDetails.getStartingRecNumber();
      int maxDisplay = refDetails.getMaxRecordsToShow();
      String q = null;

      q = "SELECT NEW gov.hhs.fha.nhinc.kmr.model.RefCodeSimple(c.preferredConceptName, c.refTaxonomyCodePK.conceptCode)"
              + " FROM KMVPopulationDependency fs, RefTaxonomyCode c, RefDictionary s"
              + " WHERE "
              + "     s.conceptName = 'ACUITY'"
              + " AND s.dictionaryId = c.refTaxonomyCodePK.dictionaryId"
              + " AND c.refTaxonomyCodePK.codeSystemOid = s.activeCodeSystemOid"
              + " AND c.refTaxonomyCodePK.conceptCode = fs.pDScope";

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
   private ArrayList<ReferenceDataRefDataType> getUsedRefDemogStatus()
   {
      System.out.println("\n========> Getting Used demogstatus references");

      ArrayList<ReferenceDataRefDataType> refColl = new ArrayList<ReferenceDataRefDataType>();
      Query query = null;
      int startRec = refDetails.getStartingRecNumber();
      int maxDisplay = refDetails.getMaxRecordsToShow();
      String q = null;

      q = "SELECT NEW gov.hhs.fha.nhinc.kmr.model.RefCodeSimple(c.preferredConceptName, c.refTaxonomyCodePK.conceptCode)"
              + " FROM KMVPopulationDependency fs, RefTaxonomyCode c, RefDictionary s"
              + " WHERE "
              + "     s.conceptName = 'DEMOGSTATUS'"
              + " AND s.dictionaryId = c.refTaxonomyCodePK.dictionaryId"
              + " AND c.refTaxonomyCodePK.codeSystemOid = s.activeCodeSystemOid"
              + " AND c.refTaxonomyCodePK.conceptCode = fs.pDStatus";

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
   private ArrayList<ReferenceDataRefDataType> getUsedRefDemogSchemes()
   {      
      ArrayList<ReferenceDataRefDataType> refColl = new ArrayList<ReferenceDataRefDataType>();
      Query query = null;
      int startRec = refDetails.getStartingRecNumber();
      int maxDisplay = refDetails.getMaxRecordsToShow();

      String lookupScheme = null;
      if ((refDetails.getLookupSystem().getCodeSystem() == null) ||
          refDetails.getLookupSystem().getCodeSystem().isEmpty())
      {
         lookupScheme = "%";
      } else {
         lookupScheme = refDetails.getLookupSystem().getCodeSystem().toUpperCase() + "%";
      }

      System.out.println("\n========> Getting Used demogscheme references with "+ lookupScheme);

      query = em.createNamedQuery("PopulationSpecification.findAllDistinctSchemeByWildcard");
      query.setParameter("terminologyScheme", lookupScheme);
      query.setFirstResult(startRec);
      query.setMaxResults(maxDisplay);            
      List<String> foundList = (List<String>) query.getResultList();

      for (int r = 0; r < foundList.size(); r++) {
         ReferenceDataRefDataType aRefData = new ReferenceDataRefDataType();
         aRefData.setId(0); //TODO Need to pull the ID for each one of this type from ref_demog_type ..another call
         aRefData.setName(foundList.get(r));

         refColl.add(aRefData);
      }
      return refColl;     
   }


   /**
    *
    * @return
    */
   private ArrayList<ReferenceDataRefDataType> getUsedRefDemogCodes()
   {
      System.out.println("\n========> Getting Used demogcode references");

      ArrayList<ReferenceDataRefDataType> refColl = new ArrayList<ReferenceDataRefDataType>();
      Query query = null;
      int startRec = refDetails.getStartingRecNumber();
      int maxDisplay = refDetails.getMaxRecordsToShow();

      //when both scheme and code ARE NOT given to lookup by...
      if (((refDetails.getLookupCode().getCodeSystem() == null)||refDetails.getLookupCode().getCodeSystem().isEmpty()) &&
          ((refDetails.getLookupCode().getContentCode()== null)||refDetails.getLookupCode().getContentCode().isEmpty()))
      {
         query = em.createNamedQuery("PopulationSpecification.findAllDistinctCode");
      } 
      //when both scheme and code ARE given to lookup by...
      if (((refDetails.getLookupCode().getCodeSystem()!= null)&& !refDetails.getLookupCode().getCodeSystem().equals("")) &&
          ((refDetails.getLookupCode().getContentCode()!= null)&& !refDetails.getLookupCode().getContentCode().equals("")))
      {
         query = em.createNamedQuery("PopulationSpecification.findAllDistinctCodeByWildcardSchemeAndCode");
         query.setParameter("terminologyScheme", refDetails.getLookupCode().getCodeSystem().toUpperCase() + "%");
         query.setParameter("terminologyCode", refDetails.getLookupCode().getContentCode().toUpperCase() + "%");
      }
      //when ONLY scheme IS given.....
      else if ((refDetails.getLookupCode().getCodeSystem()!= null)&& !refDetails.getLookupCode().getCodeSystem().equals(""))
      {
         query = em.createNamedQuery("PopulationSpecification.findAllDistinctCodeByWildcardScheme");
         query.setParameter("terminologyScheme", refDetails.getLookupCode().getCodeSystem().toUpperCase() + "%");
      }
      //when ONLY code IS given.....
      else if ((refDetails.getLookupCode().getContentCode()!= null)&& !refDetails.getLookupCode().getContentCode().equals(""))
      {
         query = em.createNamedQuery("PopulationSpecification.findAllDistinctCodeByWildcardCode");
         query.setParameter("terminologyCode", refDetails.getLookupCode().getContentCode().toUpperCase() + "%");
      }
      query.setFirstResult(startRec);
      query.setMaxResults(maxDisplay);
      List<String> foundList = (List<String>) query.getResultList();

      for (int r = 0; r < foundList.size(); r++) {
         ReferenceDataRefDataType aRefData = new ReferenceDataRefDataType();
         aRefData.setId(0); //TODO Need to pull the ID for each one of this type from ref_demog_type ..another call
         aRefData.setName(foundList.get(r));

         refColl.add(aRefData);
      }
      return refColl;
   }

   /**
    * NOTE:  The DEMOG Value request will only be allowed two possible params:
    *            1)  type    --> required (currently: GENDER, AGERANGE, REGISTRY, OCCUPATION)
    *            2) value    --> optional (will be treated as a wildcard request)
    *
    * @return
    */
   private ArrayList<ReferenceDataRefDataType> getUsedRefDemogValues()
   {
      System.out.println("\n========> Getting Used demogvalue references");

      ArrayList<ReferenceDataRefDataType> refColl = new ArrayList<ReferenceDataRefDataType>();
      Query query = null;
      int startRec = refDetails.getStartingRecNumber();
      int maxDisplay = refDetails.getMaxRecordsToShow();
      String q = null;
      
      String lookupValue = null;

      //when no demog type is given, return nothing
      if ( (refDetails.getLookupCode().getCodeType() == null) ||
           refDetails.getLookupCode().getCodeType().isEmpty() )
      {
         System.out.println("ERROR:  DEMOG TYPE is NULL");
         refColl = null;
      }
      else { //when type is given...
         q = "SELECT NEW gov.hhs.fha.nhinc.kmr.model.RefCodeSimple(c.preferredConceptName, c.refTaxonomyCodePK.conceptCode)"
                 + " FROM PopulationSpecification fs, RefTaxonomyCode c, RefDictionary s"
                 + " WHERE "
                 + "     s.dictionaryId = "+refDetails.getLookupCode().getCodeType()
                 + " AND s.dictionaryId = c.refTaxonomyCodePK.dictionaryId"
                 + " AND c.refTaxonomyCodePK.codeSystemOid = s.activeCodeSystemOid"
                 + " AND c.refTaxonomyCodePK.conceptCode = fs.populationSpecificationPK.terminologyCode";

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
      }
      return refColl;
   }
}
