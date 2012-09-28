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
@Table(name = "nhin_document")
@NamedQueries({@NamedQuery(name = "NhinDocument.findAll", query = "SELECT n FROM NhinDocument n"), @NamedQuery(name = "NhinDocument.findByNhinDocKey", query = "SELECT n FROM NhinDocument n WHERE n.nhinDocKey = :nhinDocKey"), @NamedQuery(name = "NhinDocument.findByNhinGatewayRequestKey", query = "SELECT n FROM NhinDocument n WHERE n.nhinGatewayRequestKey = :nhinGatewayRequestKey"), @NamedQuery(name = "NhinDocument.findByDocType", query = "SELECT n FROM NhinDocument n WHERE n.docType = :docType"), @NamedQuery(name = "NhinDocument.findByUniqueDocId", query = "SELECT n FROM NhinDocument n WHERE n.uniqueDocId = :uniqueDocId"), @NamedQuery(name = "NhinDocument.findByUniqueRepoId", query = "SELECT n FROM NhinDocument n WHERE n.uniqueRepoId = :uniqueRepoId"), @NamedQuery(name = "NhinDocument.findByDocMimeType", query = "SELECT n FROM NhinDocument n WHERE n.docMimeType = :docMimeType"), @NamedQuery(name = "NhinDocument.findByDocTitle", query = "SELECT n FROM NhinDocument n WHERE n.docTitle = :docTitle"), @NamedQuery(name = "NhinDocument.findByDocEffectiveDate", query = "SELECT n FROM NhinDocument n WHERE n.docEffectiveDate = :docEffectiveDate"), @NamedQuery(name = "NhinDocument.findByDocLangCode", query = "SELECT n FROM NhinDocument n WHERE n.docLangCode = :docLangCode"), @NamedQuery(name = "NhinDocument.findByDrStatusKey", query = "SELECT n FROM NhinDocument n WHERE n.drStatusKey = :drStatusKey")})
public class NhinDocument implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "nhin_doc_key")
    private BigDecimal nhinDocKey;
    @Basic(optional = false)
    @Column(name = "nhin_gateway_request_key")
    private BigDecimal nhinGatewayRequestKey;
    @Lob
    @Column(name = "dr_request_doc")
    private String drRequestDoc;
    @Lob
    @Column(name = "dr_response_doc")
    private String drResponseDoc;
    @Lob
    @Column(name = "document")
    private String document;
    @Basic(optional = false)
    @Column(name = "doc_type")
    private String docType;
    @Basic(optional = false)
    @Column(name = "unique_doc_id")
    private String uniqueDocId;
    @Basic(optional = false)
    @Column(name = "unique_repo_id")
    private String uniqueRepoId;
    @Basic(optional = false)
    @Column(name = "doc_mime_type")
    private String docMimeType;
    @Basic(optional = false)
    @Column(name = "doc_title")
    private String docTitle;
    @Basic(optional = false)
    @Column(name = "doc_effective_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date docEffectiveDate;
    @Column(name = "doc_lang_code")
    private String docLangCode;
    @Basic(optional = false)
    @Column(name = "dr_status_key")
    private BigDecimal drStatusKey;

    public NhinDocument() {
    }

    public NhinDocument(BigDecimal nhinDocKey) {
        this.nhinDocKey = nhinDocKey;
    }

    public NhinDocument(BigDecimal nhinDocKey, BigDecimal nhinGatewayRequestKey, String docType, String uniqueDocId, String uniqueRepoId, String docMimeType, String docTitle, Date docEffectiveDate, BigDecimal drStatusKey) {
        this.nhinDocKey = nhinDocKey;
        this.nhinGatewayRequestKey = nhinGatewayRequestKey;
        this.docType = docType;
        this.uniqueDocId = uniqueDocId;
        this.uniqueRepoId = uniqueRepoId;
        this.docMimeType = docMimeType;
        this.docTitle = docTitle;
        this.docEffectiveDate = docEffectiveDate;
        this.drStatusKey = drStatusKey;
    }

    public BigDecimal getNhinDocKey() {
        return nhinDocKey;
    }

    public void setNhinDocKey(BigDecimal nhinDocKey) {
        this.nhinDocKey = nhinDocKey;
    }

    public BigDecimal getNhinGatewayRequestKey() {
        return nhinGatewayRequestKey;
    }

    public void setNhinGatewayRequestKey(BigDecimal nhinGatewayRequestKey) {
        this.nhinGatewayRequestKey = nhinGatewayRequestKey;
    }

    public String getDrRequestDoc() {
        return drRequestDoc;
    }

    public void setDrRequestDoc(String drRequestDoc) {
        this.drRequestDoc = drRequestDoc;
    }

    public String getDrResponseDoc() {
        return drResponseDoc;
    }

    public void setDrResponseDoc(String drResponseDoc) {
        this.drResponseDoc = drResponseDoc;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public String getDocType() {
        return docType;
    }

    public void setDocType(String docType) {
        this.docType = docType;
    }

    public String getUniqueDocId() {
        return uniqueDocId;
    }

    public void setUniqueDocId(String uniqueDocId) {
        this.uniqueDocId = uniqueDocId;
    }

    public String getUniqueRepoId() {
        return uniqueRepoId;
    }

    public void setUniqueRepoId(String uniqueRepoId) {
        this.uniqueRepoId = uniqueRepoId;
    }

    public String getDocMimeType() {
        return docMimeType;
    }

    public void setDocMimeType(String docMimeType) {
        this.docMimeType = docMimeType;
    }

    public String getDocTitle() {
        return docTitle;
    }

    public void setDocTitle(String docTitle) {
        this.docTitle = docTitle;
    }

    public Date getDocEffectiveDate() {
        return docEffectiveDate;
    }

    public void setDocEffectiveDate(Date docEffectiveDate) {
        this.docEffectiveDate = docEffectiveDate;
    }

    public String getDocLangCode() {
        return docLangCode;
    }

    public void setDocLangCode(String docLangCode) {
        this.docLangCode = docLangCode;
    }

    public BigDecimal getDrStatusKey() {
        return drStatusKey;
    }

    public void setDrStatusKey(BigDecimal drStatusKey) {
        this.drStatusKey = drStatusKey;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (nhinDocKey != null ? nhinDocKey.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof NhinDocument)) {
            return false;
        }
        NhinDocument other = (NhinDocument) object;
        if ((this.nhinDocKey == null && other.nhinDocKey != null) || (this.nhinDocKey != null && !this.nhinDocKey.equals(other.nhinDocKey))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.hhs.fha.nhinc.kmr.mirth.NhinDocument[nhinDocKey=" + nhinDocKey + "]";
    }

}
