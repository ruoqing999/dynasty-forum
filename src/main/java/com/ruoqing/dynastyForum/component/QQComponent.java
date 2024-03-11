package com.ruoqing.dynastyForum.component;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "qq.oauth")
public class QQComponent {

    private String backUrl;
    private String appId;
    private String appKey;

}
