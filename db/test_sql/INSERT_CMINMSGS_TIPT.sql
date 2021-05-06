-- UPD
INSERT INTO aodbinterface.cminmsgs (CMINMSGS_CLOB_MSG, CMINMSGS_DATE_RECEIVED, CMINMSGS_STATUS) VALUES ('<?xml version="1.0" encoding="UTF-8"?>
<MSG xmlns:xsi = "http://www.w3.org/2001/XMLSchema-instance" xsi:noNamespaceSchemaLocation = "unisysaodbsis.xsd">
	<META>
		<SNDR>AODB</SNDR>
		<SEQN>1243</SEQN>
		<DTTM>20180810090311</DTTM>
		<TYPE>FLOP</TYPE>
		<STYP>TIPT</STYP>
	</META>
	<FLOP>
		<FLID>15</FLID>
		<FFID>CA-CA101-D-10AUG181345-D</FFID>
		<TIPT>
		    <TRTM>10AUG181350</TRTM>
		    <TRST>503</TRST>
		</TIPT>
		<TIPT>
		    <TRTM>10AUG181344</TRTM>
		    <TRST>504</TRST>
		</TIPT>
	</FLOP>
</MSG>
', sysdate(), 'VALID');

