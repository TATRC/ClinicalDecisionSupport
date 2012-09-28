/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gov.hhs.fha.nhinc.adapter.fact.extractor;

import gov.hhs.fha.nhinc.adapter.fact.ImagingResultFactType;
import gov.hhs.fha.nhinc.adapter.fact.ProcedureFactType;
import gov.hhs.fha.nhinc.adapter.fact.xml.XMLUtils;
import java.util.ArrayList;
import java.util.List;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.w3c.dom.Node;

/**
 *
 * @author kim
 */
public class ImagingEventsFactExtractor extends XpathFactExtractorImpl {

  private static Log log = LogFactory.getLog(ImagingEventsFactExtractor.class);
  /**
   * XPath expression to extract the total number of care events.
   */
  protected String eventsCountXpath;
  /**
   * The extractor to use for extracting diagnpstic imaging results info
   */
  protected FactExtractor imagingResultExtractor;
  /**
   * The extractor to use for extracting procedure info
   */
  protected FactExtractor procedureExtractor;

  //List<Object> oFacts = null;
  public ImagingEventsFactExtractor(XPath xpath) {
    super(xpath);
  }

  /**
   * 
   * @param eventCountXpath
   */
  public void setEventsCountXpath(String eventCountXpath) {
    this.eventsCountXpath = eventCountXpath;
  }

  public void setProcedureExtractor(FactExtractor procedureExtractor) {
    this.procedureExtractor = procedureExtractor;
  }

  public void setImagingResultExtractor(FactExtractor imagingResultExtractor) {
    this.imagingResultExtractor = imagingResultExtractor;
  }

  @Override
  public void extract(Object doc) throws Exception {

    //--------------------------------------------------------------------------
    // clear out fact objects list(s)
    //--------------------------------------------------------------------------
    this.deleteFacts();
    this.procedureExtractor.deleteFacts();
    this.imagingResultExtractor.deleteFacts();

    //--------------------------------------------------------------------------
    // return due to error(s)
    //--------------------------------------------------------------------------
    if (doc == null) {
      return;
    }

    //--------------------------------------------------------------------------
    // keep copy of original xpath expressions
    //--------------------------------------------------------------------------
    String procedureEventXpath = ((XPathFactExtractor) procedureExtractor).getFactBaseXpath();
    String resultFactXpath = ((XPathFactExtractor) imagingResultExtractor).getFactBaseXpath();
    String resultFactsCountXpath = ((ResultFactExtractor) imagingResultExtractor).getFactsCountXpath();

    //--------------------------------------------------------------------------
    // debug info
    //--------------------------------------------------------------------------
    if (log.isDebugEnabled()) {
      log.debug("Diagnostic imaging events count xpath=" + this.eventsCountXpath);
      log.debug("Diagnostic imaging event xpath=" + this.factBaseXpath);
      log.debug("resultFactXpath=" + resultFactXpath);
      log.debug("Imaging results count xpath=" + resultFactsCountXpath);
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
        if (log.isDebugEnabled()) {
          XMLUtils.printNode(eventNode, System.out);
        }

        //----------------------------------------------------------------------
        // override current base xpath for lab test info
        //----------------------------------------------------------------------
        tmpExpr = procedureEventXpath;
        tmpExpr = tmpExpr.replace(this.factIndexCharset, String.valueOf(eventIdx));
        ((XPathFactExtractor) procedureExtractor).setFactBaseXpath(tmpExpr);

        //----------------------------------------------------------------------
        // extract lab test fact in this event
        //----------------------------------------------------------------------
        procedureExtractor.extract(eventNode);

        // debug info
        if (log.isDebugEnabled()) {
          log.debug("Test Event #" + eventIdx + ": lab test facts=" + procedureExtractor.getFacts().size());
          for (int i = 0; i < procedureExtractor.getFacts().size(); i++) {
            log.debug(procedureExtractor.getFacts().get(i));
          }
        }

        //----------------------------------------------------------------------
        // override default xpath expressions of results extractor with relevant
        // node indexing value.
        //----------------------------------------------------------------------
        tmpExpr = resultFactXpath;
        tmpExpr = tmpExpr.replace(this.factIndexCharset, String.valueOf(eventIdx));
        ((XPathFactExtractor) imagingResultExtractor).setFactBaseXpath(tmpExpr);

        tmpExpr = resultFactsCountXpath;
        tmpExpr = tmpExpr.replace(this.factIndexCharset, String.valueOf(eventIdx));
        ((ResultFactExtractor) imagingResultExtractor).setFactsCountXpath(tmpExpr);

        //----------------------------------------------------------------------
        // extract result facts in this event
        //----------------------------------------------------------------------
        imagingResultExtractor.extract(doc);

        // debug info
        if (log.isDebugEnabled()) {          
          log.debug("Event #" + eventIdx + ":diagnostic imaging result facts=" + imagingResultExtractor.getFacts().size());
          for (int i = 0; i < imagingResultExtractor.getFacts().size(); i++) {
            log.debug(imagingResultExtractor.getFacts().get(i));
          }
        }

        //----------------------------------------------------------------------
        // merge lab test and result facts
        //----------------------------------------------------------------------
        mergeFacts(imagingResultExtractor.getFacts(), (ProcedureFactType) procedureExtractor.getFacts().get(0));

      } else {
        log.debug("No nodes found - xpath \"" + tmpExpr + "\"!");
      }

      // clear out extractor fact lists
      procedureExtractor.deleteFacts();
      imagingResultExtractor.deleteFacts();
    }

    setHistorical(true);

    //----------------------------------------------------------------------
    // debug info
    //----------------------------------------------------------------------
    for (int i = 0; i < getFacts().size(); i++) {
      log.debug(getFacts().get(i));
    }
  }

  private void mergeFacts(List<Object> resultFacts, ProcedureFactType procedureFact) {

    for (int i = 0; i < resultFacts.size(); i++) {
      ImagingResultFactType fact = (ImagingResultFactType) resultFacts.get(i);

      if (procedureFact != null) {
        fact.setPatientId(procedureFact.getPatientId());
        fact.setId((ArrayList) procedureFact.getId());       
        fact.setProcedureCode(procedureFact.getProcedureType());
        fact.setFreeTextProcedureCode(procedureFact.getFreeTextProcedureType());
        // HL7 date+time format is YYYYMMDDHHMMSS.SSSS+/-ZZZZ  with the time zone (+/-ZZZZ)
        // is represented as +/-HHMM offset from UCT (formerly Greenwich Mean Time (GMT)),
        // where +0000 or -0000 both represent UCT (without offset).
        // Example: 20090629144357-0700
        if (procedureFact.getProcedureDate() != null)
          fact.setProcedureDate(procedureFact.getProcedureDate());
        if (procedureFact.getCodedTargetSite() != null && !procedureFact.getCodedTargetSite().isEmpty())
          fact.setTargetSiteCode(procedureFact.getCodedTargetSite().get(0));
      }

      this.facts.add(fact);
    }
  }

  private void setHistorical(boolean indicator) {
    for (int i = 0; i <
            getFacts().size(); i++) {
      if (getFacts().get(i).getClass().getName().equalsIgnoreCase(factClassName)) {
        ImagingResultFactType fact = (ImagingResultFactType) getFacts().get(i);
        fact.setHistorical(indicator);
      }
    }
  }
}
