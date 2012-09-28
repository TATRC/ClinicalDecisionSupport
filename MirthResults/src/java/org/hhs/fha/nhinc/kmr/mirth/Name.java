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
@Table(name = "name")
@NamedQueries({@NamedQuery(name = "Name.findAll", query = "SELECT n FROM Name n"), @NamedQuery(name = "Name.findByNamekey", query = "SELECT n FROM Name n WHERE n.namekey = :namekey"), @NamedQuery(name = "Name.findByLast", query = "SELECT n FROM Name n WHERE n.last = :last"), @NamedQuery(name = "Name.findByFirst", query = "SELECT n FROM Name n WHERE n.first = :first"), @NamedQuery(name = "Name.findByMiddle", query = "SELECT n FROM Name n WHERE n.middle = :middle"), @NamedQuery(name = "Name.findBySuffix", query = "SELECT n FROM Name n WHERE n.suffix = :suffix"), @NamedQuery(name = "Name.findByPrefix", query = "SELECT n FROM Name n WHERE n.prefix = :prefix"), @NamedQuery(name = "Name.findByDegree", query = "SELECT n FROM Name n WHERE n.degree = :degree"), @NamedQuery(name = "Name.findByEffectivedate", query = "SELECT n FROM Name n WHERE n.effectivedate = :effectivedate"), @NamedQuery(name = "Name.findByExpirationdate", query = "SELECT n FROM Name n WHERE n.expirationdate = :expirationdate"), @NamedQuery(name = "Name.findByRowversion", query = "SELECT n FROM Name n WHERE n.rowversion = :rowversion"), @NamedQuery(name = "Name.findByNametypekey", query = "SELECT n FROM Name n WHERE n.nametypekey = :nametypekey")})
public class Name implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "namekey")
    private BigDecimal namekey;
    @Column(name = "last")
    private String last;
    @Column(name = "first")
    private String first;
    @Column(name = "middle")
    private String middle;
    @Column(name = "suffix")
    private String suffix;
    @Column(name = "prefix")
    private String prefix;
    @Column(name = "degree")
    private String degree;
    @Column(name = "effectivedate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date effectivedate;
    @Column(name = "expirationdate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date expirationdate;
    @Column(name = "rowversion")
    private Short rowversion;
    @Column(name = "nametypekey")
    private BigDecimal nametypekey;

    public Name() {
    }

    public Name(BigDecimal namekey) {
        this.namekey = namekey;
    }

    public BigDecimal getNamekey() {
        return namekey;
    }

    public void setNamekey(BigDecimal namekey) {
        this.namekey = namekey;
    }

    public String getLast() {
        return last;
    }

    public void setLast(String last) {
        this.last = last;
    }

    public String getFirst() {
        return first;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    public String getMiddle() {
        return middle;
    }

    public void setMiddle(String middle) {
        this.middle = middle;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public Date getEffectivedate() {
        return effectivedate;
    }

    public void setEffectivedate(Date effectivedate) {
        this.effectivedate = effectivedate;
    }

    public Date getExpirationdate() {
        return expirationdate;
    }

    public void setExpirationdate(Date expirationdate) {
        this.expirationdate = expirationdate;
    }

    public Short getRowversion() {
        return rowversion;
    }

    public void setRowversion(Short rowversion) {
        this.rowversion = rowversion;
    }

    public BigDecimal getNametypekey() {
        return nametypekey;
    }

    public void setNametypekey(BigDecimal nametypekey) {
        this.nametypekey = nametypekey;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (namekey != null ? namekey.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Name)) {
            return false;
        }
        Name other = (Name) object;
        if ((this.namekey == null && other.namekey != null) || (this.namekey != null && !this.namekey.equals(other.namekey))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.hhs.fha.nhinc.kmr.mirth.Name[namekey=" + namekey + "]";
    }

}
