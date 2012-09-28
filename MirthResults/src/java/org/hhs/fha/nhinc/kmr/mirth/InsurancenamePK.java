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
public class InsurancenamePK implements Serializable {
    @Basic(optional = false)
    @Column(name = "insurancekey")
    private BigDecimal insurancekey;
    @Basic(optional = false)
    @Column(name = "namekey")
    private BigDecimal namekey;

    public InsurancenamePK(BigDecimal insurancekey, BigDecimal namekey) {
        this.insurancekey = insurancekey;
        this.namekey = namekey;
    }

    public BigDecimal getInsurancekey() {
        return insurancekey;
    }

    public void setInsurancekey(BigDecimal insurancekey) {
        this.insurancekey = insurancekey;
    }

    public BigDecimal getNamekey() {
        return namekey;
    }

    public void setNamekey(BigDecimal namekey) {
        this.namekey = namekey;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (insurancekey != null ? insurancekey.hashCode() : 0);
        hash += (namekey != null ? namekey.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof InsurancenamePK)) {
            return false;
        }
        InsurancenamePK other = (InsurancenamePK) object;
        if ((this.insurancekey == null && other.insurancekey != null) || (this.insurancekey != null && !this.insurancekey.equals(other.insurancekey))) {
            return false;
        }
        if ((this.namekey == null && other.namekey != null) || (this.namekey != null && !this.namekey.equals(other.namekey))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.hhs.fha.nhinc.kmr.mirth.InsurancenamePK[insurancekey=" + insurancekey + ", namekey=" + namekey + "]";
    }

}
