-- DNLD
INSERT INTO aodbinterface.cminmsgs (CMINMSGS_CLOB_MSG, CMINMSGS_DATE_RECEIVED, CMINMSGS_STATUS) VALUES ('<?xml version="1.0" encoding="UTF-8"?>
<MSG xmlns:xsi = "http://www.w3.org/2001/XMLSchema-instance" xsi:noNamespaceSchemaLocation = "unisysaodbsis.xsd">    
	<META>
		<SNDR>AODB</SNDR>
		<SEQN>1243</SEQN>
		<DTTM>20021010090311</DTTM>
		<TYPE>REGN</TYPE>
		<STYP>DNLD</STYP>
	</META>
	<RECS>2</RECS>
	<REGN>
		<RNUM>A40LF</RNUM>
		<ITAT>312</ITAT>
		<OWID>52156</OWID>
		<ACAL>CA</ACAL>
		<ALSC>WN</ALSC>
		<MAXP>200</MAXP>
		<MFWT></MFWT>
		<MTWT></MTWT>
	</REGN>
	<REGN>
		<RNUM>A6AIN</RNUM>
		<ITAT>320</ITAT>
		<OWID>521526</OWID>
		<ACAL>CZ</ACAL>
		<ALSC></ALSC>
		<MAXP></MAXP>
		<MFWT></MFWT>
		<MTWT></MTWT>
	</REGN>
	</MSG>', sysdate(), 'VALID');

-- ADD
INSERT INTO aodbinterface.cminmsgs (CMINMSGS_CLOB_MSG, CMINMSGS_DATE_RECEIVED, CMINMSGS_STATUS) VALUES ('<?xml version="1.0" encoding="UTF-8"?>
<MSG xmlns:xsi = "http://www.w3.org/2001/XMLSchema-instance" xsi:noNamespaceSchemaLocation = "unisysaodbsis.xsd">
	<META>
		<SNDR>AODB</SNDR>
		<SEQN>1243</SEQN>
		<DTTM>20021010090311</DTTM>
		<TYPE>REGN</TYPE>
		<STYP>ADD</STYP>
	</META>
	<RECS>1</RECS>
	<REGN>
		<RNUM>B2290</RNUM>
		<ITAT>321</ITAT>
		<OWID>52156</OWID>
		<ACAL>CA</ACAL>
		<ALSC>WN</ALSC>
		<MAXP>200</MAXP>
		<MFWT></MFWT>
		<MTWT></MTWT>
	</REGN>
	</MSG>', sysdate(), 'VALID');

-- Delete
INSERT INTO aodbinterface.cminmsgs (CMINMSGS_CLOB_MSG, CMINMSGS_DATE_RECEIVED, CMINMSGS_STATUS) VALUES ('<?xml version="1.0" encoding="UTF-8"?>
<MSG xmlns:xsi = "http://www.w3.org/2001/XMLSchema-instance" xsi:noNamespaceSchemaLocation = "unisysaodbsis.xsd">
	<META>
		<SNDR>AODB</SNDR>
		<SEQN>1243</SEQN>
		<DTTM>20021010090311</DTTM>
		<TYPE>REGN</TYPE>
		<STYP>DEL</STYP>
	</META>
	<RECS>1</RECS>
	<REGN>
		<RNUM>B2290</RNUM>
		<ITAT>321</ITAT>
		<OWID>52156</OWID>
		<ACAL>CA</ACAL>
		<ALSC>EN</ALSC>
		<MAXP>200</MAXP>
		<MFWT></MFWT>
		<MTWT></MTWT>
	</REGN>
	</MSG>', sysdate(), 'VALID');

-- RESP
INSERT INTO aodbinterface.cminmsgs (CMINMSGS_CLOB_MSG, CMINMSGS_DATE_RECEIVED, CMINMSGS_STATUS) VALUES ('<?xml version="1.0" encoding="UTF-8"?>
<MSG xmlns:xsi = "http://www.w3.org/2001/XMLSchema-instance" xsi:noNamespaceSchemaLocation = "unisysaodbsis.xsd">
	<META>
		<SNDR>AODB</SNDR>
		<SEQN>1243</SEQN>
		<DTTM>20021010090311</DTTM>
		<TYPE>REGN</TYPE>
		<STYP>RESP</STYP>
	</META>
	<RECS>2</RECS>
	<REGN>
		<RNUM>A40LF</RNUM>
		<ITAT>312</ITAT>
		<OWID>52156</OWID>
		<ACAL>CA</ACAL>
		<ALSC>WN</ALSC>
		<MAXP>200</MAXP>
		<MFWT></MFWT>
		<MTWT></MTWT>
	</REGN>
	<REGN>
		<RNUM>A6AIN</RNUM>
		<ITAT>320</ITAT>
		<OWID>521526</OWID>
		<ACAL>CZ</ACAL>
		<ALSC></ALSC>
		<MAXP></MAXP>
		<MFWT></MFWT>
		<MTWT></MTWT>
	</REGN>
	</MSG>', sysdate(), 'VALID');

-- UPD
INSERT INTO aodbinterface.cminmsgs (CMINMSGS_CLOB_MSG, CMINMSGS_DATE_RECEIVED, CMINMSGS_STATUS) VALUES ('<?xml version="1.0" encoding="UTF-8"?>
<MSG xmlns:xsi = "http://www.w3.org/2001/XMLSchema-instance" xsi:noNamespaceSchemaLocation = "unisysaodbsis.xsd">
	<META>
		<SNDR>AODB</SNDR>
		<SEQN>1243</SEQN>
		<DTTM>20021010090311</DTTM>
		<TYPE>REGN</TYPE>
		<STYP>UPD</STYP>
	</META>
	<RECS>1</RECS>
	<REGN>
		<RNUM>B2290</RNUM>
		<ITAT>321</ITAT>
		<OWID>52156</OWID>
		<ACAL>CA</ACAL>
		<ALSC>EN</ALSC>
		<MAXP>200</MAXP>
		<MFWT>888</MFWT>
		<MTWT>999</MTWT>
	</REGN>
	</MSG>', sysdate(), 'VALID');
