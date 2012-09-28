
/*
DROP TABLE public.debug_representation;
CREATE TABLE public.debug_representation (
  trxn        text,
  crdate      timestamp,
  id          integer,
  ident_id    integer ,
  mime_type   text ,
  "language"  text DEFAULT 'en_US'::text,
  title       text DEFAULT ''::text,
  summary     text DEFAULT ''::text,
  created     timestamp WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,
  updated     timestamp WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,
  "type"      text  DEFAULT 'undefined'::text);

DROP TRIGGER synchKMR_rep ON public.representation;
CREATE TRIGGER synchKMR_rep 
  AFTER INSERT OR UPDATE ON public.representation
  FOR EACH ROW EXECUTE PROCEDURE public.synchRep();
*/


-- DROP FUNCTION synchRep();

CREATE OR REPLACE FUNCTION synchRep()
  RETURNS trigger AS
$$ DECLARE
    v_model           text;
    v_contentId       integer;

    v_model_type      text;  --> represents the type of the model
                           --  (i.e. http://b3mn.org/stencilset/bpmn2.0# )

  v_km_id                      integer;
  v_km_name                    varchar(50);
  v_km_type                    varchar(50);
  v_km_organizationlevel       varchar(50);
  v_km_keywords                varchar(255);
  v_km_description             varchar(255);
  v_parent_km_id               integer;
  v_origininstitution          varchar(50);
  v_createdby_authorid         varchar(50);
  v_createdby_authorname       varchar(50);
  v_createdtimestamp           timestamp(6);
  v_latestversionnum           integer;
  v_lastmodifiedby_authorid    varchar(50);
  v_lastmodifiedby_authorname  varchar(50);
  v_lastmodifiedtimestamp      timestamp(6);
  v_productionversionnum       integer;
  v_validationstatus           varchar(50);
  v_ischeckedout               boolean;

  v_lastKMVnum                 integer;

BEGIN
   -- DEBUG
   IF (TG_OP = 'INSERT') THEN
      insert into debug_representation VALUES ('INSERT', now(), NEW.*);
      insert into debug_content (trxn, createdate, id) VALUES ('INS-REP',now(), NEW.ident_id);
   ELSEIF (TG_OP = 'UPDATE') THEN
      insert into debug_representation VALUES ('UPDATE-old', now(), OLD.*);
      insert into debug_representation VALUES ('UPDATE-new', now(), NEW.*);
   END IF;
	
   RETURN NULL;
END;$$
  LANGUAGE 'plpgsql' VOLATILE
  ;
















