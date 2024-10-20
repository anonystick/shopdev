package com.myshop.common.enums;

import com.myshop.common.vo.ResultMessage;

/**
 * Tiện ích trả về kết quả
 *
 * @author vantrang
 */
public class ResultUtil<T> {

    /**
     * Lớp trừu tượng, lưu trữ kết quả
     */
    private final ResultMessage<T> resultMessage;
    /**
     * Phản hồi thành công
     */
    private static final Integer SUCCESS = 200;


    /**
     * Phương thức khởi tạo, đặt giá trị mặc định cho kết quả phản hồi
     */
    public ResultUtil() {
        resultMessage = new ResultMessage<>();
        resultMessage.setSuccess(true);
        resultMessage.setMessage("success");
        resultMessage.setCode(SUCCESS);
    }

    /**
     * Trả về dữ liệu
     *
     * @param t Kiểu dữ liệu
     * @return Thông báo
     */
    public ResultMessage<T> setData(T t) {
        this.resultMessage.setResult(t);
        return this.resultMessage;
    }


    /**
     * Trả về thông báo thành công
     *
     * @param resultCode Mã trả về
     * @return Trả về thông báo thành công
     */
    public ResultMessage<T> setSuccessMsg(ResultCode resultCode) {
        this.resultMessage.setSuccess(true);
        this.resultMessage.setMessage(resultCode.message());
        this.resultMessage.setCode(resultCode.code());
        return this.resultMessage;

    }

    /**
     * Phương thức tĩnh trừu tượng, trả về tập kết quả
     *
     * @param t   Kiểu dữ liệu
     * @param <T> Kiểu dữ liệu
     * @return Thông báo
     */
    public static <T> ResultMessage<T> data(T t) {
        return new ResultUtil<T>().setData(t);
    }

    /**
     * Trả về thành công
     *
     * @param resultCode Mã trạng thái trả về
     * @return Thông báo
     */
    public static <T> ResultMessage<T> success(ResultCode resultCode) {
        return new ResultUtil<T>().setSuccessMsg(resultCode);
    }

    /**
     * Trả về thành công
     *
     * @return Thông báo
     */
    public static <T> ResultMessage<T> success() {
        return new ResultUtil<T>().setSuccessMsg(ResultCode.SUCCESS);
    }

    /**
     * Trả về thất bại
     *
     * @param resultCode Mã trạng thái trả về
     * @return Thông báo
     */
    public static <T> ResultMessage<T> error(ResultCode resultCode) {
        return new ResultUtil<T>().setErrorMsg(resultCode);
    }

    /**
     * Trả về thất bại
     *
     * @param code Mã trạng thái
     * @param msg  Thông báo trả về
     * @return Thông báo
     */
    public static <T> ResultMessage<T> error(Integer code, String msg) {
        return new ResultUtil<T>().setErrorMsg(code, msg);
    }

    /**
     * Lỗi máy chủ, thêm mã trạng thái
     *
     * @param resultCode Mã trả về
     * @return Thông báo
     */
    public ResultMessage<T> setErrorMsg(ResultCode resultCode) {
        this.resultMessage.setSuccess(false);
        this.resultMessage.setMessage(resultCode.message());
        this.resultMessage.setCode(resultCode.code());
        return this.resultMessage;
    }

    /**
     * Lỗi máy chủ, thêm mã trạng thái
     *
     * @param code Mã trạng thái
     * @param msg  Thông báo trả về
     * @return Thông báo
     */
    public ResultMessage<T> setErrorMsg(Integer code, String msg) {
        this.resultMessage.setSuccess(false);
        this.resultMessage.setMessage(msg);
        this.resultMessage.setCode(code);
        return this.resultMessage;
    }

}