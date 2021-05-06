-- DNLD
INSERT INTO aodbinterface.cminmsgs (CMINMSGS_CLOB_MSG, CMINMSGS_DATE_RECEIVED, CMINMSGS_STATUS) VALUES ('<?xml version="1.0" encoding="UTF-8"?>
<MSG xmlns:xsi = "http://www.w3.org/2001/XMLSchema-instance" xsi:noNamespaceSchemaLocation = "unisysaodbsis.xsd">    
	<META>
		<SNDR>AODB</SNDR>
		<SEQN>1243</SEQN>
	  <DTTM>20031010090311</DTTM>
		<TYPE>HLTR</TYPE>
		<STYP>DNLD</STYP>
	</META>
	<RECS>2</RECS>
	<HLTR>
		<HLTC>DIT</HLTC>
		<HLTD>DEICE START/END TIMING</HLTD>
		<HTDC>除冰开始结束时间</HTDC>
		<HLTT>T</HLTT>
		<UMNM></UMNM>
	</HLTR>
	<HLTR>
		<HLTC>WCH</HLTC>
		<HLTD>WHEELCHAIRS</HLTD>
		<HTDC>轮椅</HTDC>
		<HLTT>Q</HLTT>
		<UMNM></UMNM>
	</HLTR>
	</MSG>', sysdate(), 'VALID');

-- ADD
INSERT INTO aodbinterface.cminmsgs (CMINMSGS_CLOB_MSG, CMINMSGS_DATE_RECEIVED, CMINMSGS_STATUS) VALUES ('<?xml version="1.0" encoding="UTF-8"?>
<MSG xmlns:xsi = "http://www.w3.org/2001/XMLSchema-instance" xsi:noNamespaceSchemaLocation = "unisysaodbsis.xsd">
	<META>
		<SNDR>AODB</SNDR>
		<SEQN>1243</SEQN>
	  <DTTM>20031010090311</DTTM>
		<TYPE>HLTR</TYPE>
		<STYP>ADD</STYP>
	</META>
	<RECS>1</RECS>
	<HLTR>
		<HLTC>MNTT</HLTC>
		<HLTD>MAINTENANCE APPROVED TIME</HLTD>
		<HTDC>机务放行时间</HTDC>
		<HLTT>T</HLTT>
		<UMNM></UMNM>
	</HLTR>
	</MSG>', sysdate(), 'VALID');

-- Delete
INSERT INTO aodbinterface.cminmsgs (CMINMSGS_CLOB_MSG, CMINMSGS_DATE_RECEIVED, CMINMSGS_STATUS) VALUES ('<?xml version="1.0" encoding="UTF-8"?>
<MSG xmlns:xsi = "http://www.w3.org/2001/XMLSchema-instance" xsi:noNamespaceSchemaLocation = "unisysaodbsis.xsd">
	<META>
		<SNDR>AODB</SNDR>
		<SEQN>1243</SEQN>
	  <DTTM>20031010090311</DTTM>
		<TYPE>HLTR</TYPE>
		<STYP>DEL</STYP>
	</META>
	<RECS>1</RECS>
	<HLTR>
		<HLTC>MNTT</HLTC>
		<HLTD>MAINTENANCE APPROVED TIME</HLTD>
		<HTDC>机务放行的时间</HTDC>
		<HLTT>T</HLTT>
		<UMNM></UMNM>
	</HLTR>
	</MSG>', sysdate(), 'VALID');

-- RESP
INSERT INTO aodbinterface.cminmsgs (CMINMSGS_CLOB_MSG, CMINMSGS_DATE_RECEIVED, CMINMSGS_STATUS) VALUES ('<?xml version="1.0" encoding="UTF-8"?>
<MSG xmlns:xsi = "http://www.w3.org/2001/XMLSchema-instance" xsi:noNamespaceSchemaLocation = "unisysaodbsis.xsd">
	<META>
		<SNDR>AODB</SNDR>
		<SEQN>1243</SEQN>
	  <DTTM>20031010090311</DTTM>
		<TYPE>HLTR</TYPE>
		<STYP>RESP</STYP>
	</META>
	<RECS>2</RECS>
	<HLTR>
		<HLTC>DIT</HLTC>
		<HLTD>DEICE START/END TIMING</HLTD>
		<HTDC>除冰开始结束时间</HTDC>
		<HLTT>T</HLTT>
		<UMNM></UMNM>
	</HLTR>
	<HLTR>
		<HLTC>WCH</HLTC>
		<HLTD>WHEELCHAIRS</HLTD>
		<HTDC>轮椅</HTDC>
		<HLTT>Q</HLTT>
		<UMNM></UMNM>
	</HLTR>
	</MSG>', sysdate(), 'VALID');

-- UPD
INSERT INTO aodbinterface.cminmsgs (CMINMSGS_CLOB_MSG, CMINMSGS_DATE_RECEIVED, CMINMSGS_STATUS) VALUES ('<?xml version="1.0" encoding="UTF-8"?>
<MSG xmlns:xsi = "http://www.w3.org/2001/XMLSchema-instance" xsi:noNamespaceSchemaLocation = "unisysaodbsis.xsd">
	<META>
		<SNDR>AODB</SNDR>
		<SEQN>1243</SEQN>
	  <DTTM>20031010090311</DTTM>
		<TYPE>HLTR</TYPE>
		<STYP>UPD</STYP>
	</META>
	<RECS>1</RECS>
	<HLTR>
		<HLTC>MNTT</HLTC>
		<HLTD>MAINTENANCE APPROVED TIME</HLTD>
		<HTDC>放行的时间</HTDC>
		<HLTT>T</HLTT>
		<UMNM></UMNM>
	</HLTR>
	</MSG>', sysdate(), 'VALID');
