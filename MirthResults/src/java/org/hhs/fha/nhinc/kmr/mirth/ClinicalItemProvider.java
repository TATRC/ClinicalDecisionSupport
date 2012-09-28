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
@Table(name = "clinical_item_provider")
@NamedQueries({@NamedQuery(name = "ClinicalItemProvider.findAll", query = "SELECT c FROM ClinicalItemProvider c"), @NamedQuery(name = "ClinicalItemProvider.findByClinicalItemProviderKey", query = "SELECT c FROM ClinicalItemProvider c WHERE c.clinicalItemProviderKey = :clinicalItemProviderKey"), @NamedQuery(name = "ClinicalItemProvider.findByClinicalItemKey", query = "SELECT c FROM ClinicalItemProvider c WHERE c.clinicalItemKey = :clinicalItemKey"), @NamedQuery(name = "ClinicalItemProvider.findByProviderKey", query = "SELECT c FROM ClinicalItemProvider c WHERE c.providerKey = :providerKey"), @NamedQuery(name = "ClinicalItemProvider.findByProviderParticipationTypeKey", query = "SELECT c FROM ClinicalItemProvider c WHERE c.providerParticipationTypeKey = :providerParticipationTypeKey")})
public class ClinicalItemProvider implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "clinical_item_provider_key")
    private BigDecimal clinicalItemProviderKey;
    @Basic(optional = false)
    @Column(name = "clinical_item_key")
    private BigDecimal clinicalItemKey;
    @Basic(optional = false)
    @Column(name = "provider_key")
    private int providerKey;
    @Basic(optional = false)
    @Column(name = "provider_participation_type_key")
    private BigDecimal providerParticipationTypeKey;

    public ClinicalItemProvider() {
    }

    public ClinicalItemProvider(BigDecimal clinicalItemProviderKey) {
        this.clinicalItemProviderKey = clinicalItemProviderKey;
    }

    public ClinicalItemProvider(BigDecimal clinicalItemProviderKey, BigDecimal clinicalItemKey, int providerKey, BigDecimal providerParticipationTypeKey) {
        this.clinicalItemProviderKey = clinicalItemProviderKey;
        this.clinicalItemKey = clinicalItemKey;
        this.providerKey = providerKey;
        this.providerParticipationTypeKey = providerParticipationTypeKey;
    }

    public BigDecimal getClinicalItemProviderKey() {
        return clinicalItemProviderKey;
    }

    public void setClinicalItemProviderKey(BigDecimal clinicalItemProviderKey) {
        this.clinicalItemProviderKey = clinicalItemProviderKey;
    }

    public BigDecimal getClinicalItemKey() {
        return clinicalItemKey;
    }

    public void setClinicalItemKey(BigDecimal clinicalItemKey) {
        this.clinicalItemKey = clinicalItemKey;
    }

    public int getProviderKey() {
        return providerKey;
    }

    public void setProviderKey(int providerKey) {
        this.providerKey = providerKey;
    }

    public BigDecimal getProviderParticipationTypeKey() {
        return providerParticipationTypeKey;
    }

    public void setProviderParticipationTypeKey(BigDecimal providerParticipationTypeKey) {
        this.providerParticipationTypeKey = providerParticipationTypeKey;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (clinicalItemProviderKey != null ? clinicalItemProviderKey.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ClinicalItemProvider)) {
            return false;
        }
        ClinicalItemProvider other = (ClinicalItemProvider) object;
        if ((this.clinicalItemProviderKey == null && other.clinicalItemProviderKey != null) || (this.clinicalItemProviderKey != null && !this.clinicalItemProviderKey.equals(other.clinicalItemProviderKey))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.hhs.fha.nhinc.kmr.mirth.ClinicalItemProvider[clinicalItemProviderKey=" + clinicalItemProviderKey + "]";
    }

}
