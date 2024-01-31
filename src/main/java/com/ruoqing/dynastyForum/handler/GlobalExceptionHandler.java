package com.ruoqing.dynastyForum.handler;


import com.ruoqing.dynastyForum.common.Result;
import com.ruoqing.dynastyForum.constant.ResultConstant;
import com.ruoqing.dynastyForum.handler.exception.AuthorizationException;
import com.ruoqing.dynastyForum.handler.exception.ServiceException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;


/**
 * 全局异常处理器
 *
 * @author ruoyi
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ServiceException.class)
    public Result<Void> handleRuntimeException(ServiceException e) {
        return Result.error(ResultConstant.SERVICE_ERROR.getCode(), e.getMessage());
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public Result<Map<String, String>> handleValidException(MethodArgumentNotValidException exception) {

        Map<String, String> map = new HashMap<>();
        // 获取数据校验的错误结果
        BindingResult bindingResult = exception.getBindingResult();
        // 处理错误
        bindingResult.getFieldErrors().forEach(fieldError -> {
            String message = fieldError.getDefaultMessage();
            String field = fieldError.getField();
            map.put(field, message);
        });
        log.error("数据校验出现问题：{},异常类型：{}", exception.getMessage(), exception.getClass());

        return Result.error(ResultConstant.VALID_ERROR.getCode(), ResultConstant.VALID_ERROR.getMessage(), map);
    }

    @ExceptionHandler(value = AuthorizationException.class)
    public Result<Void> handleAuthorizationException(AuthorizationException exception) {
        return Result.error(ResultConstant.AUTHORIZATION_ERROR.getCode(), ResultConstant.AUTHORIZATION_ERROR.getMessage());
    }


}
