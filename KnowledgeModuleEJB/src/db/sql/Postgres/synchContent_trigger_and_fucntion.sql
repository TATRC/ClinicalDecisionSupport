
/*
DROP TRIGGER synchKMR_content ON public.content;
CREATE TRIGGER synchKMR_content
  AFTER UPDATE
  ON public.content
  FOR EACH ROW EXECUTE PROCEDURE public.synchContent();
*/


-- DROP FUNCTION synchContent();

CREATE OR REPLACE FUNCTION synchContent()
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

v_authorid integer;

BEGIN
   /*DEBUG*/ insert into debug_content VALUES ('UPDATE-old', now(), OLD.*);
   /*DEBUG*/ insert into debug_content VALUES ('UPDATE-new', now(), NEW.*);

   v_contentId := NEW.id;
   v_model := NEW.erdf;

   -- Look for the update that will populate the new erdf.
   IF (OLD.erdf <> NEW.erdf) THEN

      /*DEBUG*/ insert into debug_content VALUES ('FOUND', now(), NEW.*); 

	   -- Getting the Model's metadata	
       SELECT 
	       r.ident_id   --km_id
	      ,r.title       --km_name
	      ,'VALIDATION'  --km_type
	      ,'PERSONAL'    --km_organizationlevel
	      ,r.summary     --km_keywords
	      ,r.summary     --km_description
	      ,0             --parent_km_id
	      ,'NHRC'        --origininstitution
--	      ,to_char(a.subject_id,'999999')  --createdby_authorid
--	      ,a.subject_name   --createdby_authorname
	      ,r.created        --createdtimestamp
	      ,1                --latestversionnum
--	      ,to_char(a.subject_id,'999999')     --lastmodifiedby_authorid
--	      ,a.subject_name   --lastmodifiedby_authorname
	      ,r.updated        --lastmodifiedtimestamp
	      ,null             --productionversionnum
	      ,null             --validationstatus
	      ,false            --ischeckedout
          ,r.type           --model type
       INTO   
	       v_km_id
	      ,v_km_name
	      ,v_km_type
	      ,v_km_organizationlevel
	      ,v_km_keywords
	      ,v_km_description
	      ,v_parent_km_id
	      ,v_origininstitution
--	      ,v_createdby_authorid
--	      ,v_createdby_authorname
	      ,v_createdtimestamp
	      ,v_latestversionnum
--	      ,v_lastmodifiedby_authorid
--	      ,v_lastmodifiedby_authorname
	      ,v_lastmodifiedtimestamp
	      ,v_productionversionnum
	      ,v_validationstatus
	      ,v_ischeckedout
	      ,v_model_type
       FROM representation r 
       WHERE r.id = v_contentId
       ;

	   -- DBG: PRINT OUT
	   RAISE NOTICE '=========> v_km_id = %', v_km_id;
	   RAISE NOTICE '=========> v_km_name = %', v_km_name;
	   RAISE NOTICE '=========> v_km_type = %', v_km_type;

      insert into debug (note1, note2, ival1, ival2) 
      values ('GATHER', v_model, v_km_id, v_contentId);
    
/*DEBUG*/ 
-- ----------------------------- 
-- PROB:  ACCESS RECORD/DATA not there.
-- Trying to get the Author Id and Name but 
-- ACCESS has no content.
-- -----------------------------
select to_char(a.subject_id, '999999'), a.subject_name, a.subject_id
into   v_createdby_authorid,v_createdby_authorname, v_authorid
from access a 
where a.object_id = v_km_id;

insert into debug (note1, note2, ival1, ival2) 
values ('ACCESS-1', v_createdby_authorid, v_authorid, 0);
/*DEBUG*/ 

      -- --------------------------------------------------------------------
      -- Call routine to create a completely new KnowledgeModule and KM_Version.
      -- IN:  contentId ==> KM_ID
      -- Use that KM_ID to  locate the KM_VERSION record to update/insert.
      -- --------------------------------------------------------------------
      INSERT INTO public.knowledgemodule
      (km_id, km_name, km_type, km_organizationlevel, km_keywords, km_description, parent_km_id, origininstitution, createdby_authorid, createdby_authorname, createdtimestamp, latestversionnum, lastmodifiedby_authorid, lastmodifiedby_authorname, lastmodifiedtimestamp, productionversionnum, validationstatus, ischeckedout)
      VALUES
      (v_km_id
      ,v_km_name
      ,v_km_type
      ,v_km_organizationlevel
      ,v_km_keywords
      ,v_km_description
      ,v_parent_km_id
      ,v_origininstitution
      ,v_createdby_authorid
      ,v_createdby_authorname
      ,v_createdtimestamp
      ,v_latestversionnum
      ,v_lastmodifiedby_authorid
      ,v_lastmodifiedby_authorname
      ,v_lastmodifiedtimestamp
      ,v_productionversionnum
      ,v_validationstatus
      ,v_ischeckedout
      );

      INSERT INTO public.km_version (
  		 km_id                   
  		,km_versionnum           
  		,kmv_name                
  		,acl_id                  
  		,lmt_id                  
  		,validationstatus        
  		,logic_intermediateform  
  		,logic_nativeform        
  		,logic_binaryform        
  		,authorcomments          
  		,lastmodifiedtimestamp   
  		,createdby_authorid      
  		,createdby_authorname    
  		,createdtimestamp                             
      ) VALUES (
         v_km_id                  --km_id  		                   
  		,1                   	  --km_versionnum           
  		,v_km_name||'v1'          --kmv_name                
  		,21                       --acl_id  -- TBD:DEFAULT for now                 
  		,NULL                     --lmt_id                  
  		,'DRAFT'                  --validationstatus        
  		,NULL                     --logic_intermediateform  
  		,v_model                  --logic_nativeform        
  		,NULL                     --logic_binaryform        
  		,NULL                     --authorcomments          
  		,v_lastmodifiedtimestamp  --lastmodifiedtimestamp   
  		,v_createdby_authorid     --createdby_authorid      
  		,v_createdby_authorname   --createdby_authorname    
  		,v_createdtimestamp       --createdtimestamp 
      );

   ELSE
       -- CHECK if can get teh author Id and Name at later update.
       SELECT r.ident_id
       INTO   v_km_id
       FROM representation r 
       WHERE r.id = v_contentId;

select to_char(a.subject_id, '999999'), a.subject_name, a.subject_id
into   v_createdby_authorid,v_createdby_authorname, v_authorid
from access a 
where a.object_id = v_km_id;

insert into debug (note1, note2, ival1, ival2) 
values ('ACCESS-ELSE', v_createdby_authorid, v_authorid, v_km_id);

   END IF;
	
   RETURN NULL;
END;$$
  LANGUAGE 'plpgsql' VOLATILE
  ;


















