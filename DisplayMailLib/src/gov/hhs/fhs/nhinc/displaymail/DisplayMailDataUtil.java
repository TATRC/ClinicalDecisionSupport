/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gov.hhs.fhs.nhinc.displaymail;

import gov.hhs.fha.nhinc.common.dda.GetMessagesRequestType;
import gov.hhs.fha.nhinc.common.dda.GetMessagesResponseType;
import gov.hhs.fha.nhinc.common.dda.SetMessageRequestType;
import gov.hhs.fha.nhinc.common.dda.SetMessageResponseType;

/**
 *
 * @author jharby
 */
public class DisplayMailDataUtil {

    public GetMessagesResponseType getMessages(GetMessagesRequestType request) {
        return new GetMessagesResponseType();
    }

    public SetMessageResponseType setMessage(SetMessageRequestType request) {
        return new SetMessageResponseType();
    }

}
