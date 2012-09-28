/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gov.hhs.fha.nhinc.adapter.fact.utils;

import gov.hhs.fha.nhinc.adapter.fact.*;
import gov.hhs.fha.nhinc.adapter.fact.extractor.FactExtractorFactory;
import java.util.List;

/**
 *
 * @author kim
 */
public class FactUtils {

  public static NameFactType getLegalName(List<NameFactType> names) {
    if (names != null) {
      for (int j = 0; j < names.size(); j++) {
        NameFactType nameFact = (NameFactType) names.get(j);
        if (nameFact.getNameType() != null) {
          if (nameFact.getNameType().getCode().equalsIgnoreCase(FactExtractorFactory.LEGAL_NAME_INDICATOR)) {
            nameFact.getNameType().setLabel(FactExtractorFactory.getInstance().getLegalNameLabel());
            return nameFact;
          }
        }
      }
    }

    return null;
  }

  public static ValueType getNPI(List<ValueType> ids) {
    if (ids != null) {
      for (int j = 0; j < ids.size(); j++) {
        ValueType idFact = (ValueType) ids.get(j);
        if ((idFact.getCodeSystem() != null) && (idFact.getCodeSystem().equalsIgnoreCase(FactExtractorFactory.NPI_EXTENSION))) {
            return idFact;
        }
      }
    }

    return null;
  }

}
