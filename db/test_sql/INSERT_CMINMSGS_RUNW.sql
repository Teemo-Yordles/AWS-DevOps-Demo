-- UPD
INSERT INTO aodbinterface.cminmsgs (CMINMSGS_CLOB_MSG, CMINMSGS_DATE_RECEIVED, CMINMSGS_STATUS) VALUES ('<?xml version="1.0" encoding="UTF-8"?>
<MSG xmlns:xsi = "http://www.w3.org/2001/XMLSchema-instance" xsi:noNamespaceSchemaLocation = "unisysaodbsis.xsd">
	<META>
		<SNDR>AODB</SNDR>
		<SEQN>1243</SEQN>
		<DTTM>20180810090311</DTTM>
		<TYPE>FLOP</TYPE>
		<STYP>RUNW</STYP>
	</META>
	<FLOP>
		<FLID>121112312</FLID>
		<FFID>CA-CA101-A-10AUG181850-I</FFID>
		<RUNW>02L</RUNW>
	</FLOP>
</MSG>', sysdate(), 'VALID');

