/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gov.hhs.fha.nhinc.alertmanager.dao;

import gov.hhs.fha.nhinc.alertmanager.model.AlertAction;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author cmatser
 */
public class AlertActionDao {

    ObjectDao<AlertAction> objectDao = new ObjectDao();

    Log log = LogFactory.getLog(AlertActionDao.class);

    /**
     * Save a record to the database.
     * Insert if id is null. Update otherwise.
     *
     * @param alertAction AlertAction object to save.
     */
    public void save(AlertAction alertAction) {
        log.debug("Performing alertAction save");

        try {
            objectDao.save(alertAction);
        }
        catch (Throwable t) {
            log.error("Failure during object save.", t);
        }

        log.debug("Completed alertAction save");
    }

    /**
     * Delete a alertAction
     *
     * @param alertAction AlertAction to delete
     */
    public void delete(AlertAction alertAction) {
        log.debug("Performing alertAction delete");

        try {
            objectDao.delete(alertAction);
        }
        catch (Throwable t) {
            log.error("Failure during object delete.", t);
        }

        log.debug("Completed alertAction delete");
    }

    /**
     * Retrieve a alertAction by identifier
     *
     * @param alertActionId AlertAction identifier
     * @return Retrieved alertAction
     */
    public AlertAction findById(Long alertActionId) {
        AlertAction alertAction = null;

        log.debug("Performing alertAction retrieve using id: " + alertActionId);

        try {
            alertAction = objectDao.findById(alertActionId, AlertAction.class);
        }
        catch (Throwable t) {
            log.error("Failure during object findById", t);
        }

        log.debug("Completed alertAction retrieve by id");

        return alertAction;
    }

    /**
     * Retrieves all alertActions
     *
     * @return All alertAction records
     */
    @SuppressWarnings("unchecked")
    public List<AlertAction> findAll() {
        List<AlertAction> alertActions = null;

        log.debug("Performing retrieve of all alertActions");

        try {
            alertActions = objectDao.findAll(AlertAction.class);
        }
        catch (Throwable t) {
            log.error("Failure during object findAll", t);
        }

        log.debug("Completed alertAction retrieve all");

        return alertActions;
    }

}

