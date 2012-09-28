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
@Table(name = "communication")
@NamedQueries({@NamedQuery(name = "Communication.findAll", query = "SELECT c FROM Communication c"), @NamedQuery(name = "Communication.findByCommunicationkey", query = "SELECT c FROM Communication c WHERE c.communicationkey = :communicationkey"), @NamedQuery(name = "Communication.findByAddress", query = "SELECT c FROM Communication c WHERE c.address = :address"), @NamedQuery(name = "Communication.findByEffectivedate", query = "SELECT c FROM Communication c WHERE c.effectivedate = :effectivedate"), @NamedQuery(name = "Communication.findByExpirationdate", query = "SELECT c FROM Communication c WHERE c.expirationdate = :expirationdate"), @NamedQuery(name = "Communication.findByRowversion", query = "SELECT c FROM Communication c WHERE c.rowversion = :rowversion"), @NamedQuery(name = "Communication.findByCommunicationtypekey", query = "SELECT c FROM Communication c WHERE c.communicationtypekey = :communicationtypekey")})
public class Communication implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "communicationkey")
    private BigDecimal communicationkey;
    @Column(name = "address")
    private String address;
    @Column(name = "effectivedate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date effectivedate;
    @Column(name = "expirationdate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date expirationdate;
    @Basic(optional = false)
    @Column(name = "rowversion")
    private short rowversion;
    @Column(name = "communicationtypekey")
    private BigDecimal communicationtypekey;

    public Communication() {
    }

    public Communication(BigDecimal communicationkey) {
        this.communicationkey = communicationkey;
    }

    public Communication(BigDecimal communicationkey, short rowversion) {
        this.communicationkey = communicationkey;
        this.rowversion = rowversion;
    }

    public BigDecimal getCommunicationkey() {
        return communicationkey;
    }

    public void setCommunicationkey(BigDecimal communicationkey) {
        this.communicationkey = communicationkey;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public short getRowversion() {
        return rowversion;
    }

    public void setRowversion(short rowversion) {
        this.rowversion = rowversion;
    }

    public BigDecimal getCommunicationtypekey() {
        return communicationtypekey;
    }

    public void setCommunicationtypekey(BigDecimal communicationtypekey) {
        this.communicationtypekey = communicationtypekey;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (communicationkey != null ? communicationkey.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Communication)) {
            return false;
        }
        Communication other = (Communication) object;
        if ((this.communicationkey == null && other.communicationkey != null) || (this.communicationkey != null && !this.communicationkey.equals(other.communicationkey))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.hhs.fha.nhinc.kmr.mirth.Communication[communicationkey=" + communicationkey + "]";
    }

}
