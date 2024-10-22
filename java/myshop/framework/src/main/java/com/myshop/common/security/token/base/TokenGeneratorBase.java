package com.myshop.common.security.token.base;

import com.myshop.common.security.enums.UserEnums;
import com.myshop.common.security.token.Token;

/**
 * AbstractToken
 * Lớp trừu tượng token, định nghĩa lớp tạo token
 *
 * @author vantrang
 */
public abstract class TokenGeneratorBase<T> {

    /**
     * Tạo token
     *
     * @param user     Tên người dùng
     * @param longTerm Có thời hạn dài hay không
     * @return Đối tượng TOKEN
     */
    public abstract Token createToken(T user, Boolean longTerm);

    /**
     * Làm mới token
     *
     * @param refreshToken Token làm mới
     * @return token
     */
    public abstract Token refreshToken(String refreshToken);

    /**
     * Vai trò mặc định
     */
    public UserEnums role = UserEnums.MANAGER;

}
