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
@Table(name = "clinical_item_access_type")
@NamedQueries({@NamedQuery(name = "ClinicalItemAccessType.findAll", query = "SELECT c FROM ClinicalItemAccessType c"), @NamedQuery(name = "ClinicalItemAccessType.findByClinicalItemAccessTypeKey", query = "SELECT c FROM ClinicalItemAccessType c WHERE c.clinicalItemAccessTypeKey = :clinicalItemAccessTypeKey"), @NamedQuery(name = "ClinicalItemAccessType.findByLabel", query = "SELECT c FROM ClinicalItemAccessType c WHERE c.label = :label"), @NamedQuery(name = "ClinicalItemAccessType.findByDescription", query = "SELECT c FROM ClinicalItemAccessType c WHERE c.description = :description")})
public class ClinicalItemAccessType implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "clinical_item_access_type_key")
    private BigDecimal clinicalItemAccessTypeKey;
    @Basic(optional = false)
    @Column(name = "label")
    private String label;
    @Column(name = "description")
    private String description;

    public ClinicalItemAccessType() {
    }

    public ClinicalItemAccessType(BigDecimal clinicalItemAccessTypeKey) {
        this.clinicalItemAccessTypeKey = clinicalItemAccessTypeKey;
    }

    public ClinicalItemAccessType(BigDecimal clinicalItemAccessTypeKey, String label) {
        this.clinicalItemAccessTypeKey = clinicalItemAccessTypeKey;
        this.label = label;
    }

    public BigDecimal getClinicalItemAccessTypeKey() {
        return clinicalItemAccessTypeKey;
    }

    public void setClinicalItemAccessTypeKey(BigDecimal clinicalItemAccessTypeKey) {
        this.clinicalItemAccessTypeKey = clinicalItemAccessTypeKey;
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
        hash += (clinicalItemAccessTypeKey != null ? clinicalItemAccessTypeKey.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ClinicalItemAccessType)) {
            return false;
        }
        ClinicalItemAccessType other = (ClinicalItemAccessType) object;
        if ((this.clinicalItemAccessTypeKey == null && other.clinicalItemAccessTypeKey != null) || (this.clinicalItemAccessTypeKey != null && !this.clinicalItemAccessTypeKey.equals(other.clinicalItemAccessTypeKey))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.hhs.fha.nhinc.kmr.mirth.ClinicalItemAccessType[clinicalItemAccessTypeKey=" + clinicalItemAccessTypeKey + "]";
    }

}
