package com.song.basicx.config.exception;

import com.song.basicx.enums.ResultCodeEnum;
import lombok.Getter;

/**
 * 自定义业务异常类
 */
@Getter
public class BizException extends RuntimeException {

    /**
     * 业务异常信息枚举
     */
    private ResultCodeEnum codeEnum;

    public  BizException(ResultCodeEnum codeEnum) {
        this.codeEnum = codeEnum;
    }
}
