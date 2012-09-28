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
public class ContactCertAttributeMapper implements AttributesMapper {

    public Object mapFromAttributes(Attributes attributes) throws NamingException {
        ContactCertDTO contactCertDTO = new ContactCertDTO();
        Attribute commonName = attributes.get("cn");
        if(commonName != null)
            contactCertDTO.setCommonName((String) commonName.get());
        Attribute description = attributes.get("description");
        if(description != null)
            contactCertDTO.setDescription((String) description.get());
        Attribute documentAuthor = attributes.get("documentAuthor");
        if(documentAuthor != null)
            contactCertDTO.setDocumentAuthor((String) documentAuthor.get());
        Attribute documentExpirationTime = attributes.get("documentExpirationTime");
        if(documentExpirationTime != null)
            contactCertDTO.setDocumentExpirationTime((String) documentExpirationTime.get());
        Attribute documentIdentifier = attributes.get("documentIdentifier");
        if(documentIdentifier != null)
            contactCertDTO.setDocumentIdentifier((String) documentIdentifier.get());
        Attribute documentLocation = attributes.get("documentLocation");
        if(documentLocation != null)
            contactCertDTO.setDocumentLocation((String) documentLocation.get());
        Attribute documentPublisher = attributes.get("documentPublisher");
        if(documentPublisher != null)
            contactCertDTO.setDocumentPublisher((String) documentPublisher.get());
        Attribute documentStatus = attributes.get("documentStatus");
        if(documentStatus != null)
            contactCertDTO.setDocumentStatus((String) documentStatus.get());
        Attribute documentTitle = attributes.get("documentTitle");
        if(documentTitle != null)
            contactCertDTO.setDocumentTitle((String) documentTitle.get());
        Attribute documentVersion = attributes.get("documentVersion");
        if(documentVersion != null)
            contactCertDTO.setDocumentVersion((String) documentVersion.get());

        return contactCertDTO;
    }

}