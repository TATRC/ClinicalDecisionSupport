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
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Duane DeCouteau
 */
@Entity
@Table(name = "codetype")
@NamedQueries({@NamedQuery(name = "Codetype.findAll", query = "SELECT c FROM Codetype c"), @NamedQuery(name = "Codetype.findByCodetypekey", query = "SELECT c FROM Codetype c WHERE c.codetypekey = :codetypekey"), @NamedQuery(name = "Codetype.findByLabel", query = "SELECT c FROM Codetype c WHERE c.label = :label")})
public class Codetype implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "codetypekey")
    private BigDecimal codetypekey;
    @Column(name = "label")
    private String label;
    @Lob
    @Column(name = "description")
    private String description;

    public Codetype() {
    }

    public Codetype(BigDecimal codetypekey) {
        this.codetypekey = codetypekey;
    }

    public BigDecimal getCodetypekey() {
        return codetypekey;
    }

    public void setCodetypekey(BigDecimal codetypekey) {
        this.codetypekey = codetypekey;
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
        hash += (codetypekey != null ? codetypekey.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Codetype)) {
            return false;
        }
        Codetype other = (Codetype) object;
        if ((this.codetypekey == null && other.codetypekey != null) || (this.codetypekey != null && !this.codetypekey.equals(other.codetypekey))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.hhs.fha.nhinc.kmr.mirth.Codetype[codetypekey=" + codetypekey + "]";
    }

}
