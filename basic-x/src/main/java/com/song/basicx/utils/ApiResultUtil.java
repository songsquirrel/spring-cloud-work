package com.song.basicx.utils;

import com.song.basicx.enums.ResultCodeEnum;
import com.song.basicx.model.respond.ApiResult;

/**
 * api 返回结果工具类, code 及 msg 必须在 ResultCodeEnum 中维护
 * 不暴露自定义 code, msg 的方法.
 */
public class ApiResultUtil {

    public static <T> ApiResult<T> success(){
        return new ApiResult<>(ResultCodeEnum.SUCCESS, null);
    }

    public static <T> ApiResult<T> success(T t){
        return new ApiResult<>(ResultCodeEnum.SUCCESS, t);
    }

    public static <T> ApiResult<T> failed(){
        return new ApiResult<>(ResultCodeEnum.FAILED, null);
    }

    public static <T> ApiResult<T> failed(T t){
        return new ApiResult<>(ResultCodeEnum.FAILED, t);
    }

    public static <T> ApiResult<T> result(ResultCodeEnum codeEnum){
        return new ApiResult<>(codeEnum, null);
    }

    public static <T> ApiResult<T> result(ResultCodeEnum codeEnum, T t){
        return new ApiResult<>(codeEnum, t);
    }



}
