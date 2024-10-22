package com.myshop.modules.permission.entity.dos;

import com.baomidou.mybatisplus.annotation.TableName;
import com.myshop.mybatis.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

/**
 * Lớp Quản trị viên
 */
@Data
@TableName("myshop_admin_user")
@ApiModel(value = "Quản trị viên")
public class AdminUser extends BaseEntity {

    private static final long serialVersionUID = 2918352800205024873L;

    @ApiModelProperty(value = "Tên đăng nhập") // Swagger annotation để mô tả trường
    @Length(max = 20, message = "Tên đăng nhập không được vượt quá 20 ký tự")
    // Hibernate Validator annotation để kiểm tra độ dài
    private String username;

    @ApiModelProperty(value = "Mật khẩu")
    private String password;

    @ApiModelProperty(value = "Biệt danh")
    @Length(max = 10, message = "Biệt danh không được vượt quá 10 ký tự")
    private String nickName;

    @ApiModelProperty(value = "Số điện thoại")
    @Length(max = 11, message = "Số điện thoại không được vượt quá 11 ký tự")
    private String mobile;

    @ApiModelProperty(value = "Email")
    @Length(max = 100, message = "Email không được vượt quá 100 ký tự")
    private String email;

    @ApiModelProperty(value = "Ảnh đại diện")
    private String avatar = "https://i.loli.net/2020/11/19/LyN6JF7zZRskdIe.png";

    @ApiModelProperty(value = "Là super admin hay không (Super admin/Quản trị viên)")
    private Boolean isSuper = false; // Giá trị mặc định

    @ApiModelProperty(value = "Trạng thái (true: hoạt động, false: bị khóa)")
    private Boolean status = true; // Giá trị mặc định

    @ApiModelProperty(value = "Mô tả/Chi tiết/Ghi chú")
    private String description;

    @ApiModelProperty(value = "ID phòng ban")
    private String departmentId;

    @ApiModelProperty(value = "Danh sách ID vai trò")
    private String roleIds;

}