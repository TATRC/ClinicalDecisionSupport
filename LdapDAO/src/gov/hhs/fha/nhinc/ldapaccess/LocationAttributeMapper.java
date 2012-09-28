/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gov.hhs.fha.nhinc.ldapaccess;

import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import org.springframework.ldap.core.AttributesMapper;

/**
 * Maps a set of attributes to a Location object.
 *
 * @author cmatser
 */
public class LocationAttributeMapper implements AttributesMapper {

    public Object mapFromAttributes(Attributes attributes) throws NamingException {
        LocationDTO locationDTO = new LocationDTO();
        Attribute commonName = attributes.get("cn");
        if(commonName != null)
            locationDTO.setCommonName((String) commonName.get());
        Attribute departmentNumber = attributes.get("departmentNumber");
        if(departmentNumber != null)
            locationDTO.setDepartmentNumber((String) departmentNumber.get());
        Attribute description = attributes.get("description");
        if(description != null)
            locationDTO.setDescription((String) description.get());
        Attribute destinationIndicator = attributes.get("destinationIndicator");
        if(destinationIndicator != null)
            locationDTO.setDestinationIndicator((String) destinationIndicator.get());
        Attribute displayName = attributes.get("displayName");
        if(displayName != null)
            locationDTO.setDisplayName((String) displayName.get());
        Attribute organizationalUnit = attributes.get("ou");
        if(organizationalUnit != null)
            locationDTO.setOrganizationalUnit((String) organizationalUnit.get());
        Attribute telephoneNumber = attributes.get("telephoneNumber");
        if(telephoneNumber != null)
            locationDTO.setTelephoneNumber((String) telephoneNumber.get());

        return locationDTO;
    }

}