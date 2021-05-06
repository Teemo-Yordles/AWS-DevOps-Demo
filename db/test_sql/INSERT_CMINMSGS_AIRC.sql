-- DNLD
INSERT INTO aodbinterface.cminmsgs (CMINMSGS_CLOB_MSG, CMINMSGS_DATE_RECEIVED, CMINMSGS_STATUS) VALUES ('<?xml version="1.0" encoding="UTF-8"?>
<MSG xmlns:xsi = "http://www.w3.org/2001/XMLSchema-instance" xsi:noNamespaceSchemaLocation = "unisysaodbsis.xsd">
	<META>
		<SNDR>AODB</SNDR>
		<SEQN>1243</SEQN>
		<DTTM>20021010090311</DTTM>
		<TYPE>AIRC</TYPE>
		<STYP>DNLD</STYP>
	</META>
	<RECS>1</RECS>
	<AIRC>
		<ITAT>312</ITAT>
		<ICAT>A310</ICAT>
		<DESC>AIRBUS 310- 100 / 200</DESC>
		<CDSC>AIRBUS中文310- 100 / 200</CDSC>
		<CHAP>C</CHAP>
		<MAXP>241</MAXP>
		<MFWT>31730</MFWT>
		<MTWT>138600</MTWT>
		<ALEN>33.8</ALEN>
		<WSPN>30</WSPN>
		<MHTM>40</MHTM>
		<MABR>2</MABR>
	</AIRC>
	<AIRC>
		<ITAT>320</ITAT>
		<ICAT>A320</ICAT>
		<DESC>AIRBUS 320- 100 / 200</DESC>
		<CDSC>AIRBUS中文320- 100 / 200</CDSC>
		<CHAP>C</CHAP>
		<MAXP>241</MAXP>
		<MFWT>31730</MFWT>
		<MTWT>138600</MTWT>
		<ALEN>33.8</ALEN>
		<WSPN>30</WSPN>
		<MHTM>40</MHTM>
		<MABR>2</MABR>
	</AIRC>
	</MSG>', sysdate(), 'VALID');

--ADD
INSERT INTO aodbinterface.cminmsgs (CMINMSGS_CLOB_MSG, CMINMSGS_DATE_RECEIVED, CMINMSGS_STATUS) VALUES ('<?xml version="1.0" encoding="UTF-8"?>
<MSG xmlns:xsi = "http://www.w3.org/2001/XMLSchema-instance" xsi:noNamespaceSchemaLocation = "unisysaodbsis.xsd">
	<META>
		<SNDR>AODB</SNDR>
		<SEQN>1243</SEQN>
		<DTTM>20021010090311</DTTM>
		<TYPE>AIRC</TYPE>
		<STYP>ADD</STYP>
	</META>
	<RECS>1</RECS>
	<AIRC>
		<ITAT>310</ITAT>
		<ICAT>A310</ICAT>
		<DESC>AIRBUS 310- 120 / 200</DESC>
		<CDSC>AIRBUS中文310- 120 / 200</CDSC>
		<CHAP>C</CHAP>
		<MAXP>242</MAXP>
		<MFWT>31732</MFWT>
		<MTWT>138600</MTWT>
		<ALEN>33.2</ALEN>
		<WSPN>32</WSPN>
		<MHTM>42</MHTM>
		<MABR>2</MABR>
	</AIRC>
	</MSG>', sysdate(), 'VALID');

--Delete
INSERT INTO aodbinterface.cminmsgs (CMINMSGS_CLOB_MSG, CMINMSGS_DATE_RECEIVED, CMINMSGS_STATUS) VALUES ('<?xml version="1.0" encoding="UTF-8"?>
<MSG xmlns:xsi = "http://www.w3.org/2001/XMLSchema-instance" xsi:noNamespaceSchemaLocation = "unisysaodbsis.xsd">
	<META>
		<SNDR>AODB</SNDR>
		<SEQN>1243</SEQN>
		<DTTM>20021010090311</DTTM>
		<TYPE>AIRC</TYPE>
		<STYP>DEL</STYP>
	</META>
	<RECS>1</RECS>
	<AIRC>
		<ITAT>312</ITAT>
	</AIRC>
	</MSG>', sysdate(), 'VALID');
