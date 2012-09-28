/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.hhs.fha.nhinc.kmr.mirth;

import java.io.Serializable;
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
@Table(name = "subjectaliastype")
@NamedQueries({@NamedQuery(name = "Subjectaliastype.findAll", query = "SELECT s FROM Subjectaliastype s"), @NamedQuery(name = "Subjectaliastype.findBySubjectaliastypekey", query = "SELECT s FROM Subjectaliastype s WHERE s.subjectaliastypekey = :subjectaliastypekey"), @NamedQuery(name = "Subjectaliastype.findByLabel", query = "SELECT s FROM Subjectaliastype s WHERE s.label = :label"), @NamedQuery(name = "Subjectaliastype.findByGuid", query = "SELECT s FROM Subjectaliastype s WHERE s.guid = :guid")})
public class Subjectaliastype implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "subjectaliastypekey")
    private Integer subjectaliastypekey;
    @Basic(optional = false)
    @Column(name = "label")
    private String label;
    @Lob
    @Column(name = "descr")
    private String descr;
    @Basic(optional = false)
    @Column(name = "guid")
    private String guid;

    public Subjectaliastype() {
    }

    public Subjectaliastype(Integer subjectaliastypekey) {
        this.subjectaliastypekey = subjectaliastypekey;
    }

    public Subjectaliastype(Integer subjectaliastypekey, String label, String guid) {
        this.subjectaliastypekey = subjectaliastypekey;
        this.label = label;
        this.guid = guid;
    }

    public Integer getSubjectaliastypekey() {
        return subjectaliastypekey;
    }

    public void setSubjectaliastypekey(Integer subjectaliastypekey) {
        this.subjectaliastypekey = subjectaliastypekey;
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
        hash += (subjectaliastypekey != null ? subjectaliastypekey.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Subjectaliastype)) {
            return false;
        }
        Subjectaliastype other = (Subjectaliastype) object;
        if ((this.subjectaliastypekey == null && other.subjectaliastypekey != null) || (this.subjectaliastypekey != null && !this.subjectaliastypekey.equals(other.subjectaliastypekey))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.hhs.fha.nhinc.kmr.mirth.Subjectaliastype[subjectaliastypekey=" + subjectaliastypekey + "]";
    }

}
