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
 * Maps a set of attributes to a Equipment object.
 *
 * @author cmatser
 */
public class EquipmentAttributeMapper implements AttributesMapper {

    public Object mapFromAttributes(Attributes attributes) throws NamingException {
        EquipmentDTO equipmentDTO = new EquipmentDTO();
        Attribute commonName = attributes.get("cn");
        if(commonName != null)
            equipmentDTO.setCommonName((String) commonName.get());
        Attribute description = attributes.get("description");
        if(description != null)
            equipmentDTO.setDescription((String) description.get());
        Attribute locality = attributes.get("locality");
        if(locality != null)
            equipmentDTO.setLocality((String) locality.get());
        Attribute organization = attributes.get("o");
        if(organization != null)
            equipmentDTO.setOrganization((String) organization.get());
        Attribute organizationalUnit = attributes.get("ou");
        if(organizationalUnit != null)
            equipmentDTO.setOrganizationalUnit((String) organizationalUnit.get());
        Attribute owner = attributes.get("owner");
        if(owner != null)
            equipmentDTO.setOwner((String) owner.get());
        Attribute seeAlso = attributes.get("seeAlso");
        if(seeAlso != null)
            equipmentDTO.setSeeAlso((String) seeAlso.get());
        Attribute serialNumber = attributes.get("serialNumber");
        if(serialNumber != null)
            equipmentDTO.setSerialNumber((String) serialNumber.get());

        return equipmentDTO;
    }

}