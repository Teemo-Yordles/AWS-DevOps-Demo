use `view_admin`;

SET FOREIGN_KEY_CHECKS = 0;
-- --------------------------------------------------------------------------------
-- Table structure for system management module - profile-column-setting table
-- --------------------------------------------------------------------------------
DROP TABLE IF EXISTS `smm_profile_column_setting`;
CREATE TABLE `smm_profile_column_setting`
(
    `id`                 bigint                          NOT NULL AUTO_INCREMENT,
    `profile_id`         bigint                          NOT NULL COMMENT 'profile id',
    `column_id`          bigint                          NOT NULL COMMENT 'column id',
    `authorized`         tinyint                         NOT NULL DEFAULT '1' COMMENT 'if authorized to see',
    `visible`            tinyint                         NOT NULL DEFAULT '1' COMMENT 'visible or not',
    `order`              smallint                        NOT NULL DEFAULT '1' COMMENT 'column order',
    `highlight`          tinyint                         NOT NULL DEFAULT '1' COMMENT 'need highlight or not',
    `highlight_color`    varchar(10) COLLATE utf8mb4_bin NOT NULL DEFAULT '#ffffff' COMMENT 'highlight color',
    `highlight_duration` smallint                        NOT NULL DEFAULT '5' COMMENT 'highlight duration in minute',
    `notification`       tinyint                         NOT NULL DEFAULT '0' COMMENT 'allow notification or not',
    `create_time`        datetime COMMENT 'create date/time',
    `update_time`        datetime COMMENT 'update date/time',
    `version`            int                                      DEFAULT 1 COMMENT 'optimistic locker',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_smm_profile_column_setting_3` (`profile_id`, `column_id`),
    index `idx_smm_profile_column_setting_1` (`profile_id`),
    index `idx_smm_profile_column_setting_2` (`column_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_bin COMMENT ='profile - flight column access right relation table';

INSERT INTO view_admin.smm_profile_column_setting (`profile_id`, column_id, `order`)
VALUES (1, 1, 1);
INSERT INTO view_admin.smm_profile_column_setting (`profile_id`, column_id, `order`)
VALUES (1, 2, 2);
INSERT INTO view_admin.smm_profile_column_setting (`profile_id`, column_id, `order`)
VALUES (1, 3, 3);
INSERT INTO view_admin.smm_profile_column_setting (`profile_id`, column_id, `order`)
VALUES (1, 4, 4);
INSERT INTO view_admin.smm_profile_column_setting (`profile_id`, column_id, `order`)
VALUES (1, 5, 5);
INSERT INTO view_admin.smm_profile_column_setting (`profile_id`, column_id, `order`)
VALUES (1, 6, 6);
INSERT INTO view_admin.smm_profile_column_setting (`profile_id`, column_id, `order`)
VALUES (1, 7, 7);
INSERT INTO view_admin.smm_profile_column_setting (`profile_id`, column_id, `order`)
VALUES (1, 8, 8);
INSERT INTO view_admin.smm_profile_column_setting (`profile_id`, column_id, `order`)
VALUES (1, 9, 9);
INSERT INTO view_admin.smm_profile_column_setting (`profile_id`, column_id, `order`)
VALUES (1, 10, 10);
INSERT INTO view_admin.smm_profile_column_setting (`profile_id`, column_id, `order`)
VALUES (1, 11, 11);
INSERT INTO view_admin.smm_profile_column_setting (`profile_id`, column_id, `order`)
VALUES (1, 12, 12);
INSERT INTO view_admin.smm_profile_column_setting (`profile_id`, column_id, `order`)
VALUES (1, 13, 13);
INSERT INTO view_admin.smm_profile_column_setting (`profile_id`, column_id, `order`)
VALUES (1, 14, 14);
INSERT INTO view_admin.smm_profile_column_setting (`profile_id`, column_id, `order`)
VALUES (1, 15, 15);
INSERT INTO view_admin.smm_profile_column_setting (`profile_id`, column_id, `order`)
VALUES (1, 16, 16);
INSERT INTO view_admin.smm_profile_column_setting (`profile_id`, column_id, `order`)
VALUES (1, 17, 17);
INSERT INTO view_admin.smm_profile_column_setting (`profile_id`, column_id, `order`)
VALUES (1, 18, 18);
INSERT INTO view_admin.smm_profile_column_setting (`profile_id`, column_id, `order`)
VALUES (1, 19, 19);
INSERT INTO view_admin.smm_profile_column_setting (`profile_id`, column_id, `order`)
VALUES (1, 20, 20);
INSERT INTO view_admin.smm_profile_column_setting (`profile_id`, column_id, `order`)
VALUES (1, 21, 21);
INSERT INTO view_admin.smm_profile_column_setting (`profile_id`, column_id, `order`)
VALUES (1, 22, 22);
INSERT INTO view_admin.smm_profile_column_setting (`profile_id`, column_id, `order`)
VALUES (1, 23, 23);
INSERT INTO view_admin.smm_profile_column_setting (`profile_id`, column_id, `order`)
VALUES (1, 24, 24);
INSERT INTO view_admin.smm_profile_column_setting (`profile_id`, column_id, `order`)
VALUES (1, 25, 25);
INSERT INTO view_admin.smm_profile_column_setting (`profile_id`, column_id, `order`)
VALUES (1, 26, 26);
INSERT INTO view_admin.smm_profile_column_setting (`profile_id`, column_id, `order`)
VALUES (1, 27, 27);
INSERT INTO view_admin.smm_profile_column_setting (`profile_id`, column_id, `order`)
VALUES (1, 28, 28);
INSERT INTO view_admin.smm_profile_column_setting (`profile_id`, column_id, `order`)
VALUES (1, 29, 29);
INSERT INTO view_admin.smm_profile_column_setting (`profile_id`, column_id, `order`)
VALUES (1, 30, 30);
INSERT INTO view_admin.smm_profile_column_setting (`profile_id`, column_id, `order`)
VALUES (1, 31, 31);
INSERT INTO view_admin.smm_profile_column_setting (`profile_id`, column_id, `order`)
VALUES (1, 32, 32);
INSERT INTO view_admin.smm_profile_column_setting (`profile_id`, column_id, `order`)
VALUES (1, 33, 33);
INSERT INTO view_admin.smm_profile_column_setting (`profile_id`, column_id, `order`)
VALUES (1, 34, 34);
INSERT INTO view_admin.smm_profile_column_setting (`profile_id`, column_id, `order`)
VALUES (1, 35, 35);
INSERT INTO view_admin.smm_profile_column_setting (`profile_id`, column_id, `order`)
VALUES (1, 36, 36);
INSERT INTO view_admin.smm_profile_column_setting (`profile_id`, column_id, `order`)
VALUES (1, 37, 37);
INSERT INTO view_admin.smm_profile_column_setting (`profile_id`, column_id, `order`)
VALUES (1, 38, 38);
INSERT INTO view_admin.smm_profile_column_setting (`profile_id`, column_id, `order`)
VALUES (1, 39, 39);
INSERT INTO view_admin.smm_profile_column_setting (`profile_id`, column_id, `order`)
VALUES (1, 40, 40);
INSERT INTO view_admin.smm_profile_column_setting (`profile_id`, column_id, `order`)
VALUES (1, 41, 41);
INSERT INTO view_admin.smm_profile_column_setting (`profile_id`, column_id, `order`)
VALUES (1, 42, 42);
INSERT INTO view_admin.smm_profile_column_setting (`profile_id`, column_id, `order`)
VALUES (1, 43, 43);
INSERT INTO view_admin.smm_profile_column_setting (`profile_id`, column_id, `order`)
VALUES (1, 44, 44);
INSERT INTO view_admin.smm_profile_column_setting (`profile_id`, column_id, `order`)
VALUES (1, 45, 45);
INSERT INTO view_admin.smm_profile_column_setting (`profile_id`, column_id, `order`)
VALUES (1, 46, 46);
INSERT INTO view_admin.smm_profile_column_setting (`profile_id`, column_id, `order`)
VALUES (1, 47, 47);
INSERT INTO view_admin.smm_profile_column_setting (`profile_id`, column_id, `order`)
VALUES (1, 48, 48);
INSERT INTO view_admin.smm_profile_column_setting (`profile_id`, column_id, `order`)
VALUES (1, 49, 49);
INSERT INTO view_admin.smm_profile_column_setting (`profile_id`, column_id, `order`)
VALUES (1, 50, 50);
INSERT INTO view_admin.smm_profile_column_setting (`profile_id`, column_id, `order`)
VALUES (1, 51, 51);
INSERT INTO view_admin.smm_profile_column_setting (`profile_id`, column_id, `order`)
VALUES (1, 52, 52);
INSERT INTO view_admin.smm_profile_column_setting (`profile_id`, column_id, `order`)
VALUES (1, 53, 53);
INSERT INTO view_admin.smm_profile_column_setting (`profile_id`, column_id, `order`)
VALUES (1, 54, 54);
INSERT INTO view_admin.smm_profile_column_setting (`profile_id`, column_id, `order`)
VALUES (1, 55, 55);
INSERT INTO view_admin.smm_profile_column_setting (`profile_id`, column_id, `order`)
VALUES (1, 56, 56);
INSERT INTO view_admin.smm_profile_column_setting (`profile_id`, column_id, `order`)
VALUES (1, 57, 57);
INSERT INTO view_admin.smm_profile_column_setting (`profile_id`, column_id, `order`)
VALUES (1, 58, 58);
INSERT INTO view_admin.smm_profile_column_setting (`profile_id`, column_id, `order`)
VALUES (1, 59, 59);
INSERT INTO view_admin.smm_profile_column_setting (`profile_id`, column_id, `order`)
VALUES (1, 60, 60);
INSERT INTO view_admin.smm_profile_column_setting (`profile_id`, column_id, `order`)
VALUES (1, 61, 61);
INSERT INTO view_admin.smm_profile_column_setting (`profile_id`, column_id, `order`)
VALUES (1, 62, 62);
INSERT INTO view_admin.smm_profile_column_setting (`profile_id`, column_id, `order`)
VALUES (1, 63, 63);
INSERT INTO view_admin.smm_profile_column_setting (`profile_id`, column_id, `order`)
VALUES (1, 64, 64);
INSERT INTO view_admin.smm_profile_column_setting (`profile_id`, column_id, `order`)
VALUES (1, 65, 65);
INSERT INTO view_admin.smm_profile_column_setting (`profile_id`, column_id, `order`)
VALUES (1, 66, 66);
INSERT INTO view_admin.smm_profile_column_setting (`profile_id`, column_id, `order`)
VALUES (1, 67, 67);
INSERT INTO view_admin.smm_profile_column_setting (`profile_id`, column_id, `order`)
VALUES (1, 68, 68);
INSERT INTO view_admin.smm_profile_column_setting (`profile_id`, column_id, `order`)
VALUES (2, 1, 1);
INSERT INTO view_admin.smm_profile_column_setting (`profile_id`, column_id, `order`)
VALUES (2, 2, 2);
INSERT INTO view_admin.smm_profile_column_setting (`profile_id`, column_id, `order`)
VALUES (2, 3, 3);
INSERT INTO view_admin.smm_profile_column_setting (`profile_id`, column_id, `order`)
VALUES (2, 4, 4);
INSERT INTO view_admin.smm_profile_column_setting (`profile_id`, column_id, `order`)
VALUES (2, 5, 5);
INSERT INTO view_admin.smm_profile_column_setting (`profile_id`, column_id, `order`)
VALUES (2, 6, 6);
INSERT INTO view_admin.smm_profile_column_setting (`profile_id`, column_id, `order`)
VALUES (2, 7, 7);
INSERT INTO view_admin.smm_profile_column_setting (`profile_id`, column_id, `order`)
VALUES (2, 8, 8);
INSERT INTO view_admin.smm_profile_column_setting (`profile_id`, column_id, `order`)
VALUES (2, 9, 9);
INSERT INTO view_admin.smm_profile_column_setting (`profile_id`, column_id, `order`)
VALUES (2, 10, 10);
INSERT INTO view_admin.smm_profile_column_setting (`profile_id`, column_id, `order`)
VALUES (2, 11, 11);
INSERT INTO view_admin.smm_profile_column_setting (`profile_id`, column_id, `order`)
VALUES (2, 12, 12);
INSERT INTO view_admin.smm_profile_column_setting (`profile_id`, column_id, `order`)
VALUES (2, 13, 13);
INSERT INTO view_admin.smm_profile_column_setting (`profile_id`, column_id, `order`)
VALUES (2, 14, 14);
INSERT INTO view_admin.smm_profile_column_setting (`profile_id`, column_id, `order`)
VALUES (2, 15, 15);
INSERT INTO view_admin.smm_profile_column_setting (`profile_id`, column_id, `order`)
VALUES (2, 16, 16);
INSERT INTO view_admin.smm_profile_column_setting (`profile_id`, column_id, `order`)
VALUES (2, 17, 17);
INSERT INTO view_admin.smm_profile_column_setting (`profile_id`, column_id, `order`)
VALUES (2, 18, 18);
INSERT INTO view_admin.smm_profile_column_setting (`profile_id`, column_id, `order`)
VALUES (2, 19, 19);
INSERT INTO view_admin.smm_profile_column_setting (`profile_id`, column_id, `order`)
VALUES (2, 20, 20);
INSERT INTO view_admin.smm_profile_column_setting (`profile_id`, column_id, `order`)
VALUES (2, 21, 21);
INSERT INTO view_admin.smm_profile_column_setting (`profile_id`, column_id, `order`)
VALUES (2, 22, 22);
INSERT INTO view_admin.smm_profile_column_setting (`profile_id`, column_id, `order`)
VALUES (2, 23, 23);
INSERT INTO view_admin.smm_profile_column_setting (`profile_id`, column_id, `order`)
VALUES (2, 24, 24);
INSERT INTO view_admin.smm_profile_column_setting (`profile_id`, column_id, `order`)
VALUES (2, 25, 25);
INSERT INTO view_admin.smm_profile_column_setting (`profile_id`, column_id, `order`)
VALUES (2, 26, 26);
INSERT INTO view_admin.smm_profile_column_setting (`profile_id`, column_id, `order`)
VALUES (2, 27, 27);
INSERT INTO view_admin.smm_profile_column_setting (`profile_id`, column_id, `order`)
VALUES (2, 28, 28);
INSERT INTO view_admin.smm_profile_column_setting (`profile_id`, column_id, `order`)
VALUES (2, 29, 29);
INSERT INTO view_admin.smm_profile_column_setting (`profile_id`, column_id, `order`)
VALUES (2, 30, 30);
INSERT INTO view_admin.smm_profile_column_setting (`profile_id`, column_id, `order`)
VALUES (2, 31, 31);
INSERT INTO view_admin.smm_profile_column_setting (`profile_id`, column_id, `order`)
VALUES (2, 32, 32);
INSERT INTO view_admin.smm_profile_column_setting (`profile_id`, column_id, `order`)
VALUES (2, 33, 33);
INSERT INTO view_admin.smm_profile_column_setting (`profile_id`, column_id, `order`)
VALUES (2, 34, 34);
INSERT INTO view_admin.smm_profile_column_setting (`profile_id`, column_id, `order`)
VALUES (2, 35, 35);
INSERT INTO view_admin.smm_profile_column_setting (`profile_id`, column_id, `order`)
VALUES (2, 36, 36);
INSERT INTO view_admin.smm_profile_column_setting (`profile_id`, column_id, `order`)
VALUES (2, 37, 37);
INSERT INTO view_admin.smm_profile_column_setting (`profile_id`, column_id, `order`)
VALUES (2, 38, 38);
INSERT INTO view_admin.smm_profile_column_setting (`profile_id`, column_id, `order`)
VALUES (2, 39, 39);
INSERT INTO view_admin.smm_profile_column_setting (`profile_id`, column_id, `order`)
VALUES (2, 40, 40);
INSERT INTO view_admin.smm_profile_column_setting (`profile_id`, column_id, `order`)
VALUES (2, 41, 41);
INSERT INTO view_admin.smm_profile_column_setting (`profile_id`, column_id, `order`)
VALUES (2, 42, 42);
INSERT INTO view_admin.smm_profile_column_setting (`profile_id`, column_id, `order`)
VALUES (2, 43, 43);
INSERT INTO view_admin.smm_profile_column_setting (`profile_id`, column_id, `order`)
VALUES (2, 44, 44);
INSERT INTO view_admin.smm_profile_column_setting (`profile_id`, column_id, `order`)
VALUES (2, 45, 45);
INSERT INTO view_admin.smm_profile_column_setting (`profile_id`, column_id, `order`)
VALUES (2, 46, 46);
INSERT INTO view_admin.smm_profile_column_setting (`profile_id`, column_id, `order`)
VALUES (2, 47, 47);
INSERT INTO view_admin.smm_profile_column_setting (`profile_id`, column_id, `order`)
VALUES (2, 48, 48);
INSERT INTO view_admin.smm_profile_column_setting (`profile_id`, column_id, `order`)
VALUES (2, 49, 49);
INSERT INTO view_admin.smm_profile_column_setting (`profile_id`, column_id, `order`)
VALUES (2, 50, 50);
INSERT INTO view_admin.smm_profile_column_setting (`profile_id`, column_id, `order`)
VALUES (2, 51, 51);
INSERT INTO view_admin.smm_profile_column_setting (`profile_id`, column_id, `order`)
VALUES (2, 52, 52);
INSERT INTO view_admin.smm_profile_column_setting (`profile_id`, column_id, `order`)
VALUES (2, 53, 53);
INSERT INTO view_admin.smm_profile_column_setting (`profile_id`, column_id, `order`)
VALUES (2, 54, 54);
INSERT INTO view_admin.smm_profile_column_setting (`profile_id`, column_id, `order`)
VALUES (2, 55, 55);
INSERT INTO view_admin.smm_profile_column_setting (`profile_id`, column_id, `order`)
VALUES (2, 56, 56);
INSERT INTO view_admin.smm_profile_column_setting (`profile_id`, column_id, `order`)
VALUES (2, 57, 57);
INSERT INTO view_admin.smm_profile_column_setting (`profile_id`, column_id, `order`)
VALUES (2, 58, 58);
INSERT INTO view_admin.smm_profile_column_setting (`profile_id`, column_id, `order`)
VALUES (2, 59, 59);
INSERT INTO view_admin.smm_profile_column_setting (`profile_id`, column_id, `order`)
VALUES (2, 60, 60);
INSERT INTO view_admin.smm_profile_column_setting (`profile_id`, column_id, `order`)
VALUES (2, 61, 61);
INSERT INTO view_admin.smm_profile_column_setting (`profile_id`, column_id, `order`)
VALUES (2, 62, 62);
INSERT INTO view_admin.smm_profile_column_setting (`profile_id`, column_id, `order`)
VALUES (2, 63, 63);
INSERT INTO view_admin.smm_profile_column_setting (`profile_id`, column_id, `order`)
VALUES (2, 64, 64);
INSERT INTO view_admin.smm_profile_column_setting (`profile_id`, column_id, `order`)
VALUES (2, 65, 65);
INSERT INTO view_admin.smm_profile_column_setting (`profile_id`, column_id, `order`)
VALUES (2, 66, 66);
INSERT INTO view_admin.smm_profile_column_setting (`profile_id`, column_id, `order`)
VALUES (2, 67, 67);
INSERT INTO view_admin.smm_profile_column_setting (`profile_id`, column_id, `order`)
VALUES (2, 68, 68);

COMMIT;