/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gov.hhs.fha.nhinc.kmr.model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author tmn
 */

@Entity

public class RefCodeSimple implements Serializable {

   private static final long serialVersionUID = 1L;
   @Id
   //private int id;
   private String name;
   private String description;

   public RefCodeSimple() {
   }

   public RefCodeSimple(String name, String description) {
      this.name = name;
      this.description = description;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public String getDescription() {
      return description;
   }

   public void setDescription(String description) {
      this.description = description;
   }

}
