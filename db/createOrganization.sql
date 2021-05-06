-- script to create flight related tables
-- DROP DATABASE IF EXISTS `view`;
-- CREATE DATABASE `view`;
use `view`;

SET FOREIGN_KEY_CHECKS=0;
-- ---------------------------------------------------------------
-- Table structure for reference data management system tables
-- ---------------------------------------------------------------
DROP TABLE IF EXISTS `rdms_organization`;
CREATE TABLE `rdms_organization` (
    `id` bigint(20) NOT NULL AUTO_INCREMENT,
    `organization_identifier` INT NOT NULL,
    `description` varchar(40) NOT NULL,
    `local_description` varchar(40) NOT NULL,
    `organization_categories` varchar(300),
    `inspire_time` bigint NOT NULL,
    `expire_time` bigint NOT NULL,
    `create_time` datetime,
    `update_time` datetime,
    PRIMARY KEY (`id`),
    UNIQUE KEY uk_rdms_organization_1 ( `organization_identifier`, `inspire_time`, `expire_time` )
) ENGINE=InnoDB DEFAULT charSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='参考数据管理系统_机构代码表';

