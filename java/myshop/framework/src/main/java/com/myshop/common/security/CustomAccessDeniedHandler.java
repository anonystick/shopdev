package com.myshop.common.security;

import com.myshop.common.utils.ResponseUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;


/**
 * Trình xử lý truy cập bị từ chối tùy chỉnh
 *
 * @author vantrang
 */
@Component
@Slf4j
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) {
        // Xuất ra phản hồi cho client khi truy cập bị từ chối.
        ResponseUtil.output(response, ResponseUtil.resultMap(false, 401, "Xin lỗi, bạn không có quyền truy cập"));
    }

}