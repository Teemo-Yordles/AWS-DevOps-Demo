use `view`;

SET FOREIGN_KEY_CHECKS=0;
-- ---------------------------------------------------------------
-- Table structure for flight service archive table.
-- ---------------------------------------------------------------
DROP TABLE IF EXISTS `fms_service_archive`;
CREATE TABLE `fms_service_archive` (
    `id` bigint(20) NOT NULL AUTO_INCREMENT,
    `flight_id` bigint(20) NOT NULL,
    `service_code` varchar(4) NOT NULL,
    `service_quantity` TINYINT,
    `start_time` datetime,
    `end_time` datetime,
    `service_provider` INT,
    `abnormal_reason` varchar(8),
    `abnormal_remark` varchar(256),
    `create_time` datetime,
    `update_time` datetime,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT charSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='航班服务历史表';
ALTER TABLE `fms_service_archive` ADD INDEX idx_fms_service_archive_1 ( `flight_id` );
