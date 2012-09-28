/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mil.navy.med.dzreg.dao;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import mil.navy.med.dzreg.Constants;
import mil.navy.med.dzreg.RegistriesManager;
import mil.navy.med.dzreg.Utils;
import mil.navy.med.dzreg.jpa.DzPatients;
import mil.navy.med.dzreg.jpa.DzReg;
import mil.navy.med.dzreg.jpa.DzRegPK;
import mil.navy.med.dzreg.jpa.DzType;
import mil.navy.med.dzreg.types.AckType;
import mil.navy.med.dzreg.types.AddressType;
import mil.navy.med.dzreg.types.ObjectFactory;
import mil.navy.med.dzreg.types.PersonType;
import mil.navy.med.dzreg.types.PersonRegistryProfileType;
import mil.navy.med.dzreg.types.RegistryProfileType;
import mil.navy.med.dzreg.types.RegistryType;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author kim
 */
public class RegistriesManagerDAO extends Constants implements RegistriesManager {

  public final static String REGISTRY_MANAGER_PU = "RegistriesPU";
  private static PersistentServiceFactory factory = null;
  private static Log log = LogFactory.getLog(RegistriesManagerDAO.class);
  private static RegistriesManagerDAO dao = null;

  private static ObjectFactory objFactory = null;
  
  protected RegistriesManagerDAO(String pUnit) {
    factory = PersistentServiceFactory.getInstance(pUnit);
    if (objFactory == null)
       objFactory = new ObjectFactory();
  }

  public static RegistriesManagerDAO getInstance() {
    synchronized (RegistriesManagerDAO.class) {
      if (dao == null) {
        dao = new RegistriesManagerDAO(REGISTRY_MANAGER_PU);
      }
    }

    return dao;
  }

  /**
   * Returns the registry profile of a person.
   * @param personId  unique identifier of person
   * @return  PersonRegistryProfileType
   */
  public PersonRegistryProfileType getRegistryProfile(Long personId) {
    EntityManager em = null;
    PersonRegistryProfileType profile = null;
    PersistentServiceFactory psf = null;

    if (personId != null) {
      try {
        psf = PersistentServiceFactory.getInstance(REGISTRY_MANAGER_PU);
        em = psf.getEntityManager();

        Query query = em.createNamedQuery("DzPatients.findByPatid");
        query.setParameter("patid", personId);
        DzPatients result = (DzPatients) query.getSingleResult();

        if (result != null) {
          em.refresh(result);
          //log.debug(result.toString());
          profile = map(result);
        }
      } catch (javax.persistence.NoResultException nr) {
        log.error("No data found for personId=" + personId);
      } finally {
        em.close();
      }
    } else {
      log.error("personId can not be null or empty");
    }

    return profile;
  }

  /**
   * Returns a list of available registry types.
   * @return  List<RegistryType>
   */
  public List<RegistryType> getRegistryTypes() {
    EntityManager em = null;
    List<RegistryType> types = new ArrayList<RegistryType>();
    PersistentServiceFactory psf = null;

    RegistryType regType = null;

    try {
      psf = PersistentServiceFactory.getInstance(REGISTRY_MANAGER_PU);
      em = psf.getEntityManager();

      Query query = em.createNamedQuery("DzType.findAll");
      Collection<DzType> results = query.getResultList();

      for (DzType d : results) {
        log.debug(d.toString());

        regType = new RegistryType();
        regType.setRegistryId(d.getDztypeId());
        regType.setRegistryDesc(d.getDescr());
        types.add(regType);
      }
    } catch (javax.persistence.NoResultException nr) {
      log.error(nr);
    } finally {
      em.close();
    }

    return types;
  }

