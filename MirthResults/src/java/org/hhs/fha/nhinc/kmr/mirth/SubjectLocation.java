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
@Table(name = "subject_location")
@NamedQueries({@NamedQuery(name = "SubjectLocation.findAll", query = "SELECT s FROM SubjectLocation s"), @NamedQuery(name = "SubjectLocation.findBySubjectLocationKey", query = "SELECT s FROM SubjectLocation s WHERE s.subjectLocationKey = :subjectLocationKey"), @NamedQuery(name = "SubjectLocation.findByLocationIdentifier", query = "SELECT s FROM SubjectLocation s WHERE s.locationIdentifier = :locationIdentifier"), @NamedQuery(name = "SubjectLocation.findByPointOfCare", query = "SELECT s FROM SubjectLocation s WHERE s.pointOfCare = :pointOfCare"), @NamedQuery(name = "SubjectLocation.findByRoom", query = "SELECT s FROM SubjectLocation s WHERE s.room = :room"), @NamedQuery(name = "SubjectLocation.findByBed", query = "SELECT s FROM SubjectLocation s WHERE s.bed = :bed"), @NamedQuery(name = "SubjectLocation.findByFacility", query = "SELECT s FROM SubjectLocation s WHERE s.facility = :facility"), @NamedQuery(name = "SubjectLocation.findByStatus", query = "SELECT s FROM SubjectLocation s WHERE s.status = :status"), @NamedQuery(name = "SubjectLocation.findByLocationType", query = "SELECT s FROM SubjectLocation s WHERE s.locationType = :locationType"), @NamedQuery(name = "SubjectLocation.findByBuilding", query = "SELECT s FROM SubjectLocation s WHERE s.building = :building"), @NamedQuery(name = "SubjectLocation.findByFloor", query = "SELECT s FROM SubjectLocation s WHERE s.floor = :floor"), @NamedQuery(name = "SubjectLocation.findByDescription", query = "SELECT s FROM SubjectLocation s WHERE s.description = :description")})
public class SubjectLocation implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "subject_location_key")
    private BigDecimal subjectLocationKey;
    @Column(name = "location_identifier")
    private String locationIdentifier;
    @Column(name = "point_of_care")
    private String pointOfCare;
    @Column(name = "room")
    private String room;
    @Column(name = "bed")
    private String bed;
    @Column(name = "facility")
    private String facility;
    @Column(name = "status")
    private String status;
    @Column(name = "location_type")
    private String locationType;
    @Column(name = "building")
    private String building;
    @Column(name = "floor")
    private String floor;
    @Column(name = "description")
    private String description;

    public SubjectLocation() {
    }

    public SubjectLocation(BigDecimal subjectLocationKey) {
        this.subjectLocationKey = subjectLocationKey;
    }

    public BigDecimal getSubjectLocationKey() {
        return subjectLocationKey;
    }

    public void setSubjectLocationKey(BigDecimal subjectLocationKey) {
        this.subjectLocationKey = subjectLocationKey;
    }

    public String getLocationIdentifier() {
        return locationIdentifier;
    }

    public void setLocationIdentifier(String locationIdentifier) {
        this.locationIdentifier = locationIdentifier;
    }

    public String getPointOfCare() {
        return pointOfCare;
    }

    public void setPointOfCare(String pointOfCare) {
        this.pointOfCare = pointOfCare;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getBed() {
        return bed;
    }

    public void setBed(String bed) {
        this.bed = bed;
    }

    public String getFacility() {
        return facility;
    }

    public void setFacility(String facility) {
        this.facility = facility;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getLocationType() {
        return locationType;
    }

    public void setLocationType(String locationType) {
        this.locationType = locationType;
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (subjectLocationKey != null ? subjectLocationKey.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SubjectLocation)) {
            return false;
        }
        SubjectLocation other = (SubjectLocation) object;
        if ((this.subjectLocationKey == null && other.subjectLocationKey != null) || (this.subjectLocationKey != null && !this.subjectLocationKey.equals(other.subjectLocationKey))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.hhs.fha.nhinc.kmr.mirth.SubjectLocation[subjectLocationKey=" + subjectLocationKey + "]";
    }

}
