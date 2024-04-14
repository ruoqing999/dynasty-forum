package com.ruoqing.dynastyForum.vo;

import com.ruoqing.dynastyForum.entity.Notice;
import lombok.Data;

@Data
public class NoticeDetailVO extends Notice {

    private String avatar;

    private String nickName;
}
