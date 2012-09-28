/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gov.hhs.fha.nhinc.ldapaccess;

import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import org.springframework.ldap.core.AttributesMapper;

/**
 * Maps a set of attributes to a Role object.
 *
 * @author cmatser
 */
public class RoleAttributeMapper implements AttributesMapper {

    public Object mapFromAttributes(Attributes attributes) throws NamingException {
        RoleDTO roleDTO = new RoleDTO();
        Attribute commonName = attributes.get("cn");
        if(commonName != null)
            roleDTO.setCommonName((String) commonName.get());
        Attribute departmentNumber = attributes.get("departmentNumber");
        if(departmentNumber != null)
            roleDTO.setDepartmentNumber((String) departmentNumber.get());
        Attribute description = attributes.get("description");
        if(description != null)
            roleDTO.setDescription((String) description.get());
        Attribute displayName = attributes.get("displayName");
        if(displayName != null)
            roleDTO.setDisplayName((String) displayName.get());
        Attribute fax = attributes.get("fax");
        if(fax != null)
            roleDTO.setFax((String) fax.get());
        Attribute locationCode = attributes.get("locationCode");
        if(locationCode != null)
            roleDTO.setLocationCode((String) locationCode.get());
        Attribute mail = attributes.get("mail");
        if(mail != null)
            roleDTO.setMail((String) mail.get());
        Attribute organizationalUnit = attributes.get("ou");
        if(organizationalUnit != null)
            roleDTO.setOrganizationalUnit((String) organizationalUnit.get());
        Attribute pager = attributes.get("pager");
        if(pager != null)
            roleDTO.setPager((String) pager.get());
        Attribute roleOccupant = attributes.get("roleOccupant");
        if(roleOccupant != null) {
            NamingEnumeration<?> occupants = roleOccupant.getAll();
            while (occupants.hasMore()) {
                roleDTO.getRoleOccupants().add((String) occupants.next());
            }
        }
        Attribute telephoneNumber = attributes.get("telephoneNumber");
        if(telephoneNumber != null)
            roleDTO.setTelephoneNumber((String) telephoneNumber.get());

        return roleDTO;
    }

}