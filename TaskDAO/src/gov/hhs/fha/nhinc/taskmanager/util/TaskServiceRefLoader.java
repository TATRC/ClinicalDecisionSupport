/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gov.hhs.fha.nhinc.taskmanager.util;

import com.thoughtworks.xstream.XStream;
import gov.hhs.fha.nhinc.taskmanager.model.TaskServiceRef;
import gov.hhs.fha.nhinc.taskmanager.service.TaskService;
import java.io.FileReader;

/**
 * Simple task service ref loader for testing out tasks.
 *
 * @author cmatser
 */
public class TaskServiceRefLoader {

    public static void main(String args[]) {

        //Read xml file from command line
        if (args.length != 1) {
            System.out.println("Usage: java " +
                "TaskServiceRefLoader <file-name>");
            System.exit(1);
        }

        try {
            //Setup XStream
            XStream xstream = new XStream();
            xstream.alias("TaskServiceRef", TaskServiceRef.class);

            //Get ServiceRef out of file
            TaskServiceRef svc = (TaskServiceRef) xstream.fromXML(new FileReader(args[0]));

            //Save task
            TaskService taskService = new TaskService();
            taskService.saveServiceRef(svc);

            System.out.println("Task service ref saved, id: " + svc.getTaskServiceRefId());
        }
        catch (Throwable t) {
            t.printStackTrace();
        }
    }

}
