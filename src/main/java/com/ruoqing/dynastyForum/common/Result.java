package com.ruoqing.dynastyForum.common;

import cn.hutool.http.HttpStatus;
import lombok.Data;

@Data
public class Result<T> {

    private Integer code;

    private String msg;

    private T data;

    private Result(T data) {
        this.code = HttpStatus.HTTP_OK;
        this.data = data;
    }

    private Result() {
        this.code = HttpStatus.HTTP_OK;
    }

    private Result(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Result(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static <T> Result<T> ok(T data) {
        return new Result<>(data);
    }

    public static <T> Result<T> ok() {
        return new Result<>();
    }

    public static <T> Result<T> error(int code, String msg) {
        return new Result<>(code, msg);
    }

    public static <T> Result<T> error(int code, String msg, T data) {
        return new Result<>(code, msg, data);
    }

}
