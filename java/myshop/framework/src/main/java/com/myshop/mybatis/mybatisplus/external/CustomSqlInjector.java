package com.myshop.mybatis.mybatisplus.external;

import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.injector.DefaultSqlInjector;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import com.baomidou.mybatisplus.extension.injector.methods.InsertBatchSomeColumn;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author vantrang
 */
@Component
public class CustomSqlInjector extends DefaultSqlInjector {

    /**
     * Nếu bạn chỉ cần thêm phương thức, hãy giữ lại các phương thức đi kèm với mybatis plus.
     * Trước tiên, bạn có thể lấy super.getMethodList(), sau đó thêm add
     */
    @Override
    public List<AbstractMethod> getMethodList(Class<?> mapperClass, TableInfo tableInfo) {
        // Lưu ý: Bộ tiêm SQL này kế thừa DefaultSqlInjector (bộ tiêm mặc định), gọi phương thức getMethodList của DefaultSqlInjector và giữ lại phương thức tích hợp sẵn của mybatis-plus.
        List<AbstractMethod> methodList = super.getMethodList(mapperClass, tableInfo);
        // Tiêm InsertBatchSomeColumn
        // !t.isLogicDelete() có nghĩa là không xóa trường một cách logic, !"update_time".equals(t.getColumn()) có nghĩa là không cần trường có tên update_time, sẽ không có thao tác nào được thực hiện
        // MethodList.add(new InsertBatchSomeColumn(t -> !t.isLogicDelete() && !"update_time".equals(t.getColumn())));
        // Để xóa t.isLogicDelete() một cách logic, mặc định không bắt buộc
        methodList.add(new InsertBatchSomeColumn(t -> !t.isLogicDelete()));
        methodList.add(new BulkInsertIgnoreAll("bulkInsertIgnoreAll"));
        return methodList;
    }
}