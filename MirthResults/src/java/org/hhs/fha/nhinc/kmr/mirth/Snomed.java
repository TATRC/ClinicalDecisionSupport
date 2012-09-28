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
@Table(name = "snomed")
@NamedQueries({@NamedQuery(name = "Snomed.findAll", query = "SELECT s FROM Snomed s"), @NamedQuery(name = "Snomed.findByConceptKey", query = "SELECT s FROM Snomed s WHERE s.conceptKey = :conceptKey")})
public class Snomed implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "concept_key")
    private BigDecimal conceptKey;

    public Snomed() {
    }

    public Snomed(BigDecimal conceptKey) {
        this.conceptKey = conceptKey;
    }

    public BigDecimal getConceptKey() {
        return conceptKey;
    }

    public void setConceptKey(BigDecimal conceptKey) {
        this.conceptKey = conceptKey;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (conceptKey != null ? conceptKey.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Snomed)) {
            return false;
        }
        Snomed other = (Snomed) object;
        if ((this.conceptKey == null && other.conceptKey != null) || (this.conceptKey != null && !this.conceptKey.equals(other.conceptKey))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.hhs.fha.nhinc.kmr.mirth.Snomed[conceptKey=" + conceptKey + "]";
    }

}
