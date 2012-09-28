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
import javax.persistence.Lob;
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
@Table(name = "message")
@NamedQueries({@NamedQuery(name = "Message.findAll", query = "SELECT m FROM Message m"), @NamedQuery(name = "Message.findByMessagekey", query = "SELECT m FROM Message m WHERE m.messagekey = :messagekey"), @NamedQuery(name = "Message.findByMessagetypekey", query = "SELECT m FROM Message m WHERE m.messagetypekey = :messagetypekey"), @NamedQuery(name = "Message.findBySenderkey", query = "SELECT m FROM Message m WHERE m.senderkey = :senderkey"), @NamedQuery(name = "Message.findBySenderAddr", query = "SELECT m FROM Message m WHERE m.senderAddr = :senderAddr"), @NamedQuery(name = "Message.findBySubject", query = "SELECT m FROM Message m WHERE m.subject = :subject"), @NamedQuery(name = "Message.findByInbound", query = "SELECT m FROM Message m WHERE m.inbound = :inbound"), @NamedQuery(name = "Message.findByReceiveddate", query = "SELECT m FROM Message m WHERE m.receiveddate = :receiveddate"), @NamedQuery(name = "Message.findByDispatcheddate", query = "SELECT m FROM Message m WHERE m.dispatcheddate = :dispatcheddate"), @NamedQuery(name = "Message.findByFacilitykey", query = "SELECT m FROM Message m WHERE m.facilitykey = :facilitykey"), @NamedQuery(name = "Message.findByUnread", query = "SELECT m FROM Message m WHERE m.unread = :unread")})
public class Message implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "messagekey")
    private BigDecimal messagekey;
    @Basic(optional = false)
    @Column(name = "messagetypekey")
    private BigDecimal messagetypekey;
    @Column(name = "senderkey")
    private Integer senderkey;
    @Column(name = "sender_addr")
    private String senderAddr;
    @Column(name = "subject")
    private String subject;
    @Lob
    @Column(name = "body_text")
    private String bodyText;
    @Basic(optional = false)
    @Column(name = "inbound")
    private short inbound;
    @Basic(optional = false)
    @Column(name = "receiveddate")
    @Temporal(TemporalType.DATE)
    private Date receiveddate;
    @Column(name = "dispatcheddate")
    @Temporal(TemporalType.DATE)
    private Date dispatcheddate;
    @Basic(optional = false)
    @Column(name = "facilitykey")
    private int facilitykey;
    @Column(name = "unread")
    private Short unread;

    public Message() {
    }

    public Message(BigDecimal messagekey) {
        this.messagekey = messagekey;
    }

    public Message(BigDecimal messagekey, BigDecimal messagetypekey, short inbound, Date receiveddate, int facilitykey) {
        this.messagekey = messagekey;
        this.messagetypekey = messagetypekey;
        this.inbound = inbound;
        this.receiveddate = receiveddate;
        this.facilitykey = facilitykey;
    }

    public BigDecimal getMessagekey() {
        return messagekey;
    }

    public void setMessagekey(BigDecimal messagekey) {
        this.messagekey = messagekey;
    }

    public BigDecimal getMessagetypekey() {
        return messagetypekey;
    }

    public void setMessagetypekey(BigDecimal messagetypekey) {
        this.messagetypekey = messagetypekey;
    }

    public Integer getSenderkey() {
        return senderkey;
    }

    public void setSenderkey(Integer senderkey) {
        this.senderkey = senderkey;
    }

    public String getSenderAddr() {
        return senderAddr;
    }

    public void setSenderAddr(String senderAddr) {
        this.senderAddr = senderAddr;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBodyText() {
        return bodyText;
    }

    public void setBodyText(String bodyText) {
        this.bodyText = bodyText;
    }

    public short getInbound() {
        return inbound;
    }

    public void setInbound(short inbound) {
        this.inbound = inbound;
    }

    public Date getReceiveddate() {
        return receiveddate;
    }

    public void setReceiveddate(Date receiveddate) {
        this.receiveddate = receiveddate;
    }

    public Date getDispatcheddate() {
        return dispatcheddate;
    }

    public void setDispatcheddate(Date dispatcheddate) {
        this.dispatcheddate = dispatcheddate;
    }

    public int getFacilitykey() {
        return facilitykey;
    }

    public void setFacilitykey(int facilitykey) {
        this.facilitykey = facilitykey;
    }

    public Short getUnread() {
        return unread;
    }

    public void setUnread(Short unread) {
        this.unread = unread;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (messagekey != null ? messagekey.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Message)) {
            return false;
        }
        Message other = (Message) object;
        if ((this.messagekey == null && other.messagekey != null) || (this.messagekey != null && !this.messagekey.equals(other.messagekey))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.hhs.fha.nhinc.kmr.mirth.Message[messagekey=" + messagekey + "]";
    }

}
