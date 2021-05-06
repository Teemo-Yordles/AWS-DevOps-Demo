use `view`;

SET FOREIGN_KEY_CHECKS=0;
-- ---------------------------------------------------------------
-- Table structure for season flight master table.
-- ---------------------------------------------------------------
DROP TABLE IF EXISTS `fms_season_flight`;
CREATE TABLE `fms_season_flight` (
    `id` bigint(20) NOT NULL AUTO_INCREMENT,
    `season_name` varchar(20) NOT NULL COMMENT 'season name',
    `season_start_date` date NOT NULL,
    `season_end_date` date NOT NULL,
    `airline_code` varchar(3) NOT NULL COMMENT 'IATA operator code',
    `flight_number` varchar(12) NOT NULL,
    `movement_indicator` char(1) NOT NULL COMMENT 'movement indicator, A or D',
    `season_schedule_start_date` date NOT NULL,
    `season_schedule_end_date` date NOT NULL,
    `week_operate_day` char(7) NOT NULL COMMENT '0 – Flight does not operate on that day',
    `flight_schedule_time` char(5) NOT NULL,
    `flight_type` varchar(3) NOT NULL,
    `flight_indicator` char(1) NOT NULL COMMENT 'domestic or international or mixed',
    `aircraft_type` char(3) NOT NULL COMMENT 'aircraft type',
    `registration` varchar(10),
    `linked_airline_code` varchar(3),
    `linked_flight_number` varchar(12),
    `terminal` varchar(5),
    `max_pax` SMALLINT,
    `master_airline_code` varchar(3),
    `master_flight_number` varchar(12),
    `create_time` datetime,
    `update_time` datetime,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT charSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='季度航班计划表';
ALTER TABLE `fms_season_flight` ADD INDEX idx_fms_season_flight_1 ( `season_name` );
