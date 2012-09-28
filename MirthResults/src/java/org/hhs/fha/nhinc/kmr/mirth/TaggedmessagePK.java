/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.hhs.fha.nhinc.kmr.mirth;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author Duane DeCouteau
 */
@Embeddable
public class TaggedmessagePK implements Serializable {
    @Basic(optional = false)
    @Column(name = "messagerecipientkey")
    private BigDecimal messagerecipientkey;
    @Basic(optional = false)
    @Column(name = "usermessagetagkey")
    private BigDecimal usermessagetagkey;

    public TaggedmessagePK(BigDecimal messagerecipientkey, BigDecimal usermessagetagkey) {
        this.messagerecipientkey = messagerecipientkey;
        this.usermessagetagkey = usermessagetagkey;
    }

    public BigDecimal getMessagerecipientkey() {
        return messagerecipientkey;
    }

    public void setMessagerecipientkey(BigDecimal messagerecipientkey) {
        this.messagerecipientkey = messagerecipientkey;
    }

    public BigDecimal getUsermessagetagkey() {
        return usermessagetagkey;
    }

    public void setUsermessagetagkey(BigDecimal usermessagetagkey) {
        this.usermessagetagkey = usermessagetagkey;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (messagerecipientkey != null ? messagerecipientkey.hashCode() : 0);
        hash += (usermessagetagkey != null ? usermessagetagkey.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TaggedmessagePK)) {
            return false;
        }
        TaggedmessagePK other = (TaggedmessagePK) object;
        if ((this.messagerecipientkey == null && other.messagerecipientkey != null) || (this.messagerecipientkey != null && !this.messagerecipientkey.equals(other.messagerecipientkey))) {
            return false;
        }
        if ((this.usermessagetagkey == null && other.usermessagetagkey != null) || (this.usermessagetagkey != null && !this.usermessagetagkey.equals(other.usermessagetagkey))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.hhs.fha.nhinc.kmr.mirth.TaggedmessagePK[messagerecipientkey=" + messagerecipientkey + ", usermessagetagkey=" + usermessagetagkey + "]";
    }

}
