-- DNLD
INSERT INTO aodbinterface.cminmsgs (CMINMSGS_CLOB_MSG, CMINMSGS_DATE_RECEIVED, CMINMSGS_STATUS) VALUES ('<?xml version="1.0" encoding="UTF-8"?>
<MSG xmlns:xsi = "http://www.w3.org/2001/XMLSchema-instance" xsi:noNamespaceSchemaLocation = "unisysaodbsis.xsd">    
	<META>
		<SNDR>AODB</SNDR>
		<SEQN>1243</SEQN>
		<DTTM>20021010090311</DTTM>
		<TYPE>TLST</TYPE>
		<STYP>DNLD</STYP>
	</META>
	<RECS>1</RECS>
	<TLST>
		<TCOD>T3A</TCOD>
		<TNAM>TERMINAL T1</TNAM>
		<TNMC>T1航站楼</TNMC>
		<TCAT>D</TCAT>
		<TPAX>Y</TPAX>
	</TLST>
	</MSG>', sysdate(), 'VALID');

-- RESP
INSERT INTO aodbinterface.cminmsgs (CMINMSGS_CLOB_MSG, CMINMSGS_DATE_RECEIVED, CMINMSGS_STATUS) VALUES ('<?xml version="1.0" encoding="UTF-8"?>
<MSG xmlns:xsi = "http://www.w3.org/2001/XMLSchema-instance" xsi:noNamespaceSchemaLocation = "unisysaodbsis.xsd">
	<META>
		<SNDR>AODB</SNDR>
		<SEQN>1243</SEQN>
		<DTTM>20021010090311</DTTM>
		<TYPE>TLST</TYPE>
		<STYP>RESP</STYP>
	</META>
	<RECS>1</RECS>
	<TLST>
		<TCOD>T3A</TCOD>
		<TNAM>TERMINAL T1</TNAM>
		<TNMC>T1航站楼</TNMC>
		<TCAT>D</TCAT>
		<TPAX>Y</TPAX>
	</TLST>
	</MSG>', sysdate(), 'VALID');

-- ADD
INSERT INTO aodbinterface.cminmsgs (CMINMSGS_CLOB_MSG, CMINMSGS_DATE_RECEIVED, CMINMSGS_STATUS) VALUES ('<?xml version="1.0" encoding="UTF-8"?>
<MSG xmlns:xsi = "http://www.w3.org/2001/XMLSchema-instance" xsi:noNamespaceSchemaLocation = "unisysaodbsis.xsd">
	<META>
		<SNDR>AODB</SNDR>
		<SEQN>1243</SEQN>
		<DTTM>20021010090311</DTTM>
		<TYPE>TLST</TYPE>
		<STYP>ADD</STYP>
	</META>
	<RECS>1</RECS>
	<TLST>
		<TCOD>T3A</TCOD>
		<TNAM>TERMINAL T1</TNAM>
		<TNMC>T1航站楼</TNMC>
		<TCAT>D</TCAT>
		<TPAX>Y</TPAX>
	</TLST>
	</MSG>', sysdate(), 'VALID');

-- UPD
INSERT INTO aodbinterface.cminmsgs (CMINMSGS_CLOB_MSG, CMINMSGS_DATE_RECEIVED, CMINMSGS_STATUS) VALUES ('<?xml version="1.0" encoding="UTF-8"?>
<MSG xmlns:xsi = "http://www.w3.org/2001/XMLSchema-instance" xsi:noNamespaceSchemaLocation = "unisysaodbsis.xsd">
	<META>
		<SNDR>AODB</SNDR>
		<SEQN>1243</SEQN>
		<DTTM>20021010090311</DTTM>
		<TYPE>TLST</TYPE>
		<STYP>UPD</STYP>
	</META>
	<RECS>1</RECS>
	<TLST>
		<TCOD>T3A</TCOD>
		<TNAM>TERMINAL T1</TNAM>
		<TNMC>T1航站楼============</TNMC>
		<TCAT>D</TCAT>
		<TPAX>Y</TPAX>
	</TLST>
	</MSG>', sysdate(), 'VALID');

-- DEL
INSERT INTO aodbinterface.cminmsgs (CMINMSGS_CLOB_MSG, CMINMSGS_DATE_RECEIVED, CMINMSGS_STATUS) VALUES ('<?xml version="1.0" encoding="UTF-8"?>
<MSG xmlns:xsi = "http://www.w3.org/2001/XMLSchema-instance" xsi:noNamespaceSchemaLocation = "unisysaodbsis.xsd">
	<META>
		<SNDR>AODB</SNDR>
		<SEQN>1243</SEQN>
		<DTTM>20021010090311</DTTM>
		<TYPE>TLST</TYPE>
		<STYP>DEL</STYP>
	</META>
	<RECS>1</RECS>
	<TLST>
		<TCOD>T3A</TCOD>
		<TNAM>TERMINAL T1</TNAM>
		<TNMC>T1航站楼</TNMC>
		<TCAT>D</TCAT>
		<TPAX>Y</TPAX>
	</TLST>
	</MSG>', sysdate(), 'VALID');
