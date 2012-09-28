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
@Table(name = "subjectcommunication")
@NamedQueries({@NamedQuery(name = "Subjectcommunication.findAll", query = "SELECT s FROM Subjectcommunication s"), @NamedQuery(name = "Subjectcommunication.findByCommunicationkey", query = "SELECT s FROM Subjectcommunication s WHERE s.subjectcommunicationPK.communicationkey = :communicationkey"), @NamedQuery(name = "Subjectcommunication.findBySubjectkey", query = "SELECT s FROM Subjectcommunication s WHERE s.subjectcommunicationPK.subjectkey = :subjectkey")})
public class Subjectcommunication implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected SubjectcommunicationPK subjectcommunicationPK;

    public Subjectcommunication() {
    }

    public Subjectcommunication(SubjectcommunicationPK subjectcommunicationPK) {
        this.subjectcommunicationPK = subjectcommunicationPK;
    }

    public Subjectcommunication(BigDecimal communicationkey, int subjectkey) {
        this.subjectcommunicationPK = new SubjectcommunicationPK(communicationkey, subjectkey);
    }

    public SubjectcommunicationPK getSubjectcommunicationPK() {
        return subjectcommunicationPK;
    }

    public void setSubjectcommunicationPK(SubjectcommunicationPK subjectcommunicationPK) {
        this.subjectcommunicationPK = subjectcommunicationPK;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (subjectcommunicationPK != null ? subjectcommunicationPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Subjectcommunication)) {
            return false;
        }
        Subjectcommunication other = (Subjectcommunication) object;
        if ((this.subjectcommunicationPK == null && other.subjectcommunicationPK != null) || (this.subjectcommunicationPK != null && !this.subjectcommunicationPK.equals(other.subjectcommunicationPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.hhs.fha.nhinc.kmr.mirth.Subjectcommunication[subjectcommunicationPK=" + subjectcommunicationPK + "]";
    }

}
