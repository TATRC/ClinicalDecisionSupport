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
@Table(name = "recipienttype")
@NamedQueries({@NamedQuery(name = "Recipienttype.findAll", query = "SELECT r FROM Recipienttype r"), @NamedQuery(name = "Recipienttype.findByRecipienttypekey", query = "SELECT r FROM Recipienttype r WHERE r.recipienttypekey = :recipienttypekey"), @NamedQuery(name = "Recipienttype.findByLabel", query = "SELECT r FROM Recipienttype r WHERE r.label = :label")})
public class Recipienttype implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "recipienttypekey")
    private BigDecimal recipienttypekey;
    @Basic(optional = false)
    @Column(name = "label")
    private String label;
    @Lob
    @Column(name = "descr")
    private String descr;

    public Recipienttype() {
    }

    public Recipienttype(BigDecimal recipienttypekey) {
        this.recipienttypekey = recipienttypekey;
    }

    public Recipienttype(BigDecimal recipienttypekey, String label) {
        this.recipienttypekey = recipienttypekey;
        this.label = label;
    }

    public BigDecimal getRecipienttypekey() {
        return recipienttypekey;
    }

    public void setRecipienttypekey(BigDecimal recipienttypekey) {
        this.recipienttypekey = recipienttypekey;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (recipienttypekey != null ? recipienttypekey.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Recipienttype)) {
            return false;
        }
        Recipienttype other = (Recipienttype) object;
        if ((this.recipienttypekey == null && other.recipienttypekey != null) || (this.recipienttypekey != null && !this.recipienttypekey.equals(other.recipienttypekey))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.hhs.fha.nhinc.kmr.mirth.Recipienttype[recipienttypekey=" + recipienttypekey + "]";
    }

}
