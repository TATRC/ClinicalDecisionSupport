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
public class NodeinstancefacilityPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "nodeinstancekey")
    private short nodeinstancekey;
    @Basic(optional = false)
    @Column(name = "facilitykey")
    private int facilitykey;

    public NodeinstancefacilityPK(short nodeinstancekey, int facilitykey) {
        this.nodeinstancekey = nodeinstancekey;
        this.facilitykey = facilitykey;
    }

    public short getNodeinstancekey() {
        return nodeinstancekey;
    }

    public void setNodeinstancekey(short nodeinstancekey) {
        this.nodeinstancekey = nodeinstancekey;
    }

    public int getFacilitykey() {
        return facilitykey;
    }

    public void setFacilitykey(int facilitykey) {
        this.facilitykey = facilitykey;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) nodeinstancekey;
        hash += (int) facilitykey;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof NodeinstancefacilityPK)) {
            return false;
        }
        NodeinstancefacilityPK other = (NodeinstancefacilityPK) object;
        if (this.nodeinstancekey != other.nodeinstancekey) {
            return false;
        }
        if (this.facilitykey != other.facilitykey) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.hhs.fha.nhinc.kmr.mirth.NodeinstancefacilityPK[nodeinstancekey=" + nodeinstancekey + ", facilitykey=" + facilitykey + "]";
    }

}
