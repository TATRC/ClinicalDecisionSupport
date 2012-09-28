/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.hhs.fha.nhinc.kmr.mirth;

import java.io.Serializable;
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
@Table(name = "providerfacility")
@NamedQueries({@NamedQuery(name = "Providerfacility.findAll", query = "SELECT p FROM Providerfacility p"), @NamedQuery(name = "Providerfacility.findByProviderkey", query = "SELECT p FROM Providerfacility p WHERE p.providerfacilityPK.providerkey = :providerkey"), @NamedQuery(name = "Providerfacility.findByFacilitykey", query = "SELECT p FROM Providerfacility p WHERE p.providerfacilityPK.facilitykey = :facilitykey")})
public class Providerfacility implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ProviderfacilityPK providerfacilityPK;

    public Providerfacility() {
    }

    public Providerfacility(ProviderfacilityPK providerfacilityPK) {
        this.providerfacilityPK = providerfacilityPK;
    }

    public Providerfacility(int providerkey, int facilitykey) {
        this.providerfacilityPK = new ProviderfacilityPK(providerkey, facilitykey);
    }

    public ProviderfacilityPK getProviderfacilityPK() {
        return providerfacilityPK;
    }

    public void setProviderfacilityPK(ProviderfacilityPK providerfacilityPK) {
        this.providerfacilityPK = providerfacilityPK;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (providerfacilityPK != null ? providerfacilityPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Providerfacility)) {
            return false;
        }
        Providerfacility other = (Providerfacility) object;
        if ((this.providerfacilityPK == null && other.providerfacilityPK != null) || (this.providerfacilityPK != null && !this.providerfacilityPK.equals(other.providerfacilityPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.hhs.fha.nhinc.kmr.mirth.Providerfacility[providerfacilityPK=" + providerfacilityPK + "]";
    }

}
