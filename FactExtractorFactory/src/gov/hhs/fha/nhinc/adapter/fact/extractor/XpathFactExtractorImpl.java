/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gov.hhs.fha.nhinc.adapter.fact.extractor;

import gov.hhs.fha.nhinc.adapter.fact.CodeLabelPair;
import gov.hhs.fha.nhinc.adapter.fact.ValueType;
import gov.hhs.fha.nhinc.adapter.fact.ValueUnitPair;
import gov.hhs.fha.nhinc.adapter.fact.def.CLPPropertyDefinition;
import gov.hhs.fha.nhinc.adapter.fact.def.CLPListPropertyDefinition;
import gov.hhs.fha.nhinc.adapter.fact.def.SimplePropertyDefinition;
import gov.hhs.fha.nhinc.adapter.fact.def.SimpleListPropertyDefinition;
import gov.hhs.fha.nhinc.adapter.fact.def.ComplexPropertyDefinition;
import gov.hhs.fha.nhinc.adapter.fact.def.ComplexListPropertyDefinition;
import gov.hhs.fha.nhinc.adapter.fact.def.PropertyDefinition;
import gov.hhs.fha.nhinc.adapter.fact.def.ValueTypeListPropertyDefinition;
import gov.hhs.fha.nhinc.adapter.fact.def.ValueTypePropertyDefinition;
import gov.hhs.fha.nhinc.adapter.fact.def.VUPListPropertyDefinition;
import gov.hhs.fha.nhinc.adapter.fact.def.VUPPropertyDefinition;
import gov.hhs.fha.nhinc.adapter.fact.utils.DateUtil;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.w3c.dom.NodeList;

/**
 *
 * @author kim
 */
public abstract class XpathFactExtractorImpl implements XPathFactExtractor {

  protected static Log log = LogFactory.getLog(XpathFactExtractorImpl.class);
  /**
   * XPath expression to extract fact information.
   */
  protected String factBaseXpath;
  /**
   * The name of the fact class supported by this extractor.
   */
  protected String factClassName;
  /**
   * A list of fact objects.
   */
  protected List<Object> facts = null;
  /**
   * 
   */
  protected javax.xml.xpath.XPath xpath;
  /**
   * A string of chars representing the dynamic indexing of XML nodes.
   */
  protected String factIndexCharset;
  /**
   * A set of properties belonged to the fact object.
   */
  protected Map<String, SimplePropertyDefinition> factProperties;

  public XpathFactExtractorImpl(XPath xpath) {
    this.xpath = xpath;
    if (facts == null) {
      facts = new ArrayList();
    }
  }

  /**
   *
   * @param domObj
   * @throws java.lang.Exception
   */
  @Override
  public abstract void extract(Object domObj) throws Exception;

//  @Override
//  public void extract(Object domObj) throws Exception {
//    Object obj = extract(domObj, 0);
//
//    if (obj != null) {
//      facts.add(obj);
//    }
//  }
  /**
   *
   * @param domObj
   * @param nodeIdx
   * @return
   * @throws java.lang.Exception
   */
  protected Object extract(Object domObj, int factIdx) throws Exception {
    // create a "fact" object
    Class factClass = Class.forName(this.factClassName);
    Object factObj = factClass.newInstance();

    if (factProperties != null && factProperties.size() > 0) {
      Set<Entry<String, SimplePropertyDefinition>> factPropertySet = factProperties.entrySet();

      for (Entry factProperty : factPropertySet) {
        PropertyDefinition propDef = ((PropertyDefinition) factProperty.getValue()).clone();
        log.debug("factIndexCharset=" + factIndexCharset + ",factIdx=" + factIdx);
        if (factIdx > 0) {
          propDef.updateXpathExpression(factIndexCharset, String.valueOf(factIdx));
        }

        factObj = extract(domObj, propDef, factObj);
      }
    }

    return factObj;
  }

