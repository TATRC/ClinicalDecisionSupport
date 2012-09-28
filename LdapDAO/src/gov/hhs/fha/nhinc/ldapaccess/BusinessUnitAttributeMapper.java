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
 * Maps a set of attributes to a BusinessUnit object.
 *
 * @author cmatser
 */
public class BusinessUnitAttributeMapper implements AttributesMapper {

    public Object mapFromAttributes(Attributes attributes) throws NamingException {
        BusinessUnitDTO contactDTO = new BusinessUnitDTO();
        Attribute commonName = attributes.get("cn");
        if(commonName != null)
            contactDTO.setCommonName((String) commonName.get());
        Attribute description = attributes.get("description");
        if(description != null)
            contactDTO.setDescription((String) description.get());
        Attribute displayName = attributes.get("displayName");
        if(displayName != null)
            contactDTO.setDisplayName((String) displayName.get());
        Attribute organization = attributes.get("o");
        if(organization != null)
            contactDTO.setOrganization((String) organization.get());

        return contactDTO;
    }

}