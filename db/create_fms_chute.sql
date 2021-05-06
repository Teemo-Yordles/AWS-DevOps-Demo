use `view`;

SET FOREIGN_KEY_CHECKS=0;
-- ---------------------------------------------------------------
-- Table structure for flight chute allocation table.
-- ---------------------------------------------------------------
DROP TABLE IF EXISTS `fms_chute`;
CREATE TABLE `fms_chute` (
    `id` bigint(20) NOT NULL AUTO_INCREMENT,
    `flight_id` bigint(20) NOT NULL,
    `sequence_number` TINYINT NOT NULL,
    `chute_code` varchar(8) NOT NULL,
    `chute_category` char(1) NOT NULL,
    `planned_start_time` datetime,
    `planned_end_time` datetime,
    `actual_start_time` datetime,
    `actual_end_time` datetime,
    `chute_indicator` char(1) NOT NULL COMMENT 'domestic or international',
    `create_time` datetime,
    `update_time` datetime,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT charSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='航班行李传送带分配表';
ALTER TABLE `fms_chute` ADD INDEX idx_fms_chute_1 ( `flight_id` );

