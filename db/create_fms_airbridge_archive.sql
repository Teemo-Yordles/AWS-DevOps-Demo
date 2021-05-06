use `view`;

SET FOREIGN_KEY_CHECKS=0;
-- ---------------------------------------------------------------
-- Table structure for flight airbridge usage archive table.
-- ---------------------------------------------------------------
DROP TABLE IF EXISTS `fms_airbridge_archive`;
CREATE TABLE `fms_airbridge_archive` (
    `id` bigint(20) NOT NULL AUTO_INCREMENT,
    `flight_id` bigint(20) NOT NULL,
    `sequence_number` TINYINT NOT NULL,
    `airbridge_code` varchar(8) NOT NULL,
    `operation` char(6) NOT NULL COMMENT 'attach or detach',
    `operation_time` datetime NOT NULL,
    `create_time` datetime,
    `update_time` datetime,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT charSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='航班登机桥使用历史表';
ALTER TABLE `fms_airbridge_archive` ADD INDEX idx_fms_airbridge_archive_1 ( `flight_id` );
