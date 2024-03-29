package com.ruoqing.dynastyForum;

import cn.hutool.core.net.URLEncodeUtil;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.ruoqing.dynastyForum.common.BaseEntity;
import com.ruoqing.dynastyForum.component.QQComponent;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;

@SpringBootTest
class DynastyForumApplicationTests {



    private static final String SEPARATOR = System.getProperty("file.separator");

    private static final String PATH =
            System.getProperty("user.dir")
                    + SEPARATOR
                    + "src"
                    + SEPARATOR
                    + "main";

    @Resource
    private DataSourceProperties dataSourceProperties;

    @Resource
    private QQComponent qqComponent;


    @Test
    void testQQ() {
        System.out.println(qqComponent.getAppId());
        System.out.println(qqComponent.getBackUrl());
        System.out.println(qqComponent.getAppKey());
        System.out.println(URLEncodeUtil.encode(qqComponent.getBackUrl()));
    }

    @Test
    void generateCode() {
        FastAutoGenerator.create(
                        dataSourceProperties.getUrl(),
                        dataSourceProperties.getUsername(),
                        dataSourceProperties.getPassword())
                .globalConfig(builder -> builder.author("java")
                        .disableOpenDir()
                        .dateType(DateType.ONLY_DATE) //ONLY_DATE:java.util.date  SQL_PACK:java.sql TIME_PACK:java.time-LocalDateTime
                        .outputDir(PATH + SEPARATOR + "java"))
                .packageConfig(builder -> builder.parent("com.ruoqing.dynastyForum")
                        .pathInfo(Collections.singletonMap(
                                OutputFile.xml,
                                PATH + SEPARATOR + "resources" + SEPARATOR + "mapper")))
                .strategyConfig(builder -> builder.addInclude("dy_third_oauth").addTablePrefix("dy")
                        .entityBuilder()
                        .superClass(BaseEntity.class)
                        .addIgnoreColumns("create_time", "update_time", "status")
                        .enableTableFieldAnnotation()
                        .enableLombok()
                        .idType(IdType.ASSIGN_ID)
                )
                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();
    }

}
