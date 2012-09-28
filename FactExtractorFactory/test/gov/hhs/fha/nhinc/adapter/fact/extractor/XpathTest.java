/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gov.hhs.fha.nhinc.adapter.fact.extractor;

import gov.hhs.fha.nhinc.adapter.fact.xml.HL7NamespaceContext;
import gov.hhs.fha.nhinc.adapter.fact.xml.XMLUtils;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

/**
 *
 * @author kim
 */
public class XpathTest {

   private HL7NamespaceContext hl7Namespace = null;
   private String EXTRACTOR_NAME = "demographics.extractor";
   private FactExtractor extractor = null;
   private XPath xpath;

   public XpathTest() {
   }

   @BeforeClass
   public static void setUpClass() throws Exception {
   }

   @AfterClass
   public static void tearDownClass() throws Exception {
   }

   @Before
   public void setUp() {
      String[] cfiles = new String[]{
         "classpath:resources/xpath-context.xml"
      };

      ApplicationContext context = new ClassPathXmlApplicationContext(cfiles);
      //extractor = (FactExtractor) context.getBean(EXTRACTOR_NAME);
      hl7Namespace = (HL7NamespaceContext) context.getBean("hl7NamespaceContext");

      xpath = XPathFactory.newInstance().newXPath();
      xpath.setNamespaceContext(hl7Namespace);
   }

   @After
   public void tearDown() {
   }

   // TODO add test methods here.
   // The methods must be annotated with annotation @Test. For example:
   //
   @Test
   public void testXpathEval() {
      try {
         Document doc = TestUtilities.getXML("test/data/new/allergies-2-events_1.xml");
         //Document doc = TestUtilities.getXML("test/data/old/allergies-1-event.xml");
         //Document doc = TestUtilities.getXML("test/data/old/demographics.xml");
         //System.out.println(XMLUtils.documentToString(result));
         XMLUtils.printDOM(doc, System.out);

         testAllergiesXPath(doc);
         //demographicsXPath(doc);
      } catch (XPathExpressionException ex) {
         ex.printStackTrace();
      }
   }

   private void demographicsXPath(Document doc) throws XPathExpressionException {
      // expression to evaluate
      //String expression = "count(//hl7:subject)";
      String expression = "count(//hl7:subject/hl7:patientPerson/hl7:name)";
      System.out.println(expression);

      XPathExpression expr = xpath.compile(expression);

      // get # of name nodes
      String val = expr.evaluate(doc);
      System.out.println(val);
      int names = Integer.parseInt(val);

      String namesXpath =
            //"//hl7:subject/hl7:patientPerson/hl7:name[xxx]/hl7:family/text()";
            //"//hl7:subject/hl7:patientPerson/hl7:name[xxx]/hl7:given[1]/text()";
            //"//hl7:subject/hl7:patientPerson/hl7:name[xxx]/hl7:given[2]/text()";
            "//hl7:subject/hl7:patientPerson/hl7:name[xxx]/@use";
      
      for (int i = 1; i <= names; i++) {
         // expression to evaluate
         expression = namesXpath;
         expression = expression.replace("xxx", String.valueOf(i));
         System.out.println(expression);
         expr = xpath.compile(expression);
         val = expr.evaluate(doc);
         System.out.println(val);
      }
   }

   private void testAllergiesXPath(Document doc) throws XPathExpressionException {

      // expression to evaluate
      //String expression = "count(//hl7:subject)";
      String expression = "count(//hl7:pertinentInformation3)";
      System.out.println(expression);

      XPathExpression expr = xpath.compile(expression);

      // evaluate
      String val = expr.evaluate(doc);
      System.out.println(val);

      int events = Integer.parseInt(val);
      String allergyExpression =
              //"/hl7:CareRecord_QUPC_IN043200UV01Response//hl7:response/hl7:controlActProcess/hl7:subject[xxx]";
              //"/hl7:CareRecord_QUPC_IN043200UV01Response//hl7:careRecord/hl7:registrationEvent/hl7:subject2/hl7:careProvisionEvent/hl7:pertinentInformation3[xxx]";
              "/hl7:CareRecord_QUPC_IN043200UV01Message/hl7:response/hl7:controlActProcess/hl7:subject/hl7:registrationEvent/hl7:subject2/hl7:careProvisionEvent/hl7:pertinentInformation3[xxx]";
      for (int i = 1; i <= events; i++) {
         // expression to evaluate
         expression = allergyExpression;
         expression = expression.replace("xxx", String.valueOf(i));
         System.out.println(expression);
         expr = xpath.compile(expression);

         // evaluate
         Node allergyNode = (Node) xpath.evaluate(expression, doc, XPathConstants.NODE);
         System.out.println("----------------------------------------------------  Allergy Event #" + i + "  ----------------");
         XMLUtils.printNode(allergyNode, System.out);

         evalulateAllergyNode(allergyNode, i);
         System.out.println("------------------------------------------------------------------------------------------------");
      }
   }

