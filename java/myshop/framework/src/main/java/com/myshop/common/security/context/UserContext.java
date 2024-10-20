package com.myshop.common.security.context;


import com.google.gson.Gson;
import com.myshop.cache.Cache;
import com.myshop.cache.CachePrefix;
import com.myshop.common.enums.ResultCode;
import com.myshop.common.exception.ServiceException;
import com.myshop.common.security.AuthUser;
import com.myshop.common.security.enums.SecurityEnum;
import com.myshop.common.security.token.SecretKeyUtil;
import com.myshop.common.utils.StringUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;


/**
 * User context
 */
public class UserContext {

    /**
     * Lấy thông tin người dùng từ request
     *
     * @return Người dùng được ủy quyền
     */
    public static AuthUser getCurrentUser() {
        // Kiểm tra xem thuộc tính request có tồn tại hay không
        if (RequestContextHolder.getRequestAttributes() != null) {
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            String accessToken = request.getHeader(SecurityEnum.HEADER_TOKEN.getValue());
            return getAuthUser(accessToken);
        }
        return null;
    }

    /**
     * Lấy thông tin người dùng từ request
     *
     * @return Người dùng được ủy quyền
     */
    public static String getUuid() {
        // Kiểm tra xem thuộc tính request có tồn tại hay không
        if (RequestContextHolder.getRequestAttributes() != null) {
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            return request.getHeader(SecurityEnum.UUID.getValue());
        }
        return null;
    }


    /**
     * Lấy thông tin người dùng từ token trong bộ nhớ cache
     *
     * @param cache       Bộ nhớ cache
     * @param accessToken Token
     * @return Người dùng được ủy quyền
     */
    public static AuthUser getAuthUser(Cache cache, String accessToken) {
        try {
            // Gọi phương thức getAuthUser để lấy thông tin người dùng từ token
            AuthUser authUser = getAuthUser(accessToken);
            assert authUser != null;

            if (!cache.hasKey(CachePrefix.ACCESS_TOKEN.getPrefix(authUser.getRole(), authUser.getId()) + accessToken)) {
                throw new ServiceException(ResultCode.USER_AUTHORITY_ERROR);
            }
            return authUser;
        } catch (Exception e) {
            return null;
        }
    }

    public static String getCurrentUserToken() {
        // Kiểm tra xem thuộc tính request có tồn tại hay không
        if (RequestContextHolder.getRequestAttributes() != null) {
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            return request.getHeader(SecurityEnum.HEADER_TOKEN.getValue());
        }
        return null;
    }

    /**
     * Lấy thông tin người dùng từ token trong JWT
     *
     * @param accessToken Token
     * @return Người dùng được ủy quyền
     */
    public static AuthUser getAuthUser(String accessToken) {
        try {
            // Lấy thông tin từ token
            Claims claims = Jwts.parser().setSigningKey(SecretKeyUtil.generalKeyByDecoders()).parseClaimsJws(accessToken).getBody();
            // Lấy thông tin người dùng được lưu trữ trong claims
            String json = claims.get(SecurityEnum.USER_CONTEXT.getValue()).toString();
            return new Gson().fromJson(json, AuthUser.class);
        } catch (Exception e) {
            return null;
        }
    }


    /**
     * Ghi thông tin người mời
     */
    public static void settingInviter(String memberId, Cache cache) {
        if (RequestContextHolder.getRequestAttributes() != null) {
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            // ID người mời
            String inviterId = request.getHeader(SecurityEnum.INVITER.getValue());
            if (StringUtils.isNotEmpty(inviterId)) {
                cache.put(CachePrefix.INVITER.getPrefix() + memberId, inviterId);
            }
        }
    }


}