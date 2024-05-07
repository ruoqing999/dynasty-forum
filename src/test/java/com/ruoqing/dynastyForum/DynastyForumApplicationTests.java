package com.ruoqing.dynastyForum;

import cn.hutool.core.util.CharsetUtil;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpStatus;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.ruoqing.dynastyForum.common.BaseEntity;
import com.ruoqing.dynastyForum.component.QQComponent;
import com.ruoqing.dynastyForum.component.XunFeiComponent;
import com.ruoqing.dynastyForum.util.Assert;
import com.ruoqing.dynastyForum.xunfei.ApiAuthUtil;
import com.ruoqing.dynastyForum.xunfei.UploadRespVO;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URLEncoder;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@SpringBootTest
@Slf4j
@ActiveProfiles("dev")
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
        String encode1 = URLEncoder.encode(qqComponent.getBackUrl(), CharsetUtil.CHARSET_UTF_8);
        String encode2 = URLEncoder.encode(qqComponent.getBackUrl(), CharsetUtil.CHARSET_GBK);
        log.info("encode1: {}, encode2: {}", encode1, encode2);
        log.info("xunFeiComponent: {}", xunFeiComponent);
        log.info("test file:{}", ResourceUtils.getFile("classpath:test.txt"));
    }

    @Test
    void uploadDocToXF() throws FileNotFoundException {

        long ts = System.currentTimeMillis() / 1000;

        String appId = xunFeiComponent.getAppId();
        String secret = xunFeiComponent.getSecret();
        String uploadUrl = xunFeiComponent.getUploadUrl();

        File file = ResourceUtils.getFile("classpath:test.txt");

        Map<String, String> headerMap = new HashMap<>();
        headerMap.put("Content-Type", org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE);
        headerMap.put("appId", appId);
        headerMap.put("timestamp", String.valueOf(ts));
        headerMap.put("signature", ApiAuthUtil.getSignature(appId, secret, ts));

        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("file", file);
        paramMap.put("fileType", "wiki");

        try (HttpResponse httpResponse = HttpUtil.createPost(uploadUrl).form(paramMap).addHeaders(headerMap).execute()) {
            log.info("讯飞文档上传相应: {}", httpResponse);
            Assert.isTrue(!Objects.equals(httpResponse.getStatus(), HttpStatus.HTTP_OK), "上传失败");
            log.info("UploadRespVO: {}", JSONUtil.toBean(httpResponse.body(), UploadRespVO.class));
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
                .strategyConfig(builder -> builder.addInclude("dy_user_post").addTablePrefix("dy")
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
