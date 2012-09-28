/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gov.hhs.fha.nhinc.adapter.commondatalayer.helper;

import gov.hhs.fha.nhinc.adapter.commondatalayer.client.CommonDataLayerConstants;
import gov.hhs.fha.nhinc.adapter.commondatalayer.util.DateUtils;
import gov.hhs.fha.nhinc.adapter.commondatalayer.util.Utils;
import java.io.Serializable;
import java.util.Date;
import java.util.Properties;
import org.hl7.v3.CS;
import org.hl7.v3.CommunicationFunctionType;
import org.hl7.v3.EntityClassDevice;
import org.hl7.v3.II;
import org.hl7.v3.MCCIMT000100UV01Device;
import org.hl7.v3.MCCIMT000100UV01Receiver;
import org.hl7.v3.MCCIMT000100UV01Sender;
import org.hl7.v3.TELExplicit;
import org.hl7.v3.ObjectFactory;
import org.hl7.v3.TS;

/**
 *
 * @author kim
 */
public class RequestHelper implements Serializable {

  public final static String CDL_SERVICE = "Common Data Layer Service";
  public final static String CDL_SERVICE_TEL = "http://localhost:8080/NhinConnect/CommonDataLayerService";
  public final static String PROCESSING_CODE = "R";
  public final static String PROCESSING_MOOD_CODE = "T";
  public final static String ACCEPT_ACK_CODE = "AL";
  protected static ObjectFactory factory = null;
  protected String homeOid = "";

  protected RequestHelper(Properties properties, ObjectFactory hl7Factory) {
    homeOid = properties.getProperty(CommonDataLayerConstants.ORGANIZATION_OID);
    if (factory != null) {
      factory = hl7Factory;
    } else {
      factory = new ObjectFactory();
    }
  }

  protected II setMessageId() {
    II messageId = new II();
    messageId.setRoot(homeOid);
    messageId.setExtension(Utils.generateDocumentId());
    return messageId;
  }

  protected II setInteractionId(String actionId) {
    II interactionId = new II();
    interactionId.setRoot("2.16.840.1.113883.5");
    interactionId.setAssigningAuthorityName("HL7");
    interactionId.setExtension(actionId);
    return interactionId;
  }

  protected CS setCSCode(String code) {
    CS csCode = new CS();
    csCode.setCode(code);
    return csCode;
  }

  protected TS setMessageTimestamp(Date date) {
    TS creationTime = new TS();
    creationTime.setValue(DateUtils.convertToCDATime(date));
    return creationTime;
  }

  protected MCCIMT000100UV01Receiver createReceiver() {
    MCCIMT000100UV01Receiver receiver = new MCCIMT000100UV01Receiver();

    receiver.setTypeCode(CommunicationFunctionType.RCV);

    MCCIMT000100UV01Device device = new MCCIMT000100UV01Device();
    device.setDeterminerCode("INSTANCE");
    device.setClassCode(EntityClassDevice.DEV);

    II id = new II();
    id.setExtension(CDL_SERVICE);
    id.setRoot(homeOid);
    device.getId().add(id);

    TELExplicit url = new TELExplicit();
    url.setValue(CDL_SERVICE_TEL);
    device.getTelecom().add(url);

    receiver.setDevice(device);

    return receiver;
  }

  protected MCCIMT000100UV01Sender createSender(String id) {
    MCCIMT000100UV01Sender sender = new MCCIMT000100UV01Sender();

    sender.setTypeCode(CommunicationFunctionType.SND);

    MCCIMT000100UV01Device device = new MCCIMT000100UV01Device();
    device.setDeterminerCode("INSTANCE");
    device.setClassCode(EntityClassDevice.DEV);

    II senderId = new II();
    senderId.setExtension(id);
    senderId.setRoot(homeOid);
    device.getId().add(senderId);

    sender.setDevice(device);

    return sender;
  }
}
