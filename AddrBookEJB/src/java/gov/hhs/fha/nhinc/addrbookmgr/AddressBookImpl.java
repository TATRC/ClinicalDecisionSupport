/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gov.hhs.fha.nhinc.addrbookmgr;

import gov.hhs.fha.nhinc.addrbook.model.AddressItem;
import gov.hhs.fha.nhinc.addrbook.model.AddressQueryParams;
import gov.hhs.fha.nhinc.addrbook.service.AddrBookService;
import gov.hhs.fha.nhinc.common.addrbook.ContactDetails;
import gov.hhs.fha.nhinc.common.addrbook.ContactSummary;
import gov.hhs.fha.nhinc.common.addrbook.GetContactDetailsResponseType;
import gov.hhs.fha.nhinc.common.addrbook.GetSummariesResponseType;
import gov.hhs.fha.nhinc.common.addrbook.SearchAddrRequestType;
import gov.hhs.fha.nhinc.common.addrbook.ServiceError;
import gov.hhs.fha.nhinc.ldapaccess.ContactDAO;
import gov.hhs.fha.nhinc.ldapaccess.ContactDTO;
import gov.hhs.fha.nhinc.ldapaccess.LdapService;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author cmatser
 */
public class AddressBookImpl {

    /** List of address agents. */
    private List<AddrBookAgent> agents = null;

    /** General error. */
    private static int ERROR_ADDRBOOK = -1;

    /** Delimiter used in address id for detail retrieval. */
    public static final String ADDR_ID_DELIM = "/";

    /** Logger. */
    private static Log log = LogFactory.getLog(AddressBookImpl.class);

    public AddressBookImpl() {

        //Init agent list
        if (agents == null) {
            agents = new LinkedList<AddrBookAgent>();
            agents.add(new AllPatientAgent());
            agents.add(new AllProviderAgent());
            agents.add(new OptInProviderAgent());
            agents.add(new AppointmentAgent());
            agents.add(new PrimaryCareAgent());
        }

    }

    /**
     * Return all addresses for this user.
     * 
     * @param request
     * @return
     */
    public gov.hhs.fha.nhinc.common.addrbook.GetSummariesResponseType getAllAddr(gov.hhs.fha.nhinc.common.addrbook.GetAllAddrRequestType request) {

        log.debug("Starting address agents for getAll query.");

        //Forward to search query (search for all)
        SearchAddrRequestType searchRequest = new SearchAddrRequestType();
        searchRequest.setUserId(request.getUserId());
        searchRequest.setSearch(null);

        return searchAddr(searchRequest);
    }

    /**
     * Return the addresses that match the query.
     * 
     * @param request
     * @return
     */
    public gov.hhs.fha.nhinc.common.addrbook.GetSummariesResponseType searchAddr(gov.hhs.fha.nhinc.common.addrbook.SearchAddrRequestType request) {
        GetSummariesResponseType response = new GetSummariesResponseType();

        try {
            AddrBookService dbService = new AddrBookService();
            AddressQueryParams query = new AddressQueryParams();
            query.setUserId(request.getUserId());
            List<AddressItem> addrs = dbService.addressQuery(query);

            log.debug("Starting address agents for query.");

            //Loop through all agents and ask them to return the contact ids
            List<Thread> threads = new LinkedList<Thread>();
            for (AddrBookAgent agent : agents) {
                agent.setAddressStart(addrs);
                Thread thread = new Thread(agent);
                thread.start();
                threads.add(thread);
            }

            //Wait for all threads to finish
            for (Thread thread : threads) {
                while (thread.isAlive()) {
                    try { Thread.sleep(1000); } catch (InterruptedException ie) { }
                }
            }

            log.debug("Processing agent retrieved contact info for query: " + request.getSearch());

            //Loop through get contacts
            ContactDAO contactDAO = LdapService.getContactDAO();
            List<String> lookups = new LinkedList<String>();
            for (AddrBookAgent agent : agents) {
                for (AddressItem addr : agent.getAddressFinish()) {

                    //First check if the contact matches our search query
                    if ((request.getSearch() != null)
                            && !request.getSearch().isEmpty()
                            && !addr.getName().matches(request.getSearch())) {
                        continue;
                    }

                    //Check if we've already done this lookup
                    if (lookups.contains(addr.getContactId())) {
                        continue;
                    }
                    lookups.add(addr.getContactId());

                    log.debug("Looking up ldap contact: " + addr.getContactId());

                    List<ContactDTO> contacts = contactDAO.findContact(addr.getContactId());
                    for (ContactDTO contact : contacts) {
                        ContactSummary summary = new ContactSummary();
                        summary.setAddressId(addr.getAddressId().toString()
                                + ADDR_ID_DELIM + addr.getName()
                                + ADDR_ID_DELIM + "cn=" + contact.getCommonName());
                        summary.setName(addr.getName());
                        summary.setLocation(contact.getLocality() == null ? "" : contact.getLocality());
                        summary.setDescription(contact.getDescription() == null ? "" : contact.getDescription());

                        response.getSummaryObjects().add(summary);
                    }
                }
            }

            //Sort contacts
            Collections.sort(response.getSummaryObjects(), new SummaryComparator());
        }
        catch (Throwable t) {
            log.error("Error getting all addresses.", t);

            ServiceError error = new ServiceError();
            error.setCode(ERROR_ADDRBOOK);
            error.setText(t.getMessage() == null ? t.getClass().getName() : t.getMessage());
            response.getErrorList().add(error);
        }

        return response;
    }

