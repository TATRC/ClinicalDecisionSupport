/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gov.hhs.fha.nhinc.displaydocdata;

import gov.hhs.fha.nhinc.common.dda.DetailData;
import gov.hhs.fha.nhinc.common.dda.GetComponentDetailDataForUserRequestType;
import gov.hhs.fha.nhinc.common.dda.GetDataSourceNameRequestType;
import gov.hhs.fha.nhinc.common.dda.GetDataSourceNameResponseType;
import gov.hhs.fha.nhinc.common.dda.GetComponentDetailDataResponseType;
import gov.hhs.fha.nhinc.common.dda.GetComponentSummaryDataForUserRequestType;
import gov.hhs.fha.nhinc.common.dda.GetComponentSummaryDataResponseType;
import gov.hhs.fha.nhinc.common.dda.UpdateComponentInboxStatusRequestType;
import gov.hhs.fha.nhinc.common.dda.UpdateComponentInboxStatusResponseType;
import gov.hhs.fha.nhinc.common.dda.NameValuesPair;
import gov.hhs.fha.nhinc.common.dda.ServiceError;
import gov.hhs.fha.nhinc.common.dda.SummaryData;
import gov.hhs.fha.nhinc.properties.PropertyAccessor;
import ihe.iti.xds_b._2007.RetrieveDocumentSetRequestType.DocumentRequest;
import ihe.iti.xds_b._2007.RetrieveDocumentSetResponseType.DocumentResponse;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import javax.xml.bind.JAXBElement;
import javax.xml.datatype.DatatypeFactory;
import oasis.names.tc.ebxml_regrep.xsd.query._3.ResponseOptionType;
import oasis.names.tc.ebxml_regrep.xsd.rim._3.AdhocQueryType;
import oasis.names.tc.ebxml_regrep.xsd.rim._3.ClassificationType;
import oasis.names.tc.ebxml_regrep.xsd.rim._3.ExternalIdentifierType;
import oasis.names.tc.ebxml_regrep.xsd.rim._3.ExtrinsicObjectType;
import oasis.names.tc.ebxml_regrep.xsd.rim._3.IdentifiableType;
import oasis.names.tc.ebxml_regrep.xsd.rim._3.SlotType1;
import oasis.names.tc.ebxml_regrep.xsd.rim._3.ValueListType;
import oasis.names.tc.ebxml_regrep.xsd.rs._3.RegistryError;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Retrieves NHIN Documents for aggregator display.
 *
 * @author cmatser
 */
public class DisplayDocDataImpl {

    /** Property constants. */
    public static final String PROPERTY_FILE = "displayAggregator";
    public static final String DOCUMENT_MANAGER_URL = "documentManager.url";

    /** Data Source name. */
    public static final String DATA_SOURCE = "NHIN Documents";

    /** Item names for name value pairs. */
    public static final String ITEM_DOCUMENT_NAME = "Name";
    public static final String ITEM_MIME_TYPE = "MIME Type";
    public static final String ITEM_DOCUMENT_SIZE = "Size";
    public static final String ITEM_INSTITUTION = "Author Institution";
    public static final String ITEM_CREATION_TIME = "Creation Time";
    public static final String ITEM_LANGUAGE_CODE = "Language Code";
    public static final String ITEM_SERVICE_START = "Service Start Time";
    public static final String ITEM_SERVICE_STOP = "Service Stop Time";
    public static final String ITEM_PATIENT_ADDRESS = "Patient Address";
    public static final String ITEM_PATIENT_GENDER = "Patient Gender";
    public static final String ITEM_PATIENT_DOB = "Patient DOB";
    public static final String ITEM_REPOSITORY_ID = "Repository ID";
    public static final String ITEM_ACCESSED = "Accessed";

