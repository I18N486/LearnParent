package com.zst.mybatis.generator;

import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author stzhang
 * @Description
 * @Date 2021/4/20 19:14
 **/
public class MyGenerator {

    //程序员信息
    private static final String AUTHOR = "zst";

    //数据库配置
    private static final String DATABASENAME = "db_mybatis";
    private static final String DATABASE_URL = "81.70.151.134:3306";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "zst@0514";
    private static final String DRIVERNAME = "com.mysql.jdbc.Driver";

    //需要生成的表
    private static final String[] GENERATOR_TABLES = {
            "children",
            "user"
    };

    //路径配置
    // 模块名
    private static final String MODULENAME = "mybatis-plus-demo";
    // 模块服务特征包名
    private static final String MODULEKEY = "mybatis";
    // 项目路径（代码生成器当前父路径+模块名）
    private static final String PROJECTPATH = System.getProperty("user.dir") + File.separator + MODULENAME;
    // 基础包名
    private static final String BASEPACKAG = String.format("com.zst.%s", MODULEKEY);
    // xml mapper生成路径
    private static final String MAPPERXMLPATH = "/src/main/resources/mapper/";

    public static void main(String[] args) {
        AutoGenerator mpg = new AutoGenerator();

        //全局配置
        GlobalConfig gc = new GlobalConfig();
        gc.setAuthor(AUTHOR);
        // 输出路径
        gc.setOutputDir(PROJECTPATH+"/src/main/java");
        // 开启 ActiveRecord 模式
        gc.setActiveRecord(true);
        // 不打开输出目录
        gc.setOpen(false);
        // 覆盖重复文件
        gc.setFileOverride(true);
        // 开启BaseResultMap
        gc.setBaseResultMap(true);
        // 开启BaseColumnList
        gc.setBaseColumnList(true);
        // 开启swagger2模式
        gc.setSwagger2(true);
        mpg.setGlobalConfig(gc);


        //数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl(String.format("jdbc:mysql://%s/%s?useUnicode=true&serverTimezone=GMT&useSSL=false&characterEncoding=utf8",DATABASE_URL,DATABASENAME));
        dsc.setDriverName(DRIVERNAME);
        dsc.setUsername(USERNAME);
        dsc.setPassword(PASSWORD);
        mpg.setDataSource(dsc);


        //包配置
        PackageConfig pc = new PackageConfig();
        pc.setModuleName("");
        //父包名
        pc.setParent(BASEPACKAG);
        //实体类包名
        pc.setEntity("api.pojo.entity");
        //mapper包名
        pc.setMapper("biz.dao");
        //service包名
        pc.setService("biz.service");
        //serviceImpl包名
        pc.setServiceImpl("biz.service.impl");
        //controller包名
        pc.setController("biz.controller");
        mpg.setPackageInfo(pc);


        //自定义配置（多用于配置生成mapper xml）
        InjectionConfig ic = new InjectionConfig() {
            @Override
            public void initMap() {

            }
        };
        //对应模板引擎freemarker
        //String templatePath = "/templates/mapper.xml.ftl";
        //对应模板引擎velocity
        String templatePath = "/templates/mapper.xml.vm";
        List<FileOutConfig> focList = new ArrayList<>();
        //tips：注意别忘记填入模板路径
        focList.add(new FileOutConfig(templatePath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                //自定义mapper xml文件名
                return PROJECTPATH+MAPPERXMLPATH+tableInfo.getEntityName()+"Mapper"+ StringPool.DOT_XML;
            }
        });
        ic.setFileOutConfigList(focList);
        mpg.setCfg(ic);


        //配置模板
        TemplateConfig templateConfig = new TemplateConfig();
        //已经在自定义配置过了，这里去掉生成器的默认值
        templateConfig.setXml(null);
        mpg.setTemplate(templateConfig);


        //策略配置
        StrategyConfig strategyConfig = new StrategyConfig();
        //表名与实体类名对应关系（下划线转驼峰）
        strategyConfig.setColumnNaming(NamingStrategy.underline_to_camel);
        //表字段与实体属性对应关系（下划线转驼峰）
        strategyConfig.setNaming(NamingStrategy.underline_to_camel);
        //开启实体类lombok模式
        strategyConfig.setEntityLombokModel(true);
        //开始实体类链式模式（类似builder模式）
        strategyConfig.setChainModel(true);
        //设置生成的controller是RestController
        strategyConfig.setRestControllerStyle(true);
        //设置表名
        strategyConfig.setInclude(GENERATOR_TABLES);
        //表名前缀
        strategyConfig.setTablePrefix(pc.getModuleName()+"_");
        mpg.setStrategy(strategyConfig);
        //设置模板引擎，默认是velocity，要用freemarker，则需要进行设置
        //mpg.setTemplateEngine(new FreemarkerTemplateEngine());
        mpg.execute();
    }


}
