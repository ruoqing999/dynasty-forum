package com.ruoqing.dynastyForum.component;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "xf")
public class XunFeiComponent {

    private String appId;
    private String secret;
    private String uploadUrl;

}
