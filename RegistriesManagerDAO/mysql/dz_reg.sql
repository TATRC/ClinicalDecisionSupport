ALTER TABLE DZ_REG.DZ_TYPE
 DROP PRIMARY KEY CASCADE
/
DROP TABLE DZ_REG.DZ_TYPE CASCADE CONSTRAINTS
/

CREATE TABLE DZ_REG.DZ_TYPE ( 
	DZTYPE_ID INTEGER, 
	DESCR VARCHAR2(30 BYTE), 
	INSERTED_DT DATE DEFAULT (SYSDATE) ) 
TABLESPACE DZ_REG_DATA01 PCTUSED 40 PCTFREE 10 INITRANS 1 MAXTRANS 255 STORAGE 
( INITIAL 64K MINEXTENTS 1 MAXEXTENTS 2147483645 PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT ) 
LOGGING NOCOMPRESS NOCACHE NOPARALLEL MONITORING
/

COMMENT ON TABLE DZ_REG.DZ_TYPE IS 'List of diseases.'
/

COMMENT ON COLUMN DZ_REG.DZ_TYPE.DZTYPE_ID IS '1 for diabetes, 2 for asthma, 3 for newborn, 4 for breasthealth.'
/

COMMENT ON COLUMN DZ_REG.DZ_TYPE.DESCR IS 'Disease name.'
/

COMMENT ON COLUMN DZ_REG.DZ_TYPE.INSERTED_DT IS 'Record inserted date.'
/


CREATE UNIQUE INDEX DZ_REG.PK_DZ_TYPE ON DZ_REG.DZ_TYPE (DZTYPE_ID) 
LOGGING TABLESPACE DZ_REG_DATA01 PCTFREE 10 INITRANS 2 MAXTRANS 255 STORAGE 
( INITIAL 64K MINEXTENTS 1 MAXEXTENTS 2147483645 PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT ) NOPARALLEL
/


ALTER TABLE DZ_REG.DZ_TYPE ADD ( 
	CONSTRAINT PK_DZ_TYPE PRIMARY KEY (DZTYPE_ID) 
	USING INDEX TABLESPACE DZ_REG_DATA01 PCTFREE 10 INITRANS 2 MAXTRANS 255 STORAGE 
	( INITIAL 64K MINEXTENTS 1 MAXEXTENTS 2147483645 PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 ))
/


GRANT SELECT ON  DZ_REG.DZ_TYPE TO DZREG_READ
/

GRANT DELETE, INSERT, SELECT, UPDATE ON  DZ_REG.DZ_TYPE TO DZREG_BATCH
/

GRANT SELECT ON  DZ_REG.DZ_TYPE TO USR_PRODDZ
/


ALTER TABLE DZ_REG.DZ_REG
 DROP PRIMARY KEY CASCADE
/
DROP TABLE DZ_REG.DZ_REG CASCADE CONSTRAINTS
/

CREATE TABLE DZ_REG.DZ_REG ( 
	PATID INTEGER, 
	DISEASETYPE_ID INTEGER, 
	REGISTERED_DT DATE, 
	ACTIVE INTEGER NOT NULL, 
	CMD_FLAG_DT DATE, 
	INSERTED_DT DATE DEFAULT SYSDATE, 
	DATA_SOURCE VARCHAR2(25 BYTE) NOT NULL, 
	HEDIS INTEGER, 
	CYCLE1_SCENARIO INTEGER, 
	FLAG INTEGER ) 
TABLESPACE DZ_REG_DATA01 PCTUSED 40 PCTFREE 10 INITRANS 1 MAXTRANS 255 STORAGE 
( INITIAL 64K MINEXTENTS 1 MAXEXTENTS 2147483645 PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT ) 
LOGGING NOCOMPRESS NOCACHE NOPARALLEL MONITORING
/

COMMENT ON TABLE DZ_REG.DZ_REG IS 'List of patient w/ specific disease.'
/

COMMENT ON COLUMN DZ_REG.DZ_REG.PATID IS 'This is the internal entry number of the patient.'
/

COMMENT ON COLUMN DZ_REG.DZ_REG.DISEASETYPE_ID IS '1 for diabetes, 2 for asthma, 3 for newborn, 4 for breasthealth.'
/

COMMENT ON COLUMN DZ_REG.DZ_REG.REGISTERED_DT IS 'Date the patient was identified for a disease'
/

