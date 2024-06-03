package com.ruoqing.dynastyForum.vo;

import lombok.Data;

@Data
public class ImgPiCuiVO {

    private Boolean status;
    private String message;
    private ImgVO data;

    @Data
    public static class ImgVO {
        private String pathname;
        private Links links;
    }

    @Data
    public static class Links{
        private String url;
    }
}
