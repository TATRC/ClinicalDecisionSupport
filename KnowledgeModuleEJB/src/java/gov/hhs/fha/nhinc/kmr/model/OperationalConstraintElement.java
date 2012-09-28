/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gov.hhs.fha.nhinc.kmr.model;

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
 * @author tmn
 */
@Entity
@Table(name = "OperationalConstraintElement")
@NamedQueries({@NamedQuery(name = "OperationalConstraintElement.findAll", query = "SELECT o FROM OperationalConstraintElement o"), @NamedQuery(name = "OperationalConstraintElement.findByOcId", query = "SELECT o FROM OperationalConstraintElement o WHERE o.ocId = :ocId"), @NamedQuery(name = "OperationalConstraintElement.findByOceId", query = "SELECT o FROM OperationalConstraintElement o WHERE o.oceId = :oceId"), @NamedQuery(name = "OperationalConstraintElement.findByOCEName", query = "SELECT o FROM OperationalConstraintElement o WHERE o.oCEName = :oCEName"), @NamedQuery(name = "OperationalConstraintElement.findByOCEType", query = "SELECT o FROM OperationalConstraintElement o WHERE o.oCEType = :oCEType"), @NamedQuery(name = "OperationalConstraintElement.findByOCEDescription", query = "SELECT o FROM OperationalConstraintElement o WHERE o.oCEDescription = :oCEDescription"), @NamedQuery(name = "OperationalConstraintElement.findByOCEInterpretation", query = "SELECT o FROM OperationalConstraintElement o WHERE o.oCEInterpretation = :oCEInterpretation"), @NamedQuery(name = "OperationalConstraintElement.findByOCEAttributeName", query = "SELECT o FROM OperationalConstraintElement o WHERE o.oCEAttributeName = :oCEAttributeName"), @NamedQuery(name = "OperationalConstraintElement.findByOCEComparator", query = "SELECT o FROM OperationalConstraintElement o WHERE o.oCEComparator = :oCEComparator"), @NamedQuery(name = "OperationalConstraintElement.findByOCEAttributeValues", query = "SELECT o FROM OperationalConstraintElement o WHERE o.oCEAttributeValues = :oCEAttributeValues"), @NamedQuery(name = "OperationalConstraintElement.findByOCEUnitOfMeasure", query = "SELECT o FROM OperationalConstraintElement o WHERE o.oCEUnitOfMeasure = :oCEUnitOfMeasure")})
public class OperationalConstraintElement implements Serializable {
   private static final long serialVersionUID = 1L;
   @Basic(optional = false)
   @Column(name = "OC_ID")
   private int ocId;
   @Id
   @Basic(optional = false)
   @Column(name = "OCE_ID")
   private Integer oceId;
   @Column(name = "OCE_Name")
   private String oCEName;
   @Column(name = "OCE_Type")
   private String oCEType;
   @Column(name = "OCE_Description")
   private String oCEDescription;
   @Column(name = "OCE_Interpretation")
   private String oCEInterpretation;
   @Column(name = "OCE_AttributeName")
   private String oCEAttributeName;
   @Column(name = "OCE_Comparator")
   private String oCEComparator;
   @Column(name = "OCE_AttributeValues")
   private String oCEAttributeValues;
   @Column(name = "OCE_UnitOfMeasure")
   private String oCEUnitOfMeasure;

   public OperationalConstraintElement() {
   }

   public OperationalConstraintElement(Integer oceId) {
      this.oceId = oceId;
   }

   public OperationalConstraintElement(Integer oceId, int ocId) {
      this.oceId = oceId;
      this.ocId = ocId;
   }

   public int getOcId() {
      return ocId;
   }

   public void setOcId(int ocId) {
      this.ocId = ocId;
   }

   public Integer getOceId() {
      return oceId;
   }

   public void setOceId(Integer oceId) {
      this.oceId = oceId;
   }

   public String getOCEName() {
      return oCEName;
   }

   public void setOCEName(String oCEName) {
      this.oCEName = oCEName;
   }

   public String getOCEType() {
      return oCEType;
   }

   public void setOCEType(String oCEType) {
      this.oCEType = oCEType;
   }

   public String getOCEDescription() {
      return oCEDescription;
   }

   public void setOCEDescription(String oCEDescription) {
      this.oCEDescription = oCEDescription;
   }

   public String getOCEInterpretation() {
      return oCEInterpretation;
   }

   public void setOCEInterpretation(String oCEInterpretation) {
      this.oCEInterpretation = oCEInterpretation;
   }

   public String getOCEAttributeName() {
      return oCEAttributeName;
   }

   public void setOCEAttributeName(String oCEAttributeName) {
      this.oCEAttributeName = oCEAttributeName;
   }

   public String getOCEComparator() {
      return oCEComparator;
   }

   public void setOCEComparator(String oCEComparator) {
      this.oCEComparator = oCEComparator;
   }

   public String getOCEAttributeValues() {
      return oCEAttributeValues;
   }

   public void setOCEAttributeValues(String oCEAttributeValues) {
      this.oCEAttributeValues = oCEAttributeValues;
   }

   public String getOCEUnitOfMeasure() {
      return oCEUnitOfMeasure;
   }

   public void setOCEUnitOfMeasure(String oCEUnitOfMeasure) {
      this.oCEUnitOfMeasure = oCEUnitOfMeasure;
   }

   @Override
   public int hashCode() {
      int hash = 0;
      hash += (oceId != null ? oceId.hashCode() : 0);
      return hash;
   }

   @Override
   public boolean equals(Object object) {
      // TODO: Warning - this method won't work in the case the id fields are not set
      if (!(object instanceof OperationalConstraintElement)) {
         return false;
      }
      OperationalConstraintElement other = (OperationalConstraintElement) object;
      if ((this.oceId == null && other.oceId != null) || (this.oceId != null && !this.oceId.equals(other.oceId))) {
         return false;
      }
      return true;
   }

   @Override
   public String toString() {
      return "gov.hhs.fha.nhinc.kmr.model.OperationalConstraintElement[oceId=" + oceId + "]";
   }

}
