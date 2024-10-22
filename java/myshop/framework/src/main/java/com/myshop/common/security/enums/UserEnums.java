package com.myshop.common.security.enums;

/**
 * Token Role Type
 *
 * @author vantrang
 * @since 2024/10/16
 */
public enum UserEnums {
    /**
     * Vai trò
     */
    MEMBER("member"),
    STORE("store"),
    MANAGER("manager"),
    SYSTEM("system"),
    SEAT("seat");
    private final String role;

    UserEnums(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}