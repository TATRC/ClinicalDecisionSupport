/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.util.Calendar;
import java.util.Date;
import mil.navy.med.dzreg.service.RegistriesServiceImpl;
import mil.navy.med.dzreg.types.AckType;
import mil.navy.med.dzreg.types.AddressType;
import mil.navy.med.dzreg.types.ObjectFactory;
import mil.navy.med.dzreg.types.PersonRegistryProfileType;
import mil.navy.med.dzreg.types.PersonType;
import mil.navy.med.dzreg.types.RegisterPersonRequestType;
import mil.navy.med.dzreg.types.RegistryProfileType;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author kim
 */
public class RegistriesServiceTest {

   RegistriesServiceImpl impl = new RegistriesServiceImpl();
   
   public RegistriesServiceTest() {
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

   /**
    * Test of register method, of class RegistryManagerDAO.
    */
   @Test
   public void testRegisterNewPerson() {
      System.out.println("testRegisterNewPerson()------------------------------");
      ObjectFactory o = new ObjectFactory();

      try {
         RegisterPersonRequestType request = new RegisterPersonRequestType();

         PersonType newperson = new PersonType();
         newperson.setId(new Long(999990));
         Calendar cal = Calendar.getInstance();
         cal.set(2000, Calendar.JANUARY, 1);
         Date dob = cal.getTime();
         newperson.setDateOfBirth(dob);
         newperson.setDataSource("KMR");
         newperson.setFmpssn("222-22-2222");
         newperson.setName("Annie Patient");
         newperson.setHomePhone("111-1111");
         newperson.setOfficePhone("222-2222");
         newperson.setEligibilityIdentifier(new Long(1234567890));

         AddressType address = new AddressType();
         address.setStreetAddress("111 Any Street Ave");
         address.setCity("ANY CITY");
         address.setPostalCode("00000");
         address.setState("CA");
         newperson.setAddress(o.createPersonTypeAddress(address));

         RegistryProfileType regprofile = new RegistryProfileType();
         regprofile.setRegistryId(4);
         regprofile.setRegistryDesc("BREAST HEALTH");

         request.setPerson(newperson);
         request.getRegistry().add(regprofile);

         request.setDataSource("KMR");

         AckType ack = impl.register(request);

         assertEquals("OK", ack.getResponseCode());
         assertEquals("", ack.getDetectedIssueText());
      } catch (Exception ex) {
         System.err.println(ex.getMessage());
      }
   }

   /**
    * Test of register method, of class RegistryManagerDAO.
    */
   @Test
   public void testUnregisterNewPerson() {
      System.out.println("testUnregisterNewPerson()------------------------------");
      ObjectFactory o = new ObjectFactory();

      try {
         RegisterPersonRequestType request = new RegisterPersonRequestType();

         PersonType newperson = new PersonType();
         newperson.setId(new Long(999990));
         Calendar cal = Calendar.getInstance();
         cal.set(2000, Calendar.JANUARY, 1);
         Date dob = cal.getTime();
         newperson.setDateOfBirth(dob);
         newperson.setDataSource("KMR");
         newperson.setFmpssn("222-22-2222");
         newperson.setName("Annie Patient");
         newperson.setHomePhone("111-1111");
         newperson.setOfficePhone("222-2222");
         newperson.setEligibilityIdentifier(new Long(1234567890));

         AddressType address = new AddressType();
         address.setStreetAddress("111 Any Street Ave");
         address.setCity("ANY CITY");
         address.setPostalCode("00000");
         address.setState("CA");
         newperson.setAddress(o.createPersonTypeAddress(address));

         RegistryProfileType regprofile = new RegistryProfileType();
         regprofile.setRegistryId(4);
         regprofile.setRegistryDesc("BREAST HEALTH");

         request.setPerson(newperson);
         request.getRegistry().add(regprofile);

         request.setDataSource("KMR");

         AckType ack = impl.unregister(request);

         assertEquals("OK", ack.getResponseCode());
         assertEquals("", ack.getDetectedIssueText());
      } catch (Exception ex) {
         System.err.println(ex.getMessage());
      }
   }
}