  /**
   *
   * @param domObj
   * @param factProperty
   * @param factClass
   * @param nodeIdx
   * @throws java.lang.Exception
   */
  protected Object extract(Object domObj, PropertyDefinition factProperty, Object extractFactObj) throws Exception {

    // populate "fact" object's field(s)
    String propName = null;
    PropertyDefinition propDef = factProperty;
    Object propValue = null;
    List propValues;

    try {
      //propName = (String) factProperty.getKey();
      //propDef = (PropertyDefinition) factProperty.getValue();
      propName = propDef.getPropertyName();

      //log.debug("propDef ==> " + propDef);

      // evaluate xpath expression of property
      if (propDef.getClass().getName().equals("gov.hhs.fha.nhinc.adapter.fact.def.SimplePropertyDefinition")) {
        SimplePropertyDefinition simplePropDef = (SimplePropertyDefinition) propDef;
        
        // extract and set value for property
        propValue = extractPropertyValue(domObj, simplePropDef);
        if (propValue != null) {
          setProperty(extractFactObj, propName, propValue);
        }

        //log.debug("Fact \"" + extractFactObj.getClass().getName() + "\" ==> property:" + propName + ",value:" + propValue);
      } else if (propDef.getClass().getName().equals("gov.hhs.fha.nhinc.adapter.fact.def.SimpleListPropertyDefinition")) {
        propValues = extractPropertyValues(domObj, (SimpleListPropertyDefinition) propDef);
        setProperty(extractFactObj, propName, propValues);
      } else if (propDef.getClass().getName().equals("gov.hhs.fha.nhinc.adapter.fact.def.CLPPropertyDefinition")) {
        CLPPropertyDefinition clpPropDef = (CLPPropertyDefinition) propDef;

        // extract values for this type of property
        propValue = extractPropertyValue(domObj, clpPropDef);
        if (propValue != null) {
          setProperty(extractFactObj, propName, propValue);
        }
      } else if (propDef.getClass().getName().equals("gov.hhs.fha.nhinc.adapter.fact.def.CLPListPropertyDefinition")) {
        CLPListPropertyDefinition clpListPropDef = (CLPListPropertyDefinition) propDef;

        // extract and set values for property
        propValues = extractPropertyValues(domObj, clpListPropDef);
        setProperty(extractFactObj, propName, propValues);
      } else if (propDef.getClass().getName().equals("gov.hhs.fha.nhinc.adapter.fact.def.VUPPropertyDefinition")) {
        VUPPropertyDefinition vupPropDef = (VUPPropertyDefinition) propDef;

        // extract values for this type of property
        propValue = extractPropertyValue(domObj, vupPropDef);
        if (propValue != null) {
          setProperty(extractFactObj, propName, propValue);
        }
      } else if (propDef.getClass().getName().equals("gov.hhs.fha.nhinc.adapter.fact.def.VUPListPropertyDefinition")) {        
        VUPListPropertyDefinition vupListPropDef = (VUPListPropertyDefinition) propDef;

        // extract and set values for property
        propValues = extractPropertyValues(domObj, vupListPropDef);
        setProperty(extractFactObj, propName, propValues);
      } else if (propDef.getClass().getName().equals("gov.hhs.fha.nhinc.adapter.fact.def.ValueTypeListPropertyDefinition")) {
        ValueTypeListPropertyDefinition vtListPropDef = (ValueTypeListPropertyDefinition) propDef;

        // extract and set values for property
        propValues = extractPropertyValues(domObj, vtListPropDef);
        setProperty(extractFactObj, propName, propValues);
      } else if (propDef.getClass().getName().equals("gov.hhs.fha.nhinc.adapter.fact.def.ValueTypePropertyDefinition")) {
        ValueTypePropertyDefinition vtPropDef = (ValueTypePropertyDefinition) propDef;

        // extract values for this type of property
        propValue = extractPropertyValue(domObj, vtPropDef);
        if (propValue != null) {
          setProperty(extractFactObj, propName, propValue);
        }
      } else if (propDef.getClass().getName().equals("gov.hhs.fha.nhinc.adapter.fact.def.ComplexListPropertyDefinition")) {
        ComplexListPropertyDefinition objPropDef = (ComplexListPropertyDefinition) propDef;
        propValues = extractPropertyValues(domObj, objPropDef);
        setProperty(extractFactObj, propName, propValues);
      } else if (propDef.getClass().getName().equals("gov.hhs.fha.nhinc.adapter.fact.def.ComplexPropertyDefinition")) {
        propValue = extractPropertyValue(domObj, (ComplexPropertyDefinition) propDef, factIndexCharset);
        if (propValue != null) {
          setProperty(extractFactObj, propName, propValue);
        }
      }
    } catch (Exception e) {
      log.error(e);
    }

    return extractFactObj;
  }

