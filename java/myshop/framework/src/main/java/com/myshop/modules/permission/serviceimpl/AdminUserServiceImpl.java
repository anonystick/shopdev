package com.myshop.modules.permission.serviceimpl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.myshop.common.enums.ResultCode;
import com.myshop.common.exception.ServiceException;
import com.myshop.common.security.token.Token;
import com.myshop.modules.permission.entity.dos.AdminUser;
import com.myshop.modules.permission.mapper.AdminUserMapper;
import com.myshop.modules.permission.service.AdminUserService;
import com.myshop.modules.system.token.ManagerTokenGenerate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AdminUserServiceImpl extends ServiceImpl<AdminUserMapper, AdminUser> implements AdminUserService {

    @Autowired
    private ManagerTokenGenerate managerTokenGenerate;

    @Override
    public Token login(String username, String password) {
        AdminUser adminUser = this.findByUsername(username);

        if (adminUser == null || !adminUser.getStatus()) {
            throw new ServiceException(ResultCode.USER_PASSWORD_ERROR);
        }
        if (!new BCryptPasswordEncoder().matches(password, adminUser.getPassword())) {
            throw new ServiceException(ResultCode.USER_PASSWORD_ERROR);
        }
        try {
            return managerTokenGenerate.createToken(adminUser, false);
        } catch (Exception e) {
            log.error("Administrator login error", e);
        }
        return null;

    }

    @Override
    public AdminUser findByUsername(String username) {
        return getOne(new LambdaQueryWrapper<AdminUser>().eq(AdminUser::getUsername, username), false);
    }
}
