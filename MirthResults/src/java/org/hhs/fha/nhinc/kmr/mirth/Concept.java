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
@Table(name = "concept")
@NamedQueries({@NamedQuery(name = "Concept.findAll", query = "SELECT c FROM Concept c"), @NamedQuery(name = "Concept.findByConceptKey", query = "SELECT c FROM Concept c WHERE c.conceptKey = :conceptKey"), @NamedQuery(name = "Concept.findByCode", query = "SELECT c FROM Concept c WHERE c.code = :code"), @NamedQuery(name = "Concept.findByLabel", query = "SELECT c FROM Concept c WHERE c.label = :label"), @NamedQuery(name = "Concept.findByDescription", query = "SELECT c FROM Concept c WHERE c.description = :description"), @NamedQuery(name = "Concept.findByConceptClassId", query = "SELECT c FROM Concept c WHERE c.conceptClassId = :conceptClassId")})
public class Concept implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "concept_key")
    private BigDecimal conceptKey;
    @Basic(optional = false)
    @Column(name = "code")
    private String code;
    @Basic(optional = false)
    @Column(name = "label")
    private String label;
    @Column(name = "description")
    private String description;
    @Column(name = "concept_class_id")
    private String conceptClassId;

    public Concept() {
    }

    public Concept(BigDecimal conceptKey) {
        this.conceptKey = conceptKey;
    }

    public Concept(BigDecimal conceptKey, String code, String label) {
        this.conceptKey = conceptKey;
        this.code = code;
        this.label = label;
    }

    public BigDecimal getConceptKey() {
        return conceptKey;
    }

    public void setConceptKey(BigDecimal conceptKey) {
        this.conceptKey = conceptKey;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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

    public String getConceptClassId() {
        return conceptClassId;
    }

    public void setConceptClassId(String conceptClassId) {
        this.conceptClassId = conceptClassId;
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
        if (!(object instanceof Concept)) {
            return false;
        }
        Concept other = (Concept) object;
        if ((this.conceptKey == null && other.conceptKey != null) || (this.conceptKey != null && !this.conceptKey.equals(other.conceptKey))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.hhs.fha.nhinc.kmr.mirth.Concept[conceptKey=" + conceptKey + "]";
    }

}
