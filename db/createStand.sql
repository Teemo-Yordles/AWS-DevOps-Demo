-- script to create flight related tables
-- DROP DATABASE IF EXISTS `view`;
-- CREATE DATABASE `view`;
use `view`;

SET FOREIGN_KEY_CHECKS=0;
-- ---------------------------------------------------------------
-- Table structure for reference data management system tables
-- ---------------------------------------------------------------
DROP TABLE IF EXISTS `rdms_stand`;
CREATE TABLE `rdms_stand` (
    `id` bigint(20) NOT NULL AUTO_INCREMENT,
    `stand_code` varchar(8) NOT NULL,
    `description` varchar(30) NOT NULL,
    `local_description` varchar(30) NOT NULL,
    `allocate_big_aircraft` char(1),
    `stand_group_code` varchar(30),
    `width` decimal(11,2),
    `length` decimal(11,2),
    `terminal_code` varchar(5),
    `attache_max_airbridge` TINYINT(4),
    `international_gate` varchar(8),
    `domestic_gate` varchar(8),
    `stand_type` char(1) NOT NULL,
    `fixed_electric` TINYINT(1),
    `inspire_time` bigint NOT NULL,
    `expire_time` bigint NOT NULL,
    `create_time` datetime,
    `update_time` datetime,
    PRIMARY KEY (`id`),
    UNIQUE KEY uk_rdms_stand_1 ( `stand_code`, `inspire_time`, `expire_time` )
) ENGINE=InnoDB DEFAULT charSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='参考数据管理系统_停机位表';

