package com.evolvus.cda.cdacesign;

public interface Pain009Request {
	
	
	 static String  Pain009="<?xml version=\"1.0\" encoding=\"UTF-8\"?>"+ 
			"<Document"+ 
			"	xmlns=\"urn:iso:std:iso:20022:tech:xsd:pain.009.001.04\">"+ 
			"	<MndtInitnReq>"+ 
			"		<GrpHdr>"+ 
			"			<MsgId>${msgId}</MsgId>"+ 
			"			<CreDtTm>2017-12-22T12:39:00"+ 
			"			</CreDtTm>"+ 
			"			<InstgAgt>"+ 
			"				<FinInstnId>"+ 
			"					<ClrSysMmbId>"+ 
			"						<MmbId>${sponserBankCode}</MmbId>"+ 
			"					</ClrSysMmbId>"+ 
			"					<Nm>${sponserBankName}</Nm>"+ 
			"				</FinInstnId>"+ 
			"			</InstgAgt>"+ 
			"			<InstdAgt>"+ 
			"				<FinInstnId>"+ 
			"					<ClrSysMmbId>"+ 
			"						<MmbId>${destbankCode}</MmbId>"+ 
			"					</ClrSysMmbId>"+ 
			"					<Nm>${destbankName}</Nm>"+ 
			"				</FinInstnId>"+ 
			"			</InstdAgt>"+ 
			"		</GrpHdr>"+ 
			"		<Mndt>"+ 
			"			<MndtReqId>${mndtReqRef}</MndtReqId>"+ 
			"			<Tp>"+ 
			"				<SvcLvl>"+ 
			"					<Prtry>${mandateCataogoryCode}</Prtry>"+ 
			"				</SvcLvl>"+ 
			"				<LclInstrm>"+ 
			"					<Prtry>DEBIT</Prtry>"+ 
			"				</LclInstrm>"+ 
			"			</Tp>"+ 
			"			<Ocrncs>"+ 
			"				<SeqTp>RCUR</SeqTp>"+ 
			"				<Frqcy>${frequency}</Frqcy>"+ 
			"				<FrstColltnDt>${mndStartDate}</FrstColltnDt>"+ 
			"				<FnlColltnDt>${mndEnddate}</FnlColltnDt>"+ 
			"			</Ocrncs>"+ 
			"			<ColltnAmt Ccy=\"INR\">${collectAmount}</ColltnAmt>"+ 
			"<MaxAmt Ccy=\"INR\">${maxAmt}</MaxAmt>"+
			"			<Cdtr>"+ 
			"				<Nm>${corporateName}</Nm>"+ 
			"			</Cdtr>"+ 
			"			<CdtrAcct>"+ 
			"				<Id>"+ 
			"					<Othr>"+ 
			"						<Id>${utilityCode}</Id>"+ 
			"					</Othr>"+ 
			"				</Id>"+ 
			"			</CdtrAcct>"+ 
			"			<CdtrAgt>"+ 
			"				<FinInstnId>"+ 
			"					<ClrSysMmbId>"+ 
			"						<MmbId>${sponserBankCode}</MmbId>"+ 
			"					</ClrSysMmbId>"+ 
			"					<Nm>${sponserBankName}</Nm>"+ 
			"				</FinInstnId>"+ 
			"			</CdtrAgt>"+ 
			"			<Dbtr>"+ 
			"				<Nm>${payerName}</Nm>"+ 
			"				<Id>"+ 
			"					<PrvtId>"+ 
			"						<Othr>"+ 
			"							<Id>${consumerRefeNumber}</Id>"+ 
			"						</Othr>"+ 
			"					</PrvtId>"+ 
			"				</Id>"+ 
			"				<CtctDtls>"+ 
			"        <Nm>${aadharNumber}</Nm>"+
			"					<PhneNb>+91-080-12345678</PhneNb>"+ 
			"					<MobNb>${mobileNo}</MobNb>"+ 
			"					<EmailAdr>${emailId}</EmailAdr>"+ 
			"				</CtctDtls>"+ 
			"			</Dbtr>"+ 
			"			<DbtrAcct>"+ 
			"				<Id>"+ 
			"					<Othr>"+ 
			"						<Id>${legalAccountNumber}</Id>"+ 
			"					</Othr>"+ 
			"				</Id>"+ 
			"				<Tp>"+ 
			"					<Prtry>${customerAccType}</Prtry>"+ 
			"				</Tp>"+ 
			"			</DbtrAcct>"+ 
			"			<DbtrAgt>"+ 
			"				<FinInstnId>"+ 
			"					<ClrSysMmbId>"+ 
			"						<MmbId>${destbankCode}</MmbId>"+ 
			"					</ClrSysMmbId>"+ 
			"					<Nm>${destbankName}</Nm>"+ 
			"				</FinInstnId>"+ 
			"			</DbtrAgt>"+ 
			"		</Mndt>"+ 
			"	</MndtInitnReq>"+ 
			"</Document>";
	
}
