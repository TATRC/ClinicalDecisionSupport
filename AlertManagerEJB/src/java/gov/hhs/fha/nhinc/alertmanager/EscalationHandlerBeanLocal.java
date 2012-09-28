/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gov.hhs.fha.nhinc.alertmanager;

import javax.ejb.Local;
import javax.ejb.Timer;

/**
 *
 * @author cmatser
 */
@Local
public interface EscalationHandlerBeanLocal {

    void initOneTimeTimer(String ticket, int minutes, String actionMessage, boolean isManual, String userId, String userName, boolean isUserProvider);

    void processEscalation(Timer timer);
    
}
