use `view_admin`;

SET FOREIGN_KEY_CHECKS = 0;
-- --------------------------------------------------------------------------------
-- Table structure for system management module - role-parameter association table
-- --------------------------------------------------------------------------------
DROP TABLE IF EXISTS `smm_role_parameter`;
CREATE TABLE IF NOT EXISTS `smm_role_parameter`
(
    `id`           bigint       NOT NULL AUTO_INCREMENT,
    `role_id`      bigint       NOT NULL COMMENT 'role id',
    `parameter_id` bigint       NOT NULL COMMENT 'parameter id',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_smm_role_parameter_1` (`role_id`, `parameter_id`),
    INDEX `idx_smm_role_parameter_2` (`role_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_bin COMMENT ='role parameter association table';

INSERT INTO view_admin.smm_role_parameter
    (role_id, parameter_id)
VALUES (1, 4);

commit;
