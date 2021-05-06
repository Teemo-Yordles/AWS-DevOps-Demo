-- DNLD
INSERT INTO aodbinterface.cminmsgs (CMINMSGS_CLOB_MSG, CMINMSGS_DATE_RECEIVED, CMINMSGS_STATUS) VALUES ('<?xml version="1.0" encoding="UTF-8"?>
<MSG xmlns:xsi = "http://www.w3.org/2001/XMLSchema-instance" xsi:noNamespaceSchemaLocation = "unisysaodbsis.xsd">
	<META>
		<SNDR>AODB</SNDR>
		<SEQN>1243</SEQN>
		<DTTM>20021010090311</DTTM>
		<TYPE>AIRL</TYPE>
		<STYP>DNLD</STYP>
	</META>
	<RECS>3</RECS>
	<AIRL>
		<ITOP>UA</ITOP>
		<ICOP>UAL</ICOP>
		<ONAM>UNITED AIRLINES</ONAM>
		<ONMC>UNITED 航空公司</ONMC>
		<CTRY>USA</CTRY>
		<TRML MVIN="" FLIN=""></TRML>
		<OGRP>STAR ALLIANCE</OGRP>
		<SUBC>
			<CODE>001</CODE>
			<NAME>First Subcompany</NAME>
			<NAMC>第一子公司</NAMC>
		</SUBC>
		<SUBC>
			<CODE>002</CODE>
			<NAME>Second Subcompany</NAME>
			<NAMC>第二子公司</NAMC>
		</SUBC>
		<DORI>I</DORI>
	</AIRL>
	<AIRL>
		<ITOP>AA</ITOP>
		<ICOP>AAL</ICOP>
		<ONAM>AMERICAN AIRLINES</ONAM>
		<ONMC>美国航空公司</ONMC>
		<CTRY>USA</CTRY>
		<TRML MVIN="A" FLIN="I">T2</TRML>
		<OGRP>ONE WORLD</OGRP>
		<DORI>I</DORI>
	</AIRL>
	<AIRL>
		<ITOP>CX</ITOP>
		<ICOP>CPA</ICOP>
		<ONAM>CATHAY PACIFIC</ONAM>
		<ONMC>香港航空公司</ONMC>
		<CTRY>HKG</CTRY>
		<TRML MVIN="A" FLIN="I">T3A</TRML>
		<TRML MVIN="D" FLIN="I">T3B</TRML>
		<OGRP>ONE WORLD</OGRP>
		<OGRP>CHINA WORLD</OGRP>
		<DORI>I</DORI>
	</AIRL>
	</MSG>', sysdate(), 'VALID');

-- ADD
INSERT INTO aodbinterface.cminmsgs (CMINMSGS_CLOB_MSG, CMINMSGS_DATE_RECEIVED, CMINMSGS_STATUS) VALUES ('<?xml version="1.0" encoding="UTF-8"?>
<MSG xmlns:xsi = "http://www.w3.org/2001/XMLSchema-instance" xsi:noNamespaceSchemaLocation = "unisysaodbsis.xsd">
	<META>
		<SNDR>AODB</SNDR>
		<SEQN>1243</SEQN>
		<DTTM>20021010090311</DTTM>
		<TYPE>AIRL</TYPE>
		<STYP>ADD</STYP>
	</META>
	<RECS>2</RECS>
	<AIRL>
		<ITOP>AB</ITOP>
		<ICOP>AB</ICOP>
		<ONAM>AMERICAN AIRLINES</ONAM>
		<ONMC>美国BBB公司</ONMC>
		<CTRY>USA</CTRY>
		<TRML MVIN="A" FLIN="I">T1</TRML>
		<OGRP>ONE WORLD</OGRP>
        <SUBC>
			<CODE>001</CODE>
			<NAME>First Subcompany</NAME>
			<NAMC>子公司</NAMC>
		</SUBC>
		<SUBC>
			<CODE>002</CODE>
			<NAME>Second Subcompany</NAME>
			<NAMC>第二子公司</NAMC>
		</SUBC>
		<DORI>I</DORI>
	</AIRL>
	<AIRL>
		<ITOP>ZZ</ITOP>
		<ICOP>ZZZ</ICOP>
		<ONAM>CATHAY PACIFIC</ONAM>
		<ONMC>香港航空公司</ONMC>
		<CTRY>HKG</CTRY>
		<TRML MVIN="A" FLIN="I">T3A</TRML>
		<TRML MVIN="D" FLIN="I">T3B</TRML>
		<OGRP>ONE WORLD</OGRP>
		<OGRP>CHINA WORLD</OGRP>
        <SUBC>
			<CODE>001</CODE>
			<NAME>Subcompany</NAME>
			<NAMC>第一子公司</NAMC>
		</SUBC>
		<DORI>I</DORI>
	</AIRL>
	</MSG>', sysdate(), 'VALID');

-- Delete
INSERT INTO aodbinterface.cminmsgs (CMINMSGS_CLOB_MSG, CMINMSGS_DATE_RECEIVED, CMINMSGS_STATUS) VALUES ('<?xml version="1.0" encoding="UTF-8"?>
<MSG xmlns:xsi = "http://www.w3.org/2001/XMLSchema-instance" xsi:noNamespaceSchemaLocation = "unisysaodbsis.xsd">
	<META>
		<SNDR>AODB</SNDR>
		<SEQN>1243</SEQN>
		<DTTM>20021010090311</DTTM>
		<TYPE>AIRL</TYPE>
		<STYP>DEL</STYP>
	</META>
	<RECS>1</RECS>
	<AIRL>
		<ITOP>ZZ</ITOP>
	</AIRL>
	</MSG>', sysdate(), 'VALID');
