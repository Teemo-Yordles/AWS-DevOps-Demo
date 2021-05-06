-- script to create flight related tables
-- DROP DATABASE IF EXISTS `view`;
-- CREATE DATABASE `view`;
use `view`;

SET FOREIGN_KEY_CHECKS=0;
-- ---------------------------------------------------------------
-- Table structure for reference data management system tables
-- ---------------------------------------------------------------
DROP TABLE IF EXISTS `rdms_region`;
CREATE TABLE `rdms_region` (
    `id` bigint(20) NOT NULL AUTO_INCREMENT,
    `region_code` varchar(6) NOT NULL,
    `description` varchar(30) NOT NULL,
    `local_description` varchar(30) NOT NULL,
    `inspire_time` bigint NOT NULL,
    `expire_time` bigint NOT NULL,
    `create_time` datetime,
    `update_time` datetime,
    PRIMARY KEY (`id`),
    UNIQUE KEY uk_rdms_region_1 ( `region_code`, `inspire_time`, `expire_time` )
) ENGINE=InnoDB DEFAULT charSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='参考数据管理系统_区域表';

