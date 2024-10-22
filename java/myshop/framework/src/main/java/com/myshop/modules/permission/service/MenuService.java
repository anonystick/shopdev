package com.myshop.modules.permission.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.myshop.modules.permission.entity.dos.Menu;
import com.myshop.modules.permission.entity.vo.UserMenuVO;
import org.springframework.cache.annotation.CacheConfig;

import java.util.List;

@CacheConfig(cacheNames = "{menu}")
public interface MenuService extends IService<Menu> {
    /**
     * Lấy danh sách quyền menu cụ thể mà người dùng sở hữu dựa trên tập hợp vai trò.
     *
     * @param userId ID người dùng
     * @return Danh sách các đối tượng UserMenuVO chứa thông tin quyền menu.
     */
    List<UserMenuVO> findAllMenu(String userId);
}
