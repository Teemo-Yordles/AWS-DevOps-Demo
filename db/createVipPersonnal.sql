-- script to create flight related tables
-- DROP DATABASE IF EXISTS `view`;
-- CREATE DATABASE `view`;
use `view`;

SET FOREIGN_KEY_CHECKS=0;
-- ---------------------------------------------------------------
-- Table structure for reference data management system tables
-- ---------------------------------------------------------------
DROP TABLE IF EXISTS `rdms_vip_personnal`;
CREATE TABLE `rdms_vip_personnal` (
    `id` bigint(20) NOT NULL AUTO_INCREMENT,
    `vip_person_code` varchar(10) NOT NULL,
    `vip_person_number` varchar(40) NOT NULL,
    `vip_person_firstname` varchar(40) NOT NULL,
    `vip_person_lastname` varchar(40) NOT NULL,
    `vip_person_position` varchar(60),
    `vip_person_ranking` varchar(60),
    `description` varchar(80) NOT NULL,
    `local_description` varchar(80) NOT NULL,
    `vip_contact_person` varchar(60),
    `vip_contact_telephone_number` varchar(20),
    `vip_contact_mobile_number` varchar(20),
    `vip_person_work_unit` varchar(100),
    `vip_person_remarks` varchar(200),
    `inspire_time` bigint NOT NULL,
    `expire_time` bigint NOT NULL,
    `create_time` datetime,
    `update_time` datetime,
    PRIMARY KEY (`id`),
    UNIQUE KEY uk_rdms_vip_personnal_1 ( `vip_person_code`, `inspire_time`, `expire_time` )
) ENGINE=InnoDB DEFAULT charSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='参考数据管理系统_VIP人员表';

