package com.myshop.modules.permission.serviceimpl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.myshop.modules.permission.entity.dos.Menu;
import com.myshop.modules.permission.entity.vo.UserMenuVO;
import com.myshop.modules.permission.mapper.MenuMapper;
import com.myshop.modules.permission.service.MenuService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {

    @Override
    public List<UserMenuVO> findAllMenu(String userId) {
        return this.baseMapper.getUserRoleMenu(userId);
    }
}
