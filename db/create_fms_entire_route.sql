use `view`;

SET FOREIGN_KEY_CHECKS=0;
-- ---------------------------------------------------------------
-- Table structure for flight entire route table.
-- ---------------------------------------------------------------
DROP TABLE IF EXISTS `fms_entire_route`;
CREATE TABLE `fms_entire_route` (
    `id` bigint(20) NOT NULL AUTO_INCREMENT,
    `flight_id` bigint(20) NOT NULL,
    `sequence_number` TINYINT NOT NULL,
    `airport_code` varchar(8) NOT NULL,
    `arrival_time` datetime,
    `departure_time` datetime,
    `create_time` datetime,
    `update_time` datetime,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT charSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='航班完整航线表';
ALTER TABLE `fms_entire_route` ADD INDEX idx_fms_entire_route_1 ( `flight_id` );
