
USE kmr;


-- ======== ACL/PERMISSION/USERROLE =========== 

-- UserRole
INSERT INTO UserRole
 (UR_Name,UR_Description)
VALUES                                                      -- UR_ID
 ('Providers-Nurse','Provider-Nurse Role')                  -- 1 
,('Providers-Physicians','Providers-Physicians Role')       -- 2 
,('Internal Domain Expert','Insitution Domain Expert Role') -- 3
,('External Domain Expert','Insitution Domain Expert Role') -- 4
;

INSERT INTO KMV_AccessControlList
 (ACL_ID,ACL_Name,ACL_Description)
VALUES
 (20,'Permission Set #1','Read only Providers-Nurse, Read/Write Providers-Physicians, Read only External')
,(21,'Permission Set #2','Read/Write Providers-Nurse, Read/Write Providers-Physicians, Read only External')
;

INSERT INTO ACL_Permission
 (ACL_ID,UR_ID, UR_PERMISSION)
VALUES        
 (20,1,'R')   -- ACL=20 Read - Providers-Nurse
,(20,2,'RW')  -- ACL=20 Read/Write - Providers-Physicians
,(20,4,'R')  -- ACL=20 Read - External
,(21,1,'RW')   -- ACL=20 Read - Providers-Nurse
,(21,2,'RW')  -- ACL=20 Read/Write - Providers-Physicians
,(21,4,'R')  -- ACL=20 Read - External
;

-- ======== KNOWLEDGE/SPECIALTY =========== 

INSERT INTO KnowledgeModule
(KM_Name, KM_Type, KM_OrganizationLevel, KM_Keywords, KM_Description, 
 Parent_KM_ID, OriginInstitution, CreatedBy_AuthorID, CreatedBy_AuthorName, CreatedTimestamp, 
 LatestVersionNum, LastModifiedBy_AuthorID, LastModifiedBy_AuthorName, LastModifiedTimestamp, 
 ProductionVersionNum, ValidationStatus, isCheckedOut) 
VALUES 
('myKM', 'VALIDATION', 'PERSONAL', 'test KM', 'test KM', 
 0, 'NHRC', '99', 'tmn', '2010-03-23 14:20:30', 
 4, '99', 'tmn', '2010-03-23 14:20:30', 
 3, 'UNDER REVIEW', 0)
,('myKM 2', 'VALIDATION', 'PERSONAL', 'test KM 2', 'test KM 2',
 0, 'NHRC', '99', 'tmn', '2010-03-24 14:20:30',
 4, '99', 'tmn', '2010-03-24 14:20:30',
 3, 'PRODUCTION', 0) 
 ;
 
 INSERT INTO KM_Specialty(KM_ID, TerminologyScheme,TerminologyCode,Comments) VALUES 
  (1, 'AMA123','207Q00000X','Family Medicine')
, (1, 'AMA123','207QA0401X','Addiction Medicine')
, (1, 'AMA123','207QA0505X','Adult Medicine')
, (1, 'AMA456','207Q00009X','Family Medicine 2')
, (2, 'AMA123','207Q00000X','Family Medicine')
;

-- ======== KM_VERSION =========== 

INSERT INTO  KM_Version
(KM_ID,KM_VersionNum,KMV_Name,
 CreatedBy_AuthorID,CreatedBy_AuthorName,CreatedTimestamp,LastModifiedTimestamp,ValidationStatus,
 AuthorComments,LMT_ID,Logic_IntermediateForm,Logic_NativeForm,Logic_BinaryForm,ACL_ID) 
VALUES                                                                         -- KMV_ID
 (1,1,'myKM - v1',
  99,'tmn','2010-03-22 12:00:00','2010-03-22 12:00:00','DRAFT',                 -- 1
  'no comment',null,'intermediate form - A','native form - DROOLS - A',null,20)
,(1,2,'myKM - v2',
  99,'tmn','2010-03-22 01:00:00','2010-03-22 01:00:00','APPROVED',              -- 2
  'no comment',null,'intermediate form - B','native form - DROOLS - B',null,20)
,(1,3,'myKM - v3',
  99,'tmn','2010-03-24 12:00:00','2010-03-24 12:00:00','PRODUCTION',            -- 3
  'no comment',null,'intermediate form - C','native form - DROOLS - C',null,21)
,(1,4,'myKM - v4',
  99,'tmn','2010-03-25 12:00:00','2010-03-25 12:00:00','DRAFT',                 -- 4
  'no comment',null,'intermediate form - D','native form - DROOLS - D',null,20)
,(2,1,'myKM2 - v1',
  99,'tmn','2010-03-24 12:00:00','2010-03-24 12:00:00','PRODUCTION',            -- 5
  'no comment',null,'intermediate form - E','native form - DROOLS - E',null,21)
,(2,2,'myKM2 - v2',
  99,'tmn','2010-03-25 12:00:00','2010-03-25 12:00:00','DRAFT',                -- 6
  'no comment',null,null,'native form - DROOLS - F','ABC123abc',21)