  /**
   *
   * @param domObj
   * @param factProperty
   * @param factClass
   * @param nodeIdx
   * @throws java.lang.Exception
   */
  protected Object extract(Object domObj, Entry factProperty, Object extractFactObj, int indexVal) throws Exception {

    // populate "fact" object's field(s)
    String propName = null;
    PropertyDefinition propDef = null;
    Object propValue = null;
    List propValues;

    try {
      //propName = (String) factProperty.getKey();
      propDef = (PropertyDefinition) factProperty.getValue();
      propName = (String) propDef.getPropertyName();

      log.debug("propDef ==> " + propDef);

      if (propDef.getClass().getName().equals("gov.hhs.fha.nhinc.adapter.fact.def.ComplexPropertyDefinition")) {
        if (indexVal <= 0) {
          propValue = extractPropertyValue(domObj, (ComplexPropertyDefinition) propDef);
        } else {
          propValue = extractPropertyValue(domObj, (ComplexPropertyDefinition) propDef, factIndexCharset);
        }

        setProperty(extractFactObj, propName, propValue);
      }
    } catch (Exception e) {
      log.error(e);
    }

    return extractFactObj;
  }
  

  /**
   *
   * @param domObj  a DOM document or node
   * @param propDef a set of property definitions for a complex FACT object.
   * @return  the extracted complex FACT object.
   * @throws java.lang.Exception
   */
  protected Object extractPropertyValue(Object domObj, ComplexPropertyDefinition propDef, String indexCharsSet) throws Exception {
    // construct an object
    Object factObj = null;

    if (propDef != null && domObj != null) {
      try {
        if (propDef.getObjectProperties() != null && propDef.getObjectProperties().size() > 0) {
          Set<Entry<String, SimplePropertyDefinition>> propDefSet = propDef.getObjectProperties().entrySet();

          Class propObjClass = Class.forName(propDef.getPropertyClassName());
          factObj = propObjClass.newInstance();

          for (Entry propDefEntry : propDefSet) {
            //factObj = extract(domObj, propDefEntry, factObj, indexCharsSet, 0);
            factObj = extract(domObj, (PropertyDefinition) propDefEntry.getValue(), factObj);
          }
        }
      } catch (Exception e) {
        log.error(e);
      }
    }

    return factObj;
  }

  /**
   *
   * @param domObj
   * @param propDef
   * @return
   * @throws java.lang.Exception
   */
  protected List extractPropertyValues(Object domObj, ComplexListPropertyDefinition propDef) throws Exception {
    // construct an array of objects
    List<Object> objsList = new ArrayList();

    if (propDef != null && domObj != null) {
      try {
        String strObjListSize = xpathEvaluate(domObj, propDef.getListSizeXpath());
        int objListSize = Integer.parseInt(strObjListSize);
        //log.debug("Property \"" + propDef.getPropertyName() + "\"==> list size:" + objListSize + ",xpath:" + propDef.getListSizeXpath());

        for (int listIdx = 1; listIdx <= objListSize; listIdx++) {
          if (propDef.getObjectProperties() != null && propDef.getObjectProperties().size() > 0) {
            Set<Entry<String, SimplePropertyDefinition>> propDefSet = propDef.getObjectProperties().entrySet();

            Class propObjClass = Class.forName(propDef.getPropertyClassName());
            Object propObj = propObjClass.newInstance();

            for (Entry propDefEntry : propDefSet) {
              PropertyDefinition complexPropDef = ((PropertyDefinition) propDefEntry.getValue()).clone();
              //log.debug("indexRef=" + propDef.getIndexRef() + ",listIdx=" + listIdx);
              
              complexPropDef.updateXpathExpression(propDef.getIndexRef(), String.valueOf(listIdx));
              //propDefEntry.setValue(complexPropDef);

              propObj = extract(domObj, complexPropDef, propObj);
            }

            // add to array of property values
            objsList.add(propObj);
          }
        }
      } catch (Exception e) {
        log.error(e);
      }
    }

    return objsList;
  }

