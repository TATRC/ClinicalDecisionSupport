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
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Duane DeCouteau
 */
@Entity
@Table(name = "reporttemplate")
@NamedQueries({@NamedQuery(name = "Reporttemplate.findAll", query = "SELECT r FROM Reporttemplate r"), @NamedQuery(name = "Reporttemplate.findByReporttemplatekey", query = "SELECT r FROM Reporttemplate r WHERE r.reporttemplatekey = :reporttemplatekey"), @NamedQuery(name = "Reporttemplate.findById", query = "SELECT r FROM Reporttemplate r WHERE r.id = :id"), @NamedQuery(name = "Reporttemplate.findByLabel", query = "SELECT r FROM Reporttemplate r WHERE r.label = :label"), @NamedQuery(name = "Reporttemplate.findByRowversion", query = "SELECT r FROM Reporttemplate r WHERE r.rowversion = :rowversion"), @NamedQuery(name = "Reporttemplate.findByGuid", query = "SELECT r FROM Reporttemplate r WHERE r.guid = :guid")})
public class Reporttemplate implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "reporttemplatekey")
    private Short reporttemplatekey;
    @Basic(optional = false)
    @Column(name = "id")
    private String id;
    @Basic(optional = false)
    @Column(name = "label")
    private String label;
    @Lob
    @Column(name = "descr")
    private String descr;
    @Basic(optional = false)
    @Lob
    @Column(name = "template")
    private String template;
    @Basic(optional = false)
    @Column(name = "rowversion")
    private short rowversion;
    @Basic(optional = false)
    @Column(name = "guid")
    private String guid;

    public Reporttemplate() {
    }

    public Reporttemplate(Short reporttemplatekey) {
        this.reporttemplatekey = reporttemplatekey;
    }

    public Reporttemplate(Short reporttemplatekey, String id, String label, String template, short rowversion, String guid) {
        this.reporttemplatekey = reporttemplatekey;
        this.id = id;
        this.label = label;
        this.template = template;
        this.rowversion = rowversion;
        this.guid = guid;
    }

    public Short getReporttemplatekey() {
        return reporttemplatekey;
    }

    public void setReporttemplatekey(Short reporttemplatekey) {
        this.reporttemplatekey = reporttemplatekey;
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

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

    public short getRowversion() {
        return rowversion;
    }

    public void setRowversion(short rowversion) {
        this.rowversion = rowversion;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (reporttemplatekey != null ? reporttemplatekey.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Reporttemplate)) {
            return false;
        }
        Reporttemplate other = (Reporttemplate) object;
        if ((this.reporttemplatekey == null && other.reporttemplatekey != null) || (this.reporttemplatekey != null && !this.reporttemplatekey.equals(other.reporttemplatekey))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.hhs.fha.nhinc.kmr.mirth.Reporttemplate[reporttemplatekey=" + reporttemplatekey + "]";
    }

}
