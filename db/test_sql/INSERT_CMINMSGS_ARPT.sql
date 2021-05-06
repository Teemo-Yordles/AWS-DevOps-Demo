-- DNLD
INSERT INTO aodbinterface.cminmsgs (CMINMSGS_CLOB_MSG, CMINMSGS_DATE_RECEIVED, CMINMSGS_STATUS) VALUES ('<?xml version="1.0" encoding="UTF-8"?>
<MSG xmlns:xsi = "http://www.w3.org/2001/XMLSchema-instance" xsi:noNamespaceSchemaLocation = "unisysaodbsis.xsd">
 <META>
  <SNDR>AODB</SNDR>
  <SEQN>1243</SEQN>
  <DTTM>20021010090311</DTTM>
  <TYPE>ARPT</TYPE>
  <STYP>DNLD</STYP>
 </META>
 <RECS>3</RECS>
 <ARPT>
  <ITCD>SHA</ITCD>
  <ICCD>SHHA</ICCD>
  <ANAM>SHANGHAI HONGQIAO INTL AIRPORT</ANAM>
  <ANMC>上海虹桥国际机场</ANMC>
  <BDIS>1200</BDIS>
  <CTRY>PRC</CTRY>
  <ACTY>SHA</ACTY>
  <ATYP>D</ATYP>
  <HAUL>S</HAUL>
     <ABBR CODE="ABR1">HU</ABBR>
     <ABBR CODE="ABR2">沪</ABBR>
     <ABBR CODE="ABR3">SHANGHAI</ABBR>
     <ABBR CODE="ABR4">上海</ABBR>
     <ABBR CODE="ABR5">HONGQIAO AIRPORT</ABBR>
     <ABBR CODE="ABR6">上海虹桥</ABBR>
 </ARPT>
 <ARPT>
  <ITCD>MEL</ITCD>
  <ICCD>AMML</ICCD>
  <ANAM>MELBOURNE TULLMARINE</ANAM>
  <ANMC>墨尔本国际机场</ANMC>
  <BDIS>4500</BDIS>
  <CTRY>AUS</CTRY>
  <ACTY>MEL</ACTY>
  <ATYP>I</ATYP>
  <HAUL>L</HAUL>
 </ARPT>
 <ARPT>
  <ITCD>PEK</ITCD>
  <ICCD>ZBAA</ICCD>
  <ANAM>BEIJING CAPITAL AIRPORT</ANAM>
  <ANMC>北京首都国际机场</ANMC>
  <BDIS>1900</BDIS>
  <CTRY>PRC</CTRY>
  <ACTY>BJS</ACTY>
  <ATYP>D</ATYP>
  <HAUL>S</HAUL>
     <ABBR CODE="ABR5">BEIJING</ABBR>
     <ABBR CODE="ABR6">北京首都</ABBR>
 </ARPT>
</MSG>
', sysdate(), 'VALID');

-- ADD
INSERT INTO aodbinterface.cminmsgs (CMINMSGS_CLOB_MSG, CMINMSGS_DATE_RECEIVED, CMINMSGS_STATUS) VALUES ('<?xml version="1.0" encoding="UTF-8"?>
<MSG xmlns:xsi = "http://www.w3.org/2001/XMLSchema-instance" xsi:noNamespaceSchemaLocation = "unisysaodbsis.xsd">
 <META>
  <SNDR>AODB</SNDR>
  <SEQN>1243</SEQN>
  <DTTM>20021010090311</DTTM>
  <TYPE>ARPT</TYPE>
  <STYP>ADD</STYP>
 </META>
 <RECS>1</RECS>
 <ARPT>
  <ITCD>PEK</ITCD>
  <ICCD>ZBAA</ICCD>
  <ANAM>BEIJING CAPITAL AIRPORT</ANAM>
  <ANMC>北京首都国际机场</ANMC>
  <BDIS>1900</BDIS>
  <CTRY>PRC</CTRY>
  <ACTY>BJS</ACTY>
  <ATYP>D</ATYP>
  <HAUL>S</HAUL>
     <ABBR CODE="ABR5">BEIJING</ABBR>
     <ABBR CODE="ABR6">北京首都</ABBR>
 </ARPT>
</MSG>
', sysdate(), 'VALID');

-- Delete
INSERT INTO aodbinterface.cminmsgs (CMINMSGS_CLOB_MSG, CMINMSGS_DATE_RECEIVED, CMINMSGS_STATUS) VALUES ('<?xml version="1.0" encoding="UTF-8"?>
<MSG xmlns:xsi = "http://www.w3.org/2001/XMLSchema-instance" xsi:noNamespaceSchemaLocation = "unisysaodbsis.xsd">
 <META>
  <SNDR>AODB</SNDR>
  <SEQN>1243</SEQN>
  <DTTM>20021010090311</DTTM>
  <TYPE>ARPT</TYPE>
  <STYP>DEL</STYP>
 </META>
 <RECS>1</RECS>
 <ARPT>
  <ITCD>SHA</ITCD>
 </ARPT>
</MSG>', sysdate(), 'VALID');
