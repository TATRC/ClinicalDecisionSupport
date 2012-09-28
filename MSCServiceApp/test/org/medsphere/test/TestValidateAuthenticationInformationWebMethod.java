/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.medsphere.test;

import com.medsphere.vistarpc.RPCConnection;
import com.medsphere.vistarpc.RPCException;
import com.medsphere.vistarpc.RPCResponse;
import com.medsphere.vistarpc.RPCResponse.ResponseType;
import com.medsphere.vistarpc.VistaRPC;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Assert;
import org.junit.Test;
import org.medsphere.util.service.MSCAuthentication;
import org.medsphere.util.service.UserInfo;

/**
 *
 * @author apardue
 */
public class TestValidateAuthenticationInformationWebMethod {

    @Test
    public void getATokenAndDUZAndValidateIt() throws RPCException {
        RPCConnection connection = null;
        try {
            connection = new MSCAuthentication().getCIABrokerConnection(new MSCAuthentication().getConnectionProperties());
            String duz = connection.getDUZ();
            VistaRPC rpc = new VistaRPC("XUS GET TOKEN", ResponseType.SINGLE_VALUE);

            RPCResponse response = connection.execute(rpc);
            if (response == null || response.getError() != null) {
                System.err.println("ERROR: " + response.getError());
                throw new RPCException(response.getError());
            }
            String token = response.getString();

            System.out.println("token=[" + token +"] duz=[" + duz + "]");

            UserInfo userInfo = new MSCAuthentication().validateAuthenticationInformation(duz, token);

            System.out.println("userInfo: " + userInfo);
            Assert.assertNull(userInfo.errorMessage);
            Assert.assertEquals(userInfo.duz, duz);
            Assert.assertNotNull(userInfo.userName);
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }

    @Test
    public void aBadTokenShouldResultInAnError() {        
        UserInfo userInfo = new MSCAuthentication().validateAuthenticationInformation("1", "this isn't really a token");
        Assert.assertNotNull(userInfo.errorMessage);
        System.out.println(userInfo.errorMessage);

    }

    @Test
    public void aMismatchedDUZShouldResultInAnError() throws RPCException {
        RPCConnection connection = null;
        try {
            connection = new MSCAuthentication().getCIABrokerConnection(new MSCAuthentication().getConnectionProperties());
            String duz = connection.getDUZ();
            VistaRPC rpc = new VistaRPC("XUS GET TOKEN", ResponseType.SINGLE_VALUE);

            RPCResponse response = connection.execute(rpc);
            if (response == null || response.getError() != null) {
                System.err.println("ERROR: " + response.getError());
                throw new RPCException(response.getError());
            }
            String token = response.getString();

            duz = "This is not a valid duz";
            System.out.println("token=[" + token +"] duz=[" + duz + "]");

            UserInfo userInfo = new MSCAuthentication().validateAuthenticationInformation(duz, token);

            System.out.println("userInfo: " + userInfo);
            Assert.assertNotNull(userInfo.errorMessage);
            Assert.assertNull(userInfo.duz);
            Assert.assertNull(userInfo.userName);
        } finally {
            if (connection != null) {
                connection.close();
            }
        }

    }
    
    @Test
    public void testThatATokenExpiresAfterAbout20Seconds() throws RPCException {
        RPCConnection connection = null;
        try {
            connection = new MSCAuthentication().getCIABrokerConnection(new MSCAuthentication().getConnectionProperties());
            String duz = connection.getDUZ();
            VistaRPC rpc = new VistaRPC("XUS GET TOKEN", ResponseType.SINGLE_VALUE);

            RPCResponse response = connection.execute(rpc);
            if (response == null || response.getError() != null) {
                System.err.println("ERROR: " + response.getError());
                throw new RPCException(response.getError());
            }
            String token = response.getString();

            System.out.println("token=[" + token +"] duz=[" + duz + "]");
            int nSeconds = 21;
            System.out.println("sleeping for " + nSeconds + " seconds");
            Thread.sleep(nSeconds*1000);
            System.out.println("awake... token should be expired");
            UserInfo userInfo = new MSCAuthentication().validateAuthenticationInformation(duz, token);

            System.out.println("userInfo: " + userInfo);
            Assert.assertNotNull(userInfo.errorMessage);
            Assert.assertNull(userInfo.duz);
            Assert.assertNull(userInfo.userName);
        } catch (InterruptedException ex) {
            Logger.getLogger(TestValidateAuthenticationInformationWebMethod.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (connection != null) {
                connection.close();
            }
        }

    }
}
