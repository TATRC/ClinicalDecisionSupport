/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.hhs.fha.nhinc.kmr.mirth;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Duane DeCouteau
 */
@Entity
@Table(name = "attachment")
@NamedQueries({@NamedQuery(name = "Attachment.findAll", query = "SELECT a FROM Attachment a"), @NamedQuery(name = "Attachment.findByClinicalItemKey", query = "SELECT a FROM Attachment a WHERE a.clinicalItemKey = :clinicalItemKey"), @NamedQuery(name = "Attachment.findByAttachmentTypeKey", query = "SELECT a FROM Attachment a WHERE a.attachmentTypeKey = :attachmentTypeKey")})
public class Attachment implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "clinical_item_key")
    private BigDecimal clinicalItemKey;
    @Basic(optional = false)
    @Column(name = "attachment_type_key")
    private BigDecimal attachmentTypeKey;
    @Lob
    @Column(name = "data")
    private byte[] data;

    public Attachment() {
    }

    public Attachment(BigDecimal clinicalItemKey) {
        this.clinicalItemKey = clinicalItemKey;
    }

    public Attachment(BigDecimal clinicalItemKey, BigDecimal attachmentTypeKey) {
        this.clinicalItemKey = clinicalItemKey;
        this.attachmentTypeKey = attachmentTypeKey;
    }

    public BigDecimal getClinicalItemKey() {
        return clinicalItemKey;
    }

    public void setClinicalItemKey(BigDecimal clinicalItemKey) {
        this.clinicalItemKey = clinicalItemKey;
    }

    public BigDecimal getAttachmentTypeKey() {
        return attachmentTypeKey;
    }

    public void setAttachmentTypeKey(BigDecimal attachmentTypeKey) {
        this.attachmentTypeKey = attachmentTypeKey;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (clinicalItemKey != null ? clinicalItemKey.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Attachment)) {
            return false;
        }
        Attachment other = (Attachment) object;
        if ((this.clinicalItemKey == null && other.clinicalItemKey != null) || (this.clinicalItemKey != null && !this.clinicalItemKey.equals(other.clinicalItemKey))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.hhs.fha.nhinc.kmr.mirth.Attachment[clinicalItemKey=" + clinicalItemKey + "]";
    }

}
