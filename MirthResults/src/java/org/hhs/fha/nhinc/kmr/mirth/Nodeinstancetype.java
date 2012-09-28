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
@Table(name = "nodeinstancetype")
@NamedQueries({@NamedQuery(name = "Nodeinstancetype.findAll", query = "SELECT n FROM Nodeinstancetype n"), @NamedQuery(name = "Nodeinstancetype.findByNodeinstancetypekey", query = "SELECT n FROM Nodeinstancetype n WHERE n.nodeinstancetypekey = :nodeinstancetypekey"), @NamedQuery(name = "Nodeinstancetype.findByLabel", query = "SELECT n FROM Nodeinstancetype n WHERE n.label = :label"), @NamedQuery(name = "Nodeinstancetype.findByDescr", query = "SELECT n FROM Nodeinstancetype n WHERE n.descr = :descr")})
public class Nodeinstancetype implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "nodeinstancetypekey")
    private Short nodeinstancetypekey;
    @Basic(optional = false)
    @Column(name = "label")
    private String label;
    @Column(name = "descr")
    private String descr;

    public Nodeinstancetype() {
    }

    public Nodeinstancetype(Short nodeinstancetypekey) {
        this.nodeinstancetypekey = nodeinstancetypekey;
    }

    public Nodeinstancetype(Short nodeinstancetypekey, String label) {
        this.nodeinstancetypekey = nodeinstancetypekey;
        this.label = label;
    }

    public Short getNodeinstancetypekey() {
        return nodeinstancetypekey;
    }

    public void setNodeinstancetypekey(Short nodeinstancetypekey) {
        this.nodeinstancetypekey = nodeinstancetypekey;
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
        hash += (nodeinstancetypekey != null ? nodeinstancetypekey.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Nodeinstancetype)) {
            return false;
        }
        Nodeinstancetype other = (Nodeinstancetype) object;
        if ((this.nodeinstancetypekey == null && other.nodeinstancetypekey != null) || (this.nodeinstancetypekey != null && !this.nodeinstancetypekey.equals(other.nodeinstancetypekey))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.hhs.fha.nhinc.kmr.mirth.Nodeinstancetype[nodeinstancetypekey=" + nodeinstancetypekey + "]";
    }

}
