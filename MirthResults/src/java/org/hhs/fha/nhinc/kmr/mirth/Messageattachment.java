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
@Table(name = "messageattachment")
@NamedQueries({@NamedQuery(name = "Messageattachment.findAll", query = "SELECT m FROM Messageattachment m"), @NamedQuery(name = "Messageattachment.findByMessageattachmentkey", query = "SELECT m FROM Messageattachment m WHERE m.messageattachmentkey = :messageattachmentkey"), @NamedQuery(name = "Messageattachment.findByAttachmenttypekey", query = "SELECT m FROM Messageattachment m WHERE m.attachmenttypekey = :attachmenttypekey"), @NamedQuery(name = "Messageattachment.findByMessagekey", query = "SELECT m FROM Messageattachment m WHERE m.messagekey = :messagekey"), @NamedQuery(name = "Messageattachment.findByUri", query = "SELECT m FROM Messageattachment m WHERE m.uri = :uri"), @NamedQuery(name = "Messageattachment.findByLabel", query = "SELECT m FROM Messageattachment m WHERE m.label = :label"), @NamedQuery(name = "Messageattachment.findByClinicalitemkey", query = "SELECT m FROM Messageattachment m WHERE m.clinicalitemkey = :clinicalitemkey")})
public class Messageattachment implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "messageattachmentkey")
    private BigDecimal messageattachmentkey;
    @Basic(optional = false)
    @Column(name = "attachmenttypekey")
    private BigDecimal attachmenttypekey;
    @Basic(optional = false)
    @Column(name = "messagekey")
    private BigDecimal messagekey;
    @Lob
    @Column(name = "data")
    private byte[] data;
    @Column(name = "uri")
    private String uri;
    @Column(name = "label")
    private String label;
    @Basic(optional = false)
    @Column(name = "clinicalitemkey")
    private BigDecimal clinicalitemkey;

    public Messageattachment() {
    }

    public Messageattachment(BigDecimal messageattachmentkey) {
        this.messageattachmentkey = messageattachmentkey;
    }

    public Messageattachment(BigDecimal messageattachmentkey, BigDecimal attachmenttypekey, BigDecimal messagekey, BigDecimal clinicalitemkey) {
        this.messageattachmentkey = messageattachmentkey;
        this.attachmenttypekey = attachmenttypekey;
        this.messagekey = messagekey;
        this.clinicalitemkey = clinicalitemkey;
    }

    public BigDecimal getMessageattachmentkey() {
        return messageattachmentkey;
    }

    public void setMessageattachmentkey(BigDecimal messageattachmentkey) {
        this.messageattachmentkey = messageattachmentkey;
    }

    public BigDecimal getAttachmenttypekey() {
        return attachmenttypekey;
    }

    public void setAttachmenttypekey(BigDecimal attachmenttypekey) {
        this.attachmenttypekey = attachmenttypekey;
    }

    public BigDecimal getMessagekey() {
        return messagekey;
    }

    public void setMessagekey(BigDecimal messagekey) {
        this.messagekey = messagekey;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public BigDecimal getClinicalitemkey() {
        return clinicalitemkey;
    }

    public void setClinicalitemkey(BigDecimal clinicalitemkey) {
        this.clinicalitemkey = clinicalitemkey;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (messageattachmentkey != null ? messageattachmentkey.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Messageattachment)) {
            return false;
        }
        Messageattachment other = (Messageattachment) object;
        if ((this.messageattachmentkey == null && other.messageattachmentkey != null) || (this.messageattachmentkey != null && !this.messageattachmentkey.equals(other.messageattachmentkey))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.hhs.fha.nhinc.kmr.mirth.Messageattachment[messageattachmentkey=" + messageattachmentkey + "]";
    }

}
