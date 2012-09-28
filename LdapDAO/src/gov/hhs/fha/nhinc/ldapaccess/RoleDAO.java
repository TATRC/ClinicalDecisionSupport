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
 * Data access object for role information.
 *
 * @author cmatser
 */
public class RoleDAO {

    /** Spring LDAP Context */
    private LdapTemplate ldapTemplate;

    public void setLdapTemplate(LdapTemplate ldapTemplate) {
        this.ldapTemplate = ldapTemplate;
    }

    /** Make private to force use of the service class. */
    private RoleDAO() { }

    /**
     * Return all roles.
     * 
     * @return
     */
    public List<RoleDTO> findAllRoles() {
        return ldapTemplate.search("", "(objectclass=organizationalRole)", new RoleAttributeMapper());
    }

    /**
     * Find a specific set of roles.
     * 
     * @param lookup ie. "cn=my.identity,st=CA"
     * @return
     */
    public List<RoleDTO> findRole(String lookup) {
        StringTokenizer st = new StringTokenizer(lookup, ",=");

        //Create filter for role objects
        AndFilter andFilter = new AndFilter();
        andFilter.and(new EqualsFilter("objectclass","organizationalRole"));

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

        return ldapTemplate.search("", andFilter.encode(), new RoleAttributeMapper());
    }

    /**
     * Find a role for a location.
     *
     * @param locationCode
     * @param name ie. "charge nurse"
     * @return
     */
    public List<RoleDTO> findLocationRole(String locationCode, String roleName) {

        //Create filter for role objects
        AndFilter andFilter = new AndFilter();
        andFilter.and(new EqualsFilter("objectclass","organizationalRole"));

        if ((locationCode != null) && !locationCode.isEmpty()) {
            andFilter.and(new EqualsFilter("locationCode", locationCode));
        }

        return ldapTemplate.search("ou="+roleName, andFilter.encode(), new RoleAttributeMapper());
    }

}