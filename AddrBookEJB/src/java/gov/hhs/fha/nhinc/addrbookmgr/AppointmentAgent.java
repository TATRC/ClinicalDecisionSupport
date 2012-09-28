/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gov.hhs.fha.nhinc.addrbookmgr;

import gov.hhs.fha.nhinc.addrbook.model.AddressItem;

/**
 * Address agent for providers whom the patient has seen or is about to see.
 *
 * @author cmatser
 */
public class AppointmentAgent extends AddrBookAgent {

    public void run() {
        //Find the contacts for my class type
        for (AddressItem addr : startAddrs) {
            if (!CLASS_APPOINTMENT.equals(addr.getClassId())) {
                continue;
            }

            //Appointment service is not ready.  For now, just add them to the result
            getAddressFinish().add(addr);
        }
    }

}
