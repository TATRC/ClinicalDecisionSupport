/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gov.hhs.fha.nhinc.displaydataaggregator;

import gov.hhs.fha.nhinc.common.dda.DetailData;
import gov.hhs.fha.nhinc.common.dda.GetAvailableSourcesResponseType;
import gov.hhs.fha.nhinc.common.dda.GetComponentDetailDataForUserRequestType;
import gov.hhs.fha.nhinc.common.dda.GetComponentDetailDataResponseType;
import gov.hhs.fha.nhinc.common.dda.GetComponentSummaryDataForUserRequestType;
import gov.hhs.fha.nhinc.common.dda.GetComponentSummaryDataResponseType;
import gov.hhs.fha.nhinc.common.dda.GetDetailDataForUserRequestType;
import gov.hhs.fha.nhinc.common.dda.GetDetailDataResponseType;
import gov.hhs.fha.nhinc.common.dda.GetSummaryDataForUserRequestType;
import gov.hhs.fha.nhinc.common.dda.GetSummaryDataResponseType;
import gov.hhs.fha.nhinc.common.dda.NameValuesPair;
import gov.hhs.fha.nhinc.common.dda.ServiceError;
import gov.hhs.fha.nhinc.common.dda.SummaryData;
import gov.hhs.fha.nhinc.common.dda.UpdateInboxStatusResponseType;
import gov.hhs.fha.nhinc.displaymanager.model.InboxStatus;
import gov.hhs.fha.nhinc.displaymanager.model.InboxStatusQueryParams;
import gov.hhs.fha.nhinc.displaymanager.service.DisplayStatusException;
import gov.hhs.fha.nhinc.displaymanager.service.DisplayStatusService;
import gov.hhs.fha.nhinc.kmr.properties.PropertyAccessException;
import gov.hhs.fha.nhinc.kmr.properties.PropertyAccessor;
import gov.hhs.fha.nhinc.kmr.ura.IdAddressBean;
import gov.hhs.fha.nhinc.kmr.ura.UniversalResourceAddressBean;
import gov.hhs.fha.nhinc.kmr.ura.UniversalResourceAddressBeanFactory;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author cmatser
 */
public class DisplayDataAggregatorImpl {

    /** Property constants. */
    public static final String PROPERTY_FILE = "displayAggregator";
    public static final String DATA_SOURCE_COUNT = "dataSource.count";
    public static final String DATA_SOURCE_PREFIX = "dataSource.";
    public static final String DATA_SOURCE_NAME = ".name";
    public static final String DATA_SOURCE_URL = ".url";

    /** Max data sources. */
    public static final Integer MAX_DATA_SOURCES = 100;

    /** Error code. */
    public static final Integer ERROR_CODE = -1;

    /** Sources where we fetch status values. */
    public static final String STATUS_NHIN_DOCS = "NHIN Documents";

    /** Item names for name value pairs. */
    public static final String ITEM_READ_STATUS = "Read";

    private static Log log = LogFactory.getLog(DisplayDataAggregatorImpl.class);

    /**
     * Update Inbox status with passed params.
     * 
     * @param updateInboxStatusRequest
     * @return
     */
    public gov.hhs.fha.nhinc.common.dda.UpdateInboxStatusResponseType updateInboxStatus(gov.hhs.fha.nhinc.common.dda.UpdateInboxStatusRequestType request) {
        UpdateInboxStatusResponseType response = new UpdateInboxStatusResponseType();

        if ((request.getUserId() == null) || request.getUserId().isEmpty()) {
            ServiceError error = new ServiceError();
            error.setCode(-1);
            error.setText("Missing user.");
            response.getErrorList().add(error);
        }

        if ((request.getDataSource() == null) || request.getDataSource().isEmpty()) {
            ServiceError error = new ServiceError();
            error.setCode(-1);
            error.setText("Missing data source.");
            response.getErrorList().add(error);
        }

        if ((request.getItemId() == null) || request.getItemId().isEmpty()) {
            ServiceError error = new ServiceError();
            error.setCode(-1);
            error.setText("Missing item.");
            response.getErrorList().add(error);
        }

        //Check for errors
        if (!response.getErrorList().isEmpty()) {
            return response;
        }

        DisplayStatusService service = new DisplayStatusService();

        //First find existing
        InboxStatusQueryParams params = new InboxStatusQueryParams();
        params.setUser(request.getUserId());
        params.setSource(request.getDataSource());
        params.setItem(request.getItemId());
        List<InboxStatus> list = service.inboxStatusQuery(params);

        //Pull out existing or create new
        InboxStatus status = null;
        if ((list == null) || (list.isEmpty())) {
            status = new InboxStatus();
            status.setUser(request.getUserId());
            status.setSource(request.getDataSource());
            status.setItem(request.getItemId());
        }
        else {
            status = list.get(0);
        }

        //Update status
        status.setRead(request.isRead());

        //Save or update in db
        service.saveInboxStatus(status);

        return response;
    }

