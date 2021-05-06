use `view_admin`;

SET FOREIGN_KEY_CHECKS=0;
-- ---------------------------------------------------------------
-- Table structure for system management module - user-role relationship table
-- ---------------------------------------------------------------
DROP TABLE IF EXISTS `smm_user_profile`;
CREATE TABLE IF NOT EXISTS `smm_user_profile` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NOT NULL COMMENT 'user id',
  `profile_id` bigint(20) NOT NULL COMMENT 'profile id',
  PRIMARY KEY (`id`),
  INDEX idx_smm_user_profile_1 (`user_id`),
  UNIQUE KEY `uk_smm_user_profile_2` (`user_id`, `profile_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='user-profile relation table';

INSERT INTO `smm_user_profile` (`user_id`, `profile_id`)
VALUES (1, 2);

commit;
