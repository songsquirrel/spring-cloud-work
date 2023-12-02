package com.song.basicx.model.respond;

import com.song.basicx.enums.ResultCodeEnum;
import lombok.Getter;
import lombok.Setter;

/**
 * 统一返回结果实体
 */
@Getter
@Setter
public class ApiResult<T> {

    private int code;

    private String msg;

    private T data;

    public ApiResult(ResultCodeEnum codeEnum, T t) {
        this.code = codeEnum.getCode();
        this.msg = codeEnum.getMsg();
        this.data = t;
    }

    public ApiResult(ResultCodeEnum codeEnum) {
        this.code = codeEnum.getCode();
        this.msg = codeEnum.getMsg();
    }

}
