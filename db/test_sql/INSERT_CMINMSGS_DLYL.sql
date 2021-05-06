-- DNLD
INSERT INTO aodbinterface.cminmsgs (CMINMSGS_CLOB_MSG, CMINMSGS_DATE_RECEIVED, CMINMSGS_STATUS) VALUES ('<?xml version="1.0" encoding="UTF-8"?>
<MSG xmlns:xsi = "http://www.w3.org/2001/XMLSchema-instance" xsi:noNamespaceSchemaLocation = "unisysaodbsis.xsd">    
	<META>
		<SNDR>AODB</SNDR>
		<SEQN>1243</SEQN>
		<DTTM>20021010090311</DTTM>
		<TYPE>DLYL</TYPE>
		<STYP>DNLD</STYP>
	</META>
	<RECS>2</RECS>
	<DLYL>
		<DCOD>AX</DCOD>
		<DCDN>82</DCDN>
		<DDES>ATC STAFF/EQUIPMENT PROBLEM</DDES>
		<DDSC>ATC STAFF/EQUIPMENT PROBLEM中文</DDSC>
	</DLYL>
	<DLYL>
		<DCOD>RE</DCOD>
		<DCDN>95</DCDN>
		<DDES>CREW ROTATION</DDES>
		<DDSC>CREW ROTATION中文</DDSC>
    </DLYL>
	</MSG>', sysdate(), 'VALID');

-- RESP
INSERT INTO aodbinterface.cminmsgs (CMINMSGS_CLOB_MSG, CMINMSGS_DATE_RECEIVED, CMINMSGS_STATUS) VALUES ('<?xml version="1.0" encoding="UTF-8"?>
<MSG xmlns:xsi = "http://www.w3.org/2001/XMLSchema-instance" xsi:noNamespaceSchemaLocation = "unisysaodbsis.xsd">
	<META>
		<SNDR>AODB</SNDR>
		<SEQN>1243</SEQN>
		<DTTM>20021010090311</DTTM>
		<TYPE>DLYL</TYPE>
		<STYP>RESP</STYP>
	</META>
	<RECS>2</RECS>
	<DLYL>
		<DCOD>AX</DCOD>
		<DCDN>82</DCDN>
		<DDES>ATC STAFF/EQUIPMENT PROBLEM</DDES>
		<DDSC>ATC STAFF/EQUIPMENT PROBLEM中文</DDSC>
	</DLYL>
	<DLYL>
		<DCOD>RE</DCOD>
		<DCDN>95</DCDN>
		<DDES>CREW ROTATION</DDES>
		<DDSC>CREW ROTATION中文</DDSC>
	</DLYL>
	</MSG>', sysdate(), 'VALID');

-- ADD
INSERT INTO aodbinterface.cminmsgs (CMINMSGS_CLOB_MSG, CMINMSGS_DATE_RECEIVED, CMINMSGS_STATUS) VALUES ('<?xml version="1.0" encoding="UTF-8"?>
<MSG xmlns:xsi = "http://www.w3.org/2001/XMLSchema-instance" xsi:noNamespaceSchemaLocation = "unisysaodbsis.xsd">
	<META>
		<SNDR>AODB</SNDR>
		<SEQN>1243</SEQN>
		<DTTM>20021010090311</DTTM>
		<TYPE>DLYL</TYPE>
		<STYP>ADD</STYP>
	</META>
	<RECS>2</RECS>
	<DLYL>
		<DCOD>AX</DCOD>
		<DCDN>82</DCDN>
		<DDES>ATC STAFF/EQUIPMENT PROBLEM</DDES>
		<DDSC>ATC STAFF/EQUIPMENT PROBLEM中文</DDSC>
	</DLYL>
	<DLYL>
		<DCOD>RE</DCOD>
		<DCDN>95</DCDN>
		<DDES>CREW ROTATION</DDES>
		<DDSC>CREW ROTATION中文</DDSC>
	</DLYL>
	</MSG>', sysdate(), 'VALID');

-- UPD
INSERT INTO aodbinterface.cminmsgs (CMINMSGS_CLOB_MSG, CMINMSGS_DATE_RECEIVED, CMINMSGS_STATUS) VALUES ('<?xml version="1.0" encoding="UTF-8"?>
<MSG xmlns:xsi = "http://www.w3.org/2001/XMLSchema-instance" xsi:noNamespaceSchemaLocation = "unisysaodbsis.xsd">
	<META>
		<SNDR>AODB</SNDR>
		<SEQN>1243</SEQN>
		<DTTM>20021010090311</DTTM>
		<TYPE>DLYL</TYPE>
		<STYP>UPD</STYP>
	</META>
	<RECS>2</RECS>
	<DLYL>
		<DCOD>AX</DCOD>
		<DCDN>82</DCDN>
		<DDES>PROBLEM</DDES>
		<DDSC>PROBLEM中文</DDSC>
	</DLYL>
	<DLYL>
		<DCOD>RE</DCOD>
		<DCDN>95</DCDN>
		<DDES>CREW ROTATION</DDES>
		<DDSC>CREW ROTATION中文</DDSC>
	</DLYL>
	</MSG>', sysdate(), 'VALID');

-- DEL
INSERT INTO aodbinterface.cminmsgs (CMINMSGS_CLOB_MSG, CMINMSGS_DATE_RECEIVED, CMINMSGS_STATUS) VALUES ('<?xml version="1.0" encoding="UTF-8"?>
<MSG xmlns:xsi = "http://www.w3.org/2001/XMLSchema-instance" xsi:noNamespaceSchemaLocation = "unisysaodbsis.xsd">
	<META>
		<SNDR>AODB</SNDR>
		<SEQN>1243</SEQN>
		<DTTM>20021010090311</DTTM>
		<TYPE>DLYL</TYPE>
		<STYP>DEL</STYP>
	</META>
	<RECS>2</RECS>
	<DLYL>
		<DCOD>AX</DCOD>
		<DCDN>82</DCDN>
		<DDES>ATC STAFF/EQUIPMENT PROBLEM</DDES>
		<DDSC>ATC STAFF/EQUIPMENT PROBLEM中文</DDSC>
	</DLYL>
	<DLYL>
		<DCOD>RE</DCOD>
		<DCDN>95</DCDN>
		<DDES>CREW ROTATION</DDES>
		<DDSC>CREW ROTATION中文</DDSC>
	</DLYL>
	</MSG>', sysdate(), 'VALID');
