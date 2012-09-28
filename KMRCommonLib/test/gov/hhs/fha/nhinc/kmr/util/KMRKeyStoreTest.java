/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gov.hhs.fha.nhinc.kmr.util;

import javax.crypto.spec.SecretKeySpec;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import sun.misc.BASE64Encoder;

/**
 *
 * @author nhin
 */
public class KMRKeyStoreTest {

    public KMRKeyStoreTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of saveKey method, of class KMRKeyStore.
     */
    @Test
    public void testSaveKey() throws Exception {
        System.out.println("saveKey");
        KMRKeyStore instance = new KMRKeyStore();
        instance.saveKey();
    }

    /**
     * Test of loadKey method, of class KMRKeyStore.
     */
    @Test
    public void testLoadKey() throws Exception {
        System.out.println("loadKey");
        BASE64Encoder encoder=new BASE64Encoder();
        KMRKeyStore instance = new KMRKeyStore();
        instance.loadKey();
        SecretKeySpec keySpec = instance.getKeySpec();
        System.out.println(new String(encoder.encode(keySpec.getEncoded())));
    }

}