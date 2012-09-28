/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gov.hhs.fha.nhinc.alertmanager.dao;

import gov.hhs.fha.nhinc.alertmanager.model.AlertStatus;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author Sushma
 */
public class AlertStatusDao {
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

    ObjectDao<AlertStatus> objectDao = new ObjectDao();

    Log log = LogFactory.getLog(AlertStatusDao.class);

    /**
     * Save a record to the database.
     * Insert if id is null. Update otherwise.
     *
     * @param alertStatus AlertStatus object to save.
     */
    public void save(AlertStatus alertStatus) {
        log.debug("Performing alertStatus save");

        try {
            objectDao.save(alertStatus);
        }
        catch (Throwable t) {
            log.error("Failure during object save.", t);
        }

        log.debug("Completed alertStatus save");
    }

    /**
     * Delete a alertStatus
     *
     * @param alertStatus AlertStatus to delete
     */
    public void delete(AlertStatus alertStatus) {
        log.debug("Performing alertStatus delete");

        try {
            objectDao.delete(alertStatus);
        }
        catch (Throwable t) {
            log.error("Failure during object delete.", t);
        }

        log.debug("Completed alertStatus delete");
    }

    /**
     * Retrieve a alertStatus by identifier
     *
     * @param alertStatusId AlertStatus identifier
     * @return Retrieved alertStatus
     */
    public AlertStatus findById(Long alertStatusId) {
        AlertStatus alertStatus = null;

        log.debug("Performing alertStatus retrieve using id: " + alertStatusId);

        try {
            alertStatus = objectDao.findById(alertStatusId, AlertStatus.class);
        }
        catch (Throwable t) {
            log.error("Failure during object findById", t);
        }

        log.debug("Completed alertStatus retrieve by id");

        return alertStatus;
    }

    /**
     * Retrieves all alertStatus
     *
     * @return All alertStatus records
     */
    @SuppressWarnings("unchecked")
    public List<AlertStatus> findAll() {
        List<AlertStatus> alertStatus = null;

        log.debug("Performing retrieve of all alertStatus");

        try {
            alertStatus = objectDao.findAll(AlertStatus.class);
        }
        catch (Throwable t) {
            log.error("Failure during object findAll", t);
        }

        log.debug("Completed alertStatus retrieve all");

        return alertStatus;
    }
    

}


