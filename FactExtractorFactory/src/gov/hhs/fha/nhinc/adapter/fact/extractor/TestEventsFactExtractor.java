/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gov.hhs.fha.nhinc.adapter.fact.extractor;

import gov.hhs.fha.nhinc.adapter.fact.LabTestFactType;
import gov.hhs.fha.nhinc.adapter.fact.ResultFactType;
import gov.hhs.fha.nhinc.adapter.fact.utils.DateUtil;
import java.util.ArrayList;
import java.util.List;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import org.apache.commons.logging.LogFactory;
import org.w3c.dom.Node;

/**
 *
 * @author kim
 */
public class TestEventsFactExtractor extends XpathFactExtractorImpl {

  //private static Log log = LogFactory.getLog(TestEventsFactExtractor.class);
  /**
   * XPath expression to extract the total number of care events.
   */
  protected String eventsCountXpath;
  /**
   * The extractor to use for extracting test results info
   */
  protected FactExtractor resultExtractor;
  /**
   * The extractor to use for extracting lab test info
   */
  protected FactExtractor labTestExtractor;

  //List<Object> oFacts = null;
  public TestEventsFactExtractor(XPath xpath) {
    super(xpath);
    log = LogFactory.getLog(TestEventsFactExtractor.class);
  }

  /**
   * 
   * @param eventCountXpath
   */
  public void setEventsCountXpath(String eventCountXpath) {
    this.eventsCountXpath = eventCountXpath;
  }

  public void setLabTestExtractor(FactExtractor labTestExtractor) {
    this.labTestExtractor = labTestExtractor;
  }

  public void setResultExtractor(FactExtractor resultExtractor) {
    this.resultExtractor = resultExtractor;
  }

