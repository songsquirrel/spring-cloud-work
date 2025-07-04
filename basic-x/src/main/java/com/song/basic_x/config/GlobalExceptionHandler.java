package com.song.basic_x.config;

import com.song.basic_x.exception.BizException;
import com.song.basic_x.model.respond.ApiResult;
import com.song.basic_x.utils.ApiResultUtil;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Objects;

/**
 * 统一异常处理
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    // 业务异常
    @ExceptionHandler(BizException.class)
    public ApiResult<?> handleBusinessException(BizException ex) {
        return ApiResult.error(ex.getCode(), ex.getMessage());
    }

    // 参数校验异常
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ApiResult<?> handleValidationException(MethodArgumentNotValidException ex) {
        String errorMsg = Objects.requireNonNull(ex.getBindingResult().getFieldError()).getDefaultMessage();
        return ApiResult.error(HttpStatus.BAD_REQUEST.value(), errorMsg);
    }

    // 运行时异常
    @ExceptionHandler(RuntimeException.class)
    public ApiResult<?> handleRuntimeException(RuntimeException ex) {
        return ApiResult.error(HttpStatus.INTERNAL_SERVER_ERROR.value(), "服务运行异常：" + ex.getMessage());
    }

    // 兜底异常
    @ExceptionHandler(Exception.class)
    public ApiResult<?> handleException(Exception ex) {
        return ApiResult.error(HttpStatus.INTERNAL_SERVER_ERROR.value(), "系统异常：" + ex.getMessage());
    }
}
