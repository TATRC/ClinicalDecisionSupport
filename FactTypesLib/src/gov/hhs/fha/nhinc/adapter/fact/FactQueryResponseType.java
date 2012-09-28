
package gov.hhs.fha.nhinc.adapter.fact;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for FactQueryResponseType complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="FactQueryResponseType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;choice maxOccurs="unbounded" minOccurs="0">
 *           &lt;element name="problemFact" type="{urn:gov:hhs:fha:nhinc:adapter:fact}ProblemFactType"/>
 *           &lt;element name="medicationFact" type="{urn:gov:hhs:fha:nhinc:adapter:fact}MedicationFactType"/>
 *           &lt;element name="allergyFact" type="{urn:gov:hhs:fha:nhinc:adapter:fact}AllergyFactType"/>
 *           &lt;element name="testResultFact" type="{urn:gov:hhs:fha:nhinc:adapter:fact}ResultFactType"/>
 *           &lt;element name="personFact" type="{urn:gov:hhs:fha:nhinc:adapter:fact}PersonFactType"/>
 *           &lt;element name="supportContactFact" type="{urn:gov:hhs:fha:nhinc:adapter:fact}SupportSourceFactType"/>
 *           &lt;element name="immunizationFact" type="{urn:gov:hhs:fha:nhinc:adapter:fact}ImmunizationFactType"/>
 *           &lt;element name="vitalFact" type="{urn:gov:hhs:fha:nhinc:adapter:fact}VitalFactType"/>
 *           &lt;element name="procedureFact" type="{urn:gov:hhs:fha:nhinc:adapter:fact}ProcedureFactType"/>
 *           &lt;element name="labOrderFact" type="{urn:gov:hhs:fha:nhinc:adapter:fact}LabOrderFactType"/>
 *           &lt;element name="encounterFact" type="{urn:gov:hhs:fha:nhinc:adapter:fact}EncounterFactType"/>
 *           &lt;element name="diagnosisFact" type="{urn:gov:hhs:fha:nhinc:adapter:fact}DiagnosisFactType"/>
 *           &lt;element name="appointmentFact" type="{urn:gov:hhs:fha:nhinc:adapter:fact}AppointmentFactType"/>
 *           &lt;element name="providerFact" type="{urn:gov:hhs:fha:nhinc:adapter:fact}AppointmentFactType"/>
 *           &lt;element name="insuranceFact" type="{urn:gov:hhs:fha:nhinc:adapter:fact}InsuranceFactType"/>
 *           &lt;element name="admissionFact" type="{urn:gov:hhs:fha:nhinc:adapter:fact}AdmissionFactType"/>
 *           &lt;element name="registryFact" type="{urn:gov:hhs:fha:nhinc:adapter:fact}RegistryProfileFactType"/>
 *           &lt;element name="medOrderFact" type="{urn:gov:hhs:fha:nhinc:adapter:fact}MedOrderFactType"/>
 *           &lt;element name="imagingResultFact" type="{urn:gov:hhs:fha:nhinc:adapter:fact}ImagingResultFactType"/>
 *           &lt;element name="slotFactType" type="{urn:gov:hhs:fha:nhinc:adapter:fact}SlotFactType"/>
 *         &lt;/choice>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "FactQueryResponseType", propOrder = {
    "facts"
})
public class FactQueryResponseType implements Serializable {

    @XmlElements({
        @XmlElement(name = "problemFact", type = ProblemFactType.class, nillable = true),
        @XmlElement(name = "medicationFact", type = MedicationFactType.class, nillable = true),
        @XmlElement(name = "allergyFact", type = AllergyFactType.class, nillable = true),
        @XmlElement(name = "testResultFact", type = ResultFactType.class, nillable = true),
        @XmlElement(name = "personFact", type = PersonFactType.class, nillable = true),
        @XmlElement(name = "supportContactFact", type = SupportContactFactType.class, nillable = true),
        @XmlElement(name = "immunizationFact", type = ImmunizationFactType.class, nillable = true),
        @XmlElement(name = "vitalFact", type = VitalFactType.class, nillable = true),
        @XmlElement(name = "procedureFact", type = ProcedureFactType.class, nillable = true),
        @XmlElement(name = "labOrderFact", type = LabOrderFactType.class, nillable = true),
        @XmlElement(name = "encounterFact", type = EncounterFactType.class, nillable = true),
        @XmlElement(name = "diagnosisFact", type = DiagnosisFactType.class, nillable = true),
        @XmlElement(name = "appointmentFact", type = AppointmentFactType.class, nillable = true),
        @XmlElement(name = "providerFact", type = HealthcareProviderFactType.class, nillable = true),
        @XmlElement(name = "insuranceFact", type = InsuranceFactType.class, nillable = true),
        @XmlElement(name = "admissionFact", type = AdmissionFactType.class, nillable = true),
        @XmlElement(name = "registryFact", type = RegistryProfileFactType.class, nillable = true),
        @XmlElement(name = "medOrderFact", type = MedOrderFactType.class, nillable = true),
        @XmlElement(name = "imagingResultFact", type = ImagingResultFactType.class, nillable = true),
        @XmlElement(name = "slotFactType", type = SlotFactType.class, nillable = true)
    })
    protected List<FactType> facts;

    /**
     * Gets the value of the personFactOrAllergyFactOrProblemFact property.
     *
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the facts property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getFacts().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ProblemFactType }
     * {@link MedicationFactType }
     * {@link AllergyFactType }
     * {@link ResultFactType }
     * {@link PersonFactType }
     * {@link SupportContactFactType }
     * {@link ImmunizationFactType }
     * {@link VitalFactType }
     * {@link ProcedureFactType }
     * {@link LabOrderFactType }
     * {@link EncounterFactType }
     * {@link DiagnosisFactType }
     * {@link AppointmentFactType }
     * {@link HealthcareProviderFactType }
     * {@link InsuranceFactType }
     * {@link AdmissionFactType }
     * {@link RegistryProfileFactType }
     * {@link MedOrderFactType }
     * {@link ImagingResultFactType }
     * {@link SlotFactType }
     *
     */
    public List<FactType> getFacts() {
        if (facts == null) {
            facts = new ArrayList<FactType>();
        }
        return this.facts;
    }

}