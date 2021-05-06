-- script to create flight related tables
DROP DATABASE IF EXISTS `view`;
CREATE DATABASE `view` CHARACTER SET utf8mb4 COLLATE utf8mb4_bin;
use `view`;

SET FOREIGN_KEY_CHECKS=0;

-- reference data section
source .\createAircraftType.sql
source .\createAbnormalReason.sql
source .\createAirbridge.sql
source .\createAirline.sql
source .\createAirlineSubcompany.sql
source .\createAirport.sql
source .\createCarousel.sql
source .\createCheckinDesk.sql
source .\createChute.sql
source .\createCity.sql
source .\createCountry.sql
source .\createDelayCode.sql
source .\createFlightStatus.sql
source .\createFlightType.sql
source .\createGate.sql
source .\createHandlingAgent.sql
source .\createOrganization.sql
source .\createPier.sql
source .\createRegion.sql
source .\createRegionCountryAssociation.sql
source .\createRegistration.sql
source .\createStand.sql
source .\createTerminal.sql
source .\createVipPersonnal.sql
source .\createVipService.sql
source .\create_rdms_flight_indicator.sql

-- flight related table section
source .\create_fms_flight.sql
source .\create_fms_stand.sql
source .\create_fms_entire_route.sql
source .\create_fms_chute.sql
source .\create_fms_gate.sql
source .\create_fms_checkin_desk.sql
source .\create_fms_carousel.sql
source .\create_fms_airbridge.sql
source .\create_fms_delay.sql
source .\create_fms_chock.sql
source .\create_fms_service.sql
source .\create_fms_vip.sql
source .\create_fms_season_flight.sql
source .\create_fms_season_entire_route.sql