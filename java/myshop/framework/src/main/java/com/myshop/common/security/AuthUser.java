package com.myshop.common.security;


import com.myshop.common.security.enums.UserEnums;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author vantrang
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthUser implements Serializable {

    private static final long serialVersionUID = 582441893336003319L;

    /**
     * username
     */
    private String username;

    /**
     * nickname
     */
    private String nickName;

    /**
     * avatar
     */
    private String face;

    /**
     * id
     */
    private String id;

    /**
     * Có hiệu lực trong thời gian dài (được sử dụng trong các tình huống đăng nhập ứng dụng di động hoặc các tình huống tin cậy, v.v.)
     */
    private Boolean longTerm = false;

    /**
     * @see UserEnums
     * Vai trò
     */
    private UserEnums role;

    /**
     * Nếu vai trò là người bán, trường id cửa hàng này sẽ tồn tại
     * storeId
     */
    private String storeId;
    /**
     * Nếu vai trò là người bán thì trường id cửa hàng này sẽ tồn tại
     * clerkId
     */
    private String clerkId;

    /**
     * Nếu vai trò là người bán thì trường tên cửa hàng này sẽ tồn tại
     * storeName
     */
    private String storeName;

    /**
     * Có phải là siêu quản trị viên không?
     */
    private Boolean isSuper = false;

    /**
     * id tenant
     */
    private String tenantId;


    /**
     * // Khai báo constructor của lớp AuthUser, nhận vào các tham số: username, id, nickName, face, role
     *
     * @param username
     * @param id
     * @param nickName
     * @param face
     * @param role
     */
    public AuthUser(String username, String id, String nickName, String face, UserEnums role) {
        // Gán giá trị cho thuộc tính username,...
        this.username = username;
        this.face = face;
        this.id = id;
        this.role = role;
        this.nickName = nickName;
    }


}