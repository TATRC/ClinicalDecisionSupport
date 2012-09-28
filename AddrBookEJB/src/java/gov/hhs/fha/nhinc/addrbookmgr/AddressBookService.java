/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gov.hhs.fha.nhinc.addrbookmgr;

import gov.hhs.fha.nhinc.addrservice.AddressBookManagerPortType;
import javax.ejb.Stateless;
import javax.jws.WebService;

/**
 *
 * @author cmatser
 */
@WebService(serviceName = "AddressBookManager", portName = "AddressBookManagerPortSoap11", endpointInterface = "gov.hhs.fha.nhinc.addrservice.AddressBookManagerPortType", targetNamespace = "urn:gov:hhs:fha:nhinc:addrservice", wsdlLocation = "META-INF/wsdl/AddressBookService/AddressBookManager.wsdl")
@Stateless
public class AddressBookService implements AddressBookManagerPortType {

    public gov.hhs.fha.nhinc.common.addrbook.GetSummariesResponseType getAllAddr(gov.hhs.fha.nhinc.common.addrbook.GetAllAddrRequestType getAllAddrRequest) {
        return new AddressBookImpl().getAllAddr(getAllAddrRequest);
    }

    public gov.hhs.fha.nhinc.common.addrbook.GetSummariesResponseType searchAddr(gov.hhs.fha.nhinc.common.addrbook.SearchAddrRequestType searchAddrRequest) {
        return new AddressBookImpl().searchAddr(searchAddrRequest);
    }

    public gov.hhs.fha.nhinc.common.addrbook.GetContactDetailsResponseType getContactDetails(gov.hhs.fha.nhinc.common.addrbook.GetContactDetailsRequestType getContactDetailsRequest) {
        return new AddressBookImpl().getContactDetails(getContactDetailsRequest);
    }

}
