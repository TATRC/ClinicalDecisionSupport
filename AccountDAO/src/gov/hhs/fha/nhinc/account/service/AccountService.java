/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gov.hhs.fha.nhinc.account.service;

import gov.hhs.fha.nhinc.account.dao.PatientProviderItemDao;
import gov.hhs.fha.nhinc.account.dao.UserSessionDao;
import gov.hhs.fha.nhinc.account.model.PatientProviderItem;
import gov.hhs.fha.nhinc.account.model.PatientProviderQueryParams;
import gov.hhs.fha.nhinc.account.model.UserSession;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


/**
 *
 * @author Sushma
 */
public class AccountService {

    private static Log log = LogFactory.getLog(AccountService.class);

    /**
     * Save address record.
     *
     * @param addr Address object to save.
     */
    public void savePatientProvider(PatientProviderItem ppi) {
        log.debug("Saving a PatientProvider");

        if (ppi != null) {
            if (ppi.getPatientProviderId() != null) {
                log.debug("Performing an update for patient provider: " + ppi.getPatientProviderId());
            } else {
                log.debug("Performing an insert");
            }

        }

        PatientProviderItemDao dao = new PatientProviderItemDao();
        dao.save(ppi);
    }

    /**
     * Saves the user session in the User Session table in the database.
     * 
     * @param userSession
     */
    public void saveUserSession(UserSession userSession) {
        log.debug("Saving a UserSession");

        if (userSession != null) {
            log.debug("Performing an update for user session, token is: " + userSession.getToken());
        }
        else {
            log.debug("Inserting user session");
        }

        UserSessionDao dao = new UserSessionDao();
        dao.save(userSession);
    }

    /**
     * Deletes a user session. To be called from the KMRHttpBindingListener
     * when a session times out.
     *
     * @param userSession
     * @throws AccountServiceException
     */
    public void deleteUserSession(UserSession userSession) throws AccountServiceException {
        if (userSession == null) {
            throw new AccountServiceException("UserSession to delete was null");
        }
        log.debug("Deleting a UserSession for " + userSession.getUserId());

        UserSessionDao dao = new UserSessionDao();
        dao.delete(userSession);
    }

    /**
     * Delete a patientProvider
     *
     * @param ppi patient provider to delete
     * @throws AccountServiceException
     */
    public void deleteAdress(PatientProviderItem ppi) throws AccountServiceException {
        log.debug("Deleting a PatientProvider");
        PatientProviderItemDao dao = new PatientProviderItemDao();

        if (ppi == null) {
            throw new AccountServiceException("PatientProvider to delete was null");
        }

        dao.delete(ppi);
    }

    public UserSession getUserSession(String token) {
        UserSessionDao dao = new UserSessionDao();
        return dao.findById(token);
    }

    /**
     * Retrieve a PatientProvider by identifier
     *
     * @param PatientProviderId PatientProvider identifier
     * @return Retrieved PatientProviderItem
     */
    public PatientProviderItem getPatient(Long providerId) {
        PatientProviderItemDao dao = new PatientProviderItemDao();
        return dao.findById(providerId);
    }

    /**
     * Retrieve a PatientProvider by identifier
     *
     * @param PatientProviderId PatientProvider identifier
     * @return Retrieved PatientProviderItem
     */
    public PatientProviderItem getProvider(Long patientId) {
        PatientProviderItemDao dao = new PatientProviderItemDao();
        return dao.findById(patientId);
    }

    /**
     * Retrieve a list of PatientProviders by PatientProviderQueryParams
     *
     * @param queryParams PatientProviderQueryParams 
     * @return Retrieved List<PatientProviderItem>
     */
    public List<PatientProviderItem> getProvidersByParams(PatientProviderQueryParams queryParams) {
        PatientProviderItemDao dao = new PatientProviderItemDao();
        return dao.findPatient(queryParams);
    }

    /**
     * Retrieves all PatientProvider
     *
     * @return All PatientProvider records
     */
    public List<PatientProviderItem> getAllPatientProvider() {
        PatientProviderItemDao dao = new PatientProviderItemDao();
        return dao.findAll();
    }
}
