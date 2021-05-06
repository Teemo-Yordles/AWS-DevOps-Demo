-- UPD
INSERT INTO aodbinterface.cminmsgs (CMINMSGS_CLOB_MSG, CMINMSGS_DATE_RECEIVED, CMINMSGS_STATUS) VALUES ('<?xml version="1.0" encoding="UTF-8"?>
<MSG xmlns:xsi = "http://www.w3.org/2001/XMLSchema-instance" xsi:noNamespaceSchemaLocation = "unisysaodbsis.xsd">
	<META>
		<SNDR>AODB</SNDR>
		<SEQN>1243</SEQN>
		<DTTM>20021010092111</DTTM>
		<TYPE>FLOP</TYPE>
		<STYP>ERUT</STYP>
	</META>
	<FLOP>
		<FLID>16</FLID>
		<FFID>CA-CA101-A-12DEC211345-D</FFID>
		<ERUT RTNO="1">
			<APCD>SHA</APCD>
			<SCDT>15DEC211140</SCDT>
		</ERUT>
		<ERUT RTNO="2">
			<APCD>CTU</APCD>
			<SCAT>15DEC211345</SCAT>
			<SCDT>15DEC211640</SCDT>
		</ERUT>
		<ERUT RTNO="3">
			<APCD>BAV</APCD>
			<SCAT>15DEC211840</SCAT>
		</ERUT>
	</FLOP>
</MSG>
', sysdate(), 'VALID');

-- UPD
INSERT INTO aodbinterface.cminmsgs (CMINMSGS_CLOB_MSG, CMINMSGS_DATE_RECEIVED, CMINMSGS_STATUS) VALUES ('<?xml version="1.0" encoding="UTF-8"?>
<MSG xmlns:xsi = "http://www.w3.org/2001/XMLSchema-instance" xsi:noNamespaceSchemaLocation = "unisysaodbsis.xsd">
	<META>
		<SNDR>AODB</SNDR>
		<SEQN>1243</SEQN>
		<DTTM>20021010092111</DTTM>
		<TYPE>FLOP</TYPE>
		<STYP>ERUT</STYP>
	</META>
	<FLOP>
		<FLID>16</FLID>
		<FFID>CA-CA101-A-12DEC211345-D</FFID>
		<ERUT RTNO="1">
			<APCD>XXX</APCD>
			<SCDT>15DEC251140</SCDT>
		</ERUT>
		<ERUT RTNO="2">
			<APCD>YYY</APCD>
			<SCAT>15DEC251345</SCAT>
			<SCDT>15DEC251640</SCDT>
		</ERUT>
		<ERUT RTNO="3">
			<APCD>ZZZ</APCD>
			<SCAT>15DEC251840</SCAT>
		</ERUT>
	</FLOP>
</MSG>
', sysdate(), 'VALID');

