/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gov.hhs.fha.nhinc.displayptalertdata;

import gov.hhs.fha.nhinc.common.dda.GetComponentDetailDataForUserRequestType;
import gov.hhs.fha.nhinc.common.dda.GetDataSourceNameRequestType;
import gov.hhs.fha.nhinc.common.dda.GetDataSourceNameResponseType;
import gov.hhs.fha.nhinc.common.dda.GetComponentDetailDataResponseType;
import gov.hhs.fha.nhinc.common.dda.GetComponentSummaryDataForUserRequestType;
import gov.hhs.fha.nhinc.common.dda.GetComponentSummaryDataResponseType;
import gov.hhs.fha.nhinc.common.dda.UpdateComponentInboxStatusRequestType;
import gov.hhs.fha.nhinc.common.dda.UpdateComponentInboxStatusResponseType;
import gov.hhs.fha.nhinc.displayalert.DisplayAlertDataUtil;

/**
 *
 * @author cmatser
 */
public class DisplayPtAlertDataImpl {

    /**
     * Retrieve data source name.
     * 
     * @param getDataSourceNameRequest
     * @return
     */
    public GetDataSourceNameResponseType getDataSourceName(GetDataSourceNameRequestType getDataSourceNameRequest) {
        GetDataSourceNameResponseType response = new GetDataSourceNameResponseType();
        response.setReturn(DisplayAlertDataUtil.DATA_SOURCE_PT_ALERTS);

        return response;
    }

    /**
     * Update inbox status.
     *
     * @param request
     * @return
     */
    public UpdateComponentInboxStatusResponseType updateComponentInboxStatus(UpdateComponentInboxStatusRequestType request) {
        return new DisplayAlertDataUtil().updateComponentInboxStatus(DisplayAlertDataUtil.DATA_SOURCE_PT_ALERTS, request);
    }

    /**
     * Retrieve detail data.
     *
     * @param request
     * @return
     */
    public GetComponentDetailDataResponseType getComponentDetailDataForUser(GetComponentDetailDataForUserRequestType request) {
        return new DisplayAlertDataUtil().getComponentDetailDataForUser(DisplayAlertDataUtil.DATA_SOURCE_PT_ALERTS, request);
    }

    /**
     * Retreive summary objects for all alerts.
     *
     * @param request
     * @return
     */
    public GetComponentSummaryDataResponseType getComponentSummaryDataForUser(GetComponentSummaryDataForUserRequestType request) {
        return new DisplayAlertDataUtil().getComponentSummaryDataForUser(DisplayAlertDataUtil.DATA_SOURCE_PT_ALERTS, request);
    }

}
