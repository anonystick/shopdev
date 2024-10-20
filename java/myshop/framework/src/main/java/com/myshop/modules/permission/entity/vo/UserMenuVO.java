package com.myshop.modules.permission.entity.vo;

import com.myshop.modules.permission.entity.dos.Menu;
import lombok.Data;

import java.io.Serializable;

/**
 * Đối tượng giá trị Quyền Menu của Người Dùng (UserMenuVO)
 *
 * @author vantrang
 */
@Data
public class UserMenuVO extends Menu implements Serializable {

    private static final long serialVersionUID = -7478870595109016162L;

    /**
     * Có phải Super Admin hay không
     */
    private Boolean isSuper;

    /**
     * Lấy giá trị "Có phải Super Admin hay không".
     * Nếu giá trị isSuper là null, trả về false.
     *
     * @return true nếu là Super Admin, false nếu không phải hoặc null.
     */
    public Boolean getSuper() {
        if (this.isSuper == null) {
            return false;
        }
        return isSuper;
    }
}