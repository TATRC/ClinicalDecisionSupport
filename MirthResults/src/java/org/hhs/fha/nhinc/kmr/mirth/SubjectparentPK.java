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
public class SubjectparentPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "subject_a_key")
    private int subjectAKey;
    @Basic(optional = false)
    @Column(name = "subject_b_key")
    private int subjectBKey;

    public SubjectparentPK(int subjectAKey, int subjectBKey) {
        this.subjectAKey = subjectAKey;
        this.subjectBKey = subjectBKey;
    }

    public int getSubjectAKey() {
        return subjectAKey;
    }

    public void setSubjectAKey(int subjectAKey) {
        this.subjectAKey = subjectAKey;
    }

    public int getSubjectBKey() {
        return subjectBKey;
    }

    public void setSubjectBKey(int subjectBKey) {
        this.subjectBKey = subjectBKey;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) subjectAKey;
        hash += (int) subjectBKey;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SubjectparentPK)) {
            return false;
        }
        SubjectparentPK other = (SubjectparentPK) object;
        if (this.subjectAKey != other.subjectAKey) {
            return false;
        }
        if (this.subjectBKey != other.subjectBKey) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.hhs.fha.nhinc.kmr.mirth.SubjectparentPK[subjectAKey=" + subjectAKey + ", subjectBKey=" + subjectBKey + "]";
    }

}
