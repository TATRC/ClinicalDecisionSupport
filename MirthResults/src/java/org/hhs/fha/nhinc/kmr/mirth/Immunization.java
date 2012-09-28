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
@Table(name = "immunization")
@NamedQueries({@NamedQuery(name = "Immunization.findAll", query = "SELECT i FROM Immunization i"), @NamedQuery(name = "Immunization.findByClinicalItemKey", query = "SELECT i FROM Immunization i WHERE i.clinicalItemKey = :clinicalItemKey"), @NamedQuery(name = "Immunization.findByLotNumber", query = "SELECT i FROM Immunization i WHERE i.lotNumber = :lotNumber"), @NamedQuery(name = "Immunization.findByRoute", query = "SELECT i FROM Immunization i WHERE i.route = :route"), @NamedQuery(name = "Immunization.findByManufacturer", query = "SELECT i FROM Immunization i WHERE i.manufacturer = :manufacturer"), @NamedQuery(name = "Immunization.findByAdministrationSite", query = "SELECT i FROM Immunization i WHERE i.administrationSite = :administrationSite"), @NamedQuery(name = "Immunization.findByDose", query = "SELECT i FROM Immunization i WHERE i.dose = :dose")})
public class Immunization implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "clinical_item_key")
    private BigDecimal clinicalItemKey;
    @Column(name = "lot_number")
    private String lotNumber;
    @Column(name = "route")
    private String route;
    @Column(name = "manufacturer")
    private String manufacturer;
    @Column(name = "administration_site")
    private String administrationSite;
    @Column(name = "dose")
    private String dose;

    public Immunization() {
    }

    public Immunization(BigDecimal clinicalItemKey) {
        this.clinicalItemKey = clinicalItemKey;
    }

    public BigDecimal getClinicalItemKey() {
        return clinicalItemKey;
    }

    public void setClinicalItemKey(BigDecimal clinicalItemKey) {
        this.clinicalItemKey = clinicalItemKey;
    }

    public String getLotNumber() {
        return lotNumber;
    }

    public void setLotNumber(String lotNumber) {
        this.lotNumber = lotNumber;
    }

    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getAdministrationSite() {
        return administrationSite;
    }

    public void setAdministrationSite(String administrationSite) {
        this.administrationSite = administrationSite;
    }

    public String getDose() {
        return dose;
    }

    public void setDose(String dose) {
        this.dose = dose;
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
        if (!(object instanceof Immunization)) {
            return false;
        }
        Immunization other = (Immunization) object;
        if ((this.clinicalItemKey == null && other.clinicalItemKey != null) || (this.clinicalItemKey != null && !this.clinicalItemKey.equals(other.clinicalItemKey))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.hhs.fha.nhinc.kmr.mirth.Immunization[clinicalItemKey=" + clinicalItemKey + "]";
    }

}
