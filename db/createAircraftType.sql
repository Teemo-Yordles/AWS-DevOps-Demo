-- ---------------------------------------------------------------
-- Table structure for reference data management system tables
-- ---------------------------------------------------------------
DROP TABLE IF EXISTS `rdms_aircraft_type`;
CREATE TABLE `rdms_aircraft_type` (
    `id` bigint(20) NOT NULL AUTO_INCREMENT,
    `iata_code` varchar(3) NOT NULL,
    `icao_code` varchar(4) NOT NULL,
    `description` varchar(30) NOT NULL,
    `local_description` varchar(30) NOT NULL,
    `size_category` char(1),
    `max_pax` SMALLINT,
    `max_freight_weight` decimal(11,2),
    `max_takeoff_weight` decimal(11,2),
    `aircraft_length` decimal(11,2),
    `wing_span` decimal(11,2),
    `min_handling_time` decimal(8,2),
    `max_airbridge` TINYINT(4),
    `inspire_time` bigint NOT NULL,
    `expire_time` bigint NOT NULL,
    `create_time` datetime,
    `update_time` datetime,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT charSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='参考数据管理系统_机型表';
ALTER TABLE `rdms_aircraft_type` ADD UNIQUE uk_rdms_aircraft_type_1 ( `iata_code`, `inspire_time`, `expire_time` );