COMMENT ON COLUMN DZ_REG.DZ_REG.ACTIVE IS 'Asthma: 1 = patient qualify in the 12 mths measuring year.  Diabetes: 1 = patient qualify in the 24 mths measuring year.'
/

COMMENT ON COLUMN DZ_REG.DZ_REG.CMD_FLAG_DT IS 'Date the command interest flag was set.'
/

COMMENT ON COLUMN DZ_REG.DZ_REG.INSERTED_DT IS 'Record inserted date.'
/

COMMENT ON COLUMN DZ_REG.DZ_REG.DATA_SOURCE IS 'Where did we get the data from. Eg: ICDB, CHCS.'
/

COMMENT ON COLUMN DZ_REG.DZ_REG.HEDIS IS 'Asthma: 1 = patient qualify in the 12 mths measuring year and the 12 mths previous year.'
/

COMMENT ON COLUMN DZ_REG.DZ_REG.CYCLE1_SCENARIO IS 'Criteria in which the patient qualify in the measuring year.'
/

COMMENT ON COLUMN DZ_REG.DZ_REG.FLAG IS 'Dummy flag (0 or 1) as needed by PL/SQL code.Dummy flag (0 or 1) as needed by PL/SQL code.'
/


CREATE INDEX DZ_REG.IDX_DZREG_DZTYPE ON DZ_REG.DZ_REG (DISEASETYPE_ID) 
NOLOGGING TABLESPACE DZ_REG_IDX01 PCTFREE 10 INITRANS 2 MAXTRANS 255 STORAGE 
( INITIAL 2544K MINEXTENTS 1 MAXEXTENTS 2147483645 PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT ) NOPARALLEL
/


CREATE INDEX DZ_REG.IDX_DZREG_DZTYPEACTIVE ON DZ_REG.DZ_REG (DISEASETYPE_ID, ACTIVE) 
NOLOGGING TABLESPACE DZ_REG_IDX01 PCTFREE 10 INITRANS 2 MAXTRANS 255 STORAGE 
( INITIAL 1M MINEXTENTS 1 MAXEXTENTS 2147483645 PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT ) NOPARALLEL
/


CREATE INDEX DZ_REG.IDX_DZREG_ACTIVE ON DZ_REG.DZ_REG (ACTIVE) 
NOLOGGING TABLESPACE DZ_REG_IDX01 PCTFREE 10 INITRANS 2 MAXTRANS 255 STORAGE 
( INITIAL 3864K MINEXTENTS 1 MAXEXTENTS 2147483645 PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT ) NOPARALLEL
/


CREATE INDEX DZ_REG.IDX_DZREG_DZTYPEPATID ON DZ_REG.DZ_REG (DISEASETYPE_ID, PATID) 
NOLOGGING TABLESPACE DZ_REG_IDX01 PCTFREE 10 INITRANS 2 MAXTRANS 255 STORAGE 
( INITIAL 664K MINEXTENTS 1 MAXEXTENTS 2147483645 PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT ) NOPARALLEL
/


CREATE INDEX DZ_REG.IDX_DZREG_COMBO3 ON DZ_REG.DZ_REG (DISEASETYPE_ID, ACTIVE, PATID) 
NOLOGGING TABLESPACE DZ_REG_IDX01 PCTFREE 10 INITRANS 2 MAXTRANS 255 STORAGE 
( INITIAL 1M MINEXTENTS 1 MAXEXTENTS 2147483645 PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT ) NOPARALLEL
/


ALTER TABLE DZ_REG.DZ_REG ADD ( CONSTRAINT PK_DZ_REG PRIMARY KEY (PATID, DISEASETYPE_ID))
/


ALTER TABLE DZ_REG.DZ_REG ADD ( CONSTRAINT FK_DZREG_DTYPEID FOREIGN KEY (DISEASETYPE_ID) REFERENCES DZ_REG.DZ_TYPE (DZTYPE_ID))
/

ALTER TABLE DZ_REG.DZ_REG ADD ( CONSTRAINT FK_DZREG_PATID FOREIGN KEY (PATID) REFERENCES DZ_REG.DZ_PATIENTS (PATID))
/


GRANT SELECT ON  DZ_REG.DZ_REG TO DZREG_READ
/

GRANT DELETE, INSERT, SELECT, UPDATE ON  DZ_REG.DZ_REG TO DZREG_BATCH
/

