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
@Table(name = "subjectparent")
@NamedQueries({@NamedQuery(name = "Subjectparent.findAll", query = "SELECT s FROM Subjectparent s"), @NamedQuery(name = "Subjectparent.findBySubjectAKey", query = "SELECT s FROM Subjectparent s WHERE s.subjectparentPK.subjectAKey = :subjectAKey"), @NamedQuery(name = "Subjectparent.findBySubjectBKey", query = "SELECT s FROM Subjectparent s WHERE s.subjectparentPK.subjectBKey = :subjectBKey")})
public class Subjectparent implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected SubjectparentPK subjectparentPK;

    public Subjectparent() {
    }

    public Subjectparent(SubjectparentPK subjectparentPK) {
        this.subjectparentPK = subjectparentPK;
    }

    public Subjectparent(int subjectAKey, int subjectBKey) {
        this.subjectparentPK = new SubjectparentPK(subjectAKey, subjectBKey);
    }

    public SubjectparentPK getSubjectparentPK() {
        return subjectparentPK;
    }

    public void setSubjectparentPK(SubjectparentPK subjectparentPK) {
        this.subjectparentPK = subjectparentPK;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (subjectparentPK != null ? subjectparentPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Subjectparent)) {
            return false;
        }
        Subjectparent other = (Subjectparent) object;
        if ((this.subjectparentPK == null && other.subjectparentPK != null) || (this.subjectparentPK != null && !this.subjectparentPK.equals(other.subjectparentPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.hhs.fha.nhinc.kmr.mirth.Subjectparent[subjectparentPK=" + subjectparentPK + "]";
    }

}
