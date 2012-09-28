-- ================================================================================
-- NOTE:  Order of CREATION and DROP very important because of embedded CONSTRAINT syntax.
-- Use of ALTER stmt for CONSTRAINTS did not workj without the innoDB specification.
-- But already laid out with embedded.

-- DROP pattern is:  Child table witg FK dropped first.  Then parent.

-- Make sure to have the schema already created:
-- CREATE SCHEMA kmr;
-- ================================================================================

DROP TABLE IF EXISTS KMV_InferenceEngineDependency ;

DROP TABLE IF EXISTS FactSpecification ;
DROP TABLE IF EXISTS KMV_FactDependency ;

DROP TABLE IF EXISTS PopulationSpecification ;
DROP TABLE IF EXISTS KMV_PopulationDependency ;

DROP TABLE IF EXISTS TT_Specification ;
DROP TABLE IF EXISTS KMV_TaskDependency ;


DROP TABLE IF EXISTS KM_Version ;
DROP TABLE IF EXISTS KM_Specialty ;
DROP TABLE IF EXISTS KnowledgeModule ;

DROP TABLE IF EXISTS ACL_Permission ;
DROP TABLE IF EXISTS UserRole ;
DROP TABLE IF EXISTS KMV_AccessControlList ;

-- -------------- ACL ------------------

CREATE TABLE KMV_AccessControlList
(
	ACL_ID SERIAL,
	ACL_Name VARCHAR(50) NOT NULL,
	ACL_Description VARCHAR(255),
	PRIMARY KEY (ACL_ID),
	UNIQUE (ACL_Name)
) ;


CREATE TABLE UserRole
(
	UR_ID SERIAL,
	UR_Name VARCHAR(50) NOT NULL,
	UR_Description VARCHAR(255),
	PRIMARY KEY (UR_ID),
	UNIQUE (UR_Name)
) ;

CREATE TABLE ACL_Permission
(
	ACL_ID INTEGER NOT NULL,
	UR_ID INTEGER NOT NULL,
	UR_Permission VARCHAR(255),
	PRIMARY KEY (ACL_ID, UR_ID),
--	KEY (ACL_ID),
	CONSTRAINT FK_ACL_Permission_KMV_AccessControlList FOREIGN KEY (ACL_ID) REFERENCES KMV_AccessControlList (ACL_ID) ,
  CONSTRAINT FK_ACL_Permission_UserRole FOREIGN KEY (UR_ID) REFERENCES UserRole (UR_ID)
)  ;


-- -------------- KM ------------------
CREATE TABLE KnowledgeModule
(
	KM_ID INTEGER NOT NULL,
	KM_Name VARCHAR(50) NOT NULL,
	KM_Type VARCHAR(50) DEFAULT NULL ,--COMMENT 'Type of KM. eg: workflow, guideline, validation rule.',
	KM_OrganizationLevel VARCHAR(50) DEFAULT NULL ,--COMMENT 'Highest organization level this KM is applicable. eg: institution, local, personal ',
	KM_Keywords VARCHAR(255),
	KM_Description VARCHAR(255),
	Parent_KM_ID INTEGER,
	OriginInstitution VARCHAR(50),
	CreatedBy_AuthorID VARCHAR(50),
	CreatedBy_AuthorName VARCHAR(50),
	CreatedTimestamp TIMESTAMP(6) DEFAULT current_timestamp,
	LatestVersionNum INTEGER,
	LastModifiedBy_AuthorID VARCHAR(50),
	LastModifiedBy_AuthorName VARCHAR(50),
	LastModifiedTimestamp TIMESTAMP(6),
	ProductionVersionNum INTEGER ,--COMMENT 'Version of KM that is in production (VersionNum from KM_Version table)',
	ValidationStatus VARCHAR(50) DEFAULT NULL ,--COMMENT 'Status of this KM. eg: draft; under-review; approved; QA-passed; production',
	isCheckedOut BOOL,
	PRIMARY KEY (KM_ID),
	UNIQUE (KM_Name)
	-- KEY (Parent_KM_ID)
  -- CONSTRAINT FK_KnowledgeModule_KnowledgeModule FOREIGN KEY (Parent_KM_ID) REFERENCES KnowledgeModule (KM_ID)
) ;

-- -------------- SPECIALTY ------------------
CREATE TABLE KM_Specialty
(
	KM_ID INTEGER NOT NULL,
	TerminologyScheme VARCHAR(50) NOT NULL,
	TerminologyCode VARCHAR(50) NOT NULL,
	Comments VARCHAR(255),
	PRIMARY KEY (KM_ID,TerminologyScheme,TerminologyCode),
  CONSTRAINT FK_KM_Specialty_KnowledgeModule FOREIGN KEY (KM_ID) REFERENCES KnowledgeModule (KM_ID)
) ;

