/*
CREATE OR REPLACE FUNCTION synchContent()
  RETURNS trigger AS
$$ DECLARE
    v_uri           text;
    v_model           text;
    v_contentId       integer;
BEGIN
   v_contentId := NEW.id;
   v_model := NEW.erdf;

   IF (TG_OP = 'INSERT') THEN
      v_uri = 'Inserting: '||v_contentId;
   ELSEIF (TG_OP = 'UPDATE') THEN
      v_uri = 'Updating';
   END IF;
 
   insert into identity
   VALUES (v_contentId, v_uri);

   RETURN NULL;
END;$$
  LANGUAGE 'plpgsql' VOLATILE
  ;
*/

/*
DROP TRIGGER synchKMR_content ON public.content;
CREATE TRIGGER synchKMR_content
  AFTER INSERT OR UPDATE
  ON public.content
  FOR EACH ROW
  EXECUTE PROCEDURE public.synchContent();
*/

-- insert into content (erdf) values ('model-14');
-- insert into content (erdf) values ('model-10');

update content set erdf = 'model-10-b' where id = 10;
commit;

-- ===============================
DROP TABLE public.debug;

CREATE TABLE public.debug (
  note1       text,
  note2       text,
  ival1       integer,
  ival2       integer,
  createdate  timestamp(6) WITHOUT TIME ZONE DEFAULT CURRENT_TIMESTAMP
);

CREATE INDEX debug_index01
  ON public.debug
  (createdate);

-- =================================
DROP TABLE public.debug_content;
CREATE TABLE public.debug_content (
  trxn       text,
  createdate  timestamp(6) WITHOUT TIME ZONE DEFAULT CURRENT_TIMESTAMP
  id         integer,
  erdf       text ,
  svg        text,
  png_large  bytea,
  png_small  bytea

);

-- ================================
DROP TABLE public.debug_knowledgemodule;
CREATE TABLE public.debug_knowledgemodule (
  km_id                      integer ,
  km_name                    varchar(50) ,
  km_type                    varchar(50) DEFAULT NULL::character varying,
  km_organizationlevel       varchar(50) DEFAULT NULL::character varying,
  km_keywords                varchar(255),
  km_description             varchar(255),
  parent_km_id               integer,
  origininstitution          varchar(50),
  createdby_authorid         varchar(50),
  createdby_authorname       varchar(50),
  createdtimestamp           timestamp(6) WITHOUT TIME ZONE,
  latestversionnum           integer,
  lastmodifiedby_authorid    varchar(50),
  lastmodifiedby_authorname  varchar(50),
  lastmodifiedtimestamp      timestamp(6) WITHOUT TIME ZONE,
  productionversionnum       integer,
  validationstatus           varchar(50) DEFAULT NULL::character varying,
  ischeckedout               boolean
);

TRUNCATE TABLE public.debug_content;

TRUNCATE TABLE public.debug_representation;
TRUNCATE TABLE public.debug_knowledgemodule;
TRUNCATE TABLE public.debug;


