package gov.hhs.fha.nhinc.kmr;

import gov.hhs.fha.nhinc.kmr.util.DateAdapter;
import javax.xml.bind.annotation.XmlRegistry;

/**
 *
 * @author Steven Clark
 */
@XmlRegistry
public class ObjectFactory
{

    /**
     * Create a new ObjectFactory that can be used to create new instances of classes for package: gov.hhs.fha.nhinc.kmr
     *
     */
    public ObjectFactory() {    }

    /**
     * Create an instance of {@link DSSHelper }
     *
     */
    public DSSHelper createDSSHelper()
    {
        return new DSSHelper();
    }

    /**
     * Create an instance of {@link DateAdapter }
     *
     */
    public DateAdapter createDateAdapter()
    {
        return new DateAdapter();
    }

    /**
     * Create an instance of {@link DroolsHelper }
     *
     */
    public DroolsHelper createDroolsHelper()
    {
        return new DroolsHelper();
    }

    /**
     * Create an instance of {@link PatientCohortHelper }
     *
     */
    public PatientCohortHelper createPatientCohortHelper()
    {
        return new PatientCohortHelper();
    }

    /**
     * Create an instance of {@link TaskManagerHelper }
     *
     */
    public TaskManagerHelper createTaskManagerHelper()
    {
        return new TaskManagerHelper();
    }

    /**
     * Create an instance of {@link VirtualMedicalRecord }
     *
     */
    public VirtualMedicalRecord createVirtualMedicalRecord()
    {
        return new VirtualMedicalRecord();
    }

    /**
     * Create an instance of {@link ClinicalReference }
     *
     */
    public ClinicalReference createClinicalReference()
    {
        return new ClinicalReference();
    }

    /**
     * Create an instance of {@link InclusionTable }
     *
     */
    public InclusionTable createInclusionTable()
    {
        return new InclusionTable();
    }

    /**
     * Create an instance of {@link AverageLastAccumulateFunction }
     *
     */
    public AverageLastAccumulateFunction createAverageLastAccumulateFunction()
    {
        return new AverageLastAccumulateFunction();
    }

    /**
     * Create an instance of {@link KMRAgendaEventListener }
     *
     */
    public KMRAgendaEventListener createKMRAgendaEventListener()
    {
        return new KMRAgendaEventListener();
    }

    /**
     * Create an instance of {@link KMRWorkingMemoryEventListener }
     *
     */
    public KMRWorkingMemoryEventListener createKMRWorkingMemoryEventListener()
    {
        return new KMRWorkingMemoryEventListener();
    }
}