-- -------------- KM_VERSION ------------------
CREATE TABLE KM_Version
(
	KMV_ID SERIAL,
	KM_ID INTEGER NOT NULL,
	KM_VersionNum INTEGER,
	KMV_Name VARCHAR(50),
	ACL_ID INTEGER,
	LMT_ID INTEGER,
	ValidationStatus VARCHAR(50) ,--COMMENT 'Status of this version of the KM. eg: draft; under-review; approved; QA-passed; production',
	Logic_IntermediateForm TEXT ,--COMMENT 'Intermediate or text form of the inference logic of this version of the KM as represented by the inference engine, for example, the .drl file content of the Drools rule engine for this KM.',
	Logic_NativeForm TEXT ,--COMMENT 'Native representation of inference logic by the inference engine',
	Logic_BinaryForm BYTEA ,--COMMENT 'Binary form of the inference logic as used in the inference engine',
	AuthorComments TEXT,
	LastModifiedTimestamp TIMESTAMP(6),
	CreatedBy_AuthorID VARCHAR(50),
	CreatedBy_AuthorName VARCHAR(50),
	CreatedTimestamp TIMESTAMP(6) DEFAULT current_timestamp,
--  Thumbnail_Large BLOB,
--  Thumbnail_Small BLOB,
	PRIMARY KEY (KMV_ID),
  -- UNIQUE (KMV_ID,KM_VersionNum),
	--KEY (ACL_ID),
  CONSTRAINT FK_KM_Version_KMV_AccessControlList FOREIGN KEY (ACL_ID) REFERENCES KMV_AccessControlList (ACL_ID) ,
  CONSTRAINT FK_KM_Version_KnowledgeModule       FOREIGN KEY (KM_ID)  REFERENCES KnowledgeModule (KM_ID)
) ;


CREATE TABLE KMV_FactDependency
(
	FD_ID SERIAL,
	KMV_ID INTEGER NOT NULL,
	Type VARCHAR(50) NOT NULL,
	Description VARCHAR(255),
	PRIMARY KEY (FD_ID),
	--KEY (KMV_ID),
  CONSTRAINT FK_KMV_FactDependency_KM_Version FOREIGN KEY (KMV_ID) REFERENCES KM_Version (KMV_ID)
) ;


CREATE TABLE KMV_InferenceEngineDependency
(
	KMV_ID INTEGER NOT NULL,
	TerminologyScheme VARCHAR(50) NOT NULL,
	TerminologyCode VARCHAR(50) NOT NULL,
	Comments VARCHAR(255),
	PRIMARY KEY (KMV_ID),
  CONSTRAINT FK_KMV_InferenceEngineDependancy_KM_Version FOREIGN KEY (KMV_ID) REFERENCES KM_Version (KMV_ID)
) ;


CREATE TABLE KMV_PopulationDependency
(
	BP_ID SERIAL,
	KMV_ID INTEGER NOT NULL,
	PD_Status VARCHAR(50),
	PD_Scope VARCHAR(50),
	PD_Name VARCHAR(255),
	PD_Description VARCHAR(255),
	PRIMARY KEY (BP_ID),
	--KEY (KMV_ID),
  CONSTRAINT FK_KMV_PopulationDependency_KM_Version FOREIGN KEY (KMV_ID) REFERENCES KM_Version (KMV_ID)
) ;


CREATE TABLE KMV_TaskDependency
(
	TT_ID SERIAL,
	KMV_ID INTEGER,
	TD_Type VARCHAR(50),
	PRIMARY KEY (TT_ID),
	--KEY (KMV_ID),
	--KEY (TD_Type),
  CONSTRAINT FK_KMV_TaskDependency_KM_Version FOREIGN KEY (KMV_ID) REFERENCES KM_Version (KMV_ID)
) ;


CREATE TABLE PopulationSpecification
(
	BP_ID INTEGER NOT NULL,
	TerminologyScheme VARCHAR(50) NOT NULL,
	TerminologyCode VARCHAR(50) NOT NULL,
	TerminologyValue VARCHAR(255) NOT NULL,
	Type VARCHAR(255),
	PRIMARY KEY (BP_ID,TerminologyScheme,TerminologyCode,TerminologyValue),
  CONSTRAINT FK_PopulationSpecification_KMV_PopulationDependency FOREIGN KEY (BP_ID) REFERENCES KMV_PopulationDependency (BP_ID)
) ;


CREATE TABLE TT_Specification
(
	TT_ID INTEGER NOT NULL,
	TerminologyScheme VARCHAR(50) NOT NULL,
	TerminologyCode VARCHAR(50) NOT NULL,    
	Comments VARCHAR(255),
	PRIMARY KEY (TT_ID,TerminologyScheme,TerminologyCode),
  CONSTRAINT FK_TT_Specification_TaskType FOREIGN KEY (TT_ID) REFERENCES KMV_TaskDependency (TT_ID)
) ;


CREATE TABLE FactSpecification
(
	FD_ID INTEGER NOT NULL,
	TerminologyScheme VARCHAR(50) NOT NULL, -- equivalent to ref_fact_scheme.SchemeName
	TerminologyCode VARCHAR(50) NOT NULL,   -- equivalent to ref_fact_code.Concept_Code
	Comments VARCHAR(255),
	PRIMARY KEY (FD_ID,TerminologyScheme,TerminologyCode),
  CONSTRAINT FK_FactSpecification_KMV_FactDependency FOREIGN KEY (FD_ID) REFERENCES KMV_FactDependency (FD_ID)
) ;

/*
----------------------- TRIGGERS ----------------------------------
CREATE TRIGGER insMaxKMVersionNum
  BEFORE INSERT ON KM_Version FOR EACH ROW
BEGIN
  DECLARE maxVersion INTEGER;

  select max(KM_VersionNum) INTO maxVersion
  from KM_Version where KM_ID = NEW.KM_ID;

  SET NEW.KM_VersionNum = maxVersion+1;
END;
*/

COMMIT;  