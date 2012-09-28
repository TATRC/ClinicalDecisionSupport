/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gov.hhs.fha.nhinc.kmr.util;

import gov.hhs.fha.nhinc.kmr.properties.PropertyAccessException;
import gov.hhs.fha.nhinc.kmr.properties.PropertyAccessor;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.cert.Certificate;
import java.security.GeneralSecurityException;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import sun.misc.BASE64Encoder;

/**
 * KMRKeystore class provides keys for AES encryption by retrieving them from
 * the Java keystore. The keys should be setup using keytool in advance. For
 * example this command was used to generate the key to test this initial
 * development: 
 *
 * keytool -genkey -alias kmr2key -keyalg RSA -validity 365 -keystore keystore.jks
 *
 * @author jharby
 */
public class KMRKeyStore {

    private SecretKeySpec aesKeySpec;
    private Cipher pkCipher;
    private Cipher aesCipher;
    private byte[] aesKey;
    private static final int AES_KEY_SIZE = 128;
    private File privateKeyFile;
    private File publicKeyFile;
    private File keystoreFile;
private static String PROPERTY_FILE = "KMRCommonUtil";

    Logger logger = Logger.getLogger("gov.hhs.fha.nhinc.kmr.util.KMRKeyStore");

    public KMRKeyStore() throws GeneralSecurityException {
        pkCipher = Cipher.getInstance("RSA");
        aesCipher = Cipher.getInstance("AES");
        setFiles();
    }

    private void setFiles() {
        try {
            String kmrDir = System.getProperty("KMR_PROPERTIES_DIR");
            String privFile = PropertyAccessor.getProperty(PROPERTY_FILE, "privateKeyFile");
            String pubFile = PropertyAccessor.getProperty(PROPERTY_FILE, "publicKeyFile");
            String keyFile = PropertyAccessor.getProperty(PROPERTY_FILE, "keystoreFile");
            privateKeyFile = new File(kmrDir + "/" + privFile);
            publicKeyFile = new File(kmrDir + "/" + pubFile);
            keystoreFile = new File(kmrDir + "/" + keyFile);
            logger.log(Level.INFO, "key files are " + privFile + " and " +
                    pubFile + " and " + keyFile);

        } catch (PropertyAccessException pae) {
            pae.printStackTrace();
        }
    }

    SecretKeySpec getKeySpec() {
        return aesKeySpec;
    }

    /**
     * Thie method generates the AES key to use for storage and cryptographic
     * operations.
     *
     * @return the SecretKeySpec object which can be stored or used.
     *
     * @throws NoSuchAlgorithmException 
     */
    private SecretKeySpec generateKeySpec() throws NoSuchAlgorithmException {
        KeyGenerator kgen = KeyGenerator.getInstance("AES");
        kgen.init(128);
        SecretKey key = kgen.generateKey();
        aesKey = key.getEncoded();
        SecretKeySpec skeySpec = new SecretKeySpec(aesKey, "AES");
        return skeySpec;
    }

    /**
     * The getPubKey() method obtains the public key used to secure the AES key
     * from a file. This key can be exported using the keytool. Note this may
     * be reworked to use the KeyStore class instead of the file access.
     *
     * @return the PublicKey object obtained from the file
     *
     * @throws IOException
     * @throws GeneralSecurityException
     */
    private PublicKey getPubKey() throws GeneralSecurityException, IOException {
        CertificateFactory cf = CertificateFactory.getInstance("X509");
        InputStream is = new FileInputStream(publicKeyFile);
        X509Certificate certificate = (X509Certificate) cf.generateCertificate(is);
        is.close();
        RSAPublicKey pubKey = (RSAPublicKey) certificate.getPublicKey();
        return pubKey;
    }

    /**
     * Saves the key to the keystore. First reads the RSA key used to secure
     * the AES key from the public key file. Then writes the AES key to the
     * specified private key file.
     *
     * @throws IOException
     * @throws GeneralSecurityException
     */
    public void saveKey() throws IOException, GeneralSecurityException {
        // read public key to be used to encrypt the AES key
        byte[] encodedKey = new byte[(int) publicKeyFile.length()];
        new FileInputStream(publicKeyFile).read(encodedKey);

        // create public key
        X509EncodedKeySpec publicKeySpec =
                new X509EncodedKeySpec(getPubKey().getEncoded());

        KeyFactory kf = KeyFactory.getInstance("RSA");
        PublicKey pk = kf.generatePublic(publicKeySpec);

        // write AES key
        SecretKeySpec sks = generateKeySpec();

        pkCipher.init(Cipher.ENCRYPT_MODE, pk);
        CipherOutputStream os =
                new CipherOutputStream(new FileOutputStream(privateKeyFile), pkCipher);
        os.write(aesKey);
        os.close();
    }

    /**
     * Loads the key from the keystore. First loads the KeyStore instance
     * and gets the private RSA key used to secure the AES key. Then the
     * AES key is read from the file in which it is stored. Finally the
     * SecretKeySpec object is created using the AES key.
     *
     * @throws IOException
     * @throws GeneralSecurityException
     */
    public void loadKey() throws GeneralSecurityException, IOException {
        // read private key to be used to decrypt the AES key

        KeyStore keystore = KeyStore.getInstance("JKS");
        BASE64Encoder encoder = new BASE64Encoder();
        keystore.load(new FileInputStream(keystoreFile), "nhinpass".toCharArray());
        KeyPair keyPair = getPrivateKey(keystore, "kmr2key", "nhinpass".toCharArray());
        PrivateKey privateKey = keyPair.getPrivate();

        // read AES key
        pkCipher.init(Cipher.DECRYPT_MODE, privateKey);
        aesKey = new byte[AES_KEY_SIZE / 8];
        CipherInputStream is = new CipherInputStream(new FileInputStream(privateKeyFile), pkCipher);
        is.read(aesKey);
        aesKeySpec = new SecretKeySpec(aesKey, "AES");
    }

    /**
     *  Gets the RSA private key from the Java keystore.
     *
     * @param keystore - the KeyStore object representing the Java keystore
     * @param alias - the alias created for the key on generation
     * @param password - password for the keystore
     */
    public static KeyPair getPrivateKey(KeyStore keystore, String alias, char[] password) {
        try {
            Key key = keystore.getKey(alias, password);
            if (key instanceof PrivateKey) {
                Certificate cert = keystore.getCertificate(alias);
                PublicKey publicKey = cert.getPublicKey();
                return new KeyPair(publicKey, (PrivateKey) key);
            }
        } catch (UnrecoverableKeyException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyStoreException e) {
            e.printStackTrace();
        }
        return null;
    }
}
