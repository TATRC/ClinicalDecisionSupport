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
@Table(name = "registeredwithmpi")
@NamedQueries({@NamedQuery(name = "Registeredwithmpi.findAll", query = "SELECT r FROM Registeredwithmpi r"), @NamedQuery(name = "Registeredwithmpi.findByNodeinstancekey", query = "SELECT r FROM Registeredwithmpi r WHERE r.registeredwithmpiPK.nodeinstancekey = :nodeinstancekey"), @NamedQuery(name = "Registeredwithmpi.findByNodeinstancetypekey", query = "SELECT r FROM Registeredwithmpi r WHERE r.registeredwithmpiPK.nodeinstancetypekey = :nodeinstancetypekey")})
public class Registeredwithmpi implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected RegisteredwithmpiPK registeredwithmpiPK;

    public Registeredwithmpi() {
    }

    public Registeredwithmpi(RegisteredwithmpiPK registeredwithmpiPK) {
        this.registeredwithmpiPK = registeredwithmpiPK;
    }

    public Registeredwithmpi(short nodeinstancekey, short nodeinstancetypekey) {
        this.registeredwithmpiPK = new RegisteredwithmpiPK(nodeinstancekey, nodeinstancetypekey);
    }

    public RegisteredwithmpiPK getRegisteredwithmpiPK() {
        return registeredwithmpiPK;
    }

    public void setRegisteredwithmpiPK(RegisteredwithmpiPK registeredwithmpiPK) {
        this.registeredwithmpiPK = registeredwithmpiPK;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (registeredwithmpiPK != null ? registeredwithmpiPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Registeredwithmpi)) {
            return false;
        }
        Registeredwithmpi other = (Registeredwithmpi) object;
        if ((this.registeredwithmpiPK == null && other.registeredwithmpiPK != null) || (this.registeredwithmpiPK != null && !this.registeredwithmpiPK.equals(other.registeredwithmpiPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.hhs.fha.nhinc.kmr.mirth.Registeredwithmpi[registeredwithmpiPK=" + registeredwithmpiPK + "]";
    }

}
