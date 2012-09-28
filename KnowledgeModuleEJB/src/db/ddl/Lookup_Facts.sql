/** 
 * FACTS - LAB 
 * PHVS_LabTestName_CDC_V3.xls
 */  

-- Ordering is important for correct dependecies
DROP TABLE IF EXISTS ref_fact_scheme_type CASCADE;
DROP TABLE IF EXISTS ref_fact_type;
DROP TABLE IF EXISTS ref_fact_scheme;
DROP TABLE IF EXISTS ref_fact_code;

-- -----------------------------------------
-- Table containing all possible fact types
-- i.e. FactType = (LAB, DIAGNOSTICS, ALLERGIES, MEDICATION)
-- -----------------------------------------
CREATE TABLE ref_fact_type
(
	 facttypeid  	SERIAL
	,facttype 	   VARCHAR(32) NOT NULL
	--,PRIMARY KEY (facttypeid)
) ;

INSERT INTO ref_fact_type
 (FactType)
VALUES
 ('LAB')
,('DIAGNOSTIC')
,('ALLERGY')
,('MEDICATION')
;

-- -----------------------------------------
-- table containing all possible fact schemes
-- i.e. SchemeName = (LOINC , ICD-9 , SNOWMED)
-- -----------------------------------------
CREATE TABLE ref_fact_scheme
(
	 factschemeid  SERIAL
	,schemename    VARCHAR(32) NOT NULL
	,descr         TEXT
	--,PRIMARY KEY (FactSchemeId)
) ;

INSERT INTO ref_fact_scheme
 (SchemeName)
VALUES
 ('LOINC')
,('ICD-9')
,('FDA')
,('SNOWMED')
;

/* -- -----------------------------------------
-- Linker table for al posible types per fact scheme
-- (i.e. Scheme ICD-9 can be both LAB and DIAGNOSTICS)
  LOINC  == LAB
  ICD-9  == DIAGONISTIC
  FDA    == MEDICATION
  SNOWMED    == ALLERGY
-- ----------------------------------------- */
CREATE TABLE ref_fact_scheme_type
(
    schemetypeid  SERIAL
	,factschemeid  INTEGER
	,facttypeid    INTEGER
   ,descr         VARCHAR(255)
	--,PRIMARY KEY (SchemeTypeId)
   --,CONSTRAINT FK_ref_fact_scheme FOREIGN KEY (FactSchemeId) REFERENCES ref_fact_scheme (FactSchemeId)
   --,CONSTRAINT FK_ref_fact_type   FOREIGN KEY (FactTypeId) REFERENCES ref_fact_type (FactTypeId)
) ;

INSERT INTO ref_fact_scheme_type
 (FactSchemeId, FactTypeId, Descr)
VALUES
 ((select FactSchemeId from ref_fact_scheme where SchemeName = 'LOINC'),
  (select FactTypeId   from ref_fact_type   where FactType   = 'LAB'),
  'LOINC-LAB')
,((select FactSchemeId from ref_fact_scheme where SchemeName = 'ICD-9'),
  (select FactTypeId   from ref_fact_type   where FactType   = 'DIAGNOSTIC'),
  'ICD9-DIAGNOSTIC')
,((select FactSchemeId from ref_fact_scheme where SchemeName = 'FDA'),
  (select FactTypeId   from ref_fact_type   where FactType   = 'MEDICATION'),
  'FDA-MEDICATION')
,((select FactSchemeId from ref_fact_scheme where SchemeName = 'SNOWMED'),
  (select FactTypeId   from ref_fact_type   where FactType   = 'ALLERGY'),
  'HL7-ALLERGY')
,((select FactSchemeId from ref_fact_scheme where SchemeName = 'ICD-9'),
  (select FactTypeId   from ref_fact_type   where FactType   = 'LAB'),
  'ICD9-LAB')
;
COMMIT;

-- -----------------------------------------
-- table containing all possible fact codes per fact Scheme/Type
-- i.e.
-- 1) fact type LAB can have fact scheme ICD-9
--          which has it's list of fact codes.
-- 2) fact type DIAGNOSTIC can have fact scheme ICD-9
--          which has it's list of fact codes.
-- 3) fact type LAB can have fact scheme LOINC
--          which has it's list of fact codes.
--
-- NOTE(09/18/2010) - had to remove FK constraints since JPA will also pull 
--   on the this table when pulling ref_fact_scheme_type.  
--   This would be unecssary for our purposes at times.
-- -----------------------------------------
CREATE TABLE ref_fact_code (
     SchemeTypeId              INTEGER
    ,Concept_Code              VARCHAR(255)
    ,Concept_Name              VARCHAR(255)
    ,Preferred_Concept_Name    TEXT
    ,Preferred_Alternate_Code  VARCHAR(255)
    ,Code_System_OID           VARCHAR(255)
    ,Code_System_Name          VARCHAR(255)
    ,Code_System_Code          VARCHAR(255)
    ,Code_System_Version       VARCHAR(255)
    ,HL7_Table_0396_Code       VARCHAR(255)
    --,PRIMARY KEY (SchemeTypeId, Concept_Code)
    -- ,CONSTRAINT FK_ref_fact_code_scheme FOREIGN KEY (SchemeTypeId) REFERENCES ref_fact_scheme_type (SchemeTypeId)
);

--IMPORT DATA in BULK ---> via Maestro DataWizard app
-- Copy command will only work if you are executing it
-- while sitting on the db server itself (i.e. socraticgrid.org).
-- So we'll use Maestro DataWizard.
-- COPY ref_fact_code FROM 'C:/DSS/KMR/REFDATA/logs/LAB-LOINC/PHVS_LabTestName_CDC_V4_kmr.csv' USING DELIMITERS ',';


ALTER TABLE ref_fact_type
   ADD CONSTRAINT pkey_ref_fact_type_facttypeid
   PRIMARY KEY (facttypeid);

ALTER TABLE ref_fact_scheme
   ADD CONSTRAINT pkey_ref_fact_scheme_factschemeid
   PRIMARY KEY (factschemeid);

ALTER TABLE ref_fact_scheme_type
   ADD CONSTRAINT pkey_ref_fact_schemetype_schemetypeid
   PRIMARY KEY (schemetypeid);

ALTER TABLE ref_fact_scheme_type
   ADD CONSTRAINT fkey_ref_fact_schemetype_factschemeid
   FOREIGN KEY (factschemeid)
   REFERENCES ref_fact_scheme (factschemeid) MATCH SIMPLE
   ON UPDATE NO ACTION ON DELETE NO ACTION;

ALTER TABLE ref_fact_scheme_type
   ADD CONSTRAINT fkey_ref_fact_schemetype_facttypeid
   FOREIGN KEY (facttypeid)
   REFERENCES ref_fact_type (facttypeid) MATCH SIMPLE
   ON UPDATE NO ACTION ON DELETE NO ACTION;

ALTER TABLE ref_fact_code
   ADD CONSTRAINT ref_fact_code_pkey
   PRIMARY KEY (schemetypeid, concept_code);


CREATE INDEX idx_ref_fact_type_facttype
  ON ref_fact_type (facttype);

CREATE INDEX idx_ref_fact_scheme_schemename
  ON ref_fact_scheme (schemename);

CREATE INDEX idx_ref_fact_code_schemetypeid_concept_code
  ON ref_fact_code (schemetypeid, concept_code);

ANALYZE ref_fact_code;
ANALYZE ref_fact_type;
ANALYZE ref_fact_scheme;
ANALYZE ref_fact_scheme_type;