use `view`;

SET FOREIGN_KEY_CHECKS=0;
-- ---------------------------------------------------------------
-- Table structure for flight entire route archive table.
-- ---------------------------------------------------------------
DROP TABLE IF EXISTS `fms_entire_route_archive`;
CREATE TABLE `fms_entire_route_archive` (
    `id` bigint(20) NOT NULL AUTO_INCREMENT,
    `flight_id` bigint(20) NOT NULL,
    `sequence_number` TINYINT NOT NULL,
    `airport_code` varchar(8) NOT NULL,
    `arrival_time` datetime,
    `departure_time` datetime,
    `airport_name_cn` varchar(30),
    `airport_name_en` varchar(30),
    `create_time` datetime,
    `update_time` datetime,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT charSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='航班完整航线历史表';
ALTER TABLE `fms_entire_route_archive` ADD INDEX idx_fms_entire_route_archive_1 ( `flight_id` );
