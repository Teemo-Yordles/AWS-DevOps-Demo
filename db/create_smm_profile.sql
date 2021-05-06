use `view_admin`;

SET FOREIGN_KEY_CHECKS=0;
-- ---------------------------------------------------------------
-- Table structure for system management module - profile table
-- ---------------------------------------------------------------
DROP TABLE IF EXISTS `smm_profile`;
CREATE TABLE IF NOT EXISTS `smm_profile` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(256) NOT NULL DEFAULT '0' COMMENT 'profile name',
  `description` varchar(128) NOT NULL DEFAULT '0' COMMENT 'profile description',
  `role_id` bigint(20) NOT NULL COMMENT 'profile related role id',
  `create_time` datetime COMMENT 'creation date/time',
  `update_time` datetime COMMENT 'update date/time',
  `version` int DEFAULT 1 COMMENT 'optimistic locker',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_smm_profile_1` (`name`),
  INDEX `idx_smm_profile_2` (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='system profile table';

INSERT INTO `smm_profile`
(`id`,
`name`, 
`description`,
`role_id`,
`create_time`,
`update_time`) 
VALUES
	(1, 
  'role:administrator/default_profile',
  'administrator role profile',
  1,
  CURRENT_TIMESTAMP,
  CURRENT_TIMESTAMP);

INSERT INTO `smm_profile`
(`id`,
 `name`,
 `description`,
 `role_id`,
 `create_time`,
 `update_time`)
VALUES
(2,
 'user:admin/admin_profile',
 'user admin profile',
 1,
 CURRENT_TIMESTAMP,
 CURRENT_TIMESTAMP);

commit;
