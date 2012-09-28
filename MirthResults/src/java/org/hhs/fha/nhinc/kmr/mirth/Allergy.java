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
@Table(name = "allergy")
@NamedQueries({@NamedQuery(name = "Allergy.findAll", query = "SELECT a FROM Allergy a"), @NamedQuery(name = "Allergy.findByClinicalItemKey", query = "SELECT a FROM Allergy a WHERE a.clinicalItemKey = :clinicalItemKey"), @NamedQuery(name = "Allergy.findByAllergenTypeCodeKey", query = "SELECT a FROM Allergy a WHERE a.allergenTypeCodeKey = :allergenTypeCodeKey"), @NamedQuery(name = "Allergy.findBySeverityCodeKey", query = "SELECT a FROM Allergy a WHERE a.severityCodeKey = :severityCodeKey"), @NamedQuery(name = "Allergy.findByReaction", query = "SELECT a FROM Allergy a WHERE a.reaction = :reaction")})
public class Allergy implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "clinical_item_key")
    private BigDecimal clinicalItemKey;
    @Column(name = "allergen_type_code_key")
    private BigDecimal allergenTypeCodeKey;
    @Column(name = "severity_code_key")
    private BigDecimal severityCodeKey;
    @Column(name = "reaction")
    private String reaction;

    public Allergy() {
    }

    public Allergy(BigDecimal clinicalItemKey) {
        this.clinicalItemKey = clinicalItemKey;
    }

    public BigDecimal getClinicalItemKey() {
        return clinicalItemKey;
    }

    public void setClinicalItemKey(BigDecimal clinicalItemKey) {
        this.clinicalItemKey = clinicalItemKey;
    }

    public BigDecimal getAllergenTypeCodeKey() {
        return allergenTypeCodeKey;
    }

    public void setAllergenTypeCodeKey(BigDecimal allergenTypeCodeKey) {
        this.allergenTypeCodeKey = allergenTypeCodeKey;
    }

    public BigDecimal getSeverityCodeKey() {
        return severityCodeKey;
    }

    public void setSeverityCodeKey(BigDecimal severityCodeKey) {
        this.severityCodeKey = severityCodeKey;
    }

    public String getReaction() {
        return reaction;
    }

    public void setReaction(String reaction) {
        this.reaction = reaction;
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
        if (!(object instanceof Allergy)) {
            return false;
        }
        Allergy other = (Allergy) object;
        if ((this.clinicalItemKey == null && other.clinicalItemKey != null) || (this.clinicalItemKey != null && !this.clinicalItemKey.equals(other.clinicalItemKey))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.hhs.fha.nhinc.kmr.mirth.Allergy[clinicalItemKey=" + clinicalItemKey + "]";
    }

}