  /**
   * Register a new registry profile.
   * @param profile
   * @return
   * @throws Exception
   */
  public AckType register(PersonRegistryProfileType profile) {

    EntityManager em = null;
    PersistentServiceFactory psf = null;
    StringBuffer exceptionMsg = new StringBuffer();

    AckType ack = new AckType();
    ack.setResponseCode(_APPLICATION_ERROR);

    if (profile != null &&
            profile.getPerson() != null &&
            profile.getRegistry() != null && !profile.getRegistry().isEmpty() &&
            profile.getDataSource() != null) {
      //------------------------------------------------------------------------
      // Registry type must be valid.
      //------------------------------------------------------------------------
      Map<Integer, DzType> toBeRegisterDzTypes = Collections.synchronizedMap(new HashMap<Integer, DzType>());
      for (RegistryType r : profile.getRegistry()) {
        try {
          DzType dzType = this.validRegistryType(r);
          if (dzType != null) {
            toBeRegisterDzTypes.put(Integer.valueOf(dzType.getDztypeId()), dzType);
          } else {
            exceptionMsg.append("Invalid/Unknown registy type specified - " + r.getRegistryId() + ";");
          }
        } catch (Exception ex) {
          exceptionMsg.append(ex + ";");
        }
      }

      //----------------------------------------------------------------------
      // Person info must have following elements:
      //   1. Identifer
      //   2. Name
      //   3. Date of birth
      //   4. Data Source
      //----------------------------------------------------------------------
      PersonType person = profile.getPerson();

      if (person.getName() == null || person.getName().isEmpty() ||
              (person.getDataSource() == null && profile.getDataSource() == null)) {
        ack.setDetectedIssueText("Missing required metadata (person identifier or name or data source);");
        return ack;
      }

      //------------------------------------------------------------------------
      // Check to see if this person already registered.
      //------------------------------------------------------------------------
      DzPatients registeredPatient = null;

      try {
        registeredPatient = validPerson(person);
      } catch (javax.persistence.NoResultException nre) {
      } catch (Exception ex) {
        ack.setDetectedIssueText("Failed to register patient - " + ex.getMessage());
        return ack;
      }

      try {
        psf = PersistentServiceFactory.getInstance(REGISTRY_MANAGER_PU);
        em = psf.getEntityManager();

        em.getTransaction().begin();

        // Get the date today using Calendar object.
        Calendar cal = Calendar.getInstance();
        Timestamp today = new Timestamp(cal.getTimeInMillis());

        //----------------------------------------------------------------------
        // If yes, only need to add a record to table DZ_REG for each new
        // registry type.
        //----------------------------------------------------------------------
        if (registeredPatient != null) {
          // remove any registry type (from request) that is already assigned to
          // this person
          Collection<DzReg> registries = registeredPatient.getDzRegCollection();
          for (DzReg r : registries) {
            Integer intDzTypeId = Integer.valueOf(r.getDzType().getDztypeId());
            if (toBeRegisterDzTypes.containsKey(intDzTypeId)) {
              toBeRegisterDzTypes.remove(intDzTypeId);

              log.debug("Already registered in Registry " + intDzTypeId + "!");
              exceptionMsg.append("Already registered in Registry " + intDzTypeId + ";");
            }
          }

          // what we have left is new registry type to be add to person registry
          // profile
          Collection<DzType> toBeRegisterColl = toBeRegisterDzTypes.values();
          for (DzType d : toBeRegisterColl) {
            // only need to add a record to table DZ_REG
            DzRegPK pk = new DzRegPK(person.getId(), d.getDztypeId());
            DzReg newDzreg = new DzReg();
            newDzreg.setDzRegPK(pk);
            newDzreg.setActive(_ACTIVE);
            newDzreg.setDataSource(person.getDataSource());
            newDzreg.setRegisteredDt(today);
            newDzreg.setInsertedDt(today);

            em.persist(newDzreg);
          }
        } //----------------------------------------------------------------------
        // If no, need to insert a new record in DZ_PATIENTS table and a new
        // record in table DZ_REG for each new registry type.
        //----------------------------------------------------------------------
        else {
          DzPatients newDzPatient = map(person);
          newDzPatient.setInsertedDt(today);
          newDzPatient.setUpdatedDt(today);
          if (person.getDataSource() == null) {
            if (profile.getDataSource() != null) {
              newDzPatient.setDataSource(profile.getDataSource());
            } else {
              // cannot insert record
              throw new Exception("Missing required metadata (data source);");
            }
          } else {
            newDzPatient.setDataSource(profile.getDataSource());
          }

          Collection<DzType> dzTypes = toBeRegisterDzTypes.values();
          Collection<DzReg> newDzregList = new ArrayList<DzReg>(dzTypes.size());
          for (DzType dzType : dzTypes) {
            DzRegPK pk = new DzRegPK(person.getId(), dzType.getDztypeId());
            DzReg newDzreg = new DzReg();
            newDzreg.setDzRegPK(pk);
            newDzreg.setActive(_ACTIVE);
            newDzreg.setRegisteredDt(today);
            newDzreg.setInsertedDt(today);

            if (person.getDataSource() == null) {
              newDzreg.setDataSource(profile.getDataSource());
            } else {
              newDzreg.setDataSource(person.getDataSource());
            }

            newDzregList.add(newDzreg);
          }

          newDzPatient.setDzRegCollection(newDzregList);
          em.persist(newDzPatient);
        }

        em.getTransaction().commit();

        ack.setResponseCode(_OK);
        ack.setDetectedIssueText(exceptionMsg.toString());
        
        return ack;
      } catch (Exception ex) {
        ex.printStackTrace();
        em.getTransaction().rollback();
        log.error("Failed to create new records in table DZ_PATIENTS/DZ_REG for profile=" + profile);

        ack.setDetectedIssueText("Failed to register patient " + profile.getPerson().getId() + "-" + ex.getMessage());
        return ack;
      } finally {
        em.close();
      }
    } else {
      ack.setDetectedIssueText("Invalid registry profile");
      return ack;
    }
  }

