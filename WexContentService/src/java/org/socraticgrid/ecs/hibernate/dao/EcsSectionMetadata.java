package org.socraticgrid.ecs.hibernate.dao;
// Generated Nov 21, 2011 5:16:07 PM by Hibernate Tools 3.2.1.GA



/**
 * EcsSectionMetadata generated by hbm2java
 */
public class EcsSectionMetadata  implements java.io.Serializable {


     private int id;
     private String sectioncode;
     private String description;

    public EcsSectionMetadata() {
    }

	
    public EcsSectionMetadata(int id) {
        this.id = id;
    }
    public EcsSectionMetadata(int id, String sectioncode, String description) {
       this.id = id;
       this.sectioncode = sectioncode;
       this.description = description;
    }
   
    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    public String getSectioncode() {
        return this.sectioncode;
    }
    
    public void setSectioncode(String sectioncode) {
        this.sectioncode = sectioncode;
    }
    public String getDescription() {
        return this.description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }




}


