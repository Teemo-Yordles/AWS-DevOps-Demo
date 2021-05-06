-- script to create flight related tables
-- DROP DATABASE IF EXISTS `view`;
-- CREATE DATABASE `view`;
use `view`;

SET FOREIGN_KEY_CHECKS=0;
-- ---------------------------------------------------------------
-- Table structure for reference data management system tables
-- ---------------------------------------------------------------
DROP TABLE IF EXISTS `rdms_delay_type`;
CREATE TABLE `rdms_delay_type` (
    `id` bigint(20) NOT NULL AUTO_INCREMENT,
    `delay_type_code` varchar(3) NOT NULL,
    `delay_type_code_number` SMALLINT,
    `description` varchar(100) NOT NULL,
    `local_description` varchar(100) NOT NULL,
    `inspire_time` bigint NOT NULL,
    `expire_time` bigint NOT NULL,
    `create_time` datetime,
    `update_time` datetime,
    PRIMARY KEY (`id`),
    UNIQUE KEY uk_rdms_delay_type_1 ( `delay_type_code`, `inspire_time`, `expire_time` )
) ENGINE=InnoDB DEFAULT charSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='参考数据管理系统_航班延误代码表';

