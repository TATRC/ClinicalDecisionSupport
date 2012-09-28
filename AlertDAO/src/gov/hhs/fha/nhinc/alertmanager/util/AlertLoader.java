/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gov.hhs.fha.nhinc.alertmanager.util;

import com.thoughtworks.xstream.XStream;
import gov.hhs.fha.nhinc.alertmanager.model.AlertSpec;
import gov.hhs.fha.nhinc.alertmanager.model.AlertType;
import gov.hhs.fha.nhinc.alertmanager.service.AlertService;
import java.io.FileReader;

/**
 * Simple alert loader for testing out alerts.
 *
 * @author cmatser
 */
public class AlertLoader {

    public static void main(String args[]) {

        //Read xml file from command line
        if (args.length != 1) {
            System.out.println("Usage: java " +
                "AlertLoader <file-name>");
            System.exit(1);
        }

        try {
            //Setup XStream
            XStream xstream = new XStream();
            xstream.alias("AlertType", AlertType.class);
            xstream.alias("AlertSpec", AlertSpec.class);

            //Get Alert out of file
            AlertType alert = (AlertType) xstream.fromXML(new FileReader(args[0]));

            //Save alert
            AlertService alertService = new AlertService();
            alertService.saveAlert(alert);

            System.out.println("Alert saved, id: " + alert.getAlertTypeId());
        }
        catch (Throwable t) {
            t.printStackTrace();
        }
    }

}
