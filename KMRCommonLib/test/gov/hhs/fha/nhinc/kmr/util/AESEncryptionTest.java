/*
 * @author jharby
 *
 * Test class for the AESEncryption class.
 * 
 */

package gov.hhs.fha.nhinc.kmr.util;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author nhin
 */
public class AESEncryptionTest {

    public AESEncryptionTest() {
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
     * Test of doAESEncrypt and aesDecrypt method, of class AESEncryption.
     * Verifies that the result decrypted string is equal to the original
     * string.
     */
    @Test
    public void testEncryptDecrypt() throws Exception {
        String toEncrypt = "This is a test";
        AESEncryption instance = new AESEncryption();
        String expResult = "";
        byte[] encrypted = instance.aesEncrypt(toEncrypt);
        System.out.println("Encrypted is: " +
                AESEncryption.toHexString(encrypted));
        String original = instance.aesDecrypt(encrypted);
        System.out.println("Decrypted is: " + original);
        assertEquals(toEncrypt, original);
    }

}