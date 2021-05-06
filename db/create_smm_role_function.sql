use `view_admin`;

SET FOREIGN_KEY_CHECKS = 0;
-- ---------------------------------------------------------------
-- Table structure for system management module - role-function relation table
-- ---------------------------------------------------------------
DROP TABLE IF EXISTS `smm_role_function`;
CREATE TABLE IF NOT EXISTS `smm_role_function`
(
    `id`          bigint(20) NOT NULL AUTO_INCREMENT,
    `role_id`     bigint(20) NOT NULL COMMENT 'role id',
    `function_id` bigint(20) NOT NULL COMMENT 'function id',
    PRIMARY KEY (`id`),
    INDEX idx_smm_role_function_1 (`function_id`),
    INDEX idx_smm_role_function_2 (`role_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_bin COMMENT ='role function access right relation table';

insert into `smm_role_function` (`role_id`, `function_id`)
values (1, 1);
insert into `smm_role_function` (`role_id`, `function_id`)
values (1, 2);

INSERT INTO `smm_role_function` (`function_id`, `role_id`)
VALUES (10, 1);

INSERT INTO `smm_role_function` (`function_id`, `role_id`)
VALUES (11, 1);

INSERT INTO `smm_role_function` (`function_id`, `role_id`)
VALUES (12, 1);

INSERT INTO `smm_role_function` (`function_id`, `role_id`)
VALUES (13, 1);

INSERT INTO `smm_role_function` (`function_id`, `role_id`)
VALUES (14, 1);

INSERT INTO `smm_role_function` (`function_id`, `role_id`)
VALUES (15, 1);

INSERT INTO `smm_role_function` (`function_id`, `role_id`)
VALUES (16, 1);

INSERT INTO `smm_role_function` (`function_id`, `role_id`)
VALUES (18, 1);

INSERT INTO `smm_role_function` (`function_id`, `role_id`)
VALUES (19, 1);

INSERT INTO `smm_role_function` (`function_id`, `role_id`)
VALUES (20, 1);

INSERT INTO `smm_role_function` (`function_id`, `role_id`)
VALUES (30, 1);

INSERT INTO `smm_role_function` (`function_id`, `role_id`)
VALUES (40, 1);

INSERT INTO `smm_role_function` (`function_id`, `role_id`)
VALUES (41, 1);

INSERT INTO `smm_role_function` (`function_id`, `role_id`)
VALUES (42, 1);

INSERT INTO `smm_role_function` (`function_id`, `role_id`)
VALUES (43, 1);

INSERT INTO `smm_role_function` (`function_id`, `role_id`)
VALUES (44, 1);

INSERT INTO `smm_role_function` (`function_id`, `role_id`)
VALUES (45, 1);

INSERT INTO `smm_role_function` (`function_id`, `role_id`)
VALUES (46, 1);

INSERT INTO `smm_role_function` (`function_id`, `role_id`)
VALUES (47, 1);

INSERT INTO `smm_role_function` (`function_id`, `role_id`)
VALUES (48, 1);

INSERT INTO `smm_role_function` (`function_id`, `role_id`)
VALUES (50, 1);

INSERT INTO `smm_role_function` (`function_id`, `role_id`)
VALUES (51, 1);

INSERT INTO `smm_role_function` (`function_id`, `role_id`)
VALUES (52, 1);

INSERT INTO `smm_role_function` (`function_id`, `role_id`)
VALUES (53, 1);

INSERT INTO `smm_role_function` (`function_id`, `role_id`)
VALUES (60, 1);

INSERT INTO `smm_role_function` (`function_id`, `role_id`)
VALUES (61, 1);

INSERT INTO `smm_role_function` (`function_id`, `role_id`)
VALUES (62, 1);

INSERT INTO `smm_role_function` (`function_id`, `role_id`)
VALUES (63, 1);

INSERT INTO `smm_role_function` (`function_id`, `role_id`)
VALUES (64, 1);

INSERT INTO `smm_role_function` (`function_id`, `role_id`)
VALUES (70, 1);

INSERT INTO `smm_role_function` (`function_id`, `role_id`)
VALUES (71, 1);

INSERT INTO `smm_role_function` (`function_id`, `role_id`)
VALUES (72, 1);

INSERT INTO `smm_role_function` (`function_id`, `role_id`)
VALUES (73, 1);

INSERT INTO `smm_role_function` (`function_id`, `role_id`)
VALUES (74, 1);

INSERT INTO `smm_role_function` (`function_id`, `role_id`)
VALUES (75, 1);

INSERT INTO `smm_role_function` (`function_id`, `role_id`)
VALUES (76, 1);

INSERT INTO `smm_role_function` (`function_id`, `role_id`)
VALUES (80, 1);

INSERT INTO `smm_role_function` (`function_id`, `role_id`)
VALUES (81, 1);

INSERT INTO `smm_role_function` (`function_id`, `role_id`)
VALUES (82, 1);

INSERT INTO `smm_role_function` (`function_id`, `role_id`)
VALUES (90, 1);

COMMIT;