/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mil.navy.med.dzreg.jpa;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author kim
 */
@Entity
@Table(name = "DZ_PCM")
@NamedQueries({
  @NamedQuery(name = "DzPcm.findAll", query = "SELECT d FROM DzPcm d"),
  @NamedQuery(name = "DzPcm.findByPcmid", query = "SELECT d FROM DzPcm d WHERE d.pcmid = :pcmid"),
  @NamedQuery(name = "DzPcm.findByPcm", query = "SELECT d FROM DzPcm d WHERE d.pcm = :pcm")
//  @NamedQuery(name = "DzPcm.findByInsertedDt", query = "SELECT d FROM DzPcm d WHERE d.insertedDt = :insertedDt"),
//  @NamedQuery(name = "DzPcm.findByHospitalLocationId", query = "SELECT d FROM DzPcm d WHERE d.hospitalLocationId = :hospitalLocationId"),
//  @NamedQuery(name = "DzPcm.findByDataSource", query = "SELECT d FROM DzPcm d WHERE d.dataSource = :dataSource"),
//  @NamedQuery(name = "DzPcm.findByProviderIdCode", query = "SELECT d FROM DzPcm d WHERE d.providerIdCode = :providerIdCode"),
//  @NamedQuery(name = "DzPcm.findByProviderSidr", query = "SELECT d FROM DzPcm d WHERE d.providerSidr = :providerSidr"),
//  @NamedQuery(name = "DzPcm.findByFlag", query = "SELECT d FROM DzPcm d WHERE d.flag = :flag")
})
public class DzPcm implements Serializable {
  private static final long serialVersionUID = 1L;
  @Id
  @Basic(optional = false)
  @Column(name = "PCMID")
  private Long pcmid;
  @Column(name = "PCM")
  private String pcm;
  @Column(name = "INSERTED_DT")
  @Temporal(TemporalType.DATE)
  private Date insertedDt;
  @Column(name = "HOSPITAL_LOCATION_ID")
  private BigInteger hospitalLocationId;
  @Basic(optional = false)
  @Column(name = "DATA_SOURCE")
  private String dataSource;
  @Column(name = "PROVIDER_ID_CODE")
  private String providerIdCode;
  @Column(name = "PROVIDER_SIDR")
  private String providerSidr;
  @Column(name = "FLAG")
  private Integer flag;
  //@OneToMany(mappedBy = "pcmid", fetch = FetchType.EAGER)
  //private Collection<DzPatients> dzPatientsCollection;

  public DzPcm() {
  }

  public DzPcm(Long pcmid) {
    this.pcmid = pcmid;
  }

  public DzPcm(Long pcmid, String dataSource) {
    this.pcmid = pcmid;
    this.dataSource = dataSource;
  }

  public Long getPcmid() {
    return pcmid;
  }

  public void setPcmid(Long pcmid) {
    this.pcmid = pcmid;
  }

  public String getPcm() {
    return pcm;
  }

  public void setPcm(String pcm) {
    this.pcm = pcm;
  }

  public Date getInsertedDt() {
    return insertedDt;
  }

  public void setInsertedDt(Date insertedDt) {
    this.insertedDt = insertedDt;
  }

  public BigInteger getHospitalLocationId() {
    return hospitalLocationId;
  }

  public void setHospitalLocationId(BigInteger hospitalLocationId) {
    this.hospitalLocationId = hospitalLocationId;
  }

  public String getDataSource() {
    return dataSource;
  }

  public void setDataSource(String dataSource) {
    this.dataSource = dataSource;
  }

  public String getProviderIdCode() {
    return providerIdCode;
  }

  public void setProviderIdCode(String providerIdCode) {
    this.providerIdCode = providerIdCode;
  }

  public String getProviderSidr() {
    return providerSidr;
  }

  public void setProviderSidr(String providerSidr) {
    this.providerSidr = providerSidr;
  }

  public Integer getFlag() {
    return flag;
  }

  public void setFlag(Integer flag) {
    this.flag = flag;
  }

//  public Collection<DzPatients> getDzPatientsCollection() {
//    return dzPatientsCollection;
//  }
//
//  public void setDzPatientsCollection(Collection<DzPatients> dzPatientsCollection) {
//    this.dzPatientsCollection = dzPatientsCollection;
//  }

  @Override
  public int hashCode() {
    int hash = 0;
    hash += (pcmid != null ? pcmid.hashCode() : 0);
    return hash;
  }

  @Override
  public boolean equals(Object object) {
    // TODO: Warning - this method won't work in the case the id fields are not set
    if (!(object instanceof DzPcm)) {
      return false;
    }
    DzPcm other = (DzPcm) object;
    if ((this.pcmid == null && other.pcmid != null) || (this.pcmid != null && !this.pcmid.equals(other.pcmid))) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "mil.navy.med.dzreg.jpa.DzPcm[pcmid=" + pcmid + "]";
  }

}
