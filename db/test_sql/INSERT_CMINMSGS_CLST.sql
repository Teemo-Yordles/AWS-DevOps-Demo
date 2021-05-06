-- DNLD
INSERT INTO aodbinterface.cminmsgs (CMINMSGS_CLOB_MSG, CMINMSGS_DATE_RECEIVED, CMINMSGS_STATUS) VALUES ('<?xml version="1.0" encoding="UTF-8"?>
<MSG xmlns:xsi = "http://www.w3.org/2001/XMLSchema-instance" xsi:noNamespaceSchemaLocation = "unisysaodbsis.xsd">
	<META>
		<SNDR>AODB</SNDR>
		<SEQN>1243</SEQN>
		<DTTM>20021010090311</DTTM>
		<TYPE>CLST</TYPE>
		<STYP>DNLD</STYP>
	</META>
	<RECS>1</RECS>
	<CLST>
		<CCOD>C11</CCOD>
		<CTNM>Desk 11 North side</CTNM>
		<CNMC>北方柜台十一</CNMC>
		<CCAT>D</CCAT>
		<CTML>T3A</CTML>
		<CTRA>Y</CTRA>
	</CLST>
</MSG>', sysdate(), 'VALID');

-- ADD
INSERT INTO aodbinterface.cminmsgs (CMINMSGS_CLOB_MSG, CMINMSGS_DATE_RECEIVED, CMINMSGS_STATUS) VALUES ('<?xml version="1.0" encoding="UTF-8"?>
<MSG xmlns:xsi = "http://www.w3.org/2001/XMLSchema-instance" xsi:noNamespaceSchemaLocation = "unisysaodbsis.xsd">
	<META>
		<SNDR>AODB</SNDR>
		<SEQN>1243</SEQN>
		<DTTM>20021010090311</DTTM>
		<TYPE>CLST</TYPE>
		<STYP>ADD</STYP>
	</META>
	<RECS>1</RECS>
	<CLST>
		<CCOD>C12</CCOD>
		<CTNM>Desk 11 North side</CTNM>
		<CNMC>北方柜台十一</CNMC>
		<CCAT>D</CCAT>
		<CTML>T3A</CTML>
		<CTRA>Y</CTRA>
	</CLST>
</MSG>', sysdate(), 'VALID');

-- Delete
INSERT INTO aodbinterface.cminmsgs (CMINMSGS_CLOB_MSG, CMINMSGS_DATE_RECEIVED, CMINMSGS_STATUS) VALUES ('<?xml version="1.0" encoding="UTF-8"?>
<MSG xmlns:xsi = "http://www.w3.org/2001/XMLSchema-instance" xsi:noNamespaceSchemaLocation = "unisysaodbsis.xsd">
	<META>
		<SNDR>AODB</SNDR>
		<SEQN>1243</SEQN>
		<DTTM>20021010090311</DTTM>
		<TYPE>CLST</TYPE>
		<STYP>DEL</STYP>
	</META>
	<RECS>1</RECS>
	<CLST>
		<CCOD>C11</CCOD>
	</CLST>
</MSG>', sysdate(), 'VALID');
