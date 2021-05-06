-- DNLD
INSERT INTO aodbinterface.cminmsgs (CMINMSGS_CLOB_MSG, CMINMSGS_DATE_RECEIVED, CMINMSGS_STATUS) VALUES ('<?xml version="1.0" encoding="UTF-8"?>
<MSG xmlns:xsi = "http://www.w3.org/2001/XMLSchema-instance" xsi:noNamespaceSchemaLocation = "unisysaodbsis.xsd">
	<META>
		<SNDR>AODB</SNDR>
		<SEQN>1243</SEQN>
		<DTTM>20120229090311</DTTM>
		<TYPE>ABDG</TYPE>
		<STYP>DNLD</STYP>
	</META>
	<RECS>2</RECS>
	<ABDG>
		<ABCD>212</ABCD>
		<DESC>Airbridge 212</DESC>
		<CDSC>登机桥 212</CDSC>
		<AHGT></AHGT>
		<ATML>T2</ATML>
		<ATHE>N</ATHE>
		<STND>212</STND>
		<GATE>21</GATE>
		<AMNO>1</AMNO>
	</ABDG>
	<ABDG>
		<ABCD>112</ABCD>
		<DESC>Airbridge 112</DESC>
		<CDSC>登机桥 112</CDSC>
		<AHGT></AHGT>
		<ATML>T1</ATML>
		<ATHE>Y</ATHE>
		<STND>112</STND>
		<STND>111</STND>
		<GATE>11A</GATE>
		<GATE>11B</GATE>
		<AMNO>2</AMNO>
	</ABDG>
	</MSG>', sysdate(), 'VALID');

-- RESP
INSERT INTO aodbinterface.cminmsgs (CMINMSGS_CLOB_MSG, CMINMSGS_DATE_RECEIVED, CMINMSGS_STATUS) VALUES ('<?xml version="1.0" encoding="UTF-8"?>
<MSG xmlns:xsi = "http://www.w3.org/2001/XMLSchema-instance" xsi:noNamespaceSchemaLocation = "unisysaodbsis.xsd">
	<META>
		<SNDR>AODB</SNDR>
		<SEQN>1243</SEQN>
		<DTTM>20120229090411</DTTM>
		<TYPE>ABDG</TYPE>
		<STYP>RESP</STYP>
	</META>
	<RECS>2</RECS>
	<ABDG>
		<ABCD>212</ABCD>
		<DESC>Airbridge 212</DESC>
		<CDSC>登机桥 212</CDSC>
		<AHGT></AHGT>
		<ATML>T2</ATML>
		<ATHE>N</ATHE>
		<STND>212</STND>
		<GATE>21</GATE>
		<AMNO>1</AMNO>
	</ABDG>
	<ABDG>
		<ABCD>112</ABCD>
		<DESC>Airbridge 112</DESC>
		<CDSC>登机桥 112</CDSC>
		<AHGT></AHGT>
		<ATML>T1</ATML>
		<ATHE>Y</ATHE>
		<STND>112</STND>
		<STND>111</STND>
		<GATE>11A</GATE>
		<GATE>11B</GATE>
		<AMNO>2</AMNO>
	</ABDG>
	</MSG>', sysdate(), 'VALID');

-- ADD
INSERT INTO aodbinterface.cminmsgs (CMINMSGS_CLOB_MSG, CMINMSGS_DATE_RECEIVED, CMINMSGS_STATUS) VALUES ('<?xml version="1.0" encoding="UTF-8"?>
<MSG xmlns:xsi = "http://www.w3.org/2001/XMLSchema-instance" xsi:noNamespaceSchemaLocation = "unisysaodbsis.xsd">
	<META>
		<SNDR>AODB</SNDR>
		<SEQN>1243</SEQN>
		<DTTM>20120229090511</DTTM>
		<TYPE>ABDG</TYPE>
		<STYP>ADD</STYP>
	</META>
	<RECS>1</RECS>
	<ABDG>
		<ABCD>213</ABCD>
		<DESC>Airbridge 213</DESC>
		<CDSC>登机桥 213</CDSC>
		<AHGT></AHGT>
		<ATML>T2</ATML>
		<ATHE>N</ATHE>
		<STND>213</STND>
        <STND></STND>
        <STND>613</STND>
		<GATE>23</GATE>
        <GATE></GATE>
        <GATE>223</GATE>
		<AMNO></AMNO>
	</ABDG>
	</MSG>', sysdate(), 'VALID');

-- UPD
INSERT INTO aodbinterface.cminmsgs (CMINMSGS_CLOB_MSG, CMINMSGS_DATE_RECEIVED, CMINMSGS_STATUS) VALUES ('<?xml version="1.0" encoding="UTF-8"?>
<MSG xmlns:xsi = "http://www.w3.org/2001/XMLSchema-instance" xsi:noNamespaceSchemaLocation = "unisysaodbsis.xsd">
	<META>
		<SNDR>AODB</SNDR>
		<SEQN>1243</SEQN>
		<DTTM>20120229090611</DTTM>
		<TYPE>ABDG</TYPE>
		<STYP>UPD</STYP>
	</META>
	<RECS>1</RECS>
	<ABDG>
		<ABCD>213</ABCD>
		<DESC>T2 Airbridge 213</DESC>
		<CDSC>登机桥 213</CDSC>
		<AHGT></AHGT>
		<ATML>T2</ATML>
		<ATHE>N</ATHE>
		<STND>213</STND>
        <STND>256</STND>
		<GATE>123</GATE>
        <GATE>233</GATE>
		<AMNO></AMNO>
	</ABDG>
	</MSG>', sysdate(), 'VALID');

-- DEL
INSERT INTO aodbinterface.cminmsgs (CMINMSGS_CLOB_MSG, CMINMSGS_DATE_RECEIVED, CMINMSGS_STATUS) VALUES ('<?xml version="1.0" encoding="UTF-8"?>
<MSG xmlns:xsi = "http://www.w3.org/2001/XMLSchema-instance" xsi:noNamespaceSchemaLocation = "unisysaodbsis.xsd">
	<META>
		<SNDR>AODB</SNDR>
		<SEQN>1243</SEQN>
		<DTTM>20120229090711</DTTM>
		<TYPE>ABDG</TYPE>
		<STYP>DEL</STYP>
	</META>
	<RECS>1</RECS>
	<ABDG>
		<ABCD>213</ABCD>
		<DESC>T2 Airbridge 213</DESC>
		<CDSC>登机桥 213</CDSC>
		<AHGT></AHGT>
		<ATML>T2</ATML>
		<ATHE>N</ATHE>
		<STND>213</STND>
		<GATE>23</GATE>
		<AMNO></AMNO>
	</ABDG>
	</MSG>', sysdate(), 'VALID');
