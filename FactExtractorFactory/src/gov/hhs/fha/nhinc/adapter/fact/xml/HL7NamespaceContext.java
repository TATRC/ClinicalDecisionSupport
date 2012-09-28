/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gov.hhs.fha.nhinc.adapter.fact.xml;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import javax.xml.XMLConstants;
import javax.xml.namespace.NamespaceContext;

/**
 *
 * @author kim
 */
public class HL7NamespaceContext implements NamespaceContext {

   private Map prefixes = new HashMap();
   private Map namespaces = new HashMap();

   public HL7NamespaceContext(HashMap urisByPrefixes) {
      if (urisByPrefixes != null && urisByPrefixes.size() > 0) {
         Set<Entry> entries = urisByPrefixes.entrySet();
         for (Entry prefixuri : entries) {
            addNamespace((String) prefixuri.getKey(), (String) prefixuri.getValue());
         }
         //addNamespace("hl7", "urn:hl7-org:v3");
         //addNamespace("ns2", "urn:gov:hhs:fha:nhinc:common:nhinccommon");
      }

   }

   /**
    * Prefix                                 Namespace
    * XMLConstants.XML_NS_PREFIX ("xml")     XMLConstants.XML_NS_URI  ("http://www.w3.org/XML/1998/namespace")
    * XMLConstants.XMLNS_ATTRIBUTE ("xmlns") XMLConstants.XMLNS_ATTRIBUTE_NS_URI  ("http://www.w3.org/2000/xmlns/")
    * DEFAULT_NS_PREFIX ("")                 default Namespace URI in the current scope or XMLConstants.NULL_NS_URI("") 
    *                                        when there is no default Namespace URI in the current scope
    * bound prefix                           Namespace URI bound to prefix in current scope
    * unbound prefix                         XMLConstants.NULL_NS_URI("")
    * @param prefix
    * @return
    */
   @Override
   public String getNamespaceURI(String prefix) {
      if (prefix == null) {
         throw new NullPointerException("Null prefix");
      //} else if (prefix.equals(XMLConstants.XMLNS_ATTRIBUTE)) {
      //   return namespaces.get(prefix);
      } else if (prefix.equals(XMLConstants.XML_NS_PREFIX)) {
         return XMLConstants.XML_NS_URI;
      } else if (prefixes.containsKey(prefix)) {
         return (String) prefixes.get(prefix);
      }

      return XMLConstants.NULL_NS_URI;
   }

   /**
    * Get prefix bound to Namespace URI in the current scope.
    * Namespace                                    Prefix
    * XMLConstants.XML_NS_URI                      XMLConstants.XML_NS_PREFIX ("xml")
    *    ("http://www.w3.org/XML/1998/namespace")
    * XMLConstants.XMLNS_ATTRIBUTE_NS_URI          prefix bound to XMLConstants.XMLNS_ATTRIBUTE ("xmlns")
    *    ("http://www.w3.org/2000/xmlns/")
    * Bound Namespace URI                          prefix bound to Namespace URI in the current scope
    * Unbound Namespace URI                        null
    * @param uri
    * @return
    */
   @Override
   public String getPrefix(String namespaceURI) {
      if (namespaceURI == null) {
         throw new NullPointerException("Null namespace");
      //} else if (prefix.equals(XMLConstants.XMLNS_ATTRIBUTE)) {
      //   return namespaces.get(prefix);
      } else if (namespaceURI.equals(XMLConstants.XML_NS_URI)) {
         return XMLConstants.XML_NS_PREFIX;
      } else if (prefixes.containsKey(namespaceURI)) {
         return (String) getPrefixes(namespaceURI).next();
      }

      return XMLConstants.DEFAULT_NS_PREFIX;
   }

   // This method isn't necessary for XPath processing either.
   @Override
   public Iterator getPrefixes(String namespaceURI) {
      if (namespaceURI == null) {
         throw new IllegalArgumentException("namespaceURI cannot be null");
      }
      if (namespaces.containsKey(namespaceURI)) {
         return ((Set) namespaces.get(namespaceURI)).iterator();
      } else {
         return Collections.EMPTY_SET.iterator();
      }
   }

   public synchronized void addNamespace(String prefix, String namespaceURI) {
      prefixes.put(prefix, namespaceURI);
      if (namespaces.containsKey(namespaceURI)) {
         ((Set) namespaces.get(namespaceURI)).add(prefix);
      } else {
         Set set = new HashSet();
         set.add(prefix);
         namespaces.put(namespaceURI, set);
      }
   }
}
