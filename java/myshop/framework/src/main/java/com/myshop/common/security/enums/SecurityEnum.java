package com.myshop.common.security.enums;

/**
 * Các hằng số liên quan đến bảo mật
 *
 * @author vantrang
 */
public enum SecurityEnum {

    /**
     * Tên tham số token trong header
     */
    HEADER_TOKEN("accessToken"), USER_CONTEXT("userContext"), JWT_SECRET("secret"), UUID("uuid"), INVITER("inviter");

    String value;

    SecurityEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
