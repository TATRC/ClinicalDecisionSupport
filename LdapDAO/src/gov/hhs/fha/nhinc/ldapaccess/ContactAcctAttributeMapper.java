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
 * Maps a set of attributes to a ContactCert object.
 *
 * @author cmatser
 */
public class ContactAcctAttributeMapper implements AttributesMapper {

    public Object mapFromAttributes(Attributes attributes) throws NamingException {
        ContactAcctDTO contactAcctDTO = new ContactAcctDTO();
        Attribute commonName = attributes.get("cn");
        if(commonName != null)
            contactAcctDTO.setCommonName((String) commonName.get());
        Attribute description = attributes.get("description");
        if(description != null)
            contactAcctDTO.setDescription((String) description.get());
        Attribute uid = attributes.get("uid");
        if(uid != null)
            contactAcctDTO.setUid((String) uid.get());
        Attribute clearPassword = attributes.get("clearPassword");
        if(clearPassword != null)
            contactAcctDTO.setClearPassword((String) clearPassword.get());

        return contactAcctDTO;
    }

}