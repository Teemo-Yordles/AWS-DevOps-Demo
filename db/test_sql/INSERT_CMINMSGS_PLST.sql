-- DNLD
INSERT INTO aodbinterface.cminmsgs (CMINMSGS_CLOB_MSG, CMINMSGS_DATE_RECEIVED, CMINMSGS_STATUS) VALUES ('<?xml version="1.0" encoding="UTF-8"?>
<MSG xmlns:xsi = "http://www.w3.org/2001/XMLSchema-instance" xsi:noNamespaceSchemaLocation = "unisysaodbsis.xsd">
	<META>
		<SNDR>AODB</SNDR>
		<SEQN>1243</SEQN>
		<DTTM>20111112120000</DTTM>
		<TYPE>PLST</TYPE>
		<STYP>DNLD</STYP>
	</META>
	<RECS>2</RECS>
	<PLST>
		<PCOD>C</PCOD>
		<PRNM>PIER C</PRNM>
		<PNMC>C指廊</PNMC>
		<PCAT>I</PCAT>
		<PTML>T1</PTML>
	</PLST>
	<PLST>
		<PCOD>D</PCOD>
		<PRNM>PIER D</PRNM>
		<PNMC>D指廊</PNMC>
		<PCAT>I</PCAT>
		<PTML>T1</PTML>
	</PLST>
</MSG>', sysdate(), 'VALID');

-- ADD
INSERT INTO aodbinterface.cminmsgs (CMINMSGS_CLOB_MSG, CMINMSGS_DATE_RECEIVED, CMINMSGS_STATUS) VALUES ('<?xml version="1.0" encoding="UTF-8"?>
<MSG xmlns:xsi = "http://www.w3.org/2001/XMLSchema-instance" xsi:noNamespaceSchemaLocation = "unisysaodbsis.xsd">
	<META>
		<SNDR>AODB</SNDR>
		<SEQN>1243</SEQN>
		<DTTM>20111112120000</DTTM>
		<TYPE>PLST</TYPE>
		<STYP>ADD</STYP>
	</META>
	<RECS>1</RECS>
	<PLST>
		<PCOD>E</PCOD>
		<PRNM>PIER E</PRNM>
		<PNMC>E指廊</PNMC>
		<PCAT>I</PCAT>
		<PTML>T1</PTML>
	</PLST>
</MSG>', sysdate(), 'VALID');
-- UPD

INSERT INTO aodbinterface.cminmsgs (CMINMSGS_CLOB_MSG, CMINMSGS_DATE_RECEIVED, CMINMSGS_STATUS) VALUES ('<?xml version="1.0" encoding="UTF-8"?>
<MSG xmlns:xsi = "http://www.w3.org/2001/XMLSchema-instance" xsi:noNamespaceSchemaLocation = "unisysaodbsis.xsd">
	<META>
		<SNDR>AODB</SNDR>
		<SEQN>1243</SEQN>
		<DTTM>20111112120000</DTTM>
		<TYPE>PLST</TYPE>
		<STYP>UPD</STYP>
	</META>
	<RECS>1</RECS>
	<PLST>
		<PCOD>E</PCOD>
		<PRNM>PIER E Updated</PRNM>
		<PNMC>E指廊 已更新</PNMC>
		<PCAT>D</PCAT>
		<PTML>T1</PTML>
	</PLST>
</MSG>', sysdate(), 'VALID');

-- Delete
INSERT INTO aodbinterface.cminmsgs (CMINMSGS_CLOB_MSG, CMINMSGS_DATE_RECEIVED, CMINMSGS_STATUS) VALUES ('<?xml version="1.0" encoding="UTF-8"?>
<MSG xmlns:xsi = "http://www.w3.org/2001/XMLSchema-instance" xsi:noNamespaceSchemaLocation = "unisysaodbsis.xsd">
	<META>
		<SNDR>AODB</SNDR>
		<SEQN>1243</SEQN>
		<DTTM>20111112120000</DTTM>
		<TYPE>PLST</TYPE>
		<STYP>DEL</STYP>
	</META>
	<RECS>1</RECS>
	<PLST>
		<PCOD>E</PCOD>
		<PRNM>PIER E Updated</PRNM>
		<PNMC>E指廊 已更新</PNMC>
		<PCAT>D</PCAT>
		<PTML>T1</PTML>
	</PLST>
</MSG>', sysdate(), 'VALID');