    /**
     * Return the contact details.
     * 
     * @param request
     * @return
     */
    public gov.hhs.fha.nhinc.common.addrbook.GetContactDetailsResponseType getContactDetails(gov.hhs.fha.nhinc.common.addrbook.GetContactDetailsRequestType request) {
        GetContactDetailsResponseType response = new GetContactDetailsResponseType();

        try {
            log.debug("Finding contact details for: " + request.getAddressId());

            //Extract info from address id in request
            StringTokenizer tokenizer = new StringTokenizer(request.getAddressId(), ADDR_ID_DELIM);
            String addrId = null;
            String addrName = null;
            String addrContactId = null;
            if (tokenizer.hasMoreTokens()) {
                addrId = tokenizer.nextToken();
            }
            if (tokenizer.hasMoreTokens()) {
                addrName = tokenizer.nextToken();
            }
            if (tokenizer.hasMoreTokens()) {
                addrContactId = tokenizer.nextToken();
            }
            else {
                addrContactId = addrName; //in case name not in tokens
            }

            if ((addrContactId == null) || addrContactId.isEmpty()) {
                throw new Exception("Contact id not found.");
            }

            //Search ldap
            ContactDAO contactDAO = LdapService.getContactDAO();
            List<ContactDTO> contacts = contactDAO.findContact(addrContactId);
            if (contacts.isEmpty()) {
                throw new Exception("Contact not found: " + request.getAddressId());
            }

            //Create return object
            ContactDetails details = new ContactDetails();
            details.setAddressId(request.getAddressId());
            details.setName(addrName);
            details.setLocation(contacts.get(0).getLocality() == null ? "" : contacts.get(0).getLocality());
            details.setRoomNumber(contacts.get(0).getRoomNumber() == null ? "" : contacts.get(0).getRoomNumber());
            details.setDescription(contacts.get(0).getDescription() == null ? "" : contacts.get(0).getDescription());
            details.setEmail(contacts.get(0).getMail() == null ? "" : contacts.get(0).getMail());
            details.setFax(contacts.get(0).getFacsimileTelephoneNumber() == null ? "" : contacts.get(0).getFacsimileTelephoneNumber());
            details.setGender(contacts.get(0).getGender() == null ? "" : contacts.get(0).getGender());
            details.setOffice(contacts.get(0).getTelephoneNumber() == null ? "" : contacts.get(0).getTelephoneNumber());

            response.setContact(details);
        }
        catch (Throwable t) {
            log.error("Error getting contact details.", t);

            ServiceError error = new ServiceError();
            error.setCode(ERROR_ADDRBOOK);
            error.setText(t.getMessage() == null ? t.getClass().getName() : t.getMessage());
            response.getErrorList().add(error);
        }

        return response;
    }

}
