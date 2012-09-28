/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gov.hhs.fha.nhinc.alertmanager.dao;

import gov.hhs.fha.nhinc.alertmanager.model.AlertSpec;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author cmatser
 */
public class AlertSpecDao {

    ObjectDao<AlertSpec> objectDao = new ObjectDao();

    Log log = LogFactory.getLog(AlertSpecDao.class);

    /**
     * Save a record to the database.
     * Insert if id is null. Update otherwise.
     *
     * @param alertSpec AlertSpec object to save.
     */
    public void save(AlertSpec alertSpec) {
        log.debug("Performing alertSpec save");

        try {
            objectDao.save(alertSpec);
        }
        catch (Throwable t) {
            log.error("Failure during object save.", t);
        }

        log.debug("Completed alertSpec save");
    }

    /**
     * Delete a alertSpec
     *
     * @param alertSpec AlertSpec to delete
     */
    public void delete(AlertSpec alertSpec) {
        log.debug("Performing alertSpec delete");

        try {
            objectDao.delete(alertSpec);
        }
        catch (Throwable t) {
            log.error("Failure during object delete.", t);
        }

        log.debug("Completed alertSpec delete");
    }

    /**
     * Retrieve a alertSpec by identifier
     *
     * @param alertSpecId AlertSpec identifier
     * @return Retrieved alertSpec
     */
    public AlertSpec findById(Long alertSpecId) {
        AlertSpec alertSpec = null;

        log.debug("Performing alertSpec retrieve using id: " + alertSpecId);

        try {
            alertSpec = objectDao.findById(alertSpecId, AlertSpec.class);
        }
        catch (Throwable t) {
            log.error("Failure during object findById", t);
        }

        log.debug("Completed alertSpec retrieve by id");

        return alertSpec;
    }

    /**
     * Retrieves all alertSpecs
     *
     * @return All alertSpec records
     */
    @SuppressWarnings("unchecked")
    public List<AlertSpec> findAll() {
        List<AlertSpec> alertSpecs = null;

        log.debug("Performing retrieve of all alertSpecs");

        try {
            alertSpecs = objectDao.findAll(AlertSpec.class);
        }
        catch (Throwable t) {
            log.error("Failure during object findAll", t);
        }

        log.debug("Completed alertSpec retrieve all");

        return alertSpecs;
    }

}

