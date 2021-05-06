-- UPD
INSERT INTO aodbinterface.cminmsgs (CMINMSGS_CLOB_MSG, CMINMSGS_DATE_RECEIVED, CMINMSGS_STATUS) VALUES ('<?xml version="1.0" encoding="UTF-8"?>
<MSG xmlns:xsi = "http://www.w3.org/2001/XMLSchema-instance" xsi:noNamespaceSchemaLocation = "unisysaodbsis.xsd">
	<META>
		<SNDR>AODB</SNDR>
		<SEQN>1243</SEQN>
		<DTTM>20180810090311</DTTM>
		<TYPE>FLOP</TYPE>
		<STYP>PSHT</STYP>
	</META>
	<FLOP>
		<FLID>121112312</FLID>
		<FFID>CA-CA101-A-10AUG181345-D</FFID>
		<PSHT PSHN="1">
			<PSTM>10AUG181346</PSTM>
		    <PSID>IN</PSID>
		    <PUST>504</PUST>
		</PSHT>
	</FLOP>
</MSG>
', sysdate(), 'VALID');

