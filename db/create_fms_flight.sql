use `view`;

SET FOREIGN_KEY_CHECKS=0;
-- ---------------------------------------------------------------
-- Table structure for flight master table.
-- ---------------------------------------------------------------
DROP TABLE IF EXISTS `fms_flight`;
CREATE TABLE `fms_flight` (
    `id` bigint(20) NOT NULL AUTO_INCREMENT,
    `aodb_id` bigint(12),
    `airline_code` varchar(3) NOT NULL,
    `airline_subcompany_code` varchar(10),
    `flight_number` varchar(12) NOT NULL,
    `movement_indicator` char(1) NOT NULL COMMENT 'movement indicator',
    `sto` datetime NOT NULL,
    `flight_type` varchar(3) NOT NULL,
    `flight_indicator` char(1) NOT NULL COMMENT 'domestic or international',
    `aircraft_type` varchar(3) NOT NULL COMMENT 'aircraft type',
    `registration` varchar(10),
    `linked_id` bigint(20),
    `linked_aodb_id` bigint(12),
    `linked_airline_code` varchar(3),
    `linked_flight_number` varchar(12),
    `terminal` varchar(5),
    `max_pax` SMALLINT,
    `master_id` bigint(20),
    `master_aodb_id` bigint(12),
    `master_airline_code` varchar(3),
    `master_flight_number` varchar(12),
    `eto` datetime,
    `ato` datetime,
    `current_stand` varchar(8),
    `pax_agent` INT,
    `cancel` datetime,
    `remarks` varchar(80),
    `boarding_open` datetime,
    `last_call` datetime,
    `final_time` datetime,
    `approved_time` datetime,
    `engine_start` datetime,
    `engine_start_request` datetime,
    `field_agent` INT COMMENT 'field handling agent',
    `maintenance_agent` INT COMMENT 'maintenance handling agent',
    `vip_count` SMALLINT(3) COMMENT 'numbers of VIP',
    `vip_flag` TINYINT(2) COMMENT 'single digit to show vip ranking',
    `div_airport` varchar(3) COMMENT 'divert airport',
    `div_direction` varchar(4) COMMENT 'divert to/from',
    `div_reason` varchar(30) COMMENT 'divert comment',
    `ret_typ` char(1) COMMENT 'G or A',
    `ret_rsn` varchar(30),
    `landing_abort_indicator` char(1) COMMENT 'O-Overshot, G-Go around',
    `landing_abort_reason` varchar(30),
    `local_bag_count` SMALLINT(3),
    `local_bag_weight` decimal(11,2),
    `passenger_count` SMALLINT(3),
    `external_status_code` varchar(8),
    `external_status_comment` varchar(256),
    `status_code` varchar(8),
    `pedt` datetime,
    `neat` datetime,
    `padt` datetime,
    `naat` datetime,
    `rwy_code` varchar(20),
    `cal_offblock` datetime,
    `cal_takeoff` datetime,
    `estimated_offblock` datetime,
    `internal_estimated` datetime,
    `port_open_time` datetime,
    `port_close_time` datetime,
    `target_offblock` datetime,
    `logical_runway` varchar(8),
    `create_time` datetime,
    `update_time` datetime,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT charSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='航班表';
ALTER TABLE `fms_flight` ADD UNIQUE uk_fms_flight_1 ( `aodb_id` );
ALTER TABLE `fms_flight` ADD INDEX idx_fms_flight_2 ( `sto` );
