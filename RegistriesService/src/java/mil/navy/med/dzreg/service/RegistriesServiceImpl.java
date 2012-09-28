/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mil.navy.med.dzreg.service;

import javax.jws.WebService;
import mil.navy.med.dzreg.Constants;
import mil.navy.med.dzreg.dao.RegistriesManagerDAO;
import mil.navy.med.dzreg.types.AckType;
import mil.navy.med.dzreg.types.AddressType;
import mil.navy.med.dzreg.types.PersonRegistryProfileResponseType;
import mil.navy.med.dzreg.types.PersonRegistryProfileType;
import mil.navy.med.dzreg.types.PersonType;
import mil.navy.med.dzreg.types.RegisterPersonRequestType;
import mil.navy.med.dzreg.types.RegistryProfileType;
import mil.navy.med.dzreg.types.RegistryType;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author kim
 */
@WebService(serviceName = "RegistriesService", portName = "RegistriesServicePort", endpointInterface = "mil.navy.med.dzreg.service.RegistriesServicePortType", targetNamespace = "urn:mil:navy:med:dzreg:service", wsdlLocation = "WEB-INF/wsdl/RegistriesServiceImpl/RegistriesService.wsdl")
public class RegistriesServiceImpl {

   private Log logger = LogFactory.getLog(RegistriesServiceImpl.class);

   public mil.navy.med.dzreg.types.PersonRegistryProfileResponseType getRegistryProfile(mil.navy.med.dzreg.types.PersonRegistryProfileRequestType request) {
      PersonRegistryProfileResponseType response = new PersonRegistryProfileResponseType();
      
      try {
         if (request != null) {
            // get input parameters
            long id = request.getId();
            PersonRegistryProfileType profile = RegistriesManagerDAO.getInstance().getRegistryProfile(id);           
            response.setProfile(profile);
         }
      }
      catch (Exception e) {
         e.printStackTrace();
      }

      return response;
   }

   public mil.navy.med.dzreg.types.AckType register(mil.navy.med.dzreg.types.RegisterPersonRequestType request) {
      AckType response = new AckType();

      try {
         if (request != null &&
             request.getPerson() != null &&
             request.getRegistry() != null && !request.getRegistry().isEmpty()) {
            if (logger.isDebugEnabled())
               dumpRequest(request);
            
            PersonRegistryProfileType profile = new PersonRegistryProfileType();
            profile.setPerson(request.getPerson());
            profile.setDataSource(request.getDataSource());

            RegistryProfileType regprofile = null;
            for (RegistryType regtype: request.getRegistry()) {
               regprofile = new RegistryProfileType();
               regprofile.setRegistryId(regtype.getRegistryId());
               regprofile.setRegistryDesc(regtype.getRegistryDesc());
               profile.getRegistry().add(regprofile);
            }

            if (logger.isDebugEnabled())
               dump(profile);
            
            response = RegistriesManagerDAO.getInstance().register(profile);
         }
         else {
            response.setResponseCode(Constants._APPLICATION_ERROR);
            response.setDetectedIssueText("Missing demographics information for person to be register.");
         }
      }
      catch (Exception e) {
         e.printStackTrace();
      }


      logger.debug("response=" + response.getResponseCode() + "|" + response.getDetectedIssueText());
      
      return response;
   }

   public mil.navy.med.dzreg.types.AckType unregister(mil.navy.med.dzreg.types.RegisterPersonRequestType request) {
      AckType response = new AckType();

      try {
         if (request != null && request.getPerson() != null) {
            if (logger.isDebugEnabled())
               dumpRequest(request);

            PersonRegistryProfileType profile = new PersonRegistryProfileType();
            profile.setPerson(request.getPerson());
            profile.setDataSource(request.getDataSource());

            RegistryProfileType regprofile = null;
            for (RegistryType regtype: request.getRegistry()) {
               regprofile = new RegistryProfileType();
               regprofile.setRegistryId(regtype.getRegistryId());
               regprofile.setRegistryDesc(regtype.getRegistryDesc());
               profile.getRegistry().add(regprofile);
            }
            
            if (logger.isDebugEnabled())
               dump(profile);
            
            response = RegistriesManagerDAO.getInstance().unregister(profile);
         }
         else {
            response.setResponseCode(Constants._APPLICATION_ERROR);
            response.setDetectedIssueText("Missing demographics information for person to be unregister.");
         }
      }
      catch (Exception e) {
         e.printStackTrace();
      }
      
      logger.debug("response=" + response.getResponseCode() + "|" + response.getDetectedIssueText());
      
      return response;
   }

   private void dump(PersonRegistryProfileType obj) {
      StringBuffer str = new StringBuffer();
      str.append("PersonRegistryProfileType[dataSource=" + obj.getDataSource());

      if (obj.getPerson() != null) {
         PersonType person = obj.getPerson();
         str.append("] person=[" + person.getId() + "|" + person.getName() + "|" + person.getDateOfBirth() + "|" +
                  person.getEligibilityIdentifier() + "|" + person.getHomePhone() + "|" + person.getOfficePhone() + "]");
         if (person.getAddress() != null) {
            AddressType address = person.getAddress().getValue();
            str.append(" address=[" + address.getStreetAddress() + "|" + address.getStreetAddress2() + "|" + address.getCity() + "|" +
                     address.getPostalCode() + "|" + address.getState() + "]");
         }
      }

      if (obj.getRegistry() != null && !obj.getRegistry().isEmpty()) {
         for (RegistryProfileType registry: obj.getRegistry()) {
            str.append(" registry=[" + registry.getRegistryId() + "|" + registry.getRegistryDesc() + "]");
         }
      }

      System.out.println(str.toString());
   }

   private void dumpRequest(RegisterPersonRequestType obj) {
      StringBuffer str = new StringBuffer();
      str.append("RegisterPersonRequestType[dataSource=" + obj.getDataSource());

      if (obj.getPerson() != null) {
         PersonType person = obj.getPerson();
         str.append("] person=[" + person.getId() + "|" + person.getName() + "|" + person.getDateOfBirth() + "|" +
                  person.getEligibilityIdentifier() + "|" + person.getHomePhone() + "|" + person.getOfficePhone() + "]");
         if (person.getAddress() != null) {
            AddressType address = person.getAddress().getValue();
            str.append(" address=[" + address.getStreetAddress() + "|" + address.getStreetAddress2() + "|" + address.getCity() + "|" +
                     address.getPostalCode() + "|" + address.getState() + "]");
         }
      }

      if (obj.getRegistry() != null && !obj.getRegistry().isEmpty()) {
         for (RegistryType registry: obj.getRegistry()) {
            str.append(" registry=[" + registry.getRegistryId() + "|" + registry.getRegistryDesc() + "]");
         }
      }

      System.out.println(str.toString());
   }
}