  /**
   *
   * @param doc
   * @param propDef
   * @return  an array of property values
   */
  protected List extractPropertyValues(Object doc, SimpleListPropertyDefinition propDef) {
    // array of property values
    List<Object> objsList = new ArrayList();

    if (propDef != null && doc != null) {
      try {
        String strListSize = xpathEvaluate(doc, propDef.getListSizeXpath());
        int listSize = Integer.parseInt(strListSize);
        //log.debug("List size of property \"" + propDef.getPropertyName() +
        //        "\"==> value:" + listSize + ",expression:" + propDef.getListSizeXpath());

        String propXpathExpression = propDef.getPropertyXpath();
        String propValue = "";
        if (propXpathExpression != null && !propXpathExpression.isEmpty()) {
          for (int i = 1; i <= listSize; i++) {
            propXpathExpression = propXpathExpression.replace("xxx", String.valueOf(i));
            propValue = xpathEvaluate(doc, propXpathExpression);
            //log.debug("Property \"" + propDef.getPropertyName() + "\"==> class:" + propDef.getPropertyClassName() + ",value:" + propValue + ",expression:" + propXpathExpression);
            // convert property value to expected object class type
            Object obj = toObject(propDef.getPropertyClassName(), propValue);
            // add to array of property values
            objsList.add(obj);

            propXpathExpression = propDef.getPropertyXpath();
          }
        }
//        else {
//          log.error("Error: property \"" + propDef.getPropertyName() + "\" has no xpath expression!");
//        }
      } catch (Exception e) {
        log.error(e);
      }
    }

    return objsList;
  }

  protected Object extractPropertyValue(Object doc, SimplePropertyDefinition propDef) {
    if (propDef != null && doc != null) {
      String propValue = "";
      propValue = xpathEvaluate(doc, propDef.getPropertyXpath());
      //log.debug("Property \"" + propDef.getPropertyName() + "\" ==> class:" +
      //        propDef.getPropertyClassName() + ",expression:" + propDef.getPropertyXpath() +
      //        ",value:" + propValue);

      // convert property value to expected object class type
      Object retObj = toObject(propDef.getPropertyClassName(), propValue);

      return retObj;
    }
    else {
      //log.error("Cannot extract property -- PropertyDefinition not available!");
      return null;
    }
  }

  /**
   *
   * @param doc
   * @param propDef
   * @return
   */
  protected List extractPropertyValues(Object doc, CLPListPropertyDefinition propDef) {
    // array of property values
    List<Object> objsList = new ArrayList();

    if (propDef != null && doc != null) {
      try {
        String strListSize = xpathEvaluate(doc, propDef.getListSizeXpath());
        int listSize = Integer.parseInt(strListSize);
        //log.debug("List size of property \"" + propDef.getPropertyName() +
        //        "\"==> value:" + listSize + ",expression:" + propDef.getListSizeXpath());

        String codeXpathExpression = propDef.getCodeXpath();
        String labelXpathExpression = propDef.getLabelXpath();
        String codeSystemXpathExpression = propDef.getCodeSystemXpath();
        String codeSystemNameXpathExpression = propDef.getCodeSystemNameXpath();
        CLPPropertyDefinition cldef = null;

        if (codeXpathExpression != null && !codeXpathExpression.isEmpty()) {
          for (int i = 1; i <= listSize; i++) {
            if (codeXpathExpression != null && !codeXpathExpression.isEmpty()) {
              codeXpathExpression = codeXpathExpression.replace("xxx", String.valueOf(i));
            }
            if (labelXpathExpression != null && !labelXpathExpression.isEmpty()) {
              labelXpathExpression = labelXpathExpression.replace("xxx", String.valueOf(i));
            }
            if (codeSystemXpathExpression != null && !codeSystemXpathExpression.isEmpty()) {
              codeSystemXpathExpression = codeSystemXpathExpression.replace("xxx", String.valueOf(i));
            }
            if (codeSystemNameXpathExpression != null && !codeSystemNameXpathExpression.isEmpty()) {
              codeSystemNameXpathExpression = codeSystemNameXpathExpression.replace("xxx", String.valueOf(i));
            }

            cldef = new CLPPropertyDefinition(codeXpathExpression, labelXpathExpression, codeSystemXpathExpression, codeSystemNameXpathExpression, propDef.getPropertyClassName(), propDef.getPropertyName());
            Object obj = extractPropertyValue(doc, cldef);
            //log.debug("cldef ==> " + cldef.toString());

            // add to array of property values
            if (obj != null) {
              objsList.add(obj);
            }

            codeXpathExpression = propDef.getCodeXpath();
            labelXpathExpression = propDef.getLabelXpath();
          }
        }
//        else {
//          log.error("Error: property \"" + propDef.getPropertyName() + "\" has no xpath expression!");
//        }
      } catch (Exception e) {
        log.error(e);
      }
    }

    return objsList;
  }

