/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gov.hhs.fha.nhinc.addrbookmgr;

import gov.hhs.fha.nhinc.common.addrbook.ContactSummary;
import java.util.Comparator;

/**
 *
 * @author cmatser
 */
public class SummaryComparator
        implements Comparator<ContactSummary> {

    public int compare(ContactSummary arg0, ContactSummary arg1) {
        return arg0.getName().compareTo(arg1.getName());
    }

}
