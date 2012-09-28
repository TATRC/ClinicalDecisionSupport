/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gov.hhs.fha.nhinc.taskmanager;

import gov.hhs.fha.nhinc.ldapaccess.ContactAcctDTO;
import gov.hhs.fha.nhinc.ldapaccess.ContactDAO;
import gov.hhs.fha.nhinc.ldapaccess.ContactDTO;
import gov.hhs.fha.nhinc.ldapaccess.LdapService;
import gov.hhs.fha.nhinc.ldapaccess.RoleDAO;
import gov.hhs.fha.nhinc.ldapaccess.RoleDTO;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author cmatser
 */
public class TaskManagerUtil {

    /**
     * Search the ldap roles to find the provider occupying that role.  The
     * return is a ldap query string that will find the provider object.
     *
     * @param role
     * @param location
     * @return
     */
    public static List<String> retrieveProviderLdaps(String role, String location) {
        List<String> providers = new LinkedList<String>();

        //Search for role occupant
        RoleDAO roleDAO = LdapService.getRoleDAO();
        List<RoleDTO> roles = roleDAO.findLocationRole(location, role);
        if (!roles.isEmpty()
                && !roles.get(0).getRoleOccupants().isEmpty()) {
            providers = roles.get(0).getRoleOccupants();
        }

        return providers;
    }

    /**
     * Find zimbra access.  For now, this is stored in the postal address of the
     * user ldap record.
     *
     * @param lookup
     * @return
     */
    public static String[] retrieveCalendarAccess(String lookup) {
        String access[] = new String[2];

        ContactDAO contactDAO = LdapService.getContactDAO();
        List<ContactDTO> contacts = contactDAO.findContact(lookup);
        if (contacts.size() > 0) {
            //Get mail login info
            List<ContactAcctDTO> accts = contactDAO.findContactAcct(
                contacts.get(0).getCommonName(), ContactAcctDTO.CN_CALENDAR);
            if (accts.size() > 0) {
                access[0] = accts.get(0).getUid();
                access[1] = accts.get(0).getClearPassword();
            }
        }

        return access;
    }

}
