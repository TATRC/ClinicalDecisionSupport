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
@Table(name = "clinical_item_relationship")
@NamedQueries({@NamedQuery(name = "ClinicalItemRelationship.findAll", query = "SELECT c FROM ClinicalItemRelationship c"), @NamedQuery(name = "ClinicalItemRelationship.findByClinicalItemRelationshipKey", query = "SELECT c FROM ClinicalItemRelationship c WHERE c.clinicalItemRelationshipKey = :clinicalItemRelationshipKey"), @NamedQuery(name = "ClinicalItemRelationship.findByClinicalItemSourceKey", query = "SELECT c FROM ClinicalItemRelationship c WHERE c.clinicalItemSourceKey = :clinicalItemSourceKey"), @NamedQuery(name = "ClinicalItemRelationship.findByClinicalItemTargetKey", query = "SELECT c FROM ClinicalItemRelationship c WHERE c.clinicalItemTargetKey = :clinicalItemTargetKey"), @NamedQuery(name = "ClinicalItemRelationship.findByClinicalItemRelationshipTypeKey", query = "SELECT c FROM ClinicalItemRelationship c WHERE c.clinicalItemRelationshipTypeKey = :clinicalItemRelationshipTypeKey"), @NamedQuery(name = "ClinicalItemRelationship.findBySequenceNumber", query = "SELECT c FROM ClinicalItemRelationship c WHERE c.sequenceNumber = :sequenceNumber")})
public class ClinicalItemRelationship implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "clinical_item_relationship_key")
    private BigDecimal clinicalItemRelationshipKey;
    @Column(name = "clinical_item_source_key")
    private BigDecimal clinicalItemSourceKey;
    @Column(name = "clinical_item_target_key")
    private BigDecimal clinicalItemTargetKey;
    @Column(name = "clinical_item_relationship_type_key")
    private BigDecimal clinicalItemRelationshipTypeKey;
    @Column(name = "sequence_number")
    private BigDecimal sequenceNumber;

    public ClinicalItemRelationship() {
    }

    public ClinicalItemRelationship(BigDecimal clinicalItemRelationshipKey) {
        this.clinicalItemRelationshipKey = clinicalItemRelationshipKey;
    }

    public BigDecimal getClinicalItemRelationshipKey() {
        return clinicalItemRelationshipKey;
    }

    public void setClinicalItemRelationshipKey(BigDecimal clinicalItemRelationshipKey) {
        this.clinicalItemRelationshipKey = clinicalItemRelationshipKey;
    }

    public BigDecimal getClinicalItemSourceKey() {
        return clinicalItemSourceKey;
    }

    public void setClinicalItemSourceKey(BigDecimal clinicalItemSourceKey) {
        this.clinicalItemSourceKey = clinicalItemSourceKey;
    }

    public BigDecimal getClinicalItemTargetKey() {
        return clinicalItemTargetKey;
    }

    public void setClinicalItemTargetKey(BigDecimal clinicalItemTargetKey) {
        this.clinicalItemTargetKey = clinicalItemTargetKey;
    }

    public BigDecimal getClinicalItemRelationshipTypeKey() {
        return clinicalItemRelationshipTypeKey;
    }

    public void setClinicalItemRelationshipTypeKey(BigDecimal clinicalItemRelationshipTypeKey) {
        this.clinicalItemRelationshipTypeKey = clinicalItemRelationshipTypeKey;
    }

    public BigDecimal getSequenceNumber() {
        return sequenceNumber;
    }

    public void setSequenceNumber(BigDecimal sequenceNumber) {
        this.sequenceNumber = sequenceNumber;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (clinicalItemRelationshipKey != null ? clinicalItemRelationshipKey.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ClinicalItemRelationship)) {
            return false;
        }
        ClinicalItemRelationship other = (ClinicalItemRelationship) object;
        if ((this.clinicalItemRelationshipKey == null && other.clinicalItemRelationshipKey != null) || (this.clinicalItemRelationshipKey != null && !this.clinicalItemRelationshipKey.equals(other.clinicalItemRelationshipKey))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.hhs.fha.nhinc.kmr.mirth.ClinicalItemRelationship[clinicalItemRelationshipKey=" + clinicalItemRelationshipKey + "]";
    }

}