   private void evalulateAllergyNode(Object node, int nodeIndex) {
      try {
         // get adverse event date
         System.out.println("ADVERSE EVENT DATE:");
         String expression =
                 //"hl7:registrationEvent/hl7:subject2/hl7:careProvisionEvent/hl7:pertinentInformation3/hl7:observation[@classCode='OBS']/hl7:effectiveTime/@value";
                 "hl7:observation[@classCode='OBS']/hl7:effectiveTime/@value";
         System.out.println("  ==> " + expression);
         XPathExpression expr = xpath.compile(expression);
         String val = expr.evaluate(node);
         System.out.println(val);

//         System.out.println("ADVERSE EVENT TYPE:");
//         expression =
//                 //"hl7:registrationEvent/hl7:subject2/hl7:careProvisionEvent/hl7:pertinentInformation3/hl7:observation[@classCode='OBS']/hl7:code/@code";
//                 "hl7:observation[@classCode='OBS']/hl7:code/@code";
//         System.out.println("  ==> " + expression);
//         expr = xpath.compile(expression);
//         val = expr.evaluate(node);
//         System.out.println(val);

//         expression =
//                 //"hl7:registrationEvent/hl7:subject2/hl7:careProvisionEvent/hl7:pertinentInformation3/hl7:observation[@classCode='OBS']/hl7:code/@displayName";
//                 "hl7:observation[@classCode='OBS']/hl7:code/@displayName";
//         System.out.println("  ==> " + expression);
//         expr = xpath.compile(expression);
//         val = expr.evaluate(node);
//         System.out.println("  ==> " + val);

         System.out.println("CODED PRODUCT:");
//         expression =
//                 //"hl7:registrationEvent/hl7:subject2/hl7:careProvisionEvent/hl7:pertinentInformation3/hl7:observation/hl7:sourceOf/hl7:substanceAdministration/hl7:consumable/hl7:administerableMaterial/hl7:administerableMaterial/hl7:code/@code";
//                 "hl7:observation/hl7:sourceOf/hl7:substanceAdministration/hl7:consumable/hl7:administerableMaterial/hl7:administerableMaterial/hl7:code/@code";
//         System.out.println("  ==> " + expression);
//         expr = xpath.compile(expression);
//         val = expr.evaluate(node);
//         System.out.println("  ==> " + val);

         expression =
                 //"hl7:registrationEvent/hl7:subject2/hl7:careProvisionEvent/hl7:pertinentInformation3/hl7:observation/hl7:sourceOf/hl7:substanceAdministration/hl7:consumable/hl7:administerableMaterial/hl7:administerableMaterial/hl7:code/@displayName";
                 "hl7:observation/hl7:sourceOf/hl7:substanceAdministration/hl7:consumable/hl7:administerableMaterial/hl7:administerableMaterial/hl7:code/@displayName";
         System.out.println("  ==> " + expression);
         expr = xpath.compile(expression);
         val = expr.evaluate(node);
         System.out.println("  ==> " + val);

         System.out.println("REACTIONS COUNT:");
         String reactionsExpression = "count(hl7:observation/hl7:sourceOf[@typeCode='MFST'])";
         //reactionsExpression = reactionsExpression.replace("xxx", String.valueOf(nodeIndex + 1));
         System.out.println("  ==> " + reactionsExpression);
         expr = xpath.compile(reactionsExpression);
         val = expr.evaluate(node);
         int reactions = Integer.parseInt(val);
         System.out.println("  ==> " + reactions);

         String reactionXpath =
                 //"/hl7:CareRecord_QUPC_IN043200UV01Response//hl7:response/hl7:controlActProcess/hl7:subject[xxx]";
                 //"/hl7:CareRecord_QUPC_IN043200UV01Response//hl7:careRecord/hl7:registrationEvent/hl7:subject2/hl7:careProvisionEvent/hl7:pertinentInformation3[xxx]";
                 "/hl7:CareRecord_QUPC_IN043200UV01Message/hl7:response/hl7:controlActProcess/hl7:subject/hl7:registrationEvent/hl7:subject2/hl7:careProvisionEvent/hl7:pertinentInformation3[xxx]/" +
                 "hl7:observation/hl7:sourceOf[@typeCode='MFST'][yyy]";

         for (int i = 1; i <= reactions; i++) {
            String nodeReactionXpath = reactionXpath.replace("xxx", String.valueOf(nodeIndex));
            nodeReactionXpath = nodeReactionXpath.replace("yyy", String.valueOf(i));
            Node reactionNode = (Node) xpath.evaluate(nodeReactionXpath, node, XPathConstants.NODE);
            System.out.println("----------------  Reaction #" + i + "  ----------------");
            System.out.println("  ==> " + nodeReactionXpath);
            XMLUtils.printNode(reactionNode, System.out);

            expression = "hl7:observation/hl7:value/@displayName";
            //expression = expression.replace("xxx", String.valueOf(i));
            System.out.println("  ==> reaction " + i + ": " + expression);
            expr = xpath.compile(expression);
            val = expr.evaluate(reactionNode);
            System.out.println("  ==> reaction " + i + ": " + val);


//            String severitiesExpression =
//               "count(hl7:observation/hl7:sourceOf[@typeCode='MFST']/hl7:observation[xxx]/hl7:sourceOf[@typeCode='SUBJ']/hl7:observation)";
//            severitiesExpression = severitiesExpression.replace("yyy", String.valueOf(nodeIndex));
//            severitiesExpression = severitiesExpression.replace("xxx", String.valueOf(i));
//            System.out.println("  ==> reaction " + i + " severity count: " + severitiesExpression);
//            expr = xpath.compile(severitiesExpression);
//            val = expr.evaluate(node);
//            System.out.println("  ==> reaction " + i + " severity count: " + val);
         }
      } catch (XPathExpressionException ex) {
         ex.printStackTrace();
      }
   }
}