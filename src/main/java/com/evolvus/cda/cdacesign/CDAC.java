package com.evolvus.cda.cdacesign;

public interface CDAC {
public  static String  CDACREQUEST="<Esign ver=\"${esignVersion}\" sc=\"Y\" ts=\"${dateTime}\" txn=\"${txnId}\" ekycMode=\"${eKYCMode}\" ekycId=\"${ekycId}\" ekycIdType=\"${eKYCIdType}\" aspId=\"${aspId}\" AuthMode=\"${authMode}\" responseSigType=\"${responseSigType}\" preVerified=\"n\" organizationFlag=\"n\" responseUrl=\"${responseURL}\">"+
"<Docs>"+
"<InputHash id=\"1\" hashAlgorithm=\"SHA256\" docInfo=\"FluxEsign\">${hashXMLData}</InputHash>"+
"</Docs>"+
/*"<OrgDetails>${organization Deatails}</OrgDetails>"+
*//*"<Signature></Signature>"+*/
"</Esign>";


public  static  String CDACRESPONSE="<EsignResp errCode=\"NA\" errMsg=\"NA\" resCode=\"1104100d-2530-4c5d-bbc7-96009dd8cdb8\" status=\"1\" ts=\"2018-03-07T21:11:49.544\" txn=\"30537\">\""+ 
		"		+ \"<UserX509Certificate>"+ 
		"</UserX509Certificate><Signatures><DocSignature error=\"NA\" id=\"1\" sigHashAlgorithm=\"SHA256\"></DocSignature></Signatures><Signature xmlns=\"http://www.w3.org/2000/09/xmldsig#\">"+ 
		"<SignedInfo>"+ 
		"<CanonicalizationMethod Algorithm=\"http://www.w3.org/TR/2001/REC-xml-c14n-20010315\"></CanonicalizationMethod>"+ 
		"<SignatureMethod Algorithm=\"http://www.w3.org/2000/09/xmldsig#rsa-sha1\"></SignatureMethod>"+ 
		"<Reference URI=\"\">"+ 
		"<Transforms>"+ 
		"<Transform Algorithm=\"http://www.w3.org/2000/09/xmldsig#enveloped-signature\"></Transform>"+ 
		"</Transforms>"+ 
		"<DigestMethod Algorithm=\"http://www.w3.org/2000/09/xmldsig#sha1\"></DigestMethod>"+ 
		"<DigestValue>ansxWa8OZzFE0lHHeBsQsKKawVg=</DigestValue>"+ 
		"</Reference>"+ 
		"</SignedInfo>"+ 
		"<SignatureValue>"+ 
		"</SignatureValue>"+ 
		"</Signature></EsignResp>";

}