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
@Table(name = "clinical_item_facility")
@NamedQueries({@NamedQuery(name = "ClinicalItemFacility.findAll", query = "SELECT c FROM ClinicalItemFacility c"), @NamedQuery(name = "ClinicalItemFacility.findByClinicalItemFacilityKey", query = "SELECT c FROM ClinicalItemFacility c WHERE c.clinicalItemFacilityKey = :clinicalItemFacilityKey"), @NamedQuery(name = "ClinicalItemFacility.findByClinicalItemKey", query = "SELECT c FROM ClinicalItemFacility c WHERE c.clinicalItemKey = :clinicalItemKey"), @NamedQuery(name = "ClinicalItemFacility.findByFacilityKey", query = "SELECT c FROM ClinicalItemFacility c WHERE c.facilityKey = :facilityKey"), @NamedQuery(name = "ClinicalItemFacility.findByFacilityParticipationTypeKey", query = "SELECT c FROM ClinicalItemFacility c WHERE c.facilityParticipationTypeKey = :facilityParticipationTypeKey")})
public class ClinicalItemFacility implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "clinical_item_facility_key")
    private BigDecimal clinicalItemFacilityKey;
    @Basic(optional = false)
    @Column(name = "clinical_item_key")
    private BigDecimal clinicalItemKey;
    @Basic(optional = false)
    @Column(name = "facility_key")
    private int facilityKey;
    @Basic(optional = false)
    @Column(name = "facility_participation_type_key")
    private BigDecimal facilityParticipationTypeKey;

    public ClinicalItemFacility() {
    }

    public ClinicalItemFacility(BigDecimal clinicalItemFacilityKey) {
        this.clinicalItemFacilityKey = clinicalItemFacilityKey;
    }

    public ClinicalItemFacility(BigDecimal clinicalItemFacilityKey, BigDecimal clinicalItemKey, int facilityKey, BigDecimal facilityParticipationTypeKey) {
        this.clinicalItemFacilityKey = clinicalItemFacilityKey;
        this.clinicalItemKey = clinicalItemKey;
        this.facilityKey = facilityKey;
        this.facilityParticipationTypeKey = facilityParticipationTypeKey;
    }

    public BigDecimal getClinicalItemFacilityKey() {
        return clinicalItemFacilityKey;
    }

    public void setClinicalItemFacilityKey(BigDecimal clinicalItemFacilityKey) {
        this.clinicalItemFacilityKey = clinicalItemFacilityKey;
    }

    public BigDecimal getClinicalItemKey() {
        return clinicalItemKey;
    }

    public void setClinicalItemKey(BigDecimal clinicalItemKey) {
        this.clinicalItemKey = clinicalItemKey;
    }

    public int getFacilityKey() {
        return facilityKey;
    }

    public void setFacilityKey(int facilityKey) {
        this.facilityKey = facilityKey;
    }

    public BigDecimal getFacilityParticipationTypeKey() {
        return facilityParticipationTypeKey;
    }

    public void setFacilityParticipationTypeKey(BigDecimal facilityParticipationTypeKey) {
        this.facilityParticipationTypeKey = facilityParticipationTypeKey;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (clinicalItemFacilityKey != null ? clinicalItemFacilityKey.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ClinicalItemFacility)) {
            return false;
        }
        ClinicalItemFacility other = (ClinicalItemFacility) object;
        if ((this.clinicalItemFacilityKey == null && other.clinicalItemFacilityKey != null) || (this.clinicalItemFacilityKey != null && !this.clinicalItemFacilityKey.equals(other.clinicalItemFacilityKey))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.hhs.fha.nhinc.kmr.mirth.ClinicalItemFacility[clinicalItemFacilityKey=" + clinicalItemFacilityKey + "]";
    }

}
