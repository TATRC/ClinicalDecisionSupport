/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gov.hhs.fha.nhinc.taskmanager;

import java.util.GregorianCalendar;

/**
 * JMS message for disease registry actions.
 *
 * @author cmatser
 */
public class DiseaseRegistryMessage extends TaskMessage
        implements java.io.Serializable {

    private boolean actionRegister;
    private String patientId;
    private String patientName;
    private GregorianCalendar patientDOB;
    private String diseaseType;

    public boolean isActionRegister() {
        return actionRegister;
    }

    public void setActionRegister(boolean actionRegister) {
        this.actionRegister = actionRegister;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public GregorianCalendar getPatientDOB() {
        return patientDOB;
    }

    public void setPatientDOB(GregorianCalendar patientDOB) {
        this.patientDOB = patientDOB;
    }

    public String getDiseaseType() {
        return diseaseType;
    }

    public void setDiseaseType(String diseaseType) {
        this.diseaseType = diseaseType;
    }

}