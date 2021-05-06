use `view`;

SET FOREIGN_KEY_CHECKS=0;
-- ---------------------------------------------------------------
-- Table structure for flight carousel allocation table.
-- ---------------------------------------------------------------
DROP TABLE IF EXISTS `fms_carousel`;
CREATE TABLE `fms_carousel` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `flight_id` bigint NOT NULL,
  `sequence_number` tinyint NOT NULL,
  `carousel_code` varchar(8) NOT NULL,
  `planned_start_time` datetime DEFAULT NULL,
  `planned_end_time` datetime DEFAULT NULL,
  `first_bag_time` datetime DEFAULT NULL,
  `last_bag_time` datetime DEFAULT NULL,
  `carousel_indicator` char(1) NOT NULL COMMENT 'domestic or international',
  `carousel_class` char(1) DEFAULT NULL COMMENT 'F/C/Y/X',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_fms_carousel_1` (`flight_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COLLATE=utf8mb4_0900_ai_ci COMMENT='航班行李转盘分配表'


