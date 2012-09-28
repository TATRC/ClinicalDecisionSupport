/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gov.hhs.fha.nhinc.kmr.cohort;

import gov.hhs.fha.nhinc.adapter.fact.FactQueryRequestType;
import gov.hhs.fha.nhinc.adapter.fact.FactQueryResponseType;
import gov.hhs.fha.nhinc.kmr.patientcohort.PreferenceType;
import gov.hhs.fha.nhinc.kmr.patientcohort.PreferencesType;
import gov.hhs.fha.nhinc.kmr.patientcohort.PatientCohort;
import gov.hhs.fha.nhinc.kmr.patientcohort.PopulationType;
import gov.hhs.fha.nhinc.kmr.patientcohort.PopulationRegistriesType;
import gov.hhs.fha.nhinc.adapter.fact.FactType;
import gov.hhs.fha.nhinc.adapter.fact.PersonFactType;
import gov.hhs.fha.nhinc.adapter.factservice.*;
import gov.hhs.fha.nhinc.kmr.properties.DateProperty;
import gov.hhs.fha.nhinc.kmr.properties.DatePropertyAccessor;
import gov.hhs.fha.nhinc.kmr.properties.PropertyAccessor;
import gov.hhs.fha.nhinc.kmr.properties.PropertyAccessException;
import java.util.Iterator;
import java.util.List;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.ws.BindingProvider;
import mil.navy.med.dzreg.types.RegistryProfileType;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author Jerry Goodnough
 */

public class PatientCohortQueryServiceImpl extends PatientCohortQueryService
{
    private static Log log = LogFactory.getLog(PatientCohortQueryServiceImpl.class);


    private static PatientCohortQueryServiceImpl singlton = new PatientCohortQueryServiceImpl();

