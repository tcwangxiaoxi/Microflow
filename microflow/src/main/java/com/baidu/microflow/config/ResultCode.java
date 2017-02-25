package com.baidu.microflow.config;

/**
 * Created by zhangcuibao on 2016/11/3.
 */
public enum ResultCode {
    /** 成功 */
    SUCCESS("0", "success"),
    ERROR("10001", "unknown error"),
    NOTFOUND("20001", "record not found");

    private String val;
    private String msg;

    private ResultCode(String value, String msg) {
        this.val = value;
        this.msg = msg;
    }

    public String val() {
        return val;
    }

    public String msg() {
        return msg;
    }
}
