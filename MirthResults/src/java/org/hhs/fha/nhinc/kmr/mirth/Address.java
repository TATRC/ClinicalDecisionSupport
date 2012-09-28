/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.hhs.fha.nhinc.kmr.mirth;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Duane DeCouteau
 */
@Entity
@Table(name = "address")
@NamedQueries({@NamedQuery(name = "Address.findAll", query = "SELECT a FROM Address a"), @NamedQuery(name = "Address.findByAddresskey", query = "SELECT a FROM Address a WHERE a.addresskey = :addresskey"), @NamedQuery(name = "Address.findByStreet1", query = "SELECT a FROM Address a WHERE a.street1 = :street1"), @NamedQuery(name = "Address.findByStreet2", query = "SELECT a FROM Address a WHERE a.street2 = :street2"), @NamedQuery(name = "Address.findByLocality", query = "SELECT a FROM Address a WHERE a.locality = :locality"), @NamedQuery(name = "Address.findByRegion", query = "SELECT a FROM Address a WHERE a.region = :region"), @NamedQuery(name = "Address.findByPostal", query = "SELECT a FROM Address a WHERE a.postal = :postal"), @NamedQuery(name = "Address.findByEffectivedate", query = "SELECT a FROM Address a WHERE a.effectivedate = :effectivedate"), @NamedQuery(name = "Address.findByExpirationdate", query = "SELECT a FROM Address a WHERE a.expirationdate = :expirationdate"), @NamedQuery(name = "Address.findByRowversion", query = "SELECT a FROM Address a WHERE a.rowversion = :rowversion")})
public class Address implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "addresskey")
    private BigDecimal addresskey;
    @Column(name = "street1")
    private String street1;
    @Column(name = "street2")
    private String street2;
    @Column(name = "locality")
    private String locality;
    @Column(name = "region")
    private String region;
    @Column(name = "postal")
    private String postal;
    @Column(name = "effectivedate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date effectivedate;
    @Column(name = "expirationdate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date expirationdate;
    @Column(name = "rowversion")
    private Short rowversion;

    public Address() {
    }

    public Address(BigDecimal addresskey) {
        this.addresskey = addresskey;
    }

    public BigDecimal getAddresskey() {
        return addresskey;
    }

    public void setAddresskey(BigDecimal addresskey) {
        this.addresskey = addresskey;
    }

    public String getStreet1() {
        return street1;
    }

    public void setStreet1(String street1) {
        this.street1 = street1;
    }

    public String getStreet2() {
        return street2;
    }

    public void setStreet2(String street2) {
        this.street2 = street2;
    }

    public String getLocality() {
        return locality;
    }

    public void setLocality(String locality) {
        this.locality = locality;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getPostal() {
        return postal;
    }

    public void setPostal(String postal) {
        this.postal = postal;
    }

    public Date getEffectivedate() {
        return effectivedate;
    }

    public void setEffectivedate(Date effectivedate) {
        this.effectivedate = effectivedate;
    }

    public Date getExpirationdate() {
        return expirationdate;
    }

    public void setExpirationdate(Date expirationdate) {
        this.expirationdate = expirationdate;
    }

    public Short getRowversion() {
        return rowversion;
    }

    public void setRowversion(Short rowversion) {
        this.rowversion = rowversion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (addresskey != null ? addresskey.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Address)) {
            return false;
        }
        Address other = (Address) object;
        if ((this.addresskey == null && other.addresskey != null) || (this.addresskey != null && !this.addresskey.equals(other.addresskey))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.hhs.fha.nhinc.kmr.mirth.Address[addresskey=" + addresskey + "]";
    }

}
