use `view_admin`;

SET FOREIGN_KEY_CHECKS = 0;
-- ---------------------------------------------------------------
-- Table structure for system management module - function table
-- ---------------------------------------------------------------
DROP TABLE IF EXISTS `smm_function`;
CREATE TABLE IF NOT EXISTS `smm_function`
(
    `id`                bigint(20)  NOT NULL AUTO_INCREMENT,
    `code`              varchar(64) NOT NULL COMMENT 'function code',
    `description`       varchar(64) NOT NULL COMMENT 'description in English',
    `local_description` varchar(64) NOT NULL COMMENT 'description in local language',
    `url`               varchar(64) DEFAULT NULL COMMENT 'corresponding URL',
    `sort`              int(11)     DEFAULT NULL COMMENT 'sort sequence',
    `leaf`              tinyint(1)  NOT NULL COMMENT '0-not leaf，1-leaf node',
    `parent_id`         bigint(20) COMMENT 'parent function id',
    `create_time`       datetime COMMENT 'create date/time',
    `update_time`       datetime COMMENT 'update date/time',
    `version`           int         DEFAULT 1 COMMENT 'optimistic locker',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_smm_function_1` (`code`),
    INDEX `idx_smm_function_2` (`sort`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_bin COMMENT ='系统功能接口表，配合smm_role_function控制功能访问权限';

INSERT INTO `smm_function` (`id`, `code`, `description`, `local_description`, `url`, `sort`, `leaf`, `parent_id`,
                            `create_time`, `update_time`, `version`)
VALUES (1, 'flights', 'Daily Schedule', '航班计划', '/view/flights', 1, 1, NULL, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1);

INSERT INTO `smm_function` (`id`, `code`, `description`, `local_description`, `url`, `sort`, `leaf`, `parent_id`,
                            `create_time`, `update_time`, `version`)
VALUES (2, 'seasonal_flights', 'Seasonal Schedule', '季度计划', '/view/seasonal-flights', 2, 1, NULL, CURRENT_TIMESTAMP,
        CURRENT_TIMESTAMP, 1);

INSERT INTO `smm_function` (`id`, `code`, `description`, `local_description`, `url`, `sort`, `leaf`, `parent_id`,
                            `create_time`, `update_time`, `version`)
VALUES (10, 'system_admin', 'System Administration', '系统管理', '', 10, 0, NULL, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1);

INSERT INTO `smm_function` (`id`, `code`, `description`, `local_description`, `url`, `sort`, `leaf`, `parent_id`,
                            `create_time`, `update_time`, `version`)
VALUES (11, 'admin_role', 'Role Management', '角色管理', '/view/admin/roles', 11, 1, 10, CURRENT_TIMESTAMP,
        CURRENT_TIMESTAMP, 1);

INSERT INTO `smm_function` (`id`, `code`, `description`, `local_description`, `url`, `sort`, `leaf`, `parent_id`,
                            `create_time`, `update_time`, `version`)
VALUES (12, 'admin_group', 'Group Management', '组织管理', '/view/admin/groups', 12, 1, 10, CURRENT_TIMESTAMP,
        CURRENT_TIMESTAMP, 1);

INSERT INTO `smm_function` (`id`, `code`, `description`, `local_description`, `url`, `sort`, `leaf`, `parent_id`,
                            `create_time`, `update_time`, `version`)
VALUES (13, 'admin_user', 'User Management', '用户管理', '/view/admin/users', 13, 1, 10, CURRENT_TIMESTAMP,
        CURRENT_TIMESTAMP, 1);

INSERT INTO `smm_function` (`id`, `code`, `description`, `local_description`, `url`, `sort`, `leaf`, `parent_id`,
                            `create_time`, `update_time`, `version`)
VALUES (15, 'admin_parameter', 'Parameter Management', '参数管理', '/view/admin/parameters', 15, 1, 10, CURRENT_TIMESTAMP,
        CURRENT_TIMESTAMP, 1);

INSERT INTO `smm_function` (`id`, `code`, `description`, `local_description`, `url`, `sort`, `leaf`, `parent_id`,
                            `create_time`, `update_time`, `version`)
VALUES (16, 'admin_function', 'Function Management', '功能管理', '/view/admin/functions', 16, 1, 10, CURRENT_TIMESTAMP,
        CURRENT_TIMESTAMP, 1);

INSERT INTO `smm_function` (`id`, `code`, `description`, `local_description`, `url`, `sort`, `leaf`, `parent_id`,
                            `create_time`, `update_time`, `version`)
VALUES (18, 'admin_data_request', 'Data Requests', '数据请求', '/view/admin/data-request', 18, 1, 10, CURRENT_TIMESTAMP,
        CURRENT_TIMESTAMP, 1);

INSERT INTO `smm_function` (`id`, `code`, `description`, `local_description`, `url`, `sort`, `leaf`, `parent_id`,
                            `create_time`, `update_time`, `version`)
VALUES (19, 'admin_audit', 'Audit', '审计', '/view/admin/audits', 19, 1, 10, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1);

INSERT INTO `smm_function` (`id`, `code`, `description`, `local_description`, `url`, `sort`, `leaf`, `parent_id`,
                            `create_time`, `update_time`, `version`)
VALUES (20, 'admin_cache', 'Cache Management', '缓存管理', '/view/admin/caches', 20, 1, 10, CURRENT_TIMESTAMP,
        CURRENT_TIMESTAMP, 1);

INSERT INTO `smm_function` (`id`, `code`, `description`, `local_description`, `url`, `sort`, `leaf`, `parent_id`,
                            `create_time`, `update_time`, `version`)
VALUES (23, 'force_role_user_offline', 'force role user offline', '强制角色所有用户下线', '/view/admin/force-offline', 23, 1, 10,
        CURRENT_TIMESTAMP,
        CURRENT_TIMESTAMP, 1);

INSERT INTO `smm_function` (`id`, `code`, `description`, `local_description`, `url`, `sort`, `leaf`, `parent_id`,
                            `create_time`, `update_time`, `version`)
VALUES (30, 'reference_data', 'Reference Data', '参考数据', '', 30, 0, NULL, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1);

INSERT INTO `smm_function` (`id`, `code`, `description`, `local_description`, `url`, `sort`, `leaf`, `parent_id`,
                            `create_time`, `update_time`, `version`)
VALUES (40, 'resource_data', 'Resource Data', '资源数据', '', 40, 0, 30, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1);

INSERT INTO `smm_function` (`id`, `code`, `description`, `local_description`, `url`, `sort`, `leaf`, `parent_id`,
                            `create_time`, `update_time`, `version`)
VALUES (41, 'data_stand', 'Stand', '停机位', '/view/data/reference/stands', 41, 1, 40, CURRENT_TIMESTAMP,
        CURRENT_TIMESTAMP, 1);

INSERT INTO `smm_function` (`id`, `code`, `description`, `local_description`, `url`, `sort`, `leaf`, `parent_id`,
                            `create_time`, `update_time`, `version`)
VALUES (42, 'data_gate', 'Gate', '登机口', '/view/data/reference/gates', 42, 1, 40, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP,
        1);

INSERT INTO `smm_function` (`id`, `code`, `description`, `local_description`, `url`, `sort`, `leaf`, `parent_id`,
                            `create_time`, `update_time`, `version`)
VALUES (43, 'data_carousel', 'Carousel', '行李提取转盘', '/view/data/reference/carousels', 43, 1, 40, CURRENT_TIMESTAMP,
        CURRENT_TIMESTAMP, 1);

INSERT INTO `smm_function` (`id`, `code`, `description`, `local_description`, `url`, `sort`, `leaf`, `parent_id`,
                            `create_time`, `update_time`, `version`)
VALUES (44, 'data_checkin_desk', 'Checkin Desk', '值机柜台', '/view/data/reference/checkin-desk', 44, 1, 40,
        CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1);

INSERT INTO `smm_function` (`id`, `code`, `description`, `local_description`, `url`, `sort`, `leaf`, `parent_id`,
                            `create_time`, `update_time`, `version`)
VALUES (45, 'data_terminal', 'Terminal', '航站楼', '/view/data/reference/terminals', 45, 1, 40, CURRENT_TIMESTAMP,
        CURRENT_TIMESTAMP, 1);

INSERT INTO `smm_function` (`id`, `code`, `description`, `local_description`, `url`, `sort`, `leaf`, `parent_id`,
                            `create_time`, `update_time`, `version`)
VALUES (46, 'data_airbridge', 'Airbridge', '登机桥', '/view/data/reference/airbridges', 46, 1, 40, CURRENT_TIMESTAMP,
        CURRENT_TIMESTAMP, 1);

INSERT INTO `smm_function` (`id`, `code`, `description`, `local_description`, `url`, `sort`, `leaf`, `parent_id`,
                            `create_time`, `update_time`, `version`)
VALUES (47, 'data_chute', 'Chute', '行李传送带', '/view/data/reference/chute', 47, 1, 40, CURRENT_TIMESTAMP,
        CURRENT_TIMESTAMP, 1);

INSERT INTO `smm_function` (`id`, `code`, `description`, `local_description`, `url`, `sort`, `leaf`, `parent_id`,
                            `create_time`, `update_time`, `version`)
VALUES (48, 'data_pier', 'Pier', '指廊', '/view/data/reference/piers', 48, 1, 40, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP,
        1);

INSERT INTO `smm_function` (`id`, `code`, `description`, `local_description`, `url`, `sort`, `leaf`, `parent_id`,
                            `create_time`, `update_time`, `version`)
VALUES (50, 'aircraft_data', 'Aircraft Data', '飞行器数据', '', 50, 0, 30, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1);

INSERT INTO `smm_function` (`id`, `code`, `description`, `local_description`, `url`, `sort`, `leaf`, `parent_id`,
                            `create_time`, `update_time`, `version`)
VALUES (51, 'data_aircraft_type', 'Aircraft Type', '飞行器类型', '/view/data/reference/aircraft-types', 51, 1, 50,
        CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1);

INSERT INTO `smm_function` (`id`, `code`, `description`, `local_description`, `url`, `sort`, `leaf`, `parent_id`,
                            `create_time`, `update_time`, `version`)
VALUES (52, 'data_registration', 'Registration', '机尾号', '/view/data/reference/registrations', 52, 1, 50,
        CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1);

INSERT INTO `smm_function` (`id`, `code`, `description`, `local_description`, `url`, `sort`, `leaf`, `parent_id`,
                            `create_time`, `update_time`, `version`)
VALUES (53, 'data_airline', 'Airline', '航空公司', '/view/data/reference/airlines', 53, 1, 50, CURRENT_TIMESTAMP,
        CURRENT_TIMESTAMP, 1);

INSERT INTO `smm_function` (`id`, `code`, `description`, `local_description`, `url`, `sort`, `leaf`, `parent_id`,
                            `create_time`, `update_time`, `version`)
VALUES (60, 'geographic_data', 'Geographic Data', '地理数据', '', 60, 0, 30, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1);

INSERT INTO `smm_function` (`id`, `code`, `description`, `local_description`, `url`, `sort`, `leaf`, `parent_id`,
                            `create_time`, `update_time`, `version`)
VALUES (61, 'data_airport', 'Airport', '机场', '/view/data/reference/airports', 61, 1, 60, CURRENT_TIMESTAMP,
        CURRENT_TIMESTAMP, 1);

INSERT INTO `smm_function` (`id`, `code`, `description`, `local_description`, `url`, `sort`, `leaf`, `parent_id`,
                            `create_time`, `update_time`, `version`)
VALUES (62, 'data_city', 'City', '城市', '/view/data/reference/cities', 62, 1, 60, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP,
        1);

INSERT INTO `smm_function` (`id`, `code`, `description`, `local_description`, `url`, `sort`, `leaf`, `parent_id`,
                            `create_time`, `update_time`, `version`)
VALUES (63, 'data_country', 'Country', '国家', '/view/data/reference/countries', 63, 1, 60, CURRENT_TIMESTAMP,
        CURRENT_TIMESTAMP, 1);

INSERT INTO `smm_function` (`id`, `code`, `description`, `local_description`, `url`, `sort`, `leaf`, `parent_id`,
                            `create_time`, `update_time`, `version`)
VALUES (64, 'data_region', 'Region', '区域', '/view/data/reference/regions', 64, 1, 60, CURRENT_TIMESTAMP,
        CURRENT_TIMESTAMP, 1);

INSERT INTO `smm_function` (`id`, `code`, `description`, `local_description`, `url`, `sort`, `leaf`, `parent_id`,
                            `create_time`, `update_time`, `version`)
VALUES (70, 'flight_config_data', 'Flight Config Data', '航班配置数据', '', 70, 0, 30, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP,
        1);

INSERT INTO `smm_function` (`id`, `code`, `description`, `local_description`, `url`, `sort`, `leaf`, `parent_id`,
                            `create_time`, `update_time`, `version`)
VALUES (71, 'data_abnormal_status', 'Abnormal Status', '不正常原因', '/view/data/reference/abnormal-status', 71, 1, 70,
        CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1);

INSERT INTO `smm_function` (`id`, `code`, `description`, `local_description`, `url`, `sort`, `leaf`, `parent_id`,
                            `create_time`, `update_time`, `version`)
VALUES (72, 'data_delay_types', 'Delay Type', '延误类型', '/view/data/reference/delay-types', 72, 1, 70, CURRENT_TIMESTAMP,
        CURRENT_TIMESTAMP, 1);

INSERT INTO `smm_function` (`id`, `code`, `description`, `local_description`, `url`, `sort`, `leaf`, `parent_id`,
                            `create_time`, `update_time`, `version`)
VALUES (73, 'data_handling_agent', 'Handling Transaction', '代理事务', '/view/data/reference/handling-agents', 73, 1, 70,
        CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1);

INSERT INTO `smm_function` (`id`, `code`, `description`, `local_description`, `url`, `sort`, `leaf`, `parent_id`,
                            `create_time`, `update_time`, `version`)
VALUES (74, 'data_organization', 'Organization', '机构', '/view/data/reference/organizations', 74, 1, 70,
        CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1);

INSERT INTO `smm_function` (`id`, `code`, `description`, `local_description`, `url`, `sort`, `leaf`, `parent_id`,
                            `create_time`, `update_time`, `version`)
VALUES (75, 'data_flight_status', 'Flight Status', '航班状态', '/view/data/reference/status', 75, 1, 70, CURRENT_TIMESTAMP,
        CURRENT_TIMESTAMP, 1);

INSERT INTO `smm_function` (`id`, `code`, `description`, `local_description`, `url`, `sort`, `leaf`, `parent_id`,
                            `create_time`, `update_time`, `version`)
VALUES (76, 'data_flight_type', 'Flight Type', '航班类型', '/view/data/reference/flight_types', 76, 1, 70,
        CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1);

INSERT INTO `smm_function` (`id`, `code`, `description`, `local_description`, `url`, `sort`, `leaf`, `parent_id`,
                            `create_time`, `update_time`, `version`)
VALUES (80, 'vip_data', 'Vip Data', 'VIP数据', '', 80, 0, 30, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1);

INSERT INTO `smm_function` (`id`, `code`, `description`, `local_description`, `url`, `sort`, `leaf`, `parent_id`,
                            `create_time`, `update_time`, `version`)
VALUES (81, 'data_vip_personnal', 'VIP Personnal', 'VIP人员', '/view/data/reference/vip-personnals', 81, 1, 80,
        CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1);

INSERT INTO `smm_function` (`id`, `code`, `description`, `local_description`, `url`, `sort`, `leaf`, `parent_id`,
                            `create_time`, `update_time`, `version`)
VALUES (82, 'data_vip_service', 'VIP Service', 'VIP服务', '/view/data/reference/vip-services', 82, 1, 80,
        CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1);

INSERT INTO `smm_function` (`id`, `code`, `description`, `local_description`, `url`, `sort`, `leaf`, `parent_id`,
                            `create_time`, `update_time`, `version`)
VALUES (90, 'history_flights', 'History Flights', '历史航班', '/view/history-flights', 90, 1, Null, CURRENT_TIMESTAMP,
        CURRENT_TIMESTAMP, 1);
COMMIT;