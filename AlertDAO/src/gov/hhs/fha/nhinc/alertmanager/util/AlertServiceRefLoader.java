/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gov.hhs.fha.nhinc.alertmanager.util;

import com.thoughtworks.xstream.XStream;
import gov.hhs.fha.nhinc.alertmanager.model.AlertServiceRef;
import gov.hhs.fha.nhinc.alertmanager.service.AlertService;
import java.io.FileReader;

/**
 * Simple alert service ref loader for testing out alerts.
 *
 * @author cmatser
 */
public class AlertServiceRefLoader {

    public static void main(String args[]) {

        //Read xml file from command line
        if (args.length != 1) {
            System.out.println("Usage: java " +
                "AlertServiceRefLoader <file-name>");
            System.exit(1);
        }

        try {
            //Setup XStream
            XStream xstream = new XStream();
            xstream.alias("AlertServiceRef", AlertServiceRef.class);

            //Get ServiceRef out of file
            AlertServiceRef svc = (AlertServiceRef) xstream.fromXML(new FileReader(args[0]));

            //Save ServiceRef
            AlertService alertService = new AlertService();
            alertService.saveServiceRef(svc);

            System.out.println("Alert Service Ref saved, id: " + svc.getAlertServiceRefId());
        }
        catch (Throwable t) {
            t.printStackTrace();
        }
    }

}
