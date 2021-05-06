use `view_admin`;

SET FOREIGN_KEY_CHECKS=0;
-- ---------------------------------------------------------------
-- Table structure for system management module - role table
-- ---------------------------------------------------------------
DROP TABLE IF EXISTS `smm_role`;
CREATE TABLE IF NOT EXISTS `smm_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(32) NOT NULL DEFAULT '0' COMMENT 'role name',
  `description` varchar(128) NOT NULL DEFAULT '0' COMMENT 'role description',
  `profile_id` bigint(20) NOT NULL DEFAULT '0' comment 'role profile id',
  `create_time` datetime COMMENT 'creation date/time',
  `update_time` datetime COMMENT 'update date/time',
  `version` int DEFAULT 1 COMMENT 'optimistic locker',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_smm_role_1` (`name`),
  index `idx_smm_role_2` (`profile_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='system role table';

INSERT INTO `smm_role` 
(`id`,
`name`, 
`description`,
`profile_id`,
`create_time`,
`update_time`) 
VALUES
	(1, 
  'administrator',
  'system administrator',
  1,
  CURRENT_TIMESTAMP,
  CURRENT_TIMESTAMP);

commit;
