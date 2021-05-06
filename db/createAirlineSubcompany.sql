-- script to create flight related tables
-- DROP DATABASE IF EXISTS `view`;
-- CREATE DATABASE `view`;
use `view`;

SET FOREIGN_KEY_CHECKS=0;
-- ---------------------------------------------------------------
-- Table structure for reference data management system tables
-- ---------------------------------------------------------------
DROP TABLE IF EXISTS `rdms_airline_subcompany`;
CREATE TABLE `rdms_airline_subcompany` (
    `id` bigint(20) NOT NULL AUTO_INCREMENT,
    `airline_iata_operator_code` varchar(3) NOT NULL,
    `airline_subcompany_code` varchar(10) NOT NULL,
    `description` varchar(30) NOT NULL,
    `local_description` varchar(30) NOT NULL,
    `parent_company_inspire_time` bigint NOT NULL,
    `parent_company_expire_time` bigint NOT NULL,
    `create_time` datetime,
    `update_time` datetime,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT charSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='参考数据管理系统_航空公司子公司表';

