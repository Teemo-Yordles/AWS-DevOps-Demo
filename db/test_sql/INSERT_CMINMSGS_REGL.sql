-- DNLD
INSERT INTO aodbinterface.cminmsgs (CMINMSGS_CLOB_MSG, CMINMSGS_DATE_RECEIVED, CMINMSGS_STATUS) VALUES ('<?xml version="1.0" encoding="UTF-8"?>
<MSG xmlns:xsi = "http://www.w3.org/2001/XMLSchema-instance" xsi:noNamespaceSchemaLocation = "unisysaodbsis.xsd">    
	<META>
		<SNDR>AODB</SNDR>
		<SEQN>1243</SEQN>
	  <DTTM>20031010090311</DTTM>
		<TYPE>REGL</TYPE>
		<STYP>DNLD</STYP>
	</META>
	<RECS>1</RECS>
	<REGL>
		<REGC>ASIAPC</REGC>
		<REGN>ASIA AND ASIA PACIFIC AREA</REGN>
		<RGNC>亚洲和太平</RGNC>
	</REGL>
	</MSG>', sysdate(), 'VALID');

-- RESP
INSERT INTO aodbinterface.cminmsgs (CMINMSGS_CLOB_MSG, CMINMSGS_DATE_RECEIVED, CMINMSGS_STATUS) VALUES ('<?xml version="1.0" encoding="UTF-8"?>
<MSG xmlns:xsi = "http://www.w3.org/2001/XMLSchema-instance" xsi:noNamespaceSchemaLocation = "unisysaodbsis.xsd">
	<META>
		<SNDR>AODB</SNDR>
		<SEQN>1243</SEQN>
	  <DTTM>20031010090311</DTTM>
		<TYPE>REGL</TYPE>
		<STYP>RESP</STYP>
	</META>
	<RECS>1</RECS>
	<REGL>
		<REGC>ASIAPC</REGC>
		<REGN>ASIA AND ASIA PACIFIC AREA</REGN>
		<RGNC>亚洲和太平</RGNC>
	</REGL>
	</MSG>', sysdate(), 'VALID');

-- ADD
INSERT INTO aodbinterface.cminmsgs (CMINMSGS_CLOB_MSG, CMINMSGS_DATE_RECEIVED, CMINMSGS_STATUS) VALUES ('<?xml version="1.0" encoding="UTF-8"?>
<MSG xmlns:xsi = "http://www.w3.org/2001/XMLSchema-instance" xsi:noNamespaceSchemaLocation = "unisysaodbsis.xsd">
	<META>
		<SNDR>AODB</SNDR>
		<SEQN>1243</SEQN>
	  <DTTM>20031010090311</DTTM>
		<TYPE>REGL</TYPE>
		<STYP>ADD</STYP>
	</META>
	<RECS>1</RECS>
	<REGL>
		<REGC>ASIAPC</REGC>
		<REGN>ASIA AND ASIA PACIFIC AREA</REGN>
		<RGNC>亚洲和太平</RGNC>
	</REGL>
	</MSG>', sysdate(), 'VALID');

-- UPD
INSERT INTO aodbinterface.cminmsgs (CMINMSGS_CLOB_MSG, CMINMSGS_DATE_RECEIVED, CMINMSGS_STATUS) VALUES ('<?xml version="1.0" encoding="UTF-8"?>
<MSG xmlns:xsi = "http://www.w3.org/2001/XMLSchema-instance" xsi:noNamespaceSchemaLocation = "unisysaodbsis.xsd">
	<META>
		<SNDR>AODB</SNDR>
		<SEQN>1243</SEQN>
	  <DTTM>20031010090311</DTTM>
		<TYPE>REGL</TYPE>
		<STYP>UPD</STYP>
	</META>
	<RECS>1</RECS>
	<REGL>
		<REGC>ASIAPC</REGC>
		<REGN>ASIA AND ASIA PACIFIC AREA</REGN>
		<RGNC>亚洲、太平</RGNC>
	</REGL>
	</MSG>', sysdate(), 'VALID');

-- DEL
INSERT INTO aodbinterface.cminmsgs (CMINMSGS_CLOB_MSG, CMINMSGS_DATE_RECEIVED, CMINMSGS_STATUS) VALUES ('<?xml version="1.0" encoding="UTF-8"?>
<MSG xmlns:xsi = "http://www.w3.org/2001/XMLSchema-instance" xsi:noNamespaceSchemaLocation = "unisysaodbsis.xsd">
	<META>
		<SNDR>AODB</SNDR>
		<SEQN>1243</SEQN>
	  <DTTM>20031010090311</DTTM>
		<TYPE>REGL</TYPE>
		<STYP>DEL</STYP>
	</META>
	<RECS>1</RECS>
	<REGL>
		<REGC>ASIAPC</REGC>
		<REGN>ASIA AND ASIA PACIFIC AREA</REGN>
		<RGNC>亚洲和太平</RGNC>
	</REGL>
	</MSG>', sysdate(), 'VALID');
