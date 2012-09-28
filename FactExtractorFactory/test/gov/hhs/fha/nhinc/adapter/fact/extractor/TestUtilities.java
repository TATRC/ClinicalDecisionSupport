/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gov.hhs.fha.nhinc.adapter.fact.extractor;

import gov.hhs.fha.nhinc.adapter.fact.xml.HL7NamespaceContext;
import java.io.StringReader;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

/**
 *
 * @author kim
 */
public class TestUtilities {

   public static Document getXML(String filename) {
      try {
         DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();
         domFactory.setNamespaceAware(true);
         DocumentBuilder builder = domFactory.newDocumentBuilder();
         Document doc = builder.parse(filename);
         return doc;
      } catch (Exception ex) {
         ex.printStackTrace();
         return null;
      }
   }

  /**
   * Retrieves a DOM source for the provided xml string.
   * @param xml string to parse.
   * @return  DOM source for the supplied xml.
   */
  private DOMSource getDOMSource(String xml) {
    DOMSource domsrc = null;
    try {
      StringReader stringReader = new StringReader(xml);
      InputSource  inputSource  = new InputSource(stringReader);

      DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();
      DocumentBuilder builder = domFactory.newDocumentBuilder();
      Document doc = builder.parse(inputSource);

      domsrc = new DOMSource(doc);
    }
    catch (Exception e) {
    }
   return domsrc;
  }


//   public static void xpathValue(Document doc, HL7NamespaceContext hl7Namespace) {
//      try {
//         XPath xpath = XPathFactory.newInstance().newXPath();
//         xpath.setNamespaceContext(hl7Namespace);
//         //hl7Namespace.addNamespace("xmlns", "urn:hl7-org:v3");
//         //hl7Namespace.addNamespace("xmlns:ns2", "urn:gov:hhs:fha:nhinc:common:nhinccommon");
//
//         //String expression = "/projects/project[1]/@id";
//         //String expression = "/PatientDemographics_PRPA_MT201303UVResponse/subject/@classCode";
//         //String expression = "/PatientDemographics_PRPA_MT201303UVResponse/subject/id/@extension";
//         //String expression = "//hl7:subject/hl7:patientPerson/hl7:name/hl7:family/text()";
//         //String expression = "//hl7:subject[@classCode='PAT']/hl7:patientPerson/hl7:name/hl7:family/text()";
//         //String expression = "//hl7:subject[@classCode='PAT']/hl7:patientPerson/hl7:name/hl7:given/text()";
//         //String expression = "//hl7:subject[@classCode='PAT']/hl7:patientPerson/hl7:birthTime/@value";
//         //String expression = "//hl7:subject[@classCode='PAT']/hl7:id/@extension";
//
//         // example for retrieving from a list of "id" element
//         //String expression = "//hl7:subject[@classCode='PAT']/hl7:id[2]/@extension";
//
//         //Expression  "//*[name()='BBB']" will select all elements with name BBB, equivalent with //BBB,
//         //            "//*[starts-with(name(),'B')]" will select all elements name of which starts with letter B
//         //            "//*[contains(name(),'C')]" will select all elements name of which contain letter C.
//
//         //*[count(BBB)=2]"
//
//         // retrieving the "extension" attribute of the last "id" element
//         //String expression = "//hl7:subject[@classCode='PAT']/hl7:id[last()]/@extension";
//
//         // count the number of occurrences for element "id"
//         //String expression = "count(//hl7:subject[@classCode='PAT']/hl7:id)";
//
//         //String expression = "//hl7:subject[@classCode='PAT']/hl7:id[2]/@extension";
//         //String expression = "//hl7:pertinentInformation3/hl7:observation[@classCode='OBS']/hl7:code/@code";
//         //String expression = "//hl7:pertinentInformation3/hl7:observation[@classCode='OBS']/hl7:effectiveTime/@value";
//         //String expression = "//hl7:consumable/hl7:administerableMaterial/hl7:administerableMaterial/hl7:code/@code";
//         //String expression = "count(//hl7:sourceOf[@typeCode='MFST']/hl7:observation)";
//         String expression = "count(//hl7:sourceOf[@typeCode='MFST'])";
//
//         System.out.println(expression);
//
//         XPathExpression expr = xpath.compile(expression);
//         String val = expr.evaluate(doc);
//         System.out.println(val);
//      } catch (XPathExpressionException ex) {
//         ex.printStackTrace();
//      }
//   }

   public static void xpathValue(Object doc, HL7NamespaceContext hl7Namespace) {
      try {
         XPath xpath = XPathFactory.newInstance().newXPath();
         xpath.setNamespaceContext(hl7Namespace);

         // retrieving the "extension" attribute of the last "id" element
         //String expression = "//hl7:subject[@classCode='PAT']/hl7:id[last()]/@extension";

         // count the number of occurrences for element "id"
         //String expression = "count(//hl7:subject[@classCode='PAT']/hl7:id)";

         //String expression = "//hl7:subject[@classCode='PAT']/hl7:id[2]/@extension";
         //String expression = "count(//hl7:sourceOf[@typeCode='MFST'])";
         //String expression = "//hl7:pertinentInformation3/hl7:observation[@classCode='OBS']/hl7:effectiveTime/@value";
         //String expression = "//hl7:consumable/hl7:administerableMaterial/hl7:administerableMaterial/hl7:code/@code";
         //String expression = "count(//hl7:sourceOf[@typeCode='MFST']/hl7:observation)";
         String expression = "/hl7:pertinentInformation3/hl7:sequenceNumber/@value";

         System.out.println(expression);

         XPathExpression expr = xpath.compile(expression);
         String val = expr.evaluate(doc);
         System.out.println(val);
      } catch (XPathExpressionException ex) {
         ex.printStackTrace();
      }
   }

   public static NodeList getNodes(Document doc, String expression, HL7NamespaceContext hl7Namespace) {
      Object result = null;

      try {
         XPath xpath = XPathFactory.newInstance().newXPath();
         xpath.setNamespaceContext(hl7Namespace);
         
         System.out.println("XPATH expression=" + expression);
         //XPathExpression expr = xpath.compile("//book[author='Neal Stephenson']/title/text()");
         XPathExpression expr = xpath.compile(expression);
         result = expr.evaluate(doc, XPathConstants.NODESET);
         //NodeList nodes = (NodeList) result;
         //for (int i = 0; i < nodes.getLength(); i++) {
         //   System.out.println(nodes.item(i).getNodeValue());
         //}
      } catch (XPathExpressionException ex) {
         ex.printStackTrace();
      }

      return (NodeList) result;
   }
}
