/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gov.hhs.fha.nhinc.addrbookmgr;

import gov.hhs.fha.nhinc.addrbook.model.AddressItem;
import java.util.LinkedList;
import java.util.List;

/**
 * Base class for address agents.
 * 1) List the derived class identifiers here for easy identification.
 * 2) Use startAddrs as the master address list to start from
 * 3) Filter startAddrs for the addresses that pertain to the agent
 * 4) Populate the finishAddrs with the addresses that should be returned to the user
 *
 * @author cmatser
 */
public abstract class AddrBookAgent
        implements Runnable {

    /** List used for checking addresses. */
    protected List<AddressItem> startAddrs;

    /** List used for completed addresses. */
    protected List<AddressItem> finishAddrs;

    /** Agent class ids. */
    public static final String CLASS_ALL_PATIENT = "all-patient";
    public static final String CLASS_ALL_PROVIDER = "all-provider";
    public static final String CLASS_OPT_IN_PROVIDER = "opt-in-provider";
    public static final String CLASS_APPOINTMENT = "appointment";
    public static final String CLASS_PRIMARY_CARE = "primary-care";

    public void setAddressStart(List<AddressItem> startAddrs) {
        this.startAddrs = startAddrs;
    }

    public List<AddressItem> getAddressFinish() {
        if (finishAddrs == null) {
            finishAddrs = new LinkedList<AddressItem>();
        }
        return finishAddrs;
    }
}
