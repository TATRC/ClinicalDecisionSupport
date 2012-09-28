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
@Table(name = "subject_group_type")
@NamedQueries({@NamedQuery(name = "SubjectGroupType.findAll", query = "SELECT s FROM SubjectGroupType s"), @NamedQuery(name = "SubjectGroupType.findBySubjectGroupTypeKey", query = "SELECT s FROM SubjectGroupType s WHERE s.subjectGroupTypeKey = :subjectGroupTypeKey"), @NamedQuery(name = "SubjectGroupType.findByLabel", query = "SELECT s FROM SubjectGroupType s WHERE s.label = :label"), @NamedQuery(name = "SubjectGroupType.findByDescription", query = "SELECT s FROM SubjectGroupType s WHERE s.description = :description")})
public class SubjectGroupType implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "subject_group_type_key")
    private BigDecimal subjectGroupTypeKey;
    @Basic(optional = false)
    @Column(name = "label")
    private String label;
    @Column(name = "description")
    private String description;

    public SubjectGroupType() {
    }

    public SubjectGroupType(BigDecimal subjectGroupTypeKey) {
        this.subjectGroupTypeKey = subjectGroupTypeKey;
    }

    public SubjectGroupType(BigDecimal subjectGroupTypeKey, String label) {
        this.subjectGroupTypeKey = subjectGroupTypeKey;
        this.label = label;
    }

    public BigDecimal getSubjectGroupTypeKey() {
        return subjectGroupTypeKey;
    }

    public void setSubjectGroupTypeKey(BigDecimal subjectGroupTypeKey) {
        this.subjectGroupTypeKey = subjectGroupTypeKey;
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
        hash += (subjectGroupTypeKey != null ? subjectGroupTypeKey.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SubjectGroupType)) {
            return false;
        }
        SubjectGroupType other = (SubjectGroupType) object;
        if ((this.subjectGroupTypeKey == null && other.subjectGroupTypeKey != null) || (this.subjectGroupTypeKey != null && !this.subjectGroupTypeKey.equals(other.subjectGroupTypeKey))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.hhs.fha.nhinc.kmr.mirth.SubjectGroupType[subjectGroupTypeKey=" + subjectGroupTypeKey + "]";
    }

}
