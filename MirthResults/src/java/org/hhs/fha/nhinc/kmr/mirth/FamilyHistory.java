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
@Table(name = "family_history")
@NamedQueries({@NamedQuery(name = "FamilyHistory.findAll", query = "SELECT f FROM FamilyHistory f"), @NamedQuery(name = "FamilyHistory.findByClinicalItemKey", query = "SELECT f FROM FamilyHistory f WHERE f.clinicalItemKey = :clinicalItemKey"), @NamedQuery(name = "FamilyHistory.findByRelatedNameKey", query = "SELECT f FROM FamilyHistory f WHERE f.relatedNameKey = :relatedNameKey"), @NamedQuery(name = "FamilyHistory.findByRelationship", query = "SELECT f FROM FamilyHistory f WHERE f.relationship = :relationship")})
public class FamilyHistory implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "clinical_item_key")
    private BigDecimal clinicalItemKey;
    @Basic(optional = false)
    @Column(name = "related_name_key")
    private BigDecimal relatedNameKey;
    @Column(name = "relationship")
    private String relationship;

    public FamilyHistory() {
    }

    public FamilyHistory(BigDecimal clinicalItemKey) {
        this.clinicalItemKey = clinicalItemKey;
    }

    public FamilyHistory(BigDecimal clinicalItemKey, BigDecimal relatedNameKey) {
        this.clinicalItemKey = clinicalItemKey;
        this.relatedNameKey = relatedNameKey;
    }

    public BigDecimal getClinicalItemKey() {
        return clinicalItemKey;
    }

    public void setClinicalItemKey(BigDecimal clinicalItemKey) {
        this.clinicalItemKey = clinicalItemKey;
    }

    public BigDecimal getRelatedNameKey() {
        return relatedNameKey;
    }

    public void setRelatedNameKey(BigDecimal relatedNameKey) {
        this.relatedNameKey = relatedNameKey;
    }

    public String getRelationship() {
        return relationship;
    }

    public void setRelationship(String relationship) {
        this.relationship = relationship;
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
        if (!(object instanceof FamilyHistory)) {
            return false;
        }
        FamilyHistory other = (FamilyHistory) object;
        if ((this.clinicalItemKey == null && other.clinicalItemKey != null) || (this.clinicalItemKey != null && !this.clinicalItemKey.equals(other.clinicalItemKey))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.hhs.fha.nhinc.kmr.mirth.FamilyHistory[clinicalItemKey=" + clinicalItemKey + "]";
    }

}
