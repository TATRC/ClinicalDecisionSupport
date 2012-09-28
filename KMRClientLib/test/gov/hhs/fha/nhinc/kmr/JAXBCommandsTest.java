package gov.hhs.fha.nhinc.kmr;

import gov.hhs.fha.nhinc.adapter.fact.AddressFactType;
import gov.hhs.fha.nhinc.adapter.fact.CodeLabelPair;
import gov.hhs.fha.nhinc.adapter.fact.NameFactType;
import gov.hhs.fha.nhinc.adapter.fact.PersonFactType;
import gov.hhs.fha.nhinc.adapter.fact.ValueType;
import gov.hhs.fha.nhinc.adapter.fact.ValueUnitPair;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.GregorianCalendar;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

import junit.framework.TestCase;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.drools.command.runtime.BatchExecutionCommandImpl;
import org.drools.command.runtime.rule.FireAllRulesCommand;
import org.drools.command.runtime.rule.InsertObjectCommand;
import org.drools.xml.jaxb.util.DroolsJaxbContextHelper;

public class JAXBCommandsTest extends TestCase {

	private JAXBContext jaxbContext;

	@Override
	protected void setUp() throws Exception {
	}

	public void test() throws Exception {
		PersonFactType personFactType = new PersonFactType();
		personFactType.setPrimaryKey("30354");
		personFactType.setHistorical(true);
		ArrayList<ValueType> ids = new ArrayList<ValueType>();
		ValueType type1 = new ValueType();
		type1.setValue("30354");
                type1.setDisplayable(true);
		ValueType type2 = new ValueType();
                type2.setValue("509326954");
                type2.setDisplayable(false);
		personFactType.getId().add(type1);
		personFactType.getId().add(type2);
		NameFactType nameFactType = new NameFactType();
		nameFactType.setFirstName("BABY GIRL");
		nameFactType.setFamilyName("SMITH");
		personFactType.setLegalName(nameFactType);
		personFactType.setDateOfBirth(new GregorianCalendar(1991, 05, 14).getTime());
		ArrayList<CodeLabelPair> languages = new ArrayList<CodeLabelPair>();
		CodeLabelPair codeLabelPair = new CodeLabelPair();
		codeLabelPair.setCode("en-US");
		personFactType.getLanguage().add(codeLabelPair);
		ValueUnitPair age = new ValueUnitPair();
                age.setValue("19");
                age.setUnit("years");
		personFactType.setAge(age);
		ArrayList<AddressFactType> address = new ArrayList<AddressFactType>();
		AddressFactType addressType = new AddressFactType();
		addressType.setStreetAddress("129 TURQOUSE DRIVE");
		addressType.setCity("SANTO DOMINGO");
		addressType.setState("NEW MEXICO");
		addressType.setPostalCode("87052");
		addressType.setCountry("US");
		CodeLabelPair value = new CodeLabelPair();
		value.setCode("HP");
		addressType.setAddressType(value);
		personFactType.getAddress().add(addressType);

                // Test the Drools helper methods
//                DroolsHelper helper= new DroolsHelper();
//		String xml = helper.createRules(personFactType);
		String xml = createRules(personFactType);
		System.out.println(xml);

//                String result = helper.executeRules(xml, "nhinint01.asu.edu/drools-server/services/soap");
//		System.out.println(result);

                // Test the REST interface
		HttpClient httpClient = new HttpClient();
		httpClient.getHostConfiguration().setHost("nhinint01.asu.edu", 80);

		PostMethod postMethod = new PostMethod("/drools-server/services/rest/execute");
		postMethod.addParameter("command", xml);

		httpClient.executeMethod(postMethod);
		assertEquals(200, postMethod.getStatusCode());

		System.out.println(postMethod.getResponseBodyAsString());
	}
	
	public String createRules(PersonFactType fact) {
        String res = null;
        try {
            BatchExecutionCommandImpl cmd = new BatchExecutionCommandImpl();
            cmd.setLookup("ksession1");
            cmd.getCommands().add(new InsertObjectCommand(fact, "person"));
            cmd.getCommands().add(new FireAllRulesCommand());
            
            StringWriter xmlReq = new StringWriter();
            List<String> myDomainClasses = new ArrayList<String>();
            myDomainClasses.add("gov.hhs.fha.nhinc.adapter.fact.FactType");
            myDomainClasses.add("gov.hhs.fha.nhinc.adapter.fact.AllergyFactType");
            myDomainClasses.add("gov.hhs.fha.nhinc.adapter.fact.MedicationFactType");
            myDomainClasses.add("gov.hhs.fha.nhinc.adapter.fact.PersonFactType");
            myDomainClasses.add("gov.hhs.fha.nhinc.adapter.fact.ResultFactType");
            myDomainClasses.add("gov.hhs.fha.nhinc.adapter.fact.ProblemFactType");
            myDomainClasses.add("gov.hhs.fha.nhinc.adapter.fact.PreConditionFactType");
            jaxbContext = DroolsJaxbContextHelper.createDroolsJaxbContext(myDomainClasses,null);
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(cmd, xmlReq);
            res = xmlReq.toString();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return res;
    }

}
