use `view_admin`;

SET FOREIGN_KEY_CHECKS=0;
-- ---------------------------------------------------------------
-- Table structure for system management module - audit table
-- ---------------------------------------------------------------
DROP TABLE IF EXISTS `smm_audit`;
CREATE TABLE IF NOT EXISTS `smm_audit` (
                                           `id` bigint(20) NOT NULL AUTO_INCREMENT,
                                           `username` varchar(32) NOT NULL COMMENT 'audit username',
                                           `func` varchar(32) NOT NULL COMMENT 'access function',
                                           `description` varchar(128) NOT NULL DEFAULT '0' COMMENT 'access description',
                                           `start_time` datetime COMMENT 'audit start date/time',
                                           PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='audit information table';

ALTER TABLE `smm_audit` ADD INDEX idx_smm_audit_1 ( `username` );

commit;