    /**
     * Return data source list.
     * 
     * @param getAvailableSourcesRequest
     * @return
     */
    public gov.hhs.fha.nhinc.common.dda.GetAvailableSourcesResponseType getAvailableSources(gov.hhs.fha.nhinc.common.dda.GetAvailableSourcesRequestType getAvailableSourcesRequest) {
        GetAvailableSourcesResponseType response = new GetAvailableSourcesResponseType();
        Hashtable<String,String> sourceTable = new Hashtable<String,String>();

        try {
            ServiceError dataSourceError = getDataSources(sourceTable);
            if (dataSourceError != null) {
                throw new Exception("Error retrieving data sources.");
            }

            if (sourceTable.size() == 0) {
                throw new Exception("No data sources defined.");
            }

            for (String dataSource : sourceTable.keySet()) {
                response.getReturn().add(dataSource);
            }
        }
        catch (Exception e) {
            String errorMsg = "Error getting data sources.";
            log.error(errorMsg, e);
            response.getReturn().add(errorMsg + ". " + e.getMessage());
        }

        return response;
    }

    /**
     * Handle detail data sources.
     * 
     * @param getDetailDataRequest
     * @return
     */
    public gov.hhs.fha.nhinc.common.dda.GetDetailDataResponseType getDetailData(gov.hhs.fha.nhinc.common.dda.GetDetailDataRequestType request) {
        GetDetailDataForUserRequestType newRequest = new GetDetailDataForUserRequestType();
        newRequest.setDataSource(request.getDataSource());
        newRequest.setItemId(request.getItemId());
        newRequest.setUserId(null);
        return getDetailDataForUser(newRequest);
    }

    /**
     * Handle summary data sources.  This method may return partial results.  Always
     * check the error results.
     *
     * @param getSummaryDataRequest
     * @return
     */
    public gov.hhs.fha.nhinc.common.dda.GetSummaryDataResponseType getSummaryData(gov.hhs.fha.nhinc.common.dda.GetSummaryDataRequestType request) {
        GetSummaryDataForUserRequestType newRequest = new GetSummaryDataForUserRequestType();
        newRequest.setGroupId(request.getGroupId());
        newRequest.setLocationId(request.getLocationId());
        newRequest.setPatientId(request.getPatientId());
        newRequest.setProviderId(request.getProviderId());
        newRequest.setUserId(null);
        newRequest.setOnlyNew(false);
        return getSummaryDataForUser(newRequest);
    }

    /**
     * Handle detail data sources requested by a specified user.
     *
     * @param getDetailDataForUserRequest
     * @return
     */
    public gov.hhs.fha.nhinc.common.dda.GetDetailDataResponseType getDetailDataForUser(gov.hhs.fha.nhinc.common.dda.GetDetailDataForUserRequestType request) {
        GetDetailDataResponseType response  = new GetDetailDataResponseType();
        Hashtable<String,String> sourceTable = new Hashtable<String,String>();

        try {
            ServiceError dataSourceError = getDataSources(sourceTable);
            if (dataSourceError != null) {
                throw new Exception("Error retrieving data sources.");
            }

            if (sourceTable.size() == 0) {
                throw new Exception("No data sources defined.");
            }

            //Get data source url
            String dataSourceUrl = sourceTable.get(request.getDataSource());
            if (dataSourceUrl == null) {
                throw new Exception("Requested data source is not configured: " + request.getDataSource());
            }

            //Convert userId
            String userId = request.getUserId();
            try {
                UniversalResourceAddressBean beanId = UniversalResourceAddressBeanFactory.getInstance().createUniversalResourceBean(request.getUserId());
                if (beanId instanceof IdAddressBean) {
                    userId = ((IdAddressBean) beanId).getId();
                }
            }
            catch (Exception e) {
                log.warn("Bad user id: " + userId + ", " + e.getMessage());
            }

            //Call destination
            gov.hhs.fha.nhinc.aggregator.DisplayDataComponent service = new gov.hhs.fha.nhinc.aggregator.DisplayDataComponent();
            gov.hhs.fha.nhinc.aggregator.DisplayDataComponentPortType port = service.getDisplayDataComponentPortSoap11();
            ((javax.xml.ws.BindingProvider) port).getRequestContext().put(
                javax.xml.ws.BindingProvider.ENDPOINT_ADDRESS_PROPERTY,
                dataSourceUrl);
            GetComponentDetailDataForUserRequestType componentRequest = new GetComponentDetailDataForUserRequestType();
            componentRequest.setUserId(userId);
            componentRequest.setItemId(request.getItemId());
            GetComponentDetailDataResponseType componentResponse = port.getComponentDetailDataForUser(componentRequest);
            response.setDetailObject(componentResponse.getDetailObject());
            response.getErrorList().addAll(componentResponse.getErrorList());
        }
        catch (Exception e) {
            String errorMsg = "Error getting detail data.";
            log.error(errorMsg, e);
            ServiceError error = new ServiceError();
            error.setCode(ERROR_CODE);
            error.setText(errorMsg + ". " + e.getMessage());
            response.getErrorList().add(error);
        }

        //Get "read" status as needed
        ServiceError error = addReadStatus(request.getUserId(), response.getDetailObject());
        if (error != null) {
            response.getErrorList().add(error);
        }

        return response;
    }

