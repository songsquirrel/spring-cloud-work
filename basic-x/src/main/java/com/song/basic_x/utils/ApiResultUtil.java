package com.song.basic_x.utils;

import com.song.basic_x.enums.ApiResultEnum;
import com.song.basic_x.model.respond.ApiResult;

/**
 * api 返回结果工具类, code 及 msg 必须在 ResultCodeEnum 中维护
 * 不暴露自定义 code, msg 的方法.
 */
public class ApiResultUtil {

    public static <T> ApiResult<T> success(){
        return new ApiResult<>(ApiResultEnum.SUCCESS, null);
    }

    public static <T> ApiResult<T> success(T t){
        return new ApiResult<>(ApiResultEnum.SUCCESS, t);
    }

    public static <T> ApiResult<T> failed(){
        return new ApiResult<>(ApiResultEnum.FAILED, null);
    }

    public static <T> ApiResult<T> failed(T t){
        return new ApiResult<>(ApiResultEnum.FAILED, t);
    }

    public static <T> ApiResult<T> result(ApiResultEnum codeEnum){
        return new ApiResult<>(codeEnum, null);
    }

    public static <T> ApiResult<T> result(ApiResultEnum codeEnum, T t){
        return new ApiResult<>(codeEnum, t);
    }



}