  /**
   * Unregister a person from requested registry or registries.
   * @param profile
   * @return
   * @throws Exception
   */
  public AckType unregister(PersonRegistryProfileType profile) {

    StringBuffer exceptionMsg = new StringBuffer();

    AckType ack = new AckType();
    ack.setResponseCode(_OK);

    if (profile != null &&
            profile.getPerson() != null &&
            profile.getRegistry() != null && !profile.getRegistry().isEmpty()) {
      //------------------------------------------------------------------------
      // Registry type must be valid.
      //------------------------------------------------------------------------
      Map<Integer, DzType> toBeUnregisterDzTypes = new HashMap<Integer, DzType>();
      for (RegistryType r : profile.getRegistry()) {
        try {
          DzType dzType = this.validRegistryType(r);
          if (dzType != null) {
            toBeUnregisterDzTypes.put(Integer.valueOf(dzType.getDztypeId()), dzType);
          } else {
            exceptionMsg.append("Invalid/Unknown registy type specified - " + r.getRegistryId() + ";");
          }
        } catch (Exception ex) {
          exceptionMsg.append(ex.getMessage() + ";");
        }
      }

      //----------------------------------------------------------------------
      // Person info must have following elements: Identifer.
      // Update DZ_REG table and set column ACTIVE of relevant record to 0.
      //----------------------------------------------------------------------
      PersonType person = profile.getPerson();
      try {
        // Get the date today using Calendar object.
        Calendar cal = Calendar.getInstance();
        Timestamp today = new Timestamp(cal.getTimeInMillis());

        Collection<DzType> dzTypes = toBeUnregisterDzTypes.values();
        boolean unregistered;
        for (DzType d : dzTypes) {
          unregistered = unregister(new DzRegPK(person.getId(), d.getDztypeId()), today);
          if (!unregistered) {
            exceptionMsg.append("Unregister from registry " + d.getDztypeId() + " failed;");
          }
        }

        if (exceptionMsg.toString().isEmpty())
          ack.setResponseCode(_OK);
        else
          ack.setResponseCode(_APPLICATION_ERROR);
        ack.setDetectedIssueText(exceptionMsg.toString());
      } catch (Exception e) {
        e.printStackTrace();
        log.error("Failed to update profile=" + profile);

        ack.setResponseCode(_APPLICATION_ERROR);
        ack.setDetectedIssueText("Failed to unregister person " + person.getId() + "-" + e.getMessage());
      }
    } else {
      ack.setResponseCode(_APPLICATION_ERROR);
      ack.setDetectedIssueText("Invalid registry profile");
    }

    return ack;
  }

  private boolean unregister(DzRegPK registryKey, Timestamp today) {
    EntityManager em = null;
    PersistentServiceFactory psf = null;

    try {
      psf = PersistentServiceFactory.getInstance(REGISTRY_MANAGER_PU);
      em = psf.getEntityManager();
      
      em.getTransaction().begin();
      DzReg r = em.find(DzReg.class, registryKey);
      r.setActive(_INACTIVE);
      r.setInsertedDt(today);
      em.getTransaction().commit();
      
      return true;
    } catch (javax.persistence.NoResultException nre) {
      em.getTransaction().rollback();
      log.error(registryKey + " not found!");
      return false;
    } catch (Exception ex) {
      em.getTransaction().rollback();
      return false;
    } finally {
      em.close();
    }
  }

