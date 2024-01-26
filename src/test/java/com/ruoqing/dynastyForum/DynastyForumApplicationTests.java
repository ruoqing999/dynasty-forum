package com.ruoqing.dynastyForum;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
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

    @Test
    void generateCode() {
        FastAutoGenerator.create(
                        dataSourceProperties.getUrl(),
                        dataSourceProperties.getUsername(),
                        dataSourceProperties.getPassword())
                .globalConfig(builder -> builder.author("java")
                        .enableSpringdoc()
                        .disableOpenDir()
                        .dateType(DateType.SQL_PACK) //ONLY_DATE:java.util.date  SQL_PACK:java.sql TIME_PACK:java.time-LocalDateTime
                        .outputDir(PATH + SEPARATOR + "java"))
                .packageConfig(builder -> builder.parent("com.example.api")
                        .pathInfo(Collections.singletonMap(
                                OutputFile.xml,
                                PATH + SEPARATOR + "resources" + SEPARATOR + "mapper")))
                .strategyConfig(builder -> builder.addInclude("account")
                        .entityBuilder()
                        .enableTableFieldAnnotation()
                        .enableLombok()
                        .idType(IdType.ASSIGN_ID)
                        .controllerBuilder()
                        .enableRestStyle())
                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();
    }

}
