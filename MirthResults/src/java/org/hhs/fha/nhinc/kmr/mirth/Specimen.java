/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.hhs.fha.nhinc.kmr.mirth;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Duane DeCouteau
 */
@Entity
@Table(name = "specimen")
@NamedQueries({@NamedQuery(name = "Specimen.findAll", query = "SELECT s FROM Specimen s"), @NamedQuery(name = "Specimen.findByClinicalItemKey", query = "SELECT s FROM Specimen s WHERE s.clinicalItemKey = :clinicalItemKey"), @NamedQuery(name = "Specimen.findByCollectionMethod", query = "SELECT s FROM Specimen s WHERE s.collectionMethod = :collectionMethod"), @NamedQuery(name = "Specimen.findByCollectionBodyLocation", query = "SELECT s FROM Specimen s WHERE s.collectionBodyLocation = :collectionBodyLocation"), @NamedQuery(name = "Specimen.findByCollectionVolume", query = "SELECT s FROM Specimen s WHERE s.collectionVolume = :collectionVolume"), @NamedQuery(name = "Specimen.findByCollectionVolumeUnits", query = "SELECT s FROM Specimen s WHERE s.collectionVolumeUnits = :collectionVolumeUnits"), @NamedQuery(name = "Specimen.findByCollectingDeviceId", query = "SELECT s FROM Specimen s WHERE s.collectingDeviceId = :collectingDeviceId"), @NamedQuery(name = "Specimen.findByCollectingDeviceModel", query = "SELECT s FROM Specimen s WHERE s.collectingDeviceModel = :collectingDeviceModel"), @NamedQuery(name = "Specimen.findByCollectingDeviceSoftware", query = "SELECT s FROM Specimen s WHERE s.collectingDeviceSoftware = :collectingDeviceSoftware"), @NamedQuery(name = "Specimen.findByActionCode", query = "SELECT s FROM Specimen s WHERE s.actionCode = :actionCode")})
public class Specimen implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "clinical_item_key")
    private BigDecimal clinicalItemKey;
    @Column(name = "collection_method")
    private String collectionMethod;
    @Column(name = "collection_body_location")
    private String collectionBodyLocation;
    @Column(name = "collection_volume")
    private String collectionVolume;
    @Column(name = "collection_volume_units")
    private String collectionVolumeUnits;
    @Column(name = "collecting_device_id")
    private String collectingDeviceId;
    @Column(name = "collecting_device_model")
    private String collectingDeviceModel;
    @Column(name = "collecting_device_software")
    private String collectingDeviceSoftware;
    @Column(name = "action_code")
    private String actionCode;

    public Specimen() {
    }

    public Specimen(BigDecimal clinicalItemKey) {
        this.clinicalItemKey = clinicalItemKey;
    }

    public BigDecimal getClinicalItemKey() {
        return clinicalItemKey;
    }

    public void setClinicalItemKey(BigDecimal clinicalItemKey) {
        this.clinicalItemKey = clinicalItemKey;
    }

    public String getCollectionMethod() {
        return collectionMethod;
    }

    public void setCollectionMethod(String collectionMethod) {
        this.collectionMethod = collectionMethod;
    }

    public String getCollectionBodyLocation() {
        return collectionBodyLocation;
    }

    public void setCollectionBodyLocation(String collectionBodyLocation) {
        this.collectionBodyLocation = collectionBodyLocation;
    }

    public String getCollectionVolume() {
        return collectionVolume;
    }

    public void setCollectionVolume(String collectionVolume) {
        this.collectionVolume = collectionVolume;
    }

    public String getCollectionVolumeUnits() {
        return collectionVolumeUnits;
    }

    public void setCollectionVolumeUnits(String collectionVolumeUnits) {
        this.collectionVolumeUnits = collectionVolumeUnits;
    }

    public String getCollectingDeviceId() {
        return collectingDeviceId;
    }

    public void setCollectingDeviceId(String collectingDeviceId) {
        this.collectingDeviceId = collectingDeviceId;
    }

    public String getCollectingDeviceModel() {
        return collectingDeviceModel;
    }

    public void setCollectingDeviceModel(String collectingDeviceModel) {
        this.collectingDeviceModel = collectingDeviceModel;
    }

    public String getCollectingDeviceSoftware() {
        return collectingDeviceSoftware;
    }

    public void setCollectingDeviceSoftware(String collectingDeviceSoftware) {
        this.collectingDeviceSoftware = collectingDeviceSoftware;
    }

    public String getActionCode() {
        return actionCode;
    }

    public void setActionCode(String actionCode) {
        this.actionCode = actionCode;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (clinicalItemKey != null ? clinicalItemKey.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Specimen)) {
            return false;
        }
        Specimen other = (Specimen) object;
        if ((this.clinicalItemKey == null && other.clinicalItemKey != null) || (this.clinicalItemKey != null && !this.clinicalItemKey.equals(other.clinicalItemKey))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.hhs.fha.nhinc.kmr.mirth.Specimen[clinicalItemKey=" + clinicalItemKey + "]";
    }

}
