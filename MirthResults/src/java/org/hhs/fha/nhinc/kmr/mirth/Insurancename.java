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
@Table(name = "insurancename")
@NamedQueries({@NamedQuery(name = "Insurancename.findAll", query = "SELECT i FROM Insurancename i"), @NamedQuery(name = "Insurancename.findByInsurancekey", query = "SELECT i FROM Insurancename i WHERE i.insurancenamePK.insurancekey = :insurancekey"), @NamedQuery(name = "Insurancename.findByNamekey", query = "SELECT i FROM Insurancename i WHERE i.insurancenamePK.namekey = :namekey")})
public class Insurancename implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected InsurancenamePK insurancenamePK;

    public Insurancename() {
    }

    public Insurancename(InsurancenamePK insurancenamePK) {
        this.insurancenamePK = insurancenamePK;
    }

    public Insurancename(BigDecimal insurancekey, BigDecimal namekey) {
        this.insurancenamePK = new InsurancenamePK(insurancekey, namekey);
    }

    public InsurancenamePK getInsurancenamePK() {
        return insurancenamePK;
    }

    public void setInsurancenamePK(InsurancenamePK insurancenamePK) {
        this.insurancenamePK = insurancenamePK;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (insurancenamePK != null ? insurancenamePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Insurancename)) {
            return false;
        }
        Insurancename other = (Insurancename) object;
        if ((this.insurancenamePK == null && other.insurancenamePK != null) || (this.insurancenamePK != null && !this.insurancenamePK.equals(other.insurancenamePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.hhs.fha.nhinc.kmr.mirth.Insurancename[insurancenamePK=" + insurancenamePK + "]";
    }

}
