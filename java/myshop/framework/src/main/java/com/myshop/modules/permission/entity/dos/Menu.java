package com.myshop.modules.permission.entity.dos;

import com.baomidou.mybatisplus.annotation.TableName;
import com.myshop.mybatis.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * Quyền menu
 *
 * @author vantrang
 */
@Data
@TableName("myshop_menu")
@ApiModel(value = "Quyền menu")
public class Menu extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 7050744476203495207L;

    @ApiModelProperty(value = "Tiêu đề menu")
    private String title;

    @ApiModelProperty(value = "Tên route")
    private String name;

    @ApiModelProperty(value = "Đường dẫn")
    private String path;

    @ApiModelProperty(value = "Cấp độ menu")
    private Integer level;

    @ApiModelProperty(value = "Tệp route phía client")
    private String frontRoute;

    @ApiModelProperty(value = "ID cha")
    private String parentId = "0"; // Giá trị mặc định

    @ApiModelProperty(value = "Thứ tự sắp xếp")
    private Double sortOrder;

    @ApiModelProperty(value = "URL quyền, * dùng để khớp mờ, phân cách bằng dấu phẩy")
    private String permission;
}