-- DNLD
INSERT INTO aodbinterface.cminmsgs (CMINMSGS_CLOB_MSG, CMINMSGS_DATE_RECEIVED, CMINMSGS_STATUS) VALUES ('<?xml version="1.0" encoding="UTF-8"?>
<MSG xmlns:xsi = "http://www.w3.org/2001/XMLSchema-instance" xsi:noNamespaceSchemaLocation = "unisysaodbsis.xsd">
	<META>
		<SNDR>AODB</SNDR>
		<SEQN>1243</SEQN>
		<DTTM>20021010090311</DTTM>
		<TYPE>FLTL</TYPE>
		<STYP>DNLD</STYP>
	</META>
	<RECS>2</RECS>
	<FLTL>
		<FTYP>J</FTYP>
		<CTYP>SCH</CTYP>
		<FDES>SCHEDULED FLT</FDES>
		<FDSC>定期航班</FDSC>
		<FCML>Y</FCML>
	</FLTL>
	<FLTL>
		<FTYP>I</FTYP>
		<CTYP></CTYP>
		<FDES>DIPLOMATIC USE</FDES>
		<FDSC>外交航班</FDSC>
		<FCML>N</FCML>
	</FLTL>
</MSG>', sysdate(), 'VALID');

-- ADD
INSERT INTO aodbinterface.cminmsgs (CMINMSGS_CLOB_MSG, CMINMSGS_DATE_RECEIVED, CMINMSGS_STATUS) VALUES ('<?xml version="1.0" encoding="UTF-8"?>
<MSG xmlns:xsi = "http://www.w3.org/2001/XMLSchema-instance" xsi:noNamespaceSchemaLocation = "unisysaodbsis.xsd">
	<META>
		<SNDR>AODB</SNDR>
		<SEQN>1243</SEQN>
		<DTTM>20021010090311</DTTM>
		<TYPE>FLTL</TYPE>
		<STYP>ADD</STYP>
	</META>
	<RECS>2</RECS>
	<FLTL>
		<FTYP>K</FTYP>
		<CTYP>SCH</CTYP>
		<FDES>SCHEDULED FLT</FDES>
		<FDSC>定期航班</FDSC>
		<FCML>Y</FCML>
	</FLTL>
</MSG>', sysdate(), 'VALID');

-- Delete
INSERT INTO aodbinterface.cminmsgs (CMINMSGS_CLOB_MSG, CMINMSGS_DATE_RECEIVED, CMINMSGS_STATUS) VALUES ('<?xml version="1.0" encoding="UTF-8"?>
<MSG xmlns:xsi = "http://www.w3.org/2001/XMLSchema-instance" xsi:noNamespaceSchemaLocation = "unisysaodbsis.xsd">
	<META>
		<SNDR>AODB</SNDR>
		<SEQN>1243</SEQN>
		<DTTM>20021010090311</DTTM>
		<TYPE>FLTL</TYPE>
		<STYP>DEL</STYP>
	</META>
	<RECS>2</RECS>
	<FLTL>
		<FTYP>J</FTYP>
	</FLTL>
	<FLTL>
		<FTYP>I</FTYP>
	</FLTL>
</MSG>', sysdate(), 'VALID');
