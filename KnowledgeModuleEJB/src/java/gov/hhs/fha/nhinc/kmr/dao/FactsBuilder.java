package gov.hhs.fha.nhinc.kmr.dao;

import gov.hhs.fha.nhinc.kmr.kmtypes.FactSpecificationListType;
import gov.hhs.fha.nhinc.kmr.kmtypes.FactSpecificationType;
import gov.hhs.fha.nhinc.kmr.kmtypes.KMVFactDependencyType;
import gov.hhs.fha.nhinc.kmr.kmtypes.KmByParamsType;
import gov.hhs.fha.nhinc.kmr.model.FactSpecification;
import gov.hhs.fha.nhinc.kmr.model.KMVFactDependency;
import gov.hhs.fha.nhinc.kmr.model.KMVersion;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author tmn
 */
public class FactsBuilder {

   public FactsBuilder() {
   }

   /**
    * getDependencyFilters() - Used to build a NATIVE sql syntax for table element(s).
    * @param req
    * @param preFilters
    * @return
    */
   public String getDependencyFilters(KmByParamsType req, String preFilters) {
      String filters = preFilters;

      if (req.getFact() != null) {
         if ( (req.getFact().getType() != null) && !req.getFact().getType().isEmpty() ) {
            if (!filters.equals("")) {
               filters = filters + " AND ";
            }
            filters = filters + "KMV_FactDependency.Type = '" + req.getFact().getType() + "'";
         }
      }
      return filters;
   }

   /**
    * getSpecificationFilters() - Used to build a NATIVE sql syntax for table element(s).
    * @param req
    * @param preFilters
    * @return
    */
   public String getSpecificationFilters(KmByParamsType req, String preFilters) {
      String filters = preFilters;

      if (req.getFact() != null) {
         if ( (req.getFact().getTerminologyScheme() != null) && !req.getFact().getTerminologyScheme().isEmpty()) {
            if (!filters.equals("")) {
               filters = filters + " AND ";
            }
            filters = filters + "FactSpecification.TerminologyScheme = '" + req.getFact().getTerminologyScheme() + "'";
         }
         if ( (req.getFact().getTerminologyCode() != null) && !req.getFact().getTerminologyCode().isEmpty()) {
            if (!filters.equals("")) {
               filters = filters + " AND ";
            }
            filters = filters + "FactSpecification.TerminologyCode = '" + req.getFact().getTerminologyCode() + "'";
         }
      }
      return filters;
   }

   /**
    * buildElems - Build by Collection
    * @param facts
    * @return
    */
   public FactSpecificationListType buildElems(Collection<KMVFactDependency> facts) {

      FactSpecificationListType s = new FactSpecificationListType();

      Iterator<KMVFactDependency> factDepIter = facts.iterator();
      Iterator<FactSpecification> specIter;

      if (facts != null) {
         try {
            //---- PROCESS DEPENDENCIES -----------------------------------
            // build a kmtypes Collection of each found Dependencies
            while (factDepIter.hasNext()) {
               //database
               KMVFactDependency factDep = (KMVFactDependency) factDepIter.next();
               //output xml
               KMVFactDependencyType osd = new KMVFactDependencyType();

               // setting FactDep level params
               osd.setDescription(factDep.getDescription());
               osd.setType(factDep.getType());

               // Get and add all FactSpecification(s) in KMTYPEs for each FactDep
               specIter = factDep.getFactSpecificationCollection().iterator();
               while (specIter.hasNext()) {
                  osd.getFactSpecification().add(specIter.next().toKmTypes());
               }

               // Add each built FactDep to FactSpecificationList
               s.getKMVFactDependency().add(osd);
            }
         } catch (Exception e) {
            System.out.println("---> FAIL FACTS buildElems:" + e.getMessage());
         }
      }//end-if
      return s;
   }

   /**
    * Build a model object type of Facts from the kmr object type of Facts
    * including any dependency objects.
    *
    * @param kmrfact
    * @return
    */
   public KMVFactDependency buildDatabaseObject(
           Integer kmvid, KMVFactDependencyType kmrecFD, EntityManager em) {

      System.out.println("buildDatabaseObject:factType=" + kmrecFD.getType());
      //------------CREATE MODEL---------------
      KMVFactDependency newFact = new KMVFactDependency(kmrecFD);
      //------------SETTING DEFAULTS------------
      KMVersion k2 = new KMVersion();
      k2.setKmvId(kmvid);
      newFact.setKmvId(k2);
      //------------PERSIST----------------------
      em.persist(newFact);
      em.flush();
      //------------GET FK----------------------
      int newFD_ID = newFact.getFdId();
      System.out.println("new FD_ID=" + newFD_ID);
      //------------BUILD DEPEDENCIES for FACT-------------
      //=================================
      //SETTING KM_FACTSPECs
      //=================================
      //------------INSTANTIATE---------------
      FactSpecificationType kmrecFS = null;
      int totalFactspecs = kmrecFD.getFactSpecification().size();
      List<FactSpecification> factSpecs = new ArrayList<FactSpecification>(totalFactspecs);

      for (int fs = 0; fs < totalFactspecs; fs++) {
         kmrecFS = kmrecFD.getFactSpecification().get(fs);
         System.out.println("factspec=" + kmrecFS.getTerminologyScheme());
         //------------CREATE MODEL---------------
         FactSpecification newFactSpec = new FactSpecification(kmrecFS);
         //------------SETTING DEFAULTS-------------
         newFactSpec.getFactSpecificationPK().setFdId(newFD_ID);
         //------------PERSIST----------------------- NA
         //------------GET FK------------------------ NA
         //------------BUILD DEPEDENCIES------------- NA
         //------------ADD TO MODEL LIST-------------
         factSpecs.add(newFactSpec);
         newFact.setFactSpecificationCollection(factSpecs);
      }//end-factspecs-loop

      return newFact;
   }
}
