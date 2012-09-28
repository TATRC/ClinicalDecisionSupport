/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gov.hhs.fha.nhinc.kmr.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author tmn
   					"userName"
						"userId"
						"patientName"
						"patientId"
						"providerName"
						"providerId"
						"location"
						"locationId"
						"token"
 */
public class RequestInfo {

   private String userName;
   private String userId;
   private String patientName;
   private String patientId;
   private String providerName;
   private String providerId;
   private String location;
   private String locationId;
   private String token;

   public RequestInfo() {
      userName = null;
      userId = null;
      patientName = null;
      patientId = null;
      providerName = null;
      providerId = null;
      location = null;
      locationId = null;
      token = null;
      }
   
   public void setUserName(String value) {
      userName = value;
   }
   public void setUserId(String value){
      userId = value;
   }
   public void setPatientName(String value){
      patientName = value;
   }
   public void setPatientId(String value){
      patientId = value;
   }
   public void setProviderName(String value){
      providerName = value;
   }
   public void setProviderId(String value){
      providerId = value;
   }
   public void setLocation(String value){
      location = value;
   }
   public void setLocationId(String value){
      locationId = value;
   }
   public void setToken(String value){
      token = value;
   }

   public String getUserName() {
      return userName;
   }
   public String getUserId(){
      return userId;
   }
   public String getPatientName(){
      return patientName;
   }
   public String getPatientId(){
      return patientId;
   }
   public String getProviderName(){
      return providerName;
   }
   public String getProviderId(){
      return providerId;
   }
   public String getLocation(){
      return location;
   }
   public String getLocationId(){
      return locationId;
   }
   public String getToken(){
      return token;
   }

   public void getRequestAttributes(HttpServletRequest req) {
      userName 	 = (String) req.getAttribute("userName");
      userId   	 = (String) req.getAttribute("userId");
      patientName	 = (String) req.getAttribute("patientName");
      patientId	 = (String) req.getAttribute("patientId");
      providerName = (String) req.getAttribute("providerName");
      providerId	 = (String) req.getAttribute("providerId");
      location 	 = (String) req.getAttribute("location");
      locationId	 = (String) req.getAttribute("locationId");
      token   		 = (String) req.getAttribute("token");
   }

}
