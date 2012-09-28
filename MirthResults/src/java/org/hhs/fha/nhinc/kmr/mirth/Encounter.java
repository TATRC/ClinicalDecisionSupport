/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.hhs.fha.nhinc.kmr.mirth;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Duane DeCouteau
 */
@Entity
@Table(name = "encounter")
@NamedQueries({@NamedQuery(name = "Encounter.findAll", query = "SELECT e FROM Encounter e"), @NamedQuery(name = "Encounter.findByClinicalItemKey", query = "SELECT e FROM Encounter e WHERE e.clinicalItemKey = :clinicalItemKey"), @NamedQuery(name = "Encounter.findBySubjectClass", query = "SELECT e FROM Encounter e WHERE e.subjectClass = :subjectClass"), @NamedQuery(name = "Encounter.findByAdmissionType", query = "SELECT e FROM Encounter e WHERE e.admissionType = :admissionType"), @NamedQuery(name = "Encounter.findByAssignedLocationKey", query = "SELECT e FROM Encounter e WHERE e.assignedLocationKey = :assignedLocationKey"), @NamedQuery(name = "Encounter.findByPriorLocationKey", query = "SELECT e FROM Encounter e WHERE e.priorLocationKey = :priorLocationKey"), @NamedQuery(name = "Encounter.findBySubjectType", query = "SELECT e FROM Encounter e WHERE e.subjectType = :subjectType"), @NamedQuery(name = "Encounter.findByPreadminNumber", query = "SELECT e FROM Encounter e WHERE e.preadminNumber = :preadminNumber"), @NamedQuery(name = "Encounter.findByAdmitSource", query = "SELECT e FROM Encounter e WHERE e.admitSource = :admitSource"), @NamedQuery(name = "Encounter.findByAmbulatoryStatus", query = "SELECT e FROM Encounter e WHERE e.ambulatoryStatus = :ambulatoryStatus"), @NamedQuery(name = "Encounter.findByReason", query = "SELECT e FROM Encounter e WHERE e.reason = :reason")})
public class Encounter implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "clinical_item_key")
    private BigDecimal clinicalItemKey;
    @Column(name = "subject_class")
    private String subjectClass;
    @Column(name = "admission_type")
    private String admissionType;
    @Column(name = "assigned_location_key")
    private BigDecimal assignedLocationKey;
    @Column(name = "prior_location_key")
    private BigDecimal priorLocationKey;
    @Column(name = "subject_type")
    private String subjectType;
    @Column(name = "preadmin_number")
    private String preadminNumber;
    @Column(name = "admit_source")
    private String admitSource;
    @Column(name = "ambulatory_status")
    private String ambulatoryStatus;
    @Column(name = "reason")
    private String reason;

    public Encounter() {
    }

    public Encounter(BigDecimal clinicalItemKey) {
        this.clinicalItemKey = clinicalItemKey;
    }

    public BigDecimal getClinicalItemKey() {
        return clinicalItemKey;
    }

    public void setClinicalItemKey(BigDecimal clinicalItemKey) {
        this.clinicalItemKey = clinicalItemKey;
    }

    public String getSubjectClass() {
        return subjectClass;
    }

    public void setSubjectClass(String subjectClass) {
        this.subjectClass = subjectClass;
    }

    public String getAdmissionType() {
        return admissionType;
    }

    public void setAdmissionType(String admissionType) {
        this.admissionType = admissionType;
    }

    public BigDecimal getAssignedLocationKey() {
        return assignedLocationKey;
    }

    public void setAssignedLocationKey(BigDecimal assignedLocationKey) {
        this.assignedLocationKey = assignedLocationKey;
    }

    public BigDecimal getPriorLocationKey() {
        return priorLocationKey;
    }

    public void setPriorLocationKey(BigDecimal priorLocationKey) {
        this.priorLocationKey = priorLocationKey;
    }

    public String getSubjectType() {
        return subjectType;
    }

    public void setSubjectType(String subjectType) {
        this.subjectType = subjectType;
    }

    public String getPreadminNumber() {
        return preadminNumber;
    }

    public void setPreadminNumber(String preadminNumber) {
        this.preadminNumber = preadminNumber;
    }

    public String getAdmitSource() {
        return admitSource;
    }

    public void setAdmitSource(String admitSource) {
        this.admitSource = admitSource;
    }

    public String getAmbulatoryStatus() {
        return ambulatoryStatus;
    }

    public void setAmbulatoryStatus(String ambulatoryStatus) {
        this.ambulatoryStatus = ambulatoryStatus;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (clinicalItemKey != null ? clinicalItemKey.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Encounter)) {
            return false;
        }
        Encounter other = (Encounter) object;
        if ((this.clinicalItemKey == null && other.clinicalItemKey != null) || (this.clinicalItemKey != null && !this.clinicalItemKey.equals(other.clinicalItemKey))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.hhs.fha.nhinc.kmr.mirth.Encounter[clinicalItemKey=" + clinicalItemKey + "]";
    }

}
