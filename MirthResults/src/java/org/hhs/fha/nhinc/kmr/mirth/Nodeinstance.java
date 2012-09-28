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
@Table(name = "nodeinstance")
@NamedQueries({@NamedQuery(name = "Nodeinstance.findAll", query = "SELECT n FROM Nodeinstance n"), @NamedQuery(name = "Nodeinstance.findByNodeinstancekey", query = "SELECT n FROM Nodeinstance n WHERE n.nodeinstancekey = :nodeinstancekey"), @NamedQuery(name = "Nodeinstance.findById", query = "SELECT n FROM Nodeinstance n WHERE n.id = :id"), @NamedQuery(name = "Nodeinstance.findByLabel", query = "SELECT n FROM Nodeinstance n WHERE n.label = :label"), @NamedQuery(name = "Nodeinstance.findByDescr", query = "SELECT n FROM Nodeinstance n WHERE n.descr = :descr"), @NamedQuery(name = "Nodeinstance.findByHostname", query = "SELECT n FROM Nodeinstance n WHERE n.hostname = :hostname"), @NamedQuery(name = "Nodeinstance.findByMirthadminport", query = "SELECT n FROM Nodeinstance n WHERE n.mirthadminport = :mirthadminport"), @NamedQuery(name = "Nodeinstance.findByResultslistenerport", query = "SELECT n FROM Nodeinstance n WHERE n.resultslistenerport = :resultslistenerport"), @NamedQuery(name = "Nodeinstance.findBySynchlistenerport", query = "SELECT n FROM Nodeinstance n WHERE n.synchlistenerport = :synchlistenerport"), @NamedQuery(name = "Nodeinstance.findByRowversion", query = "SELECT n FROM Nodeinstance n WHERE n.rowversion = :rowversion"), @NamedQuery(name = "Nodeinstance.findByGuid", query = "SELECT n FROM Nodeinstance n WHERE n.guid = :guid")})
public class Nodeinstance implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "nodeinstancekey")
    private Short nodeinstancekey;
    @Basic(optional = false)
    @Column(name = "id")
    private String id;
    @Basic(optional = false)
    @Column(name = "label")
    private String label;
    @Column(name = "descr")
    private String descr;
    @Basic(optional = false)
    @Column(name = "hostname")
    private String hostname;
    @Basic(optional = false)
    @Column(name = "mirthadminport")
    private int mirthadminport;
    @Basic(optional = false)
    @Column(name = "resultslistenerport")
    private int resultslistenerport;
    @Basic(optional = false)
    @Column(name = "synchlistenerport")
    private int synchlistenerport;
    @Basic(optional = false)
    @Column(name = "rowversion")
    private short rowversion;
    @Basic(optional = false)
    @Column(name = "guid")
    private String guid;

    public Nodeinstance() {
    }

    public Nodeinstance(Short nodeinstancekey) {
        this.nodeinstancekey = nodeinstancekey;
    }

    public Nodeinstance(Short nodeinstancekey, String id, String label, String hostname, int mirthadminport, int resultslistenerport, int synchlistenerport, short rowversion, String guid) {
        this.nodeinstancekey = nodeinstancekey;
        this.id = id;
        this.label = label;
        this.hostname = hostname;
        this.mirthadminport = mirthadminport;
        this.resultslistenerport = resultslistenerport;
        this.synchlistenerport = synchlistenerport;
        this.rowversion = rowversion;
        this.guid = guid;
    }

    public Short getNodeinstancekey() {
        return nodeinstancekey;
    }

    public void setNodeinstancekey(Short nodeinstancekey) {
        this.nodeinstancekey = nodeinstancekey;
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

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public int getMirthadminport() {
        return mirthadminport;
    }

    public void setMirthadminport(int mirthadminport) {
        this.mirthadminport = mirthadminport;
    }

    public int getResultslistenerport() {
        return resultslistenerport;
    }

    public void setResultslistenerport(int resultslistenerport) {
        this.resultslistenerport = resultslistenerport;
    }

    public int getSynchlistenerport() {
        return synchlistenerport;
    }

    public void setSynchlistenerport(int synchlistenerport) {
        this.synchlistenerport = synchlistenerport;
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
        hash += (nodeinstancekey != null ? nodeinstancekey.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Nodeinstance)) {
            return false;
        }
        Nodeinstance other = (Nodeinstance) object;
        if ((this.nodeinstancekey == null && other.nodeinstancekey != null) || (this.nodeinstancekey != null && !this.nodeinstancekey.equals(other.nodeinstancekey))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.hhs.fha.nhinc.kmr.mirth.Nodeinstance[nodeinstancekey=" + nodeinstancekey + "]";
    }

}
