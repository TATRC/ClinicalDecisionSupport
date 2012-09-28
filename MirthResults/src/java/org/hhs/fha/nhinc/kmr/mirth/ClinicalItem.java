/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.hhs.fha.nhinc.kmr.mirth;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Duane DeCouteau
 */
@Entity
@Table(name = "clinical_item")
@NamedQueries({@NamedQuery(name = "ClinicalItem.findAll", query = "SELECT c FROM ClinicalItem c"), @NamedQuery(name = "ClinicalItem.findByClinicalItemKey", query = "SELECT c FROM ClinicalItem c WHERE c.clinicalItemKey = :clinicalItemKey"), @NamedQuery(name = "ClinicalItem.findByClinicalItemTypeId", query = "SELECT c FROM ClinicalItem c WHERE c.clinicalItemTypeId = :clinicalItemTypeId"), @NamedQuery(name = "ClinicalItem.findById", query = "SELECT c FROM ClinicalItem c WHERE c.id = :id"), @NamedQuery(name = "ClinicalItem.findByCodeKey", query = "SELECT c FROM ClinicalItem c WHERE c.codeKey = :codeKey"), @NamedQuery(name = "ClinicalItem.findByLabel", query = "SELECT c FROM ClinicalItem c WHERE c.label = :label"), @NamedQuery(name = "ClinicalItem.findByStartTime", query = "SELECT c FROM ClinicalItem c WHERE c.startTime = :startTime"), @NamedQuery(name = "ClinicalItem.findByEndTime", query = "SELECT c FROM ClinicalItem c WHERE c.endTime = :endTime"), @NamedQuery(name = "ClinicalItem.findByActivityTime", query = "SELECT c FROM ClinicalItem c WHERE c.activityTime = :activityTime"), @NamedQuery(name = "ClinicalItem.findByAvailabilityTime", query = "SELECT c FROM ClinicalItem c WHERE c.availabilityTime = :availabilityTime"), @NamedQuery(name = "ClinicalItem.findByNegationInd", query = "SELECT c FROM ClinicalItem c WHERE c.negationInd = :negationInd"), @NamedQuery(name = "ClinicalItem.findByArchived", query = "SELECT c FROM ClinicalItem c WHERE c.archived = :archived"), @NamedQuery(name = "ClinicalItem.findByFacilityKey", query = "SELECT c FROM ClinicalItem c WHERE c.facilityKey = :facilityKey"), @NamedQuery(name = "ClinicalItem.findBySourceFacilityKey", query = "SELECT c FROM ClinicalItem c WHERE c.sourceFacilityKey = :sourceFacilityKey"), @NamedQuery(name = "ClinicalItem.findBySubjectKey", query = "SELECT c FROM ClinicalItem c WHERE c.subjectKey = :subjectKey"), @NamedQuery(name = "ClinicalItem.findByConfidentialityCodeKey", query = "SELECT c FROM ClinicalItem c WHERE c.confidentialityCodeKey = :confidentialityCodeKey"), @NamedQuery(name = "ClinicalItem.findByInterpretationCodeKey", query = "SELECT c FROM ClinicalItem c WHERE c.interpretationCodeKey = :interpretationCodeKey"), @NamedQuery(name = "ClinicalItem.findByStatusCodeKey", query = "SELECT c FROM ClinicalItem c WHERE c.statusCodeKey = :statusCodeKey")})
public class ClinicalItem implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "clinical_item_key")
    private BigDecimal clinicalItemKey;
    @Basic(optional = false)
    @Column(name = "clinical_item_type_id")
    private String clinicalItemTypeId;
    @Basic(optional = false)
    @Column(name = "id")
    private String id;
    @Column(name = "code_key")
    private BigDecimal codeKey;
    @Column(name = "label")
    private String label;
    @Lob
    @Column(name = "description")
    private String description;
    @Lob
    @Column(name = "notes")
    private String notes;
    @Column(name = "start_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date startTime;
    @Column(name = "end_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date endTime;
    @Column(name = "activity_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date activityTime;
    @Column(name = "availability_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date availabilityTime;
    @Column(name = "negation_ind")
    private Short negationInd;
    @Column(name = "archived")
    private Short archived;
    @Basic(optional = false)
    @Column(name = "facility_key")
    private int facilityKey;
    @Column(name = "source_facility_key")
    private Integer sourceFacilityKey;
    @Basic(optional = false)
    @Column(name = "subject_key")
    private int subjectKey;
    @Column(name = "confidentiality_code_key")
    private BigDecimal confidentialityCodeKey;
    @Column(name = "interpretation_code_key")
    private BigDecimal interpretationCodeKey;
    @Column(name = "status_code_key")
    private BigDecimal statusCodeKey;

    public ClinicalItem() {
    }

    public ClinicalItem(BigDecimal clinicalItemKey) {
        this.clinicalItemKey = clinicalItemKey;
    }

    public ClinicalItem(BigDecimal clinicalItemKey, String clinicalItemTypeId, String id, int facilityKey, int subjectKey) {
        this.clinicalItemKey = clinicalItemKey;
        this.clinicalItemTypeId = clinicalItemTypeId;
        this.id = id;
        this.facilityKey = facilityKey;
        this.subjectKey = subjectKey;
    }

    public BigDecimal getClinicalItemKey() {
        return clinicalItemKey;
    }

    public void setClinicalItemKey(BigDecimal clinicalItemKey) {
        this.clinicalItemKey = clinicalItemKey;
    }

    public String getClinicalItemTypeId() {
        return clinicalItemTypeId;
    }

    public void setClinicalItemTypeId(String clinicalItemTypeId) {
        this.clinicalItemTypeId = clinicalItemTypeId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public BigDecimal getCodeKey() {
        return codeKey;
    }

    public void setCodeKey(BigDecimal codeKey) {
        this.codeKey = codeKey;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Date getActivityTime() {
        return activityTime;
    }

    public void setActivityTime(Date activityTime) {
        this.activityTime = activityTime;
    }

    public Date getAvailabilityTime() {
        return availabilityTime;
    }

    public void setAvailabilityTime(Date availabilityTime) {
        this.availabilityTime = availabilityTime;
    }

    public Short getNegationInd() {
        return negationInd;
    }

    public void setNegationInd(Short negationInd) {
        this.negationInd = negationInd;
    }

    public Short getArchived() {
        return archived;
    }

    public void setArchived(Short archived) {
        this.archived = archived;
    }

    public int getFacilityKey() {
        return facilityKey;
    }

    public void setFacilityKey(int facilityKey) {
        this.facilityKey = facilityKey;
    }

    public Integer getSourceFacilityKey() {
        return sourceFacilityKey;
    }

    public void setSourceFacilityKey(Integer sourceFacilityKey) {
        this.sourceFacilityKey = sourceFacilityKey;
    }

    public int getSubjectKey() {
        return subjectKey;
    }

    public void setSubjectKey(int subjectKey) {
        this.subjectKey = subjectKey;
    }

    public BigDecimal getConfidentialityCodeKey() {
        return confidentialityCodeKey;
    }

    public void setConfidentialityCodeKey(BigDecimal confidentialityCodeKey) {
        this.confidentialityCodeKey = confidentialityCodeKey;
    }

    public BigDecimal getInterpretationCodeKey() {
        return interpretationCodeKey;
    }

    public void setInterpretationCodeKey(BigDecimal interpretationCodeKey) {
        this.interpretationCodeKey = interpretationCodeKey;
    }

    public BigDecimal getStatusCodeKey() {
        return statusCodeKey;
    }

    public void setStatusCodeKey(BigDecimal statusCodeKey) {
        this.statusCodeKey = statusCodeKey;
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
        if (!(object instanceof ClinicalItem)) {
            return false;
        }
        ClinicalItem other = (ClinicalItem) object;
        if ((this.clinicalItemKey == null && other.clinicalItemKey != null) || (this.clinicalItemKey != null && !this.clinicalItemKey.equals(other.clinicalItemKey))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.hhs.fha.nhinc.kmr.mirth.ClinicalItem[clinicalItemKey=" + clinicalItemKey + "]";
    }

}
