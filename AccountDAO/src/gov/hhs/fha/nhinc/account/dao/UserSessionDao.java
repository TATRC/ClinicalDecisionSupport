/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gov.hhs.fha.nhinc.account.dao;

import gov.hhs.fha.nhinc.account.model.UserSession;
import java.util.Date;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author nhin
 */
public class UserSessionDao {

    ObjectDao<UserSession> objectDao = new ObjectDao();
    Log log = LogFactory.getLog(UserSessionDao.class);

    public void save(UserSession session) {
        log.debug("Performing UserSession item save");

        try {
            //Update date
            session.setLoginTime(new Date());

            objectDao.save(session);
        } catch (Throwable t) {
            log.error("Failure during object save.", t);
        }

        log.debug("Completed user session save");
    }

    public void delete(UserSession session) {
        log.debug("Performing user session delete");

        try {
            objectDao.delete(session);
        } catch (Throwable t) {
            log.error("Failure during user session delete.", t);
        }

        log.debug("Completed user session delete");
    }

    public UserSession findById(String userSessionId) {
        UserSession userSession = null;

        log.debug("Performing user session retrieve using id: " + userSessionId);

        try {
            userSession = objectDao.findById(userSessionId, UserSession.class);
        } catch (Throwable t) {
            log.error("Failure during user session findById", t);
        }

        log.debug("Completed user session retrieve by id");

        return userSession;
    }

}
