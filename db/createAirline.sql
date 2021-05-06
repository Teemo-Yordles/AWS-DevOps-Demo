-- script to create flight related tables
-- DROP DATABASE IF EXISTS `view`;
-- CREATE DATABASE `view`;
use `view`;

SET FOREIGN_KEY_CHECKS=0;
-- ---------------------------------------------------------------
-- Table structure for reference data management system tables
-- ---------------------------------------------------------------
DROP TABLE IF EXISTS `rdms_airline`;
CREATE TABLE `rdms_airline` (
    `id` bigint(20) NOT NULL AUTO_INCREMENT,
    `iata_operator_code` varchar(3) NOT NULL,
    `icao_operator_code` char(3) NOT NULL,
    `description` varchar(30) NOT NULL,
    `local_description` varchar(30) NOT NULL,
    `country_code` varchar(3),
    `default_terminal` varchar(5),
    `default_terminal_mvin` char(1),
    `default_terminal_flin` char(1),
    `operator_groups` varchar(300),
    `airline_category` char(1) NOT NULL,
    `inspire_time` bigint NOT NULL,
    `expire_time` bigint NOT NULL,
    `create_time` datetime,
    `update_time` datetime,
    PRIMARY KEY (`id`),
    UNIQUE KEY uk_rdms_airline_1 ( `iata_operator_code`, `inspire_time`, `expire_time` )
) ENGINE=InnoDB DEFAULT charSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='参考数据管理系统_航空公司表';

