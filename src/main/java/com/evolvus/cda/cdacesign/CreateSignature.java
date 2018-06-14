package com.evolvus.cda.cdacesign;


import java.io.IOException;
import java.io.StringReader;
import java.security.PrivateKey;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.io.output.ByteArrayOutputStream;
import org.apache.xml.security.Init;

import org.apache.xml.security.c14n.Canonicalizer;
import org.apache.xml.security.exceptions.XMLSecurityException;
import org.apache.xml.security.signature.XMLSignature;
import org.apache.xml.security.transforms.Transforms;
import org.apache.xml.security.utils.Constants;
import org.apache.xml.security.utils.ElementProxy;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class CreateSignature {

	public String signFile(String xmlString, PrivateKey privateKey) {
		String signedXml = null;
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		Document doc;
		try {
			doc = DocumentBuilderFactory.newInstance().newDocumentBuilder()
					.parse(new InputSource(new StringReader(xmlString)));
			Init.init();
			ElementProxy.setDefaultPrefix(Constants.SignatureSpecNS, "");
			final XMLSignature sig = new XMLSignature(doc, null,
					XMLSignature.ALGO_ID_SIGNATURE_RSA);
			final Transforms transforms = new Transforms(doc);
			transforms.addTransform(Transforms.TRANSFORM_ENVELOPED_SIGNATURE);
			sig.addDocument("", transforms, Constants.ALGO_ID_DIGEST_SHA1);
			sig.sign(privateKey);
			doc.getDocumentElement().appendChild(sig.getElement());

			byteArrayOutputStream.write(Canonicalizer.getInstance(
					Canonicalizer.ALGO_ID_C14N_WITH_COMMENTS)
					.canonicalizeSubtree(doc));
			signedXml = byteArrayOutputStream.toString();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (XMLSecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				byteArrayOutputStream.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return signedXml;
	}
}