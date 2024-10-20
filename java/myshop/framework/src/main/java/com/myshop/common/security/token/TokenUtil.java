package com.myshop.common.security.token;

import com.google.gson.Gson;
import com.myshop.cache.Cache;
import com.myshop.cache.CachePrefix;
import com.myshop.common.exception.ServiceException;
import com.myshop.common.properties.JWTTokenProperties;
import com.myshop.common.security.AuthUser;
import com.myshop.common.enums.ResultCode;
import com.myshop.common.security.enums.SecurityEnum;
import com.myshop.common.security.enums.UserEnums;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * TokenUtil
 *
 * @author vantrang
 */
@Component
public class TokenUtil {

    @Autowired
    private JWTTokenProperties tokenProperties;

    @Autowired
    private Cache cache;

    /**
     * Tạo token
     *
     * @param authUser Khai báo riêng tư
     * @return TOKEN
     */
    public Token createToken(AuthUser authUser) {
        Token token = new Token();

        String accessToken = createToken(authUser, tokenProperties.getTokenExpireTime());

        cache.put(CachePrefix.ACCESS_TOKEN.getPrefix(authUser.getRole(), authUser.getId()) + accessToken, 1, tokenProperties.getTokenExpireTime(), TimeUnit.MINUTES);
        // Chiến lược tạo token làm mới: Nếu token có thời hạn dài (cho ứng dụng), thì token làm mới có thời hạn mặc định là 15 ngày. Nếu là đăng nhập người dùng thông thường, thì token làm mới có thời hạn gấp đôi token thông thường.
        Long expireTime = authUser.getLongTerm() ? 15 * 24 * 60L : tokenProperties.getTokenExpireTime() * 2;
        String refreshToken = createToken(authUser, expireTime);

        cache.put(CachePrefix.REFRESH_TOKEN.getPrefix(authUser.getRole(), authUser.getId()) + refreshToken, 1, expireTime, TimeUnit.MINUTES);

        token.setAccessToken(accessToken);
        token.setRefreshToken(refreshToken);
        return token;
    }

    /**
     * Làm mới token
     *
     * @param oldRefreshToken Token làm mới
     * @return token
     */
    public Token refreshToken(String oldRefreshToken) {

        Claims claims;
        try {
            claims = Jwts.parser().setSigningKey(SecretKeyUtil.generalKeyByDecoders()).parseClaimsJws(oldRefreshToken).getBody();
        } catch (ExpiredJwtException | UnsupportedJwtException | MalformedJwtException | SignatureException |
                 IllegalArgumentException e) {
            // Token hết hạn, xác thực thất bại,...
            throw new ServiceException(ResultCode.USER_AUTH_EXPIRED);
        }

        // Lấy thông tin người dùng được lưu trữ trong claims
        String json = claims.get(SecurityEnum.USER_CONTEXT.getValue()).toString();
        AuthUser authUser = new Gson().fromJson(json, AuthUser.class);
        UserEnums userEnums = authUser.getRole();

        String username = authUser.getUsername();
        // Lấy xem token có thời hạn dài hay không
        boolean longTerm = authUser.getLongTerm();


        // Nếu có token làm mới trong cache và...
        if (cache.hasKey(CachePrefix.REFRESH_TOKEN.getPrefix(userEnums, authUser.getId()) + oldRefreshToken)) {
            Token token = new Token();

            String accessToken = createToken(authUser, tokenProperties.getTokenExpireTime());
            cache.put(CachePrefix.ACCESS_TOKEN.getPrefix(userEnums, authUser.getId()) + accessToken, 1, tokenProperties.getTokenExpireTime(), TimeUnit.MINUTES);

            // Nếu là thiết bị tin cậy, thì gia hạn thời gian token làm mới
            Long expirationTime = tokenProperties.getTokenExpireTime() * 2;
            if (longTerm) {
                expirationTime = 60 * 24 * 15L;
                authUser.setLongTerm(true);
            }

            // Chiến lược tạo token làm mới: Nếu token có thời hạn dài (cho ứng dụng), thì token làm mới có thời hạn mặc định là 15 ngày. Nếu là đăng nhập người dùng thông thường, thì token làm mới có thời hạn gấp đôi token thông thường.
            String refreshToken = createToken(authUser, expirationTime);

            cache.put(CachePrefix.REFRESH_TOKEN.getPrefix(userEnums, authUser.getId()) + refreshToken, 1, expirationTime, TimeUnit.MINUTES);
            token.setAccessToken(accessToken);
            token.setRefreshToken(refreshToken);
            cache.remove(CachePrefix.REFRESH_TOKEN.getPrefix(userEnums, authUser.getId()) + oldRefreshToken);
            return token;
        } else {
            throw new ServiceException(ResultCode.USER_AUTH_EXPIRED);
        }

    }

    /**
     * Tạo token
     *
     * @param authUser       Đối tượng chính JWT
     * @param expirationTime Thời gian hết hạn (phút)
     * @return Chuỗi token
     */
    private String createToken(AuthUser authUser, Long expirationTime) {
        // Tạo JWT
        return Jwts.builder()
                // Khai báo riêng tư JWT
                .claim(SecurityEnum.USER_CONTEXT.getValue(), new Gson().toJson(authUser))
                // Chủ thể JWT
                .setSubject(authUser.getUsername())
                // Thời gian hết hạn: hiện tại + thời gian hết hạn (phút)
                .setExpiration(new Date(System.currentTimeMillis() + expirationTime * 60 * 1000))
                // Thuật toán ký và khóa
                .signWith(SecretKeyUtil.generalKey()).compact();
    }
}