/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gov.hhs.fha.nhinc.taskmanager.test;

import gov.hhs.fha.nhinc.taskmanager.model.Specification;
import gov.hhs.fha.nhinc.taskmanager.model.TaskServiceRef;
import gov.hhs.fha.nhinc.taskmanager.model.TaskType;
import gov.hhs.fha.nhinc.taskmanager.service.TaskService;
import java.util.HashSet;
import java.util.Set;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author cmatser
 */
public class TaskServiceTest {

    public TaskServiceTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    @Test
    public void testTaskService() {
        System.out.println("Start testTaskService");

        try
        {
            TaskService taskService = new TaskService();
            TaskType task = null;

            // Insert task
            task = new TaskType();
            Long taskId = null;
            String taskName = "Task Name";
            String taskDesc = "Task Description";

            task.setTaskTypeId(taskId);
            task.setName(taskName);
            task.setDescription(taskDesc);

            Set<Specification> specs = new HashSet<Specification>();
            Specification spec1 = new Specification();
            String specName1 = "specName1";
            String specValue1 = "specValue1";
            spec1.setTaskType(task);
            spec1.setName(specName1);
            spec1.setValue(specValue1);
            specs.add(spec1);
            Specification spec2 = new Specification();
            String specName2 = "specName2";
            String specValue2 = "specValue2";
            spec2.setTaskType(task);
            spec2.setName(specName2);
            spec2.setValue(specValue2);
            specs.add(spec2);
            task.setSpecifications(specs);

            taskService.saveTask(task);
            taskId = task.getTaskTypeId();

            // Retrieve insert
            TaskType retrieved = taskService.getTask(taskId);
            assertNotNull("Retrieved insert was null", retrieved);
            assertNotNull("After insert - id null", taskId);
            assertEquals("After insert - name", task.getName(), taskName);
            assertEquals("After insert - description", task.getDescription(), taskDesc);

            Set<Specification> retrievedSpecs = retrieved.getSpecifications();
            assertNotNull("Retrieved insert specifications was null", retrievedSpecs);
            assertEquals("After insert - specifications size", retrievedSpecs.size(), 2);


            // Update
            taskName = taskName + "updated";
            taskDesc = taskDesc + "updated";
            retrieved.setName(taskName);
            retrieved.setDescription(taskDesc);

            taskService.saveTask(retrieved);

            // Retrieve updated
            retrieved = taskService.getTask(taskId);
            assertNotNull("Retrieved update was null", retrieved);
            assertNotNull("After update - id null", taskId);
            assertEquals("After update - name", retrieved.getName(), taskName);
            assertEquals("After update - description", retrieved.getDescription(), taskDesc);

            retrievedSpecs = retrieved.getSpecifications();
            assertNotNull("Retrieved update specifications was null", retrievedSpecs);
            assertEquals("After update - specifications size", retrievedSpecs.size(), 2);

            // Delete
            taskService.deleteTask(retrieved);
            retrieved = taskService.getTask(taskId);
            assertNull("Task not null after delete", retrieved);

        }
        catch (Throwable t)
        {
            t.printStackTrace();
            fail("testTaskService: " + t.getMessage());
        }
        System.out.println("End testTaskService");
    }

    @Test
    public void testTaskServiceRefs() {
        System.out.println("Start testTaskServiceRefs");

        try
        {
            TaskService taskService = new TaskService();
            TaskServiceRef serviceRef = null;

            // Insert serviceRef
            serviceRef = new TaskServiceRef();
            Long svcId = null;
            String svcName = "Name";
            String svcDesc = "Description";
            String svcLoc = "Lococation";
            String svcType = "Type";
            String svcP1 = "Param 1";

            serviceRef.setTaskServiceRefId(svcId);
            serviceRef.setName(svcName);
            serviceRef.setDescription(svcDesc);
            serviceRef.setLocation(svcLoc);
            serviceRef.setType(svcType);
            serviceRef.setServiceParam1(svcP1);

            taskService.saveServiceRef(serviceRef);
            svcId = serviceRef.getTaskServiceRefId();

            // Retrieve insert
            TaskServiceRef retrieved = taskService.getServiceRef(svcId);
            assertNotNull("Retrieved insert was null", retrieved);
            assertNotNull("After insert - id null", svcId);
            assertEquals("After insert - name", serviceRef.getName(), svcName);
            assertEquals("After insert - description", serviceRef.getDescription(), svcDesc);
            assertEquals("After insert - location", serviceRef.getLocation(), svcLoc);
            assertEquals("After insert - type", serviceRef.getType(), svcType);
            assertEquals("After insert - param 1", serviceRef.getServiceParam1(), svcP1);

            // Update
            svcName = svcName + "updated";
            svcDesc = svcDesc + "updated";
            svcType = svcType + "updated";
            svcLoc = svcLoc + "updated";
            svcP1 = svcP1 + "updated";
            retrieved.setName(svcName);
            retrieved.setDescription(svcDesc);
            retrieved.setLocation(svcLoc);
            retrieved.setType(svcType);
            retrieved.setServiceParam1(svcP1);

            taskService.saveServiceRef(retrieved);

            // Retrieve updated
            retrieved = taskService.getServiceRef(svcId);
            assertNotNull("Retrieved update was null", retrieved);
            assertNotNull("After update - id null", svcId);
            assertEquals("After update - name", retrieved.getName(), svcName);
            assertEquals("After update - description", retrieved.getDescription(), svcDesc);
            assertEquals("After update - type", retrieved.getType(), svcType);
            assertEquals("After update - location", retrieved.getLocation(), svcLoc);
            assertEquals("After update - param 1", retrieved.getServiceParam1(), svcP1);

            // Delete
            taskService.deleteServiceRef(retrieved);
            retrieved = taskService.getServiceRef(svcId);
            assertNull("Task not null after delete", retrieved);

        }
        catch (Throwable t)
        {
            t.printStackTrace();
            fail("testTaskServiceRefs: " + t.getMessage());
        }
        System.out.println("End testTaskServiceRefs");
    }

}