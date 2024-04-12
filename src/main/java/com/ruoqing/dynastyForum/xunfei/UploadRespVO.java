package com.ruoqing.dynastyForum.xunfei;

import lombok.Data;

@Data
public class UploadRespVO {

    private boolean flag;
    private int code;
    private String desc;
    private String sid;

    private Metadata data;

    @Data
    public static class Metadata{
        private String originPath;
        private String filePath;
        private String fileId;
    }

}
