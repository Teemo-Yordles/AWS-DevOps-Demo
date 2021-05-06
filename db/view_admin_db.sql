-- script to create system manament module related tables
DROP DATABASE IF EXISTS `view_admin`;
CREATE DATABASE `view_admin` CHARACTER SET utf8mb4 COLLATE utf8mb4_bin;
use `view_admin`;

SET FOREIGN_KEY_CHECKS=0;

source .\create_smm_function.sql
source .\create_smm_flight_column.sql
source .\create_smm_group.sql
source .\create_smm_parameter.sql
source .\create_smm_system_parameter.sql
source .\create_smm_profile.sql
source .\create_smm_profile_column_setting.sql
source .\create_smm_profile_parameter.sql
source .\create_smm_user.sql
source .\create_smm_user_profile.sql
source .\create_smm_role.sql
source .\create_smm_role_function.sql
source .\create_smm_role_parameter.sql