GRANT SELECT, UPDATE ON  DZ_REG.DZ_REG TO USR_PRODDZ
/


ALTER TABLE DZ_REG.DZ_POC
 DROP PRIMARY KEY CASCADE
/
DROP TABLE DZ_REG.DZ_POC CASCADE CONSTRAINTS
/

CREATE TABLE DZ_REG.DZ_POC ( 
	POCID NUMBER, 
	POC VARCHAR2(35 BYTE), 
	INSERTED_DT DATE DEFAULT sysdate, 
	DATA_SOURCE VARCHAR2(25 BYTE) NOT NULL, 
	HOSPITAL_LOCATION_INACTIVE_DT DATE, 
	DIVISION_ID NUMBER, 
	DIVISION_DMIS VARCHAR2(4 BYTE), 
	FLAG INTEGER ) 
TABLESPACE DZ_REG_DATA01 PCTUSED 40 PCTFREE 10 INITRANS 1 MAXTRANS 255 STORAGE 
( INITIAL 64K MINEXTENTS 1 MAXEXTENTS 2147483645 PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT ) 
LOGGING NOCOMPRESS NOCACHE NOPARALLEL MONITORING
/

COMMENT ON TABLE DZ_REG.DZ_POC IS 'List of Place Of Care.'
/

COMMENT ON COLUMN DZ_REG.DZ_POC.POCID IS 'This is the internal entry number of a CHCS hospital location.'
/

COMMENT ON COLUMN DZ_REG.DZ_POC.POC IS 'This is a location name within the hospital.  This is the same data as is stored in the chcs_provider.chcs_provider_location table.'
/

COMMENT ON COLUMN DZ_REG.DZ_POC.INSERTED_DT IS 'Record inserted date.'
/

COMMENT ON COLUMN DZ_REG.DZ_POC.DATA_SOURCE IS 'Where did we get the data from. Eg: ICDB, CHCS.'
/

COMMENT ON COLUMN DZ_REG.DZ_POC.HOSPITAL_LOCATION_INACTIVE_DT IS 'If the hospital location is inactive, this is the date it was inactivated.'
/

COMMENT ON COLUMN DZ_REG.DZ_POC.DIVISION_ID IS 'This identifies the Medical Center Division (MCD) associated with the hospital location. This is the internal entry number of the CHCS division.'
/

COMMENT ON COLUMN DZ_REG.DZ_POC.DIVISION_DMIS IS 'This is the unique Defense Medical Identification (DMIS) code for this division.'
/

COMMENT ON COLUMN DZ_REG.DZ_POC.FLAG IS 'Dummy flag (0 or 1) as needed by PL/SQL code.'
/


CREATE INDEX DZ_REG.IDX_DZPOC_FLAG ON DZ_REG.DZ_POC (FLAG) 
NOLOGGING TABLESPACE DZ_REG_IDX01 PCTFREE 10 INITRANS 2 MAXTRANS 255 STORAGE 
( INITIAL 424K MINEXTENTS 1 MAXEXTENTS 2147483645 PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT ) NOPARALLEL
/


CREATE UNIQUE INDEX DZ_REG.PK_DZ_POC ON DZ_REG.DZ_POC (POCID) 
LOGGING TABLESPACE DZ_REG_DATA01 PCTFREE 10 INITRANS 2 MAXTRANS 255 STORAGE 
( INITIAL 64K MINEXTENTS 1 MAXEXTENTS 2147483645 PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT ) NOPARALLEL
/


ALTER TABLE DZ_REG.DZ_POC ADD ( 
	CONSTRAINT PK_DZ_POC PRIMARY KEY (POCID) 
	USING INDEX TABLESPACE DZ_REG_DATA01 PCTFREE 10 INITRANS 2 MAXTRANS 255 STORAGE 
	( INITIAL 64K MINEXTENTS 1 MAXEXTENTS 2147483645 PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 ))
/


GRANT SELECT ON  DZ_REG.DZ_POC TO DZREG_READ
/

GRANT DELETE, INSERT, SELECT, UPDATE ON  DZ_REG.DZ_POC TO DZREG_BATCH
/

GRANT SELECT ON  DZ_REG.DZ_POC TO USR_PRODDZ
/


ALTER TABLE DZ_REG.DZ_PCM
 DROP PRIMARY KEY CASCADE
/
DROP TABLE DZ_REG.DZ_PCM CASCADE CONSTRAINTS
/

