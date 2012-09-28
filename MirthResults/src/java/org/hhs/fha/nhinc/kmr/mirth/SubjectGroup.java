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
@Table(name = "subject_group")
@NamedQueries({@NamedQuery(name = "SubjectGroup.findAll", query = "SELECT s FROM SubjectGroup s"), @NamedQuery(name = "SubjectGroup.findBySubjectGroupKey", query = "SELECT s FROM SubjectGroup s WHERE s.subjectGroupKey = :subjectGroupKey"), @NamedQuery(name = "SubjectGroup.findById", query = "SELECT s FROM SubjectGroup s WHERE s.id = :id"), @NamedQuery(name = "SubjectGroup.findByLabel", query = "SELECT s FROM SubjectGroup s WHERE s.label = :label"), @NamedQuery(name = "SubjectGroup.findByDescription", query = "SELECT s FROM SubjectGroup s WHERE s.description = :description"), @NamedQuery(name = "SubjectGroup.findBySubjectGroupTypeKey", query = "SELECT s FROM SubjectGroup s WHERE s.subjectGroupTypeKey = :subjectGroupTypeKey")})
public class SubjectGroup implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "subject_group_key")
    private BigDecimal subjectGroupKey;
    @Basic(optional = false)
    @Column(name = "id")
    private String id;
    @Basic(optional = false)
    @Column(name = "label")
    private String label;
    @Column(name = "description")
    private String description;
    @Column(name = "subject_group_type_key")
    private BigDecimal subjectGroupTypeKey;

    public SubjectGroup() {
    }

    public SubjectGroup(BigDecimal subjectGroupKey) {
        this.subjectGroupKey = subjectGroupKey;
    }

    public SubjectGroup(BigDecimal subjectGroupKey, String id, String label) {
        this.subjectGroupKey = subjectGroupKey;
        this.id = id;
        this.label = label;
    }

    public BigDecimal getSubjectGroupKey() {
        return subjectGroupKey;
    }

    public void setSubjectGroupKey(BigDecimal subjectGroupKey) {
        this.subjectGroupKey = subjectGroupKey;
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

    public BigDecimal getSubjectGroupTypeKey() {
        return subjectGroupTypeKey;
    }

    public void setSubjectGroupTypeKey(BigDecimal subjectGroupTypeKey) {
        this.subjectGroupTypeKey = subjectGroupTypeKey;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (subjectGroupKey != null ? subjectGroupKey.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SubjectGroup)) {
            return false;
        }
        SubjectGroup other = (SubjectGroup) object;
        if ((this.subjectGroupKey == null && other.subjectGroupKey != null) || (this.subjectGroupKey != null && !this.subjectGroupKey.equals(other.subjectGroupKey))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.hhs.fha.nhinc.kmr.mirth.SubjectGroup[subjectGroupKey=" + subjectGroupKey + "]";
    }

}
