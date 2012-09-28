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
@Table(name = "provideraliastype")
@NamedQueries({@NamedQuery(name = "Provideraliastype.findAll", query = "SELECT p FROM Provideraliastype p"), @NamedQuery(name = "Provideraliastype.findByProvideraliastypekey", query = "SELECT p FROM Provideraliastype p WHERE p.provideraliastypekey = :provideraliastypekey"), @NamedQuery(name = "Provideraliastype.findByLabel", query = "SELECT p FROM Provideraliastype p WHERE p.label = :label"), @NamedQuery(name = "Provideraliastype.findByAuthorityid", query = "SELECT p FROM Provideraliastype p WHERE p.authorityid = :authorityid"), @NamedQuery(name = "Provideraliastype.findByGuid", query = "SELECT p FROM Provideraliastype p WHERE p.guid = :guid")})
public class Provideraliastype implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "provideraliastypekey")
    private Short provideraliastypekey;
    @Basic(optional = false)
    @Column(name = "label")
    private String label;
    @Basic(optional = false)
    @Column(name = "authorityid")
    private String authorityid;
    @Lob
    @Column(name = "descr")
    private String descr;
    @Basic(optional = false)
    @Column(name = "guid")
    private String guid;

    public Provideraliastype() {
    }

    public Provideraliastype(Short provideraliastypekey) {
        this.provideraliastypekey = provideraliastypekey;
    }

    public Provideraliastype(Short provideraliastypekey, String label, String authorityid, String guid) {
        this.provideraliastypekey = provideraliastypekey;
        this.label = label;
        this.authorityid = authorityid;
        this.guid = guid;
    }

    public Short getProvideraliastypekey() {
        return provideraliastypekey;
    }

    public void setProvideraliastypekey(Short provideraliastypekey) {
        this.provideraliastypekey = provideraliastypekey;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getAuthorityid() {
        return authorityid;
    }

    public void setAuthorityid(String authorityid) {
        this.authorityid = authorityid;
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
        hash += (provideraliastypekey != null ? provideraliastypekey.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Provideraliastype)) {
            return false;
        }
        Provideraliastype other = (Provideraliastype) object;
        if ((this.provideraliastypekey == null && other.provideraliastypekey != null) || (this.provideraliastypekey != null && !this.provideraliastypekey.equals(other.provideraliastypekey))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.hhs.fha.nhinc.kmr.mirth.Provideraliastype[provideraliastypekey=" + provideraliastypekey + "]";
    }

}
