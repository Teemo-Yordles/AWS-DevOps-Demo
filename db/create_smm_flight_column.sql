use `view_admin`;

SET FOREIGN_KEY_CHECKS = 0;
-- -------------------------------------------------------------------------
-- Table structure for system management module - flight window column table
-- -------------------------------------------------------------------------
DROP TABLE IF EXISTS `smm_flight_column`;
CREATE TABLE IF NOT EXISTS `smm_flight_column`
(
    `id`                bigint(20)  NOT NULL AUTO_INCREMENT,
    `code`              varchar(64) NOT NULL COMMENT 'column code',
    `description`       varchar(64) NOT NULL COMMENT 'description in English',
    `local_description` varchar(64) NOT NULL COMMENT 'description in local language',
    `create_time`       datetime COMMENT 'create date/time',
    `update_time`       datetime COMMENT 'update date/time',
    `version`           int         DEFAULT 1 COMMENT 'optimistic locker',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_smm_flight_column_1` (`code`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_bin COMMENT ='航班界面列数据表，配合smm_role_flight_column控制列访问权限';

INSERT INTO view_admin.smm_flight_column (code, description, local_description, version) VALUES ('airlineCode', 'Airline' , '航空公司', 1);
INSERT INTO view_admin.smm_flight_column (code, description, local_description, version) VALUES ('flightNumber', 'Flight Number' , '航班号', 1);
INSERT INTO view_admin.smm_flight_column (code, description, local_description, version) VALUES ('sto', 'Scheduled Time ' , '计划时间', 1);
INSERT INTO view_admin.smm_flight_column (code, description, local_description, version) VALUES ('flightIndicator_cn', 'D/I' , '国内/国际', 1);
INSERT INTO view_admin.smm_flight_column (code, description, local_description, version) VALUES ('flightIndicator_en', 'D/I' , '国内/国际', 1);
INSERT INTO view_admin.smm_flight_column (code, description, local_description, version) VALUES ('movementIndicator', 'A/D' , '到达/出发', 1);
INSERT INTO view_admin.smm_flight_column (code, description, local_description, version) VALUES ('aircraftType', 'Aircraft Type' , '机型', 1);
INSERT INTO view_admin.smm_flight_column (code, description, local_description, version) VALUES ('registration', 'Registration' , '注册号', 1);
INSERT INTO view_admin.smm_flight_column (code, description, local_description, version) VALUES ('flightType_cn', 'Flight Type' , '航班类型', 1);
INSERT INTO view_admin.smm_flight_column (code, description, local_description, version) VALUES ('flightType_en', 'Flight Type' , '航班类型', 1);
INSERT INTO view_admin.smm_flight_column (code, description, local_description, version) VALUES ('terminal', 'Terminal' , '航站楼', 1);
INSERT INTO view_admin.smm_flight_column (code, description, local_description, version) VALUES ('flightStatus_cn', 'Flight Status' , '航班状态', 1);
INSERT INTO view_admin.smm_flight_column (code, description, local_description, version) VALUES ('flightStatus_en', 'Flight Status' , '航班状态', 1);
INSERT INTO view_admin.smm_flight_column (code, description, local_description, version) VALUES ('flightExternalStatus_cn', 'External Status' , '外部状态', 1);
INSERT INTO view_admin.smm_flight_column (code, description, local_description, version) VALUES ('flightExternalStatus_en', 'External Status' , '外部状态', 1);
INSERT INTO view_admin.smm_flight_column (code, description, local_description, version) VALUES ('externalStatusComment', 'External Remarks' , '外部状态标注', 1);
INSERT INTO view_admin.smm_flight_column (code, description, local_description, version) VALUES ('previousOrNextStop', 'Prev/Next Station' , '前/后站', 1);
INSERT INTO view_admin.smm_flight_column (code, description, local_description, version) VALUES ('cityDescription_cn', 'City' , '城市', 1);
INSERT INTO view_admin.smm_flight_column (code, description, local_description, version) VALUES ('cityDescription_en', 'City' , '城市', 1);
INSERT INTO view_admin.smm_flight_column (code, description, local_description, version) VALUES ('airportCodeFullRoute', 'Full Route' , '全航线', 1);
INSERT INTO view_admin.smm_flight_column (code, description, local_description, version) VALUES ('eto', 'Estimated Time' , '预计时间', 1);
INSERT INTO view_admin.smm_flight_column (code, description, local_description, version) VALUES ('ato', 'Actual Time' , '实际时间', 1);
INSERT INTO view_admin.smm_flight_column (code, description, local_description, version) VALUES ('preOrNextStepScheduleTime', 'Prev/Next Scheduled Time' , '前/后站计划时间', 1);
INSERT INTO view_admin.smm_flight_column (code, description, local_description, version) VALUES ('linkedFlightNumber', 'Turnaround' , '后接飞航班号', 1);
INSERT INTO view_admin.smm_flight_column (code, description, local_description, version) VALUES ('masterFlightNumber', 'Master' , '主航班', 1);
INSERT INTO view_admin.smm_flight_column (code, description, local_description, version) VALUES ('standList', 'Stand' , '停机位', 1);
INSERT INTO view_admin.smm_flight_column (code, description, local_description, version) VALUES ('gateList', 'Gate' , '登机口', 1);
INSERT INTO view_admin.smm_flight_column (code, description, local_description, version) VALUES ('carouselList', 'Carousel' , '行李转盘', 1);
INSERT INTO view_admin.smm_flight_column (code, description, local_description, version) VALUES ('checkinDeskList', 'Checkin Desk' , '值机柜台', 1);
INSERT INTO view_admin.smm_flight_column (code, description, local_description, version) VALUES ('pierList', 'Pier' , '指廊', 1);
INSERT INTO view_admin.smm_flight_column (code, description, local_description, version) VALUES ('chuteList', 'Chute' , '行李转送带', 1);
INSERT INTO view_admin.smm_flight_column (code, description, local_description, version) VALUES ('airbridgeList', 'Airbridge' , '登机桥', 1);
INSERT INTO view_admin.smm_flight_column (code, description, local_description, version) VALUES ('delayDescription_cn', 'Delay' , '延误', 1);
INSERT INTO view_admin.smm_flight_column (code, description, local_description, version) VALUES ('delayDescription_en', 'Delay' , '延误', 1);
INSERT INTO view_admin.smm_flight_column (code, description, local_description, version) VALUES ('cancelFlag', 'Cancel' , '取消', 1);
INSERT INTO view_admin.smm_flight_column (code, description, local_description, version) VALUES ('engineStartRequest', 'Engine Request' , '请求引擎启动时间', 1);
INSERT INTO view_admin.smm_flight_column (code, description, local_description, version) VALUES ('engineStart', 'Engine Start' , '引擎启动时间', 1);
INSERT INTO view_admin.smm_flight_column (code, description, local_description, version) VALUES ('logicalRunway', 'Runway' , '跑道', 1);
INSERT INTO view_admin.smm_flight_column (code, description, local_description, version) VALUES ('approvedTime', 'Approved Time' , '批准时间', 1);
INSERT INTO view_admin.smm_flight_column (code, description, local_description, version) VALUES ('finalTime', 'Final Time' , '最终时间', 1);
INSERT INTO view_admin.smm_flight_column (code, description, local_description, version) VALUES ('remarks', 'Remarks' , '备注', 1);
INSERT INTO view_admin.smm_flight_column (code, description, local_description, version) VALUES ('maxPax', 'Max Pax' , '最大旅客数', 1);
INSERT INTO view_admin.smm_flight_column (code, description, local_description, version) VALUES ('flightReturn', 'Return' , '返航', 1);
INSERT INTO view_admin.smm_flight_column (code, description, local_description, version) VALUES ('landingAbortIndicator', 'Abort' , '中止', 1);
INSERT INTO view_admin.smm_flight_column (code, description, local_description, version) VALUES ('vipFlag', 'VIP Class' , 'VIP等级', 1);
INSERT INTO view_admin.smm_flight_column (code, description, local_description, version) VALUES ('vipCount', 'No of VIPs' , 'VIP人数', 1);
INSERT INTO view_admin.smm_flight_column (code, description, local_description, version) VALUES ('fieldAgent_cn', 'Ground Handler' , '外场保障', 1);
INSERT INTO view_admin.smm_flight_column (code, description, local_description, version) VALUES ('fieldAgent_en', 'Ground Handler' , '外场保障', 1);
INSERT INTO view_admin.smm_flight_column (code, description, local_description, version) VALUES ('paxAgent_cn', 'Passenger Handler' , '乘客保障', 1);
INSERT INTO view_admin.smm_flight_column (code, description, local_description, version) VALUES ('paxAgent_en', 'Passenger Handler' , '乘客保障', 1);
INSERT INTO view_admin.smm_flight_column (code, description, local_description, version) VALUES ('maintenanceAgent_cn', 'Maintenance Handler' , '维修保障', 1);
INSERT INTO view_admin.smm_flight_column (code, description, local_description, version) VALUES ('maintenanceAgent_en', 'Maintenance Handler' , '维修保障', 1);
INSERT INTO view_admin.smm_flight_column (code, description, local_description, version) VALUES ('divAirport', 'Divert' , '备降', 1);
INSERT INTO view_admin.smm_flight_column (code, description, local_description, version) VALUES ('airlineSubcompanyCode', 'Subcompany' , '子公司', 1);
INSERT INTO view_admin.smm_flight_column (code, description, local_description, version) VALUES ('estimatedOffblock', 'Estimated Chock Off' , '预计撤轮挡时间', 1);
INSERT INTO view_admin.smm_flight_column (code, description, local_description, version) VALUES ('calOffblock', 'Calculated Chock Off' , '计算撤轮挡时间', 1);
INSERT INTO view_admin.smm_flight_column (code, description, local_description, version) VALUES ('targetOffblock', 'Target Chock Off' , '目标撤轮挡时间', 1);
INSERT INTO view_admin.smm_flight_column (code, description, local_description, version) VALUES ('internalEstimated', 'Internal Estimated' , '内部预计时间', 1);
INSERT INTO view_admin.smm_flight_column (code, description, local_description, version) VALUES ('calTakeoff', 'Calculated Takeoff' , '计算起飞时间', 1);
INSERT INTO view_admin.smm_flight_column (code, description, local_description, version) VALUES ('basicInformation', 'Basic Information' , '基本信息', 1);
INSERT INTO view_admin.smm_flight_column (code, description, local_description, version) VALUES ('timeInformation', 'Time Information' , '时间信息', 1);
INSERT INTO view_admin.smm_flight_column (code, description, local_description, version) VALUES ('entireRouteDTOList', 'Routes Information' , '航线信息', 1);
INSERT INTO view_admin.smm_flight_column (code, description, local_description, version) VALUES ('stands', 'Stand Information' , '停机位信息', 1);
INSERT INTO view_admin.smm_flight_column (code, description, local_description, version) VALUES ('gates', 'Gate Information' , '登机口信息', 1);
INSERT INTO view_admin.smm_flight_column (code, description, local_description, version) VALUES ('checkinDesks', 'Checkin Desk Information' , '值机柜台信息', 1);
INSERT INTO view_admin.smm_flight_column (code, description, local_description, version) VALUES ('carousels', 'Carousel Information' , '行李转盘信息', 1);
INSERT INTO view_admin.smm_flight_column (code, description, local_description, version) VALUES ('vipInformationDTOList', 'VIP Information' , 'VIP信息', 1);
INSERT INTO view_admin.smm_flight_column (code, description, local_description, version) VALUES ('delayInformationDTOList', 'Delay Information' , '延误信息', 1);

COMMIT;