  /**
   * Check registry type, returns a DzType object.
   * @param r
   * @return  DzType registry type object
   */
  private DzType validRegistryType(RegistryType r) throws Exception {
    EntityManager em = null;
    PersistentServiceFactory psf = null;
    DzType registry = null;

    try {
      psf = PersistentServiceFactory.getInstance(REGISTRY_MANAGER_PU);
      em = psf.getEntityManager();

      Query query = em.createNamedQuery("DzType.findByDztypeId");
      query.setParameter("dztypeId", r.getRegistryId());
      registry = (DzType) query.getSingleResult();
      if (registry == null) {
        throw new Exception("Invalid/Unknown registy type requested - " + r.getRegistryId());
      }

      log.debug("Located registry - " + registry);

      return registry;
    } catch (javax.persistence.NoResultException nre) {
      throw new Exception("Invalid/Unknown registy type requested - " + r.getRegistryId());
    } catch (Exception ex) {
      throw new Exception("Failed to register patient in registry.");
    } finally {
      em.close();
    }
  }

  /**
   * Check registry for requested person, return a DzPatients object.
   * @param p
   * @return  DzType registry type object
   */
  private DzPatients validPerson(PersonType p) throws Exception, NoResultException {
    EntityManager em = null;
    PersistentServiceFactory psf = null;
    DzPatients registeredPatient = null;

    try {
      psf = PersistentServiceFactory.getInstance(REGISTRY_MANAGER_PU);
      em = psf.getEntityManager();

      Query query = em.createNamedQuery("DzPatients.findByPatid");
      query.setParameter("patid", p.getId());
      registeredPatient = (DzPatients) query.getSingleResult();
    } catch (NoResultException nre) {
      throw nre;
    } catch (Exception ex) {
      throw new Exception("Person " + p.getId() + " not found;");
    } finally {
      em.close();
    }

    return registeredPatient;
  }

  /**
   *
   * @param pt
   * @return
   */
  private PersonRegistryProfileType map(
          DzPatients pt) {
    PersonRegistryProfileType profile = new PersonRegistryProfileType();

    profile.setDataSource(pt.getDataSource());

    //--------------------------------------------------------------------------
    // map person info
    //--------------------------------------------------------------------------
    PersonType person = new PersonType();

    if (pt != null) {
      person.setDataSource(pt.getDataSource());
      person.setDateOfBirth(pt.getPatientDob());
      person.setId(pt.getPatid());
      person.setEligibilityIdentifier(pt.getPatientDeersIdentifier());
      person.setFmpssn(pt.getFmpssn());
      person.setName(pt.getName());
      person.setOfficePhone(pt.getOfficePhone());
      person.setHomePhone(pt.getPhone());

      AddressType address = new AddressType();
      address.setStreetAddress(pt.getAddress());
      address.setStreetAddress2(pt.getStreet2());
      address.setCity(pt.getCity());
      address.setPostalCode(pt.getZip());
      address.setState(pt.getState());
      person.setAddress(objFactory.createPersonTypeAddress(address));

      profile.setPerson(person);
    }

    //--------------------------------------------------------------------------
    // map registry info
    //--------------------------------------------------------------------------
    Collection<DzReg> dzregColl = pt.getDzRegCollection();

    if (!dzregColl.isEmpty()) {
      for (DzReg d : dzregColl) {
        RegistryProfileType registry = map(d);
        if (registry != null) {
          registry.setRegisteredDate(d.getRegisteredDt());
        }

        profile.getRegistry().add(registry);
      }
    }

    return profile;
  }

  private DzPatients map(PersonType person) {
    DzPatients patient = new DzPatients();

    patient.setPatid(person.getId());
    patient.setFmpssn(person.getFmpssn());
    patient.setName(person.getName());
    patient.setDataSource(person.getDataSource());

    patient.setPatientDob(Utils.toTimestamp(person.getDateOfBirth()));
    if (person.getDateOfBirth() != null) {
      patient.setDob(Utils.dateToString(person.getDateOfBirth()));
    }

    if (person.getAddress() != null && !person.getAddress().isNil()) {
      AddressType addressType = person.getAddress().getValue();
      patient.setAddress(addressType.getStreetAddress());
      patient.setStreet2(addressType.getStreetAddress2());
      patient.setCity(addressType.getCity());
      patient.setState(addressType.getState());
      patient.setZip(addressType.getPostalCode());
    }

    patient.setPhone(person.getHomePhone());
    patient.setOfficePhone(person.getOfficePhone());

    return patient;
  }

  private RegistryProfileType map(DzReg d) {
    RegistryProfileType profile = null;

    DzType dz = d.getDzType();
    if (dz != null) {
      profile = new RegistryProfileType();
      profile.setRegistryId(dz.getDztypeId());
      profile.setRegistryDesc(dz.getDescr());     

      if (d.getActive() != null) {
        profile.setActive(Boolean.TRUE);
      } else {
        profile.setActive(Boolean.FALSE);
      }

    }

    return profile;
  }
}
