package com.myshop.common.exception;

import com.myshop.common.enums.ResultCode;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Lớp ngoại lệ nghiệp vụ toàn cục
 *
 * @author vantrang
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ServiceException extends RuntimeException {

    // Biến serialVersionUID được sử dụng để xác định phiên bản của lớp này khi được serialize
    private static final long serialVersionUID = 3447728300174142127L;

    public static final String DEFAULT_MESSAGE = "Lỗi mạng, vui lòng thử lại sau!";

    /**
     * Thông báo lỗi
     */
    private String msg = DEFAULT_MESSAGE;

    /**
     * Mã lỗi
     */
    private ResultCode resultCode;

    public ServiceException(String msg) {
        this.resultCode = ResultCode.ERROR;
        this.msg = msg;
    }

    public ServiceException() {
        super();
    }

    public ServiceException(ResultCode resultCode) {
        this.resultCode = resultCode;
    }

    public ServiceException(ResultCode resultCode, String message) {
        this.resultCode = resultCode;
        this.msg = message;
    }

}