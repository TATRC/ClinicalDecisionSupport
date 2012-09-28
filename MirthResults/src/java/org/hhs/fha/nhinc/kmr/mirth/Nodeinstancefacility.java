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
@Table(name = "nodeinstancefacility")
@NamedQueries({@NamedQuery(name = "Nodeinstancefacility.findAll", query = "SELECT n FROM Nodeinstancefacility n"), @NamedQuery(name = "Nodeinstancefacility.findByNodeinstancekey", query = "SELECT n FROM Nodeinstancefacility n WHERE n.nodeinstancefacilityPK.nodeinstancekey = :nodeinstancekey"), @NamedQuery(name = "Nodeinstancefacility.findByFacilitykey", query = "SELECT n FROM Nodeinstancefacility n WHERE n.nodeinstancefacilityPK.facilitykey = :facilitykey")})
public class Nodeinstancefacility implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected NodeinstancefacilityPK nodeinstancefacilityPK;

    public Nodeinstancefacility() {
    }

    public Nodeinstancefacility(NodeinstancefacilityPK nodeinstancefacilityPK) {
        this.nodeinstancefacilityPK = nodeinstancefacilityPK;
    }

    public Nodeinstancefacility(short nodeinstancekey, int facilitykey) {
        this.nodeinstancefacilityPK = new NodeinstancefacilityPK(nodeinstancekey, facilitykey);
    }

    public NodeinstancefacilityPK getNodeinstancefacilityPK() {
        return nodeinstancefacilityPK;
    }

    public void setNodeinstancefacilityPK(NodeinstancefacilityPK nodeinstancefacilityPK) {
        this.nodeinstancefacilityPK = nodeinstancefacilityPK;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (nodeinstancefacilityPK != null ? nodeinstancefacilityPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Nodeinstancefacility)) {
            return false;
        }
        Nodeinstancefacility other = (Nodeinstancefacility) object;
        if ((this.nodeinstancefacilityPK == null && other.nodeinstancefacilityPK != null) || (this.nodeinstancefacilityPK != null && !this.nodeinstancefacilityPK.equals(other.nodeinstancefacilityPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.hhs.fha.nhinc.kmr.mirth.Nodeinstancefacility[nodeinstancefacilityPK=" + nodeinstancefacilityPK + "]";
    }

}
