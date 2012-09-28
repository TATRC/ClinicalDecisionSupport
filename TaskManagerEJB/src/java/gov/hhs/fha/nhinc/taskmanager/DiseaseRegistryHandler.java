/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gov.hhs.fha.nhinc.taskmanager;

import gov.hhs.fha.nhinc.taskmanager.model.TaskServiceRef;
import gov.hhs.fha.nhinc.taskmanager.model.TaskType;
import javax.xml.datatype.DatatypeFactory;
import mil.navy.med.dzreg.types.AckType;
import mil.navy.med.dzreg.types.PersonType;
import mil.navy.med.dzreg.types.RegisterPersonRequestType;
import mil.navy.med.dzreg.types.RegistryType;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Handle Disease Registry task events.  Register/Unregister patients
 * for a specified disease.
 *
 * @author cmatser
 */
public class DiseaseRegistryHandler {

    /** Disease Registry data provider. */
    public static final String DISEASE_REG_DATA_SOURCE = "KMR";

    /** Failure code. */
    public static final String RESPONSE_FAILURE = "-1";

    /** Logging. */
    private static Log log = LogFactory.getLog(DiseaseRegistryHandler.class);

    /**
     * Process task.
     */
    public void handleMessage(String taskTicket, TaskServiceRef serviceRef, TaskMessage taskMessage, TaskType task) {
        DiseaseRegistryMessage regMessage = null;

        log.debug("Handling Disease Registry task.");

        if (taskMessage instanceof DiseaseRegistryMessage) {
            regMessage = (DiseaseRegistryMessage) taskMessage;
        }

        if (regMessage == null) {
            log.error("Error, invalid disease registry task for ticket: " + taskTicket);
            return;
        }

        try {
            //Setup call to disease registry
            AckType result = null;
            RegisterPersonRequestType params = new RegisterPersonRequestType();
            params.setDataSource(DISEASE_REG_DATA_SOURCE);
            PersonType person = new PersonType();
            person.setId(Integer.parseInt(regMessage.getPatientId()));
            person.setName(regMessage.getPatientName());
            if (regMessage.getPatientDOB() != null) {
                person.setDateOfBirth(DatatypeFactory.newInstance().newXMLGregorianCalendar(regMessage.getPatientDOB()));
            }
            person.setDataSource(DISEASE_REG_DATA_SOURCE);
            params.setPerson(person);
            RegistryType type = new RegistryType();
            type.setRegistryId(Integer.parseInt(regMessage.getDiseaseType()));
            params.getRegistry().add(type);

            //Perform appropriate action
            if (regMessage.isActionRegister()) {
                result = registerPt(serviceRef.getLocation(), params);
            }
            else {
                result = unregisterPt(serviceRef.getLocation(), params);
            }

            log.debug("Task ticket: " + taskTicket
                + ". Disease Registry result: " + result.getResponseCode() + ","
                + result.getDetectedIssueText());
        }
        catch (Exception e) {
            log.error("Disease registry failure for task ticket: " + taskTicket, e);
        }
    }

    /**
     * Makes call to disease registry service to register a patient.
     * 
     * @param endpoint
     * @param params
     * @return
     */
    private AckType registerPt(String endpoint, RegisterPersonRequestType params) {
        AckType result = null;

        try { // Call Web Service Operation
            mil.navy.med.dzreg.service.RegistriesService service = new mil.navy.med.dzreg.service.RegistriesService();
            mil.navy.med.dzreg.service.RegistriesServicePortType port = service.getRegistriesServicePort();
            ((javax.xml.ws.BindingProvider) port).getRequestContext().put(
                javax.xml.ws.BindingProvider.ENDPOINT_ADDRESS_PROPERTY,
                endpoint);
            result = port.register(params);
        } catch (Exception ex) {
            result = new AckType();
            result.setResponseCode(RESPONSE_FAILURE);
            result.setDetectedIssueText(ex.getMessage());
        }

        return result;
    }

    /**
     * Makes call to disease registry service to un-register a patient.
     *
     * @param endpoint
     * @param params
     * @return
     */
    private AckType unregisterPt(String endpoint, RegisterPersonRequestType params) {
        AckType result = null;

        try { // Call Web Service Operation
            mil.navy.med.dzreg.service.RegistriesService service = new mil.navy.med.dzreg.service.RegistriesService();
            mil.navy.med.dzreg.service.RegistriesServicePortType port = service.getRegistriesServicePort();
            ((javax.xml.ws.BindingProvider) port).getRequestContext().put(
                javax.xml.ws.BindingProvider.ENDPOINT_ADDRESS_PROPERTY,
                endpoint);
            result = port.unregister(params);
        } catch (Exception ex) {
            result = new AckType();
            result.setResponseCode(RESPONSE_FAILURE);
            result.setDetectedIssueText(ex.getMessage());
        }

        return result;
    }
}