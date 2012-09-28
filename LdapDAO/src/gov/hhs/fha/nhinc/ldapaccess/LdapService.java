/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gov.hhs.fha.nhinc.ldapaccess;

import gov.hhs.fha.nhinc.kmr.properties.PropertyAccessor;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;

/**
 * LDAP Service object creates DAO instances for user.
 *
 * @author cmatser
 */
public class LdapService {

    public static BusinessUnitDAO getBusinessUnitDAO() {
        Resource resource = new FileSystemResource(PropertyAccessor.getPropertyFileLocation() + "kmrBusinessUnits.xml");
        BeanFactory factory = new XmlBeanFactory(resource);
        return (BusinessUnitDAO) factory.getBean("ldapBusinessUnit");
    }

    public static ContactDAO getContactDAO() {
        Resource resource = new FileSystemResource(PropertyAccessor.getPropertyFileLocation() + "kmrContacts.xml");
        BeanFactory factory = new XmlBeanFactory(resource);
        return (ContactDAO) factory.getBean("ldapContact");
    }

    public static EquipmentDAO getEquipmentDAO() {
        Resource resource = new FileSystemResource(PropertyAccessor.getPropertyFileLocation() + "kmrEquipment.xml");
        BeanFactory factory = new XmlBeanFactory(resource);
        return (EquipmentDAO) factory.getBean("ldapEquipment");
    }

    public static LocationDAO getLocationDAO() {
        Resource resource = new FileSystemResource(PropertyAccessor.getPropertyFileLocation() + "kmrLocations.xml");
        BeanFactory factory = new XmlBeanFactory(resource);
        return (LocationDAO) factory.getBean("ldapLocation");
    }

    public static RoleDAO getRoleDAO() {
        Resource resource = new FileSystemResource(PropertyAccessor.getPropertyFileLocation() + "kmrRoles.xml");
        BeanFactory factory = new XmlBeanFactory(resource);
        return (RoleDAO) factory.getBean("ldapRole");
    }

}
