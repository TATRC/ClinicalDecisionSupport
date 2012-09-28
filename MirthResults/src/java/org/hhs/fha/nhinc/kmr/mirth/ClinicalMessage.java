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
@Table(name = "clinical_message")
@NamedQueries({@NamedQuery(name = "ClinicalMessage.findAll", query = "SELECT c FROM ClinicalMessage c"), @NamedQuery(name = "ClinicalMessage.findByClinicalMessageKey", query = "SELECT c FROM ClinicalMessage c WHERE c.clinicalMessageKey = :clinicalMessageKey"), @NamedQuery(name = "ClinicalMessage.findByMessageDate", query = "SELECT c FROM ClinicalMessage c WHERE c.messageDate = :messageDate"), @NamedQuery(name = "ClinicalMessage.findByMessageControlId", query = "SELECT c FROM ClinicalMessage c WHERE c.messageControlId = :messageControlId"), @NamedQuery(name = "ClinicalMessage.findByProcessingId", query = "SELECT c FROM ClinicalMessage c WHERE c.processingId = :processingId"), @NamedQuery(name = "ClinicalMessage.findByClinicalItemKey", query = "SELECT c FROM ClinicalMessage c WHERE c.clinicalItemKey = :clinicalItemKey")})
public class ClinicalMessage implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "clinical_message_key")
    private Integer clinicalMessageKey;
    @Lob
    @Column(name = "native_message")
    private String nativeMessage;
    @Basic(optional = false)
    @Column(name = "message_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date messageDate;
    @Column(name = "message_control_id")
    private String messageControlId;
    @Column(name = "processing_id")
    private String processingId;
    @Basic(optional = false)
    @Column(name = "clinical_item_key")
    private BigDecimal clinicalItemKey;

    public ClinicalMessage() {
    }

    public ClinicalMessage(Integer clinicalMessageKey) {
        this.clinicalMessageKey = clinicalMessageKey;
    }

    public ClinicalMessage(Integer clinicalMessageKey, Date messageDate, BigDecimal clinicalItemKey) {
        this.clinicalMessageKey = clinicalMessageKey;
        this.messageDate = messageDate;
        this.clinicalItemKey = clinicalItemKey;
    }

    public Integer getClinicalMessageKey() {
        return clinicalMessageKey;
    }

    public void setClinicalMessageKey(Integer clinicalMessageKey) {
        this.clinicalMessageKey = clinicalMessageKey;
    }

    public String getNativeMessage() {
        return nativeMessage;
    }

    public void setNativeMessage(String nativeMessage) {
        this.nativeMessage = nativeMessage;
    }

    public Date getMessageDate() {
        return messageDate;
    }

    public void setMessageDate(Date messageDate) {
        this.messageDate = messageDate;
    }

    public String getMessageControlId() {
        return messageControlId;
    }

    public void setMessageControlId(String messageControlId) {
        this.messageControlId = messageControlId;
    }

    public String getProcessingId() {
        return processingId;
    }

    public void setProcessingId(String processingId) {
        this.processingId = processingId;
    }

    public BigDecimal getClinicalItemKey() {
        return clinicalItemKey;
    }

    public void setClinicalItemKey(BigDecimal clinicalItemKey) {
        this.clinicalItemKey = clinicalItemKey;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (clinicalMessageKey != null ? clinicalMessageKey.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ClinicalMessage)) {
            return false;
        }
        ClinicalMessage other = (ClinicalMessage) object;
        if ((this.clinicalMessageKey == null && other.clinicalMessageKey != null) || (this.clinicalMessageKey != null && !this.clinicalMessageKey.equals(other.clinicalMessageKey))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.hhs.fha.nhinc.kmr.mirth.ClinicalMessage[clinicalMessageKey=" + clinicalMessageKey + "]";
    }

}