  /**
   *
   * @param doc
   * @param propDef
   * @return
   */
  protected List extractPropertyValues(Object doc, VUPListPropertyDefinition propDef) {
    // array of property values
    List<Object> objsList = new ArrayList();

    if (propDef != null && doc != null) {
      try {
        String strListSize = xpathEvaluate(doc, propDef.getListSizeXpath());
        int listSize = Integer.parseInt(strListSize);
        //log.debug("List size of property \"" + propDef.getPropertyName() +
        //        "\"==> value:" + listSize + ",expression:" + propDef.getListSizeXpath());

        String valueXpathExpression = propDef.getValueXpath();
        String unitXpathExpression = propDef.getUnitXpath();
        VUPPropertyDefinition cldef = null;

        if (valueXpathExpression != null && !valueXpathExpression.isEmpty()) {
          for (int i = 1; i <= listSize; i++) {
            if (valueXpathExpression != null && !valueXpathExpression.isEmpty()) {
              valueXpathExpression = valueXpathExpression.replace("xxx", String.valueOf(i));
            }
            if (unitXpathExpression != null && !unitXpathExpression.isEmpty()) {
              unitXpathExpression = unitXpathExpression.replace("xxx", String.valueOf(i));
            }

            cldef = new VUPPropertyDefinition(valueXpathExpression, unitXpathExpression, propDef.getPropertyClassName(), propDef.getPropertyName());
            Object obj = extractPropertyValue(doc, cldef);
            //log.debug("cldef ==> " + cldef.toString());

            // add to array of property values
            if (obj != null) {
              objsList.add(obj);
            }

            valueXpathExpression = propDef.getValueXpath();
            unitXpathExpression = propDef.getUnitXpath();
          }
        }
//        else {
//          log.error("Error: property \"" + propDef.getPropertyName() + "\" has no xpath expression!");
//        }
      } catch (Exception e) {
        log.error(e);
      }
    }

    return objsList;
  }

  /**
   *
   * @param doc
   * @param propDef
   * @return
   */
  protected List extractPropertyValues(Object doc, ValueTypeListPropertyDefinition propDef) {
    // array of property values
    List<Object> objsList = new ArrayList();

    if (propDef != null && doc != null) {
      try {
        String strListSize = xpathEvaluate(doc, propDef.getListSizeXpath());
        int listSize = Integer.parseInt(strListSize);
        //log.debug("List size of property \"" + propDef.getPropertyName() +
        //        "\"==> value:" + listSize + ",expression:" + propDef.getListSizeXpath());

        if (listSize > 0) {
          String valueXpathExpression = propDef.getValueXpath();
          String displayXpathExpression = propDef.getDisplayIndXpath();
          String csXpathExpression = propDef.getCodeSystemXpath();
          String csnXpathExpression = propDef.getCodeSystemNameXpath();

          ValueTypePropertyDefinition vtldef = null;

          if (valueXpathExpression != null && !valueXpathExpression.isEmpty()) {
            for (int i = 1; i <= listSize; i++) {
              if (valueXpathExpression != null && !valueXpathExpression.isEmpty()) {
                valueXpathExpression = valueXpathExpression.replace(propDef.getIndexRef(), String.valueOf(i));
              }
              if (displayXpathExpression != null && !displayXpathExpression.isEmpty()) {
                displayXpathExpression = displayXpathExpression.replace(propDef.getIndexRef(), String.valueOf(i));
              }
              if (csXpathExpression != null && !csXpathExpression.isEmpty()) {
                csXpathExpression = csXpathExpression.replace(propDef.getIndexRef(), String.valueOf(i));
              }
              if (csnXpathExpression != null && !csnXpathExpression.isEmpty()) {
                csnXpathExpression = csnXpathExpression.replace(propDef.getIndexRef(), String.valueOf(i));
              }

              vtldef = new ValueTypePropertyDefinition(valueXpathExpression, displayXpathExpression, csXpathExpression, csnXpathExpression,
                      propDef.getPropertyClassName(), propDef.getPropertyName());
              Object obj = extractPropertyValue(doc, vtldef);
              //log.debug("vtldef ==> " + vtldef.toString());

              // add to array of property values
              if (obj != null) {
                objsList.add(obj);
              }

              valueXpathExpression = propDef.getValueXpath();
              displayXpathExpression = propDef.getDisplayIndXpath();
              csXpathExpression = propDef.getCodeSystemXpath();
              csnXpathExpression = propDef.getCodeSystemNameXpath();
            }
          }
//          else {
//            log.error("Error: property \"" + propDef.getPropertyName() + "\" has no xpath expression!");
//          }
        }
      } catch (Exception e) {
        log.error(e);
      }
    }

    return objsList;
  }

