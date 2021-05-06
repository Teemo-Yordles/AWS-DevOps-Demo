-- script to create flight related tables
-- DROP DATABASE IF EXISTS `view`;
-- CREATE DATABASE `view`;
use `view`;

SET FOREIGN_KEY_CHECKS = 0;
-- ---------------------------------------------------------------
-- Table structure for reference data management system tables
-- ---------------------------------------------------------------
DROP TABLE IF EXISTS `rdms_vip_service`;
CREATE TABLE `rdms_vip_service`
(
    `id`                               bigint(20)  NOT NULL AUTO_INCREMENT,
    `vip_service_code`                 varchar(4)  NOT NULL,
    `description`                      varchar(30) NOT NULL,
    `local_description`                varchar(30) NOT NULL,
    `vip_service_qtyortim_code`        TINYINT(4)  NOT NULL DEFAULT 3,
    `vip_service_unitmeas_code_single` varchar(5),
    `inspire_time`                     bigint      NOT NULL,
    `expire_time`                      bigint      NOT NULL,
    `create_time`                      datetime,
    `update_time`                      datetime,
    PRIMARY KEY (`id`),
    UNIQUE KEY uk_rdms_vip_service_1 (`vip_service_code`, `inspire_time`, `expire_time`)
) ENGINE = InnoDB
  DEFAULT charSET = utf8mb4
  COLLATE = utf8mb4_bin COMMENT ='参考数据管理系统_VIP服务表';

