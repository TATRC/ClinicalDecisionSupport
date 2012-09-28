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
public class InsurancecommunicationPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "insurancekey")
    private BigDecimal insurancekey;
    @Basic(optional = false)
    @Column(name = "communicationkey")
    private BigDecimal communicationkey;

    public InsurancecommunicationPK(BigDecimal insurancekey, BigDecimal communicationkey) {
        this.insurancekey = insurancekey;
        this.communicationkey = communicationkey;
    }

    public BigDecimal getInsurancekey() {
        return insurancekey;
    }

    public void setInsurancekey(BigDecimal insurancekey) {
        this.insurancekey = insurancekey;
    }

    public BigDecimal getCommunicationkey() {
        return communicationkey;
    }

    public void setCommunicationkey(BigDecimal communicationkey) {
        this.communicationkey = communicationkey;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (insurancekey != null ? insurancekey.hashCode() : 0);
        hash += (communicationkey != null ? communicationkey.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof InsurancecommunicationPK)) {
            return false;
        }
        InsurancecommunicationPK other = (InsurancecommunicationPK) object;
        if ((this.insurancekey == null && other.insurancekey != null) || (this.insurancekey != null && !this.insurancekey.equals(other.insurancekey))) {
            return false;
        }
        if ((this.communicationkey == null && other.communicationkey != null) || (this.communicationkey != null && !this.communicationkey.equals(other.communicationkey))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.hhs.fha.nhinc.kmr.mirth.InsurancecommunicationPK[insurancekey=" + insurancekey + ", communicationkey=" + communicationkey + "]";
    }

}
