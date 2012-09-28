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
public class SubjectnamePK implements Serializable {
    @Basic(optional = false)
    @Column(name = "subjectkey")
    private int subjectkey;
    @Basic(optional = false)
    @Column(name = "namekey")
    private BigDecimal namekey;

    public SubjectnamePK(int subjectkey, BigDecimal namekey) {
        this.subjectkey = subjectkey;
        this.namekey = namekey;
    }

    public int getSubjectkey() {
        return subjectkey;
    }

    public void setSubjectkey(int subjectkey) {
        this.subjectkey = subjectkey;
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
        hash += (int) subjectkey;
        hash += (namekey != null ? namekey.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SubjectnamePK)) {
            return false;
        }
        SubjectnamePK other = (SubjectnamePK) object;
        if (this.subjectkey != other.subjectkey) {
            return false;
        }
        if ((this.namekey == null && other.namekey != null) || (this.namekey != null && !this.namekey.equals(other.namekey))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.hhs.fha.nhinc.kmr.mirth.SubjectnamePK[subjectkey=" + subjectkey + ", namekey=" + namekey + "]";
    }

}
