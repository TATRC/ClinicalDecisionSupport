/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mil.navy.med.dzreg.dao;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import mil.navy.med.dzreg.RegistriesManager;
import mil.navy.med.dzreg.types.AckType;
import mil.navy.med.dzreg.types.AddressType;
import mil.navy.med.dzreg.types.ObjectFactory;
import mil.navy.med.dzreg.types.PersonRegistryProfileType;
import mil.navy.med.dzreg.types.PersonType;
import mil.navy.med.dzreg.types.RegistryProfileType;
import mil.navy.med.dzreg.types.RegistryType;
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
public class RegistriesManagerDAOTest {

  private RegistriesManager instance = null;
  private long[] ids = {647922, 648995, 999995};
  private Long newPerson = new Long(999990);
  
  private String[] names = {"STONE,HAROLD", "SHETLER,HAROLD"};
  private String[] registries = {"DIABETES", "ASTHMAS", "NEWBORN", "BREAST HEALTH", "IDIOPATHIC THROMBOCYTOSIS"};

  private PersonRegistryProfileType invalidPersonProfile;
  
  public RegistriesManagerDAOTest() {
  }

  @BeforeClass
  public static void setUpClass() throws Exception {
  }

  @AfterClass
  public static void tearDownClass() throws Exception {
  }

  @Before
  public void setUp() {
    instance = RegistriesManagerDAO.getInstance();

    //--------------------------------------------------------------------------
    // setup invalid profile
    //--------------------------------------------------------------------------
    invalidPersonProfile = new PersonRegistryProfileType();

    PersonType person = new PersonType();
    person.setId(999990);
    invalidPersonProfile.setPerson(person);

    RegistryProfileType registryProfile = new RegistryProfileType();
    registryProfile.setRegistryId(1);
    registryProfile.setRegistryDesc("DIABETES");
    invalidPersonProfile.getRegistry().add(registryProfile);

    invalidPersonProfile.setDataSource("KMR");
  }

  @After
  public void tearDown() {
  }

  /**
   * Test of getRegistryProfile method, of class RegistryManagerDAO.
   */
  //@Test
  public void testGetRegistryProfile() {
    System.out.println("getRegistryProfile()---------------------------------");
    for (int i = 0; i < ids.length; i++) {
      Long personId = Long.valueOf(ids[i]);
      PersonRegistryProfileType result = instance.getRegistryProfile(personId);

      //assertEquals(expResult, result);
      assertNotNull(result);

      dump(result);

//      String name = result.getPerson().getName();
//      System.out.println("id: " + personId + " ==> name: " + name);
//      assertEquals(names[i], name);
    }
  }

  /**
   * Test of getRegistryTypes method, of class RegistryManagerDAO.
   */
  //@Test
  public void testGetRegistryTypes() {
    System.out.println("getRegistryTypes()-----------------------------------");
    List<RegistryType> results = instance.getRegistryTypes();

    //assertEquals(expResult, result);
    assertNotNull(results);

    for (RegistryType result : results) {
      System.out.println(result);
    }
  }

  /**
   * Test of register method, of class RegistryManagerDAO.
   */
  //@Test
  public void testAlreadyRegistered() {
    System.out.println("testAlreadyRegistered()------------------------------");

    Long personId = Long.valueOf(647922);
    PersonRegistryProfileType result = instance.getRegistryProfile(personId);

    // already registered test
    PersonRegistryProfileType profile = result;
    try {
      AckType ack = instance.register(profile);
      System.out.println("Ack code:" + ack.getResponseCode() + ", message:" + ack.getDetectedIssueText());
    } catch (Exception ex) {
      System.err.println(ex.getMessage());
      assertEquals(AlreadyRegisteredException.class, ex.getClass());
    }
  }

  /**
   * Test of register method, of class RegistryManagerDAO.
   */
  //@Test
  public void testRegisterExistingPersonInNewRegistry() {
    System.out.println("testRegisterExistingPersonInNewRegistry()------------");

    Long personId = Long.valueOf(647922);

    // existing patient + new registry
    try {
      PersonRegistryProfileType result = instance.getRegistryProfile(personId);

      PersonRegistryProfileType profile = result;
      RegistryProfileType registry = new RegistryProfileType();
      registry.setRegistryId(5);
      registry.setRegistryDesc(registries[4]);
      profile.getRegistry().remove(0);
      profile.getRegistry().add(registry);
      profile.setDataSource("KMR");

      AckType ack = instance.register(profile);
      System.out.println("Ack code:" + ack.getResponseCode() + ", message:" + ack.getDetectedIssueText());
    } catch (Exception ex) {
      System.err.println(ex.getMessage());
    }
  }

  /**
   * Test of register method, of class RegistryManagerDAO.
   */
  @Test
  public void testRegisterNewPerson() {
    System.out.println("testRegisterNewPerson()------------------------------");
    ObjectFactory o = new ObjectFactory();
    
    try {
      PersonRegistryProfileType newprofile = new PersonRegistryProfileType();

      PersonType newperson = new PersonType();
      newperson.setId(new Long(999992));
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

      newprofile.setPerson(newperson);
      newprofile.getRegistry().add(regprofile);
      
      newprofile.setDataSource("KMR");
      
      AckType ack = instance.register(newprofile);

      assertEquals("OK", ack.getResponseCode());
      assertEquals("", ack.getDetectedIssueText());
    } catch (Exception ex) {
      System.err.println(ex.getMessage());
    }
  }

  /**
   * Test of unregister method, of class RegistryManagerDAO.
   */
  //@Test
  public void testUnregisterInvalidPerson() {
    System.out.println("testUnregisterInvalidPerson()------------------------");

    try {
      AckType ack = instance.unregister(invalidPersonProfile);
      System.out.println("Ack code:" + ack.getResponseCode() + ", message:" + ack.getDetectedIssueText());
    } catch (Exception ex) {
      System.err.println(ex.getMessage());
      //assertEquals(AlreadyRegisteredException.class, ex.getClass());
    }
  }

    /**
   * Test of unregister method, of class RegistryManagerDAO.
   */
  //@Test
  public void testUnregisterValidPerson() {
    System.out.println("testUnregisterValidPerson()--------------------------");

    try {
      PersonRegistryProfileType profile = instance.getRegistryProfile(newPerson);
      AckType ack = instance.unregister(profile);
      System.out.println("Ack code:" + ack.getResponseCode() + ", message:" + ack.getDetectedIssueText());
      
      assertEquals(ack.getResponseCode(), "OK");
      assertEquals(ack.getDetectedIssueText(), "");
    } catch (Exception ex) {
      System.err.println(ex.getMessage());
    }
  }

  /**
   * Test of getRegistryProfile method, of class RegistryManagerDAO.
   */
  //@Test
  public void testRegistriesManagerDAO() {
//    testGetRegistryTypes();
//    testGetRegistryProfile();
//    testAlreadyRegistered();
//    testRegisterExistingPersonInNewRegistry();
    testRegisterNewPerson();
    testUnregisterInvalidPerson();
    testUnregisterValidPerson();
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

      System.out.println("# of REGISTRY=" + obj.getRegistry().size());
      
      if (obj.getRegistry() != null && !obj.getRegistry().isEmpty()) {
         for (int i = 0; i < obj.getRegistry().size(); i++) {
            RegistryProfileType registry = obj.getRegistry().get(i);
            str.append(" registry=[" + registry.getRegistryId() + "|" + registry.getRegistryDesc() + "]");
         }
      }

      System.out.println(str.toString());
   }
}
