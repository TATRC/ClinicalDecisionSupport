/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.hhs.fha.nhinc.kmr.mirth;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author Duane DeCouteau
 */
@Embeddable
public class ProviderfacilityPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "providerkey")
    private int providerkey;
    @Basic(optional = false)
    @Column(name = "facilitykey")
    private int facilitykey;

    public ProviderfacilityPK(int providerkey, int facilitykey) {
        this.providerkey = providerkey;
        this.facilitykey = facilitykey;
    }

    public int getProviderkey() {
        return providerkey;
    }

    public void setProviderkey(int providerkey) {
        this.providerkey = providerkey;
    }

    public int getFacilitykey() {
        return facilitykey;
    }

    public void setFacilitykey(int facilitykey) {
        this.facilitykey = facilitykey;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) providerkey;
        hash += (int) facilitykey;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProviderfacilityPK)) {
            return false;
        }
        ProviderfacilityPK other = (ProviderfacilityPK) object;
        if (this.providerkey != other.providerkey) {
            return false;
        }
        if (this.facilitykey != other.facilitykey) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.hhs.fha.nhinc.kmr.mirth.ProviderfacilityPK[providerkey=" + providerkey + ", facilitykey=" + facilitykey + "]";
    }

}