    /** XDS ids */
    public static final String XDS_FINDDOC_QUERY = "urn:uuid:14d4debf-8f97-4251-9a74-a90016b0af0d";
    public static final String XDS_GETDOC_QUERY = "urn:uuid:5c4f972b-d56b-40ac-a5fc-c8ca9b40b9d4";
    public static final String XDS_QUERY_PATIENT_ID = "$XDSDocumentEntryPatientId";
    public static final String XDS_QUERY_ENTRY_STATUS = "$XDSDocumentEntryStatus";
    public static final String XDS_QUERY_APPROVED_STATUS = "('urn:oasis:names:tc:ebxml-regrep:StatusType:Approved')";
    public static final String XDS_QUERY_DOCUMENT_ID = "$XDSDocumentEntryUniqueId";
    public static final String XDS_RETURN_TYPE = "LeafClass";
    public static final String XDS_CUSTOM_METADATA_PREFIX = "urn:";
    public static final String XDS_CREATION_TIME = "creationTime";
    public static final String XDS_SERVICE_START = "serviceStartTime";
    public static final String XDS_SERVICE_STOP = "serviceStopTime";
    public static final String XDS_LANGUAGE_CODE = "languageCode";
    public static final String XDS_PATIENT_INFO = "sourcePatientInfo";
    public static final String XDS_DOC_SIZE = "size";
    public static final String XDS_REPOSITORY_ID = "repositoryUniqueId";
    public static final String XDS_PATIENT_NAME = "PID-5|";
    public static final String XDS_PATIENT_GENDER = "PID-8|";
    public static final String XDS_PATIENT_DOB = "PID-7|";
    public static final String XDS_PATIENT_ADDRESS = "PID-11|";
    public static final String XDS_DOC_ID = "XDSDocumentEntry.uniqueId";
    public static final String XDS_CLASS_AUTHOR = "urn:uuid:93606bcf-9494-43ec-9b4e-a7748d1a838d";
    public static final String XDS_SLOT_AUTHOR = "authorPerson";
    public static final String XDS_SLOT_INSTITUTION = "authorInstitution";
    public static final String XDS_HAS_BEEN_ACCESSED = "urn:gov:hhs:fha:nhinc:xds:hasBeenAccessed";

    /** Date format for update records. */
    public static final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SZ");
    public static final DateFormat xdsDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");

    /** Standard error code. */
    public static final Integer ERR_CODE = -1;

    /** Service error message. */
    public static final String ERR_MSG_ITEM_NOT_FOUND = "Item was not found.";

    /** Logging. */
    private static Log log = LogFactory.getLog(DisplayDocDataImpl.class);

    /**
     * Retrieve data source name.
     * 
     * @param getDataSourceNameRequest
     * @return
     */
    public GetDataSourceNameResponseType getDataSourceName(GetDataSourceNameRequestType getDataSourceNameRequest) {
        GetDataSourceNameResponseType response = new GetDataSourceNameResponseType();
        response.setReturn(DATA_SOURCE);

        return response;
    }

    /**
     * Update Inbox status not supported.
     * 
     * @param updateComponentInboxStatusRequest
     * @return
     */
    public UpdateComponentInboxStatusResponseType updateComponentInboxStatus(UpdateComponentInboxStatusRequestType updateComponentInboxStatusRequest) {
        throw new UnsupportedOperationException("Not implemented yet.");
    }

