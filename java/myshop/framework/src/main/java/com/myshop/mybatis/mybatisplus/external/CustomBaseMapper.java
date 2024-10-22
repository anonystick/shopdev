package com.myshop.mybatis.mybatisplus.external;

import java.util.List;


/**
 * @author vantrang
 */
public interface CustomBaseMapper<T> {


    /**
     * Chèn hàng loạt
     *
     * @param entityList
     * @return
     */
    long insertBatchSomeColumn(List<T> entityList);

    int insertIgnoreBatchAllColumn(List<T> list);
}