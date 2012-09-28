/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gov.hhs.fha.nhinc.account;

import gov.hhs.fha.nhinc.common.account.CreateAccountRequestType;
import gov.hhs.fha.nhinc.common.account.Response;
import gov.hhs.fha.nhinc.common.account.UpdateAccountRequestType;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import gov.hhs.fha.nhinc.ldapaccess.ContactDAO;
import gov.hhs.fha.nhinc.ldapaccess.ContactDTO;
import gov.hhs.fha.nhinc.ldapaccess.LdapService;

/**
 *
 * @author Sushma
 */
public class AccountMgrImpl {

    /** Logger. */
    private static Log log = LogFactory.getLog(AccountMgrImpl.class);
    private String ERROR_CREATE_ACCOUNT = "Error while creating user Account";

    public Response updateAccount(UpdateAccountRequestType updateAccountRequest) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public Response createAccount(CreateAccountRequestType createAccountRequest) {
        log.debug("Starting create Account for createAccountRequest query.");
        Response response = new Response();
        
        try {
            ContactDAO contactDAO = LdapService.getContactDAO();
            ContactDTO contactDTO = new ContactDTO();
            contactDTO.setCommonName(createAccountRequest.getUserName());
            contactDTO.setUid(createAccountRequest.getSsn());
            contactDTO.setGivenName(createAccountRequest.getName().getFirstName());
            contactDTO.setInitials(createAccountRequest.getName().getMiddleName());
            contactDTO.setSurname(createAccountRequest.getName().getLastName());
            contactDTO.setHomePhone(createAccountRequest.getPhoneNumber().getHomeNumber());
            contactDTO.setMobile(createAccountRequest.getPhoneNumber().getMobileNumber());
            contactDTO.setTelephoneNumber(createAccountRequest.getPhoneNumber().getWorkNumber());
            contactDTO.setPostalAddress(createAccountRequest.getAddress().getAddress1());
            contactDTO.setStreet(createAccountRequest.getAddress().getAddress2());
            contactDTO.setCity(createAccountRequest.getAddress().getCity());
            contactDTO.setState(createAccountRequest.getAddress().getState());
            contactDTO.setPostalCode(createAccountRequest.getAddress().getZipCode());
            contactDTO.setMail(createAccountRequest.getEmailAddress());
            contactDTO.setUserPassword(createAccountRequest.getPassword().getBytes());
            contactDAO.insertContact(contactDTO);
            response.setText("User details successfully inserted into LDAP");
            log.info("User details successfully inserted into LDAP");
            response.setSuccess(true);

        } catch (Throwable t) {
            response.setSuccess(false);
            log.error("Error creating User Account", t);
            response.setText(ERROR_CREATE_ACCOUNT + t.getMessage() == null ? t.getClass().getName() : t.getMessage());
        }
        return response;
    }
}
