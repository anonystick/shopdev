package com.myshop.common.exception;

import com.myshop.common.enums.ResultCode;
import com.myshop.common.enums.ResultUtil;
import com.myshop.common.vo.ResultMessage;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.stream.Collectors;


/**
 * Xử lý ngoại lệ toàn cục
 */
@RestControllerAdvice
@Slf4j
public class GlobalControllerExceptionHandler {

    /**
     * Nếu vượt quá độ dài, trải nghiệm tương tác giữa phía frontend và backend sẽ không tốt, sử dụng thông báo lỗi mặc định
     */
    static Integer MAX_LENGTH = 200;

    /**
     * Ngoại lệ tùy chỉnh
     *
     * @param e
     * @return
     */
    @ExceptionHandler(ServiceException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ResultMessage<Object> handleServiceException(HttpServletRequest request, final Exception e, HttpServletResponse response) {


        // Nếu là ngoại lệ tùy chỉnh, thì lấy ngoại lệ và trả về thông báo lỗi tùy chỉnh
        if (e instanceof ServiceException) {
            ServiceException serviceException = ((ServiceException) e);
            ResultCode resultCode = serviceException.getResultCode();

            Integer code = null;
            String message = null;

            if (resultCode != null) {
                code = resultCode.code();
                message = resultCode.message();
            }
            // Nếu có thông báo mở rộng, thì xuất thông báo mở rộng trong ngoại lệ, sau đó bổ sung thông báo ngoại lệ
            if (!serviceException.getMsg().equals(ServiceException.DEFAULT_MESSAGE)) {
                message += ":" + serviceException.getMsg();
            }

            // Xử lý một số ngoại lệ đặc biệt, không in nhật ký ở cấp độ error nữa
            assert serviceException.getResultCode() != null;
            if (serviceException.getResultCode().equals(ResultCode.DEMO_SITE_EXCEPTION)) {
                log.debug("[DEMO_SITE_EXCEPTION]:{}", serviceException.getResultCode().message(), e);
                return ResultUtil.error(code, message);
            }
            if (serviceException.getResultCode().equals(ResultCode.USER_AUTH_EXPIRED)) {
                log.debug("403 :{}", serviceException.getResultCode().message(), e);
                return ResultUtil.error(code, message);
            }


            log.error("Ngoại lệ toàn cục [ServiceException]:{}-{}", serviceException.getResultCode().code(), serviceException.getResultCode().message(), e);
            return ResultUtil.error(code, message);

        } else {

            log.error("Ngoại lệ toàn cục [ServiceException]:", e);
        }

        // Thông báo lỗi mặc định
        String errorMsg = "Máy chủ gặp lỗi, vui lòng thử lại sau";
        if (e != null && e.getMessage() != null && e.getMessage().length() < MAX_LENGTH) {
            errorMsg = e.getMessage();
        }
        return ResultUtil.error(ResultCode.ERROR.code(), errorMsg);
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ResultMessage<Object> runtimeExceptionHandler(HttpServletRequest request, final Exception e, HttpServletResponse response) {

        log.error("Ngoại lệ toàn cục [RuntimeException]:", e);

        return ResultUtil.error(ResultCode.ERROR);
    }


    /**
     * Ngoại lệ xác thực bean không thành công
     *
     * @see javax.validation.Valid
     * @see org.springframework.validation.Validator
     * @see org.springframework.validation.DataBinder
     */
    @ExceptionHandler(BindException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ResultMessage<Object> validExceptionHandler(HttpServletRequest request, final Exception e, HttpServletResponse response) {

        BindException exception = (BindException) e;
        List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
        // Xử lý thông báo lỗi
        try {
            if (!fieldErrors.isEmpty()) {
                return ResultUtil.error(ResultCode.PARAMS_ERROR.code(), fieldErrors.stream().map(FieldError::getDefaultMessage) // Lấy tên trường của mỗi đối tượng
                        .collect(Collectors.joining(", ")));
            }
            return ResultUtil.error(ResultCode.PARAMS_ERROR);
        } catch (Exception ex) {
            return ResultUtil.error(ResultCode.PARAMS_ERROR);
        }
    }

    /**
     * Ngoại lệ xác thực bean không thành công
     *
     * @see javax.validation.Valid
     * @see org.springframework.validation.Validator
     * @see org.springframework.validation.DataBinder
     */
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ResultMessage<Object> constraintViolationExceptionHandler(HttpServletRequest request, final Exception e, HttpServletResponse response) {
        ConstraintViolationException exception = (ConstraintViolationException) e;
        return ResultUtil.error(ResultCode.PARAMS_ERROR.code(), exception.getMessage());
    }
}
