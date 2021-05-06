use `view`;

SET FOREIGN_KEY_CHECKS=0;
-- ---------------------------------------------------------------
-- Table structure for flight entire route table.
-- ---------------------------------------------------------------
DROP TABLE IF EXISTS `fms_season_entire_route`;
CREATE TABLE `fms_season_entire_route` (
    `id` bigint(20) NOT NULL AUTO_INCREMENT,
    `flight_id` bigint(20) NOT NULL,
    `sequence_number` TINYINT NOT NULL,
    `airport_code` varchar(3) NOT NULL,
    `arrival_time` char (5),
    `arrival_day_change` char(2),
    `departure_time` char (5),
    `departure_day_change` char(2),
    `create_time` datetime,
    `update_time` datetime,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT charSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='季度航班计划完整航线表';
ALTER TABLE `fms_season_entire_route` ADD INDEX idx_fms_season_entire_route_1 ( `flight_id` );