CREATE TABLE DZ_REG.DZ_PCM ( 
	PCMID NUMBER, 
	PCM VARCHAR2(30 BYTE), 
	INSERTED_DT DATE DEFAULT sysdate, 
	HOSPITAL_LOCATION_ID INTEGER, 
	DATA_SOURCE VARCHAR2(25 BYTE) NOT NULL, 
	PROVIDER_ID_CODE VARCHAR2(9 BYTE), 
	PROVIDER_SIDR VARCHAR2(6 BYTE), 
	FLAG INTEGER ) 
TABLESPACE DZ_REG_DATA01 PCTUSED 40 PCTFREE 10 INITRANS 1 MAXTRANS 255 STORAGE 
( INITIAL 64K MINEXTENTS 1 MAXEXTENTS 2147483645 PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT ) 
LOGGING NOCOMPRESS NOCACHE NOPARALLEL MONITORING
/

COMMENT ON TABLE DZ_REG.DZ_PCM IS 'List of Primary Care.'
/

COMMENT ON COLUMN DZ_REG.DZ_PCM.HOSPITAL_LOCATION_ID IS 'This is the primary hospital location with which the provider is associated.'
/

COMMENT ON COLUMN DZ_REG.DZ_PCM.DATA_SOURCE IS 'Where did we get the data from. Eg: ICDB, CHCS.'
/

COMMENT ON COLUMN DZ_REG.DZ_PCM.PROVIDER_ID_CODE IS 'This is the providers unique 6-character ID.'
/

COMMENT ON COLUMN DZ_REG.DZ_PCM.PROVIDER_SIDR IS 'This is the providers unique ID code used for the Standard Inpatient Data Record transmittals.'
/

COMMENT ON COLUMN DZ_REG.DZ_PCM.FLAG IS 'Dummy flag (0 or 1) as needed by PL/SQL code.'
/

COMMENT ON COLUMN DZ_REG.DZ_PCM.PCMID IS 'This is the internal entry number of the CHCS provider.'
/

COMMENT ON COLUMN DZ_REG.DZ_PCM.PCM IS 'This is the providers full name.'
/

COMMENT ON COLUMN DZ_REG.DZ_PCM.INSERTED_DT IS 'Record inserted date.'
/


CREATE INDEX DZ_REG.IDX_DZPCM_FLAG ON DZ_REG.DZ_PCM (FLAG) 
NOLOGGING TABLESPACE DZ_REG_IDX01 PCTFREE 10 INITRANS 2 MAXTRANS 255 STORAGE 
( INITIAL 424K MINEXTENTS 1 MAXEXTENTS 2147483645 PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT ) NOPARALLEL
/


CREATE UNIQUE INDEX DZ_REG.PK_DZ_PCM ON DZ_REG.DZ_PCM (PCMID) 
LOGGING TABLESPACE DZ_REG_DATA01 PCTFREE 10 INITRANS 2 MAXTRANS 255 STORAGE 
( INITIAL 64K MINEXTENTS 1 MAXEXTENTS 2147483645 PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT ) NOPARALLEL
/


ALTER TABLE DZ_REG.DZ_PCM ADD ( 
	CONSTRAINT PK_DZ_PCM PRIMARY KEY (PCMID) 
	USING INDEX TABLESPACE DZ_REG_DATA01 PCTFREE 10 INITRANS 2 MAXTRANS 255 STORAGE 
	( INITIAL 64K MINEXTENTS 1 MAXEXTENTS 2147483645 PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 ))
/


GRANT SELECT ON  DZ_REG.DZ_PCM TO DZREG_READ
/

GRANT DELETE, INSERT, SELECT, UPDATE ON  DZ_REG.DZ_PCM TO DZREG_BATCH
/

GRANT SELECT ON  DZ_REG.DZ_PCM TO USR_PRODDZ
/


ALTER TABLE DZ_REG.DZ_PATIENTS
 DROP PRIMARY KEY CASCADE
/
DROP TABLE DZ_REG.DZ_PATIENTS CASCADE CONSTRAINTS
/

