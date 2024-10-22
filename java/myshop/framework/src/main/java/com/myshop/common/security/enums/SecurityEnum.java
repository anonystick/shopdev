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
    HEADER_TOKEN("accessToken"),

    /**
     * Tên trường lưu thông tin người dùng trong token JWT
     */
    USER_CONTEXT("userContext"),

    /**
     * Khóa bí mật cho mã hóa và giải mã token JWT
     */
    JWT_SECRET("secret"),

    /**
     * Tên header chứa UUID của người dùng
     */
    UUID("uuid"),

    /**
     * Tên header hoặc tên trường trong token JWT chứa ID người mời
     */
    INVITER("inviter");

    String value;

    SecurityEnum(String value) {
        // Gán giá trị của chuỗi value cho thuộc tính value của enum
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
