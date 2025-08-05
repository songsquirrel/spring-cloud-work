package com.boot.basic.model.respond;

import com.boot.basic.model.Model;
import lombok.*;
import org.springframework.http.HttpStatus;

/**
 * 统一返回结果实体
 */
@Getter
@Setter
@Builder
public class ApiResult<T> extends Model {

    private int code;

    private String msg;

    private T data;

    public static <T> ApiResult<T> ok(T data) {
        return ApiResult.<T>builder()
                .code(HttpStatus.OK.value())
                .msg(HttpStatus.OK.getReasonPhrase())
                .data(data)
                .build();
    }

    public static ApiResult<?> ok() {
        return ApiResult.builder()
                .code(HttpStatus.OK.value())
                .msg(HttpStatus.OK.getReasonPhrase())
                .build();
    }

    public static ApiResult<?> saveOk() {
        return ApiResult.builder()
                .code(HttpStatus.OK.value())
                .msg("保存成功！")
                .build();
    }

    public static ApiResult<?> delOk() {
        return ApiResult.builder()
                .code(HttpStatus.OK.value())
                .msg("删除成功！")
                .build();
    }

    public static ApiResult<?> msgOk(String msg) {
        return ApiResult.builder()
                .code(HttpStatus.OK.value())
                .msg(msg)
                .build();
    }


    public static ApiResult<?> error(int code, String message) {
        return ApiResult.builder()
                .code(code)
                .msg(message)
                .build();
    }



}
