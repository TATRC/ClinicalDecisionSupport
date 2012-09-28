/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gov.hhs.fha.nhinc.kmr.dss;

import gov.hhs.fha.nhinc.adapter.fact.CodeLabelPair;
import gov.hhs.fha.nhinc.kmr.patientcohort.PatientCohort;
import gov.hhs.fha.nhinc.kmr.VirtualMedicalRecord;
import gov.hhs.fha.nhinc.kmr.InclusionTable;
import gov.hhs.fha.nhinc.kmr.KnowledgeSession;
import gov.hhs.fha.nhinc.kmr.properties.PropertyAccessor;
//import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;
import java.util.List;

/**
 *
 * @author Steven Clark
 */
public class DSSSession {

   private PatientCohort cohort = null;
   private KnowledgeSession knowledgeSession = null;
   private VirtualMedicalRecord vmr = null;
   private List<InclusionTable> inclusionTables = null;
   private List<String> factHandles = null;

   public DSSSession(KnowledgeSession knowledgeSession) {
      if (knowledgeSession != null) {
         this.knowledgeSession = knowledgeSession;
      }

      try {
         Set<String> setReferenceNames = PropertyAccessor.getPropertyNames("reference");
         if (setReferenceNames != null && setReferenceNames.size() > 0) {
            // Each property name is a refence table name
            inclusionTables = new ArrayList<InclusionTable>();
            Iterator iterTables = setReferenceNames.iterator();
            while (iterTables.hasNext()) {
               String sReferenceName = (String) iterTables.next();
               if (!sReferenceName.equalsIgnoreCase("CacheRefreshDuration")) {
                  String sTableName = PropertyAccessor.getProperty("reference", sReferenceName);
                  InclusionTable incTable = new InclusionTable();
                  incTable.setDomain(PropertyAccessor.getProperty(sTableName, "domain"));
                  incTable.setInclusionType(PropertyAccessor.getProperty(sTableName, "type"));
                  Set<String> setPropNames = PropertyAccessor.getPropertyNames(sTableName);
                  // Each property name other than domain and Type is a refence table name
                  Iterator iter = setPropNames.iterator();
                  while (iter.hasNext()) {
                     String sPropName = (String) iter.next();
                     if (!sPropName.equalsIgnoreCase("CacheRefreshDuration") &&
                             !sPropName.equalsIgnoreCase("domain") &&
                             !sPropName.equalsIgnoreCase("type")) {
                        CodeLabelPair code = new CodeLabelPair();
                        code.setCode(sPropName);
                        code.setLabel(PropertyAccessor.getProperty(sTableName, sPropName));
                        incTable.getCodes().add(code);
                     }
                  }
                  inclusionTables.add(incTable);
               }
            }
         }
      } catch (Exception e) {
      }
   }

   /**
    * @return KnowledgeSession knowledgeSession
    */
   public KnowledgeSession getKnowledgeSession() {
      return knowledgeSession;
   }

   /**
    * @return PatientCohort object
    */
   public PatientCohort getPatientCohort() {
      return cohort;
   }

   /**
    * @param PatientCohort object
    */
   public void setPatientCohort(PatientCohort cohort) {
      if (cohort != null) {
         this.cohort = cohort;
      }
   }

   /**
    * @return VirtualMedicalRecord
    */
   public VirtualMedicalRecord getVirtualMedicalRecord() {
      return vmr;
   }

   /**
    * @param VirtualMedicalRecord
    */
   public void setVirtualMedicalRecord(VirtualMedicalRecord vmr) {
      if (vmr != null) {
         this.vmr = vmr;
      }
   }

   /**
    * @return ArrayList<InclusionTable> of session inclusion tables
    */
   public List<InclusionTable> getInclusionTables() {
      if (inclusionTables == null) {
         inclusionTables = new ArrayList<InclusionTable>();
      }
      return inclusionTables;
   }

   /**
    * @return ArrayList<String> of session fact handles
    */
   public List<String> getFactHandles() {
      if (factHandles == null) {
         factHandles = new ArrayList<String>();
      }
      return factHandles;
   }

   @Override
   public String toString() {
      return "DSSSession[knowledgeSession=" + knowledgeSession + "]";
   }
}
