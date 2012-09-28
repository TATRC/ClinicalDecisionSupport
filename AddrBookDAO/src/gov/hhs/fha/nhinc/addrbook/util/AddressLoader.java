/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gov.hhs.fha.nhinc.addrbook.util;

import com.thoughtworks.xstream.XStream;
import gov.hhs.fha.nhinc.addrbook.model.AddressItem;
import gov.hhs.fha.nhinc.addrbook.service.AddrBookService;
import java.io.FileReader;
import java.util.Date;

/**
 * Simple loader for testing
 *
 * @author cmatser
 */
public class AddressLoader {

    public static void main(String args[]) {

        //Setup XStream
        XStream xstream = new XStream();
        xstream.alias("AddressItem", AddressItem.class);

        //Read xml file from command line
        if (args.length != 1) {
            System.out.println("Usage: java AddressLoader <file-name>");

            AddressItem addr = new AddressItem();
            addr.setAddressId(1L);
            addr.setClassId("classId-123");
            addr.setContactId("contactId-123");
            addr.setLastUpdated(new Date());
            addr.setName("name");
            addr.setUserId("userId-123");
            System.out.println("Eg:");
            System.out.println(xstream.toXML(addr));

            System.exit(1);
        }

        try {
            //Get object out of file
            AddressItem addr = (AddressItem) xstream.fromXML(new FileReader(args[0]));

            //Save object
            AddrBookService addrBookService = new AddrBookService();
            addrBookService.saveAddress(addr);

            System.out.println("Address saved, id: " + addr.getAddressId());
        }
        catch (Throwable t) {
            t.printStackTrace();
        }
    }

}
