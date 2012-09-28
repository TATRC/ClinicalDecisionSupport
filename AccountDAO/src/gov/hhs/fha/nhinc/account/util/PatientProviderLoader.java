/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gov.hhs.fha.nhinc.account.util;

import com.thoughtworks.xstream.XStream;
import gov.hhs.fha.nhinc.account.model.PatientProviderItem;
import gov.hhs.fha.nhinc.account.service.AccountService;
import java.io.FileReader;

/**
 * Simple loader for testing
 * @author Sushma
 */
public class PatientProviderLoader {

    public static void main(String args[]) {

        //Setup XStream
        XStream xstream = new XStream();
        xstream.alias("PatientProviderItem", PatientProviderItem.class);

        //Read xml file from command line
        if (args.length != 1) {
            System.out.println("Usage: java PatientProviderLoader <file-name>");

            PatientProviderItem ppi = new PatientProviderItem();
            ppi.setPatientProviderId(1L);
            ppi.setPatientId(2L);
            ppi.setProviderId("3");

            System.out.println("Eg:");
            System.out.println(xstream.toXML(ppi));

            System.exit(1);
        }

        try {
            //Get object out of file
            PatientProviderItem ppi = (PatientProviderItem) xstream.fromXML(new FileReader(args[0]));

            //Save object
            AccountService accountService = new AccountService();
            accountService.savePatientProvider(ppi);

            System.out.println("PatientProvider saved, id: " + ppi.getPatientProviderId());
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }
}
