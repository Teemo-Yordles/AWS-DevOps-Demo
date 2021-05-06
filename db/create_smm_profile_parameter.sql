use `view_admin`;

SET FOREIGN_KEY_CHECKS = 0;
-- --------------------------------------------------------------------------------
-- Table structure for system management module - profile-parameter association table
-- --------------------------------------------------------------------------------
DROP TABLE IF EXISTS `smm_profile_parameter`;
CREATE TABLE IF NOT EXISTS `smm_profile_parameter`
(
    `id`           bigint       NOT NULL AUTO_INCREMENT,
    `profile_id`   bigint       NOT NULL COMMENT 'role id',
    `parameter_id` bigint       NOT NULL COMMENT 'parameter id',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_smm_profile_parameter_1` (`profile_id`, `parameter_id`),
    INDEX `idx_smm_profile_parameter_2` (`profile_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_bin COMMENT ='profile parameter association table';

INSERT INTO view_admin.smm_profile_parameter
    (profile_id, parameter_id)
VALUES (1, 5);

commit;
