
package gov.hhs.fha.nhinc.kmr.dao;

import gov.hhs.fha.nhinc.kmr.kmtypes.KMSpecialtyType;
import gov.hhs.fha.nhinc.kmr.kmtypes.KmByParamsType;
import gov.hhs.fha.nhinc.kmr.kmtypes.SpecialtyListType;
import gov.hhs.fha.nhinc.kmr.model.KMSpecialty;
import gov.hhs.fha.nhinc.kmr.model.KMSpecialtyPK;
import java.util.List;
import javax.persistence.Query;
import javax.persistence.EntityManager;

/**
 *
 * @author tmn
 */
public class SpecialtyBuilder {

   /**
    * getFilters() - Used to build a NATIVE sql.
    *
    */
   public String getFilters(KmByParamsType req, String preFilters) {
      String filters = preFilters;

      if (req.getSpecialty() != null) {
         if ( (req.getSpecialty().getTerminologyScheme() != null) && !req.getSpecialty().getTerminologyScheme().isEmpty()) {
            if (!filters.equals("")) filters = filters + " AND ";
            filters = filters + "KM_Specialty.TerminologyScheme = '" + req.getSpecialty().getTerminologyScheme()+ "'";
         }
         if ( (req.getSpecialty().getTerminologyCode() != null) && !req.getSpecialty().getTerminologyCode().isEmpty()) {
            if (!filters.equals("")) filters = filters + " AND ";
            filters = filters + "KM_Specialty.TerminologyCode = '" + req.getSpecialty().getTerminologyCode() + "'";
         }
      }
      return filters;
   }
   public String getFiltersJPA(KmByParamsType req, String preFilters) {
      String filters = preFilters;

      if (req.getSpecialty() != null) {
         if ((req.getSpecialty().getTerminologyScheme() != null) && !req.getSpecialty().getTerminologyScheme().isEmpty()) {
            if (!filters.equals("")) filters = filters + " AND ";
            filters = filters + "KMSpecialty.kMSpecialtyPK.terminologyScheme = '" + req.getSpecialty().getTerminologyScheme()+ "'";
         }
         if ((req.getSpecialty().getTerminologyCode() != null) && !req.getSpecialty().getTerminologyCode().isEmpty()) {
            if (!filters.equals("")) filters = filters + " AND ";
            filters = filters + "KMSpecialty.kMSpecialtyPK.terminologyCode = '" + req.getSpecialty().getTerminologyCode() + "'";
         }
      }
      return filters;
   }

   /**
    * buildSpecialtyList() - 
    * @param em
    * @param kmid
    * @return
    */
   public SpecialtyListType buildElems(EntityManager em, Integer kmid) {
      SpecialtyListType spList = new SpecialtyListType();

      // Find matching record(s) from KM_Specialty table
      Query query = em.createNamedQuery("KMSpecialty.findByKmId");
      query.setParameter("kmId", kmid);
      List<KMSpecialty> foundSpecialtyList = (List<KMSpecialty>) query.getResultList();

      System.out.println("Found SpecialtyList size: " + foundSpecialtyList.size());

      for (int i=0; i < foundSpecialtyList.size(); i++) {
        spList.getKMSpecialty().add(foundSpecialtyList.get(i).toKmTypes());
      }
      return spList;
   }

   /**
    * Build a model object type of Facts from the kmr object type of Facts
    * including any dependency objects.
    *
    * @param kmrfact
    * @return
    */
   public KMSpecialty buildDatabaseObject(
           Integer kmid, KMSpecialtyType kmrecSP, EntityManager em) {

      System.out.println("buildDatabaseObject:Specialty code=" + kmrecSP.getTerminologyCode());
      //------------CREATE MODEL---------------
      KMSpecialty newDep = new KMSpecialty(kmrecSP);
      //------------SETTING DEFAULTS------------
      newDep.getKMSpecialtyPK().setKmId(kmid);
      //------------PERSIST----------------------
      em.persist(newDep);
      em.flush();
      //------------GET FK---------------------- NA
      //------------BUILD DEPEDENCIES for TASKS------------- NA

      return newDep;
   }
}