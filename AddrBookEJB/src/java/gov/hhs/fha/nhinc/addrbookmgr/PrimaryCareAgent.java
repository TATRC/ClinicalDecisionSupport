/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gov.hhs.fha.nhinc.addrbookmgr;

import gov.hhs.fha.nhinc.addrbook.model.AddressItem;

/**
 * Address agent for patient's primary care physician
 *
 * @author cmatser
 */
public class PrimaryCareAgent extends AddrBookAgent {

    public void run() {
        //Find the contacts for my class type
        for (AddressItem addr : startAddrs) {
            if (!CLASS_PRIMARY_CARE.equals(addr.getClassId())) {
                continue;
            }

            //Appointment service is not ready.  For now, just add them to the result
            getAddressFinish().add(addr);
        }
    }

}
