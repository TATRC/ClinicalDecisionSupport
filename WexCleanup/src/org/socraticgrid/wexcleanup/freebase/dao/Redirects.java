package org.socraticgrid.wexcleanup.freebase.dao;
// Generated Nov 6, 2010 5:46:30 PM by Hibernate Tools 3.2.1.GA



/**
 * Redirects generated by hbm2java
 */
public class Redirects  implements java.io.Serializable {


     private int wpid;
     private String name;
     private String redirectsTo;

    public Redirects() {
    }

	
    public Redirects(int wpid, String name) {
        this.wpid = wpid;
        this.name = name;
    }
    public Redirects(int wpid, String name, String redirectsTo) {
       this.wpid = wpid;
       this.name = name;
       this.redirectsTo = redirectsTo;
    }
   
    public int getWpid() {
        return this.wpid;
    }
    
    public void setWpid(int wpid) {
        this.wpid = wpid;
    }
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    public String getRedirectsTo() {
        return this.redirectsTo;
    }
    
    public void setRedirectsTo(String redirectsTo) {
        this.redirectsTo = redirectsTo;
    }




}


