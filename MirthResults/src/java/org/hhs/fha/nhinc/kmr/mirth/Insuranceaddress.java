/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.hhs.fha.nhinc.kmr.mirth;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Duane DeCouteau
 */
@Entity
@Table(name = "insuranceaddress")
@NamedQueries({@NamedQuery(name = "Insuranceaddress.findAll", query = "SELECT i FROM Insuranceaddress i"), @NamedQuery(name = "Insuranceaddress.findByInsurancekey", query = "SELECT i FROM Insuranceaddress i WHERE i.insuranceaddressPK.insurancekey = :insurancekey"), @NamedQuery(name = "Insuranceaddress.findByAddresskey", query = "SELECT i FROM Insuranceaddress i WHERE i.insuranceaddressPK.addresskey = :addresskey")})
public class Insuranceaddress implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected InsuranceaddressPK insuranceaddressPK;

    public Insuranceaddress() {
    }

    public Insuranceaddress(InsuranceaddressPK insuranceaddressPK) {
        this.insuranceaddressPK = insuranceaddressPK;
    }

    public Insuranceaddress(BigDecimal insurancekey, BigDecimal addresskey) {
        this.insuranceaddressPK = new InsuranceaddressPK(insurancekey, addresskey);
    }

    public InsuranceaddressPK getInsuranceaddressPK() {
        return insuranceaddressPK;
    }

    public void setInsuranceaddressPK(InsuranceaddressPK insuranceaddressPK) {
        this.insuranceaddressPK = insuranceaddressPK;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (insuranceaddressPK != null ? insuranceaddressPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Insuranceaddress)) {
            return false;
        }
        Insuranceaddress other = (Insuranceaddress) object;
        if ((this.insuranceaddressPK == null && other.insuranceaddressPK != null) || (this.insuranceaddressPK != null && !this.insuranceaddressPK.equals(other.insuranceaddressPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.hhs.fha.nhinc.kmr.mirth.Insuranceaddress[insuranceaddressPK=" + insuranceaddressPK + "]";
    }

}
