-- DNLD
INSERT INTO aodbinterface.cminmsgs (CMINMSGS_CLOB_MSG, CMINMSGS_DATE_RECEIVED, CMINMSGS_STATUS) VALUES ('<?xml version="1.0" encoding="UTF-8"?>
<MSG xmlns:xsi = "http://www.w3.org/2001/XMLSchema-instance" xsi:noNamespaceSchemaLocation = "unisysaodbsis.xsd">    
	<META>
		<SNDR>AODB</SNDR>
		<SEQN>1243</SEQN>
	  <DTTM>20031010090311</DTTM>
		<TYPE>ABNR</TYPE>
		<STYP>DNLD</STYP>
	</META>
	<RECS>2</RECS>
	<ABNR>
		<ANRC>WEATHER</ANRC>
		<ANRD>Bad Weather</ANRD>
		<ARDC>坏天气</ARDC>
	</ABNR>
	<ABNR>
		<ANRC>MACHINE</ANRC>
		<ANRD>Machine Problem</ANRD>
		<ARDC>机器故障</ARDC>
	</ABNR>
	</MSG>', sysdate(), 'VALID');

-- RESP
INSERT INTO aodbinterface.cminmsgs (CMINMSGS_CLOB_MSG, CMINMSGS_DATE_RECEIVED, CMINMSGS_STATUS) VALUES ('<?xml version="1.0" encoding="UTF-8"?>
<MSG xmlns:xsi = "http://www.w3.org/2001/XMLSchema-instance" xsi:noNamespaceSchemaLocation = "unisysaodbsis.xsd">
	<META>
		<SNDR>AODB</SNDR>
		<SEQN>1243</SEQN>
	  <DTTM>20031010090311</DTTM>
		<TYPE>ABNR</TYPE>
		<STYP>RESP</STYP>
	</META>
	<RECS>2</RECS>
	<ABNR>
		<ANRC>WEATHER</ANRC>
		<ANRD>Bad Weather</ANRD>
		<ARDC>坏天气</ARDC>
	</ABNR>
	<ABNR>
		<ANRC>MACHINE</ANRC>
		<ANRD>Machine Problem</ANRD>
		<ARDC>机器故障</ARDC>
	</ABNR>
	</MSG>', sysdate(), 'VALID');

-- ADD
INSERT INTO aodbinterface.cminmsgs (CMINMSGS_CLOB_MSG, CMINMSGS_DATE_RECEIVED, CMINMSGS_STATUS) VALUES ('<?xml version="1.0" encoding="UTF-8"?>
<MSG xmlns:xsi = "http://www.w3.org/2001/XMLSchema-instance" xsi:noNamespaceSchemaLocation = "unisysaodbsis.xsd">
	<META>
		<SNDR>AODB</SNDR>
		<SEQN>1243</SEQN>
	  <DTTM>20031010090311</DTTM>
		<TYPE>ABNR</TYPE>
		<STYP>ADD</STYP>
	</META>
	<RECS>1</RECS>
	<ABNR>
		<ANRC>STAFF</ANRC>
		<ANRD>Staff Arrangement</ANRD>
		<ARDC>人员安排</ARDC>
	</ABNR>
    </MSG>
	</MSG>', sysdate(), 'VALID');

-- UPD
INSERT INTO aodbinterface.cminmsgs (CMINMSGS_CLOB_MSG, CMINMSGS_DATE_RECEIVED, CMINMSGS_STATUS) VALUES ('<?xml version="1.0" encoding="UTF-8"?>
<MSG xmlns:xsi = "http://www.w3.org/2001/XMLSchema-instance" xsi:noNamespaceSchemaLocation = "unisysaodbsis.xsd">
	<META>
		<SNDR>AODB</SNDR>
		<SEQN>1243</SEQN>
	  <DTTM>20031010090311</DTTM>
		<TYPE>ABNR</TYPE>
		<STYP>UPD</STYP>
	</META>
	<RECS>1</RECS>
	<ABNR>
		<ANRC>STAFF</ANRC>
		<ANRD>Staff Arrangement Problem</ANRD>
		<ARDC>人员安排问题</ARDC>
	</ABNR>
	</MSG>', sysdate(), 'VALID');

-- DEL
INSERT INTO aodbinterface.cminmsgs (CMINMSGS_CLOB_MSG, CMINMSGS_DATE_RECEIVED, CMINMSGS_STATUS) VALUES ('<?xml version="1.0" encoding="UTF-8"?>
<MSG xmlns:xsi = "http://www.w3.org/2001/XMLSchema-instance" xsi:noNamespaceSchemaLocation = "unisysaodbsis.xsd">
	<META>
		<SNDR>AODB</SNDR>
		<SEQN>1243</SEQN>
	  <DTTM>20031010090311</DTTM>
		<TYPE>ABNR</TYPE>
		<STYP>DEL</STYP>
	</META>
	<RECS>1</RECS>
	<ABNR>
		<ANRC>STAFF</ANRC>
		<ANRD>Staff Arrangement Problem</ANRD>
		<ARDC>人员安排问题</ARDC>
	</ABNR>
	</MSG>', sysdate(), 'VALID');
