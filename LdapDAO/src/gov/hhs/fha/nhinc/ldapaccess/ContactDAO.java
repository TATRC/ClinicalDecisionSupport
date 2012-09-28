/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gov.hhs.fha.nhinc.ldapaccess;

import java.util.List;
import java.util.StringTokenizer;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.BasicAttribute;
import javax.naming.directory.BasicAttributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.ModificationItem;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.filter.AndFilter;
import org.springframework.ldap.filter.EqualsFilter;

/**
 * Data access object for people's contact information.
 *
 * @author cmatser
 */
public class ContactDAO {

    /** Base DN */
    public static final String BASE_DN = "ou=people,dc=socraticgrid,dc=org";
    /** Spring LDAP Context */
    private LdapTemplate ldapTemplate;

    public void setLdapTemplate(LdapTemplate ldapTemplate) {
        this.ldapTemplate = ldapTemplate;
    }

    /** Make private to force use of the service class. */
    private ContactDAO() {
    }

    /**
     * Return all contacts.
     * 
     * @return
     */
    public List<ContactDTO> findAllContacts() {
        return ldapTemplate.search("", "(objectclass=person)", new ContactAttributeMapper());
    }

    /**
     * Find a specific set of contacts.
     * 
     * @param lookup ie. "cn=myuser.identity,st=CA"
     * @return
     */
    public List<ContactDTO> findContact(String lookup) {
        StringTokenizer st = new StringTokenizer(lookup, ",=");

        //Create filter for person objects
        AndFilter andFilter = new AndFilter();
        andFilter.and(new EqualsFilter("objectclass", "person"));

        //Go through lookup string and add filters
        while (st.hasMoreTokens()) {
            String attr = null;
            String value = null;

            attr = st.nextToken();
            if (st.hasMoreTokens()) {
                value = st.nextToken();
            }

            if ((attr != null) && (value != null)) {
                andFilter.and(new EqualsFilter(attr, value));
            }
        }

        return ldapTemplate.search("", andFilter.encode(), new ContactAttributeMapper());
    }

    /**
     * Return all accounts for a particular contact.
     * 
     * @param contactCN
     * @return
     */
    public List<ContactAcctDTO> findAllContactAccts(String contactCN) {
        return ldapTemplate.search("cn=" + contactCN, "(objectclass=kmr-account)", new ContactAcctAttributeMapper());
    }

    /**
     * Return a specific account for a particular contact.
     * 
     * @param contactCN
     * @param acctCN
     * @return
     */
    public List<ContactAcctDTO> findContactAcct(String contactCN, String acctCN) {

        //Create filter
        AndFilter andFilter = new AndFilter();
        andFilter.and(new EqualsFilter("objectclass", "kmr-account"));
        andFilter.and(new EqualsFilter("cn", acctCN));

        return ldapTemplate.search("cn=" + contactCN, andFilter.encode(), new ContactAcctAttributeMapper());
    }

    /**
     * Return all certifications for a particular contact.
     *
     * @param contactCN
     * @return
     */
    public List<ContactCertDTO> findAllContactCerts(String contactCN) {
        return ldapTemplate.search("cn=" + contactCN, "(objectclass=document)", new ContactCertAttributeMapper());
    }

    /**
     * Return a specific certification for a particular contact.
     *
     * @param contactCN
     * @param acctCN
     * @return
     */
    public List<ContactCertDTO> findContactCert(String contactCN, String certCN) {

        //Create filter
        AndFilter andFilter = new AndFilter();
        andFilter.and(new EqualsFilter("objectclass", "document"));
        andFilter.and(new EqualsFilter("cn", certCN));

        return ldapTemplate.search("cn=" + contactCN, andFilter.encode(), new ContactCertAttributeMapper());
    }

    /**
     * Update the user's photo.
     * 
     * @param p
     */
    public void updatePhoto(ContactDTO contact) {
        Attribute attr = new BasicAttribute("jpegPhoto", contact.getJpegPhoto());
        ModificationItem item = new ModificationItem(DirContext.REPLACE_ATTRIBUTE, attr);
        ldapTemplate.modifyAttributes("cn=" + contact.getCommonName(), new ModificationItem[]{item});
    }

