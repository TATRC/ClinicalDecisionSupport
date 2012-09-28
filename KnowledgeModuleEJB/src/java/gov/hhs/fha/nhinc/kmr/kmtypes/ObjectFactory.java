
package gov.hhs.fha.nhinc.kmr.kmtypes;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the gov.hhs.fha.nhinc.kmr.kmtypes package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _GetKmLatestLogicRequest_QNAME = new QName("urn:gov:hhs:fha:nhinc:kmr:kmtypes", "GetKmLatestLogicRequest");
    private final static QName _FindKmIdsRequest_QNAME = new QName("urn:gov:hhs:fha:nhinc:kmr:kmtypes", "FindKmIdsRequest");
    private final static QName _KmRuntimeRecordRequest_QNAME = new QName("urn:gov:hhs:fha:nhinc:kmr:kmtypes", "KmRuntimeRecordRequest");
    private final static QName _GetKmLatestLogicResponse_QNAME = new QName("urn:gov:hhs:fha:nhinc:kmr:kmtypes", "GetKmLatestLogicResponse");
    private final static QName _KmIdSearchRequest_QNAME = new QName("urn:gov:hhs:fha:nhinc:kmr:kmtypes", "KmIdSearchRequest");
    private final static QName _GetRefDataResponse_QNAME = new QName("urn:gov:hhs:fha:nhinc:kmr:kmtypes", "GetRefDataResponse");
    private final static QName _KmRuntimeRecordResponse_QNAME = new QName("urn:gov:hhs:fha:nhinc:kmr:kmtypes", "KmRuntimeRecordResponse");
    private final static QName _GetKmMetaResponse_QNAME = new QName("urn:gov:hhs:fha:nhinc:kmr:kmtypes", "GetKmMetaResponse");
    private final static QName _KmInsertAckResponse_QNAME = new QName("urn:gov:hhs:fha:nhinc:kmr:kmtypes", "KmInsertAckResponse");
    private final static QName _KmInsertRequest_QNAME = new QName("urn:gov:hhs:fha:nhinc:kmr:kmtypes", "KmInsertRequest");
    private final static QName _KmRecordResponse_QNAME = new QName("urn:gov:hhs:fha:nhinc:kmr:kmtypes", "KmRecordResponse");
    private final static QName _KmIdSearchResponse_QNAME = new QName("urn:gov:hhs:fha:nhinc:kmr:kmtypes", "KmIdSearchResponse");
    private final static QName _GetKmMetaRequest_QNAME = new QName("urn:gov:hhs:fha:nhinc:kmr:kmtypes", "GetKmMetaRequest");
    private final static QName _FindKmIdsResponse_QNAME = new QName("urn:gov:hhs:fha:nhinc:kmr:kmtypes", "FindKmIdsResponse");
    private final static QName _KmUpdateRequest_QNAME = new QName("urn:gov:hhs:fha:nhinc:kmr:kmtypes", "KmUpdateRequest");
    private final static QName _KmRecordRequest_QNAME = new QName("urn:gov:hhs:fha:nhinc:kmr:kmtypes", "KmRecordRequest");
    private final static QName _KmUpdateAckResponse_QNAME = new QName("urn:gov:hhs:fha:nhinc:kmr:kmtypes", "KmUpdateAckResponse");
    private final static QName _GetRefDataRequest_QNAME = new QName("urn:gov:hhs:fha:nhinc:kmr:kmtypes", "GetRefDataRequest");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: gov.hhs.fha.nhinc.kmr.kmtypes
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link MultiParamsRequestType }
     * 
     */
    public MultiParamsRequestType createMultiParamsRequestType() {
        return new MultiParamsRequestType();
    }

    /**
     * Create an instance of {@link KmByIdRequestListType }
     * 
     */
    public KmByIdRequestListType createKmByIdRequestListType() {
        return new KmByIdRequestListType();
    }

    /**
     * Create an instance of {@link ReferenceDataRefDataType }
     * 
     */
    public ReferenceDataRefDataType createReferenceDataRefDataType() {
        return new ReferenceDataRefDataType();
    }

    /**
     * Create an instance of {@link KMSpecialtyType }
     * 
     */
    public KMSpecialtyType createKMSpecialtyType() {
        return new KMSpecialtyType();
    }

    /**
     * Create an instance of {@link KMVQualityAssessmentType }
     * 
     */
    public KMVQualityAssessmentType createKMVQualityAssessmentType() {
        return new KMVQualityAssessmentType();
    }

    /**
     * Create an instance of {@link FindKmIdsResponseType }
     * 
     */
    public FindKmIdsResponseType createFindKmIdsResponseType() {
        return new FindKmIdsResponseType();
    }

    /**
     * Create an instance of {@link ReferenceDataLookupRefTypeSystem }
     * 
     */
    public ReferenceDataLookupRefTypeSystem createReferenceDataLookupRefTypeSystem() {
        return new ReferenceDataLookupRefTypeSystem();
    }

    /**
     * Create an instance of {@link PopulationDependencyListType }
     * 
     */
    public PopulationDependencyListType createPopulationDependencyListType() {
        return new PopulationDependencyListType();
    }

    /**
     * Create an instance of {@link KmIdSearchRequestType }
     * 
     */
    public KmIdSearchRequestType createKmIdSearchRequestType() {
        return new KmIdSearchRequestType();
    }

    /**
     * Create an instance of {@link RequestRefType }
     * 
     */
    public RequestRefType createRequestRefType() {
        return new RequestRefType();
    }

    /**
     * Create an instance of {@link KmLatestLogicResponseListType }
     * 
     */
    public KmLatestLogicResponseListType createKmLatestLogicResponseListType() {
        return new KmLatestLogicResponseListType();
    }

    /**
     * Create an instance of {@link UsageStatisticTypeType }
     * 
     */
    public UsageStatisticTypeType createUsageStatisticTypeType() {
        return new UsageStatisticTypeType();
    }

    /**
     * Create an instance of {@link ExportResponseListType }
     * 
     */
    public ExportResponseListType createExportResponseListType() {
        return new ExportResponseListType();
    }

    /**
     * Create an instance of {@link ACLSimpleType }
     * 
     */
    public ACLSimpleType createACLSimpleType() {
        return new ACLSimpleType();
    }

    /**
     * Create an instance of {@link KMVFactDependencySimpleType }
     * 
     */
    public KMVFactDependencySimpleType createKMVFactDependencySimpleType() {
        return new KMVFactDependencySimpleType();
    }

    /**
     * Create an instance of {@link KMVPopulationDependencySimpleType }
     * 
     */
    public KMVPopulationDependencySimpleType createKMVPopulationDependencySimpleType() {
        return new KMVPopulationDependencySimpleType();
    }

    /**
     * Create an instance of {@link FactSpecificationType }
     * 
     */
    public FactSpecificationType createFactSpecificationType() {
        return new FactSpecificationType();
    }

    /**
     * Create an instance of {@link KmsListType }
     * 
     */
    public KmsListType createKmsListType() {
        return new KmsListType();
    }

    /**
     * Create an instance of {@link KmLatestLogicResponse }
     * 
     */
    public KmLatestLogicResponse createKmLatestLogicResponse() {
        return new KmLatestLogicResponse();
    }

    /**
     * Create an instance of {@link KMVersionType }
     * 
     */
    public KMVersionType createKMVersionType() {
        return new KMVersionType();
    }

    /**
     * Create an instance of {@link KmFullResponse }
     * 
     */
    public KmFullResponse createKmFullResponse() {
        return new KmFullResponse();
    }

    /**
     * Create an instance of {@link ImportRequestType }
     * 
     */
    public ImportRequestType createImportRequestType() {
        return new ImportRequestType();
    }

    /**
     * Create an instance of {@link KmMetaResponse }
     * 
     */
    public KmMetaResponse createKmMetaResponse() {
        return new KmMetaResponse();
    }

    /**
     * Create an instance of {@link ImportAckType }
     * 
     */
    public ImportAckType createImportAckType() {
        return new ImportAckType();
    }

    /**
     * Create an instance of {@link KMVInferenceEngineDependencyType }
     * 
     */
    public KMVInferenceEngineDependencyType createKMVInferenceEngineDependencyType() {
        return new KMVInferenceEngineDependencyType();
    }

    /**
     * Create an instance of {@link KmByParamsType }
     * 
     */
    public KmByParamsType createKmByParamsType() {
        return new KmByParamsType();
    }

    /**
     * Create an instance of {@link KmMetaResponseType }
     * 
     */
    public KmMetaResponseType createKmMetaResponseType() {
        return new KmMetaResponseType();
    }

    /**
     * Create an instance of {@link KMVTaskDependencyType }
     * 
     */
    public KMVTaskDependencyType createKMVTaskDependencyType() {
        return new KMVTaskDependencyType();
    }

    /**
     * Create an instance of {@link ACLPermissionListType }
     * 
     */
    public ACLPermissionListType createACLPermissionListType() {
        return new ACLPermissionListType();
    }

    /**
     * Create an instance of {@link ResponseListType }
     * 
     */
    public ResponseListType createResponseListType() {
        return new ResponseListType();
    }

    /**
     * Create an instance of {@link KMVInferenceEngineDependencySimpleType }
     * 
     */
    public KMVInferenceEngineDependencySimpleType createKMVInferenceEngineDependencySimpleType() {
        return new KMVInferenceEngineDependencySimpleType();
    }

    /**
     * Create an instance of {@link OperationalConstraintType }
     * 
     */
    public OperationalConstraintType createOperationalConstraintType() {
        return new OperationalConstraintType();
    }

    /**
     * Create an instance of {@link KMVFactDependencyType }
     * 
     */
    public KMVFactDependencyType createKMVFactDependencyType() {
        return new KMVFactDependencyType();
    }

    /**
     * Create an instance of {@link KMSpecialtySimpleType }
     * 
     */
    public KMSpecialtySimpleType createKMSpecialtySimpleType() {
        return new KMSpecialtySimpleType();
    }

    /**
     * Create an instance of {@link KmImportRequestType }
     * 
     */
    public KmImportRequestType createKmImportRequestType() {
        return new KmImportRequestType();
    }

    /**
     * Create an instance of {@link KmLatestLogicResponseType }
     * 
     */
    public KmLatestLogicResponseType createKmLatestLogicResponseType() {
        return new KmLatestLogicResponseType();
    }

    /**
     * Create an instance of {@link KMVTaskDependencySimpleType }
     * 
     */
    public KMVTaskDependencySimpleType createKMVTaskDependencySimpleType() {
        return new KMVTaskDependencySimpleType();
    }

    /**
     * Create an instance of {@link OperationalConstraintElementType }
     * 
     */
    public OperationalConstraintElementType createOperationalConstraintElementType() {
        return new OperationalConstraintElementType();
    }

    /**
     * Create an instance of {@link KmResponseTypeRuntime }
     * 
     */
    public KmResponseTypeRuntime createKmResponseTypeRuntime() {
        return new KmResponseTypeRuntime();
    }

    /**
     * Create an instance of {@link InferenceListType }
     * 
     */
    public InferenceListType createInferenceListType() {
        return new InferenceListType();
    }

    /**
     * Create an instance of {@link FindKmIdsResponseListType }
     * 
     */
    public FindKmIdsResponseListType createFindKmIdsResponseListType() {
        return new FindKmIdsResponseListType();
    }

    /**
     * Create an instance of {@link KMVPopulationDependencyType }
     * 
     */
    public KMVPopulationDependencyType createKMVPopulationDependencyType() {
        return new KMVPopulationDependencyType();
    }

    /**
     * Create an instance of {@link KMVSupportingReferenceType }
     * 
     */
    public KMVSupportingReferenceType createKMVSupportingReferenceType() {
        return new KMVSupportingReferenceType();
    }

    /**
     * Create an instance of {@link KMVUserCommentType }
     * 
     */
    public KMVUserCommentType createKMVUserCommentType() {
        return new KMVUserCommentType();
    }

    /**
     * Create an instance of {@link ReferenceDataRefResponseType }
     * 
     */
    public ReferenceDataRefResponseType createReferenceDataRefResponseType() {
        return new ReferenceDataRefResponseType();
    }

    /**
     * Create an instance of {@link SpecialtyListType }
     * 
     */
    public SpecialtyListType createSpecialtyListType() {
        return new SpecialtyListType();
    }

    /**
     * Create an instance of {@link ReferenceDataLookupRefTypeCode }
     * 
     */
    public ReferenceDataLookupRefTypeCode createReferenceDataLookupRefTypeCode() {
        return new ReferenceDataLookupRefTypeCode();
    }

    /**
     * Create an instance of {@link KMVUsageStatisticType }
     * 
     */
    public KMVUsageStatisticType createKMVUsageStatisticType() {
        return new KMVUsageStatisticType();
    }

    /**
     * Create an instance of {@link ReferenceDataRefRequestType }
     * 
     */
    public ReferenceDataRefRequestType createReferenceDataRefRequestType() {
        return new ReferenceDataRefRequestType();
    }

    /**
     * Create an instance of {@link UserRoleType }
     * 
     */
    public UserRoleType createUserRoleType() {
        return new UserRoleType();
    }

    /**
     * Create an instance of {@link KmVersionListType }
     * 
     */
    public KmVersionListType createKmVersionListType() {
        return new KmVersionListType();
    }

    /**
     * Create an instance of {@link ReferenceDataResponseType }
     * 
     */
    public ReferenceDataResponseType createReferenceDataResponseType() {
        return new ReferenceDataResponseType();
    }

    /**
     * Create an instance of {@link FactSpecificationListType }
     * 
     */
    public FactSpecificationListType createFactSpecificationListType() {
        return new FactSpecificationListType();
    }

    /**
     * Create an instance of {@link KMVAccessControlListType }
     * 
     */
    public KMVAccessControlListType createKMVAccessControlListType() {
        return new KMVAccessControlListType();
    }

    /**
     * Create an instance of {@link ReferenceDataLookupType }
     * 
     */
    public ReferenceDataLookupType createReferenceDataLookupType() {
        return new ReferenceDataLookupType();
    }

    /**
     * Create an instance of {@link KmResponseTypeImportAck }
     * 
     */
    public KmResponseTypeImportAck createKmResponseTypeImportAck() {
        return new KmResponseTypeImportAck();
    }

    /**
     * Create an instance of {@link KmResponseType }
     * 
     */
    public KmResponseType createKmResponseType() {
        return new KmResponseType();
    }

    /**
     * Create an instance of {@link KMVOperationalConstraintDependencyType }
     * 
     */
    public KMVOperationalConstraintDependencyType createKMVOperationalConstraintDependencyType() {
        return new KMVOperationalConstraintDependencyType();
    }

    /**
     * Create an instance of {@link KmIdType }
     * 
     */
    public KmIdType createKmIdType() {
        return new KmIdType();
    }

    /**
     * Create an instance of {@link ReferenceDataLookupRefTypeType }
     * 
     */
    public ReferenceDataLookupRefTypeType createReferenceDataLookupRefTypeType() {
        return new ReferenceDataLookupRefTypeType();
    }

    /**
     * Create an instance of {@link ACLPermissionType }
     * 
     */
    public ACLPermissionType createACLPermissionType() {
        return new ACLPermissionType();
    }

    /**
     * Create an instance of {@link KmIdSearchResponseType }
     * 
     */
    public KmIdSearchResponseType createKmIdSearchResponseType() {
        return new KmIdSearchResponseType();
    }

    /**
     * Create an instance of {@link TTSpecificationType }
     * 
     */
    public TTSpecificationType createTTSpecificationType() {
        return new TTSpecificationType();
    }

    /**
     * Create an instance of {@link KmRequestType }
     * 
     */
    public KmRequestType createKmRequestType() {
        return new KmRequestType();
    }

    /**
     * Create an instance of {@link TaskListType }
     * 
     */
    public TaskListType createTaskListType() {
        return new TaskListType();
    }

    /**
     * Create an instance of {@link KmMetaResponseListType }
     * 
     */
    public KmMetaResponseListType createKmMetaResponseListType() {
        return new KmMetaResponseListType();
    }

    /**
     * Create an instance of {@link PopulationSpecificationType }
     * 
     */
    public PopulationSpecificationType createPopulationSpecificationType() {
        return new PopulationSpecificationType();
    }

    /**
     * Create an instance of {@link ImportResponseListType }
     * 
     */
    public ImportResponseListType createImportResponseListType() {
        return new ImportResponseListType();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link KmIdSearchRequestType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:gov:hhs:fha:nhinc:kmr:kmtypes", name = "GetKmLatestLogicRequest")
    public JAXBElement<KmIdSearchRequestType> createGetKmLatestLogicRequest(KmIdSearchRequestType value) {
        return new JAXBElement<KmIdSearchRequestType>(_GetKmLatestLogicRequest_QNAME, KmIdSearchRequestType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link KmRequestType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:gov:hhs:fha:nhinc:kmr:kmtypes", name = "FindKmIdsRequest")
    public JAXBElement<KmRequestType> createFindKmIdsRequest(KmRequestType value) {
        return new JAXBElement<KmRequestType>(_FindKmIdsRequest_QNAME, KmRequestType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link KmRequestType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:gov:hhs:fha:nhinc:kmr:kmtypes", name = "KmRuntimeRecordRequest")
    public JAXBElement<KmRequestType> createKmRuntimeRecordRequest(KmRequestType value) {
        return new JAXBElement<KmRequestType>(_KmRuntimeRecordRequest_QNAME, KmRequestType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link KmLatestLogicResponseType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:gov:hhs:fha:nhinc:kmr:kmtypes", name = "GetKmLatestLogicResponse")
    public JAXBElement<KmLatestLogicResponseType> createGetKmLatestLogicResponse(KmLatestLogicResponseType value) {
        return new JAXBElement<KmLatestLogicResponseType>(_GetKmLatestLogicResponse_QNAME, KmLatestLogicResponseType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link KmIdSearchRequestType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:gov:hhs:fha:nhinc:kmr:kmtypes", name = "KmIdSearchRequest")
    public JAXBElement<KmIdSearchRequestType> createKmIdSearchRequest(KmIdSearchRequestType value) {
        return new JAXBElement<KmIdSearchRequestType>(_KmIdSearchRequest_QNAME, KmIdSearchRequestType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ReferenceDataRefResponseType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:gov:hhs:fha:nhinc:kmr:kmtypes", name = "GetRefDataResponse")
    public JAXBElement<ReferenceDataRefResponseType> createGetRefDataResponse(ReferenceDataRefResponseType value) {
        return new JAXBElement<ReferenceDataRefResponseType>(_GetRefDataResponse_QNAME, ReferenceDataRefResponseType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link KmResponseType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:gov:hhs:fha:nhinc:kmr:kmtypes", name = "KmRuntimeRecordResponse")
    public JAXBElement<KmResponseType> createKmRuntimeRecordResponse(KmResponseType value) {
        return new JAXBElement<KmResponseType>(_KmRuntimeRecordResponse_QNAME, KmResponseType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link KmMetaResponseType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:gov:hhs:fha:nhinc:kmr:kmtypes", name = "GetKmMetaResponse")
    public JAXBElement<KmMetaResponseType> createGetKmMetaResponse(KmMetaResponseType value) {
        return new JAXBElement<KmMetaResponseType>(_GetKmMetaResponse_QNAME, KmMetaResponseType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link KmResponseTypeImportAck }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:gov:hhs:fha:nhinc:kmr:kmtypes", name = "KmInsertAckResponse")
    public JAXBElement<KmResponseTypeImportAck> createKmInsertAckResponse(KmResponseTypeImportAck value) {
        return new JAXBElement<KmResponseTypeImportAck>(_KmInsertAckResponse_QNAME, KmResponseTypeImportAck.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link KmImportRequestType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:gov:hhs:fha:nhinc:kmr:kmtypes", name = "KmInsertRequest")
    public JAXBElement<KmImportRequestType> createKmInsertRequest(KmImportRequestType value) {
        return new JAXBElement<KmImportRequestType>(_KmInsertRequest_QNAME, KmImportRequestType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link KmResponseType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:gov:hhs:fha:nhinc:kmr:kmtypes", name = "KmRecordResponse")
    public JAXBElement<KmResponseType> createKmRecordResponse(KmResponseType value) {
        return new JAXBElement<KmResponseType>(_KmRecordResponse_QNAME, KmResponseType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link KmIdSearchResponseType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:gov:hhs:fha:nhinc:kmr:kmtypes", name = "KmIdSearchResponse")
    public JAXBElement<KmIdSearchResponseType> createKmIdSearchResponse(KmIdSearchResponseType value) {
        return new JAXBElement<KmIdSearchResponseType>(_KmIdSearchResponse_QNAME, KmIdSearchResponseType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link KmIdSearchRequestType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:gov:hhs:fha:nhinc:kmr:kmtypes", name = "GetKmMetaRequest")
    public JAXBElement<KmIdSearchRequestType> createGetKmMetaRequest(KmIdSearchRequestType value) {
        return new JAXBElement<KmIdSearchRequestType>(_GetKmMetaRequest_QNAME, KmIdSearchRequestType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FindKmIdsResponseType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:gov:hhs:fha:nhinc:kmr:kmtypes", name = "FindKmIdsResponse")
    public JAXBElement<FindKmIdsResponseType> createFindKmIdsResponse(FindKmIdsResponseType value) {
        return new JAXBElement<FindKmIdsResponseType>(_FindKmIdsResponse_QNAME, FindKmIdsResponseType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link KmImportRequestType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:gov:hhs:fha:nhinc:kmr:kmtypes", name = "KmUpdateRequest")
    public JAXBElement<KmImportRequestType> createKmUpdateRequest(KmImportRequestType value) {
        return new JAXBElement<KmImportRequestType>(_KmUpdateRequest_QNAME, KmImportRequestType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link KmRequestType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:gov:hhs:fha:nhinc:kmr:kmtypes", name = "KmRecordRequest")
    public JAXBElement<KmRequestType> createKmRecordRequest(KmRequestType value) {
        return new JAXBElement<KmRequestType>(_KmRecordRequest_QNAME, KmRequestType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link KmResponseTypeImportAck }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:gov:hhs:fha:nhinc:kmr:kmtypes", name = "KmUpdateAckResponse")
    public JAXBElement<KmResponseTypeImportAck> createKmUpdateAckResponse(KmResponseTypeImportAck value) {
        return new JAXBElement<KmResponseTypeImportAck>(_KmUpdateAckResponse_QNAME, KmResponseTypeImportAck.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ReferenceDataRefRequestType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:gov:hhs:fha:nhinc:kmr:kmtypes", name = "GetRefDataRequest")
    public JAXBElement<ReferenceDataRefRequestType> createGetRefDataRequest(ReferenceDataRefRequestType value) {
        return new JAXBElement<ReferenceDataRefRequestType>(_GetRefDataRequest_QNAME, ReferenceDataRefRequestType.class, null, value);
    }

}
