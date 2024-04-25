package com.ruoqing.dynastyForum.vo;

import lombok.Data;

@Data
public class ImgPtVO {

    private Integer code;
    private String msg;
    private ImgVO data;

    @Data
    public static class ImgVO{
        private String token;
        private String name;
        private String url;
    }

}
