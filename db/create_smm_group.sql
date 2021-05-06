use `view_admin`;

SET FOREIGN_KEY_CHECKS = 0;
-- ---------------------------------------------------------------
-- Table structure for system management module - group table
-- ---------------------------------------------------------------
DROP TABLE IF EXISTS `smm_group`;
CREATE TABLE IF NOT EXISTS `smm_group`
(
    `id`          bigint(20)   NOT NULL AUTO_INCREMENT,
    `name`        varchar(32)  NOT NULL DEFAULT '0' COMMENT 'group name',
    `description` varchar(128) NOT NULL DEFAULT '0' COMMENT 'group description',
    `context`     varchar(32) COMMENT 'CONTEXT PERSON',
    `tel`         varchar(15) COMMENT 'CONTEXT DETAILS',
    `address`     varchar(128),
    `create_time` datetime COMMENT 'group creation date/time',
    `update_time` datetime COMMENT 'group update date/time',
    `version`     int                   DEFAULT 1 COMMENT 'optimistic locker',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_smm_group_1` (`name`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_bin COMMENT ='group information table';

INSERT INTO `smm_group`
(`id`,
 `name`,
 `description`,
 `context`,
 `tel`,
 `address`,
 `create_time`,
 `update_time`)
VALUES (1,
        'UNISYS_Support',
        '优利公司支持团队',
        '优利公司',
        '12345678901',
        '优利公司',
        CURRENT_TIMESTAMP,
        CURRENT_TIMESTAMP);

commit;
