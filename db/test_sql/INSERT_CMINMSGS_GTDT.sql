-- UPD
INSERT INTO aodbinterface.cminmsgs (CMINMSGS_CLOB_MSG, CMINMSGS_DATE_RECEIVED, CMINMSGS_STATUS) VALUES ('<?xml version="1.0" encoding="UTF-8"?>
<MSG xmlns:xsi = "http://www.w3.org/2001/XMLSchema-instance" xsi:noNamespaceSchemaLocation = "unisysaodbsis.xsd">
	<META>
		<SNDR>AODB</SNDR>
		<SEQN>1243</SEQN>
		<DTTM>20021010090311</DTTM>
		<TYPE>FLOP</TYPE>
		<STYP>GTDT</STYP>
	</META>
	<FLOP>
		<FLID>121112312</FLID>
		<FFID>CA-CA101-A-12DEC031345-D</FFID>
		<GTDT GTNO="1">
			<GATE>G28</GATE>
			<PGOT>15DEC031805</PGOT>
			<PGCT>15DEC031925</PGCT>
			<GOTM>15DEC031825</GOTM>
			<GTYP>D</GTYP>
		</GTDT>
		<GTDT GTNO="2">
			<GATE>G33</GATE>
			<PGOT>15DEC031805</PGOT>
			<PGCT>15DEC031925</PGCT>
			<GTYP>I</GTYP>
		</GTDT>
		<GTDT GTNO="3">
			<GATE>G23</GATE>
			<PGOT>15DEC031805</PGOT>
			<PGCT>15DEC031925</PGCT>
			<GTYP>D</GTYP>
		</GTDT>
	</FLOP>
</MSG>', sysdate(), 'VALID');