  protected Object extractPropertyValue(Object doc, CLPPropertyDefinition propDef) {
    CodeLabelPair clp = null;

    if (propDef != null && doc != null) {
      String code = xpathEvaluate(doc, propDef.getCodeXpath());
      String label = xpathEvaluate(doc, propDef.getLabelXpath());
      String codeSystem = xpathEvaluate(doc, propDef.getCodeSystemXpath());
      String codeSystemName = xpathEvaluate(doc, propDef.getCodeSystemNameXpath());

      // must have a code!
      if (code != null && !code.isEmpty()) {
        clp = new CodeLabelPair(code, label, codeSystem, codeSystemName);
        //log.debug("Property \"" + propDef.getPropertyName() + "\" ==> class:" + propDef.getPropertyClassName() + ",code expression:\"" + propDef.getCodeXpathExpression() + "\",code value:" + clp.getCode() + ",label expression:\"" + propDef.getLabelXpathExpression() + "\",label value:" + clp.getLabel());
      }
    }
//    else {
//      log.error("Cannot extract property -- CodeLabelPropertyDefinition not available!");
//    }

    return clp;
  }

  protected Object extractPropertyValue(Object doc, VUPPropertyDefinition propDef) {
    ValueUnitPair vup = null;

    if (propDef != null && doc != null) {
      String value = xpathEvaluate(doc, propDef.getValueXpath());
      String unit = xpathEvaluate(doc, propDef.getUnitXpath());

      // must have a value!
      if (value != null && !value.isEmpty()) {
        vup = new ValueUnitPair(value, unit);
      }
    }
//    else {
//      log.error("Cannot extract property -- VUPPropertyDefinition not available!");
//    }

    return vup;
  }

  protected Object extractPropertyValue(Object doc, ValueTypePropertyDefinition propDef) {
    ValueType vt = null;

    if (propDef != null && doc != null) {
      String value = xpathEvaluate(doc, propDef.getValueXpath());
      String displayIndicator = xpathEvaluate(doc, propDef.getDisplayIndXpath());
      String codeSystemIndicator = xpathEvaluate(doc, propDef.getCodeSystemXpath());
      String codeSystemNameIndicator = xpathEvaluate(doc, propDef.getCodeSystemNameXpath());

      // must have a value!
      if (value != null && !value.isEmpty()) {
        vt = new ValueType(value, toBoolean(displayIndicator), codeSystemIndicator, codeSystemNameIndicator);
      }
    }
//    else {
//      log.error("Cannot extract property -- ValueTypePropertyDefinition not available!");
//    }

    return vt;
  }

  protected String xpathEvaluate(Object doc, String expression) {
    String value = "";
    if (expression != null && !expression.isEmpty()) {
      try {
        XPathExpression expr = xpath.compile(expression);
        value = expr.evaluate(doc);
        //log.debug("xpath expression=" + expression + ",value=" + value);
      } catch (XPathExpressionException ex) {
        log.error(ex.getMessage() + "-- Failed to evaluate expression \"" + expression + "\"");
      }
    }
//    else {
//      log.error("NULL/empty XPath expression -- can not extract property!");
//    }

    return value;
  }

