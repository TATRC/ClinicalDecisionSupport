
package gov.hhs.fha.nhinc.kmr.dao;

import gov.hhs.fha.nhinc.kmr.kmtypes.InferenceListType;
import gov.hhs.fha.nhinc.kmr.kmtypes.KMVInferenceEngineDependencyType;
import gov.hhs.fha.nhinc.kmr.kmtypes.KmByParamsType;
import gov.hhs.fha.nhinc.kmr.model.KMVInferenceEngineDependency;
import javax.persistence.EntityManager;

/**
 *
 * @author tmn
 */
public class InferenceBuilder {

   /**
    * getFilters() - Used to build a NATIVE sql.
    *
    */
   public String getFilters(KmByParamsType req, String preFilters) {
      String filters = preFilters;

      if (req.getInference() != null) {
         if ((req.getInference().getTerminologyScheme() != null) && !req.getInference().getTerminologyScheme().isEmpty()) {
            if (!filters.equals("")) filters = filters + " AND ";
            filters = filters + "KMV_InferenceEngineDependency.TerminologyScheme = '" + req.getInference().getTerminologyScheme()+ "'";
         }
         if ((req.getInference().getTerminologyCode() != null) && !req.getInference().getTerminologyCode().isEmpty()) {
            if (!filters.equals("")) filters = filters + " AND ";
            filters = filters + "KMV_InferenceEngineDependency.TerminologyCode = '" + req.getInference().getTerminologyCode() + "'";
         }
      }
      return filters;
   }

   /**
    * @param em
    * @param kmid
    * @return
    */
   public InferenceListType buildElems(KMVInferenceEngineDependency inf) {
      InferenceListType s =  new InferenceListType();

      if (inf != null) {
         try {
            //---- PROCESS DEPENDENCIES -----------------------------------
            //THERE should only be one inference engine per KMV.
            // i.e. a KMV (logic binary) is a DROOLS syntax.

            s.getKMVInferenceEngineDependency().add(inf.toKmTypes());

         } catch (Exception e) {
            System.out.println("---> FAIL FACTS buildElems:" + e.getMessage());
         }
      }
      return s;
   }


   /**
    * Build a model object type of Facts from the kmr object type of Facts
    * including any dependency objects.
    *
    * @param kmrfact
    * @return
    */
   public KMVInferenceEngineDependency buildDatabaseObject(
           Integer kmvid, KMVInferenceEngineDependencyType kmrecIE, EntityManager em) {

      System.out.println("buildDatabaseObject:IE comments=" + kmrecIE.getComments());
      //------------CREATE MODEL---------------
      KMVInferenceEngineDependency newDep = new KMVInferenceEngineDependency(kmrecIE);
      //------------SETTING DEFAULTS------------
      newDep.setKmvId(kmvid);
      //------------PERSIST----------------------
      em.persist(newDep);
      em.flush();
      //------------GET FK---------------------- NA
      //------------BUILD DEPEDENCIES for POPULATION------- NA

      return newDep;
   }

}