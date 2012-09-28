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
@Table(name = "provideralias")
@NamedQueries({@NamedQuery(name = "Provideralias.findAll", query = "SELECT p FROM Provideralias p"), @NamedQuery(name = "Provideralias.findByProvideraliaskey", query = "SELECT p FROM Provideralias p WHERE p.provideraliaskey = :provideraliaskey"), @NamedQuery(name = "Provideralias.findByProvideraliastypekey", query = "SELECT p FROM Provideralias p WHERE p.provideraliastypekey = :provideraliastypekey"), @NamedQuery(name = "Provideralias.findByProviderkey", query = "SELECT p FROM Provideralias p WHERE p.providerkey = :providerkey"), @NamedQuery(name = "Provideralias.findByAlias", query = "SELECT p FROM Provideralias p WHERE p.alias = :alias"), @NamedQuery(name = "Provideralias.findByActive", query = "SELECT p FROM Provideralias p WHERE p.active = :active"), @NamedQuery(name = "Provideralias.findByRowversion", query = "SELECT p FROM Provideralias p WHERE p.rowversion = :rowversion"), @NamedQuery(name = "Provideralias.findByFacilitykey", query = "SELECT p FROM Provideralias p WHERE p.facilitykey = :facilitykey"), @NamedQuery(name = "Provideralias.findByGuid", query = "SELECT p FROM Provideralias p WHERE p.guid = :guid")})
public class Provideralias implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "provideraliaskey")
    private Integer provideraliaskey;
    @Basic(optional = false)
    @Column(name = "provideraliastypekey")
    private short provideraliastypekey;
    @Basic(optional = false)
    @Column(name = "providerkey")
    private int providerkey;
    @Basic(optional = false)
    @Column(name = "alias")
    private String alias;
    @Basic(optional = false)
    @Column(name = "active")
    private short active;
    @Basic(optional = false)
    @Column(name = "rowversion")
    private short rowversion;
    @Basic(optional = false)
    @Column(name = "facilitykey")
    private int facilitykey;
    @Basic(optional = false)
    @Column(name = "guid")
    private String guid;

    public Provideralias() {
    }

    public Provideralias(Integer provideraliaskey) {
        this.provideraliaskey = provideraliaskey;
    }

    public Provideralias(Integer provideraliaskey, short provideraliastypekey, int providerkey, String alias, short active, short rowversion, int facilitykey, String guid) {
        this.provideraliaskey = provideraliaskey;
        this.provideraliastypekey = provideraliastypekey;
        this.providerkey = providerkey;
        this.alias = alias;
        this.active = active;
        this.rowversion = rowversion;
        this.facilitykey = facilitykey;
        this.guid = guid;
    }

    public Integer getProvideraliaskey() {
        return provideraliaskey;
    }

    public void setProvideraliaskey(Integer provideraliaskey) {
        this.provideraliaskey = provideraliaskey;
    }

    public short getProvideraliastypekey() {
        return provideraliastypekey;
    }

    public void setProvideraliastypekey(short provideraliastypekey) {
        this.provideraliastypekey = provideraliastypekey;
    }

    public int getProviderkey() {
        return providerkey;
    }

    public void setProviderkey(int providerkey) {
        this.providerkey = providerkey;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public short getActive() {
        return active;
    }

    public void setActive(short active) {
        this.active = active;
    }

    public short getRowversion() {
        return rowversion;
    }

    public void setRowversion(short rowversion) {
        this.rowversion = rowversion;
    }

    public int getFacilitykey() {
        return facilitykey;
    }

    public void setFacilitykey(int facilitykey) {
        this.facilitykey = facilitykey;
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
        hash += (provideraliaskey != null ? provideraliaskey.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Provideralias)) {
            return false;
        }
        Provideralias other = (Provideralias) object;
        if ((this.provideraliaskey == null && other.provideraliaskey != null) || (this.provideraliaskey != null && !this.provideraliaskey.equals(other.provideraliaskey))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.hhs.fha.nhinc.kmr.mirth.Provideralias[provideraliaskey=" + provideraliaskey + "]";
    }

}
