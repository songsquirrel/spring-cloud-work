package com.boot.basic.exception;

import com.boot.basic.enums.ApiResultEnum;
import lombok.Getter;

/**
 * 自定义业务异常类
 */
@Getter
public class BizException extends RuntimeException {

    /**
     * 业务异常信息枚举
     */
    private final int code;


    public  BizException(int code, String msg) {
        super(msg);
        this.code = code;
    }

    public BizException(ApiResultEnum apiResultEnum) {
        super(apiResultEnum.getMsg());
        this.code = apiResultEnum.getCode();
    }
}
