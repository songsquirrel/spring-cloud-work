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
    private final ResultCodeEnum codeEnum;

    public  BizException(ResultCodeEnum codeEnum) {
        super(codeEnum.getMsg());
        this.codeEnum = codeEnum;
    }
}
