/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gov.hhs.fha.nhinc.displaymobilealert;

import gov.hhs.fha.nhinc.common.dda.GetDataSourceNameResponseType;
import gov.hhs.fha.nhinc.displayalert.DisplayAlertDataUtil;

/**
 *
 * @author cmatser
 */
public class DisplayMobileAlertImpl {

    /**
     * Retrieve data source name.
     *
     * @param getDataSourceNameRequest
     * @return
     */
    public gov.hhs.fha.nhinc.common.dda.GetDataSourceNameResponseType getDataSourceName(gov.hhs.fha.nhinc.common.dda.GetDataSourceNameRequestType getDataSourceNameRequest) {
        GetDataSourceNameResponseType response = new GetDataSourceNameResponseType();
        response.setReturn(DisplayAlertDataUtil.DATA_SOURCE_ALERTS_MOBILE);

        return response;
    }

    /**
     * Update inbox status.
     * 
     * @param request
     * @return
     */
    public gov.hhs.fha.nhinc.common.dda.UpdateComponentInboxStatusResponseType updateComponentInboxStatus(gov.hhs.fha.nhinc.common.dda.UpdateComponentInboxStatusRequestType request) {
        return new DisplayAlertDataUtil().updateComponentInboxStatus(DisplayAlertDataUtil.DATA_SOURCE_ALERTS_MOBILE, request);
    }

    /**
     * Retrieve detail data.
     *
     * @param request
     * @return
     */
    public gov.hhs.fha.nhinc.common.dda.GetComponentDetailDataResponseType getComponentDetailDataForUser(gov.hhs.fha.nhinc.common.dda.GetComponentDetailDataForUserRequestType request) {
        return new DisplayAlertDataUtil().getComponentDetailDataForUser(DisplayAlertDataUtil.DATA_SOURCE_ALERTS_MOBILE, request);
    }

    /**
     * Retreive summary objects for all alerts.
     *
     * @param request
     * @return
     */
    public gov.hhs.fha.nhinc.common.dda.GetComponentSummaryDataResponseType getComponentSummaryDataForUser(gov.hhs.fha.nhinc.common.dda.GetComponentSummaryDataForUserRequestType request) {
        return new DisplayAlertDataUtil().getComponentSummaryDataForUser(DisplayAlertDataUtil.DATA_SOURCE_ALERTS_MOBILE, request);
    }

}
