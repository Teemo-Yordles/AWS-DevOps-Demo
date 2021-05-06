-- DNLD
INSERT INTO aodbinterface.cminmsgs (CMINMSGS_CLOB_MSG, CMINMSGS_DATE_RECEIVED, CMINMSGS_STATUS) VALUES ('<?xml version="1.0" encoding="UTF-8"?>
<MSG xmlns:xsi = "http://www.w3.org/2001/XMLSchema-instance" xsi:noNamespaceSchemaLocation = "unisysaodbsis.xsd">
 <META>
  <SNDR>AODB</SNDR>
  <SEQN>1243</SEQN>
  <DTTM>20021010090311</DTTM>
  <TYPE>VIPS</TYPE>
  <STYP>DNLD</STYP>
 </META>
 <RECS>1</RECS>
 <VIPS>
  <VSCD>LIM</VSCD>
  <VSDS>LIMOUSINE</VSDS>
  <VSDC>LIMOUSINE1</VSDC>
  <VSQC>1</VSQC>
  <VSUC>N</VSUC>
 </VIPS>
</MSG>', sysdate(), 'VALID');

-- ADD
INSERT INTO aodbinterface.cminmsgs (CMINMSGS_CLOB_MSG, CMINMSGS_DATE_RECEIVED, CMINMSGS_STATUS) VALUES ('<?xml version="1.0" encoding="UTF-8"?>
<MSG xmlns:xsi = "http://www.w3.org/2001/XMLSchema-instance" xsi:noNamespaceSchemaLocation = "unisysaodbsis.xsd">
 <META>
  <SNDR>AODB</SNDR>
  <SEQN>1243</SEQN>
  <DTTM>20021010090311</DTTM>
  <TYPE>VIPS</TYPE>
  <STYP>ADD</STYP>
 </META>
 <RECS>1</RECS>
 <VIPS>
  <VSCD>LIM2</VSCD>
  <VSDS>LIMOUSINE</VSDS>
  <VSDC>LIMOUSINE2</VSDC>
  <VSQC>1</VSQC>
  <VSUC>N</VSUC>
 </VIPS>
</MSG>', sysdate(), 'VALID');

-- Delete
INSERT INTO aodbinterface.cminmsgs (CMINMSGS_CLOB_MSG, CMINMSGS_DATE_RECEIVED, CMINMSGS_STATUS) VALUES ('<?xml version="1.0" encoding="UTF-8"?>
<MSG xmlns:xsi = "http://www.w3.org/2001/XMLSchema-instance" xsi:noNamespaceSchemaLocation = "unisysaodbsis.xsd">
 <META>
  <SNDR>AODB</SNDR>
  <SEQN>1243</SEQN>
  <DTTM>20021010090311</DTTM>
  <TYPE>VIPS</TYPE>
  <STYP>DEL</STYP>
 </META>
 <RECS>1</RECS>
 <VIPS>
  <VSCD>LIM</VSCD>
  <VSDS>LIMOUSINE</VSDS>
  <VSDC>LIMOUSINE1</VSDC>
 </VIPS>
</MSG>', sysdate(), 'VALID');
