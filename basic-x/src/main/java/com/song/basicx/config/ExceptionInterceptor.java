package com.song.basicx.config;

import com.song.basicx.config.exception.BizException;
import com.song.basicx.model.respond.ApiResult;
import com.song.basicx.utils.ApiResultUtil;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class ExceptionInterceptor {

    @ExceptionHandler(BizException.class)
    @ResponseBody
    public ApiResult<String> handlerBizException(BizException e){
        return ApiResultUtil.result(e.getCodeEnum());
    }
}
