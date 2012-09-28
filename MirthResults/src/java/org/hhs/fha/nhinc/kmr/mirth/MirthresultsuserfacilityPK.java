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
public class MirthresultsuserfacilityPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "mirthresultsuserkey")
    private int mirthresultsuserkey;
    @Basic(optional = false)
    @Column(name = "facilitykey")
    private int facilitykey;

    public MirthresultsuserfacilityPK(int mirthresultsuserkey, int facilitykey) {
        this.mirthresultsuserkey = mirthresultsuserkey;
        this.facilitykey = facilitykey;
    }

    public int getMirthresultsuserkey() {
        return mirthresultsuserkey;
    }

    public void setMirthresultsuserkey(int mirthresultsuserkey) {
        this.mirthresultsuserkey = mirthresultsuserkey;
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
        hash += (int) mirthresultsuserkey;
        hash += (int) facilitykey;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MirthresultsuserfacilityPK)) {
            return false;
        }
        MirthresultsuserfacilityPK other = (MirthresultsuserfacilityPK) object;
        if (this.mirthresultsuserkey != other.mirthresultsuserkey) {
            return false;
        }
        if (this.facilitykey != other.facilitykey) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.hhs.fha.nhinc.kmr.mirth.MirthresultsuserfacilityPK[mirthresultsuserkey=" + mirthresultsuserkey + ", facilitykey=" + facilitykey + "]";
    }

}
