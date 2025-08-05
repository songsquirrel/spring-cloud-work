package com.boot.basic.utils;

import com.boot.basic.exception.BizException;
import org.springframework.http.HttpStatus;

/**
 * 异常工具类
 */
public class ExceptionUtil {

    public static BizException verifyException(String msg) {
        return new BizException(HttpStatus.BAD_REQUEST.value(), msg);
    }

}
