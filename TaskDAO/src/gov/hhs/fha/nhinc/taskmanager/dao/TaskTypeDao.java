/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gov.hhs.fha.nhinc.taskmanager.dao;

import gov.hhs.fha.nhinc.taskmanager.model.TaskType;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author cmatser
 */
public class TaskTypeDao {

    ObjectDao<TaskType> objectDao = new ObjectDao();

    Log log = LogFactory.getLog(TaskTypeDao.class);

    /**
     * Save a record to the database.
     * Insert if id is null. Update otherwise.
     *
     * @param task TaskType object to save.
     */
    public void save(TaskType task) {
        log.debug("Performing task save");

        try {
            objectDao.save(task);
        }
        catch (Throwable t) {
            log.error("Failure during object save.", t);
        }

        log.debug("Completed task save");
    }

    /**
     * Delete a task
     *
     * @param task TaskType to delete
     */
    public void delete(TaskType task) {
        log.debug("Performing task delete");

        try {
            objectDao.delete(task);
        }
        catch (Throwable t) {
            log.error("Failure during object delete.", t);
        }

        log.debug("Completed task delete");
    }

    /**
     * Retrieve a task by identifier
     *
     * @param taskTypeId TaskType identifier
     * @return Retrieved task
     */
    public TaskType findById(Long taskTypeId) {
        TaskType task = null;

        log.debug("Performing task retrieve using id: " + taskTypeId);

        try {
            task = objectDao.findById(taskTypeId, TaskType.class);
        }
        catch (Throwable t) {
            log.error("Failure during object findById", t);
        }

        log.debug("Completed task retrieve by id");

        return task;
    }

    /**
     * Retrieves all tasks
     *
     * @return All task records
     */
    @SuppressWarnings("unchecked")
    public List<TaskType> findAll() {
        List<TaskType> tasks = null;

        log.debug("Performing retrieve of all tasks");

        try {
            tasks = objectDao.findAll(TaskType.class);
        }
        catch (Throwable t) {
            log.error("Failure during object findAll", t);
        }

        log.debug("Completed task retrieve all");

        return tasks;
    }

}

