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
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
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
@Table(name = "taggedmessage")
@NamedQueries({@NamedQuery(name = "Taggedmessage.findAll", query = "SELECT t FROM Taggedmessage t"), @NamedQuery(name = "Taggedmessage.findByMessagerecipientkey", query = "SELECT t FROM Taggedmessage t WHERE t.taggedmessagePK.messagerecipientkey = :messagerecipientkey"), @NamedQuery(name = "Taggedmessage.findByUsermessagetagkey", query = "SELECT t FROM Taggedmessage t WHERE t.taggedmessagePK.usermessagetagkey = :usermessagetagkey"), @NamedQuery(name = "Taggedmessage.findByTaggeddate", query = "SELECT t FROM Taggedmessage t WHERE t.taggeddate = :taggeddate")})
public class Taggedmessage implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected TaggedmessagePK taggedmessagePK;
    @Basic(optional = false)
    @Column(name = "taggeddate")
    @Temporal(TemporalType.DATE)
    private Date taggeddate;

    public Taggedmessage() {
    }

    public Taggedmessage(TaggedmessagePK taggedmessagePK) {
        this.taggedmessagePK = taggedmessagePK;
    }

    public Taggedmessage(TaggedmessagePK taggedmessagePK, Date taggeddate) {
        this.taggedmessagePK = taggedmessagePK;
        this.taggeddate = taggeddate;
    }

    public Taggedmessage(BigDecimal messagerecipientkey, BigDecimal usermessagetagkey) {
        this.taggedmessagePK = new TaggedmessagePK(messagerecipientkey, usermessagetagkey);
    }

    public TaggedmessagePK getTaggedmessagePK() {
        return taggedmessagePK;
    }

    public void setTaggedmessagePK(TaggedmessagePK taggedmessagePK) {
        this.taggedmessagePK = taggedmessagePK;
    }

    public Date getTaggeddate() {
        return taggeddate;
    }

    public void setTaggeddate(Date taggeddate) {
        this.taggeddate = taggeddate;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (taggedmessagePK != null ? taggedmessagePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Taggedmessage)) {
            return false;
        }
        Taggedmessage other = (Taggedmessage) object;
        if ((this.taggedmessagePK == null && other.taggedmessagePK != null) || (this.taggedmessagePK != null && !this.taggedmessagePK.equals(other.taggedmessagePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.hhs.fha.nhinc.kmr.mirth.Taggedmessage[taggedmessagePK=" + taggedmessagePK + "]";
    }

}
