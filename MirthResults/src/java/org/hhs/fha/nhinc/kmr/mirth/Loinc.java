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
@Table(name = "loinc")
@NamedQueries({@NamedQuery(name = "Loinc.findAll", query = "SELECT l FROM Loinc l"), @NamedQuery(name = "Loinc.findByConceptKey", query = "SELECT l FROM Loinc l WHERE l.conceptKey = :conceptKey"), @NamedQuery(name = "Loinc.findByComponent", query = "SELECT l FROM Loinc l WHERE l.component = :component"), @NamedQuery(name = "Loinc.findByProperty", query = "SELECT l FROM Loinc l WHERE l.property = :property"), @NamedQuery(name = "Loinc.findByTimeAspect", query = "SELECT l FROM Loinc l WHERE l.timeAspect = :timeAspect"), @NamedQuery(name = "Loinc.findBySystem", query = "SELECT l FROM Loinc l WHERE l.system = :system"), @NamedQuery(name = "Loinc.findByScaleType", query = "SELECT l FROM Loinc l WHERE l.scaleType = :scaleType"), @NamedQuery(name = "Loinc.findByMethodType", query = "SELECT l FROM Loinc l WHERE l.methodType = :methodType"), @NamedQuery(name = "Loinc.findByClass1", query = "SELECT l FROM Loinc l WHERE l.class1 = :class1"), @NamedQuery(name = "Loinc.findBySource", query = "SELECT l FROM Loinc l WHERE l.source = :source"), @NamedQuery(name = "Loinc.findByShortname", query = "SELECT l FROM Loinc l WHERE l.shortname = :shortname"), @NamedQuery(name = "Loinc.findByRelatedNames", query = "SELECT l FROM Loinc l WHERE l.relatedNames = :relatedNames"), @NamedQuery(name = "Loinc.findByRelatedNames2", query = "SELECT l FROM Loinc l WHERE l.relatedNames2 = :relatedNames2"), @NamedQuery(name = "Loinc.findByOrderOrObservation", query = "SELECT l FROM Loinc l WHERE l.orderOrObservation = :orderOrObservation"), @NamedQuery(name = "Loinc.findByUnitsRequired", query = "SELECT l FROM Loinc l WHERE l.unitsRequired = :unitsRequired"), @NamedQuery(name = "Loinc.findByComments", query = "SELECT l FROM Loinc l WHERE l.comments = :comments"), @NamedQuery(name = "Loinc.findByMapTo", query = "SELECT l FROM Loinc l WHERE l.mapTo = :mapTo"), @NamedQuery(name = "Loinc.findByClassType", query = "SELECT l FROM Loinc l WHERE l.classType = :classType")})
public class Loinc implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "concept_key")
    private BigDecimal conceptKey;
    @Column(name = "component")
    private String component;
    @Column(name = "property")
    private String property;
    @Column(name = "time_aspect")
    private String timeAspect;
    @Column(name = "system")
    private String system;
    @Column(name = "scale_type")
    private String scaleType;
    @Column(name = "method_type")
    private String methodType;
    @Column(name = "class")
    private String class1;
    @Column(name = "source")
    private String source;
    @Column(name = "shortname")
    private String shortname;
    @Column(name = "related_names")
    private String relatedNames;
    @Column(name = "related_names2")
    private String relatedNames2;
    @Column(name = "order_or_observation")
    private String orderOrObservation;
    @Column(name = "units_required")
    private String unitsRequired;
    @Column(name = "comments")
    private String comments;
    @Column(name = "map_to")
    private String mapTo;
    @Column(name = "class_type")
    private Short classType;

    public Loinc() {
    }

    public Loinc(BigDecimal conceptKey) {
        this.conceptKey = conceptKey;
    }

    public BigDecimal getConceptKey() {
        return conceptKey;
    }

    public void setConceptKey(BigDecimal conceptKey) {
        this.conceptKey = conceptKey;
    }

    public String getComponent() {
        return component;
    }

    public void setComponent(String component) {
        this.component = component;
    }

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }

    public String getTimeAspect() {
        return timeAspect;
    }

    public void setTimeAspect(String timeAspect) {
        this.timeAspect = timeAspect;
    }

    public String getSystem() {
        return system;
    }

    public void setSystem(String system) {
        this.system = system;
    }

    public String getScaleType() {
        return scaleType;
    }

    public void setScaleType(String scaleType) {
        this.scaleType = scaleType;
    }

    public String getMethodType() {
        return methodType;
    }

    public void setMethodType(String methodType) {
        this.methodType = methodType;
    }

    public String getClass1() {
        return class1;
    }

    public void setClass1(String class1) {
        this.class1 = class1;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getShortname() {
        return shortname;
    }

    public void setShortname(String shortname) {
        this.shortname = shortname;
    }

    public String getRelatedNames() {
        return relatedNames;
    }

    public void setRelatedNames(String relatedNames) {
        this.relatedNames = relatedNames;
    }

    public String getRelatedNames2() {
        return relatedNames2;
    }

    public void setRelatedNames2(String relatedNames2) {
        this.relatedNames2 = relatedNames2;
    }

    public String getOrderOrObservation() {
        return orderOrObservation;
    }

    public void setOrderOrObservation(String orderOrObservation) {
        this.orderOrObservation = orderOrObservation;
    }

    public String getUnitsRequired() {
        return unitsRequired;
    }

    public void setUnitsRequired(String unitsRequired) {
        this.unitsRequired = unitsRequired;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getMapTo() {
        return mapTo;
    }

    public void setMapTo(String mapTo) {
        this.mapTo = mapTo;
    }

    public Short getClassType() {
        return classType;
    }

    public void setClassType(Short classType) {
        this.classType = classType;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (conceptKey != null ? conceptKey.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Loinc)) {
            return false;
        }
        Loinc other = (Loinc) object;
        if ((this.conceptKey == null && other.conceptKey != null) || (this.conceptKey != null && !this.conceptKey.equals(other.conceptKey))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.hhs.fha.nhinc.kmr.mirth.Loinc[conceptKey=" + conceptKey + "]";
    }

}
