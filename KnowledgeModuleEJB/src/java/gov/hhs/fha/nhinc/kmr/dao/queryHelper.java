package gov.hhs.fha.nhinc.kmr.dao;

import gov.hhs.fha.nhinc.kmr.kmtypes.ExportResponseListType;
import gov.hhs.fha.nhinc.kmr.kmtypes.FindKmIdsResponseListType;
import gov.hhs.fha.nhinc.kmr.kmtypes.InferenceListType;
import gov.hhs.fha.nhinc.kmr.kmtypes.KMVersionType;
//import gov.hhs.fha.nhinc.kmr.kmtypes.KmByIdRequestListType;
import gov.hhs.fha.nhinc.kmr.kmtypes.KmByIdRequestListType;
import gov.hhs.fha.nhinc.kmr.kmtypes.KmByParamsType;
import gov.hhs.fha.nhinc.kmr.kmtypes.KmFullResponse;
import gov.hhs.fha.nhinc.kmr.kmtypes.KmIdType;
import gov.hhs.fha.nhinc.kmr.kmtypes.KmVersionListType;
import gov.hhs.fha.nhinc.kmr.kmtypes.ResponseListType;
import gov.hhs.fha.nhinc.kmr.model.KMVersion;
import gov.hhs.fha.nhinc.kmr.model.KnowledgeModule;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.apache.log4j.Logger;

public class queryHelper {

   private final static Logger log = Logger.getLogger(queryHelper.class.getName());
   //--------------------------------------------------------------------------
   //  private vars and contants
   //--------------------------------------------------------------------------
   private static final String SELECT_KM_ID = "SELECT distinct KM_Version.KM_ID";
   private static final String SELECT_KMV_ID = "SELECT distinct KM_Version.KMV_ID, KM_Version.KM_ID";

   private static final String FROM_CLAUSE_BYID = " FROM KM_Version KM_Version"
           + " LEFT JOIN KnowledgeModule USING (KM_ID)"
           + " LEFT JOIN KMV_AccessControlList ON (KM_Version.ACL_ID = KMV_AccessControlList.ACL_ID)"
           + " LEFT JOIN ACL_Permission ON (KMV_AccessControlList.ACL_ID = ACL_Permission.ACL_ID)"
           + " LEFT JOIN UserRole ON (ACL_Permission.UR_ID = UserRole.UR_ID) ";
   
   private static final String FROM_CLAUSE = " FROM KM_Version KM_Version"
           + " LEFT JOIN KM_Specialty USING (KM_ID)"
           + " LEFT JOIN KnowledgeModule USING (KM_ID)"
           + " LEFT JOIN KMV_FactDependency USING (KMV_ID)"
           + " LEFT JOIN FactSpecification ON (KMV_FactDependency.FD_ID = FactSpecification.FD_ID)"
           + " LEFT JOIN KMV_PopulationDependency USING (KMV_ID)"
           + " LEFT JOIN PopulationSpecification ON (KMV_PopulationDependency.BP_ID = PopulationSpecification.BP_ID)"
           + " LEFT JOIN KMV_TaskDependency USING (KMV_ID)"
           + " LEFT JOIN TT_Specification ON (KMV_TaskDependency.TT_ID = TT_Specification.TT_ID)"
           + " LEFT JOIN KMV_InferenceEngineDependency  USING (KMV_ID)"
           + " LEFT JOIN KMV_AccessControlList ON (KM_Version.ACL_ID = KMV_AccessControlList.ACL_ID)"
           + " LEFT JOIN ACL_Permission ON (KMV_AccessControlList.ACL_ID = ACL_Permission.ACL_ID)"
           + " LEFT JOIN UserRole ON (ACL_Permission.UR_ID = UserRole.UR_ID)";
   private static final String WHERE_CLAUSE = " WHERE ";
   private static final String ORDER_BY = " ORDER BY KM_Version.KM_ID ASC";


   //--------------------------------------------------------------------------
   //  Constructor
   //--------------------------------------------------------------------------
   public queryHelper() {

   }

   /**
    * createInClause() - given a List of Integer values, create a Native SQL
    *                    IN clause.
    * @param values - a LIst<Integer>
    * @return String containing the IN clause
    */
   public String createIntegerINClause(List<Integer> values) {
      String in = "";

      for (int i = 0; i < values.size(); i++) {        
         // add comma to front ONLY when it's NOT the first id.
         if (in.equals("")) {
            in = in + values.get(i);
         } else {
            in = in + "," + values.get(i);
         }
      }
      in = " IN (" + in + ")";
      System.out.println("---> IN clause=" + in);

      return in;
   }
   
   /**
    * createInClause() - given a List of Integer values, create a Native SQL
    *                    IN clause.
    * @param values - a LIst<String>
    * @return String containing the IN clause
    */
   public String createStringINClause(List<String> values) {
      String in = "";

      for (int i = 0; i < values.size(); i++) {
         // add comma to front ONLY when it's NOT the first id.
         if (in.equals("")) {
            in = in+ values.get(i);
         } else {
            in = in + ",'" + values.get(i)+"'";
         }
      }
      in = in + " IN (" + in + ")";
      System.out.println("---> IN clause=" + in);

      return in;
   }
}
