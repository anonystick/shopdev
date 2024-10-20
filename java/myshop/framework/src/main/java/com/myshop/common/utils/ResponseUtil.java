package com.myshop.common.utils;

import com.google.gson.Gson;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Công cụ xuất phản hồi (response)
 */
@Slf4j
public class ResponseUtil {

    static final String ENCODING = "UTF-8"; // Mã hóa mặc định
    static final String CONTENT_TYPE = "application/json;charset=UTF-8"; // Kiểu nội dung mặc định

    /**
     * Xuất nội dung ra phía client và chỉ định trạng thái
     *
     * @param response Đối tượng HttpServletResponse
     * @param status   Mã trạng thái HTTP (ví dụ: 200, 404, 500)
     * @param content  Nội dung cần xuất
     */
    public static void output(HttpServletResponse response, Integer status, String content) {
        ServletOutputStream servletOutputStream = null;
        try {
            response.setCharacterEncoding(ENCODING); // Thiết lập mã hóa cho phản hồi
            response.setContentType(CONTENT_TYPE); // Thiết lập kiểu nội dung cho phản hồi
            response.setStatus(status); // Thiết lập mã trạng thái HTTP
            servletOutputStream = response.getOutputStream(); // Lấy luồng xuất dữ liệu
            servletOutputStream.write(content.getBytes()); // Ghi nội dung vào luồng xuất
        } catch (Exception e) {
            log.error("Lỗi xuất phản hồi:", e); // Ghi log lỗi
        } finally {
            // Đảm bảo luồng xuất được đóng và flush dữ liệu
            if (servletOutputStream != null) {
                try {
                    servletOutputStream.flush(); // Xả dữ liệu còn lại trong bộ đệm
                    servletOutputStream.close(); // Đóng luồng
                } catch (IOException e) {
                    log.error("Lỗi đóng luồng xuất phản hồi:", e); // Ghi log lỗi
                }
            }
        }
    }


    /**
     * Xuất JSON ra response
     *
     * @param response  Đối tượng HttpServletResponse
     * @param status    Mã trạng thái HTTP
     * @param resultMap Map chứa dữ liệu cần chuyển đổi thành JSON
     */
    public static void output(HttpServletResponse response, Integer status, Map<String, Object> resultMap) {
        response.setStatus(status); // Thiết lập mã trạng thái
        output(response, resultMap); // Gọi phương thức output để xuất JSON
    }

    /**
     * Xuất JSON ra response
     *
     * @param response  Đối tượng HttpServletResponse
     * @param resultMap Map chứa dữ liệu cần chuyển đổi thành JSON
     */
    public static void output(HttpServletResponse response, Map<String, Object> resultMap) {
        ServletOutputStream servletOutputStream = null;
        try {
            response.setCharacterEncoding(ENCODING);
            response.setContentType(CONTENT_TYPE);
            servletOutputStream = response.getOutputStream();
            // Chuyển đổi map thành JSON sử dụng Gson và ghi vào luồng xuất
            servletOutputStream.write(new Gson().toJson(resultMap).getBytes());
        } catch (Exception e) {
            log.error("Lỗi xuất phản hồi:", e);
        } finally {
            if (servletOutputStream != null) {
                try {
                    servletOutputStream.flush();
                    servletOutputStream.close();
                } catch (IOException e) {
                    log.error("Lỗi đóng luồng xuất phản hồi:", e);
                }
            }
        }
    }

    /**
     * Xây dựng phản hồi (map)
     *
     * @param flag Cờ thành công/thất bại
     * @param code Mã kết quả
     * @param msg  Thông báo
     * @return Map chứa dữ liệu phản hồi
     */
    public static Map<String, Object> resultMap(boolean flag, Integer code, String msg) {
        return resultMap(flag, code, msg, null); // Gọi phương thức overload với data = null
    }

    /**
     * Xây dựng phản hồi (map)
     *
     * @param flag Cờ thành công/thất bại
     * @param code Mã kết quả
     * @param msg  Thông báo
     * @param data Dữ liệu (có thể là null)
     * @return Map chứa dữ liệu phản hồi
     */
    public static Map<String, Object> resultMap(boolean flag, Integer code, String msg, Object data) {
        // Khởi tạo map
        Map<String, Object> resultMap = new HashMap<String, Object>(16);
        // Thêm cờ thành công/thất bại
        resultMap.put("success", flag);
        // Thêm thông báo
        resultMap.put("message", msg);
        // Thêm mã kết quả
        resultMap.put("code", code);
        // Thêm timestamp
        resultMap.put("timestamp", System.currentTimeMillis());
        if (data != null) {
            resultMap.put("result", data);
        }
        return resultMap;
    }
}
