/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gov.hhs.fha.nhinc.account;

import javax.jws.WebService;

/**
 *
 * @author Sushma
 */
@WebService(serviceName = "AccountManager", portName = "AccountManagerPortSoap11", endpointInterface = "gov.hhs.fha.nhinc.acctmgr.AccountManagerPortType", targetNamespace = "urn:gov:hhs:fha:nhinc:acctmgr", wsdlLocation = "WEB-INF/wsdl/AccountManager/AccountManager.wsdl")
public class AccountManager {

    public gov.hhs.fha.nhinc.common.account.Response createAccount(gov.hhs.fha.nhinc.common.account.CreateAccountRequestType createAccountRequest) {
        return new AccountMgrImpl().createAccount(createAccountRequest);
    }

    public gov.hhs.fha.nhinc.common.account.Response updateAccount(gov.hhs.fha.nhinc.common.account.UpdateAccountRequestType updateAccountRequest) {
       return new AccountMgrImpl().updateAccount(updateAccountRequest);
    }

}
