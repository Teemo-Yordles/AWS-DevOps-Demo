-- UPD
INSERT INTO aodbinterface.cminmsgs (CMINMSGS_CLOB_MSG, CMINMSGS_DATE_RECEIVED, CMINMSGS_STATUS) VALUES ('<?xml version="1.0" encoding="UTF-8"?>
<MSG xmlns:xsi = "http://www.w3.org/2001/XMLSchema-instance" xsi:noNamespaceSchemaLocation = "unisysaodbsis.xsd">
	<META>
		<SNDR>AODB</SNDR>
		<SEQN>1243</SEQN>
		<DTTM>20021010090311</DTTM>
		<TYPE>FLOP</TYPE>
		<STYP>DELY</STYP>
	</META>
	<FLOP>
		<FLID>15</FLID>
		<FFID>CA-CA101-A-12DEC031345-D</FFID>
		<DELY CODE="YY" STRT="15DEC031605" DURA="0200">YYYYYYY</DELY>
        <DELY CODE="XX" STRT="15DEC031605" DURA="0201">XXXXXXX</DELY>
        <DELY CODE="ZZ" STRT="15DEC031605" DURA="0202">ZZZZZZZ</DELY>
	</FLOP>
</MSG>
', sysdate(), 'VALID');

-- UPD
INSERT INTO aodbinterface.cminmsgs (CMINMSGS_CLOB_MSG, CMINMSGS_DATE_RECEIVED, CMINMSGS_STATUS) VALUES ('<?xml version="1.0" encoding="UTF-8"?>
<MSG xmlns:xsi = "http://www.w3.org/2001/XMLSchema-instance" xsi:noNamespaceSchemaLocation = "unisysaodbsis.xsd">
	<META>
		<SNDR>AODB</SNDR>
		<SEQN>1243</SEQN>
		<DTTM>20021010090311</DTTM>
		<TYPE>FLOP</TYPE>
		<STYP>DELY</STYP>
	</META>
	<FLOP>
		<FLID>15</FLID>
		<FFID>CA-CA101-A-12DEC031345-D</FFID>
		<DELY></DELY>
	</FLOP>
</MSG>
', sysdate(), 'VALID');
