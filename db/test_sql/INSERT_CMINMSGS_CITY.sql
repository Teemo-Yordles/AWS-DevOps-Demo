-- DNLD
INSERT INTO aodbinterface.cminmsgs (CMINMSGS_CLOB_MSG, CMINMSGS_DATE_RECEIVED, CMINMSGS_STATUS) VALUES ('<?xml version="1.0" encoding="UTF-8"?>
<MSG xmlns:xsi = "http://www.w3.org/2001/XMLSchema-instance" xsi:noNamespaceSchemaLocation = "unisysaodbsis.xsd">
 <META>
  <SNDR>AODB</SNDR>
  <SEQN>1243</SEQN>
  <DTTM>20021010090311</DTTM>
  <TYPE>CITY</TYPE>
  <STYP>DNLD</STYP>
 </META>
 <RECS>3</RECS>
 <CITY>
  <ITCD>SHA</ITCD>
  <ICCD>SHHA</ICCD>
  <CNAM>SHANGHAI</CNAM>
  <CNMC>上海</CNMC>
  <CTRY>PRC</CTRY>
     <ABBR CODE="ABR1">HU</ABBR>
     <ABBR CODE="ABR2">沪</ABBR>
 </CITY>
 <CITY>
  <ITCD>HET</ITCD>
  <ICCD>ZBHH</ICCD>
  <CNAM>HOHHOT</CNAM>
  <CNMC>呼和浩特</CNMC>
     <CTRY>PRC</CTRY>
     <ABBR CODE="ABR1">HU</ABBR>
     <ABBR CODE="ABR2">呼</ABBR>
     <ABBR CODE="ABR3">HUSHI</ABBR>
     <ABBR CODE="ABR4">呼市</ABBR>
 </CITY>
 <CITY>
  <ITCD>CTU</ITCD>
  <ICCD>CTUK</ICCD>
  <CNAM>CHENGDU</CNAM>
  <CNMC>成都</CNMC>
     <CTRY>PRC</CTRY>
     <ABBR CODE="ABR1">RONG</ABBR>
     <ABBR CODE="ABR2">蓉</ABBR>
 </CITY>
</MSG>
', sysdate(), 'VALID');

-- ADD
INSERT INTO aodbinterface.cminmsgs (CMINMSGS_CLOB_MSG, CMINMSGS_DATE_RECEIVED, CMINMSGS_STATUS) VALUES ('<?xml version="1.0" encoding="UTF-8"?>
<MSG xmlns:xsi = "http://www.w3.org/2001/XMLSchema-instance" xsi:noNamespaceSchemaLocation = "unisysaodbsis.xsd">
 <META>
  <SNDR>AODB</SNDR>
  <SEQN>1243</SEQN>
  <DTTM>20021010090311</DTTM>
  <TYPE>CITY</TYPE>
  <STYP>ADD</STYP>
 </META>
 <RECS>1</RECS>
 <CITY>
  <ITCD>TEST</ITCD>
  <ICCD>SHHA</ICCD>
  <CNAM>SHANGHAI</CNAM>
  <CNMC>上海</CNMC>
  <CTRY>PRC</CTRY>
     <ABBR CODE="ABR1">HU</ABBR>
     <ABBR CODE="ABR2">沪</ABBR>
 </CITY>
</MSG>', sysdate(), 'VALID');

-- Delete
INSERT INTO aodbinterface.cminmsgs (CMINMSGS_CLOB_MSG, CMINMSGS_DATE_RECEIVED, CMINMSGS_STATUS) VALUES ('<?xml version="1.0" encoding="UTF-8"?>
<MSG xmlns:xsi = "http://www.w3.org/2001/XMLSchema-instance" xsi:noNamespaceSchemaLocation = "unisysaodbsis.xsd">
 <META>
  <SNDR>AODB</SNDR>
  <SEQN>1243</SEQN>
  <DTTM>20021010090311</DTTM>
  <TYPE>CITY</TYPE>
  <STYP>DEL</STYP>
 </META>
 <RECS>1</RECS>
 <CITY>
  <ITCD>SHA</ITCD>
 </CITY>
</MSG>', sysdate(), 'VALID');
