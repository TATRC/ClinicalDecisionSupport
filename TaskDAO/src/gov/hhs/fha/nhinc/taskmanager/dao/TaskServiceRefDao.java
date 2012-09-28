/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gov.hhs.fha.nhinc.taskmanager.dao;

import gov.hhs.fha.nhinc.taskmanager.model.TaskServiceRef;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author cmatser
 */
public class TaskServiceRefDao {

    ObjectDao<TaskServiceRef> objectDao = new ObjectDao();

    Log log = LogFactory.getLog(TaskServiceRefDao.class);

    /**
     * Save a record to the database.
     * Insert if id is null. Update otherwise.
     *
     * @param service TaskServiceRef object to save.
     */
    public void save(TaskServiceRef service) {
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
     * @param service TaskServiceRef to delete
     */
    public void delete(TaskServiceRef service) {
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
     * @param taskServiceRefId TaskServiceRef identifier
     * @return Retrieved service
     */
    public TaskServiceRef findById(Long taskServiceRefId) {
        TaskServiceRef service = null;

        log.debug("Performing service retrieve using id: " + taskServiceRefId);

        try {
            service = objectDao.findById(taskServiceRefId, TaskServiceRef.class);
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
    public List<TaskServiceRef> findAll() {
        List<TaskServiceRef> services = null;

        log.debug("Performing retrieve of all services");

        try {
            services = objectDao.findAll(TaskServiceRef.class);
        }
        catch (Throwable t) {
            log.error("Failure during object findAll", t);
        }

        log.debug("Completed service retrieve all");

        return services;
    }

}

