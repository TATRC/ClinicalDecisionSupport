
package gov.hhs.fha.nhinc.kmr.dao;

import gov.hhs.fha.nhinc.kmr.kmtypes.ACLPermissionType;
import gov.hhs.fha.nhinc.kmr.kmtypes.KMVAccessControlListType;
import gov.hhs.fha.nhinc.kmr.kmtypes.KmByParamsType;
import gov.hhs.fha.nhinc.kmr.model.ACLPermission;
import gov.hhs.fha.nhinc.kmr.model.ACLPermissionPK;
import gov.hhs.fha.nhinc.kmr.model.KMVAccessControlList;
import javax.persistence.EntityManager;

/**
 *
 * @author tmn
 */
public class ACLBuilder {

   public ACLBuilder() {
   }   

   /**
    * getFilters() - Used to build a NATIVE sql syntax for table element(s).
    * @param urName
    * @param preFilters
    * @return String
    */
   public String getFilters(String urName, String preFilters) {
      String filters = preFilters;

         if (urName != null) {
            if (!filters.equals("")) filters = filters + " AND ";
            filters = filters + "UserRole.UR_Name = '" + urName+ "'";
         }
      return filters;
   }

   /**
    * 
    * @param req
    * @param preFilters
    * @return String
    */
   public String getFiltersJPA(KmByParamsType req, String preFilters) {
      String filters = preFilters;

      if (req.getACL() != null) {
         if (req.getACL().getURName() != null) {
            if (!filters.equals("")) filters = filters + " AND ";
            filters = filters + "UserRole.uRName = '" + req.getACL().getURName()+ "'";
         }
      }
      return filters;
   }

   /**
    * Build a model object type of Facts from the kmr object type of Facts
    * including any dependency objects.
    *
    * @param kmrfact
    * @return
    */
   public ACLPermission buildDatabaseObject(
           Integer urId, Integer aclId, String urPermission, EntityManager em) {

      System.out.println("buildDatabaseObject:");
      //------------CREATE MODEL---------------
      ACLPermission newACL = new ACLPermission();
      //------------SETTING DEFAULTS------------ 
      ACLPermissionPK k2 = new ACLPermissionPK();
      k2.setAclId(aclId);
      k2.setUrId(urId);
      newACL.setACLPermissionPK(k2);
      newACL.setURPermission(urPermission);
      //------------PERSIST----------------------
      em.persist(newACL);
      em.flush();
      //------------GET FK---------------------- NA
      //------------BUILD DEPEDENCIES------------- NA

      return newACL;
   }
    
}