    /**
     * Add a new contact to LDAP.
     *
     * @param contactDTO
     * 
     */
    public void insertContact(ContactDTO contactDTO) {
        Attributes personAttributes = new BasicAttributes();
        BasicAttribute personBasicAttribute1 = new BasicAttribute("objectclass");
        BasicAttribute personBasicAttribute2 = new BasicAttribute("objectclass");
        BasicAttribute personBasicAttribute3 = new BasicAttribute("objectclass");
        BasicAttribute personBasicAttribute4 = new BasicAttribute("objectclass");
        personBasicAttribute1.add("person");
        personBasicAttribute2.add("inetOrgPerson");
        personBasicAttribute3.add("organizationalPerson");
        personBasicAttribute4.add("kmr-orgPerson");
        personAttributes.put(personBasicAttribute1);
        personAttributes.put(personBasicAttribute2);
        personAttributes.put(personBasicAttribute3);
        personAttributes.put(personBasicAttribute4);

        if (null != contactDTO.getCommonName() && !contactDTO.getCommonName().isEmpty()) {
            personAttributes.put("cn", contactDTO.getCommonName());
        }
        if (null != contactDTO.getSurname() && !contactDTO.getSurname().isEmpty()) {
            personAttributes.put("sn", contactDTO.getSurname());
        }
        if (null != contactDTO.getGivenName() && !contactDTO.getGivenName().isEmpty()) {
            personAttributes.put("givenName", contactDTO.getGivenName());
        }
        if (null != contactDTO.getInitials() && !contactDTO.getInitials().isEmpty()) {
            personAttributes.put("initials", contactDTO.getInitials());
        }
        if (null != contactDTO.getUid() && !contactDTO.getUid().isEmpty()) {
            personAttributes.put("uid", contactDTO.getUid());
        }
        if (null != contactDTO.getHomePhone() && !contactDTO.getHomePhone().isEmpty()) {
            personAttributes.put("homePhone", contactDTO.getHomePhone());
        }
        if (null != contactDTO.getMobile() && !contactDTO.getMobile().isEmpty()) {
            personAttributes.put("mobile", contactDTO.getMobile());
        }
        if (null != contactDTO.getTelephoneNumber() && !contactDTO.getTelephoneNumber().isEmpty()) {
            personAttributes.put("telephoneNumber", contactDTO.getTelephoneNumber());
        }
        if (null != contactDTO.getPostalAddress() && !contactDTO.getPostalAddress().isEmpty()) {
            personAttributes.put("postalAddress", contactDTO.getPostalAddress());
        }
        if (null != contactDTO.getStreet() && !contactDTO.getStreet().isEmpty()) {
            personAttributes.put("street", contactDTO.getStreet());
        }
        if (null != contactDTO.getCity() && !contactDTO.getCity().isEmpty()) {
            personAttributes.put("l", contactDTO.getCity());
        }
        if (null != contactDTO.getState() && !contactDTO.getState().isEmpty()) {
            personAttributes.put("st", contactDTO.getState());
        }
        if (null != contactDTO.getPostalCode() && !contactDTO.getPostalCode().isEmpty()) {
            personAttributes.put("postalCode", contactDTO.getPostalCode());
        }
        if (null != contactDTO.getMail() && !contactDTO.getMail().isEmpty()) {
            personAttributes.put("mail", contactDTO.getMail());
        }
        if (null != contactDTO.getUserPassword()) {
            personAttributes.put("userPassword", contactDTO.getUserPassword());
        }
        //personAttributes.put("sn", contactDTO.getSSN());
        if (null != contactDTO.getDescription() && !contactDTO.getDescription().isEmpty()) {
            personAttributes.put("description", contactDTO.getDescription());
        }

        ldapTemplate.bind("cn=" + contactDTO.getCommonName(), null, personAttributes);
    }

     /**
     * Update a given field of a contact in LDAP.
     *
     * @param contactDTO
     * 
     */
    public void updateContact(ContactDTO contactDTO) {
        Attributes personAttributes = new BasicAttributes();
        BasicAttribute personBasicAttribute = new BasicAttribute("objectclass");
        personBasicAttribute.add("person");
        personAttributes.put(personBasicAttribute);
        personAttributes.put("cn", contactDTO.getCommonName());
        personAttributes.put("sn", contactDTO.getSurname());
        personAttributes.put("description", contactDTO.getDescription());
        ldapTemplate.rebind("cn=" + contactDTO.getCommonName(), null, personAttributes);
    }

     /**
     * Delete a contact from LDAP.
     *
     * @param contactDTO
     *
     */
    public void deleteContact(ContactDTO contactDTO) {

        ldapTemplate.unbind("cn=" + contactDTO.getCommonName());
    }
}
