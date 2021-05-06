-- DNLD
INSERT INTO aodbinterface.cminmsgs (CMINMSGS_CLOB_MSG, CMINMSGS_DATE_RECEIVED, CMINMSGS_STATUS) VALUES ('<?xml version="1.0" encoding="UTF-8"?>
<MSG xmlns:xsi = "http://www.w3.org/2001/XMLSchema-instance" xsi:noNamespaceSchemaLocation = "unisysaodbsis.xsd">    
	<META>
		<SNDR>AODB</SNDR>
		<SEQN>1243</SEQN>
		<DTTM>20021010090311</DTTM>
		<TYPE>GLST</TYPE>
		<STYP>DNLD</STYP>
	</META>
	<RECS>1</RECS>
	<GLST>
		<GCOD>G17</GCOD>
		<GTNM>Gate for CA</GTNM>
		<GNMC>Gate for CA中文</GNMC>
		<GCAT>D</GCAT>
		<GTML>T1</GTML>
		<PIER>C</PIER>
	</GLST>
	</MSG>', sysdate(), 'VALID');

-- RESP
INSERT INTO aodbinterface.cminmsgs (CMINMSGS_CLOB_MSG, CMINMSGS_DATE_RECEIVED, CMINMSGS_STATUS) VALUES ('<?xml version="1.0" encoding="UTF-8"?>
<MSG xmlns:xsi = "http://www.w3.org/2001/XMLSchema-instance" xsi:noNamespaceSchemaLocation = "unisysaodbsis.xsd">
	<META>
		<SNDR>AODB</SNDR>
		<SEQN>1243</SEQN>
		<DTTM>20021010090311</DTTM>
		<TYPE>GLST</TYPE>
		<STYP>RESP</STYP>
	</META>
	<RECS>1</RECS>
	<GLST>
		<GCOD>G17</GCOD>
		<GTNM>Gate for CA</GTNM>
		<GNMC>Gate for CA中文</GNMC>
		<GCAT>D</GCAT>
		<GTML>T1</GTML>
		<PIER>C</PIER>
	</GLST>
	</MSG>', sysdate(), 'VALID');

-- ADD
INSERT INTO aodbinterface.cminmsgs (CMINMSGS_CLOB_MSG, CMINMSGS_DATE_RECEIVED, CMINMSGS_STATUS) VALUES ('<?xml version="1.0" encoding="UTF-8"?>
<MSG xmlns:xsi = "http://www.w3.org/2001/XMLSchema-instance" xsi:noNamespaceSchemaLocation = "unisysaodbsis.xsd">
	<META>
		<SNDR>AODB</SNDR>
		<SEQN>1243</SEQN>
		<DTTM>20021010090311</DTTM>
		<TYPE>GLST</TYPE>
		<STYP>ADD</STYP>
	</META>
	<RECS>1</RECS>
	<GLST>
		<GCOD>G17</GCOD>
		<GTNM>Gate for CA</GTNM>
		<GNMC>Gate for CA中文</GNMC>
		<GCAT>D</GCAT>
		<GTML>T1</GTML>
		<PIER>C</PIER>
	</GLST>
	</MSG>', sysdate(), 'VALID');

-- UPD
INSERT INTO aodbinterface.cminmsgs (CMINMSGS_CLOB_MSG, CMINMSGS_DATE_RECEIVED, CMINMSGS_STATUS) VALUES ('<?xml version="1.0" encoding="UTF-8"?>
<MSG xmlns:xsi = "http://www.w3.org/2001/XMLSchema-instance" xsi:noNamespaceSchemaLocation = "unisysaodbsis.xsd">
	<META>
		<SNDR>AODB</SNDR>
		<SEQN>1243</SEQN>
		<DTTM>20021010090311</DTTM>
		<TYPE>GLST</TYPE>
		<STYP>UPD</STYP>
	</META>
	<RECS>1</RECS>
	<GLST>
		<GCOD>G17</GCOD>
		<GTNM>Gate for CA</GTNM>
		<GNMC> CA中文</GNMC>
		<GCAT>D</GCAT>
		<GTML>T1</GTML>
		<PIER>C</PIER>
	</GLST>
	</MSG>', sysdate(), 'VALID');

-- DEL
INSERT INTO aodbinterface.cminmsgs (CMINMSGS_CLOB_MSG, CMINMSGS_DATE_RECEIVED, CMINMSGS_STATUS) VALUES ('<?xml version="1.0" encoding="UTF-8"?>
<MSG xmlns:xsi = "http://www.w3.org/2001/XMLSchema-instance" xsi:noNamespaceSchemaLocation = "unisysaodbsis.xsd">
	<META>
		<SNDR>AODB</SNDR>
		<SEQN>1243</SEQN>
		<DTTM>20021010090311</DTTM>
		<TYPE>GLST</TYPE>
		<STYP>DEL</STYP>
	</META>
	<RECS>1</RECS>
	<GLST>
		<GCOD>G17</GCOD>
		<GTNM>Gate for CA</GTNM>
		<GNMC>Gate for CA中文</GNMC>
		<GCAT>D</GCAT>
		<GTML>T1</GTML>
		<PIER>C</PIER>
	</GLST>
	</MSG>', sysdate(), 'VALID');
