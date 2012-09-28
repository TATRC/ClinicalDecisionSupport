/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gov.hhs.fha.nhinc.addrbookmgr;

import gov.hhs.fha.nhinc.addrbook.model.AddressItem;

/**
 * Address agent for providers who have opted-in to receiving emails
 * from this user.
 *
 * @author cmatser
 */
public class OptInProviderAgent extends AddrBookAgent {

    public void run() {
        //Find the contacts for my class type
        for (AddressItem addr : startAddrs) {
            if (!CLASS_OPT_IN_PROVIDER.equals(addr.getClassId())) {
                continue;
            }

            //Opt-in providers are easy.  If they're here, they'ved opted in.
            getAddressFinish().add(addr);
        }
    }

}
