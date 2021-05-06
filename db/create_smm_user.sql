use `view_admin`;

SET FOREIGN_KEY_CHECKS = 0;
-- ---------------------------------------------------------------
-- Table structure for system management module - user table
-- ---------------------------------------------------------------
DROP TABLE IF EXISTS `smm_user`;
CREATE TABLE IF NOT EXISTS `smm_user`
(
    `id`          bigint(20)  NOT NULL AUTO_INCREMENT,
    `username`    varchar(64) NOT NULL COMMENT 'user name',
    `password`    varchar(64) NOT NULL COMMENT 'user password',
    `name`        varchar(64) NOT NULL COMMENT 'name',
    `group_id`    bigint(20)  NOT NULL COMMENT 'group id',
    `role_id`     bigint(20)  NOT NULL COMMENT 'role id',
    `enabled`     tinyint(1)  NOT NULL DEFAULT '1' COMMENT '0-disabled，1-enabled',
    `create_time` datetime COMMENT 'user creation date/time',
    `update_time` datetime COMMENT 'user update date/time',
    `version`     int                  DEFAULT 1 COMMENT 'optimistic locker',
    `profile_id`  bigint(20)  NOT NULL COMMENT 'group id',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_smm_user_1` (`username`),
    INDEX `idx_smm_user_2` (`role_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_bin COMMENT ='user information table';

INSERT INTO `smm_user`
(`id`,
 `username`,
 `password`,
 `name`,
 `group_id`,
 `profile_id`,
 `role_id`,
 `create_time`,
 `update_time`)
VALUES (1,
        'admin',
        '$2a$10$ri4JlH7f/aXCUTBJvnWjN.4N3lbHDcBcXNhDVM/yJgkApUYuc2Aoq',
        'admin',
        '1',
        '2',
        '1',
        CURRENT_TIMESTAMP,
        CURRENT_TIMESTAMP);

commit;
