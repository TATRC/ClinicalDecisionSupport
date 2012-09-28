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
public class InsuranceaddressPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "insurancekey")
    private BigDecimal insurancekey;
    @Basic(optional = false)
    @Column(name = "addresskey")
    private BigDecimal addresskey;

    public InsuranceaddressPK(BigDecimal insurancekey, BigDecimal addresskey) {
        this.insurancekey = insurancekey;
        this.addresskey = addresskey;
    }

    public BigDecimal getInsurancekey() {
        return insurancekey;
    }

    public void setInsurancekey(BigDecimal insurancekey) {
        this.insurancekey = insurancekey;
    }

    public BigDecimal getAddresskey() {
        return addresskey;
    }

    public void setAddresskey(BigDecimal addresskey) {
        this.addresskey = addresskey;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (insurancekey != null ? insurancekey.hashCode() : 0);
        hash += (addresskey != null ? addresskey.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof InsuranceaddressPK)) {
            return false;
        }
        InsuranceaddressPK other = (InsuranceaddressPK) object;
        if ((this.insurancekey == null && other.insurancekey != null) || (this.insurancekey != null && !this.insurancekey.equals(other.insurancekey))) {
            return false;
        }
        if ((this.addresskey == null && other.addresskey != null) || (this.addresskey != null && !this.addresskey.equals(other.addresskey))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.hhs.fha.nhinc.kmr.mirth.InsuranceaddressPK[insurancekey=" + insurancekey + ", addresskey=" + addresskey + "]";
    }

}
