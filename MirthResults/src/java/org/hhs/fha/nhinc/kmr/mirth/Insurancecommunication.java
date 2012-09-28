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
@Table(name = "insurancecommunication")
@NamedQueries({@NamedQuery(name = "Insurancecommunication.findAll", query = "SELECT i FROM Insurancecommunication i"), @NamedQuery(name = "Insurancecommunication.findByInsurancekey", query = "SELECT i FROM Insurancecommunication i WHERE i.insurancecommunicationPK.insurancekey = :insurancekey"), @NamedQuery(name = "Insurancecommunication.findByCommunicationkey", query = "SELECT i FROM Insurancecommunication i WHERE i.insurancecommunicationPK.communicationkey = :communicationkey")})
public class Insurancecommunication implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected InsurancecommunicationPK insurancecommunicationPK;

    public Insurancecommunication() {
    }

    public Insurancecommunication(InsurancecommunicationPK insurancecommunicationPK) {
        this.insurancecommunicationPK = insurancecommunicationPK;
    }

    public Insurancecommunication(BigDecimal insurancekey, BigDecimal communicationkey) {
        this.insurancecommunicationPK = new InsurancecommunicationPK(insurancekey, communicationkey);
    }

    public InsurancecommunicationPK getInsurancecommunicationPK() {
        return insurancecommunicationPK;
    }

    public void setInsurancecommunicationPK(InsurancecommunicationPK insurancecommunicationPK) {
        this.insurancecommunicationPK = insurancecommunicationPK;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (insurancecommunicationPK != null ? insurancecommunicationPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Insurancecommunication)) {
            return false;
        }
        Insurancecommunication other = (Insurancecommunication) object;
        if ((this.insurancecommunicationPK == null && other.insurancecommunicationPK != null) || (this.insurancecommunicationPK != null && !this.insurancecommunicationPK.equals(other.insurancecommunicationPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.hhs.fha.nhinc.kmr.mirth.Insurancecommunication[insurancecommunicationPK=" + insurancecommunicationPK + "]";
    }

}