  protected NodeList xpathNodeEvaluate(Object doc, String expression) {
    NodeList nodes = null;

    try {
      XPathExpression expr = xpath.compile(expression);
      // The return type may also be set to NODE, STRING, BOOLEAN or NUMBER.
      // The NodeSet class implements the NodeList interface.
      nodes = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);
    } catch (XPathExpressionException ex) {
      log.error(ex.getMessage() + "-- Failed to evaluate expression \"" + expression + "\"");
    }

    return nodes;
  }

  /**
   *
   * @param obj
   * @param propertyName
   * @param propValue
   */
  protected void setProperty(Object obj, String propertyName, Object propValue) {
    try {
      String methodName = "set" + propertyName.trim();
      //log.debug("Method to invoke=" + methodName + "()");

      Class[] types = new Class[]{propValue.getClass()};

      Method method = obj.getClass().getMethod(methodName, types);
      method.invoke(obj, new Object[]{propValue});
    } catch (Exception ex) {
      log.error("Failed to invoke set" + propertyName + "()", ex);
    }
  }

  /**
   *
   * @param obj
   * @param propertyName
   * @param propValues
   */
  protected void setProperty(Object obj, String propertyName, Object[] propValues) {
    try {
      String methodName = "set" + propertyName;
      //log.debug("Method to invoke=" + methodName);

      Class[] types = new Class[]{propValues[0].getClass()};

      Method method = obj.getClass().getMethod(methodName, types);
      method.invoke(obj, propValues);
    } catch (Exception ex) {
      log.error(ex.getMessage() + "-Failed to invoke set" + propertyName + "()");
    }
  }

  /**
   * Convert the "String" property object to the correct object class.
   * @param propertyName
   * @param propertyValue
   * @return
   */
  protected Object toObject(String classname, String obj) {

    if (obj != null && !obj.isEmpty()) {
      try {
        if (obj.getClass().getName().equals(classname)) {
          return obj;
        } else {
          if (classname.equals("java.lang.Integer")) {
            return new Integer(obj);
          }
          if (classname.equals("java.lang.Boolean")) {
            return new Boolean(obj);
          }
          else if (classname.equals("java.util.Date")) {
            Date dateObj = DateUtil.parseCDADateFormat(obj);
            return dateObj;
          } else {
            log.error("Class type=" + classname + " is not supported!");
          }
        }
      } catch (Exception e) {
        log.error(e);
      }
    }

    return null;
  }

  protected Boolean toBoolean(String value) {

    if (value != null && !value.isEmpty()) {
      try {
        if (value.equalsIgnoreCase("false")) {
          return new Boolean(false);
        } else {
          return new Boolean(true);
        }
      } catch (Exception e) {
        log.error(e);
      }
    }

    return new Boolean(true);
  }

  /**
   *
   * @param xpath
   */
  public void setXpath(XPath xpath) {
    this.xpath = xpath;
  }

  /**
   *
   * @param factProperties
   */
  public void setFactProperties(Map<String, SimplePropertyDefinition> factProperties) {
    this.factProperties = factProperties;
  }

  @Override
  public String getFactBaseXpath() {
    return factBaseXpath;
  }

  @Override
  public void setFactBaseXpath(String factBaseXpath) {
    this.factBaseXpath = factBaseXpath;
  }

  public void setFacts(List<Object> facts) {
    this.facts = facts;
  }

  @Override
  public List<Object> getFacts() {
    if (facts == null) {
      facts = new ArrayList();
    }

    return facts;
  }

  /**
   *
   * @param factClassName
   */
  public void setFactClassName(String factClassName) {
    this.factClassName = factClassName;
  }

  public String getFactIndexCharset() {
    return factIndexCharset;
  }

  public void setFactIndexCharset(String factIndexCharset) {
    this.factIndexCharset = factIndexCharset;
  }
  
  /**
   *
   */
  @Override
  public void deleteFacts() {
    if (facts != null) {
      facts.clear();
    }
  }
}
