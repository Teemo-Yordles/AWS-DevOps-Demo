-- UPD
INSERT INTO aodbinterface.cminmsgs (CMINMSGS_CLOB_MSG, CMINMSGS_DATE_RECEIVED, CMINMSGS_STATUS) VALUES ('<?xml version="1.0" encoding="UTF-8"?>
<MSG xmlns:xsi = "http://www.w3.org/2001/XMLSchema-instance" xsi:noNamespaceSchemaLocation = "unisysaodbsis.xsd">
	<META>
		<SNDR>AODB</SNDR>
		<SEQN>92643</SEQN>
		<DTTM>20061210090311</DTTM>
		<TYPE>FLOP</TYPE>
		<STYP>ABTM</STYP>
	</META>
	<FLOP>
		<FLID>121112312</FLID>
		<FFID>CA-101-D-10DEC061345-D</FFID>
		<ABTM ASNO="1" >
			<ABDG>212</ABDG>
			<ABOP>D</ABOP>
			<AOTM>10DEC061343</AOTM>
		</ABTM>
	</FLOP>
</MSG>', sysdate(), 'VALID');

