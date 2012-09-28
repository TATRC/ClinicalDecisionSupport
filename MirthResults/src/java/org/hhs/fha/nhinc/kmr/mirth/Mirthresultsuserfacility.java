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
@Table(name = "mirthresultsuserfacility")
@NamedQueries({@NamedQuery(name = "Mirthresultsuserfacility.findAll", query = "SELECT m FROM Mirthresultsuserfacility m"), @NamedQuery(name = "Mirthresultsuserfacility.findByMirthresultsuserkey", query = "SELECT m FROM Mirthresultsuserfacility m WHERE m.mirthresultsuserfacilityPK.mirthresultsuserkey = :mirthresultsuserkey"), @NamedQuery(name = "Mirthresultsuserfacility.findByFacilitykey", query = "SELECT m FROM Mirthresultsuserfacility m WHERE m.mirthresultsuserfacilityPK.facilitykey = :facilitykey")})
public class Mirthresultsuserfacility implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected MirthresultsuserfacilityPK mirthresultsuserfacilityPK;

    public Mirthresultsuserfacility() {
    }

    public Mirthresultsuserfacility(MirthresultsuserfacilityPK mirthresultsuserfacilityPK) {
        this.mirthresultsuserfacilityPK = mirthresultsuserfacilityPK;
    }

    public Mirthresultsuserfacility(int mirthresultsuserkey, int facilitykey) {
        this.mirthresultsuserfacilityPK = new MirthresultsuserfacilityPK(mirthresultsuserkey, facilitykey);
    }

    public MirthresultsuserfacilityPK getMirthresultsuserfacilityPK() {
        return mirthresultsuserfacilityPK;
    }

    public void setMirthresultsuserfacilityPK(MirthresultsuserfacilityPK mirthresultsuserfacilityPK) {
        this.mirthresultsuserfacilityPK = mirthresultsuserfacilityPK;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (mirthresultsuserfacilityPK != null ? mirthresultsuserfacilityPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Mirthresultsuserfacility)) {
            return false;
        }
        Mirthresultsuserfacility other = (Mirthresultsuserfacility) object;
        if ((this.mirthresultsuserfacilityPK == null && other.mirthresultsuserfacilityPK != null) || (this.mirthresultsuserfacilityPK != null && !this.mirthresultsuserfacilityPK.equals(other.mirthresultsuserfacilityPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.hhs.fha.nhinc.kmr.mirth.Mirthresultsuserfacility[mirthresultsuserfacilityPK=" + mirthresultsuserfacilityPK + "]";
    }

}