  @Override
  public void extract(Object doc) throws Exception {

    //--------------------------------------------------------------------------
    // clear out fact objects list(s)
    //--------------------------------------------------------------------------
    this.deleteFacts();
    this.labTestExtractor.deleteFacts();
    this.resultExtractor.deleteFacts();

    //--------------------------------------------------------------------------
    // return due to error(s)
    //--------------------------------------------------------------------------
    if (doc == null) {
      return;
    }

    //--------------------------------------------------------------------------
    // keep copy of original xpath expressions
    //--------------------------------------------------------------------------
    String labtestEventXpath = ((LabTestFactExtractor) labTestExtractor).getFactBaseXpath();
    String resultFactXpath = ((XPathFactExtractor) resultExtractor).getFactBaseXpath();
    String resultFactsCountXpath = ((ResultFactExtractor) resultExtractor).getFactsCountXpath();

    if (log.isDebugEnabled()) {
      log.debug("Test events count xpath=" + this.eventsCountXpath);
      log.debug("Test event xpath=" + this.factBaseXpath);
      log.debug("resultFactXpath=" + resultFactXpath);
      log.debug("Test results count xpath=" + resultFactsCountXpath);
    }

    //--------------------------------------------------------------------------
    // extract the total number of care events from XML document.
    //--------------------------------------------------------------------------
    String tmpExpr = eventsCountXpath;
    XPathExpression expr = xpath.compile(tmpExpr);

    String val = expr.evaluate(doc);
    int events = Integer.parseInt(val);
    log.debug("# of care events=" + events);

    //--------------------------------------------------------------------------
    // iterate thru each care event
    //--------------------------------------------------------------------------
    for (int eventIdx = 1; eventIdx <= events; eventIdx++) {
      tmpExpr = this.factBaseXpath;
      tmpExpr = tmpExpr.replace(this.factIndexCharset, String.valueOf(eventIdx));

      // parse XML document to obtain only relevant node for further processing
      Node eventNode = (Node) xpath.evaluate(tmpExpr, doc, XPathConstants.NODE);
      if (eventNode != null && eventNode.hasChildNodes()) {
        //if (log.isDebugEnabled()) {
        //  XMLUtils.printNode(eventNode, System.out);
        //}

        //----------------------------------------------------------------------
        // override current base xpath for lab test info
        //----------------------------------------------------------------------
        tmpExpr = labtestEventXpath;
        tmpExpr = tmpExpr.replace(this.factIndexCharset, String.valueOf(eventIdx));
        ((LabTestFactExtractor) labTestExtractor).setFactBaseXpath(tmpExpr);

        //----------------------------------------------------------------------
        // extract lab test fact in this event
        //----------------------------------------------------------------------
        labTestExtractor.extract(eventNode);

        // debug info
//        if (log.isDebugEnabled()) {
//          log.debug("Test Event #" + eventIdx + ": lab test facts=" + labTestExtractor.getFacts().size());
//          for (int i = 0; i < labTestExtractor.getFacts().size(); i++) {
//            log.debug(labTestExtractor.getFacts().get(i));
//          }
//        }

        //----------------------------------------------------------------------
        // override default xpath expressions of results extractor with relevant
        // node indexing value.
        //----------------------------------------------------------------------
        tmpExpr = resultFactXpath;
        tmpExpr = tmpExpr.replace(this.factIndexCharset, String.valueOf(eventIdx));
        ((XPathFactExtractor) resultExtractor).setFactBaseXpath(tmpExpr);

        tmpExpr = resultFactsCountXpath;
        tmpExpr = tmpExpr.replace(this.factIndexCharset, String.valueOf(eventIdx));
        ((ResultFactExtractor) resultExtractor).setFactsCountXpath(tmpExpr);

        //----------------------------------------------------------------------
        // extract result facts in this event
        //----------------------------------------------------------------------
        resultExtractor.extract(doc);

        // debug info
        if (log.isDebugEnabled()) {          
          log.debug("Event #" + eventIdx + ":result facts=" + resultExtractor.getFacts().size());
          for (int i = 0; i < resultExtractor.getFacts().size(); i++) {
            log.debug(resultExtractor.getFacts().get(i));
          }
        }

        //----------------------------------------------------------------------
        // merge lab test and result facts
        //----------------------------------------------------------------------
        mergeFacts(resultExtractor.getFacts(), (LabTestFactType) labTestExtractor.getFacts().get(0));

      } else {
        log.debug("No nodes found - xpath \"" + tmpExpr + "\"!");
      }

      // clear out extractor fact lists
      labTestExtractor.deleteFacts();
      resultExtractor.deleteFacts();
    }

    setHistorical(true);
  }

  private void mergeFacts(List<Object> resultFacts, LabTestFactType labFact) {

    for (int i = 0; i < resultFacts.size(); i++) {
      ResultFactType fact = (ResultFactType) resultFacts.get(i);
      if (labFact != null) {
        fact.setPatientId(labFact.getPatientId());
        fact.setId((ArrayList) labFact.getId());
        if (labFact.getCodedBattery() != null && !labFact.getCodedBattery().getCode().equalsIgnoreCase("18719-5")) {
          fact.setCodedPanelType(labFact.getCodedBattery());
        }
        
        fact.setFreeTextPanelType(labFact.getFreeTextBattery());
        // HL7 date+time format is YYYYMMDDHHMMSS.SSSS+/-ZZZZ  with the time zone (+/-ZZZZ)
        // is represented as +/-HHMM offset from UCT (formerly Greenwich Mean Time (GMT)),
        // where +0000 or -0000 both represent UCT (without offset).
        // Example: 20090629144357-0700
        if (labFact.getSpecimenDate() != null)
          fact.setSpecimentDate(DateUtil.parseCDADateFormat(labFact.getSpecimenDate()));
        fact.setSpecimen(labFact.getFreeTextSpecimen());
        fact.setId((ArrayList) labFact.getId());
      }

      this.facts.add(fact);
    }
  }

  private void setHistorical(boolean indicator) {
    for (int i = 0; i <
            getFacts().size(); i++) {
      if (getFacts().get(i).getClass().getName().equalsIgnoreCase(factClassName)) {
        ResultFactType fact = (ResultFactType) getFacts().get(i);
        fact.setHistorical(indicator);
      }
    }
  }
}
