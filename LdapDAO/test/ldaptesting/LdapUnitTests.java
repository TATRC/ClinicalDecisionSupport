/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ldaptesting;

import com.thoughtworks.xstream.XStream;
import gov.hhs.fha.nhinc.ldapaccess.BusinessUnitDAO;
import gov.hhs.fha.nhinc.ldapaccess.BusinessUnitDTO;
import gov.hhs.fha.nhinc.ldapaccess.ContactAcctDTO;
import gov.hhs.fha.nhinc.ldapaccess.ContactDAO;
import gov.hhs.fha.nhinc.ldapaccess.ContactDTO;
import gov.hhs.fha.nhinc.ldapaccess.EquipmentDAO;
import gov.hhs.fha.nhinc.ldapaccess.EquipmentDTO;
import gov.hhs.fha.nhinc.ldapaccess.LdapService;
import gov.hhs.fha.nhinc.ldapaccess.LocationDAO;
import gov.hhs.fha.nhinc.ldapaccess.LocationDTO;
import gov.hhs.fha.nhinc.ldapaccess.RoleDAO;
import gov.hhs.fha.nhinc.ldapaccess.RoleDTO;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.dao.DataAccessException;

/**
 *
 * @author cmatser
 */
public class LdapUnitTests {

    public LdapUnitTests() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testContacts() {
        try {
            ContactDAO contactDAO = LdapService.getContactDAO();
            ContactDTO contactDTO = new ContactDTO();
            contactDTO.setCommonName("sushma.Narisetty");
            contactDTO.setGivenName("sushma");
            contactDTO.setSurname("Narisetty");
            contactDTO.setInitials("N");
            contactDTO.setHomePhone("858-555-0000");
            contactDTO.setMobile("858-555-0000");
            contactDTO.setTelephoneNumber("858-555-0000");
            contactDTO.setPostalAddress("1650 ");
            contactDTO.setStreet("Hotel Circle");
            contactDTO.setCity("San Diego");
            contactDTO.setState("CA");
            contactDTO.setPostalCode("92125");
            contactDTO.setMail("sushma@geneva.com");
            contactDTO.setUserPassword(("test").getBytes());
            contactDTO.setDescription("description");
            
            contactDAO.insertContact(contactDTO);
            List<ContactDTO> contactList = contactDAO.findAllContacts();
            for( int i = 0 ; i < contactList.size(); i++){
                System.out.println("Contact Name: " + contactList.get(i).getCommonName());
            }

            System.out.println("Deleting ContactDTO: " + contactDTO.getCommonName());
            contactDAO.deleteContact(contactDTO);

            List<ContactDTO> detailsList = contactDAO.findContact("cn=smith.baby");
            for( int i = 0 ; i < detailsList.size(); i++){
                System.out.println("Details Name: " + detailsList.get(i).getCommonName());
                XStream xstream = new XStream();
                System.out.println("Details: " + xstream.toXML(detailsList.get(i)));

                List<ContactAcctDTO> acctList = contactDAO.findAllContactAccts(detailsList.get(i).getCommonName());
                for( int j = 0 ; j < acctList.size(); j++){
                    System.out.println("Acct Name: " + acctList.get(j).getCommonName());
                }

                List<ContactAcctDTO> acctDetailsList = contactDAO.findContactAcct(detailsList.get(i).getCommonName(), ContactAcctDTO.CN_MAIL);
                for( int j = 0 ; j < acctDetailsList.size(); j++){
                    System.out.println("Acct Details Name: " + acctDetailsList.get(j).getCommonName());
                    System.out.println("Acct Details: " + xstream.toXML(acctDetailsList.get(j)));
                }
            }

            contactList = contactDAO.findAllContacts();
            for( int i = 0 ; i < contactList.size(); i++){
                System.out.println("Contact Name: " + contactList.get(i).getCommonName());
            }

        } catch (DataAccessException e) {
            System.out.println("Error occured " + e.getCause());
        }
    }

    //
    @Test
    public void testBusinessUnits() {
        try {
            BusinessUnitDAO buDAO = LdapService.getBusinessUnitDAO();
            List<BusinessUnitDTO> buList = buDAO.findAllBusinessUnits();
            for( int i = 0 ; i < buList.size(); i++){
                System.out.println("BusinessUnit Name: " + buList.get(i).getOrganization());
            }

            buList = buDAO.findBusinessUnit("o=medical center");
            for( int i = 0 ; i < buList.size(); i++){
                System.out.println("BusinessUnit Details: " + buList.get(i).getOrganization());
                XStream xstream = new XStream();
                System.out.println("BusinessUnit Details: " + xstream.toXML(buList.get(i)));
            }

        } catch (DataAccessException e) {
            System.out.println("Error occured " + e.getCause());
        }
    }

    @Test
    public void testEquipment() {
        try {
            EquipmentDAO equipDAO = LdapService.getEquipmentDAO();
            List<EquipmentDTO> equipList = equipDAO.findAllEquipment();
            for( int i = 0 ; i < equipList.size(); i++){
                System.out.println("Equipment Name: " + equipList.get(i).getCommonName());
            }

            equipList = equipDAO.findEquipment("cn=abgmonitor");
            for( int i = 0 ; i < equipList.size(); i++){
                System.out.println("Equipment Details: " + equipList.get(i).getCommonName());
                XStream xstream = new XStream();
                System.out.println("Equipment Details: " + xstream.toXML(equipList.get(i)));
            }

        } catch (DataAccessException e) {
            System.out.println("Error occured " + e.getCause());
        }

    }

    @Test
    public void testLocations() {
        try {
            LocationDAO locDAO = LdapService.getLocationDAO();
            List<LocationDTO> locList = locDAO.findAllLocations();
            for( int i = 0 ; i < locList.size(); i++){
                System.out.println("Location Name: " + locList.get(i).getOrganizationalUnit());
            }

            locList = locDAO.findLocation("ou=campus");
            for( int i = 0 ; i < locList.size(); i++){
                System.out.println("Location Details: " + locList.get(i).getOrganizationalUnit());
                XStream xstream = new XStream();
                System.out.println("Location Details: " + xstream.toXML(locList.get(i)));
            }

        } catch (DataAccessException e) {
            System.out.println("Error occured " + e.getCause());
        }

    }

    @Test
    public void testRoles() {
        try {
            RoleDAO roleDAO = LdapService.getRoleDAO();
            List<RoleDTO> roleList = roleDAO.findAllRoles();
            for( int i = 0 ; i < roleList.size(); i++){
                System.out.println("Role Name: " + roleList.get(i).getCommonName());
            }

            //roleList = roleDAO.findRole("cn=nicu.charge.nurse");
            roleList = roleDAO.findRole("");
            for( int i = 0 ; i < roleList.size(); i++){
                System.out.println("Role Details: " + ((RoleDTO)roleList.get(i)).getCommonName());
                XStream xstream = new XStream();
                System.out.println("Role Details: " + xstream.toXML(roleList.get(i)));
            }

        } catch (DataAccessException e) {
            System.out.println("Error occured " + e.getCause());
        }
    }

}