CREATE TABLE DZ_REG.DZ_PATIENTS ( 
	PATID INTEGER, 
	FMPSSN VARCHAR2(20 BYTE) NOT NULL, 
	NAME VARCHAR2(100 BYTE), 
	DOB VARCHAR2(25 BYTE), 
	ACV VARCHAR2(1 BYTE), 
	DMIS VARCHAR2(6 BYTE), 
	PCMID NUMBER, 
	POCID NUMBER, 
	UPDATED VARCHAR2(12 BYTE), 
	PHONE VARCHAR2(18 BYTE), 
	ADDRESS VARCHAR2(100 BYTE), 
	CITY VARCHAR2(40 BYTE), 
	STATE VARCHAR2(75 BYTE), 
	ZIP VARCHAR2(12 BYTE), 
	ENROLLED CHAR(1 BYTE), 
	END_ENROLLED_DT DATE, 
	INACTIVE_DT DATE, 
	DECEASED_DT DATE, 
	LAST_APPT_DT DATE, 
	PREV_DMIS VARCHAR2(6 BYTE), 
	PREV_PCMID INTEGER, 
	PREV_POCID INTEGER, 
	PREV_ENROLLED CHAR(1 BYTE), 
	INSERTED_DT DATE DEFAULT sysdate, 
	PREV_ENROLLED_DT DATE, 
	DATA_SOURCE VARCHAR2(25 BYTE) NOT NULL, 
	PATIENT_DEERS_IDENTIFIER INTEGER, 
	OFFICE_PHONE VARCHAR2(18 BYTE), 
	STREET2 VARCHAR2(40 BYTE), 
	START_ENROLLED_DT DATE, 
	UPDATED_DT DATE, 
	PATIENT_DOB DATE, 
	PATIENT_CATEGORY_STATUS VARCHAR2(25 BYTE), 
	NED_HCDP_CODE VARCHAR2(5 BYTE), 
	NED_HCDP_TEXT VARCHAR2(150 BYTE), 
	UNIT_SHIP_ID NUMBER, 
	UNIT_SHIP_NAME VARCHAR2(45 BYTE), 
	MCP VARCHAR2(100 BYTE), 
	MCPID INTEGER, FLAG INTEGER ) 
TABLESPACE DZ_REG_DATA01 PCTUSED 40 PCTFREE 10 INITRANS 1 MAXTRANS 255 STORAGE 
( INITIAL 64K MINEXTENTS 1 MAXEXTENTS 2147483645 PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT ) 
LOGGING NOCOMPRESS NOCACHE NOPARALLEL MONITORING
/

COMMENT ON TABLE DZ_REG.DZ_PATIENTS IS 'List of patients.'
/

COMMENT ON COLUMN DZ_REG.DZ_PATIENTS.PATID IS 'This is the internal entry number of the patient.'
/

COMMENT ON COLUMN DZ_REG.DZ_PATIENTS.FMPSSN IS 'The patients Family Member Prefix and the sponsors Social Security Number.'
/

COMMENT ON COLUMN DZ_REG.DZ_PATIENTS.NAME IS 'The patients name.'
/

COMMENT ON COLUMN DZ_REG.DZ_PATIENTS.DOB IS 'CHCS source specific. The patients date of birth.'
/

COMMENT ON COLUMN DZ_REG.DZ_PATIENTS.ACV IS 'This is the patients Alternate Care Value flag (related to the patients DEERs eligibility).'
/

COMMENT ON COLUMN DZ_REG.DZ_PATIENTS.DMIS IS 'This is the DMIS ID associated with the patient.'
/

COMMENT ON COLUMN DZ_REG.DZ_PATIENTS.PCMID IS 'This is the internal entry number of the CHCS provider.'
/

COMMENT ON COLUMN DZ_REG.DZ_PATIENTS.POCID IS 'This is the primary hospital location with which the provider is associated.'
/

COMMENT ON COLUMN DZ_REG.DZ_PATIENTS.UPDATED IS 'CHCS source specific.  Fileman date when the record was updated in CHCS.'
/

COMMENT ON COLUMN DZ_REG.DZ_PATIENTS.PHONE IS 'The patients home phone number.'
/

COMMENT ON COLUMN DZ_REG.DZ_PATIENTS.ADDRESS IS 'The patients street address.'
/

COMMENT ON COLUMN DZ_REG.DZ_PATIENTS.CITY IS 'The patients city of residence.'
/

COMMENT ON COLUMN DZ_REG.DZ_PATIENTS.STATE IS 'The patients state of residence.'
/

COMMENT ON COLUMN DZ_REG.DZ_PATIENTS.ZIP IS 'The patients zip code of residence.'
/

