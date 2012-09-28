/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.hhs.fha.nhinc.kmr.mirth;

import java.io.Serializable;
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
@Table(name = "clinical_item_type")
@NamedQueries({@NamedQuery(name = "ClinicalItemType.findAll", query = "SELECT c FROM ClinicalItemType c"), @NamedQuery(name = "ClinicalItemType.findById", query = "SELECT c FROM ClinicalItemType c WHERE c.id = :id"), @NamedQuery(name = "ClinicalItemType.findByLabel", query = "SELECT c FROM ClinicalItemType c WHERE c.label = :label"), @NamedQuery(name = "ClinicalItemType.findByDescription", query = "SELECT c FROM ClinicalItemType c WHERE c.description = :description")})
public class ClinicalItemType implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private String id;
    @Column(name = "label")
    private String label;
    @Column(name = "description")
    private String description;

    public ClinicalItemType() {
    }

    public ClinicalItemType(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
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
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ClinicalItemType)) {
            return false;
        }
        ClinicalItemType other = (ClinicalItemType) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.hhs.fha.nhinc.kmr.mirth.ClinicalItemType[id=" + id + "]";
    }

}
