use `view`;

SET FOREIGN_KEY_CHECKS=0;
-- ---------------------------------------------------------------
-- Table structure for flight chock table.
-- ---------------------------------------------------------------
DROP TABLE IF EXISTS `fms_chock`;
CREATE TABLE `fms_chock` (
    `id` bigint(20) NOT NULL AUTO_INCREMENT,
    `flight_id` bigint(20) NOT NULL,
    `sequence_number` TINYINT NOT NULL,
    `stand_code` varchar(8) NOT NULL,
    `chock_time` datetime NOT NULL,
    `chock_indicator` varchar(3) NOT NULL COMMENT 'ON or OFF',
    `create_time` datetime,
    `update_time` datetime,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT charSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='航班上下轮挡信息表';
ALTER TABLE `fms_chock` ADD INDEX idx_fms_chock_1 ( `flight_id` );