    public gov.hhs.fha.nhinc.common.dda.GetSummaryDataResponseType getSummaryDataForUser(gov.hhs.fha.nhinc.common.dda.GetSummaryDataForUserRequestType request) {
        GetSummaryDataResponseType response  = new GetSummaryDataResponseType();
        Hashtable<String,String> sourceTable = new Hashtable<String,String>();

        try {
            ServiceError dataSourceError = getDataSources(sourceTable);
            if (dataSourceError != null) {
                throw new Exception("Error retrieving data sources.");
            }

            if (sourceTable.size() == 0) {
                throw new Exception("No data sources defined.");
            }

            //Convert userId
            String userId = request.getUserId();
            try {
                UniversalResourceAddressBean beanId = UniversalResourceAddressBeanFactory.getInstance().createUniversalResourceBean(request.getUserId());
                if (beanId instanceof IdAddressBean) {
                    userId = ((IdAddressBean) beanId).getId();
                }
            }
            catch (Exception e) {
                log.warn("Bad user id: " + userId + ", " + e.getMessage());
            }

            for (String dataSource : request.getDataSources()) {
                String dataSourceUrl = sourceTable.get(dataSource);
                if (dataSourceUrl == null) {
                    throw new Exception("Requested data source is not configured: " + dataSource);
                }

                try {
                    gov.hhs.fha.nhinc.aggregator.DisplayDataComponent service = new gov.hhs.fha.nhinc.aggregator.DisplayDataComponent();
                    gov.hhs.fha.nhinc.aggregator.DisplayDataComponentPortType port = service.getDisplayDataComponentPortSoap11();
                    ((javax.xml.ws.BindingProvider) port).getRequestContext().put(
                        javax.xml.ws.BindingProvider.ENDPOINT_ADDRESS_PROPERTY,
                        dataSourceUrl);
                    GetComponentSummaryDataForUserRequestType componentRequest = new GetComponentSummaryDataForUserRequestType();
                    componentRequest.setUserId(userId);
                    componentRequest.setGroupId(request.getGroupId());
                    componentRequest.setLocationId(request.getLocationId());
                    componentRequest.setPatientId(request.getPatientId());
                    componentRequest.setProviderId(request.getProviderId());
                    componentRequest.setOnlyNew(request.isOnlyNew());
                    GetComponentSummaryDataResponseType componentResponse = port.getComponentSummaryDataForUser(componentRequest);
                    response.getSummaryObjects().addAll(componentResponse.getSummaryObjects());
                }
                catch (Exception e) {
                    String errorMsg = "Error getting component summary data from: " + dataSource;
                    log.error(errorMsg, e);
                    ServiceError error = new ServiceError();
                    error.setCode(ERROR_CODE);
                    error.setText(errorMsg + ". " + e.getMessage());
                    response.getErrorList().add(error);
                }

            } //for

        }
        catch (Exception e) {
            String errorMsg = "Error getting summary data.";
            log.error(errorMsg, e);
            ServiceError error = new ServiceError();
            error.setCode(ERROR_CODE);
            error.setText(errorMsg + ". " + e.getMessage());
            response.getErrorList().add(error);
        }

        //Get "read" status as needed
        ServiceError error = addReadStatus(request.getUserId(), response.getSummaryObjects());
        if (error != null) {
            response.getErrorList().add(error);
        }

        return response;
    }

