package gov.hhs.fha.nhinc.kmr.dao;

import gov.hhs.fha.nhinc.kmr.kmtypes.KMVPopulationDependencyType;
import gov.hhs.fha.nhinc.kmr.kmtypes.KmByParamsType;
import gov.hhs.fha.nhinc.kmr.kmtypes.PopulationDependencyListType;
import gov.hhs.fha.nhinc.kmr.kmtypes.PopulationSpecificationType;
import gov.hhs.fha.nhinc.kmr.model.KMVPopulationDependency;
import gov.hhs.fha.nhinc.kmr.model.KMVersion;
import gov.hhs.fha.nhinc.kmr.model.KMVersion;
import gov.hhs.fha.nhinc.kmr.model.KMVersion;
import gov.hhs.fha.nhinc.kmr.model.PopulationSpecification;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author tmn
 */
public class PopulationBuilder {

   public PopulationBuilder() {
   }

   /**
    *
    * @param req
    * @param preFilters
    * @return
    */
   public String getDependencyFilters(KmByParamsType req, String preFilters) {
      String filters = preFilters;

      if (req.getPopulationDependency() != null) {
         if (req.getPopulationDependency().getPDName() != null) {
            if (!filters.equals("")) {
               filters = filters + " AND ";
            }
            filters = filters + "KMV_PopulationDependency.PD_Name = '" + req.getPopulationDependency().getPDName() + "'";
         }
         if (req.getPopulationDependency().getPDScope() != null) {
            if (!filters.equals("")) {
               filters = filters + " AND ";
            }
            filters = filters + "KMV_PopulationDependency.PD_Scope = '" + req.getPopulationDependency().getPDScope() + "'";
         }
         if (req.getPopulationDependency().getPDStatus() != null) {
            if (!filters.equals("")) {
               filters = filters + " AND ";
            }
            filters = filters + "KMV_PopulationDependency.PD_Status = '" + req.getPopulationDependency().getPDStatus() + "'";
         }
      }
      return filters;
   }

   /**
    * getSpecificationFilters() 
    * @param req
    * @param preFilters
    * @return
    */
   public String getSpecificationFilters(KmByParamsType req, String preFilters) {
      String filters = preFilters;

      if (req.getPopulationDependency() != null) {
         if (req.getPopulationDependency().getTerminologyScheme() != null) {
            if (!filters.equals("")) {
               filters = filters + " AND ";
            }
            filters = filters + "PopulationSpecification.TerminologyScheme = '" + req.getPopulationDependency().getTerminologyScheme() + "'";
         }
         if (req.getPopulationDependency().getTerminologyCode() != null) {
            if (!filters.equals("")) {
               filters = filters + " AND ";
            }
            filters = filters + "PopulationSpecification.TerminologyCode = '" + req.getPopulationDependency().getTerminologyCode() + "'";
         }
         if (req.getPopulationDependency().getTerminologyValue() != null) {
            if (!filters.equals("")) {
               filters = filters + " AND ";
            }
            filters = filters + "PopulationSpecification.TerminologyValue = '" + req.getPopulationDependency().getTerminologyValue() + "'";
         }
         if (req.getPopulationDependency().getType() != null) {
            if (!filters.equals("")) {
               filters = filters + " AND ";
            }
            filters = filters + "PopulationSpecification.Type = '" + req.getPopulationDependency().getType() + "'";
         }
      }
      return filters;
   }

   /**
    * buildElems - Used to build a NATIVE sql syntax for table element(s).
    * @param facts
    * @return
    */
   public PopulationDependencyListType buildElems(Collection<KMVPopulationDependency> pops) {

      gov.hhs.fha.nhinc.kmr.kmtypes.PopulationDependencyListType s = new PopulationDependencyListType();

      Iterator<KMVPopulationDependency> popDepIter = pops.iterator();
      Iterator<PopulationSpecification> specIter;

      if (pops != null) {
         try {
            //---- PROCESS DEPENDENCIES -----------------------------------
            // build a kmtypes Collection of each found Dependencies

            while (popDepIter.hasNext()) {
               //database
               KMVPopulationDependency popDep = (KMVPopulationDependency) popDepIter.next();
               //output xml
               KMVPopulationDependencyType osd = new KMVPopulationDependencyType();

               // setting Dependency level params
               osd.setPDDescription(popDep.getPDDescription());
               osd.setPDName(popDep.getPDName());
               osd.setPDScope(popDep.getPDScope());
               osd.setPDStatus(popDep.getPDStatus());

               // Get and set all Specification level params
               specIter = popDep.getPopulationSpecificationCollection().iterator();
               while (specIter.hasNext()) {
                  osd.getPopulationSpecification().add(specIter.next().toKmTypes());
               }

               // Add each built Depedencies to main List
               s.getKMVPopulationDependency().add(osd);
            }
         } catch (Exception e) {
            System.out.println("---> FAIL POPULATION buildElems:" + e.getMessage());
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
   public KMVPopulationDependency buildDatabaseObject(
           Integer kmvid, KMVPopulationDependencyType kmrecPD, EntityManager em) {

      System.out.println("buildDatabaseObject:PDName=" + kmrecPD.getPDName());
      //------------CREATE MODEL---------------
      KMVPopulationDependency newDep = new KMVPopulationDependency(kmrecPD);
      //------------SETTING DEFAULTS------------
      KMVersion k2 = new KMVersion();
      k2.setKmvId(kmvid);
      newDep.setKmvId(k2);
      //------------PERSIST----------------------
      em.persist(newDep);
      em.flush();
      //------------GET FK----------------------
      int newPD_ID = newDep.getBpId();
      System.out.println("new FD_ID=" + newPD_ID);
      //------------BUILD DEPEDENCIES for POPULATION-------------
      //=================================
      //SETTING KMV POPULATIONSPECS
      //=================================
      //------------INSTANTIATE---------------
      PopulationSpecificationType kmrecPS = null;
      int totalspecs = kmrecPD.getPopulationSpecification().size();
      List<PopulationSpecification> popSpecs = new ArrayList<PopulationSpecification>(totalspecs);

      for (int s = 0; s < totalspecs; s++) {
         kmrecPS = kmrecPD.getPopulationSpecification().get(s);
         System.out.println("factspec=" + kmrecPS.getTerminologyScheme());
         //------------CREATE MODEL---------------
         PopulationSpecification newSpec = new PopulationSpecification(kmrecPS);
         //------------SETTING DEFAULTS-------------
         newSpec.getPopulationSpecificationPK().setBpId(newPD_ID);
         //------------PERSIST----------------------- NA
         //------------GET FK------------------------ NA
         //------------BUILD DEPEDENCIES------------- NA
         //------------ADD TO MODEL LIST-------------
         popSpecs.add(newSpec);
         newDep.setPopulationSpecificationCollection(popSpecs);
      }//end-factspecs-loop

      return newDep;
   }
   
}
