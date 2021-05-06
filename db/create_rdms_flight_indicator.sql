use `view`;

SET FOREIGN_KEY_CHECKS=0;
-- ---------------------------------------------------------------
-- Table structure for flight indicator usage table.
-- ---------------------------------------------------------------
DROP TABLE IF EXISTS `rdms_flight_indicator`;
CREATE TABLE `rdms_flight_indicator` (
    `id` bigint(20) NOT NULL AUTO_INCREMENT,
    `indicator_code` char(1) NOT NULL COMMENT 'D or I or M or R',
    `description` varchar(30) NOT NULL,
    `local_description` varchar(30) NOT NULL,
    `inspire_time` bigint NOT NULL,
    `expire_time` bigint NOT NULL,
    `create_time` datetime,
    `update_time` datetime,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT charSET=utf8mb4 COMMENT='航班标识表';
ALTER TABLE `rdms_flight_indicator` ADD INDEX idx_rdms_flight_indicator_1 ( `indicator_code`, `inspire_time`, `expire_time` );

INSERT INTO `rdms_flight_indicator` (`indicator_code`, `description`, `local_description`, `inspire_time`, `expire_time`) VALUES ('D', 'Domestic', '国内', '0', '4102444800000');
INSERT INTO `rdms_flight_indicator` (`indicator_code`, `description`, `local_description`, `inspire_time`, `expire_time`) VALUES ('I', 'Internal', '国际', '0', '4102444800000');
INSERT INTO `rdms_flight_indicator` (`indicator_code`, `description`, `local_description`, `inspire_time`, `expire_time`) VALUES ('M', 'Mixed', '混合', '0', '4102444800000');
INSERT INTO `rdms_flight_indicator` (`indicator_code`, `description`, `local_description`, `inspire_time`, `expire_time`) VALUES ('R', 'Regional', '地区', '0', '4102444800000');
