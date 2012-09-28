/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gov.hhs.fha.nhinc.alertmanager.dao;

import gov.hhs.fha.nhinc.alertmanager.model.AlertServiceRef;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author cmatser
 */
public class AlertServiceRefDao {

    ObjectDao<AlertServiceRef> objectDao = new ObjectDao();

    Log log = LogFactory.getLog(AlertServiceRefDao.class);

    /**
     * Save a record to the database.
     * Insert if id is null. Update otherwise.
     *
     * @param service AlertServiceRef object to save.
     */
    public void save(AlertServiceRef service) {
        log.debug("Performing service save");

        try {
            objectDao.save(service);
        }
        catch (Throwable t) {
            log.error("Failure during object save.", t);
        }

        log.debug("Completed service save");
    }

    /**
     * Delete a service
     *
     * @param service AlertServiceRef to delete
     */
    public void delete(AlertServiceRef service) {
        log.debug("Performing service delete");

        try {
            objectDao.delete(service);
        }
        catch (Throwable t) {
            log.error("Failure during object delete.", t);
        }

        log.debug("Completed service delete");
    }

    /**
     * Retrieve a service by identifier
     *
     * @param alertServiceRefId AlertServiceRef identifier
     * @return Retrieved service
     */
    public AlertServiceRef findById(Long alertServiceRefId) {
        AlertServiceRef service = null;

        log.debug("Performing service retrieve using id: " + alertServiceRefId);

        try {
            service = objectDao.findById(alertServiceRefId, AlertServiceRef.class);
        }
        catch (Throwable t) {
            log.error("Failure during object findById", t);
        }

        log.debug("Completed service retrieve by id");

        return service;
    }

    /**
     * Retrieves all service
     *
     * @return All service records
     */
    @SuppressWarnings("unchecked")
    public List<AlertServiceRef> findAll() {
        List<AlertServiceRef> services = null;

        log.debug("Performing retrieve of all services");

        try {
            services = objectDao.findAll(AlertServiceRef.class);
        }
        catch (Throwable t) {
            log.error("Failure during object findAll", t);
        }

        log.debug("Completed service retrieve all");

        return services;
    }

}

