-- script to create flight related tables
-- DROP DATABASE IF EXISTS `view`;
-- CREATE DATABASE `view`;
use `view`;

SET FOREIGN_KEY_CHECKS=0;
-- ---------------------------------------------------------------
-- Table structure for reference data management system tables
-- ---------------------------------------------------------------
DROP TABLE IF EXISTS `rdms_airbridge`;
CREATE TABLE `rdms_airbridge` (
    `id` bigint(20) NOT NULL AUTO_INCREMENT,
    `airbridge_code` varchar(8) NOT NULL,
    `description` varchar(30) NOT NULL,
    `local_description` varchar(30) NOT NULL,
    `max_height` decimal(11,2),
    `terminal_code` varchar(5),
    `support_a380` TINYINT(1),
    `associated_stands` varchar(200),
    `associated_gates` varchar(200),
    `arm_number` TINYINT(4),
    `inspire_time` bigint NOT NULL,
    `expire_time` bigint NOT NULL,
    `create_time` datetime,
    `update_time` datetime,
    PRIMARY KEY (`id`),
    UNIQUE KEY uk_rdms_airbridge_1 ( `airbridge_code`, `inspire_time`, `expire_time` )
) ENGINE=InnoDB DEFAULT charSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='参考数据管理系统_登机桥代码表';