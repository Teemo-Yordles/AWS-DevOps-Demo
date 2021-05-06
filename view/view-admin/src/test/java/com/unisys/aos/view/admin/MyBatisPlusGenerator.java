/*
 * Copyright 2020 Unisys.com All right reserved. This software is the
 * confidential and proprietary information of Unisys.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Unisys.com
 */
package com.unisys.aos.view.admin;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.po.TableFill;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * @author LiuJ2
 * @since 2020/12/17 16:10
 */
public class MyBatisPlusGenerator {
    public static void main(String[] args) throws IOException {
        // construct generator
        AutoGenerator mpg = new AutoGenerator();

        // Configuration
        // 1. global configuration
        GlobalConfig gc = new GlobalConfig();
        String projectPath = "C:\\Projects\\view_backend_project\\view\\view-admin";
        gc.setOutputDir(projectPath + "/src/main/java");
        gc.setAuthor("LiuJ2");
        gc.setOpen(false);
        gc.setFileOverride(true);
        gc.setServiceName("%sService");
        gc.setIdType(IdType.AUTO);
        gc.setSwagger2(true);
        gc.setDateType(DateType.ONLY_DATE);
        mpg.setGlobalConfig(gc);
        // 2. configure data source
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        dataSourceConfig.setUrl("jdbc:mysql://localhost:3306/view_admin?useSSL=true&useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai");
        dataSourceConfig.setDriverName("com.mysql.cj.jdbc.Driver");
        dataSourceConfig.setUsername("root");
        dataSourceConfig.setPassword("Best9Luck!");
        dataSourceConfig.setDbType(DbType.MYSQL);
        mpg.setDataSource(dataSourceConfig);
        // 3. package configuration
        PackageConfig packageConfig = new PackageConfig();
        packageConfig.setModuleName("admin");
        packageConfig.setParent("com.unisys.aos.view");
        packageConfig.setEntity("entity");
        packageConfig.setMapper("mapper");
        packageConfig.setService("service");
        packageConfig.setController("controller");
        mpg.setPackageInfo(packageConfig);
        // 4. set strategy
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        strategy.setEntityLombokModel(true);
        strategy.setRestControllerStyle(true);
        // 写于父类中的公共字段
        strategy.setControllerMappingHyphenStyle(true);
        TableFill createTime = new TableFill("createTime", FieldFill.INSERT);
        TableFill updateTime = new TableFill("updateTime", FieldFill.INSERT_UPDATE);
        ArrayList<TableFill> tableFills = new ArrayList<>();
        tableFills.add(createTime);
        tableFills.add(updateTime);
        strategy.setTableFillList(tableFills);
        strategy.setVersionFieldName("version");
        strategy.setRestControllerStyle(true);
        strategy.setControllerMappingHyphenStyle(true);
        strategy.setInclude("smm_profile", "smm_profile_column_setting", "smm_profile_parameter", "smm_system_parameter", "smm_user_profile");
        mpg.setStrategy(strategy);

        // execute
        // uncomment this before executing
        mpg.execute();
    }
}
