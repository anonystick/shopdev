package com.myshop.controller.identity;

import com.myshop.common.enums.ResultUtil;
import com.myshop.common.security.token.Token;
import com.myshop.common.vo.ResultMessage;
import com.myshop.modules.permission.service.AdminUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;

/**
 * API Quản trị viên
 *
 * @author vantrang
 */
@Slf4j
@RestController
@Api(tags = "Quản trị viên")
@RequestMapping("/manager/identity/user")
@Validated
public class AdminUserManagementController {

    @Autowired
    private AdminUserService adminUserService;

    @PostMapping(value = "/login")
    @ApiOperation(value = "Đăng nhập quản trị viên")
    public ResultMessage<Token> login(@NotNull(message = "Tên đăng nhập không được để trống") @RequestParam String username, @NotNull(message = "Mật khẩu không được để trống") @RequestParam String password) {
        //TODO: triển khai mã xác nhận trước khi cho login
        return ResultUtil.data(adminUserService.login(username, password));
    }

}
