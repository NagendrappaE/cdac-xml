package com.evolvus.cda.cdacesign;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URL;
import java.security.KeyPair;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value = "/CDACXML")
public class XMLController {

	@Value("${REDIRECT_URL}")
	private String redirectUrl;

	@Value("${esign_version}")
	private String esignVersion;

	@Value("${eKYCMode}")
	private String eKYCMode;

	@Value("${eKYCIdType}")
	private String eKYCIdType;

	@Value("${authMode}")
	private String authMode;

	@Value("${ResponseURL}")
	private String ResponseURL;

	@Value("${aspId}")
	private String aspId;

	@Value("${responseSigType}")
	private String responseSigType;

	@RequestMapping(value = "/CDACREQ", method = RequestMethod.POST)
	public ResponseEntity<String> returnXml(@RequestParam("aadharNum") String aadharNumber)
			throws FileNotFoundException {
		String signedXml = null;
		Map<String, Object> valueMap = new HashMap<>();
		String reqXml = Pain009Request.Pain009;
		XmlStrSubstitutor sub = new XmlStrSubstitutor(valueMap);
		String xmlInput = sub.replace(reqXml);
		InputStream inputStream = null;
		// ResponseEntity<ResponseJson> resEntity = new
		// ResponseEntity<ResponseJson>(HttpStatus.OK);
		// ResponseJson res = new ResponseJson();
		// res.setStatus(HttpStatus.OK);

		try {

			Random random = new Random();
			int msgId = random.nextInt(100000);// 6
			int mndtReqRef = random.nextInt(1000000000);// 16
			int consumerRefeNumber = random.nextInt(1600000000);// 50

			System.out.println("the generated xml ::::::::" + xmlInput);

			// verifySignedData.writePAINXML(xmlInput, mndtReqRef);

			// encodedXML = this.EncryptedString(xmlInput);
			String encodedXML = this.hashDataMkyong(xmlInput);
			valueMap.put("hashXMLData", encodedXML);
			SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd'T'HH:mm:ss.sss");
			System.out.println(sdf.format(new Date()));
			valueMap.put("dateTime", sdf.format(new Date()));
			valueMap.put("esignVersion", esignVersion);
			int transcationReferenceNumber = 177 + random.nextInt(50000);
			valueMap.put("txnId", mndtReqRef);
			valueMap.put("eKYCMode", eKYCMode);
			valueMap.put("eKYCIdType", eKYCIdType);
			valueMap.put("ekycId", aadharNumber);
			valueMap.put("authMode", authMode);
			valueMap.put("responseURL", ResponseURL);
			valueMap.put("aspId", aspId);
			valueMap.put("responseSigType", responseSigType);
			// String cdacRequestStringUnsiged = NSDL.NSDL_REQUEST;

			String cdacRequestStringUnsiged = CDAC.CDACREQUEST;
			sub = new XmlStrSubstitutor(valueMap);
			String cdacRequestedxml = sub.replace(cdacRequestStringUnsiged);

			File file = new File("/tmp/privateKeyData");
			FileUtils.copyURLToFile(
					new URL("https://s3-eu-west-1.amazonaws.com/mobilehubproject-hosting-mobilehub-776545955/privateKeyData"),
					file);
			
			inputStream = new FileInputStream(file);

			KeyExtractor keyExtractor = new KeyExtractor();
			KeyPair keypair = keyExtractor.extractKeysFromStream(inputStream);
			System.out.println("the private key :::::::" + keypair.getPrivate());
			CreateSignature createSignature = new CreateSignature();
			String xmlString = "Hi thsi  is nagendra";
			signedXml = createSignature.signFile(cdacRequestedxml, keypair.getPrivate());

			System.out.println("signed xml::::::" + signedXml);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<String>(signedXml, HttpStatus.OK);

	}

	private String hashDataMkyong(String xmlInput) {
		StringBuffer hexString = null;

		try {
			String password = "123456";
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			md.update(xmlInput.getBytes());

			byte byteData[] = md.digest();

			// convert the byte to hex format method 1
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < byteData.length; i++) {
				sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
			}

			System.out.println("Hex format : " + sb.toString());

			// convert the byte to hex format method 2
			hexString = new StringBuffer();
			for (int i = 0; i < byteData.length; i++) {
				String hex = Integer.toHexString(0xff & byteData[i]);
				if (hex.length() == 1)
					hexString.append('0');
				hexString.append(hex);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("Hex format : " + hexString.toString());
		return hexString.toString();
	}
}
