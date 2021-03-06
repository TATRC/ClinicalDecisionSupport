package org.socraticgrid.icd9wikiextractor.freebase.dao;
// Generated Sep 23, 2010 3:53:17 PM by Hibernate Tools 3.2.1.GA


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * FreebaseMedDisease generated by hbm2java
 */
@Entity
@Table(name="freebase_med_disease"
    ,schema="public"
)
public class FreebaseMedDisease  implements java.io.Serializable {


     private String id;
     private String name;
     private String icd9;
     private String icd10;
     private String icdo;
     private String diseasesDb;
     private String medlinePlus;
     private String emedicine;
     private String mesh;
     private String omim;
     private String symptoms;
     private String affiliatedDiseases;
     private String treatments;
     private String preventionFactors;
     private String trials;
     private String includesDiseases;
     private String parentDisease;
     private String causes;
     private String medicalSpecialties;
     private String riskFactors;
     private String stages;
     private String survivalRates;
     private String notablePeopleWithThisCondition;
     private String tests;

    public FreebaseMedDisease() {
    }

    public FreebaseMedDisease(String id, String name, String icd9, String icd10, String icdo, String diseasesDb, String medlinePlus, String emedicine, String mesh, String omim, String symptoms, String affiliatedDiseases, String treatments, String preventionFactors, String trials, String includesDiseases, String parentDisease, String causes, String medicalSpecialties, String riskFactors, String stages, String survivalRates, String notablePeopleWithThisCondition, String tests) {
       this.id = id;
       this.name = name;
       this.icd9 = icd9;
       this.icd10 = icd10;
       this.icdo = icdo;
       this.diseasesDb = diseasesDb;
       this.medlinePlus = medlinePlus;
       this.emedicine = emedicine;
       this.mesh = mesh;
       this.omim = omim;
       this.symptoms = symptoms;
       this.affiliatedDiseases = affiliatedDiseases;
       this.treatments = treatments;
       this.preventionFactors = preventionFactors;
       this.trials = trials;
       this.includesDiseases = includesDiseases;
       this.parentDisease = parentDisease;
       this.causes = causes;
       this.medicalSpecialties = medicalSpecialties;
       this.riskFactors = riskFactors;
       this.stages = stages;
       this.survivalRates = survivalRates;
       this.notablePeopleWithThisCondition = notablePeopleWithThisCondition;
       this.tests = tests;
    }
   
     @Id 
    
    @Column(name="id", unique=true, nullable=false)
    public String getId() {
        return this.id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    @Column(name="name", nullable=false)
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    @Column(name="icd_9", nullable=false)
    public String getIcd9() {
        return this.icd9;
    }
    
    public void setIcd9(String icd9) {
        this.icd9 = icd9;
    }
    
    @Column(name="icd_10", nullable=false)
    public String getIcd10() {
        return this.icd10;
    }
    
    public void setIcd10(String icd10) {
        this.icd10 = icd10;
    }
    
    @Column(name="icdo", nullable=false)
    public String getIcdo() {
        return this.icdo;
    }
    
    public void setIcdo(String icdo) {
        this.icdo = icdo;
    }
    
    @Column(name="diseases_db", nullable=false)
    public String getDiseasesDb() {
        return this.diseasesDb;
    }
    
    public void setDiseasesDb(String diseasesDb) {
        this.diseasesDb = diseasesDb;
    }
    
    @Column(name="medline_plus", nullable=false)
    public String getMedlinePlus() {
        return this.medlinePlus;
    }
    
    public void setMedlinePlus(String medlinePlus) {
        this.medlinePlus = medlinePlus;
    }
    
    @Column(name="emedicine", nullable=false)
    public String getEmedicine() {
        return this.emedicine;
    }
    
    public void setEmedicine(String emedicine) {
        this.emedicine = emedicine;
    }
    
    @Column(name="mesh", nullable=false)
    public String getMesh() {
        return this.mesh;
    }
    
    public void setMesh(String mesh) {
        this.mesh = mesh;
    }
    
    @Column(name="omim", nullable=false)
    public String getOmim() {
        return this.omim;
    }
    
    public void setOmim(String omim) {
        this.omim = omim;
    }
    
    @Column(name="symptoms", nullable=false, length=2550)
    public String getSymptoms() {
        return this.symptoms;
    }
    
    public void setSymptoms(String symptoms) {
        this.symptoms = symptoms;
    }
    
    @Column(name="affiliated_diseases", nullable=false, length=25500)
    public String getAffiliatedDiseases() {
        return this.affiliatedDiseases;
    }
    
    public void setAffiliatedDiseases(String affiliatedDiseases) {
        this.affiliatedDiseases = affiliatedDiseases;
    }
    
    @Column(name="treatments", nullable=false, length=2550)
    public String getTreatments() {
        return this.treatments;
    }
    
    public void setTreatments(String treatments) {
        this.treatments = treatments;
    }
    
    @Column(name="prevention_factors", nullable=false, length=2550)
    public String getPreventionFactors() {
        return this.preventionFactors;
    }
    
    public void setPreventionFactors(String preventionFactors) {
        this.preventionFactors = preventionFactors;
    }
    
    @Column(name="trials", nullable=false, length=2550)
    public String getTrials() {
        return this.trials;
    }
    
    public void setTrials(String trials) {
        this.trials = trials;
    }
    
    @Column(name="includes_diseases", nullable=false, length=25500)
    public String getIncludesDiseases() {
        return this.includesDiseases;
    }
    
    public void setIncludesDiseases(String includesDiseases) {
        this.includesDiseases = includesDiseases;
    }
    
    @Column(name="parent_disease", nullable=false, length=2550)
    public String getParentDisease() {
        return this.parentDisease;
    }
    
    public void setParentDisease(String parentDisease) {
        this.parentDisease = parentDisease;
    }
    
    @Column(name="causes", nullable=false, length=2550)
    public String getCauses() {
        return this.causes;
    }
    
    public void setCauses(String causes) {
        this.causes = causes;
    }
    
    @Column(name="medical_specialties", nullable=false, length=2550)
    public String getMedicalSpecialties() {
        return this.medicalSpecialties;
    }
    
    public void setMedicalSpecialties(String medicalSpecialties) {
        this.medicalSpecialties = medicalSpecialties;
    }
    
    @Column(name="risk_factors", nullable=false, length=2550)
    public String getRiskFactors() {
        return this.riskFactors;
    }
    
    public void setRiskFactors(String riskFactors) {
        this.riskFactors = riskFactors;
    }
    
    @Column(name="stages", nullable=false, length=2550)
    public String getStages() {
        return this.stages;
    }
    
    public void setStages(String stages) {
        this.stages = stages;
    }
    
    @Column(name="survival_rates", nullable=false, length=2550)
    public String getSurvivalRates() {
        return this.survivalRates;
    }
    
    public void setSurvivalRates(String survivalRates) {
        this.survivalRates = survivalRates;
    }
    
    @Column(name="notable_people_with_this_condition", nullable=false, length=25500)
    public String getNotablePeopleWithThisCondition() {
        return this.notablePeopleWithThisCondition;
    }
    
    public void setNotablePeopleWithThisCondition(String notablePeopleWithThisCondition) {
        this.notablePeopleWithThisCondition = notablePeopleWithThisCondition;
    }
    
    @Column(name="tests", nullable=false, length=2550)
    public String getTests() {
        return this.tests;
    }
    
    public void setTests(String tests) {
        this.tests = tests;
    }




}


