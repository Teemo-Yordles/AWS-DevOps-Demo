-- DNLD
INSERT INTO aodbinterface.cminmsgs (CMINMSGS_CLOB_MSG, CMINMSGS_DATE_RECEIVED, CMINMSGS_STATUS) VALUES ('<?xml version="1.0" encoding="UTF-8"?>
<MSG xmlns:xsi = "http://www.w3.org/2001/XMLSchema-instance" xsi:noNamespaceSchemaLocation = "unisysaodbsis.xsd">
 <META>
  <SNDR>AODB</SNDR>
  <SEQN>1243</SEQN>
  <DTTM>20021010090311</DTTM>
  <TYPE>BLST</TYPE>
  <STYP>DNLD</STYP>
 </META>
 <RECS>1</RECS>
 <BLST>
  <BCOD>B12</BCOD>
  <BTNM>Belt for night flights</BTNM>
  <BNMC>Belt for night flights中文</BNMC>
  <BCAT>D</BCAT>
  <BTML>T2</BTML>
 </BLST>
</MSG>
', sysdate(), 'VALID');

-- ADD
INSERT INTO aodbinterface.cminmsgs (CMINMSGS_CLOB_MSG, CMINMSGS_DATE_RECEIVED, CMINMSGS_STATUS) VALUES ('<?xml version="1.0" encoding="UTF-8"?>
<MSG xmlns:xsi = "http://www.w3.org/2001/XMLSchema-instance" xsi:noNamespaceSchemaLocation = "unisysaodbsis.xsd">
 <META>
  <SNDR>AODB</SNDR>
  <SEQN>1243</SEQN>
  <DTTM>20021010090311</DTTM>
  <TYPE>BLST</TYPE>
  <STYP>ADD</STYP>
 </META>
 <RECS>1</RECS>
 <BLST>
  <BCOD>b14</BCOD>
  <BTNM>Belt for night flights</BTNM>
  <BNMC>Belt for night flights中文</BNMC>
  <BCAT>D</BCAT>
  <BTML>T2</BTML>
 </BLST>
</MSG>', sysdate(), 'VALID');

-- Delete
INSERT INTO aodbinterface.cminmsgs (CMINMSGS_CLOB_MSG, CMINMSGS_DATE_RECEIVED, CMINMSGS_STATUS) VALUES ('<?xml version="1.0" encoding="UTF-8"?>
<MSG xmlns:xsi = "http://www.w3.org/2001/XMLSchema-instance" xsi:noNamespaceSchemaLocation = "unisysaodbsis.xsd">
 <META>
  <SNDR>AODB</SNDR>
  <SEQN>1243</SEQN>
  <DTTM>20021010090311</DTTM>
  <TYPE>BLST</TYPE>
  <STYP>DEL</STYP>
 </META>
 <RECS>1</RECS>
 <BLST>
  <BCOD>B12</BCOD>
 </BLST>
</MSG>', sysdate(), 'VALID');
