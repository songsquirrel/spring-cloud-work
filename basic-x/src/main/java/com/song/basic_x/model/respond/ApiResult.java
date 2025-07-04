package com.song.basic_x.model.respond;

import com.song.basic_x.enums.ApiResultEnum;
import com.song.basic_x.model.Model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.http.HttpStatus;

/**
 * 统一返回结果实体
 */
@EqualsAndHashCode(callSuper = false)
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
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
