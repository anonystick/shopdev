package com.myshop.mybatis.mybatisplus;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

/**
 * Field Auditing
 *
 * @author vantrang
 */
@Component
public class MybatisObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        //TODO: triển khai với các vai trò khác nhau
    }

    @Override
    public void updateFill(MetaObject metaObject) {

        //TODO: triển khai với các vai trò
    }
}