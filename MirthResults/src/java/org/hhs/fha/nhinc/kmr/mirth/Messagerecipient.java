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
@Table(name = "messagerecipient")
@NamedQueries({@NamedQuery(name = "Messagerecipient.findAll", query = "SELECT m FROM Messagerecipient m"), @NamedQuery(name = "Messagerecipient.findByMessagerecipientkey", query = "SELECT m FROM Messagerecipient m WHERE m.messagerecipientkey = :messagerecipientkey"), @NamedQuery(name = "Messagerecipient.findByFacilitykey", query = "SELECT m FROM Messagerecipient m WHERE m.facilitykey = :facilitykey"), @NamedQuery(name = "Messagerecipient.findByMessagekey", query = "SELECT m FROM Messagerecipient m WHERE m.messagekey = :messagekey"), @NamedQuery(name = "Messagerecipient.findByRecipientkey", query = "SELECT m FROM Messagerecipient m WHERE m.recipientkey = :recipientkey"), @NamedQuery(name = "Messagerecipient.findByRecipientAddr", query = "SELECT m FROM Messagerecipient m WHERE m.recipientAddr = :recipientAddr"), @NamedQuery(name = "Messagerecipient.findByVieweddate", query = "SELECT m FROM Messagerecipient m WHERE m.vieweddate = :vieweddate"), @NamedQuery(name = "Messagerecipient.findByArchiveddate", query = "SELECT m FROM Messagerecipient m WHERE m.archiveddate = :archiveddate"), @NamedQuery(name = "Messagerecipient.findByRecipienttypekey", query = "SELECT m FROM Messagerecipient m WHERE m.recipienttypekey = :recipienttypekey")})
public class Messagerecipient implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "messagerecipientkey")
    private BigDecimal messagerecipientkey;
    @Basic(optional = false)
    @Column(name = "facilitykey")
    private int facilitykey;
    @Basic(optional = false)
    @Column(name = "messagekey")
    private BigDecimal messagekey;
    @Basic(optional = false)
    @Column(name = "recipientkey")
    private int recipientkey;
    @Column(name = "recipient_addr")
    private String recipientAddr;
    @Column(name = "vieweddate")
    @Temporal(TemporalType.DATE)
    private Date vieweddate;
    @Column(name = "archiveddate")
    @Temporal(TemporalType.DATE)
    private Date archiveddate;
    @Basic(optional = false)
    @Column(name = "recipienttypekey")
    private BigDecimal recipienttypekey;

    public Messagerecipient() {
    }

    public Messagerecipient(BigDecimal messagerecipientkey) {
        this.messagerecipientkey = messagerecipientkey;
    }

    public Messagerecipient(BigDecimal messagerecipientkey, int facilitykey, BigDecimal messagekey, int recipientkey, BigDecimal recipienttypekey) {
        this.messagerecipientkey = messagerecipientkey;
        this.facilitykey = facilitykey;
        this.messagekey = messagekey;
        this.recipientkey = recipientkey;
        this.recipienttypekey = recipienttypekey;
    }

    public BigDecimal getMessagerecipientkey() {
        return messagerecipientkey;
    }

    public void setMessagerecipientkey(BigDecimal messagerecipientkey) {
        this.messagerecipientkey = messagerecipientkey;
    }

    public int getFacilitykey() {
        return facilitykey;
    }

    public void setFacilitykey(int facilitykey) {
        this.facilitykey = facilitykey;
    }

    public BigDecimal getMessagekey() {
        return messagekey;
    }

    public void setMessagekey(BigDecimal messagekey) {
        this.messagekey = messagekey;
    }

    public int getRecipientkey() {
        return recipientkey;
    }

    public void setRecipientkey(int recipientkey) {
        this.recipientkey = recipientkey;
    }

    public String getRecipientAddr() {
        return recipientAddr;
    }

    public void setRecipientAddr(String recipientAddr) {
        this.recipientAddr = recipientAddr;
    }

    public Date getVieweddate() {
        return vieweddate;
    }

    public void setVieweddate(Date vieweddate) {
        this.vieweddate = vieweddate;
    }

    public Date getArchiveddate() {
        return archiveddate;
    }

    public void setArchiveddate(Date archiveddate) {
        this.archiveddate = archiveddate;
    }

    public BigDecimal getRecipienttypekey() {
        return recipienttypekey;
    }

    public void setRecipienttypekey(BigDecimal recipienttypekey) {
        this.recipienttypekey = recipienttypekey;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (messagerecipientkey != null ? messagerecipientkey.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Messagerecipient)) {
            return false;
        }
        Messagerecipient other = (Messagerecipient) object;
        if ((this.messagerecipientkey == null && other.messagerecipientkey != null) || (this.messagerecipientkey != null && !this.messagerecipientkey.equals(other.messagerecipientkey))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.hhs.fha.nhinc.kmr.mirth.Messagerecipient[messagerecipientkey=" + messagerecipientkey + "]";
    }

}
