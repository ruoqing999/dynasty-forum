package com.ruoqing.dynastyForum.component;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "img.pt")
public class ImgPTComponent {

    private String url;
    private String token;

}
