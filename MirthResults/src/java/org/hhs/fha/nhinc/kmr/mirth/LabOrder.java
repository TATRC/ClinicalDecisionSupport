/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.hhs.fha.nhinc.kmr.mirth;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Duane DeCouteau
 */
@Entity
@Table(name = "lab_order")
@NamedQueries({@NamedQuery(name = "LabOrder.findAll", query = "SELECT l FROM LabOrder l"), @NamedQuery(name = "LabOrder.findByClinicalItemKey", query = "SELECT l FROM LabOrder l WHERE l.clinicalItemKey = :clinicalItemKey"), @NamedQuery(name = "LabOrder.findByPlacerOrderNumber", query = "SELECT l FROM LabOrder l WHERE l.placerOrderNumber = :placerOrderNumber"), @NamedQuery(name = "LabOrder.findByFillerOrderNumber", query = "SELECT l FROM LabOrder l WHERE l.fillerOrderNumber = :fillerOrderNumber"), @NamedQuery(name = "LabOrder.findByReportedTime", query = "SELECT l FROM LabOrder l WHERE l.reportedTime = :reportedTime")})
public class LabOrder implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "clinical_item_key")
    private BigDecimal clinicalItemKey;
    @Column(name = "placer_order_number")
    private String placerOrderNumber;
    @Column(name = "filler_order_number")
    private String fillerOrderNumber;
    @Column(name = "reported_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date reportedTime;

    public LabOrder() {
    }

    public LabOrder(BigDecimal clinicalItemKey) {
        this.clinicalItemKey = clinicalItemKey;
    }

    public BigDecimal getClinicalItemKey() {
        return clinicalItemKey;
    }

    public void setClinicalItemKey(BigDecimal clinicalItemKey) {
        this.clinicalItemKey = clinicalItemKey;
    }

    public String getPlacerOrderNumber() {
        return placerOrderNumber;
    }

    public void setPlacerOrderNumber(String placerOrderNumber) {
        this.placerOrderNumber = placerOrderNumber;
    }

    public String getFillerOrderNumber() {
        return fillerOrderNumber;
    }

    public void setFillerOrderNumber(String fillerOrderNumber) {
        this.fillerOrderNumber = fillerOrderNumber;
    }

    public Date getReportedTime() {
        return reportedTime;
    }

    public void setReportedTime(Date reportedTime) {
        this.reportedTime = reportedTime;
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
        if (!(object instanceof LabOrder)) {
            return false;
        }
        LabOrder other = (LabOrder) object;
        if ((this.clinicalItemKey == null && other.clinicalItemKey != null) || (this.clinicalItemKey != null && !this.clinicalItemKey.equals(other.clinicalItemKey))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.hhs.fha.nhinc.kmr.mirth.LabOrder[clinicalItemKey=" + clinicalItemKey + "]";
    }

}
