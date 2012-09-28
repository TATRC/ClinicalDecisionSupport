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
@Table(name = "subjectalias")
@NamedQueries({@NamedQuery(name = "Subjectalias.findAll", query = "SELECT s FROM Subjectalias s"), @NamedQuery(name = "Subjectalias.findBySubjectaliaskey", query = "SELECT s FROM Subjectalias s WHERE s.subjectaliaskey = :subjectaliaskey"), @NamedQuery(name = "Subjectalias.findBySubjectaliastypekey", query = "SELECT s FROM Subjectalias s WHERE s.subjectaliastypekey = :subjectaliastypekey"), @NamedQuery(name = "Subjectalias.findBySubjectkey", query = "SELECT s FROM Subjectalias s WHERE s.subjectkey = :subjectkey"), @NamedQuery(name = "Subjectalias.findByAlias", query = "SELECT s FROM Subjectalias s WHERE s.alias = :alias"), @NamedQuery(name = "Subjectalias.findByFacilitykey", query = "SELECT s FROM Subjectalias s WHERE s.facilitykey = :facilitykey"), @NamedQuery(name = "Subjectalias.findByRowversion", query = "SELECT s FROM Subjectalias s WHERE s.rowversion = :rowversion")})
public class Subjectalias implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "subjectaliaskey")
    private Integer subjectaliaskey;
    @Basic(optional = false)
    @Column(name = "subjectaliastypekey")
    private int subjectaliastypekey;
    @Basic(optional = false)
    @Column(name = "subjectkey")
    private int subjectkey;
    @Basic(optional = false)
    @Column(name = "alias")
    private String alias;
    @Basic(optional = false)
    @Column(name = "facilitykey")
    private int facilitykey;
    @Basic(optional = false)
    @Column(name = "rowversion")
    private short rowversion;

    public Subjectalias() {
    }

    public Subjectalias(Integer subjectaliaskey) {
        this.subjectaliaskey = subjectaliaskey;
    }

    public Subjectalias(Integer subjectaliaskey, int subjectaliastypekey, int subjectkey, String alias, int facilitykey, short rowversion) {
        this.subjectaliaskey = subjectaliaskey;
        this.subjectaliastypekey = subjectaliastypekey;
        this.subjectkey = subjectkey;
        this.alias = alias;
        this.facilitykey = facilitykey;
        this.rowversion = rowversion;
    }

    public Integer getSubjectaliaskey() {
        return subjectaliaskey;
    }

    public void setSubjectaliaskey(Integer subjectaliaskey) {
        this.subjectaliaskey = subjectaliaskey;
    }

    public int getSubjectaliastypekey() {
        return subjectaliastypekey;
    }

    public void setSubjectaliastypekey(int subjectaliastypekey) {
        this.subjectaliastypekey = subjectaliastypekey;
    }

    public int getSubjectkey() {
        return subjectkey;
    }

    public void setSubjectkey(int subjectkey) {
        this.subjectkey = subjectkey;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public int getFacilitykey() {
        return facilitykey;
    }

    public void setFacilitykey(int facilitykey) {
        this.facilitykey = facilitykey;
    }

    public short getRowversion() {
        return rowversion;
    }

    public void setRowversion(short rowversion) {
        this.rowversion = rowversion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (subjectaliaskey != null ? subjectaliaskey.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Subjectalias)) {
            return false;
        }
        Subjectalias other = (Subjectalias) object;
        if ((this.subjectaliaskey == null && other.subjectaliaskey != null) || (this.subjectaliaskey != null && !this.subjectaliaskey.equals(other.subjectaliaskey))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.hhs.fha.nhinc.kmr.mirth.Subjectalias[subjectaliaskey=" + subjectaliaskey + "]";
    }

}
