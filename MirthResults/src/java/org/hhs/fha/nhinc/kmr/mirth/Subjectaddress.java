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
@Table(name = "subjectaddress")
@NamedQueries({@NamedQuery(name = "Subjectaddress.findAll", query = "SELECT s FROM Subjectaddress s"), @NamedQuery(name = "Subjectaddress.findBySubjectkey", query = "SELECT s FROM Subjectaddress s WHERE s.subjectaddressPK.subjectkey = :subjectkey"), @NamedQuery(name = "Subjectaddress.findByAddresskey", query = "SELECT s FROM Subjectaddress s WHERE s.subjectaddressPK.addresskey = :addresskey")})
public class Subjectaddress implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected SubjectaddressPK subjectaddressPK;

    public Subjectaddress() {
    }

    public Subjectaddress(SubjectaddressPK subjectaddressPK) {
        this.subjectaddressPK = subjectaddressPK;
    }

    public Subjectaddress(int subjectkey, BigDecimal addresskey) {
        this.subjectaddressPK = new SubjectaddressPK(subjectkey, addresskey);
    }

    public SubjectaddressPK getSubjectaddressPK() {
        return subjectaddressPK;
    }

    public void setSubjectaddressPK(SubjectaddressPK subjectaddressPK) {
        this.subjectaddressPK = subjectaddressPK;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (subjectaddressPK != null ? subjectaddressPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Subjectaddress)) {
            return false;
        }
        Subjectaddress other = (Subjectaddress) object;
        if ((this.subjectaddressPK == null && other.subjectaddressPK != null) || (this.subjectaddressPK != null && !this.subjectaddressPK.equals(other.subjectaddressPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.hhs.fha.nhinc.kmr.mirth.Subjectaddress[subjectaddressPK=" + subjectaddressPK + "]";
    }

}
