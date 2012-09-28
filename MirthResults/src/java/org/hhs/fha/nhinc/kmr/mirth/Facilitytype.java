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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Duane DeCouteau
 */
@Entity
@Table(name = "facilitytype")
@NamedQueries({@NamedQuery(name = "Facilitytype.findAll", query = "SELECT f FROM Facilitytype f"), @NamedQuery(name = "Facilitytype.findByFacilitytypekey", query = "SELECT f FROM Facilitytype f WHERE f.facilitytypekey = :facilitytypekey"), @NamedQuery(name = "Facilitytype.findByLabel", query = "SELECT f FROM Facilitytype f WHERE f.label = :label"), @NamedQuery(name = "Facilitytype.findByDescr", query = "SELECT f FROM Facilitytype f WHERE f.descr = :descr"), @NamedQuery(name = "Facilitytype.findByConsumeslabs", query = "SELECT f FROM Facilitytype f WHERE f.consumeslabs = :consumeslabs")})
public class Facilitytype implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "facilitytypekey")
    private Short facilitytypekey;
    @Basic(optional = false)
    @Column(name = "label")
    private String label;
    @Column(name = "descr")
    private String descr;
    @Basic(optional = false)
    @Column(name = "consumeslabs")
    private short consumeslabs;

    public Facilitytype() {
    }

    public Facilitytype(Short facilitytypekey) {
        this.facilitytypekey = facilitytypekey;
    }

    public Facilitytype(Short facilitytypekey, String label, short consumeslabs) {
        this.facilitytypekey = facilitytypekey;
        this.label = label;
        this.consumeslabs = consumeslabs;
    }

    public Short getFacilitytypekey() {
        return facilitytypekey;
    }

    public void setFacilitytypekey(Short facilitytypekey) {
        this.facilitytypekey = facilitytypekey;
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

    public short getConsumeslabs() {
        return consumeslabs;
    }

    public void setConsumeslabs(short consumeslabs) {
        this.consumeslabs = consumeslabs;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (facilitytypekey != null ? facilitytypekey.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Facilitytype)) {
            return false;
        }
        Facilitytype other = (Facilitytype) object;
        if ((this.facilitytypekey == null && other.facilitytypekey != null) || (this.facilitytypekey != null && !this.facilitytypekey.equals(other.facilitytypekey))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.hhs.fha.nhinc.kmr.mirth.Facilitytype[facilitytypekey=" + facilitytypekey + "]";
    }

}
