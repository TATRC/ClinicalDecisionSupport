/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mil.navy.med.dzreg;

import java.util.List;
import mil.navy.med.dzreg.types.AckType;
import mil.navy.med.dzreg.types.PersonRegistryProfileType;
import mil.navy.med.dzreg.types.RegistryType;

/**
 *
 * @author kim
 */
public interface RegistriesManager {

   public PersonRegistryProfileType getRegistryProfile(Long personId);

   public List<RegistryType> getRegistryTypes();

   public AckType register(PersonRegistryProfileType profile);

   public AckType unregister(PersonRegistryProfileType profile);
}