    /**
     * Read data sources from the property file.
     *
     * @param dataSources
     * @return
     */
    private ServiceError getDataSources(Hashtable<String,String> dataSources) {
        ServiceError response = null;

        try {
            Long dataSourceCount = PropertyAccessor.getPropertyLong(PROPERTY_FILE, DATA_SOURCE_COUNT);

            if (dataSourceCount == null) {
                throw new PropertyAccessException("Property was null: " + DATA_SOURCE_COUNT);
            }

            for (int i = 1; dataSources.size() < dataSourceCount; i++) {
                String dataSourceName = PropertyAccessor.getProperty(PROPERTY_FILE, DATA_SOURCE_PREFIX + i + DATA_SOURCE_NAME);
                String dataSourceUrl = PropertyAccessor.getProperty(PROPERTY_FILE, DATA_SOURCE_PREFIX + i + DATA_SOURCE_URL);

                if (dataSourceName != null) {
                    dataSources.put(dataSourceName, dataSourceUrl);
                }

                if (i > MAX_DATA_SOURCES) {
                    break;
                }
            }

        }
        catch (PropertyAccessException e) {
            String errorMsg = "Error accessing properties in file:" + PROPERTY_FILE;
            log.error(errorMsg, e);
            response = new ServiceError();
            response.setCode(ERROR_CODE);
            response.setText(errorMsg + ". " + e.getMessage());
        }

        return response;
    }

    /**
     * Check InboxStatus and add to summary response if necessary.
     */
    private ServiceError addReadStatus(String user, List<SummaryData> summaryList) {
        ServiceError response = null;

        if (summaryList == null) {
            return null;
        }

        Iterator<SummaryData> iterator = summaryList.iterator();
        while (iterator.hasNext()) {
            SummaryData item = iterator.next();

            if (STATUS_NHIN_DOCS.equals(item.getDataSource())) {
                boolean readStatus = false;

                try {
                    readStatus = getReadStatusForItem(user, item.getDataSource(), item.getItemId());
                }
                catch (Exception e) {
                    String errorMsg = "Error accessing read status for user:" + user;
                    log.error(errorMsg, e);
                    response = new ServiceError();
                    response.setCode(ERROR_CODE);
                    response.setText(errorMsg + ". " + e.getMessage());
                }

                addNameValue(item.getItemValues(), ITEM_READ_STATUS, readStatus);
            }
        }

        return response;
    }

    /**
     * Check InboxStatus and add to detail response if necessary.
     */
    private ServiceError addReadStatus(String user, DetailData item) {
        ServiceError response = null;

        if (item == null) {
            return null;
        }

        if (STATUS_NHIN_DOCS.equals(item.getDataSource())) {
            boolean readStatus = false;

            try {
                readStatus = getReadStatusForItem(user, item.getDataSource(), item.getItemId());
            }
            catch (Exception e) {
                String errorMsg = "Error accessing read status for user:" + user;
                log.error(errorMsg, e);
                response = new ServiceError();
                response.setCode(ERROR_CODE);
                response.setText(errorMsg + ". " + e.getMessage());
            }

            addNameValue(item.getItemValues(), ITEM_READ_STATUS, readStatus);
        }

        return response;
    }

    /**
     * Retrieve InboxStatus
     */
    private boolean getReadStatusForItem(String user, String source, String item)
            throws DisplayStatusException {
        boolean response = false;

        DisplayStatusService service = new DisplayStatusService();

        InboxStatusQueryParams params = new InboxStatusQueryParams();
        params.setSource(source);
        params.setUser(user);
        params.setItem(item);

        List<InboxStatus> statusList = service.inboxStatusQuery(params);
        if ((statusList != null) && (statusList.size() > 0)) {
            response = statusList.get(0).isRead();
        }

        return response;
    }

    /**
     * Add name/value pair to response.
     *
     * @param pairList
     * @param name
     * @param value
     */
    private void addNameValue(List<NameValuesPair> pairList, String name, Object value) {
        NameValuesPair nameVal = new NameValuesPair();
        nameVal.setName(name);
        nameVal.getValues().add(String.valueOf(value));
        pairList.add(nameVal);

        return;
    }

}
