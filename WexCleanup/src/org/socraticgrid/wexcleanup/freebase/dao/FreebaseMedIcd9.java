package org.socraticgrid.wexcleanup.freebase.dao;
// Generated Nov 6, 2010 5:46:30 PM by Hibernate Tools 3.2.1.GA



/**
 * FreebaseMedIcd9 generated by hbm2java
 */
public class FreebaseMedIcd9  implements java.io.Serializable {


     private String id;
     private String name;
     private String code;
     private String includesClassifications;
     private String parentClassification;

    public FreebaseMedIcd9() {
    }

    public FreebaseMedIcd9(String id, String name, String code, String includesClassifications, String parentClassification) {
       this.id = id;
       this.name = name;
       this.code = code;
       this.includesClassifications = includesClassifications;
       this.parentClassification = parentClassification;
    }
   
    public String getId() {
        return this.id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    public String getCode() {
        return this.code;
    }
    
    public void setCode(String code) {
        this.code = code;
    }
    public String getIncludesClassifications() {
        return this.includesClassifications;
    }
    
    public void setIncludesClassifications(String includesClassifications) {
        this.includesClassifications = includesClassifications;
    }
    public String getParentClassification() {
        return this.parentClassification;
    }
    
    public void setParentClassification(String parentClassification) {
        this.parentClassification = parentClassification;
    }




}


