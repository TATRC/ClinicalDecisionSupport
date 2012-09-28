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
public class MirthresultsuserproviderPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "providerkey")
    private int providerkey;
    @Basic(optional = false)
    @Column(name = "mirthresultsuserkey")
    private int mirthresultsuserkey;

    public MirthresultsuserproviderPK(int providerkey, int mirthresultsuserkey) {
        this.providerkey = providerkey;
        this.mirthresultsuserkey = mirthresultsuserkey;
    }

    public int getProviderkey() {
        return providerkey;
    }

    public void setProviderkey(int providerkey) {
        this.providerkey = providerkey;
    }

    public int getMirthresultsuserkey() {
        return mirthresultsuserkey;
    }

    public void setMirthresultsuserkey(int mirthresultsuserkey) {
        this.mirthresultsuserkey = mirthresultsuserkey;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) providerkey;
        hash += (int) mirthresultsuserkey;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MirthresultsuserproviderPK)) {
            return false;
        }
        MirthresultsuserproviderPK other = (MirthresultsuserproviderPK) object;
        if (this.providerkey != other.providerkey) {
            return false;
        }
        if (this.mirthresultsuserkey != other.mirthresultsuserkey) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.hhs.fha.nhinc.kmr.mirth.MirthresultsuserproviderPK[providerkey=" + providerkey + ", mirthresultsuserkey=" + mirthresultsuserkey + "]";
    }

}
