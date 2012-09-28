/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gov.hhs.fha.nhinc.ldapaccess;

import java.util.List;
import java.util.StringTokenizer;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.filter.AndFilter;
import org.springframework.ldap.filter.EqualsFilter;

/**
 * Data access object for equipment information.
 *
 * @author cmatser
 */
public class EquipmentDAO {

    /** Spring LDAP Context */
    private LdapTemplate ldapTemplate;

    public void setLdapTemplate(LdapTemplate ldapTemplate) {
        this.ldapTemplate = ldapTemplate;
    }

    /** Make private to force use of the service class. */
    private EquipmentDAO() { }

    /**
     * Return all equipment.
     * 
     * @return
     */
    public List<EquipmentDTO> findAllEquipment() {
        return ldapTemplate.search("", "(objectclass=device)", new EquipmentAttributeMapper());
    }

    /**
     * Find a specific set of equipment.
     * 
     * @param lookup ie. "cn=my.identity,st=CA"
     * @return
     */
    public List<EquipmentDTO> findEquipment(String lookup) {
        StringTokenizer st = new StringTokenizer(lookup, ",=");

        //Create filter for person objects
        AndFilter andFilter = new AndFilter();
        andFilter.and(new EqualsFilter("objectclass","device"));

        //Go through lookup string and add filters
        while (st.hasMoreTokens()) {
            String attr = null;
            String value = null;

            attr = st.nextToken();
            if (st.hasMoreTokens()) {
                value = st.nextToken();
            }

            if ((attr != null) && (value != null))
                andFilter.and(new EqualsFilter(attr, value));
        }

        return ldapTemplate.search("", andFilter.encode(), new EquipmentAttributeMapper());
    }

}