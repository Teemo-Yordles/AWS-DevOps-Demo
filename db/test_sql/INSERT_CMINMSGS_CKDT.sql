-- UPD
INSERT INTO aodbinterface.cminmsgs (CMINMSGS_CLOB_MSG, CMINMSGS_DATE_RECEIVED, CMINMSGS_STATUS) VALUES ('<?xml version="1.0" encoding="UTF-8"?>
<MSG xmlns:xsi = "http://www.w3.org/2001/XMLSchema-instance" xsi:noNamespaceSchemaLocation = "unisysaodbsis.xsd">
	<META>
		<SNDR>AODB</SNDR>
		<SEQN>1243</SEQN>
		<DTTM>20021010090311</DTTM>
		<TYPE>FLOP</TYPE>
		<STYP>CKDT</STYP>
	</META>
	<FLOP>
		<FLID>121112312</FLID>
		<FFID>CA-CA101-D-12DEC031345-D</FFID>
		<CKDT CKNO="1">
			<CHKC>01</CHKC>
			<CCLS>X</CCLS>
			<PCOT>15DEC031515</PCOT>
			<PCCT>15DEC031725</PCCT>
			<COTM>15DEC031525</COTM>
			<CTYP>D</CTYP>
		</CKDT>
		<CKDT CKNO="2">
			<CHKC>17</CHKC>
			<CCLS>F</CCLS>
			<PCOT>15DEC031515</PCOT>
			<PCCT>15DEC031725</PCCT>
			<COTM>15DEC031415</COTM>
			<CTYP>D</CTYP>
		</CKDT>
	</FLOP>
</MSG>', sysdate(), 'VALID');