COMMENT ON COLUMN DZ_REG.DZ_PATIENTS.ENROLLED IS 'CHCS source specific. NOT FOUND IN ICDB. Current enrolled identifier, (Q, U) = Patient is active.'
/

COMMENT ON COLUMN DZ_REG.DZ_PATIENTS.END_ENROLLED_DT IS 'This is the patients PCM enrollment end date.'
/

COMMENT ON COLUMN DZ_REG.DZ_PATIENTS.INACTIVE_DT IS 'Inactive date.'
/

COMMENT ON COLUMN DZ_REG.DZ_PATIENTS.DECEASED_DT IS 'NOT FOUND IN ICDB.'
/

COMMENT ON COLUMN DZ_REG.DZ_PATIENTS.LAST_APPT_DT IS 'This is the patients last record appointment date.  It doesn''t have to be w/ their provider.'
/

COMMENT ON COLUMN DZ_REG.DZ_PATIENTS.PREV_DMIS IS 'CHCS source specific. Previous DMIS ID.'
/

COMMENT ON COLUMN DZ_REG.DZ_PATIENTS.PREV_PCMID IS 'CHCS source specific. Previous internal entry number of the CHCS provider.'
/

COMMENT ON COLUMN DZ_REG.DZ_PATIENTS.PREV_POCID IS 'CHCS source specific. Previous primary hospital location with which the previous provider is associated.'
/

COMMENT ON COLUMN DZ_REG.DZ_PATIENTS.PREV_ENROLLED IS 'CHCS source specific. Previous enrolled identifier.'
/

COMMENT ON COLUMN DZ_REG.DZ_PATIENTS.INSERTED_DT IS 'Record inserted date.'
/

COMMENT ON COLUMN DZ_REG.DZ_PATIENTS.PREV_ENROLLED_DT IS 'CHCS source specific. Previous enrolled PCM enrollment end date.'
/

COMMENT ON COLUMN DZ_REG.DZ_PATIENTS.DATA_SOURCE IS 'Where did we get the data from. Eg: ICDB, CHCS.'
/

COMMENT ON COLUMN DZ_REG.DZ_PATIENTS.PATIENT_DEERS_IDENTIFIER IS 'The patients DEERS identifier.'
/

COMMENT ON COLUMN DZ_REG.DZ_PATIENTS.OFFICE_PHONE IS 'The patients office phone number.'
/

COMMENT ON COLUMN DZ_REG.DZ_PATIENTS.STREET2 IS 'The patients additional street address.'
/

COMMENT ON COLUMN DZ_REG.DZ_PATIENTS.START_ENROLLED_DT IS 'This is the patients PCM enrollment start date.'
/

COMMENT ON COLUMN DZ_REG.DZ_PATIENTS.UPDATED_DT IS 'Last modified in ICDB.'
/

COMMENT ON COLUMN DZ_REG.DZ_PATIENTS.PATIENT_DOB IS 'The patients date of birth.'
/

COMMENT ON COLUMN DZ_REG.DZ_PATIENTS.PATIENT_CATEGORY_STATUS IS 'This is the patients current status.  The values include: Active Duty, Retired, Other, Fam Mbr of Retired, and Fam Mbr of Active Duty.'
/

COMMENT ON COLUMN DZ_REG.DZ_PATIENTS.NED_HCDP_CODE IS 'Code for the Health Care Delivery Program.'
/

COMMENT ON COLUMN DZ_REG.DZ_PATIENTS.NED_HCDP_TEXT IS 'Health Care Delivery Program code text.'
/

COMMENT ON COLUMN DZ_REG.DZ_PATIENTS.UNIT_SHIP_ID IS 'This is the patient unit ship ID.'
/

COMMENT ON COLUMN DZ_REG.DZ_PATIENTS.UNIT_SHIP_NAME IS 'This is the patient unit ship name.'
/

COMMENT ON COLUMN DZ_REG.DZ_PATIENTS.MCP IS 'This is the patient assign MCP Place of care.'
/

COMMENT ON COLUMN DZ_REG.DZ_PATIENTS.MCPID IS 'This is the patient assign MCP Place of care ID.'
/

COMMENT ON COLUMN DZ_REG.DZ_PATIENTS.FLAG IS 'Dummy flag (0 or 1) as needed by PL/SQL code.'
/


