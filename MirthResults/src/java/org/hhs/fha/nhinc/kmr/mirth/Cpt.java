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
@Table(name = "cpt")
@NamedQueries({@NamedQuery(name = "Cpt.findAll", query = "SELECT c FROM Cpt c"), @NamedQuery(name = "Cpt.findByConceptKey", query = "SELECT c FROM Cpt c WHERE c.conceptKey = :conceptKey")})
public class Cpt implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "concept_key")
    private BigDecimal conceptKey;

    public Cpt() {
    }

    public Cpt(BigDecimal conceptKey) {
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
        if (!(object instanceof Cpt)) {
            return false;
        }
        Cpt other = (Cpt) object;
        if ((this.conceptKey == null && other.conceptKey != null) || (this.conceptKey != null && !this.conceptKey.equals(other.conceptKey))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.hhs.fha.nhinc.kmr.mirth.Cpt[conceptKey=" + conceptKey + "]";
    }

}
