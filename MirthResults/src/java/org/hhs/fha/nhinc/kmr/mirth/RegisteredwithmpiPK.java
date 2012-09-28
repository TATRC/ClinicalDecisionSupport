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
public class RegisteredwithmpiPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "nodeinstancekey")
    private short nodeinstancekey;
    @Basic(optional = false)
    @Column(name = "nodeinstancetypekey")
    private short nodeinstancetypekey;

    public RegisteredwithmpiPK(short nodeinstancekey, short nodeinstancetypekey) {
        this.nodeinstancekey = nodeinstancekey;
        this.nodeinstancetypekey = nodeinstancetypekey;
    }

    public short getNodeinstancekey() {
        return nodeinstancekey;
    }

    public void setNodeinstancekey(short nodeinstancekey) {
        this.nodeinstancekey = nodeinstancekey;
    }

    public short getNodeinstancetypekey() {
        return nodeinstancetypekey;
    }

    public void setNodeinstancetypekey(short nodeinstancetypekey) {
        this.nodeinstancetypekey = nodeinstancetypekey;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) nodeinstancekey;
        hash += (int) nodeinstancetypekey;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RegisteredwithmpiPK)) {
            return false;
        }
        RegisteredwithmpiPK other = (RegisteredwithmpiPK) object;
        if (this.nodeinstancekey != other.nodeinstancekey) {
            return false;
        }
        if (this.nodeinstancetypekey != other.nodeinstancetypekey) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.hhs.fha.nhinc.kmr.mirth.RegisteredwithmpiPK[nodeinstancekey=" + nodeinstancekey + ", nodeinstancetypekey=" + nodeinstancetypekey + "]";
    }

}
