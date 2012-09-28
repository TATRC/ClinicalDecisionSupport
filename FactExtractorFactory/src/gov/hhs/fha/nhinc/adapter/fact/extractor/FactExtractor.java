/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gov.hhs.fha.nhinc.adapter.fact.extractor;

import java.util.List;

/**
 *
 * @author kim
 */
public interface FactExtractor {

  /**
   * Extract relevant fact from a DOM object, i.e. Document, Node ...
   * @param doc
   * @throws java.lang.Exception
   */
  public void extract(Object doc) throws Exception;

  /**
   * Returns the list of fact objects.
   * @return
   */
  public List<Object> getFacts();

  /**
   * Delete all fact objects.
   */
  public void deleteFacts();
}
