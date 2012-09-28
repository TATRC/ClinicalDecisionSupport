package gov.hhs.fha.nhinc.kmr.dao;

import gov.hhs.fha.nhinc.kmr.kmtypes.ReferenceDataLookupType;
import gov.hhs.fha.nhinc.kmr.kmtypes.ReferenceDataRefDataType;
import gov.hhs.fha.nhinc.kmr.kmtypes.ReferenceDataResponseType;
import java.util.ArrayList;

import javax.persistence.EntityManager;

import javax.xml.datatype.DatatypeConfigurationException;
import org.apache.log4j.Logger;

public class RefResponseListBuilder {

   private final static Logger log = Logger.getLogger(ResponseListBuilder.class.getName());

   //--------------------------------------------------------------------------
   //  Constructor
   //--------------------------------------------------------------------------
   public RefResponseListBuilder() {
   }


   /**
    * buildRefDataResponseList() - builds the Reference data response object.
    *
    * @param refType
    * @param em
    * @return
    * @throws DatatypeConfigurationException
    */
   public ReferenceDataResponseType buildRefDataResponseList(
            ReferenceDataLookupType refDetails
           ,EntityManager em)
   throws DatatypeConfigurationException
   {
      ArrayList<ReferenceDataRefDataType> references = null;
      ReferenceDataResponseType response = new ReferenceDataResponseType();

      //PROCESSING USED REFERENCE LOOKUP
      if (refDetails.getReference().startsWith("used")) {
         references = this.getUsedReferences(em, refDetails);

      //PROCESSING FULL REFERENCE LOOKUP
      } else {
         references = this.getAllReferences(em, refDetails);
      }

      response.getRefData().addAll(references);
      response.setTotalReferencesFound(references.size());
      return response;
   }

   //===================== USED REFERENCES ======================
   /**s
    * @param refDetails
    * @param em
    * @return
    * @throws DatatypeConfigurationException
    */
   public ArrayList<ReferenceDataRefDataType> getUsedReferences(
                                           EntityManager em
                                          ,ReferenceDataLookupType refDetails)
   throws DatatypeConfigurationException 
   {
      ArrayList<ReferenceDataRefDataType> references = null;
      String ref = refDetails.getReference();

      if (ref.equalsIgnoreCase("usedfacttype") ||
          ref.equalsIgnoreCase("usedfactscheme") ||
          ref.equalsIgnoreCase("usedfactcode")) {
         UsedFactReferences usedRefs = new UsedFactReferences(em, refDetails);
         references = usedRefs.getRefs();

      }
      else if (ref.equalsIgnoreCase("usedtasktype") ||
               ref.equalsIgnoreCase("usedtaskscheme") ||
               ref.equalsIgnoreCase("usedtaskcode")) {
         UsedTaskReferences usedRefs = new UsedTaskReferences(em, refDetails);
         references = usedRefs.getRefs();
      }
      else if (ref.equalsIgnoreCase("usedspecialtyscheme") ||
               ref.equalsIgnoreCase("usedspecialtycode")) {
         UsedSpecialtyReferences usedRefs = new UsedSpecialtyReferences(em, refDetails);
         references = usedRefs.getRefs();
      }
      else if (ref.equalsIgnoreCase("useddemogscheme")||
               ref.equalsIgnoreCase("useddemogcode")  ||
               ref.equalsIgnoreCase("useddemogtype") ||     //get ALL DEMOG TYPEs
               ref.equalsIgnoreCase("useddemoggender") ||   //get specific demogtype = GENDER
               ref.equalsIgnoreCase("useddemogagerange") || //get specific demogtype = AGERANGE
               ref.equalsIgnoreCase("useddemogvalue") ||    //used to get GENDER, AGERANGE. switch done at Oryx mid-tier.
               ref.equalsIgnoreCase("useddemogstatus") ||
               ref.equalsIgnoreCase("useddemogacuity")){
         UsedDemographicReferences usedRefs = new UsedDemographicReferences(em, refDetails);
         references = usedRefs.getRefs();
      }
      else if (ref.equalsIgnoreCase("usedkmtype") ||
               ref.equalsIgnoreCase("usedorglevel") ||
               ref.equalsIgnoreCase("usedkmstatus")) {
         UsedKmReferences usedRefs = new UsedKmReferences(em, refDetails);
         references = usedRefs.getRefs();

      }
      
      return references;
   }

   
   /**
    * getAllReferences()
    * @param em
    * @param refDetails
    * @return
    * @throws DatatypeConfigurationException
    */
   public ArrayList<ReferenceDataRefDataType> getAllReferences(
                                           EntityManager em
                                          ,ReferenceDataLookupType refDetails)
   throws DatatypeConfigurationException
   {
      ArrayList<ReferenceDataRefDataType> references = null;
      String ref = refDetails.getReference();

      //retrieve all reference codes.
      //function getRefCodes() will also filter if a specific type is given.
      if (ref.endsWith("code")) {
         References allRefs = new References(em, refDetails);
         references = allRefs.getRefCodes();
      }

/*
      else if (ref.equalsIgnoreCase("specialtyscheme") ||
               ref.equalsIgnoreCase("specialtycode")) {
         SpecialtyReferences allRefs = new SpecialtyReferences(em, refDetails);
         references = allRefs.getRefs();
      }
      else if (ref.equalsIgnoreCase("demogscheme")||
               ref.equalsIgnoreCase("demogcode")  ||
               ref.equalsIgnoreCase("demogtype") ||     //get ALL DEMOG TYPEs
               ref.equalsIgnoreCase("demoggender") ||   //get specific demogtype = GENDER
               ref.equalsIgnoreCase("demogagerange") || //get specific demogtype = AGERANGE
               ref.equalsIgnoreCase("demogvalue") ||
               ref.equalsIgnoreCase("demogstatus") ||
               ref.equalsIgnoreCase("demogacuity")){
         DemographicReferences allRefs = new DemographicReferences(em, refDetails);
         references = allRefs.getRefs();
      }
*/
      //TODO ORG LEVEL
      //TODO KM STATUS
      //TODO KM TYPE

      return references;
   }


}
