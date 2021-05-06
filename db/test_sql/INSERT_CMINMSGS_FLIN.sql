-- UPD
INSERT INTO aodbinterface.cminmsgs (CMINMSGS_CLOB_MSG, CMINMSGS_DATE_RECEIVED, CMINMSGS_STATUS) VALUES ('<?xml version="1.0" encoding="UTF-8"?>
<MSG xmlns:xsi = "http://www.w3.org/2001/XMLSchema-instance" xsi:noNamespaceSchemaLocation = "unisysaodbsis.xsd">
	<META>
		<SNDR>AODB</SNDR>
		<SEQN>173243</SEQN>
		<DTTM>20021010090311</DTTM>
		<TYPE>FLOP</TYPE>
		<STYP>FLIN</STYP>
	</META>
	<FLOP>
		<FLID>121112312</FLID>
		<FFID>CA-CA101-A-12DEC031345-I</FFID>
		<FLIN>I</FLIN>
	</FLOP>
</MSG>', sysdate(), 'VALID');