    /**
     * Retrieve detail data.  This method make 2 calls to the XDS server.  The
     * first retrieves the metadata info, while the second retrieves the actual
     * document.
     *
     * @param request
     * @return
     */
    public GetComponentDetailDataResponseType getComponentDetailDataForUser(GetComponentDetailDataForUserRequestType request) {
        GetComponentDetailDataResponseType response = new GetComponentDetailDataResponseType();

        log.debug("Retrieving NHIN Document detail.");

        try {
            //Retrieve based on item id
            ihe.iti.xds_b._2007.DocumentManagerService service = new ihe.iti.xds_b._2007.DocumentManagerService();
            ihe.iti.xds_b._2007.DocumentManagerPortType port = service.getDocumentManagerPortSoap();
            ((javax.xml.ws.BindingProvider) port).getRequestContext().put(
                javax.xml.ws.BindingProvider.ENDPOINT_ADDRESS_PROPERTY,
                PropertyAccessor.getProperty(PROPERTY_FILE, DOCUMENT_MANAGER_URL));

            //Create metadata request
            oasis.names.tc.ebxml_regrep.xsd.query._3.AdhocQueryRequest metaQueryRequest = new oasis.names.tc.ebxml_regrep.xsd.query._3.AdhocQueryRequest();

            AdhocQueryType query = new AdhocQueryType();
            query.setId(XDS_GETDOC_QUERY);

            SlotType1 slot = new SlotType1();
            slot.setName(XDS_QUERY_DOCUMENT_ID);
            ValueListType valList = new ValueListType();
            valList.getValue().add("'" + request.getItemId() + "'");
            slot.setValueList(valList);
            query.getSlot().add(slot);

            metaQueryRequest.setAdhocQuery(query);

            ResponseOptionType option = new ResponseOptionType();
            option.setReturnComposedObjects(true);
            option.setReturnType(XDS_RETURN_TYPE);
            metaQueryRequest.setResponseOption(option);

            //Parse metadata result
            oasis.names.tc.ebxml_regrep.xsd.query._3.AdhocQueryResponse result = port.documentManagerQueryInboundRepository(metaQueryRequest);
            response = parseMetadataDetailResponse(result);

            //Pull out repository id for query
            String repositoryId = null;
            for (NameValuesPair pair : response.getDetailObject().getItemValues()) {
                if (ITEM_REPOSITORY_ID.equals(pair.getName())) {
                    repositoryId = pair.getValues().get(0);
                }
            }

            //Create document query
            ihe.iti.xds_b._2007.RetrieveDocumentSetRequestType docQueryRequest = new ihe.iti.xds_b._2007.RetrieveDocumentSetRequestType();

            DocumentRequest retrieve = new DocumentRequest();
            retrieve.setDocumentUniqueId(request.getItemId());
            retrieve.setRepositoryUniqueId(repositoryId);

            docQueryRequest.getDocumentRequest().add(retrieve);

            //Parse document result
            ihe.iti.xds_b._2007.RetrieveDocumentSetResponseType docResult = port.documentManagerRetrieveInboundDocument(docQueryRequest);

            //Check for errors
            if ((docResult.getRegistryResponse() != null) && (docResult.getRegistryResponse().getRegistryErrorList() != null)) {
                List<RegistryError> errorList = docResult.getRegistryResponse().getRegistryErrorList().getRegistryError();
                for (RegistryError error : errorList) {
                    ServiceError serviceError = new ServiceError();
                    serviceError.setCode(ERR_CODE);
                    serviceError.setText(error.getErrorCode() + ": " + error.getValue());
                    response.getErrorList().add(serviceError);
                }
            }
            List<DocumentResponse> objectList = docResult.getDocumentResponse();
            log.debug("Found " + objectList.size() + " document found regarding item: " + request.getItemId());
            for (DocumentResponse object : objectList) {
                response.getDetailObject().setData(new String(object.getDocument()));
            } //end for result object list
        } //end try
        catch (Exception e) {
            log.error("Error retriving detail NHIN document: " + request.getItemId(), e);

            ServiceError serviceError = new ServiceError();
            serviceError.setCode(ERR_CODE);
            serviceError.setText(e.getMessage());
            response.getErrorList().add(serviceError);
        }

        return response;
    }

