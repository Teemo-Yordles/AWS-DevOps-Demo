use `view`;

SET FOREIGN_KEY_CHECKS=0;
-- ---------------------------------------------------------------
-- Table structure for flight stand allocation archive table.
-- ---------------------------------------------------------------
DROP TABLE IF EXISTS `fms_stand_archive`;
CREATE TABLE `fms_stand_archive` (
    `id` bigint(20) NOT NULL AUTO_INCREMENT,
    `flight_id` bigint(20) NOT NULL,
    `sequence_number` TINYINT NOT NULL,
    `stand_code` varchar(8) NOT NULL,
    `start_time` datetime,
    `end_time` datetime,
    `push_indicator` varchar(3),
    `push_time` datetime,
    `tractor_time` datetime,
    `create_time` datetime,
    `update_time` datetime,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT charSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='航班机位分配历史表';
ALTER TABLE `fms_stand_archive` ADD INDEX idx_fms_stand_archive_1 ( `flight_id` );