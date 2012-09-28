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
@Table(name = "observation_group")
@NamedQueries({@NamedQuery(name = "ObservationGroup.findAll", query = "SELECT o FROM ObservationGroup o"), @NamedQuery(name = "ObservationGroup.findByClinicalItemKey", query = "SELECT o FROM ObservationGroup o WHERE o.clinicalItemKey = :clinicalItemKey"), @NamedQuery(name = "ObservationGroup.findByRequisitionNumber", query = "SELECT o FROM ObservationGroup o WHERE o.requisitionNumber = :requisitionNumber"), @NamedQuery(name = "ObservationGroup.findByPlacerField1", query = "SELECT o FROM ObservationGroup o WHERE o.placerField1 = :placerField1"), @NamedQuery(name = "ObservationGroup.findByPlacerField2", query = "SELECT o FROM ObservationGroup o WHERE o.placerField2 = :placerField2"), @NamedQuery(name = "ObservationGroup.findByFillerField1", query = "SELECT o FROM ObservationGroup o WHERE o.fillerField1 = :fillerField1"), @NamedQuery(name = "ObservationGroup.findByFillerField2", query = "SELECT o FROM ObservationGroup o WHERE o.fillerField2 = :fillerField2")})
public class ObservationGroup implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "clinical_item_key")
    private BigDecimal clinicalItemKey;
    @Column(name = "requisition_number")
    private String requisitionNumber;
    @Column(name = "placer_field_1")
    private String placerField1;
    @Column(name = "placer_field_2")
    private String placerField2;
    @Column(name = "filler_field_1")
    private String fillerField1;
    @Column(name = "filler_field_2")
    private String fillerField2;

    public ObservationGroup() {
    }

    public ObservationGroup(BigDecimal clinicalItemKey) {
        this.clinicalItemKey = clinicalItemKey;
    }

    public BigDecimal getClinicalItemKey() {
        return clinicalItemKey;
    }

    public void setClinicalItemKey(BigDecimal clinicalItemKey) {
        this.clinicalItemKey = clinicalItemKey;
    }

    public String getRequisitionNumber() {
        return requisitionNumber;
    }

    public void setRequisitionNumber(String requisitionNumber) {
        this.requisitionNumber = requisitionNumber;
    }

    public String getPlacerField1() {
        return placerField1;
    }

    public void setPlacerField1(String placerField1) {
        this.placerField1 = placerField1;
    }

    public String getPlacerField2() {
        return placerField2;
    }

    public void setPlacerField2(String placerField2) {
        this.placerField2 = placerField2;
    }

    public String getFillerField1() {
        return fillerField1;
    }

    public void setFillerField1(String fillerField1) {
        this.fillerField1 = fillerField1;
    }

    public String getFillerField2() {
        return fillerField2;
    }

    public void setFillerField2(String fillerField2) {
        this.fillerField2 = fillerField2;
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
        if (!(object instanceof ObservationGroup)) {
            return false;
        }
        ObservationGroup other = (ObservationGroup) object;
        if ((this.clinicalItemKey == null && other.clinicalItemKey != null) || (this.clinicalItemKey != null && !this.clinicalItemKey.equals(other.clinicalItemKey))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.hhs.fha.nhinc.kmr.mirth.ObservationGroup[clinicalItemKey=" + clinicalItemKey + "]";
    }

}
