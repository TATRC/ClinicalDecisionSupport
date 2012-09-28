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
@Table(name = "attachmenttype")
@NamedQueries({@NamedQuery(name = "Attachmenttype.findAll", query = "SELECT a FROM Attachmenttype a"), @NamedQuery(name = "Attachmenttype.findByAttachmenttypekey", query = "SELECT a FROM Attachmenttype a WHERE a.attachmenttypekey = :attachmenttypekey"), @NamedQuery(name = "Attachmenttype.findByLabel", query = "SELECT a FROM Attachmenttype a WHERE a.label = :label"), @NamedQuery(name = "Attachmenttype.findByMimetype", query = "SELECT a FROM Attachmenttype a WHERE a.mimetype = :mimetype")})
public class Attachmenttype implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "attachmenttypekey")
    private BigDecimal attachmenttypekey;
    @Basic(optional = false)
    @Column(name = "label")
    private String label;
    @Lob
    @Column(name = "descr")
    private String descr;
    @Basic(optional = false)
    @Column(name = "mimetype")
    private String mimetype;

    public Attachmenttype() {
    }

    public Attachmenttype(BigDecimal attachmenttypekey) {
        this.attachmenttypekey = attachmenttypekey;
    }

    public Attachmenttype(BigDecimal attachmenttypekey, String label, String mimetype) {
        this.attachmenttypekey = attachmenttypekey;
        this.label = label;
        this.mimetype = mimetype;
    }

    public BigDecimal getAttachmenttypekey() {
        return attachmenttypekey;
    }

    public void setAttachmenttypekey(BigDecimal attachmenttypekey) {
        this.attachmenttypekey = attachmenttypekey;
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

    public String getMimetype() {
        return mimetype;
    }

    public void setMimetype(String mimetype) {
        this.mimetype = mimetype;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (attachmenttypekey != null ? attachmenttypekey.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Attachmenttype)) {
            return false;
        }
        Attachmenttype other = (Attachmenttype) object;
        if ((this.attachmenttypekey == null && other.attachmenttypekey != null) || (this.attachmenttypekey != null && !this.attachmenttypekey.equals(other.attachmenttypekey))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.hhs.fha.nhinc.kmr.mirth.Attachmenttype[attachmenttypekey=" + attachmenttypekey + "]";
    }

}
