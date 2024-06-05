package com.ruoqing.dynastyForum.common;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PageQO {

    @NotNull
    private int pageNum = 1;

    @NotNull
    @Max(100)
    private int pageSize = 10;

    private String keyword;

}
