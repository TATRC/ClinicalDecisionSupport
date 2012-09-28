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
@Table(name = "mirthresultsuserprovider")
@NamedQueries({@NamedQuery(name = "Mirthresultsuserprovider.findAll", query = "SELECT m FROM Mirthresultsuserprovider m"), @NamedQuery(name = "Mirthresultsuserprovider.findByProviderkey", query = "SELECT m FROM Mirthresultsuserprovider m WHERE m.mirthresultsuserproviderPK.providerkey = :providerkey"), @NamedQuery(name = "Mirthresultsuserprovider.findByMirthresultsuserkey", query = "SELECT m FROM Mirthresultsuserprovider m WHERE m.mirthresultsuserproviderPK.mirthresultsuserkey = :mirthresultsuserkey")})
public class Mirthresultsuserprovider implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected MirthresultsuserproviderPK mirthresultsuserproviderPK;

    public Mirthresultsuserprovider() {
    }

    public Mirthresultsuserprovider(MirthresultsuserproviderPK mirthresultsuserproviderPK) {
        this.mirthresultsuserproviderPK = mirthresultsuserproviderPK;
    }

    public Mirthresultsuserprovider(int providerkey, int mirthresultsuserkey) {
        this.mirthresultsuserproviderPK = new MirthresultsuserproviderPK(providerkey, mirthresultsuserkey);
    }

    public MirthresultsuserproviderPK getMirthresultsuserproviderPK() {
        return mirthresultsuserproviderPK;
    }

    public void setMirthresultsuserproviderPK(MirthresultsuserproviderPK mirthresultsuserproviderPK) {
        this.mirthresultsuserproviderPK = mirthresultsuserproviderPK;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (mirthresultsuserproviderPK != null ? mirthresultsuserproviderPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Mirthresultsuserprovider)) {
            return false;
        }
        Mirthresultsuserprovider other = (Mirthresultsuserprovider) object;
        if ((this.mirthresultsuserproviderPK == null && other.mirthresultsuserproviderPK != null) || (this.mirthresultsuserproviderPK != null && !this.mirthresultsuserproviderPK.equals(other.mirthresultsuserproviderPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.hhs.fha.nhinc.kmr.mirth.Mirthresultsuserprovider[mirthresultsuserproviderPK=" + mirthresultsuserproviderPK + "]";
    }

}
