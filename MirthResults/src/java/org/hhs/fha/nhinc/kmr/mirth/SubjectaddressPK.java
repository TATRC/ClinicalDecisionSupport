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
public class SubjectaddressPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "subjectkey")
    private int subjectkey;
    @Basic(optional = false)
    @Column(name = "addresskey")
    private BigDecimal addresskey;

    public SubjectaddressPK(int subjectkey, BigDecimal addresskey) {
        this.subjectkey = subjectkey;
        this.addresskey = addresskey;
    }

    public int getSubjectkey() {
        return subjectkey;
    }

    public void setSubjectkey(int subjectkey) {
        this.subjectkey = subjectkey;
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
        hash += (int) subjectkey;
        hash += (addresskey != null ? addresskey.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SubjectaddressPK)) {
            return false;
        }
        SubjectaddressPK other = (SubjectaddressPK) object;
        if (this.subjectkey != other.subjectkey) {
            return false;
        }
        if ((this.addresskey == null && other.addresskey != null) || (this.addresskey != null && !this.addresskey.equals(other.addresskey))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.hhs.fha.nhinc.kmr.mirth.SubjectaddressPK[subjectkey=" + subjectkey + ", addresskey=" + addresskey + "]";
    }

}
