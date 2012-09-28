
/**
 * CLEANUP for Import test data ONLY
 */

delete FROM KMV_InferenceEngineDependency where KMV_ID in 
(SELECT KMV_ID FROM KM_Version where KM_ID in 
  (SELECT KM_ID FROM KnowledgeModule where KM_Name in ('bobKM', 'susanKM')))
;

delete from TT_Specification where TT_ID in 
(select TT_ID FROM KMV_TaskDependency where KMV_ID in 
(SELECT KMV_ID FROM KM_Version where KM_ID in 
  (SELECT KM_ID FROM KnowledgeModule where KM_Name in ('bobKM', 'susanKM')))
);
delete FROM KMV_TaskDependency where KMV_ID in 
(SELECT KMV_ID FROM KM_Version where KM_ID in 
  (SELECT KM_ID FROM KnowledgeModule where KM_Name in ('bobKM', 'susanKM')))
;

delete from PopulationSpecification where BP_ID in 
(select BP_ID FROM KMV_PopulationDependency where KMV_ID in 
(SELECT KMV_ID FROM KM_Version where KM_ID in 
  (SELECT KM_ID FROM KnowledgeModule where KM_Name in ('bobKM', 'susanKM')))
);
delete FROM KMV_PopulationDependency where KMV_ID in 
(SELECT KMV_ID FROM KM_Version where KM_ID in 
  (SELECT KM_ID FROM KnowledgeModule where KM_Name in ('bobKM', 'susanKM')))
;

delete from FactSpecification where FD_ID in 
(select FD_ID FROM KMV_FactDependency where KMV_ID in 
(SELECT KMV_ID FROM KM_Version where KM_ID in 
  (SELECT KM_ID FROM KnowledgeModule where KM_Name in ('bobKM', 'susanKM')))
);
delete FROM KMV_FactDependency where KMV_ID in 
(SELECT KMV_ID FROM KM_Version where KM_ID in 
  (SELECT KM_ID FROM KnowledgeModule where KM_Name in ('bobKM', 'susanKM')))
;

delete from KM_Specialty where KM_ID in 
(SELECT KM_ID FROM KnowledgeModule where KM_Name in ('bobKM', 'susanKM'))
;

delete from ACL_Permission where ACL_ID in 
(SELECT ACL_ID FROM KM_Version where KM_ID in 
  (SELECT KM_ID FROM KnowledgeModule where KM_Name in ('bobKM', 'susanKM')))
;

DELETE FROM KM_Version where KM_ID in 
	(SELECT KM_ID FROM KnowledgeModule where KM_Name in ('bobKM', 'susanKM'))
;

delete from KMV_AccessControlList where 
   ACL_Name like 'bob%' OR ACL_Name like 'susan%'
;

delete FROM KnowledgeModule where KM_Name in ('bobKM', 'susanKM')
;

COMMIT;
