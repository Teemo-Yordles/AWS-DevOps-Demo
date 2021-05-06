-- DNLD
INSERT INTO aodbinterface.cminmsgs (CMINMSGS_CLOB_MSG, CMINMSGS_DATE_RECEIVED, CMINMSGS_STATUS) VALUES ('<?xml version="1.0" encoding="UTF-8"?>
<MSG xmlns:xsi = "http://www.w3.org/2001/XMLSchema-instance" xsi:noNamespaceSchemaLocation = "unisysaodbsis.xsd">
 <META>
  <SNDR>AODB</SNDR>
  <SEQN>1243</SEQN>
  <DTTM>20021010090311</DTTM>
  <TYPE>VIPL</TYPE>
  <STYP>DNLD</STYP>
 </META>
 <RECS>1</RECS>
 <VIPL>
  <VPCD>JACK</VPCD>
  <VPNC>211</VPNC>
  <VFNM>Jack</VFNM>
  <VLNM>Smith</VLNM>
  <VDES>Star</VDES>
  <VDSC>Star</VDSC>
  <VPON>K</VPON>
  <VRNK>1</VRNK>
  <VPTC>Fu Liu</VPTC>
  <VCTN>01000</VCTN>
  <VCMN>17611111111</VCMN>
  <VRMK>特特</VRMK>
  <VPWU>特特</VPWU>
  <VPPS>特特</VPPS>
 </VIPL>
</MSG>', sysdate(), 'VALID');

-- ADD
INSERT INTO aodbinterface.cminmsgs (CMINMSGS_CLOB_MSG, CMINMSGS_DATE_RECEIVED, CMINMSGS_STATUS) VALUES ('<?xml version="1.0" encoding="UTF-8"?>
<MSG xmlns:xsi = "http://www.w3.org/2001/XMLSchema-instance" xsi:noNamespaceSchemaLocation = "unisysaodbsis.xsd">
 <META>
  <SNDR>AODB</SNDR>
  <SEQN>1243</SEQN>
  <DTTM>20021010090311</DTTM>
  <TYPE>VIPL</TYPE>
  <STYP>ADD</STYP>
 </META>
 <RECS>1</RECS>
 <VIPL>
  <VPCD>Tom</VPCD>
  <VPNC>211</VPNC>
  <VFNM>Jack</VFNM>
  <VLNM>Smith</VLNM>
  <VDES>Star</VDES>
  <VDSC>Star</VDSC>
  <VPON>K</VPON>
  <VRNK>1</VRNK>
  <VPTC>Fu Liu</VPTC>
  <VCTN>01000</VCTN>
  <VCMN>17611111111</VCMN>
  <VRMK>特特</VRMK>
  <VPWU>特特</VPWU>
  <VPPS>特特</VPPS>
 </VIPL>
</MSG>', sysdate(), 'VALID');

-- Delete
INSERT INTO aodbinterface.cminmsgs (CMINMSGS_CLOB_MSG, CMINMSGS_DATE_RECEIVED, CMINMSGS_STATUS) VALUES ('<?xml version="1.0" encoding="UTF-8"?>
<MSG xmlns:xsi = "http://www.w3.org/2001/XMLSchema-instance" xsi:noNamespaceSchemaLocation = "unisysaodbsis.xsd">
 <META>
  <SNDR>AODB</SNDR>
  <SEQN>1243</SEQN>
  <DTTM>20021010090311</DTTM>
  <TYPE>VIPL</TYPE>
  <STYP>DEL</STYP>
 </META>
 <RECS>1</RECS>
 <VIPL>
  <VPCD>JACK</VPCD>
 </VIPL>
</MSG>', sysdate(), 'VALID');
