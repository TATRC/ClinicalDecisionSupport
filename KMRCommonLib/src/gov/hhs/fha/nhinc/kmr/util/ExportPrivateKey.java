/*
 * Utility to export a private key from the Java Keystore for a given
 * alias.
 */

package gov.hhs.fha.nhinc.kmr.util;

import gov.hhs.fha.nhinc.kmr.properties.PropertyAccessException;
import gov.hhs.fha.nhinc.kmr.properties.PropertyAccessor;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.UnrecoverableKeyException;
import java.security.cert.Certificate;
import java.util.logging.Level;

import sun.misc.BASE64Encoder;

/**
 * ExportPrivateKey class allows the exporting of private keys from the Java
 * keystore, keystore.jks. When new AES encryption keys are created, this can
 * be used to obtain the exported.key file containing the private key.
 * 
 * @author jharby
 */

public class ExportPrivateKey {
       private File keystoreFile;
       private String keyStoreType;
       private char[] password;
       private String alias;
       private File exportedFile;
       private File publicKeyFile;
       private static String PROPERTY_FILE = "KMRCommonUtil";

       public static KeyPair getPrivateKey(KeyStore keystore, String alias, char[] password) {
               try {
                       Key key=keystore.getKey(alias,password);
                       if(key instanceof PrivateKey) {
                               Certificate cert=keystore.getCertificate(alias);
                               PublicKey publicKey=cert.getPublicKey();
                               return new KeyPair(publicKey,(PrivateKey)key);
                       }
               } 
               catch (UnrecoverableKeyException e) {
                   e.printStackTrace();
               }
               catch (NoSuchAlgorithmException e) {
                   e.printStackTrace();
               }
               catch (KeyStoreException e) {
                   e.printStackTrace();
               }
               return null;
       }

       public void export() throws Exception{
               KeyStore keystore=KeyStore.getInstance(keyStoreType);
               BASE64Encoder encoder=new BASE64Encoder();
               keystore.load(new FileInputStream(keystoreFile),password);
               KeyPair keyPair=getPrivateKey(keystore,alias,password);
               PrivateKey privateKey=keyPair.getPrivate();
               String encoded=encoder.encode(privateKey.getEncoded());
               FileWriter fw=new FileWriter(exportedFile);
               fw.write("---BEGIN PRIVATE KEY---\n");
               fw.write(encoded);
               fw.write("\n");
               fw.write("---END PRIVATE KEY---");
               fw.close();
       }

        private void setFiles() {
        try {
            String kmrDir = System.getProperty("KMR_PROPERTIES_DIR");
            String privFile = PropertyAccessor.getProperty(PROPERTY_FILE, "privateKeyFile");
            String pubFile = PropertyAccessor.getProperty(PROPERTY_FILE, "publicKeyFile");
            String keyFile = PropertyAccessor.getProperty(PROPERTY_FILE, "keystoreFile");
            exportedFile = new File(kmrDir + "/" + privFile);
            publicKeyFile = new File(kmrDir + "/" + pubFile);
            keystoreFile = new File(kmrDir + "/" + keyFile);
        } catch (PropertyAccessException pae) {
            pae.printStackTrace();
        }
    }

       public static void main(String args[]) throws Exception {
               String keystoreFile = "/Users/jharby/keystore.jks";
               String keystoreType = "JKS";
               String password = "nhinpass";
               String alias = "kmr2key";
               String exportedFile = "/Users/jharby/exported.key";
               ExportPrivateKey export=new ExportPrivateKey();
               export.keystoreFile=new File(keystoreFile);
               export.keyStoreType=keystoreType;
               export.password=password.toCharArray();
               export.alias=alias;
               export.exportedFile=new File(exportedFile);
               export.export();
       }
}
