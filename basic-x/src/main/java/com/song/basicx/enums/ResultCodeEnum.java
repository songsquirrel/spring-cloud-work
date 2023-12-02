package com.song.basicx.enums;

import jodd.net.HttpStatus;
import lombok.Getter;

@Getter
public enum ResultCodeEnum {

    SUCCESS(200, "操作成功!"),
    FAILED(40000, "操作失败!"),
    BUSINESS_BUSY(10001, "业务繁忙, 请稍后再试!"),

    ;

    private final int code;

    private final String msg;

    ResultCodeEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

}
