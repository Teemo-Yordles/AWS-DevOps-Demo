-- script to create flight related tables
-- DROP DATABASE IF EXISTS `view`;
-- CREATE DATABASE `view`;
use `view`;

SET FOREIGN_KEY_CHECKS=0;
-- ---------------------------------------------------------------
-- Table structure for reference data management system tables
-- ---------------------------------------------------------------
DROP TABLE IF EXISTS `rdms_region_country_association`;
CREATE TABLE `rdms_region_country_association` (
    `id` bigint(20) NOT NULL AUTO_INCREMENT,
    `region_code` varchar(6) NOT NULL,
    `country_code` varchar(3) NOT NULL,
    `country_inspire_time` bigint,
    `country_expire_time` bigint,
    `region_inspire_time` bigint,
    `region_expire_time` bigint,
    `create_time` datetime,
    `update_time` datetime,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT charSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='参考数据管理系统_区域国家关联表';

