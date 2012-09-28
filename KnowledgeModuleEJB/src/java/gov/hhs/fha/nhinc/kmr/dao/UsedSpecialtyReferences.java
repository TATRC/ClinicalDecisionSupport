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

public class UsedSpecialtyReferences {

   private final static Logger log = Logger.getLogger(UsedSpecialtyReferences.class.getName());

   protected EntityManager em;
   protected ReferenceDataLookupType refDetails;

   //--------------------------------------------------------------------------
   //  Constructor
   //--------------------------------------------------------------------------
   //public UsedSpecialtyReferences() {
   //}

   public UsedSpecialtyReferences(EntityManager em, ReferenceDataLookupType refDetails) {
     this.em = em;
     this.refDetails = refDetails;
   }

   public ArrayList<ReferenceDataRefDataType> getRefs() throws DatatypeConfigurationException
   {
      ArrayList<ReferenceDataRefDataType> references = null;
      String ref = refDetails.getReference();

      if (ref.equalsIgnoreCase("usedspecialtyscheme")) {
         references = this.getUsedRefSpecialtySchemes();
      } else if (ref.equalsIgnoreCase("usedspecialtycode")) {
         references = this.getUsedRefSpecialtyCodes();
      }
      return references;
   }

   /**
    * 
    * @return
    */
   private ArrayList<ReferenceDataRefDataType> getUsedRefSpecialtySchemes()
   {
      System.out.println("\n========> Getting Used specialtyscheme references");

      ArrayList<ReferenceDataRefDataType> refColl = new ArrayList<ReferenceDataRefDataType>();
      Query query = null;
      int startRec = refDetails.getStartingRecNumber();
      int maxDisplay = refDetails.getMaxRecordsToShow();

      if ((refDetails.getLookupSystem().getCodeSystem() == null) ||
              (refDetails.getLookupSystem().getCodeSystem().isEmpty())) {
         query = em.createNamedQuery("KMSpecialty.findAllDistinctScheme");
      } else {
         query = em.createNamedQuery("KMSpecialty.findAllDistinctSchemeByWildcard");
         query.setParameter("terminologyScheme", refDetails.getLookupSystem().getCodeSystem().toUpperCase() + "%");
      }
      query.setFirstResult(startRec);
      query.setMaxResults(maxDisplay);            
      List<String> foundList = (List<String>) query.getResultList();

      for (int r = 0; r < foundList.size(); r++) {
         ReferenceDataRefDataType aRefData = new ReferenceDataRefDataType();
         aRefData.setId(0); //TODO Need to pull the ID for each one of this type from ref_specialty_type ..another call
         aRefData.setName(foundList.get(r));

         refColl.add(aRefData);
      }
      return refColl;
   }


   /**
    *
    * @return
    */
   private ArrayList<ReferenceDataRefDataType> getUsedRefSpecialtyCodes()
   {
      ArrayList<ReferenceDataRefDataType> refColl = new ArrayList<ReferenceDataRefDataType>();
      Query query = null;
      int startRec = refDetails.getStartingRecNumber();
      int maxDisplay = refDetails.getMaxRecordsToShow();
      String q = null;

      System.out.println("\n========> Getting Used Specialty reference");
      //--------------------------------------------------
      //Get Code based on schemeTypeId
      //or based on schemeTypeId AND partial Content Code.
      //--------------------------------------------------
      q = "SELECT NEW gov.hhs.fha.nhinc.kmr.model.RefCodeSimple(c.preferredConceptName, c.refTaxonomyCodePK.conceptCode)"
              + " FROM KMSpecialty fs, RefTaxonomyCode c, RefDictionary s"
              + " WHERE "
              + "     s.conceptName = 'SPECIALTY'"
              + " AND s.dictionaryId = c.refTaxonomyCodePK.dictionaryId"
              + " AND c.refTaxonomyCodePK.codeSystemOid = s.activeCodeSystemOid"
              + " AND c.refTaxonomyCodePK.conceptCode = fs.kMSpecialtyPK.terminologyCode";
      /*
      if ((refDetails.getLookupCode().getCodeSystem() != null) &&
              !(refDetails.getLookupCode().getCodeSystem().equals(""))) {
         q = q + " AND fs.kMSpecialtyPK.terminologyScheme = '" + refDetails.getLookupCode().getCodeSystem() + "'";
      } */
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
