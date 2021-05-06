-- UPD
INSERT INTO aodbinterface.cminmsgs (CMINMSGS_CLOB_MSG, CMINMSGS_DATE_RECEIVED, CMINMSGS_STATUS) VALUES ('<?xml version="1.0" encoding="UTF-8"?>
<MSG xmlns:xsi = "http://www.w3.org/2001/XMLSchema-instance" xsi:noNamespaceSchemaLocation = "unisysaodbsis.xsd">
	<META>
		<SNDR>AODB</SNDR>
		<SEQN>1243</SEQN>
		<DTTM>20120308090311</DTTM>
		<TYPE>FLOP</TYPE>
		<STYP>PEDT</STYP>
	</META>
	<FLOP>
		<FLID>121112312</FLID>
		<FFID>CA-CA101-A-08MAR121345-D</FFID>
		<PEDT>08MAR120901</PEDT>
	</FLOP>
</MSG>', sysdate(), 'VALID');

