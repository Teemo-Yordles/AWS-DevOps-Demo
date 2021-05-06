use `view_admin`;

SET FOREIGN_KEY_CHECKS=0;
-- ---------------------------------------------------------------
-- Table structure for system management module - system parameter table
-- ---------------------------------------------------------------
DROP TABLE IF EXISTS `smm_system_parameter`;
CREATE TABLE IF NOT EXISTS `smm_system_parameter` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `parameter_id` bigint(20) NOT NULL COMMENT 'parameter id',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_smm_parameter_1` (`parameter_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='system parameter list table';

INSERT INTO `smm_system_parameter`
(`id`,
 `parameter_id`)
VALUES (1,
        1);

INSERT INTO `smm_system_parameter`
(`id`,
 `parameter_id`)
VALUES (2,
        2);

INSERT INTO `smm_system_parameter`
(`id`,
 `parameter_id`)
VALUES (3,
        3);

commit;
