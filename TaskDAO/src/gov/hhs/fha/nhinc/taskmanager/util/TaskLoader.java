/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gov.hhs.fha.nhinc.taskmanager.util;

import com.thoughtworks.xstream.XStream;
import gov.hhs.fha.nhinc.taskmanager.model.Specification;
import gov.hhs.fha.nhinc.taskmanager.model.TaskType;
import gov.hhs.fha.nhinc.taskmanager.service.TaskService;
import java.io.FileReader;

/**
 * Simple task loader for testing out tasks.
 *
 * @author cmatser
 */
public class TaskLoader {

    public static void main(String args[]) {

        //Read xml file from command line
        if (args.length != 1) {
            System.out.println("Usage: java " +
                "TaskLoader <file-name>");
            System.exit(1);
        }

        try {
            //Setup XStream
            XStream xstream = new XStream();
            xstream.alias("TaskType", TaskType.class);
            xstream.alias("Specification", Specification.class);

            //Get Task out of file
            TaskType task = (TaskType) xstream.fromXML(new FileReader(args[0]));

            //Save task
            TaskService taskService = new TaskService();
            taskService.saveTask(task);

            System.out.println("Task saved, id: " + task.getTaskTypeId());
        }
        catch (Throwable t) {
            t.printStackTrace();
        }
    }

}
