-- script to create flight related tables
-- DROP DATABASE IF EXISTS `view`;
-- CREATE DATABASE `view`;
use `view`;

SET FOREIGN_KEY_CHECKS=0;
-- ---------------------------------------------------------------
-- Table structure for reference data management system tables
-- ---------------------------------------------------------------
DROP TABLE IF EXISTS `rdms_airport`;
CREATE TABLE `rdms_airport` (
    `id` bigint(20) NOT NULL AUTO_INCREMENT,
    `iata_code` char(3) NOT NULL,
    `icao_code` char(4),
    `description` varchar(30) NOT NULL,
    `local_description` varchar(30) NOT NULL,
    `distance` bigint(20),
    `country_code` varchar(3) NOT NULL,
    `city_code` char(3) NOT NULL,
    `airport_category` char(1) NOT NULL,
    `haul_category` char(1),
    `abbreviation_1` varchar(30),
    `abbreviation_2` varchar(30),
    `abbreviation_3` varchar(30),
    `abbreviation_4` varchar(30),
    `abbreviation_5` varchar(30),
    `abbreviation_6` varchar(30),
    `inspire_time` bigint NOT NULL,
    `expire_time` bigint NOT NULL,
    `create_time` datetime,
    `update_time` datetime,
    PRIMARY KEY (`id`),
    UNIQUE KEY uk_rdms_airport_1 ( `iata_code`, `inspire_time`, `expire_time` )
) ENGINE=InnoDB DEFAULT charSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='参考数据管理系统_机场表';

