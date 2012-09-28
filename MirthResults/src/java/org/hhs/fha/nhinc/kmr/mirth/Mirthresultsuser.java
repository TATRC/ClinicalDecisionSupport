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
@Table(name = "mirthresultsuser")
@NamedQueries({@NamedQuery(name = "Mirthresultsuser.findAll", query = "SELECT m FROM Mirthresultsuser m"), @NamedQuery(name = "Mirthresultsuser.findByMirthresultsuserkey", query = "SELECT m FROM Mirthresultsuser m WHERE m.mirthresultsuserkey = :mirthresultsuserkey"), @NamedQuery(name = "Mirthresultsuser.findByGuid", query = "SELECT m FROM Mirthresultsuser m WHERE m.guid = :guid"), @NamedQuery(name = "Mirthresultsuser.findByPersonkey", query = "SELECT m FROM Mirthresultsuser m WHERE m.personkey = :personkey")})
public class Mirthresultsuser implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "mirthresultsuserkey")
    private Integer mirthresultsuserkey;
    @Column(name = "guid")
    private String guid;
    @Basic(optional = false)
    @Column(name = "personkey")
    private int personkey;

    public Mirthresultsuser() {
    }

    public Mirthresultsuser(Integer mirthresultsuserkey) {
        this.mirthresultsuserkey = mirthresultsuserkey;
    }

    public Mirthresultsuser(Integer mirthresultsuserkey, int personkey) {
        this.mirthresultsuserkey = mirthresultsuserkey;
        this.personkey = personkey;
    }

    public Integer getMirthresultsuserkey() {
        return mirthresultsuserkey;
    }

    public void setMirthresultsuserkey(Integer mirthresultsuserkey) {
        this.mirthresultsuserkey = mirthresultsuserkey;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public int getPersonkey() {
        return personkey;
    }

    public void setPersonkey(int personkey) {
        this.personkey = personkey;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (mirthresultsuserkey != null ? mirthresultsuserkey.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Mirthresultsuser)) {
            return false;
        }
        Mirthresultsuser other = (Mirthresultsuser) object;
        if ((this.mirthresultsuserkey == null && other.mirthresultsuserkey != null) || (this.mirthresultsuserkey != null && !this.mirthresultsuserkey.equals(other.mirthresultsuserkey))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.hhs.fha.nhinc.kmr.mirth.Mirthresultsuser[mirthresultsuserkey=" + mirthresultsuserkey + "]";
    }

}
