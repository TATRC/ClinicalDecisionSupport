


drop table ref_taxonomy_code;
CREATE TABLE ref_taxonomy_code
(
  concept_code character varying(255),
  concept_name character varying(255),
  preferred_concept_name text,
  preferred_alternate_code character varying(255),
  code_system_oid character varying(255),
  code_system_name character varying(255),
  code_system_code character varying(255),
  code_system_version character varying(255),
  hl7_table_0396_code character varying(255),
  source_filename character varying(255),
  dictionary_id  integer,
  disable  boolean default(false)       --> false, code is not available for use. true, it is available to use to create rules.
  
);



drop table ref_dictionary;
CREATE TABLE ref_dictionary
(
  dictionary_id serial NOT NULL,
  concept_name character varying(255),
  active_code_system_oid  character varying(255),  --> contains code system version number that is valid to use for rule creation.
  PRIMARY KEY (dictionary_id)
);

INSERT INTO ref_dictionary(
	concept_name, active_code_system_oid)
VALUES 
 ('LAB', '2.16.840.1.113883.6.1')   
,('DIAGNOSTIC', '2.16.840.1.113883.6.103')
,('MEDICATION', '2.16.840.1.113883.6.69')
,('ALLERGY', '2.16.840.1.113883.6.96')
,('PROCEDURES', '2.16.840.1.113883.6.104') 
,('GENDER', '999.990')
,('AGERANGE', '999.980')
,('REGISTRY', '999.970')
,('SPECIALTY', '2.16.840.1.113883.5.53')
,('TASK', '999.960')
,('ACUITY', '2.16.840.1.113883.6.96')
,('SOURCEINSTITUTIONTYPE', '999.940')
,('KMTYPE', '999.950')                -- Clinical Objective
,('ORGLEVEL', '999.930')
,('KMSTATUS', '999.920')
,('INFERENCE', '999.910')

,('CAREFACILITYTYPE', '999.900')
,('COGNITIVE', '999.899')
,('TASKQUALIFIER', '999.898')
,('DEMOGSTATUS', '999.897')
;

-- --------------------------------------------------------------
-- ALTER TABLE ref_dictionary DROP CONSTRAINT cstr_ref_dictionary;
ALTER TABLE ref_dictionary
  ADD CONSTRAINT cstr_ref_dictionary PRIMARY KEY(dictionary_id);

-- --------------------------------------------------------------

-- ALTER TABLE ref_taxonomy_code DROP CONSTRAINT fkey_ref_taxonomy_code_id;
ALTER TABLE ref_taxonomy_code
  ADD CONSTRAINT fkey_ref_taxonomy_code_id FOREIGN KEY (dictionary_id)
      REFERENCES ref_dictionary (dictionary_id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE CASCADE;

      
-- ALTER TABLE ref_taxonomy_code DROP CONSTRAINT ref_taxonomy_code_pk;
ALTER TABLE ref_taxonomy_code
  ADD CONSTRAINT ref_taxonomy_code_pk PRIMARY KEY(dictionary_id, code_system_oid, concept_code);

-- DROP INDEX ref_taxonomy_code_pk_idx;
CREATE INDEX ref_taxonomy_code_pk_idx
   ON ref_taxonomy_code (dictionary_id ASC NULLS LAST, code_system_oid ASC NULLS LAST, concept_code ASC NULLS LAST);


-- DROP INDEX ref_taxonomy_code_oid_disable_idx;
CREATE INDEX ref_taxonomy_code_oid_disable_idx
  ON ref_taxonomy_code
  USING btree
  (code_system_oid, concept_code, disable);


-- DROP INDEX ref_taxonomy_code_pk_idx;
CREATE INDEX ref_taxonomy_code_pk_idx
  ON ref_taxonomy_code
  USING btree
  (dictionary_id, code_system_oid, concept_code);
			 
-- ----------------------------------------------------			 
/* TESTING
-- LAB test data

update ref_taxonomy_code set disable = FALSE where dictionary_id = 1 and concept_code = '600-7';
update ref_taxonomy_code set disable = FALSE where dictionary_id = 1 and concept_code = '630-4';
update ref_taxonomy_code set disable = FALSE where dictionary_id = 1 and concept_code = '10839-9';
commit;
*/