    public static PatientCohortQueryServiceImpl getSinglton()
    {
        return singlton;
    }
    public PatientCohort patientCohortQuery(gov.hhs.fha.nhinc.kmr.patientcohort.PatientCohortQueryRequestType cohortQueryRequest) {

        // For testing purposes, properties file supercedes demographics
        boolean bGetDemographics = false;
        PatientCohort out = new PatientCohort();
        out.setPatientId(cohortQueryRequest.getPatientId());
        // Assume the patient is alive
        out.setAlive(true);

        // Load default Info from properties files
        try
        {
            log.debug("Retrieving default cohort information in cohort"+cohortQueryRequest.getPatientId()+".properties");
            String admissionStatus = PropertyAccessor.getProperty("cohort"+cohortQueryRequest.getPatientId(), "AdmissionStatus");
            out.setAdmissionStatus(admissionStatus);
            String ageClassification = PropertyAccessor.getProperty("cohort"+cohortQueryRequest.getPatientId(), "AgeClassification");
            if (ageClassification != null && !ageClassification.isEmpty())
            {
                log.debug("AgeClassification: "+ageClassification);
                out.setAgeClassification(ageClassification);
            }
            else
            {
                bGetDemographics = true;
            }
            String gender = PropertyAccessor.getProperty("cohort"+cohortQueryRequest.getPatientId(), "Gender");
            if (gender != null && !gender.isEmpty())
            {
                log.debug("Gender: "+gender);
                out.setGender(gender);
            }
            else
            {
                bGetDemographics = true;
            }
            String patientLocation = PropertyAccessor.getProperty("cohort"+cohortQueryRequest.getPatientId(), "PatientLocation");
            log.debug("PatientLocation: "+patientLocation);
            out.setPatientLocation(patientLocation);
            String patientLocationId = PropertyAccessor.getProperty("cohort"+cohortQueryRequest.getPatientId(), "PatientLocationId");
            log.debug("PatientLocationId: "+patientLocationId);
            out.setPatientLocationId(patientLocationId);
            String primaryCareManagerId = PropertyAccessor.getProperty("cohort"+cohortQueryRequest.getPatientId(), "PrimaryCareManagerId");
            log.debug("PrimaryCareManagerId: "+primaryCareManagerId);
            out.setPrimaryCareManagerId(primaryCareManagerId);
            String primaryCareTeamId = PropertyAccessor.getProperty("cohort"+cohortQueryRequest.getPatientId(), "PrimaryCareTeamId");
            log.debug("PrimaryCareTeamId: "+primaryCareTeamId);
            out.setPrimaryCareTeamId(primaryCareTeamId);
        }
        catch (PropertyAccessException pae)
        {
            log.debug("No default cohort information in properties files for "+cohortQueryRequest.getPatientId());
        }

        //Call the registry service
        if (out.getPopulationRegistries() == null)
        {
            out.setPopulationRegistries(new PopulationRegistriesType());
        }
        LoadRegistryInfo(cohortQueryRequest.getPatientId(),out.getPopulationRegistries());

        //Handle Patient Preferences
        PreferenceType pref = new PreferenceType();
        pref.setCode("9809.1.1");
        pref.setName("ADV Dir");
        pref.setValue("UNK");
        if (out.getPreferences() == null)
        {
            out.setPreferences(new PreferencesType());
        }
        out.getPreferences().getPreferences().add(pref);

        if (bGetDemographics)
        {
            try
            {
                //Grab core demographic data for the cohort
                List<FactType> lDemographics = getHistoricDemographicFacts(cohortQueryRequest.getPatientId());

                if ((lDemographics != null) && (lDemographics.size() > 0))
                {
                   Iterator iter = lDemographics.iterator();
                   while (iter.hasNext())
                   {
                       Object fact = iter.next();
                       if (fact instanceof PersonFactType)
                       {
                           PersonFactType pf = (PersonFactType) fact;
                           if (out.getGender() == null || out.getGender().isEmpty())
                           {
                               if (pf.getGender() != null && pf.getGender().getCode() != null)
                               {
                                  out.setGender(pf.getGender().getCode());
                               }
                               else
                               {
                                   log.error("Error: PersonFactType does not contain a valid gender code.");

                               }
                           }

                           // Classifiy the Age
                           if (out.getAgeClassification() == null || out.getAgeClassification().isEmpty())
                           {
                               if (pf.getDateOfBirth() != null)
                               {
                                  out.setAgeClassification(getAgeClassification(pf.getDateOfBirth()));
                               }
                               else
                               {
                                   log.error("Error: PersonFactType does not contain a valid DOB.");

                               }
                           }
                       }
                   }
                }
            }
            catch (Exception e)
            {
                log.error("Error retrieving demographics from CAL Services: ", e);
            }
        }

        return out;
    }
    
    
    private String getAgeClassification(XMLGregorianCalendar dob)
    {
        String out = "Adult";
        java.util.GregorianCalendar now =  (java.util.GregorianCalendar) java.util.GregorianCalendar.getInstance();

        DateDifference dd = DateDifference.getDateDifference(now,dob.toGregorianCalendar());

        int years = dd.getYears();
        /*
            Age Classification Rules
            0 to < 29d	Newborn
            29d to <12m	Infant
            12m to <13y	Child
            13y to <21y	Adolescent
            21y + 	Adult
        */

        if (dd.getAbdDays()<29)
        {
            out="Newborn";
        }
        else if(years==0)
        {
            out="Infant";
        }
        else if (years<13)
        {
            out="Child";
        }
        else if (years < 21)
        {
            out= "Adolescent";
        }
        else
        {
            out = "Adult";
        }
        return out;
            
    }
    private List<FactType> getHistoricDemographicFacts(String patientId)
    {
        List<FactType> res = null;
        try
        {
            FactQueryRequestType request = new FactQueryRequestType();
            request.setPatientId(patientId);
            //request.setRoot(homeCommunityId);
            //request.setAssigningAuthorityName(homeCommunityName);
            DateProperty beginDateProperty = DatePropertyAccessor.getDateProperty("dss", "Patient", "Demographics", "Begin");
            log.debug("Begin date isUnbounded: " +beginDateProperty.isUnbounded());
            if (beginDateProperty != null && !beginDateProperty.isUnbounded())
            {
                log.debug("Begin date: " +beginDateProperty.getCDATime());
                request.setCareRecordStartTimePeriod(beginDateProperty.getCDATime());
            }
            DateProperty endDateProperty = DatePropertyAccessor.getDateProperty("dss", "Patient", "Demographics", "End");
            log.debug("End date isUnbounded: " +endDateProperty.isUnbounded());
            if (endDateProperty != null && !endDateProperty.isUnbounded())
            {
                log.debug("End date: " +endDateProperty.getCDATime());
                request.setCareRecordEndTimePeriod(endDateProperty.getCDATime());
            }

            CommonDataLayerFactService service = new CommonDataLayerFactService();
            AdapterFactService port = service.getCommonDataLayerFactPort();
            String endpoint = PropertyAccessor.getProperty("dss", "ADAPTER_FACT_SERVICE");
            ((BindingProvider)port).getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, endpoint);
            FactQueryResponseType resp = port.getDemographicsFact(request);
            res = resp.getProblemFactOrMedicationFactOrAllergyFact();
        }
        catch (Exception ex)
        {
            log.error("Error retrieving historic demographic facts: ", ex);
        }
        return res;
    }

    private void LoadRegistryInfo(String patId, PopulationRegistriesType pops)
    {
        try { // Call Web Service Operation
            long patIdLong = Long.parseLong(patId);
            mil.navy.med.dzreg.service.RegistriesService service = new mil.navy.med.dzreg.service.RegistriesService();
            mil.navy.med.dzreg.service.RegistriesServicePortType port = service.getRegistriesServicePort();
            mil.navy.med.dzreg.types.PersonRegistryProfileRequestType parameters = new mil.navy.med.dzreg.types.PersonRegistryProfileRequestType();
            
            parameters.setId(patIdLong);
            String endpoint = PropertyAccessor.getProperty("dss", "REGISTRY_SERVICE_ENDPOINT");
            if (endpoint != null && endpoint.length()>0)
            {
                ((BindingProvider)port).getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, endpoint);
            }
            mil.navy.med.dzreg.types.PersonRegistryProfileResponseType result = port.getRegistryProfile(parameters);
            if (result != null && result.getProfile() != null && result.getProfile().getRegistry() != null)
            {
                Iterator<RegistryProfileType> itr = result.getProfile().getRegistry().iterator();
                while (itr.hasNext())
                {
                    RegistryProfileType rgpt = itr.next();
                    PopulationType pop = new PopulationType();
                    pop.setText(rgpt.getRegistryDesc());
                    pop.setCode(Integer.toString(rgpt.getRegistryId()));
                    pop.setStatus(rgpt.isActive());

                    pops.getPopulations().add(pop);
                }
            }
            else
            {
                log.debug("No registry information found for patient id: " + patId);
            }
        } catch (Exception ex) {
            log.error("Error retrieving registry info: ", ex);
        }
       
    }
}
