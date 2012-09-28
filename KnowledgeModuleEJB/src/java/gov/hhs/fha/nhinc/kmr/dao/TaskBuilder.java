
package gov.hhs.fha.nhinc.kmr.dao;

import gov.hhs.fha.nhinc.kmr.kmtypes.KMVTaskDependencyType;
import gov.hhs.fha.nhinc.kmr.kmtypes.KmByParamsType;
import gov.hhs.fha.nhinc.kmr.kmtypes.TTSpecificationType;
import gov.hhs.fha.nhinc.kmr.kmtypes.TaskListType;
import gov.hhs.fha.nhinc.kmr.model.KMVTaskDependency;
import gov.hhs.fha.nhinc.kmr.model.KMVersion;
import gov.hhs.fha.nhinc.kmr.model.TTSpecification;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author tmn
 */
public class TaskBuilder {

   /**
    * getDependencyFilters() - Used to build a NATIVE sql.
    *
    */
   public String getDependencyFilters(KmByParamsType req, String preFilters) {
      String filters = preFilters;
     
      if (req.getTask() != null) {
         if ((req.getTask().getTDType() != null) && !req.getTask().getTDType().isEmpty()) {
            if (!filters.equals("")) { filters = filters + " AND "; }
            filters = filters + "KMV_TaskDependency.TD_Type = '" + req.getTask().getTDType() + "'";
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

      if (req.getTask() != null) {
         if ((req.getTask().getTerminologyScheme() != null) && !req.getTask().getTerminologyScheme().isEmpty()) {
            if (!filters.equals("")) filters = filters + " AND ";
            filters = filters + "TT_Specification.TerminologyScheme = '" + req.getTask().getTerminologyScheme() + "'";
         }
         if ((req.getTask().getTerminologyCode() != null) && !req.getTask().getTerminologyCode().isEmpty()) {
            if (!filters.equals("")) filters = filters + " AND ";
            filters = filters + "TT_Specification.TerminologyCode = '" + req.getTask().getTerminologyCode() + "'";
         }
      }
      return filters;
   }

   /**
    * buildElems - Build by Collection
    * @param facts
    * @return
    */
   public TaskListType buildElems(Collection<KMVTaskDependency> tasks) {

      gov.hhs.fha.nhinc.kmr.kmtypes.TaskListType s = new TaskListType();

      Iterator<KMVTaskDependency> taskDepIter = tasks.iterator();
      Iterator<TTSpecification> specIter;

      if (tasks != null) {
         try {
            //---- PROCESS DEPENDENCIES -----------------------------------
            // build a kmtypes Collection of each found Dependencies

            while (taskDepIter.hasNext()) {
               //database
               KMVTaskDependency taskDep = (KMVTaskDependency) taskDepIter.next();
               //output xml
               KMVTaskDependencyType osd = new KMVTaskDependencyType();

               // setting Dependency level params
               osd.setTDType(taskDep.getTDType());

               // Get and set all Specification level params
               specIter = taskDep.getTTSpecificationCollection().iterator();
               while (specIter.hasNext()) {
                  osd.getTTSpecification().add(specIter.next().toKmTypes());
               }

               // Add each built Depedencies to main List
               s.getKMVTaskDependency().add(osd);
            }
         } catch (Exception e) {
            System.out.println("---> FAIL TASK buildElems:" + e.getMessage());
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
   public KMVTaskDependency buildDatabaseObject(
           Integer kmvid, KMVTaskDependencyType kmrecTD, EntityManager em) {

      System.out.println("buildDatabaseObject:TDType=" + kmrecTD.getTDType());
      //------------CREATE MODEL---------------
      KMVTaskDependency newDep = new KMVTaskDependency(kmrecTD);
      //------------SETTING DEFAULTS------------
      KMVersion k2 = new KMVersion();
      k2.setKmvId(kmvid);
      newDep.setKmvId(k2);
      //------------PERSIST----------------------
      em.persist(newDep);
      em.flush();
      //------------GET FK----------------------
      int newTT_ID = newDep.getTtId();
      System.out.println("new TT_ID=" + newTT_ID);
      //------------BUILD DEPEDENCIES for TASKS-------------
      //=================================
      //SETTING KMV TASK SPECIFICATION
      //=================================
      //------------INSTANTIATE---------------
      TTSpecificationType kmrecTS = null;
      int totalspecs = kmrecTD.getTTSpecification().size();
      List<TTSpecification> taskspecs = new ArrayList<TTSpecification>(totalspecs);

      for (int s = 0; s < totalspecs; s++) {
         kmrecTS = kmrecTD.getTTSpecification().get(s);
         System.out.println("factspec=" + kmrecTS.getTerminologyScheme());
         //------------CREATE MODEL---------------
         TTSpecification newSpec = new TTSpecification(kmrecTS);
         //------------SETTING DEFAULTS-------------
         newSpec.getTTSpecificationPK().setTtId(newTT_ID);
         //------------PERSIST----------------------- NA
         //------------GET FK------------------------ NA
         //------------BUILD DEPEDENCIES------------- NA
         //------------ADD TO MODEL LIST-------------
         taskspecs.add(newSpec);
         newDep.setTTSpecificationCollection(taskspecs);
      }//end-factspecs-loop

      return newDep;
   }

}