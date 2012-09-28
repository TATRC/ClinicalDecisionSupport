package gov.hhs.fha.nhinc.kmr.dss;

import gov.hhs.fha.nhinc.kmr.KnowledgeSession;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

import gov.hhs.fha.nhinc.kmr.patientcohort.PatientCohort;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author Steven Clark
 */
@WebService()
public class Session {

  private static Log log = LogFactory.getLog(Session.class);
  private FactHelper factHelper = null;

  @Override
  protected void finalize() throws Throwable {
    factHelper = null;
  }

  private FactHelper getFactHelper() {
    if (factHelper == null) {
      factHelper = new FactHelper();
    }
    return factHelper;
  }

  /**
   * Web service operation
   */
  @WebMethod(operationName = "initKnowledgeSession")
  public Boolean initKnowledgeSession(@WebParam(name = "session") KnowledgeSession session) {
    initSession(session);
    return true;
  }

  private synchronized void initSession(KnowledgeSession session) {
    if ((session != null) && (session.getPatientId() != null)) {
      // Get the patients cohort data
      PatientCohort cohort = getFactHelper().getPatientCohort(session.getPatientId());
      if (cohort != null) {
        DSSSession dssSession = new DSSSession(session);
        // initialize fact handler thread
        FactHandler fh = new FactHandler(dssSession);
        fh.start();
        log.info("initSession: Created a new DSS KnowledgeSession session for patient " + session.getPatientId());
      } else {
        log.error("initSession: Patient " + session.getPatientId() + " is not being processed.");
      }
    } else {
      log.error("initSession: Invalid session argument");
    }
  }
}
