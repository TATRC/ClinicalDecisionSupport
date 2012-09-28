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
@Table(name = "nhin_gateway_request")
@NamedQueries({@NamedQuery(name = "NhinGatewayRequest.findAll", query = "SELECT n FROM NhinGatewayRequest n"), @NamedQuery(name = "NhinGatewayRequest.findByNhinGatewayRequestKey", query = "SELECT n FROM NhinGatewayRequest n WHERE n.nhinGatewayRequestKey = :nhinGatewayRequestKey"), @NamedQuery(name = "NhinGatewayRequest.findByGwReqStatusKey", query = "SELECT n FROM NhinGatewayRequest n WHERE n.gwReqStatusKey = :gwReqStatusKey"), @NamedQuery(name = "NhinGatewayRequest.findByNhinRequestKey", query = "SELECT n FROM NhinGatewayRequest n WHERE n.nhinRequestKey = :nhinRequestKey"), @NamedQuery(name = "NhinGatewayRequest.findByNhinGatewayKey", query = "SELECT n FROM NhinGatewayRequest n WHERE n.nhinGatewayKey = :nhinGatewayKey"), @NamedQuery(name = "NhinGatewayRequest.findByPdResponseStatusCode", query = "SELECT n FROM NhinGatewayRequest n WHERE n.pdResponseStatusCode = :pdResponseStatusCode"), @NamedQuery(name = "NhinGatewayRequest.findByPdResponsePatId", query = "SELECT n FROM NhinGatewayRequest n WHERE n.pdResponsePatId = :pdResponsePatId"), @NamedQuery(name = "NhinGatewayRequest.findByDqResponseStatusCode", query = "SELECT n FROM NhinGatewayRequest n WHERE n.dqResponseStatusCode = :dqResponseStatusCode")})
public class NhinGatewayRequest implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "nhin_gateway_request_key")
    private BigDecimal nhinGatewayRequestKey;
    @Basic(optional = false)
    @Column(name = "gw_req_status_key")
    private BigDecimal gwReqStatusKey;
    @Basic(optional = false)
    @Column(name = "nhin_request_key")
    private BigDecimal nhinRequestKey;
    @Basic(optional = false)
    @Column(name = "nhin_gateway_key")
    private BigDecimal nhinGatewayKey;
    @Lob
    @Column(name = "pd_request_doc")
    private String pdRequestDoc;
    @Lob
    @Column(name = "pd_response_doc")
    private String pdResponseDoc;
    @Column(name = "pd_response_status_code")
    private String pdResponseStatusCode;
    @Column(name = "pd_response_pat_id")
    private String pdResponsePatId;
    @Lob
    @Column(name = "dq_request_doc")
    private String dqRequestDoc;
    @Lob
    @Column(name = "dq_repsonse_doc")
    private String dqRepsonseDoc;
    @Column(name = "dq_response_status_code")
    private String dqResponseStatusCode;

    public NhinGatewayRequest() {
    }

    public NhinGatewayRequest(BigDecimal nhinGatewayRequestKey) {
        this.nhinGatewayRequestKey = nhinGatewayRequestKey;
    }

    public NhinGatewayRequest(BigDecimal nhinGatewayRequestKey, BigDecimal gwReqStatusKey, BigDecimal nhinRequestKey, BigDecimal nhinGatewayKey) {
        this.nhinGatewayRequestKey = nhinGatewayRequestKey;
        this.gwReqStatusKey = gwReqStatusKey;
        this.nhinRequestKey = nhinRequestKey;
        this.nhinGatewayKey = nhinGatewayKey;
    }

    public BigDecimal getNhinGatewayRequestKey() {
        return nhinGatewayRequestKey;
    }

    public void setNhinGatewayRequestKey(BigDecimal nhinGatewayRequestKey) {
        this.nhinGatewayRequestKey = nhinGatewayRequestKey;
    }

    public BigDecimal getGwReqStatusKey() {
        return gwReqStatusKey;
    }

    public void setGwReqStatusKey(BigDecimal gwReqStatusKey) {
        this.gwReqStatusKey = gwReqStatusKey;
    }

    public BigDecimal getNhinRequestKey() {
        return nhinRequestKey;
    }

    public void setNhinRequestKey(BigDecimal nhinRequestKey) {
        this.nhinRequestKey = nhinRequestKey;
    }

    public BigDecimal getNhinGatewayKey() {
        return nhinGatewayKey;
    }

    public void setNhinGatewayKey(BigDecimal nhinGatewayKey) {
        this.nhinGatewayKey = nhinGatewayKey;
    }

    public String getPdRequestDoc() {
        return pdRequestDoc;
    }

    public void setPdRequestDoc(String pdRequestDoc) {
        this.pdRequestDoc = pdRequestDoc;
    }

    public String getPdResponseDoc() {
        return pdResponseDoc;
    }

    public void setPdResponseDoc(String pdResponseDoc) {
        this.pdResponseDoc = pdResponseDoc;
    }

    public String getPdResponseStatusCode() {
        return pdResponseStatusCode;
    }

    public void setPdResponseStatusCode(String pdResponseStatusCode) {
        this.pdResponseStatusCode = pdResponseStatusCode;
    }

    public String getPdResponsePatId() {
        return pdResponsePatId;
    }

    public void setPdResponsePatId(String pdResponsePatId) {
        this.pdResponsePatId = pdResponsePatId;
    }

    public String getDqRequestDoc() {
        return dqRequestDoc;
    }

    public void setDqRequestDoc(String dqRequestDoc) {
        this.dqRequestDoc = dqRequestDoc;
    }

    public String getDqRepsonseDoc() {
        return dqRepsonseDoc;
    }

    public void setDqRepsonseDoc(String dqRepsonseDoc) {
        this.dqRepsonseDoc = dqRepsonseDoc;
    }

    public String getDqResponseStatusCode() {
        return dqResponseStatusCode;
    }

    public void setDqResponseStatusCode(String dqResponseStatusCode) {
        this.dqResponseStatusCode = dqResponseStatusCode;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (nhinGatewayRequestKey != null ? nhinGatewayRequestKey.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof NhinGatewayRequest)) {
            return false;
        }
        NhinGatewayRequest other = (NhinGatewayRequest) object;
        if ((this.nhinGatewayRequestKey == null && other.nhinGatewayRequestKey != null) || (this.nhinGatewayRequestKey != null && !this.nhinGatewayRequestKey.equals(other.nhinGatewayRequestKey))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.hhs.fha.nhinc.kmr.mirth.NhinGatewayRequest[nhinGatewayRequestKey=" + nhinGatewayRequestKey + "]";
    }

}
