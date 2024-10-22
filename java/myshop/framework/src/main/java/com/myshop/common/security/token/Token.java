package com.myshop.common.security.token;

import lombok.Data;

/**
 * Lớp thực thể Token
 *
 * @author vantrang
 */
@Data
public class Token {
    /**
     * Access token
     */
    private String accessToken;

    /**
     * Refresh token
     */
    private String refreshToken;

}
