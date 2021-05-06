-- DNLD
INSERT INTO aodbinterface.cminmsgs (CMINMSGS_CLOB_MSG, CMINMSGS_DATE_RECEIVED, CMINMSGS_STATUS) VALUES ('<?xml version="1.0" encoding="UTF-8"?>
<MSG xmlns:xsi = "http://www.w3.org/2001/XMLSchema-instance" xsi:noNamespaceSchemaLocation = "unisysaodbsis.xsd">
	<META>
		<SNDR>AODB</SNDR>
		<SEQN>1243</SEQN>
		<DTTM>20021010090311</DTTM>
		<TYPE>CHLT</TYPE>
		<STYP>DNLD</STYP>
	</META>
	<RECS>1</RECS>
	<CHLT>
		<CCOD>C11</CCOD>
		<CHNM>Chute 11 North side</CHNM>
		<CNMC>北方行李传送带十一</CNMC>
		<CTML>T3A</CTML>
		<CCAT>D</CCAT>
	</CHLT>
</MSG>', sysdate(), 'VALID');

-- ADD
INSERT INTO aodbinterface.cminmsgs (CMINMSGS_CLOB_MSG, CMINMSGS_DATE_RECEIVED, CMINMSGS_STATUS) VALUES ('<?xml version="1.0" encoding="UTF-8"?>
<MSG xmlns:xsi = "http://www.w3.org/2001/XMLSchema-instance" xsi:noNamespaceSchemaLocation = "unisysaodbsis.xsd">
	<META>
		<SNDR>AODB</SNDR>
		<SEQN>1243</SEQN>
		<DTTM>20021010090311</DTTM>
		<TYPE>CHLT</TYPE>
		<STYP>ADD</STYP>
	</META>
	<RECS>1</RECS>
	<CHLT>
		<CCOD>C12</CCOD>
		<CHNM>Chute 11 North side</CHNM>
		<CNMC>北方行李传送带十一</CNMC>
		<CTML>T3A</CTML>
		<CCAT>D</CCAT>
	</CHLT>
</MSG>', sysdate(), 'VALID');

-- Delete
INSERT INTO aodbinterface.cminmsgs (CMINMSGS_CLOB_MSG, CMINMSGS_DATE_RECEIVED, CMINMSGS_STATUS) VALUES ('<?xml version="1.0" encoding="UTF-8"?>
<MSG xmlns:xsi = "http://www.w3.org/2001/XMLSchema-instance" xsi:noNamespaceSchemaLocation = "unisysaodbsis.xsd">
	<META>
		<SNDR>AODB</SNDR>
		<SEQN>1243</SEQN>
		<DTTM>20021010090311</DTTM>
		<TYPE>CHLT</TYPE>
		<STYP>DEL</STYP>
	</META>
	<RECS>1</RECS>
	<CHLT>
		<CCOD>C12</CCOD>
	</CHLT>
</MSG>', sysdate(), 'VALID');
