/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.hhs.fha.nhinc.kmr.mirth;

import java.io.Serializable;
import java.math.BigDecimal;
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
@Table(name = "subjectname")
@NamedQueries({@NamedQuery(name = "Subjectname.findAll", query = "SELECT s FROM Subjectname s"), @NamedQuery(name = "Subjectname.findBySubjectkey", query = "SELECT s FROM Subjectname s WHERE s.subjectnamePK.subjectkey = :subjectkey"), @NamedQuery(name = "Subjectname.findByNamekey", query = "SELECT s FROM Subjectname s WHERE s.subjectnamePK.namekey = :namekey")})
public class Subjectname implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected SubjectnamePK subjectnamePK;

    public Subjectname() {
    }

    public Subjectname(SubjectnamePK subjectnamePK) {
        this.subjectnamePK = subjectnamePK;
    }

    public Subjectname(int subjectkey, BigDecimal namekey) {
        this.subjectnamePK = new SubjectnamePK(subjectkey, namekey);
    }

    public SubjectnamePK getSubjectnamePK() {
        return subjectnamePK;
    }

    public void setSubjectnamePK(SubjectnamePK subjectnamePK) {
        this.subjectnamePK = subjectnamePK;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (subjectnamePK != null ? subjectnamePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Subjectname)) {
            return false;
        }
        Subjectname other = (Subjectname) object;
        if ((this.subjectnamePK == null && other.subjectnamePK != null) || (this.subjectnamePK != null && !this.subjectnamePK.equals(other.subjectnamePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.hhs.fha.nhinc.kmr.mirth.Subjectname[subjectnamePK=" + subjectnamePK + "]";
    }

}
