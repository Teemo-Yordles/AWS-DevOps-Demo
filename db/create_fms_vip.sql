use `view`;

SET FOREIGN_KEY_CHECKS=0;
-- ---------------------------------------------------------------
-- Table structure for flight VIP information.
-- ---------------------------------------------------------------
DROP TABLE IF EXISTS `fms_vip`;
CREATE TABLE `fms_vip` (
    `id` bigint(20) NOT NULL AUTO_INCREMENT,
    `flight_id` bigint(20) NOT NULL,
    `vip_code` varchar(10) NOT NULL,
    `entourage_size` SMALLINT,
    `service_code` varchar(10),
    `service_quantity` BIGINT,
    `service_start_time` datetime,
    `service_end_time` datetime,
    `create_time` datetime,
    `update_time` datetime,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT charSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='航班VIP信息表';
ALTER TABLE `fms_vip` ADD INDEX idx_fms_vip_1 ( `flight_id` );

