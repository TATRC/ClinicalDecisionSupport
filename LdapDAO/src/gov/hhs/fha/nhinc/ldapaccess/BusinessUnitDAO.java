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
 * Data access object for business unit information.
 *
 * @author cmatser
 */
public class BusinessUnitDAO {

    /** Spring LDAP Context */
    private LdapTemplate ldapTemplate;

    public void setLdapTemplate(LdapTemplate ldapTemplate) {
        this.ldapTemplate = ldapTemplate;
    }

    /** Make private to force use of the service class. */
    private BusinessUnitDAO() { }

    /**
     * Return all businss units.
     * 
     * @return
     */
    public List<BusinessUnitDTO> findAllBusinessUnits() {
        return ldapTemplate.search("", "(objectclass=organization)", new BusinessUnitAttributeMapper());
    }

    /**
     * Find a specific set of business units.
     * 
     * @param lookup ie. "cn=my.identity,st=CA"
     * @return
     */
    public List<BusinessUnitDTO> findBusinessUnit(String lookup) {
        StringTokenizer st = new StringTokenizer(lookup, ",=");

        //Create filter for person objects
        AndFilter andFilter = new AndFilter();
        andFilter.and(new EqualsFilter("objectclass","organization"));

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

        return ldapTemplate.search("", andFilter.encode(), new BusinessUnitAttributeMapper());
    }

}