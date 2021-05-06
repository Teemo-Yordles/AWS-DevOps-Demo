-- UPD
INSERT INTO aodbinterface.cminmsgs (CMINMSGS_CLOB_MSG, CMINMSGS_DATE_RECEIVED, CMINMSGS_STATUS) VALUES ('<?xml version="1.0" encoding="UTF-8"?>
<MSG xmlns:xsi = "http://www.w3.org/2001/XMLSchema-instance" xsi:noNamespaceSchemaLocation = "unisysaodbsis.xsd">
	<META>
		<SNDR>AODB</SNDR>
		<SEQN>1243</SEQN>
		<DTTM>20021010092111</DTTM>
		<TYPE>FLOP</TYPE>
		<STYP>SRVT</STYP>
	</META>
	<FLOP>
		<FLID>15</FLID>
		<FFID>CA-CA101-A-12DEC211345-D</FFID>
	<SRVT>
       <SRTC>ATN</SRTC>
       <SRQT>2</SRQT>
       <SRST>12DEC211400</SRST>
       <SRET>12DEC211430</SRET>
       <SRPR></SRPR>
       <SANR>MACHINE</SANR>
       <SARR>由于机器问题延误服务事务</SARR>
     </SRVT>
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
		<STYP>SRVT</STYP>
	</META>
	<FLOP>
		<FLID>15</FLID>
		<FFID>CA-CA101-A-12DEC211345-D</FFID>
	<SRVT>
       <SRTC>ATN</SRTC>
       <SRQT>2</SRQT>
       <SRST>12DEC231400</SRST>
       <SRET>12DEC231430</SRET>
       <SRPR></SRPR>
       <SANR>MAC</SANR>
       <SARR>由于机器问题</SARR>
	</SRVT>
	<SRVT>
       <SRTC>ATL</SRTC>
       <SRQT>2</SRQT>
       <SRST></SRST>
       <SRET></SRET>
       <SRPR></SRPR>
     </SRVT>
    <SRVT>
       <SRTC>ACCN</SRTC>
       <SRQT>0</SRQT>
       <SRST>12DEC211400</SRST>
       <SRET>12DEC211430</SRET>
       <SRPR>107007</SRPR>
     </SRVT>
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
		<STYP>SRVT</STYP>
	</META>
	<FLOP>
		<FLID>15</FLID>
		<FFID>CA-CA101-A-12DEC211345-D</FFID>
		<SRVT></SRVT>
	</FLOP>
</MSG>
', sysdate(), 'VALID');



