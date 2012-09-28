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
@Table(name = "DZ_POC")
@NamedQueries({
  @NamedQuery(name = "DzPoc.findAll", query = "SELECT d FROM DzPoc d"),
  @NamedQuery(name = "DzPoc.findByPocid", query = "SELECT d FROM DzPoc d WHERE d.pocid = :pocid"),
  @NamedQuery(name = "DzPoc.findByPoc", query = "SELECT d FROM DzPoc d WHERE d.poc = :poc")
//  @NamedQuery(name = "DzPoc.findByInsertedDt", query = "SELECT d FROM DzPoc d WHERE d.insertedDt = :insertedDt"),
//  @NamedQuery(name = "DzPoc.findByDataSource", query = "SELECT d FROM DzPoc d WHERE d.dataSource = :dataSource"),
//  @NamedQuery(name = "DzPoc.findByHospitalLocationInactiveDt", query = "SELECT d FROM DzPoc d WHERE d.hospitalLocationInactiveDt = :hospitalLocationInactiveDt"),
//  @NamedQuery(name = "DzPoc.findByDivisionId", query = "SELECT d FROM DzPoc d WHERE d.divisionId = :divisionId"),
//  @NamedQuery(name = "DzPoc.findByDivisionDmis", query = "SELECT d FROM DzPoc d WHERE d.divisionDmis = :divisionDmis"),
//  @NamedQuery(name = "DzPoc.findByFlag", query = "SELECT d FROM DzPoc d WHERE d.flag = :flag")
})
public class DzPoc implements Serializable {
  private static final long serialVersionUID = 1L;
  @Id
  @Basic(optional = false)
  @Column(name = "POCID")
  private Long pocid;
  @Column(name = "POC")
  private String poc;
  @Column(name = "INSERTED_DT")
  @Temporal(TemporalType.DATE)
  private Date insertedDt;
  @Basic(optional = false)
  @Column(name = "DATA_SOURCE")
  private String dataSource;
  @Column(name = "HOSPITAL_LOCATION_INACTIVE_DT")
  @Temporal(TemporalType.DATE)
  private Date hospitalLocationInactiveDt;
  @Column(name = "DIVISION_ID")
  private Integer divisionId;
  @Column(name = "DIVISION_DMIS")
  private String divisionDmis;
  @Column(name = "FLAG")
  private Integer flag;
  //@OneToMany(mappedBy = "pocid", fetch = FetchType.EAGER)
  //private Collection<DzAppt> dzApptCollection;
  //@OneToMany(mappedBy = "pocid", fetch = FetchType.EAGER)
  //private Collection<DzPatients> dzPatientsCollection;

  public DzPoc() {
  }

  public DzPoc(Long pocid) {
    this.pocid = pocid;
  }

  public DzPoc(Long pocid, String dataSource) {
    this.pocid = pocid;
    this.dataSource = dataSource;
  }

  public Long getPocid() {
    return pocid;
  }

  public void setPocid(Long pocid) {
    this.pocid = pocid;
  }

  public String getPoc() {
    return poc;
  }

  public void setPoc(String poc) {
    this.poc = poc;
  }

  public Date getInsertedDt() {
    return insertedDt;
  }

  public void setInsertedDt(Date insertedDt) {
    this.insertedDt = insertedDt;
  }

  public String getDataSource() {
    return dataSource;
  }

  public void setDataSource(String dataSource) {
    this.dataSource = dataSource;
  }

  public Date getHospitalLocationInactiveDt() {
    return hospitalLocationInactiveDt;
  }

  public void setHospitalLocationInactiveDt(Date hospitalLocationInactiveDt) {
    this.hospitalLocationInactiveDt = hospitalLocationInactiveDt;
  }

  public Integer getDivisionId() {
    return divisionId;
  }

  public void setDivisionId(Integer divisionId) {
    this.divisionId = divisionId;
  }

  public String getDivisionDmis() {
    return divisionDmis;
  }

  public void setDivisionDmis(String divisionDmis) {
    this.divisionDmis = divisionDmis;
  }

  public Integer getFlag() {
    return flag;
  }

  public void setFlag(Integer flag) {
    this.flag = flag;
  }

//  public Collection<DzAppt> getDzApptCollection() {
//    return dzApptCollection;
//  }
//
//  public void setDzApptCollection(Collection<DzAppt> dzApptCollection) {
//    this.dzApptCollection = dzApptCollection;
//  }

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
    hash += (pocid != null ? pocid.hashCode() : 0);
    return hash;
  }

  @Override
  public boolean equals(Object object) {
    // TODO: Warning - this method won't work in the case the id fields are not set
    if (!(object instanceof DzPoc)) {
      return false;
    }
    DzPoc other = (DzPoc) object;
    if ((this.pocid == null && other.pocid != null) || (this.pocid != null && !this.pocid.equals(other.pocid))) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "mil.navy.med.dzreg.jpa.DzPoc[pocid=" + pocid + "]";
  }

}
