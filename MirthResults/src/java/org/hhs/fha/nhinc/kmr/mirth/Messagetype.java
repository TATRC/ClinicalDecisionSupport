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
@Table(name = "messagetype")
@NamedQueries({@NamedQuery(name = "Messagetype.findAll", query = "SELECT m FROM Messagetype m"), @NamedQuery(name = "Messagetype.findByMessagetypekey", query = "SELECT m FROM Messagetype m WHERE m.messagetypekey = :messagetypekey"), @NamedQuery(name = "Messagetype.findByLabel", query = "SELECT m FROM Messagetype m WHERE m.label = :label"), @NamedQuery(name = "Messagetype.findByGuid", query = "SELECT m FROM Messagetype m WHERE m.guid = :guid")})
public class Messagetype implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "messagetypekey")
    private BigDecimal messagetypekey;
    @Basic(optional = false)
    @Column(name = "label")
    private String label;
    @Lob
    @Column(name = "descr")
    private String descr;
    @Column(name = "guid")
    private String guid;

    public Messagetype() {
    }

    public Messagetype(BigDecimal messagetypekey) {
        this.messagetypekey = messagetypekey;
    }

    public Messagetype(BigDecimal messagetypekey, String label) {
        this.messagetypekey = messagetypekey;
        this.label = label;
    }

    public BigDecimal getMessagetypekey() {
        return messagetypekey;
    }

    public void setMessagetypekey(BigDecimal messagetypekey) {
        this.messagetypekey = messagetypekey;
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

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (messagetypekey != null ? messagetypekey.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Messagetype)) {
            return false;
        }
        Messagetype other = (Messagetype) object;
        if ((this.messagetypekey == null && other.messagetypekey != null) || (this.messagetypekey != null && !this.messagetypekey.equals(other.messagetypekey))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.hhs.fha.nhinc.kmr.mirth.Messagetype[messagetypekey=" + messagetypekey + "]";
    }

}
