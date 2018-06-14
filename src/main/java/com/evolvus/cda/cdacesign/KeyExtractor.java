package com.evolvus.cda.cdacesign;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.KeyPair;
import java.security.Security;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.openssl.PEMDecryptorProvider;
import org.bouncycastle.openssl.PEMEncryptedKeyPair;
import org.bouncycastle.openssl.PEMKeyPair;
import org.bouncycastle.openssl.PEMParser;
import org.bouncycastle.openssl.jcajce.JcaPEMKeyConverter;
import org.bouncycastle.openssl.jcajce.JcePEMDecryptorProviderBuilder;



/**
 *
 * @author user1
 */
public class KeyExtractor {
 	/**
	 * extract certificate from file system
 	 * @throws FileNotFoundException 
	 */
	public KeyPair extractKeysFromStream() throws FileNotFoundException {
                Security.addProvider(new BouncyCastleProvider());
		KeyPair keyPair = null;
		File file = new File("/home/mahanteshm/Documents/SSL/privateKeyData");
		InputStream inputStream = new FileInputStream(file);
		
		//InputStream inputStream = KeyExtractor.class.getResourceAsStream("privateKeyData");
		InputStreamReader reader = new InputStreamReader(inputStream);
		PEMParser pemParser = new PEMParser(reader);
		Object object = null;
		try {
			object = pemParser.readObject();
			PEMDecryptorProvider decProv = new JcePEMDecryptorProviderBuilder().build("".toCharArray());
			JcaPEMKeyConverter converter = new JcaPEMKeyConverter().setProvider("BC");

			if (object instanceof PEMEncryptedKeyPair) {
				keyPair = converter.getKeyPair(((PEMEncryptedKeyPair) object)
						.decryptKeyPair(decProv));

			} else {
				keyPair = converter.getKeyPair((PEMKeyPair) object);
			}
                       
		} catch (IOException e) {
                    e.printStackTrace();
		} finally {
                try {
                    pemParser.close();
                } catch (IOException ex) {
                    Logger.getLogger(KeyExtractor.class.getName()).log(Level.SEVERE, null, ex);
                }
		}
		return keyPair;
	}
}
        
        
        
	/*public X509Certificate loadPublicX509(InputStream inputStream) {
		X509Certificate crt = null;
		try {
			CertificateFactory cf = CertificateFactory.getInstance("X.509");
			crt = (X509Certificate) cf.generateCertificate(inputStream);
		} catch (CertificateException e) {
			e.printStackTrace();
		} 
		System.out.println(crt);
		return crt;
	}*/
	

