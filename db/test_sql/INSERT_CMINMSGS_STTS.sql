-- DNLD
INSERT INTO aodbinterface.cminmsgs (CMINMSGS_CLOB_MSG, CMINMSGS_DATE_RECEIVED, CMINMSGS_STATUS) VALUES ('<?xml version="1.0" encoding="UTF-8"?>
<MSG xmlns:xsi = "http://www.w3.org/2001/XMLSchema-instance" xsi:noNamespaceSchemaLocation = "unisysaodbsis.xsd">    
	<META>
		<SNDR>AODB</SNDR>
		<SEQN>1243</SEQN>
	  <DTTM>20031010090311</DTTM>
		<TYPE>STTS</TYPE>
		<STYP>DNLD</STYP>
	</META>
	<RECS>3</RECS>
	<STTS>
		<STTC>DELAY</STTC>
		<STTD>Flight Delay</STTD>
		<STDC>航班延误</STDC>
		<ABNS>Y</ABNS>
	</STTS>
	<STTS>
		<STTC>CANCEL</STTC>
		<STTD>Flight Cancel</STTD>
		<STDC>航班取消</STDC>
		<ABNS>Y</ABNS>
	</STTS>
	<STTS>
		<STTC>BOR</STTC>
		<STTD>Boarding</STTD>
		<STDC>登机</STDC>
		<ABNS>N</ABNS>
	</STTS>
	</MSG>', sysdate(), 'VALID');

-- RESP
INSERT INTO aodbinterface.cminmsgs (CMINMSGS_CLOB_MSG, CMINMSGS_DATE_RECEIVED, CMINMSGS_STATUS) VALUES ('<?xml version="1.0" encoding="UTF-8"?>
<MSG xmlns:xsi = "http://www.w3.org/2001/XMLSchema-instance" xsi:noNamespaceSchemaLocation = "unisysaodbsis.xsd">
	<META>
		<SNDR>AODB</SNDR>
		<SEQN>1243</SEQN>
	  <DTTM>20031010090311</DTTM>
		<TYPE>STTS</TYPE>
		<STYP>RESP</STYP>
	</META>
	<RECS>3</RECS>
	<STTS>
		<STTC>DELAY</STTC>
		<STTD>Flight Delay</STTD>
		<STDC>航班延误</STDC>
		<ABNS>Y</ABNS>
	</STTS>
	<STTS>
		<STTC>CANCEL</STTC>
		<STTD>Flight Cancel</STTD>
		<STDC>航班取消</STDC>
		<ABNS>Y</ABNS>
	</STTS>
	<STTS>
		<STTC>BOR</STTC>
		<STTD>Boarding</STTD>
		<STDC>登机</STDC>
		<ABNS>N</ABNS>
	</STTS>
	</MSG>', sysdate(), 'VALID');

-- ADD
INSERT INTO aodbinterface.cminmsgs (CMINMSGS_CLOB_MSG, CMINMSGS_DATE_RECEIVED, CMINMSGS_STATUS) VALUES ('<?xml version="1.0" encoding="UTF-8"?>
<MSG xmlns:xsi = "http://www.w3.org/2001/XMLSchema-instance" xsi:noNamespaceSchemaLocation = "unisysaodbsis.xsd">
	<META>
		<SNDR>AODB</SNDR>
		<SEQN>1243</SEQN>
	  <DTTM>20031010090311</DTTM>
		<TYPE>STTS</TYPE>
		<STYP>ADD</STYP>
	</META>
	<RECS>1</RECS>
	<STTS>
		<STTC>RETURN</STTC>
		<STTD>Flight Air Return Because of some reason</STTD>
		<STDC>由于某种原因导致航班空返</STDC>
		<ABNS>Y</ABNS>
	</STTS>
	</MSG>', sysdate(), 'VALID');

-- UPD
INSERT INTO aodbinterface.cminmsgs (CMINMSGS_CLOB_MSG, CMINMSGS_DATE_RECEIVED, CMINMSGS_STATUS) VALUES ('<?xml version="1.0" encoding="UTF-8"?>
<MSG xmlns:xsi = "http://www.w3.org/2001/XMLSchema-instance" xsi:noNamespaceSchemaLocation = "unisysaodbsis.xsd">
	<META>
		<SNDR>AODB</SNDR>
		<SEQN>1243</SEQN>
	  <DTTM>20031010090311</DTTM>
		<TYPE>STTS</TYPE>
		<STYP>UPD</STYP>
	</META>
	<RECS>1</RECS>
	<STTS>
		<STTC>RETURN</STTC>
		<STTD>Flight Air Return Because of weather</STTD>
		<STDC>空返</STDC>
		<ABNS>Y</ABNS>
	</STTS>
	</MSG>', sysdate(), 'VALID');

-- DEL
INSERT INTO aodbinterface.cminmsgs (CMINMSGS_CLOB_MSG, CMINMSGS_DATE_RECEIVED, CMINMSGS_STATUS) VALUES ('<?xml version="1.0" encoding="UTF-8"?>
<MSG xmlns:xsi = "http://www.w3.org/2001/XMLSchema-instance" xsi:noNamespaceSchemaLocation = "unisysaodbsis.xsd">
	<META>
		<SNDR>AODB</SNDR>
		<SEQN>1243</SEQN>
	  <DTTM>20031010090311</DTTM>
		<TYPE>STTS</TYPE>
		<STYP>DEL</STYP>
	</META>
	<RECS>1</RECS>
	<STTS>
		<STTC>RETURN</STTC>
		<STTD>Flight Air Return Because of weather</STTD>
		<STDC>由于天气原因导致航班空返</STDC>
		<ABNS>Y</ABNS>
	</STTS>
	</MSG>', sysdate(), 'VALID');
