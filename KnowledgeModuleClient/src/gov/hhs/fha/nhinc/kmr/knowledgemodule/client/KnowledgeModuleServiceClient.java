/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gov.hhs.fha.nhinc.kmr.knowledgemodule.client;

import gov.hhs.fha.nhinc.kmr.properties.PropertyAccessor;
import gov.hhs.fha.nhinc.kmr.kmtypes.FindKmIdsResponseType;
import gov.hhs.fha.nhinc.kmr.kmtypes.KmByIdRequestListType;
import gov.hhs.fha.nhinc.kmr.kmtypes.KmIdSearchRequestType;
import gov.hhs.fha.nhinc.kmr.kmtypes.KmIdType;
import gov.hhs.fha.nhinc.kmr.kmtypes.KmLatestLogicResponseType;
import gov.hhs.fha.nhinc.kmr.kmtypes.KmMetaResponseType;
import gov.hhs.fha.nhinc.kmr.kmtypes.KmRequestType;
import gov.hhs.fha.nhinc.kmr.kmtypes.MultiParamsRequestType;
import gov.hhs.fha.nhinc.kmr.kmtypes.RequestRefType;
import gov.hhs.fha.nhinc.kmr.knowledgemodule.KnowledgeModulePortType;
import gov.hhs.fha.nhinc.kmr.knowledgemodule.KnowledgeModuleService;
import java.net.MalformedURLException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import javax.xml.namespace.QName;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

/**
 *
 * @author tmn
 */
public class KnowledgeModuleServiceClient {

   private static Log log = LogFactory.getLog(KnowledgeModuleServiceClient.class);
   private static ApplicationContext context;
   private static Map authenServiceProps;
   private final static String KM_SERVICE_CLIENT_PROPS = "knowledgemodule.client";
   private static KnowledgeModulePortType port = null;
   private static KnowledgeModuleService service = null;
   public static final String KMR_LAYER_QNAME = "urn:gov:hhs:fha:nhinc:kmr:knowledgemodule";

   static {
      String[] cfiles = new String[]{
         PropertyAccessor.getPropertyFileLocation() + "kmr/kmr-service-context.xml"
      };

      // ----------------------------------------------------
      //Read KMR property file and get the WSDL URL
      // ----------------------------------------------------
      context = new FileSystemXmlApplicationContext(cfiles);
      authenServiceProps = (Map) context.getBean(KM_SERVICE_CLIENT_PROPS);

      String serviceEndpoint = (String) authenServiceProps.get("serviceEndpoint");

      System.out.println("\n\nserviceEndpoint="+serviceEndpoint);

      initService(serviceEndpoint);
      port = service.getKnowledgeModulePort();
   }

   // ----------------------------------------------------
   //Overlay the URL with the one listed in property file.
   // ----------------------------------------------------
   private static void initService(String serviceEndpoint) {
      URL baseUrl;

      baseUrl = gov.hhs.fha.nhinc.kmr.knowledgemodule.KnowledgeModuleService.class.getResource(".");
      try {
         URL url = new URL(baseUrl, serviceEndpoint);
         service = new KnowledgeModuleService(url, new QName(KMR_LAYER_QNAME, "KnowledgeModuleService"));
      } catch (MalformedURLException e) {
         log.error("Failed to create URL for the wsdl Location: " + serviceEndpoint);
      }
   }

   //ADd in method to call (1.1) - return list<string> of IDs
   public static List<String> getModelIds(String requestId) {      

      Calendar cal = Calendar.getInstance();
      SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
      String currDate = sdf.format(cal.getTime());

      KmRequestType param0 = new KmRequestType();
      MultiParamsRequestType req = new MultiParamsRequestType();
      RequestRefType reqRef = new RequestRefType();
      reqRef.setRequestDate(currDate);
      reqRef.setRequestId(requestId);
      reqRef.setResponseDate(currDate);
      req.setRequestReference(reqRef);
      param0.setRequest(req);
      FindKmIdsResponseType response = port.findKmId(param0);

      //--------------------------------------------------
      //Converting to CLIENT accepted object: String list
      //Integer list to String list.
      //--------------------------------------------------
      String all = new ArrayList<Integer>(response.getResponse().getKms().getKmId()).toString();
      String[] split = all.substring(1, all.length() - 1).split(", ");
      List<String> modelList = Arrays.asList(split);

      return modelList;

   }

   /* TODO:  But need Oryx jar from Sam ..
    
   public static org.b3mn.poem.Representation getRepresentation(int id) {
   KmIdSearchRequestType request = new KmIdSearchRequestType();

   org.b3mn.poem.Representation representation = new Representation();


   KmMetaResponseType response = port.getKmMeta(request);
   representation.setXXX(response.getResponseList().getKm().get(0));

   return representation;
   }
    */
   
   //TODO:  ADd in method to call 2.0 - return Logic Native
   public static KmLatestLogicResponseType getModelForm(String requestId) {

      Calendar cal = Calendar.getInstance();
      SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
      String currDate = sdf.format(cal.getTime());

      KmIdSearchRequestType param0 = new KmIdSearchRequestType();
      KmByIdRequestListType reqList = new KmByIdRequestListType();

      RequestRefType reqRef = new RequestRefType();
      reqRef.setRequestDate(currDate);
      reqRef.setRequestId(requestId);
      reqRef.setResponseDate(currDate);
      reqList.setRequestReference(reqRef);
      
      KmIdType kms = new KmIdType();
      kms.getKmId().add((new Integer(requestId)));
      reqList.setKms(kms);

      param0.setRequest(reqList);
      KmLatestLogicResponseType response = port.getKmLatestLogic(param0);

      //--------------------------------------------------
      //TODO:  Need to know what Oryx is expecting.
      //Converting to CLIENT accepted object: String list
      //Integer list to String list.
      //--------------------------------------------------

      return response;

   }
}
