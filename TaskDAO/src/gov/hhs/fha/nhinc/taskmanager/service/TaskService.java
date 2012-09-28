/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gov.hhs.fha.nhinc.taskmanager.service;

import gov.hhs.fha.nhinc.taskmanager.dao.SpecificationDao;
import gov.hhs.fha.nhinc.taskmanager.dao.TaskServiceRefDao;
import gov.hhs.fha.nhinc.taskmanager.dao.TaskTypeDao;
import gov.hhs.fha.nhinc.taskmanager.model.Specification;
import gov.hhs.fha.nhinc.taskmanager.model.TaskServiceRef;
import gov.hhs.fha.nhinc.taskmanager.model.TaskType;
import java.util.List;
import java.util.Set;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author cmatser
 */
public class TaskService {

    private static Log log = LogFactory.getLog(TaskService.class);

    /**
     * Save a task record.
     *
     * @param task Task object to save.
     */
    public void saveTask(TaskType task)
    {
        log.debug("Saving a task");

        if (task != null)
        {
            if (task.getTaskTypeId() != null)
            {
                if(log.isDebugEnabled())
                {
                    log.debug("Performing an update for task: " + task.getTaskTypeId());
                }

                TaskType eTask = getTask(task.getTaskTypeId());
                if (eTask != null)
                {
                    // Delete existing specifications
                    Set<Specification> specs = eTask.getSpecifications();
                    if ((specs != null) && !specs.isEmpty())
                    {
                        SpecificationDao specDao = new SpecificationDao();
                        for (Specification spec : specs)
                        {
                            specDao.delete(spec);
                            spec.setSpecificationId(null);
                        }
                    }

                    // Reset specification identifiers
                    specs = task.getSpecifications();
                    if ((specs != null) && !specs.isEmpty())
                    {
                        for (Specification spec : specs)
                        {
                                spec.setSpecificationId(null);
                        }
                    }
                }
            }
            else
            {
                log.debug("Performing an insert");
            }

        }

        TaskTypeDao dao = new TaskTypeDao();
        dao.save(task);
    }

    /**
     * Delete a task
     *
     * @param task TaskType to delete
     * @throws TaskServiceException
     */
    public void deleteTask(TaskType task) throws TaskServiceException
    {
        log.debug("Deleting a task");
        TaskTypeDao dao = new TaskTypeDao();

        if (task == null)
        {
            throw new TaskServiceException("Task to delete was null");
        }

        dao.delete(task);
    }

    /**
     * Retrieve a task by identifier
     *
     * @param taskTypeId Task identifier
     * @return Retrieved task
     */
    public TaskType getTask(Long taskTypeId)
    {
        TaskTypeDao dao = new TaskTypeDao();
        return dao.findById(taskTypeId);
    }

    /**
     * Retrieves all tasks
     *
     * @return All task records
     */
    public List<TaskType> getAllTasks()
    {
        TaskTypeDao dao = new TaskTypeDao();
        return dao.findAll();
    }

    /**
     * Save a service ref record.
     *
     * @param serviceRef object to save.
     */
    public void saveServiceRef(TaskServiceRef serviceRef)
    {
        log.debug("Saving a task service ref");

        if (serviceRef != null)
        {
            if (serviceRef.getTaskServiceRefId() != null)
            {
                log.debug("Performing an update for service ref: " + serviceRef.getTaskServiceRefId());
            }
            else
            {
                log.debug("Performing an insert");
            }
        }

        TaskServiceRefDao dao = new TaskServiceRefDao();
        dao.save(serviceRef);
    }

    /**
     * Delete a service ref
     *
     * @param serviceRef record to delete
     * @throws TaskServiceException
     */
    public void deleteServiceRef(TaskServiceRef serviceRef) throws TaskServiceException
    {
        log.debug("Deleting a service ref");
        TaskServiceRefDao dao = new TaskServiceRefDao();

        if (serviceRef == null)
        {
            throw new TaskServiceException("Service ref to delete was null");
        }

        dao.delete(serviceRef);
    }

    /**
     * Retrieve a service ref by identifier
     *
     * @param taskServiceRefId identifier
     * @return Retrieved record
     */
    public TaskServiceRef getServiceRef(Long taskServiceRefId)
    {
        TaskServiceRefDao dao = new TaskServiceRefDao();
        return dao.findById(taskServiceRefId);
    }

    /**
     * Retrieves all service refs
     *
     * @return All records
     */
    public List<TaskServiceRef> getAllServiceRefs()
    {
        TaskServiceRefDao dao = new TaskServiceRefDao();
        return dao.findAll();
    }

}
