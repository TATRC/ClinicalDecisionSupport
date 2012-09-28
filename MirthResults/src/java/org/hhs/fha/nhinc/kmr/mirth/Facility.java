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
@Table(name = "facility")
@NamedQueries({@NamedQuery(name = "Facility.findAll", query = "SELECT f FROM Facility f"), @NamedQuery(name = "Facility.findByFacilitykey", query = "SELECT f FROM Facility f WHERE f.facilitykey = :facilitykey"), @NamedQuery(name = "Facility.findByGuid", query = "SELECT f FROM Facility f WHERE f.guid = :guid"), @NamedQuery(name = "Facility.findByParentfacilitykey", query = "SELECT f FROM Facility f WHERE f.parentfacilitykey = :parentfacilitykey"), @NamedQuery(name = "Facility.findByReporttemplatekey", query = "SELECT f FROM Facility f WHERE f.reporttemplatekey = :reporttemplatekey"), @NamedQuery(name = "Facility.findById", query = "SELECT f FROM Facility f WHERE f.id = :id"), @NamedQuery(name = "Facility.findByGlobalid", query = "SELECT f FROM Facility f WHERE f.globalid = :globalid"), @NamedQuery(name = "Facility.findByGlobalidauthority", query = "SELECT f FROM Facility f WHERE f.globalidauthority = :globalidauthority"), @NamedQuery(name = "Facility.findByLabel", query = "SELECT f FROM Facility f WHERE f.label = :label"), @NamedQuery(name = "Facility.findByShortname", query = "SELECT f FROM Facility f WHERE f.shortname = :shortname"), @NamedQuery(name = "Facility.findByContactname", query = "SELECT f FROM Facility f WHERE f.contactname = :contactname"), @NamedQuery(name = "Facility.findByAddress1", query = "SELECT f FROM Facility f WHERE f.address1 = :address1"), @NamedQuery(name = "Facility.findByAddress2", query = "SELECT f FROM Facility f WHERE f.address2 = :address2"), @NamedQuery(name = "Facility.findByLocality", query = "SELECT f FROM Facility f WHERE f.locality = :locality"), @NamedQuery(name = "Facility.findByRegion", query = "SELECT f FROM Facility f WHERE f.region = :region"), @NamedQuery(name = "Facility.findByPostalcode", query = "SELECT f FROM Facility f WHERE f.postalcode = :postalcode"), @NamedQuery(name = "Facility.findByIsocountrycode", query = "SELECT f FROM Facility f WHERE f.isocountrycode = :isocountrycode"), @NamedQuery(name = "Facility.findByVoicecontact", query = "SELECT f FROM Facility f WHERE f.voicecontact = :voicecontact"), @NamedQuery(name = "Facility.findByEmailcontact", query = "SELECT f FROM Facility f WHERE f.emailcontact = :emailcontact"), @NamedQuery(name = "Facility.findByFaxcontact", query = "SELECT f FROM Facility f WHERE f.faxcontact = :faxcontact"), @NamedQuery(name = "Facility.findByMedicaldirector", query = "SELECT f FROM Facility f WHERE f.medicaldirector = :medicaldirector"), @NamedQuery(name = "Facility.findByRowversion", query = "SELECT f FROM Facility f WHERE f.rowversion = :rowversion"), @NamedQuery(name = "Facility.findByFacilitytypekey", query = "SELECT f FROM Facility f WHERE f.facilitytypekey = :facilitytypekey"), @NamedQuery(name = "Facility.findByEnableprinting", query = "SELECT f FROM Facility f WHERE f.enableprinting = :enableprinting"), @NamedQuery(name = "Facility.findByEnabledownloading", query = "SELECT f FROM Facility f WHERE f.enabledownloading = :enabledownloading"), @NamedQuery(name = "Facility.findByAttachedprintername", query = "SELECT f FROM Facility f WHERE f.attachedprintername = :attachedprintername"), @NamedQuery(name = "Facility.findByEnablempi", query = "SELECT f FROM Facility f WHERE f.enablempi = :enablempi"), @NamedQuery(name = "Facility.findByEnablemessageemail", query = "SELECT f FROM Facility f WHERE f.enablemessageemail = :enablemessageemail"), @NamedQuery(name = "Facility.findByEnablemessagefax", query = "SELECT f FROM Facility f WHERE f.enablemessagefax = :enablemessagefax"), @NamedQuery(name = "Facility.findByEnablemessagehie", query = "SELECT f FROM Facility f WHERE f.enablemessagehie = :enablemessagehie")})
public class Facility implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "facilitykey")
    private Integer facilitykey;
    @Basic(optional = false)
    @Column(name = "guid")
    private String guid;
    @Basic(optional = false)
    @Column(name = "parentfacilitykey")
    private int parentfacilitykey;
    @Column(name = "reporttemplatekey")
    private Short reporttemplatekey;
    @Basic(optional = false)
    @Column(name = "id")
    private String id;
    @Column(name = "globalid")
    private String globalid;
    @Column(name = "globalidauthority")
    private String globalidauthority;
    @Basic(optional = false)
    @Column(name = "label")
    private String label;
    @Basic(optional = false)
    @Column(name = "shortname")
    private String shortname;
    @Lob
    @Column(name = "descr")
    private String descr;
    @Column(name = "contactname")
    private String contactname;
    @Column(name = "address1")
    private String address1;
    @Column(name = "address2")
    private String address2;
    @Column(name = "locality")
    private String locality;
    @Column(name = "region")
    private String region;
    @Column(name = "postalcode")
    private String postalcode;
    @Column(name = "isocountrycode")
    private String isocountrycode;
    @Column(name = "voicecontact")
    private String voicecontact;
    @Column(name = "emailcontact")
    private String emailcontact;
    @Column(name = "faxcontact")
    private String faxcontact;
    @Lob
    @Column(name = "encounteridexpr")
    private String encounteridexpr;
    @Lob
    @Column(name = "provideraliasexpr")
    private String provideraliasexpr;
    @Lob
    @Column(name = "subjectaliasexpr")
    private String subjectaliasexpr;
    @Column(name = "medicaldirector")
    private String medicaldirector;
    @Lob
    @Column(name = "logouri")
    private String logouri;
    @Basic(optional = false)
    @Column(name = "rowversion")
    private short rowversion;
    @Basic(optional = false)
    @Column(name = "facilitytypekey")
    private short facilitytypekey;
    @Column(name = "enableprinting")
    private Short enableprinting;
    @Column(name = "enabledownloading")
    private Short enabledownloading;
    @Column(name = "attachedprintername")
    private String attachedprintername;
    @Column(name = "enablempi")
    private Short enablempi;
    @Column(name = "enablemessageemail")
    private Short enablemessageemail;
    @Column(name = "enablemessagefax")
    private Short enablemessagefax;
    @Column(name = "enablemessagehie")
    private Short enablemessagehie;

    public Facility() {
    }

    public Facility(Integer facilitykey) {
        this.facilitykey = facilitykey;
    }

    public Facility(Integer facilitykey, String guid, int parentfacilitykey, String id, String label, String shortname, short rowversion, short facilitytypekey) {
        this.facilitykey = facilitykey;
        this.guid = guid;
        this.parentfacilitykey = parentfacilitykey;
        this.id = id;
        this.label = label;
        this.shortname = shortname;
        this.rowversion = rowversion;
        this.facilitytypekey = facilitytypekey;
    }

    public Integer getFacilitykey() {
        return facilitykey;
    }

    public void setFacilitykey(Integer facilitykey) {
        this.facilitykey = facilitykey;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public int getParentfacilitykey() {
        return parentfacilitykey;
    }

    public void setParentfacilitykey(int parentfacilitykey) {
        this.parentfacilitykey = parentfacilitykey;
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

    public String getGlobalid() {
        return globalid;
    }

    public void setGlobalid(String globalid) {
        this.globalid = globalid;
    }

    public String getGlobalidauthority() {
        return globalidauthority;
    }

    public void setGlobalidauthority(String globalidauthority) {
        this.globalidauthority = globalidauthority;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getShortname() {
        return shortname;
    }

    public void setShortname(String shortname) {
        this.shortname = shortname;
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

    public String getContactname() {
        return contactname;
    }

    public void setContactname(String contactname) {
        this.contactname = contactname;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getLocality() {
        return locality;
    }

    public void setLocality(String locality) {
        this.locality = locality;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getPostalcode() {
        return postalcode;
    }

    public void setPostalcode(String postalcode) {
        this.postalcode = postalcode;
    }

    public String getIsocountrycode() {
        return isocountrycode;
    }

    public void setIsocountrycode(String isocountrycode) {
        this.isocountrycode = isocountrycode;
    }

    public String getVoicecontact() {
        return voicecontact;
    }

    public void setVoicecontact(String voicecontact) {
        this.voicecontact = voicecontact;
    }

    public String getEmailcontact() {
        return emailcontact;
    }

    public void setEmailcontact(String emailcontact) {
        this.emailcontact = emailcontact;
    }

    public String getFaxcontact() {
        return faxcontact;
    }

    public void setFaxcontact(String faxcontact) {
        this.faxcontact = faxcontact;
    }

    public String getEncounteridexpr() {
        return encounteridexpr;
    }

    public void setEncounteridexpr(String encounteridexpr) {
        this.encounteridexpr = encounteridexpr;
    }

    public String getProvideraliasexpr() {
        return provideraliasexpr;
    }

    public void setProvideraliasexpr(String provideraliasexpr) {
        this.provideraliasexpr = provideraliasexpr;
    }

    public String getSubjectaliasexpr() {
        return subjectaliasexpr;
    }

    public void setSubjectaliasexpr(String subjectaliasexpr) {
        this.subjectaliasexpr = subjectaliasexpr;
    }

    public String getMedicaldirector() {
        return medicaldirector;
    }

    public void setMedicaldirector(String medicaldirector) {
        this.medicaldirector = medicaldirector;
    }

    public String getLogouri() {
        return logouri;
    }

    public void setLogouri(String logouri) {
        this.logouri = logouri;
    }

    public short getRowversion() {
        return rowversion;
    }

    public void setRowversion(short rowversion) {
        this.rowversion = rowversion;
    }

    public short getFacilitytypekey() {
        return facilitytypekey;
    }

    public void setFacilitytypekey(short facilitytypekey) {
        this.facilitytypekey = facilitytypekey;
    }

    public Short getEnableprinting() {
        return enableprinting;
    }

    public void setEnableprinting(Short enableprinting) {
        this.enableprinting = enableprinting;
    }

    public Short getEnabledownloading() {
        return enabledownloading;
    }

    public void setEnabledownloading(Short enabledownloading) {
        this.enabledownloading = enabledownloading;
    }

    public String getAttachedprintername() {
        return attachedprintername;
    }

    public void setAttachedprintername(String attachedprintername) {
        this.attachedprintername = attachedprintername;
    }

    public Short getEnablempi() {
        return enablempi;
    }

    public void setEnablempi(Short enablempi) {
        this.enablempi = enablempi;
    }

    public Short getEnablemessageemail() {
        return enablemessageemail;
    }

    public void setEnablemessageemail(Short enablemessageemail) {
        this.enablemessageemail = enablemessageemail;
    }

    public Short getEnablemessagefax() {
        return enablemessagefax;
    }

    public void setEnablemessagefax(Short enablemessagefax) {
        this.enablemessagefax = enablemessagefax;
    }

    public Short getEnablemessagehie() {
        return enablemessagehie;
    }

    public void setEnablemessagehie(Short enablemessagehie) {
        this.enablemessagehie = enablemessagehie;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (facilitykey != null ? facilitykey.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Facility)) {
            return false;
        }
        Facility other = (Facility) object;
        if ((this.facilitykey == null && other.facilitykey != null) || (this.facilitykey != null && !this.facilitykey.equals(other.facilitykey))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.hhs.fha.nhinc.kmr.mirth.Facility[facilitykey=" + facilitykey + "]";
    }

}
