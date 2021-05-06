use `view`;

SET FOREIGN_KEY_CHECKS=0;
-- ---------------------------------------------------------------
-- Table structure for flight delay information archive table.
-- ---------------------------------------------------------------
DROP TABLE IF EXISTS `fms_delay_archive`;
CREATE TABLE `fms_delay_archive` (
    `id` bigint(20) NOT NULL AUTO_INCREMENT,
    `flight_id` bigint(20) NOT NULL,
    `delay_code` varchar(3) NOT NULL,
    `start_time` datetime,
    `duration` smallint,
    `comments` varchar(80),
    `delay_cn` varchar(80),
    `delay_en` varchar(80),
    `create_time` datetime,
    `update_time` datetime,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT charSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='航班延误信息历史表';
ALTER TABLE `fms_delay_archive` ADD INDEX idx_fms_delay_archive_1 ( `flight_id` );

