-- script to create flight related tables
-- DROP DATABASE IF EXISTS `view`;
-- CREATE DATABASE `view`;
use `view`;

SET FOREIGN_KEY_CHECKS=0;
-- ---------------------------------------------------------------
-- Table structure for reference data management system tables
-- ---------------------------------------------------------------
DROP TABLE IF EXISTS `rdms_registration`;
CREATE TABLE `rdms_registration` (
    `id` bigint(20) NOT NULL AUTO_INCREMENT,
    `registration_code` varchar(10) NOT NULL,
    `aircraft_type_code` char(3) NOT NULL,
    `organization_id` INT,
    `airline_code` varchar(3),
    `airline_subcompany_code` varchar(10),
    `max_pax` SMALLINT,
    `max_freight_weight` decimal(11,2),
    `max_takeoff_weight` decimal(11,2),
    `inspire_time` bigint NOT NULL,
    `expire_time` bigint NOT NULL,
    `create_time` datetime,
    `update_time` datetime,
    PRIMARY KEY (`id`),
    UNIQUE KEY uk_rdms_registration_1 ( `registration_code`, `inspire_time`, `expire_time` )
) ENGINE=InnoDB DEFAULT charSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='参考数据管理系统_注册码表';