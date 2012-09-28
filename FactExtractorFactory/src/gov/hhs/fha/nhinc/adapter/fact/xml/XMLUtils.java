/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gov.hhs.fha.nhinc.adapter.fact.xml;

import com.sun.xml.bind.marshaller.NamespacePrefixMapper;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.StringWriter;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.apache.xml.serialize.OutputFormat;
import org.apache.xml.serialize.XMLSerializer;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

/**
 *
 * @author kim
 */
public class XMLUtils {

   public static Document marshalToDocument(String factoryClass, javax.xml.bind.JAXBElement o) throws Exception {
      Document doc = null;
      if (o == null) {
         return null;
      }

      JAXBContext jc = JAXBContext.newInstance(factoryClass);
      Marshaller marshaller = jc.createMarshaller();
      //In the JAXB RI, this is in com.sun.xml.bind.marshaller,
      //but in Java6, it's in com.sun.xml.internal.bind.marshaller.
      NamespacePrefixMapper prefixMapper = new HL7NamespacePrefixMapper();
      marshaller.setProperty("com.sun.xml.bind.namespacePrefixMapper", prefixMapper);

      DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
      //dbf.setNamespaceAware(true);
      DocumentBuilder db = dbf.newDocumentBuilder();
      doc = db.newDocument();
      marshaller.marshal(o, doc);

      return doc;
   }

   public static void serialize(Document doc, OutputStream out) throws Exception {
      TransformerFactory tfactory = TransformerFactory.newInstance();
      Transformer serializer;
      try {
         serializer = tfactory.newTransformer();
         //Setup indenting to "pretty print"
         serializer.setOutputProperty(OutputKeys.INDENT, "yes");
         serializer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
         serializer.transform(new DOMSource(doc), new StreamResult(out));
      } catch (TransformerException e) {
         e.printStackTrace();
         throw new RuntimeException(e);
      }
   }

   public static String documentToString(Document doc) throws Exception {
      try {
         StringWriter output = new StringWriter();
         OutputFormat format = new OutputFormat(doc);
         format.setIndenting(true);
         format.setIndent(2);
         XMLSerializer serializer = new XMLSerializer(output, format);
         serializer.asDOMSerializer();
         serializer.serialize(doc.getDocumentElement());
         return output.toString();
      } catch (Exception e) {
         e.printStackTrace();
         throw new RuntimeException(e);
      }
   }

   public static void printDOM(Document doc, PrintStream out) {
      try {
         Transformer transformer = TransformerFactory.newInstance().newTransformer();
         transformer.setOutputProperty(OutputKeys.INDENT, "yes");
         Source source = new DOMSource(doc);
         StreamResult output = new StreamResult(out);
         transformer.transform(source, output);
      } catch (TransformerException ex) {
         ex.printStackTrace();
      }
   }

   public static void printNode(Node node, PrintStream out) {
      try {
         // Set up the output transformer
         Transformer transformer = TransformerFactory.newInstance().newTransformer();
         transformer.setOutputProperty(OutputKeys.INDENT, "yes");
         
         // Print the DOM node
         Source source = new DOMSource(node);
         StreamResult output = new StreamResult(out);
         transformer.transform(source, output);
      } catch (TransformerException e) {
         e.printStackTrace();
      }
   }
   
//   private static void listElements(List es, String indent) {
//      for (Iterator i = es.iterator(); i.hasNext();) {
//         Element e = (Element) i.next();
//         listElement(e, indent);
//      }
//   }

//   private static void listElement(Element e, String indent) {
//      System.out.println(indent + "*Element, name:" +
//              e.getName() + ", text:" +
//              e.getText().trim());
//
//      //List all attributes
//      List as = e.getAttributes();
//      listAttributes(as, indent + " ");
//
//      //List all children
//      List c = e.getChildren();
//      listElements(c, indent + " ");
//   }
//
//   private static void listAttributes(List as, String indent) {
//      for (Iterator i = as.iterator(); i.hasNext();) {
//         Attribute a = (Attribute) i.next();
//         System.out.println(indent + "*Attribute, name:" +
//                 a.getName() + ", value:" +
//                 a.getValue());
//      }
//   }
}