;

-- ======== FACTS =========== 
INSERT INTO KMV_FactDependency
(KMV_ID,Type,Description)
VALUES                               -- FD_ID
 (1,'LAB','A Lab Fact')              -- 1
,(2,'LAB','A Lab Fact')              -- 2
,(3,'LAB','A Lab Fact')              -- 3
,(5,'LAB','A Lab Fact')              -- 4
,(5,'ALLERGIES','An Allergies Fact') -- 5 
;

INSERT INTO FactSpecification
(FD_ID,TerminologyScheme,TerminologyCode,Comments)
VALUES
 (1,100,'26453-1','LOINC - Erythrocytes')
,(2,100,'26453-1','LOINC - Erythrocytes')
,(2,100,'26464-8','LOINC - Leukocytes')
,(2,100,'718-7','LOINC - Hemoglobin')
,(2,100,'20570-8','LOINC - Hematocrit')
,(3,100,'26453-1','LOINC - Erythrocytes')
,(3,100,'26464-8','LOINC - Leukocytes')
,(3,100,'718-7','LOINC - Hemoglobin')
,(4,100,'26453-1','LOINC - Erythrocytes')
,(5,100,'26464-10','LOINC - Allergies')
;


-- ======== POPULATION =========== 
-- Population DEpendency
INSERT INTO KMV_PopulationDependency
(KMV_ID,PD_Status,PD_Scope,PD_Name,PD_Description)
VALUES
 (2,'draft','enterprise','Disease,Gender,Age','Disease,Gender,and Age')
,(6,'draft','enterprise','Disease,Gender,Age','Disease,Gender,and Age')
;


-- PopulationSpecification
INSERT INTO PopulationSpecification
(BP_ID,TerminologyScheme,TerminologyCode,TerminologyValue,Comments)
VALUES
 (1,101,'900-1','F','HL7 - Gender')
,(1,102,'2900-1','250.1','ICD9 - Diabetes')
,(1,200,'800-1','20','UDS - Age Minimum')
,(1,200,'800-2','30','UDS - Age Maximum')
,(2,101,'900-1','M','HL7 - Gender')
,(2,102,'2900-1','250.1','ICD9 - Diabetes')
,(2,200,'800-1','40','UDS - Age Minimum')
,(2,200,'800-2','60','UDS - Age Maximum')
;


-- ======== TASK =========== 
-- Task Dependency
INSERT INTO KMV_TaskDependency
 (KMV_ID, TD_Type)
VALUES
 (1,'ALERT')
,(1,'TELECOM')
,(2,'ALERT')
;

-- Task Specification
INSERT INTO TT_Specification
 (TT_ID, TerminologyScheme, TerminologyCode, Comments)
VALUES
 (1,'task scheme 1','task code 1-3','Task Spec for Task 1')
,(2,'task scheme 1','task code 1-9','Task Spec for Task 2')
;


-- ======== INFERENCE ENGINE =========== 
INSERT INTO KMV_InferenceEngineDependency
 (KMV_ID, TerminologyScheme, TerminologyCode, Comments)
VALUES
 (1,'DROOLS EXPERT','DR2.0', 'myKM v1 - Drools Expert Engine version 2.0')
,(2,'DROOLS EXPERT','DR2.0', 'myKM v2 - Drools Expert Engine version 2.0')
,(3,'DROOLS EXPERT','DR2.1', 'myKM v3 - Drools Expert Engine version 2.1')
,(4,'DROOLS EXPERT','DR2.1', 'myKM v4 - Drools Expert Engine version 2.1')
,(5,'DROOLS EXPERT','DR2.0', 'myKM2 v1 - Drools Expert Engine version 2.0')
,(6,'DROOLS EXPERT','DR2.1', 'myKM2 v2 - Drools Expert Engine version 2.1')
;

COMMIT;

/*  ==================================================================
 Test Lookup Data - Assuming these were pulled from predefined 
 lookup tables that will need to be established later/somewhere.
 
    SCHEME  SCHEME-ID/CODE   TERM CODE   TERM DESCRIPTION       Test Usage
    ------  -----------      ---------   ----------------       ----------
    LOINC   100              "26453-1"   "LOINC - Erythrocytes" (fact)
                             "26464-8"   "LOINC - Leukocytes"   (fact)
                             "718-7"     "LOINC - Hemoglobin"   (fact)
                             
    HL7     101              "900-1"     "HL7 - Gender"         (pop)
    
    ICD9    102              "2900-1"    "ICD9 - Diabetes"      (pop)
    
    ICD10   103              "3900-1"    "ICD10 - Diabetes"     (pop)
    
    UDS     200              "800-1"     "UDS - Age Minimum"    (pop)
                             "800-2"     "UDS - Age Maximum"    (pop)
                             
    ================================================================== */