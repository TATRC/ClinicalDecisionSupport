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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Duane DeCouteau
 */
@Entity
@Table(name = "nhin_gateway")
@NamedQueries({@NamedQuery(name = "NhinGateway.findAll", query = "SELECT n FROM NhinGateway n"), @NamedQuery(name = "NhinGateway.findByNhinGatewayKey", query = "SELECT n FROM NhinGateway n WHERE n.nhinGatewayKey = :nhinGatewayKey"), @NamedQuery(name = "NhinGateway.findByLabel", query = "SELECT n FROM NhinGateway n WHERE n.label = :label"), @NamedQuery(name = "NhinGateway.findByIdentifier", query = "SELECT n FROM NhinGateway n WHERE n.identifier = :identifier"), @NamedQuery(name = "NhinGateway.findByOid", query = "SELECT n FROM NhinGateway n WHERE n.oid = :oid"), @NamedQuery(name = "NhinGateway.findByDescr", query = "SELECT n FROM NhinGateway n WHERE n.descr = :descr")})
public class NhinGateway implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "nhin_gateway_key")
    private BigDecimal nhinGatewayKey;
    @Basic(optional = false)
    @Column(name = "label")
    private String label;
    @Basic(optional = false)
    @Column(name = "identifier")
    private String identifier;
    @Basic(optional = false)
    @Column(name = "oid")
    private String oid;
    @Column(name = "descr")
    private String descr;

    public NhinGateway() {
    }

    public NhinGateway(BigDecimal nhinGatewayKey) {
        this.nhinGatewayKey = nhinGatewayKey;
    }

    public NhinGateway(BigDecimal nhinGatewayKey, String label, String identifier, String oid) {
        this.nhinGatewayKey = nhinGatewayKey;
        this.label = label;
        this.identifier = identifier;
        this.oid = oid;
    }

    public BigDecimal getNhinGatewayKey() {
        return nhinGatewayKey;
    }

    public void setNhinGatewayKey(BigDecimal nhinGatewayKey) {
        this.nhinGatewayKey = nhinGatewayKey;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (nhinGatewayKey != null ? nhinGatewayKey.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof NhinGateway)) {
            return false;
        }
        NhinGateway other = (NhinGateway) object;
        if ((this.nhinGatewayKey == null && other.nhinGatewayKey != null) || (this.nhinGatewayKey != null && !this.nhinGatewayKey.equals(other.nhinGatewayKey))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.hhs.fha.nhinc.kmr.mirth.NhinGateway[nhinGatewayKey=" + nhinGatewayKey + "]";
    }

}
