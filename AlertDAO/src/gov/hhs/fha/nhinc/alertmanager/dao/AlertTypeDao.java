/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gov.hhs.fha.nhinc.alertmanager.dao;

import gov.hhs.fha.nhinc.alertmanager.model.AlertType;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author cmatser
 */
public class AlertTypeDao {

    ObjectDao<AlertType> objectDao = new ObjectDao();

    Log log = LogFactory.getLog(AlertTypeDao.class);

    /**
     * Save a record to the database.
     * Insert if id is null. Update otherwise.
     *
     * @param alert AlertType object to save.
     */
    public void save(AlertType alert) {
        log.debug("Performing alert save");

        try {
            objectDao.save(alert);
        }
        catch (Throwable t) {
            log.error("Failure during object save.", t);
        }

        log.debug("Completed alert save");
    }

    /**
     * Delete a alert
     *
     * @param alert AlertType to delete
     */
    public void delete(AlertType alert) {
        log.debug("Performing alert delete");

        try {
            objectDao.delete(alert);
        }
        catch (Throwable t) {
            log.error("Failure during object delete.", t);
        }

        log.debug("Completed alert delete");
    }

    /**
     * Retrieve a alert by identifier
     *
     * @param alertTypeId AlertType identifier
     * @return Retrieved alert
     */
    public AlertType findById(Long alertTypeId) {
        AlertType alert = null;

        log.debug("Performing alert retrieve using id: " + alertTypeId);

        try {
            alert = objectDao.findById(alertTypeId, AlertType.class);
        }
        catch (Throwable t) {
            log.error("Failure during object findById", t);
        }

        log.debug("Completed alert retrieve by id");

        return alert;
    }

    /**
     * Retrieves all alerts
     *
     * @return All alert records
     */
    @SuppressWarnings("unchecked")
    public List<AlertType> findAll() {
        List<AlertType> alerts = null;

        log.debug("Performing retrieve of all alerts");

        try {
            alerts = objectDao.findAll(AlertType.class);
        }
        catch (Throwable t) {
            log.error("Failure during object findAll", t);
        }

        log.debug("Completed alert retrieve all");

        return alerts;
    }

}