    /**
     * Retreive summary objects for all documents.
     *
     * @param request
     * @return
     */
    public GetComponentSummaryDataResponseType getComponentSummaryDataForUser(GetComponentSummaryDataForUserRequestType request) {
        GetComponentSummaryDataResponseType response = new GetComponentSummaryDataResponseType();

        log.debug("Retrieving NHIN document summaries for provider.");

        try {
            //If no patient is passed, return
            if ((request.getPatientId() == null) || request.getPatientId().isEmpty()) {
                return response;
            }

            //Query based on patient id
            ihe.iti.xds_b._2007.DocumentManagerService service = new ihe.iti.xds_b._2007.DocumentManagerService();
            ihe.iti.xds_b._2007.DocumentManagerPortType port = service.getDocumentManagerPortSoap();
            ((javax.xml.ws.BindingProvider) port).getRequestContext().put(
                javax.xml.ws.BindingProvider.ENDPOINT_ADDRESS_PROPERTY,
                PropertyAccessor.getProperty(PROPERTY_FILE, DOCUMENT_MANAGER_URL));

            //Create query
            oasis.names.tc.ebxml_regrep.xsd.query._3.AdhocQueryRequest body = new oasis.names.tc.ebxml_regrep.xsd.query._3.AdhocQueryRequest();

            AdhocQueryType query = new AdhocQueryType();
            query.setId(XDS_FINDDOC_QUERY);

            SlotType1 slot = new SlotType1();
            slot.setName(XDS_QUERY_PATIENT_ID);
            ValueListType valList = new ValueListType();
            //Format id for xds query
            valList.getValue().add("'" + request.getPatientId() + "^^^&" + request.getLocationId() + "&ISO'");
            slot.setValueList(valList);
            query.getSlot().add(slot);

            slot = new SlotType1();
            slot.setName(XDS_QUERY_ENTRY_STATUS);
            valList = new ValueListType();
            valList.getValue().add(XDS_QUERY_APPROVED_STATUS);
            slot.setValueList(valList);
            query.getSlot().add(slot);

            body.setAdhocQuery(query);

            ResponseOptionType option = new ResponseOptionType();
            option.setReturnComposedObjects(true);
            option.setReturnType(XDS_RETURN_TYPE);
            body.setResponseOption(option);

            //Parse result
            oasis.names.tc.ebxml_regrep.xsd.query._3.AdhocQueryResponse result = port.documentManagerQueryInboundRepository(body);
            response = parseMetadataSummaryResponse(result, request.isOnlyNew());
        } //end try
        catch (Exception e) {
            log.error("Error retriving summary NHIN documents on patient: " + request.getPatientId(), e);

            ServiceError serviceError = new ServiceError();
            serviceError.setCode(ERR_CODE);
            serviceError.setText(e.getMessage());
            response.getErrorList().add(serviceError);
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
    private void addNameValue(List<NameValuesPair> pairList, String name, String value) {
        NameValuesPair nameVal = new NameValuesPair();
        nameVal.setName(name);
        nameVal.getValues().add(value);
        pairList.add(nameVal);

        return;
    }
    
    /**
     * Parse metadata query result for summary return.
     * 
     * @param result summary info
     * @return
     */
    private GetComponentSummaryDataResponseType parseMetadataSummaryResponse(oasis.names.tc.ebxml_regrep.xsd.query._3.AdhocQueryResponse result, boolean onlyNew) {

        GetComponentSummaryDataResponseType response = new GetComponentSummaryDataResponseType();

        try {
            //Check for errors
            if (result.getRegistryErrorList() != null) {
                List<RegistryError> errorList = result.getRegistryErrorList().getRegistryError();
                for (RegistryError error : errorList) {
                    ServiceError serviceError = new ServiceError();
                    serviceError.setCode(ERR_CODE);
                    serviceError.setText(error.getErrorCode() + ": " + error.getValue());
                    response.getErrorList().add(serviceError);
                }
            }
            List<JAXBElement<? extends IdentifiableType>> objectList = result.getRegistryObjectList().getIdentifiable();
            log.debug("Found metadata for documents: " + objectList.size());
            for (JAXBElement<? extends IdentifiableType> object : objectList) {
                IdentifiableType identifiableType = object.getValue();
                if (identifiableType instanceof ExtrinsicObjectType) {
                    //Poplulate summary data object
                    GregorianCalendar cal = new GregorianCalendar();
                    boolean hasBeenAccessed = false;
                    StringBuilder from = new StringBuilder();
                    SummaryData summaryData = new SummaryData();
                    summaryData.setDataSource(DATA_SOURCE);
                    ExtrinsicObjectType extrinsic = (ExtrinsicObjectType) identifiableType;
                    summaryData.setDescription(extrinsic.getDescription().getLocalizedString().get(0).getValue());
                    addNameValue(summaryData.getItemValues(), ITEM_DOCUMENT_NAME, extrinsic.getName().getLocalizedString().get(0).getValue());
                    addNameValue(summaryData.getItemValues(), ITEM_MIME_TYPE, extrinsic.getMimeType());
                    for (SlotType1 metaSlot : extrinsic.getSlot()) {
                        if (XDS_CREATION_TIME.equals(metaSlot.getName())) {
                            try {
                                cal.setTime(parseXDSDate(metaSlot.getValueList().getValue().get(0)));
                                summaryData.setDateCreated(
                                   DatatypeFactory.newInstance().newXMLGregorianCalendar(cal));
                            }
                            catch (Exception pe) {
                                String msg = "Error parsing: " + XDS_CREATION_TIME;
                                log.error(msg, pe);

                                ServiceError serviceError = new ServiceError();
                                serviceError.setCode(ERR_CODE);
                                serviceError.setText(msg + ". " + pe.getMessage());
                                response.getErrorList().add(serviceError);
                            }
                        }
                        if (XDS_PATIENT_INFO.equals(metaSlot.getName())) {
                            //Find patient name
                            for (String ptVal : metaSlot.getValueList().getValue()) {
                                //Patient name is PID-5
                                if (ptVal.startsWith(XDS_PATIENT_NAME)) {
                                    summaryData.setPatient(ptVal.substring(XDS_PATIENT_NAME.length()));
                                }
                            }
                        }
                        if (XDS_DOC_SIZE.equals(metaSlot.getName())) {
                            addNameValue(summaryData.getItemValues(), ITEM_DOCUMENT_SIZE, metaSlot.getValueList().getValue().get(0));
                        }
                        if (XDS_HAS_BEEN_ACCESSED.equals(metaSlot.getName())) {
                            try {
                                addNameValue(summaryData.getItemValues(), ITEM_ACCESSED, dateFormat.format(parseXDSDate(metaSlot.getValueList().getValue().get(0))));
                                //If we get here, we have a valid date that the itme was accessed
                                hasBeenAccessed = true;
                            }
                            catch (Exception e) {
                                addNameValue(summaryData.getItemValues(), ITEM_ACCESSED, metaSlot.getValueList().getValue().get(0));
                            }
                        }
                    } //end for meta slots
                    for (ExternalIdentifierType identifier : extrinsic.getExternalIdentifier()) {
                        if (XDS_DOC_ID.equals(identifier.getName().getLocalizedString().get(0).getValue())) {
                            summaryData.setItemId(identifier.getValue());
                        }
                    }
                    for (ClassificationType classification : extrinsic.getClassification()) {
                        if (XDS_CLASS_AUTHOR.equals(classification.getClassificationScheme())) {
                            for (SlotType1 authorSlot : classification.getSlot()) {
                                if (XDS_SLOT_AUTHOR.equals(authorSlot.getName())) {
                                    summaryData.setAuthor(authorSlot.getValueList().getValue().get(0));
                                }
                            }
                            for (SlotType1 authorSlot : classification.getSlot()) {
                                if (XDS_SLOT_INSTITUTION.equals(authorSlot.getName())) {
                                    String institution = authorSlot.getValueList().getValue().get(0);
                                    addNameValue(summaryData.getItemValues(), ITEM_INSTITUTION, institution);
                                    if ((institution != null) && !institution.isEmpty()) {
                                        from.append(institution);
                                        from.append(" - ");
                                    }
                                }
                            }
                        }
                    }

                    //Set From field as combo of author and institution
                    from.append(summaryData.getAuthor());
                    summaryData.setFrom(from.toString());
                       
                    //Check if this document has been accessed,
                    //  skip if option for onlyNew was selected
                    if (onlyNew && hasBeenAccessed) {
                        continue;
                    }

                    response.getSummaryObjects().add(summaryData);
                } //end if extrinisc object
            } //end for result object list
        }
        catch (Exception e) {
            log.error("Error parsing metadata result.", e);

            ServiceError serviceError = new ServiceError();
            serviceError.setCode(ERR_CODE);
            serviceError.setText(e.getMessage());
            response.getErrorList().add(serviceError);
        }
                  
        return response;
    }

    /**
     * Parse metadata query result for detail return.
     *
     * @param result detail info
     * @return
     */
    private GetComponentDetailDataResponseType parseMetadataDetailResponse(oasis.names.tc.ebxml_regrep.xsd.query._3.AdhocQueryResponse result) {

        GetComponentDetailDataResponseType response = new GetComponentDetailDataResponseType();

        try {
            //Check for errors
            if (result.getRegistryErrorList() != null) {
                List<RegistryError> errorList = result.getRegistryErrorList().getRegistryError();
                for (RegistryError error : errorList) {
                    ServiceError serviceError = new ServiceError();
                    serviceError.setCode(ERR_CODE);
                    serviceError.setText(error.getErrorCode() + ": " + error.getValue());
                    response.getErrorList().add(serviceError);
                }
            }
            List<JAXBElement<? extends IdentifiableType>> objectList = result.getRegistryObjectList().getIdentifiable();
            log.debug("Found metadata for documents: " + objectList.size());
            for (JAXBElement<? extends IdentifiableType> object : objectList) {
                IdentifiableType identifiableType = object.getValue();
                if (identifiableType instanceof ExtrinsicObjectType) {
                    //Poplulate detail data object
                    GregorianCalendar cal = new GregorianCalendar();
                    StringBuilder from = new StringBuilder();
                    DetailData detailData = new DetailData();
                    detailData.setDataSource(DATA_SOURCE);
                    ExtrinsicObjectType extrinsic = (ExtrinsicObjectType) identifiableType;
                    detailData.setDescription(extrinsic.getDescription().getLocalizedString().get(0).getValue());
                    addNameValue(detailData.getItemValues(), ITEM_DOCUMENT_NAME, extrinsic.getName().getLocalizedString().get(0).getValue());
                    addNameValue(detailData.getItemValues(), ITEM_MIME_TYPE, extrinsic.getMimeType());
                    for (SlotType1 metaSlot : extrinsic.getSlot()) {
                        if (XDS_CREATION_TIME.equals(metaSlot.getName())) {
                            try {
                                cal.setTime(parseXDSDate(metaSlot.getValueList().getValue().get(0)));
                                detailData.setDateCreated(
                                    DatatypeFactory.newInstance().newXMLGregorianCalendar(cal));
                            }
                            catch (Exception pe) {
                                String msg = "Error parsing: " + XDS_CREATION_TIME;
                                log.error(msg, pe);

                                ServiceError serviceError = new ServiceError();
                                serviceError.setCode(ERR_CODE);
                                serviceError.setText(msg + ". " + pe.getMessage());
                                response.getErrorList().add(serviceError);
                            }
                        }
                        if (XDS_SERVICE_START.equals(metaSlot.getName())) {
                            try {
                                addNameValue(detailData.getItemValues(), ITEM_SERVICE_START, dateFormat.format(parseXDSDate(metaSlot.getValueList().getValue().get(0))));
                            }
                            catch (Exception pe) {
                                String msg = "Error parsing: " + XDS_SERVICE_START;
                                log.error(msg, pe);

                                ServiceError serviceError = new ServiceError();
                                serviceError.setCode(ERR_CODE);
                                serviceError.setText(msg + ". " + pe.getMessage());
                                response.getErrorList().add(serviceError);
                            }
                        }
                        if (XDS_SERVICE_STOP.equals(metaSlot.getName())) {
                            try {
                                addNameValue(detailData.getItemValues(), ITEM_SERVICE_STOP, dateFormat.format(parseXDSDate(metaSlot.getValueList().getValue().get(0))));
                            }
                            catch (Exception pe) {
                                String msg = "Error parsing: " + XDS_SERVICE_STOP;
                                log.error(msg, pe);

                                ServiceError serviceError = new ServiceError();
                                serviceError.setCode(ERR_CODE);
                                serviceError.setText(msg + ". " + pe.getMessage());
                                response.getErrorList().add(serviceError);
                            }
                        }
                        if (XDS_PATIENT_INFO.equals(metaSlot.getName())) {
                            //Find patient name
                            for (String ptVal : metaSlot.getValueList().getValue()) {
                                //Patient name is PID-5
                                if (ptVal.startsWith(XDS_PATIENT_NAME)) {
                                    detailData.setPatient(ptVal.substring(XDS_PATIENT_NAME.length()));
                                }
                                //Patient gender is PID-8
                                if (ptVal.startsWith(XDS_PATIENT_GENDER)) {
                                    addNameValue(detailData.getItemValues(), ITEM_PATIENT_GENDER, ptVal.substring(XDS_PATIENT_GENDER.length()));
                                }
                                //Patient dob is PID-7
                                if (ptVal.startsWith(XDS_PATIENT_DOB)) {
                                    addNameValue(detailData.getItemValues(), ITEM_PATIENT_DOB, ptVal.substring(XDS_PATIENT_DOB.length()));
                                }
                                //Patient address is PID-11
                                if (ptVal.startsWith(XDS_PATIENT_ADDRESS)) {
                                    addNameValue(detailData.getItemValues(), ITEM_PATIENT_ADDRESS, ptVal.substring(XDS_PATIENT_ADDRESS.length()));
                                }
                            }
                        }
                        if (XDS_DOC_SIZE.equals(metaSlot.getName())) {
                            addNameValue(detailData.getItemValues(), ITEM_DOCUMENT_SIZE, metaSlot.getValueList().getValue().get(0));
                        }
                        if (XDS_LANGUAGE_CODE.equals(metaSlot.getName())) {
                            addNameValue(detailData.getItemValues(), ITEM_LANGUAGE_CODE, metaSlot.getValueList().getValue().get(0));
                        }
                        if (XDS_REPOSITORY_ID.equals(metaSlot.getName())) {
                            addNameValue(detailData.getItemValues(), ITEM_REPOSITORY_ID, metaSlot.getValueList().getValue().get(0));
                        }
                        if (XDS_HAS_BEEN_ACCESSED.equals(metaSlot.getName())) {
                            try {
                                addNameValue(detailData.getItemValues(), ITEM_ACCESSED, dateFormat.format(parseXDSDate(metaSlot.getValueList().getValue().get(0))));
                            }
                            catch (Exception e) {
                                addNameValue(detailData.getItemValues(), ITEM_ACCESSED, metaSlot.getValueList().getValue().get(0));
                            }
                        }
                        if (metaSlot.getName().startsWith(XDS_CUSTOM_METADATA_PREFIX)) {
                            addNameValue(detailData.getItemValues(), metaSlot.getName(), metaSlot.getValueList().getValue().get(0));
                        }
                    } //end for meta slots
                    for (ExternalIdentifierType identifier : extrinsic.getExternalIdentifier()) {
                        if (XDS_DOC_ID.equals(identifier.getName().getLocalizedString().get(0).getValue())) {
                            detailData.setItemId(identifier.getValue());
                        }
                    }
                    for (ClassificationType classification : extrinsic.getClassification()) {
                        if (XDS_CLASS_AUTHOR.equals(classification.getClassificationScheme())) {
                            for (SlotType1 authorSlot : classification.getSlot()) {
                                if (XDS_SLOT_AUTHOR.equals(authorSlot.getName())) {
                                    detailData.setAuthor(authorSlot.getValueList().getValue().get(0));
                                }
                            }
                            for (SlotType1 authorSlot : classification.getSlot()) {
                                if (XDS_SLOT_INSTITUTION.equals(authorSlot.getName())) {
                                    String institution = authorSlot.getValueList().getValue().get(0);
                                    addNameValue(detailData.getItemValues(), ITEM_INSTITUTION, institution);
                                    if ((institution != null) && !institution.isEmpty()) {
                                        from.append(institution);
                                        from.append(" - ");
                                    }
                                }
                            }
                        }
                    }

                    //Set From field as combo of author and institution
                    from.append(detailData.getAuthor());
                    detailData.setFrom(from.toString());

                    response.setDetailObject(detailData);
                } //end if extrinisc object

                break;
            } //end for result object list
        }
        catch (Exception e) {
            log.error("Error parsing metadata result.", e);

            ServiceError serviceError = new ServiceError();
            serviceError.setCode(ERR_CODE);
            serviceError.setText(e.getMessage());
            response.getErrorList().add(serviceError);
        }

        return response;
    }

    private Date parseXDSDate(String dateStr)
            throws ParseException {

        //Often, date consists of just year,month,day.  So, we
        //  pad the time in order for the parse to succeed
        if (dateStr.length() == 8) {
            dateStr = dateStr + "00000000";
        }

        return xdsDateFormat.parse(dateStr);
    }
}