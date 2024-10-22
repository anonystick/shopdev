package com.myshop.modules.permission.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.myshop.common.security.token.Token;
import com.myshop.modules.permission.entity.dos.AdminUser;
import org.springframework.cache.annotation.CacheConfig;

@CacheConfig(cacheNames = "{adminuser}")
public interface AdminUserService extends IService<AdminUser> {

    /**
     * Đăng nhập người dùng
     *
     * @param username Tên đăng nhập
     * @param password Mật khẩu
     * @return Token
     */
    Token login(String username, String password);

    /**
     * Tìm kiếm quản trị viên theo tên đăng nhập
     *
     * @param username Tên đăng nhập
     * @return Đối tượng quản trị viên hoặc null nếu không tìm thấy
     */
    AdminUser findByUsername(String username);
}