CREATE INDEX DZ_REG.IDX_DZPATIENTS_DMISPATID ON DZ_REG.DZ_PATIENTS (DMIS, PATID) 
NOLOGGING TABLESPACE DZ_REG_IDX01 PCTFREE 10 INITRANS 2 MAXTRANS 255 STORAGE 
( INITIAL 1224K MINEXTENTS 1 MAXEXTENTS 2147483645 PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT ) NOPARALLEL
/


CREATE INDEX DZ_REG.IDX_DZPATIENTS_COMBO3 ON DZ_REG.DZ_PATIENTS (DMIS, PATID, END_ENROLLED_DT) 
NOLOGGING TABLESPACE DZ_REG_IDX01 PCTFREE 10 INITRANS 2 MAXTRANS 255 STORAGE 
( INITIAL 1064K MINEXTENTS 1 MAXEXTENTS 2147483645 PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT ) NOPARALLEL
/


CREATE INDEX DZ_REG.IDX_DZPATIENTS_DMIS ON DZ_REG.DZ_PATIENTS (DMIS) 
NOLOGGING TABLESPACE DZ_REG_IDX01 PCTFREE 10 INITRANS 2 MAXTRANS 255 STORAGE 
( INITIAL 424K MINEXTENTS 1 MAXEXTENTS 2147483645 PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT ) NOPARALLEL
/


CREATE INDEX DZ_REG.IDX_DZPATIENTS_FMPSSN ON DZ_REG.DZ_PATIENTS (FMPSSN) 
NOLOGGING TABLESPACE DZ_REG_IDX01 PCTFREE 10 INITRANS 2 MAXTRANS 255 STORAGE 
( INITIAL 784K MINEXTENTS 1 MAXEXTENTS 2147483645 PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT ) NOPARALLEL
/


CREATE INDEX DZ_REG.IDX_DZPATIENTS_PCMID ON DZ_REG.DZ_PATIENTS (PCMID) 
NOLOGGING TABLESPACE DZ_REG_IDX01 PCTFREE 10 INITRANS 2 MAXTRANS 255 STORAGE 
( INITIAL 744K MINEXTENTS 1 MAXEXTENTS 2147483645 PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT ) NOPARALLEL
/


CREATE INDEX DZ_REG.IDX_DZPATIENTS_POCID ON DZ_REG.DZ_PATIENTS (POCID) 
NOLOGGING TABLESPACE DZ_REG_IDX01 PCTFREE 10 INITRANS 2 MAXTRANS 255 STORAGE 
( INITIAL 464K MINEXTENTS 1 MAXEXTENTS 2147483645 PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT ) NOPARALLEL
/


CREATE INDEX DZ_REG.IDX_DZPATIENTS_FLAG ON DZ_REG.DZ_PATIENTS (FLAG) 
NOLOGGING TABLESPACE DZ_REG_IDX01 PCTFREE 10 INITRANS 2 MAXTRANS 255 STORAGE 
( INITIAL 2544K MINEXTENTS 1 MAXEXTENTS 2147483645 PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT ) NOPARALLEL
/


CREATE INDEX DZ_REG.IDX_DZPATIENTS_PATIDFLAG ON DZ_REG.DZ_PATIENTS (PATID, FLAG) 
NOLOGGING TABLESPACE DZ_REG_IDX01 PCTFREE 10 INITRANS 2 MAXTRANS 255 STORAGE 
( INITIAL 2544K MINEXTENTS 1 MAXEXTENTS 2147483645 PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT ) NOPARALLEL
/


ALTER TABLE DZ_REG.DZ_PATIENTS ADD ( CONSTRAINT PK_DZ_PATIENTS PRIMARY KEY (PATID))
/


ALTER TABLE DZ_REG.DZ_PATIENTS ADD ( CONSTRAINT FK_DZPATIENTS_PCMID FOREIGN KEY (PCMID) REFERENCES DZ_REG.DZ_PCM (PCMID))
/

ALTER TABLE DZ_REG.DZ_PATIENTS ADD ( CONSTRAINT FK_DZPATIENTS_POCID FOREIGN KEY (POCID) REFERENCES DZ_REG.DZ_POC (POCID))
/


GRANT SELECT ON  DZ_REG.DZ_PATIENTS TO DZREG_READ
/

GRANT DELETE, INSERT, SELECT, UPDATE ON  DZ_REG.DZ_PATIENTS TO DZREG_BATCH
/

GRANT SELECT ON  DZ_REG.DZ_PATIENTS TO USR_PRODDZ
/

