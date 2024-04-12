package com.ruoqing.dynastyForum;

import cn.hutool.http.HttpStatus;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.ruoqing.dynastyForum.common.BaseEntity;
import com.ruoqing.dynastyForum.component.QQComponent;
import com.ruoqing.dynastyForum.component.XunFeiComponent;
import com.ruoqing.dynastyForum.xunfei.ApiAuthUtil;
import com.ruoqing.dynastyForum.xunfei.UploadRespVO;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collections;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

@SpringBootTest
@Slf4j
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

    @Resource
    private XunFeiComponent xunFeiComponent;


    @Test
    void testQQ() throws FileNotFoundException {
        log.info("qqComponent: {}", qqComponent);
        log.info("xunFeiComponent: {}", xunFeiComponent);
        log.info("test file:{}", ResourceUtils.getFile("classpath:test.txt"));
    }

    @Test
    void uploadDocToXF() throws FileNotFoundException {

        String appId = xunFeiComponent.getAppId();
        String secret = xunFeiComponent.getSecret();
        String uploadUrl = xunFeiComponent.getUploadUrl();

        File file = ResourceUtils.getFile("classpath:test.txt");

        OkHttpClient okHttpClient = new OkHttpClient().newBuilder()
                .readTimeout(20, TimeUnit.SECONDS)
                .writeTimeout(20, TimeUnit.SECONDS)
                .connectTimeout(20, TimeUnit.SECONDS)
                .build();

        MultipartBody.Builder builder = new MultipartBody.Builder();
        RequestBody fileBody = RequestBody.Companion.create(file, MediaType.parse("multipart/form-data"));
        builder.setType(MultipartBody.FORM);
        builder.addFormDataPart("file", file.getName(), fileBody);
        builder.addFormDataPart("fileType", "wiki");
        RequestBody body = builder.build();
        long ts = System.currentTimeMillis() / 1000;
        Request request = new Request.Builder()
                .url(uploadUrl)
                .post(body)
                .addHeader("appId", appId)
                .addHeader("timestamp", String.valueOf(ts))
                .addHeader("signature", ApiAuthUtil.getSignature(appId, secret, ts))
                .build();
        try {
            Response response = okHttpClient.newCall(request).execute();
            log.info("response: {}", response);
            if (Objects.equals(response.code(), HttpStatus.HTTP_OK)) {
                assert response.body() != null;
                String respBody = response.body().string();
                log.info("UploadRespVO: {}", JSONUtil.toBean(respBody, UploadRespVO.class));
            }
        } catch (IOException e) {
            log.error("讯飞文档上传失败：{}", e.getMessage());
        }
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
                .strategyConfig(builder -> builder.addInclude("dy_notice").addTablePrefix("dy")
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
