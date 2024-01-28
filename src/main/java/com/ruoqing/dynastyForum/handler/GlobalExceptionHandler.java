package com.ruoqing.dynastyForum.handler;


import com.ruoqing.dynastyForum.common.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


/**
 * 全局异常处理器
 *
 * @author ruoyi
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ServiceException.class)
    public Result<Void> handleRuntimeException(ServiceException e) {
        return Result.error(110, e.getMessage());
    }

}
