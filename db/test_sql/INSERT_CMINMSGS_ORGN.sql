-- DNLD
INSERT INTO aodbinterface.cminmsgs (CMINMSGS_CLOB_MSG, CMINMSGS_DATE_RECEIVED, CMINMSGS_STATUS) VALUES ('<?xml version="1.0" encoding="UTF-8"?>
<MSG xmlns:xsi = "http://www.w3.org/2001/XMLSchema-instance" xsi:noNamespaceSchemaLocation = "unisysaodbsis.xsd">
	<META>
		<SNDR>AODB</SNDR>
		<SEQN>1243</SEQN>
		<DTTM>20021010090311</DTTM>
		<TYPE>ORGN</TYPE>
		<STYP>DNLD</STYP>
	</META>
	<RECS>2</RECS>
	<ORGN>
		<OGID>423423</OGID>
		<ONAM>China Eastern Holding Company</ONAM>
		<ONMC>中国东方有限公司</ONMC>
		<OCAT>RAMP HANDLER</OCAT>
	</ORGN>
	<ORGN>
		<OGID>477923</OGID>
		<ONAM>ServisAir Private Limited</ONAM>
		<ONMC>美国SERVISAIR有限公司</ONMC>
		<OCAT>RAMP HANDLER</OCAT>
		<OCAT>PAX HANDLER</OCAT>
	</ORGN>
	</MSG>', sysdate(), 'VALID');

-- RESP
INSERT INTO aodbinterface.cminmsgs (CMINMSGS_CLOB_MSG, CMINMSGS_DATE_RECEIVED, CMINMSGS_STATUS) VALUES ('<?xml version="1.0" encoding="UTF-8"?>
<MSG xmlns:xsi = "http://www.w3.org/2001/XMLSchema-instance" xsi:noNamespaceSchemaLocation = "unisysaodbsis.xsd">
	<META>
		<SNDR>AODB</SNDR>
		<SEQN>1243</SEQN>
		<DTTM>20021010090311</DTTM>
		<TYPE>ORGN</TYPE>
		<STYP>RESP</STYP>
	</META>
	<RECS>2</RECS>
	<ORGN>
		<OGID>423423</OGID>
		<ONAM>China Eastern Holding Company</ONAM>
		<ONMC>中国东方</ONMC>
		<OCAT>RAMP HANDLER</OCAT>
	</ORGN>
	<ORGN>
		<OGID>477923</OGID>
		<ONAM>ServisAir Private Limited</ONAM>
		<ONMC>美国SERVISAIR有限公司</ONMC>
		<OCAT>RAMP HANDLER</OCAT>
	</ORGN>
	</MSG>', sysdate(), 'VALID');

-- ADD
INSERT INTO aodbinterface.cminmsgs (CMINMSGS_CLOB_MSG, CMINMSGS_DATE_RECEIVED, CMINMSGS_STATUS) VALUES ('<?xml version="1.0" encoding="UTF-8"?>
<MSG xmlns:xsi = "http://www.w3.org/2001/XMLSchema-instance" xsi:noNamespaceSchemaLocation = "unisysaodbsis.xsd">
	<META>
		<SNDR>AODB</SNDR>
		<SEQN>1243</SEQN>
		<DTTM>20021010090311</DTTM>
		<TYPE>ORGN</TYPE>
		<STYP>ADD</STYP>
	</META>
	<RECS>2</RECS>
	<ORGN>
		<OGID>423423</OGID>
		<ONAM>China Eastern Holding Company</ONAM>
		<ONMC>中国东方有限公司</ONMC>
		<OCAT>RAMP HANDLER</OCAT>
	</ORGN>
	<ORGN>
		<OGID>477923</OGID>
		<ONAM>ServisAir Private Limited</ONAM>
		<ONMC>美国SERVISAIR有限公司</ONMC>
		<OCAT>RAMP HANDLER</OCAT>
		<OCAT>PAX HANDLER</OCAT>
	</ORGN>
	</MSG>', sysdate(), 'VALID');

-- UPD
INSERT INTO aodbinterface.cminmsgs (CMINMSGS_CLOB_MSG, CMINMSGS_DATE_RECEIVED, CMINMSGS_STATUS) VALUES ('<?xml version="1.0" encoding="UTF-8"?>
<MSG xmlns:xsi = "http://www.w3.org/2001/XMLSchema-instance" xsi:noNamespaceSchemaLocation = "unisysaodbsis.xsd">
	<META>
		<SNDR>AODB</SNDR>
		<SEQN>1243</SEQN>
		<DTTM>20021010090311</DTTM>
		<TYPE>ORGN</TYPE>
		<STYP>UPD</STYP>
	</META>
	<RECS>2</RECS>
	<ORGN>
		<OGID>423423</OGID>
		<ONAM>China Eastern Holding Company</ONAM>
		<ONMC>中国东方有限公司</ONMC>
		<OCAT>RAMP HANDLER</OCAT>
	</ORGN>
	<ORGN>
		<OGID>477923</OGID>
		<ONAM>ServisAir Private Limited</ONAM>
		<ONMC>美国SERVISAIR有限公司</ONMC>
		<OCAT>RAMP HANDLER</OCAT>
		<OCAT>PAX HANDLER</OCAT>
	</ORGN>
	</MSG>', sysdate(), 'VALID');

-- DEL
INSERT INTO aodbinterface.cminmsgs (CMINMSGS_CLOB_MSG, CMINMSGS_DATE_RECEIVED, CMINMSGS_STATUS) VALUES ('<?xml version="1.0" encoding="UTF-8"?>
<MSG xmlns:xsi = "http://www.w3.org/2001/XMLSchema-instance" xsi:noNamespaceSchemaLocation = "unisysaodbsis.xsd">
	<META>
		<SNDR>AODB</SNDR>
		<SEQN>1243</SEQN>
		<DTTM>20021010090311</DTTM>
		<TYPE>ORGN</TYPE>
		<STYP>DEL</STYP>
	</META>
	<RECS>2</RECS>
	<ORGN>
		<OGID>423423</OGID>
		<ONAM>China Eastern Holding Company</ONAM>
		<ONMC>中国东方有限公司</ONMC>
		<OCAT>RAMP HANDLER</OCAT>
	</ORGN>
	<ORGN>
		<OGID>477923</OGID>
		<ONAM>ServisAir Private Limited</ONAM>
		<ONMC>美国SERVISAIR有限公司</ONMC>
		<OCAT>RAMP HANDLER</OCAT>
		<OCAT>PAX HANDLER</OCAT>
	</ORGN>
	</MSG>', sysdate(), 'VALID');
