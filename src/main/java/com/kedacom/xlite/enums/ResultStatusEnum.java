package com.kedacom.xlite.enums;

/**
 * 返回结果status
 * @author wangshuxuan
 * @date 2019/5/5 16:41
 */
public enum ResultStatusEnum {

    SUCCESS(200),
    FAILURE(406),
    NONLOGIN(-99);

    private Integer code;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    ResultStatusEnum(Integer code) {
        this.code = code;
    }
}
