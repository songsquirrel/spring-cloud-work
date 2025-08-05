package com.boot.basic.enums;

import lombok.Getter;

@Getter
public enum ApiResultEnum {

    SUCCESS(200, "操作成功!"),
    FAILED(40000, "操作失败!"),
    BUSINESS_BUSY(10001, "业务繁忙, 请稍后再试!"),

    ;

    private final int code;

    private final String msg;

    ApiResultEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

}
