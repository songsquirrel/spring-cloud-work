package com.boot.basic.utils;

import com.boot.basic.enums.ApiResultEnum;
import com.boot.basic.model.respond.ApiResult;

/**
 * api 返回结果工具类, code 及 msg 必须在 ResultCodeEnum 中维护
 * 不暴露自定义 code, msg 的方法.
 */
public class ApiResultUtil {

    public static ApiResult<?> success() {
        return result(ApiResultEnum.SUCCESS);
    }

    public static <T> ApiResult<T> success(T t) {
        return result(ApiResultEnum.SUCCESS, t);
    }

    public static <T> ApiResult<T> failed() {
        return result(ApiResultEnum.FAILED);
    }

    public static <T> ApiResult<T> failed(T t) {
        return result(ApiResultEnum.FAILED, t);
    }

    public static <T> ApiResult<T> result(ApiResultEnum codeEnum) {
        return result(codeEnum, null);
    }

    public static <T> ApiResult<T> result(ApiResultEnum codeEnum, T t) {
        return ApiResult.<T>builder()
                .code(codeEnum.getCode())
                .msg(codeEnum.getMsg())
                .data(t)
                .build();
    }

}
