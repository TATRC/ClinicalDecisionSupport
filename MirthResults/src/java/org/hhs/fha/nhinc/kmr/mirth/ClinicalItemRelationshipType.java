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
@Table(name = "clinical_item_relationship_type")
@NamedQueries({@NamedQuery(name = "ClinicalItemRelationshipType.findAll", query = "SELECT c FROM ClinicalItemRelationshipType c"), @NamedQuery(name = "ClinicalItemRelationshipType.findByClinicalItemRelationshipTypeKey", query = "SELECT c FROM ClinicalItemRelationshipType c WHERE c.clinicalItemRelationshipTypeKey = :clinicalItemRelationshipTypeKey"), @NamedQuery(name = "ClinicalItemRelationshipType.findByLabel", query = "SELECT c FROM ClinicalItemRelationshipType c WHERE c.label = :label"), @NamedQuery(name = "ClinicalItemRelationshipType.findByDescription", query = "SELECT c FROM ClinicalItemRelationshipType c WHERE c.description = :description")})
public class ClinicalItemRelationshipType implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "clinical_item_relationship_type_key")
    private BigDecimal clinicalItemRelationshipTypeKey;
    @Basic(optional = false)
    @Column(name = "label")
    private String label;
    @Column(name = "description")
    private String description;

    public ClinicalItemRelationshipType() {
    }

    public ClinicalItemRelationshipType(BigDecimal clinicalItemRelationshipTypeKey) {
        this.clinicalItemRelationshipTypeKey = clinicalItemRelationshipTypeKey;
    }

    public ClinicalItemRelationshipType(BigDecimal clinicalItemRelationshipTypeKey, String label) {
        this.clinicalItemRelationshipTypeKey = clinicalItemRelationshipTypeKey;
        this.label = label;
    }

    public BigDecimal getClinicalItemRelationshipTypeKey() {
        return clinicalItemRelationshipTypeKey;
    }

    public void setClinicalItemRelationshipTypeKey(BigDecimal clinicalItemRelationshipTypeKey) {
        this.clinicalItemRelationshipTypeKey = clinicalItemRelationshipTypeKey;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (clinicalItemRelationshipTypeKey != null ? clinicalItemRelationshipTypeKey.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ClinicalItemRelationshipType)) {
            return false;
        }
        ClinicalItemRelationshipType other = (ClinicalItemRelationshipType) object;
        if ((this.clinicalItemRelationshipTypeKey == null && other.clinicalItemRelationshipTypeKey != null) || (this.clinicalItemRelationshipTypeKey != null && !this.clinicalItemRelationshipTypeKey.equals(other.clinicalItemRelationshipTypeKey))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.hhs.fha.nhinc.kmr.mirth.ClinicalItemRelationshipType[clinicalItemRelationshipTypeKey=" + clinicalItemRelationshipTypeKey + "]";
    }

}
