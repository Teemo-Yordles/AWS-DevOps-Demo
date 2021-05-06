use `view_admin`;

SET FOREIGN_KEY_CHECKS=0;
-- ---------------------------------------------------------------
-- Table structure for system management module - parameter table
-- ---------------------------------------------------------------
DROP TABLE IF EXISTS `smm_parameter`;
CREATE TABLE IF NOT EXISTS `smm_parameter` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `key` varchar(64) NOT NULL COMMENT 'unique parameter key',
  `description` varchar(256) NOT NULL COMMENT 'parameter description',
  `local_description` varchar(256) NOT NULL COMMENT 'parameter description in local language',
  `value` varchar(256) NOT NULL COMMENT 'parameter value',
  `level` varchar(20) NOT NULL COMMENT 'parameter level system/role/profile',
  `create_time` datetime COMMENT 'create date/time',
  `update_time` datetime COMMENT 'parameter update date/time',
  `version` int DEFAULT 1 COMMENT 'optimistic locker',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='system global parameters';

INSERT INTO `smm_parameter` 
(`id`, 
`key`, 
`description`,
`local_description`, 
`value`,
`level`,
`create_time`,
`update_time`) 
VALUES
	(1, 
  'password.default',
  'user inital password', 
  '用户初始密码', 
  '000000',
  'system',
  CURRENT_TIMESTAMP,
  CURRENT_TIMESTAMP);

INSERT INTO `smm_parameter`
(`id`,
 `key`,
 `description`,
 `local_description`,
 `value`,
 `level`,
 `create_time`,
 `update_time`)
VALUES
(2,
 'password.enforce.complexity.check',
 'if enforce password complexity check',
 '是否强制密码复杂性检查',
 '1',
 'role',
 CURRENT_TIMESTAMP,
 CURRENT_TIMESTAMP);

INSERT INTO `smm_parameter`
(`id`,
 `key`,
 `description`,
 `local_description`,
 `value`,
 `level`,
 `create_time`,
 `update_time`)
VALUES
(3,
 'flight.show.codeshare',
 'if show codeshare flights',
 '是否显示代码共享航班',
 '0',
 'profile',
 CURRENT_TIMESTAMP,
 CURRENT_TIMESTAMP);

INSERT INTO `smm_parameter`
(`id`,
 `key`,
 `description`,
 `local_description`,
 `value`,
 `level`,
 `create_time`,
 `update_time`)
VALUES
(4,
 'password.enforce.complexity.check',
 'if enforce password complexity check',
 '是否强制密码复杂性检查',
 '1',
 'role',
 CURRENT_TIMESTAMP,
 CURRENT_TIMESTAMP);

INSERT INTO `smm_parameter`
(`id`,
 `key`,
 `description`,
 `local_description`,
 `value`,
 `level`,
 `create_time`,
 `update_time`)
VALUES
(5,
 'flight.show.codeshare',
 'if show codeshare flights',
 '是否显示代码共享航班',
 '0',
 'profile',
 CURRENT_TIMESTAMP,
 CURRENT_TIMESTAMP);

commit;
