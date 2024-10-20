package com.myshop.common.enums;

/**
 * Trả về mã trạng thái
 * Chữ số đầu tiên: 1: Sản phẩm; 2: Người dùng; 3: Giao dịch,
 * 4: Khuyến mãi, 5: Cửa hàng, 6: Trang web, 7: Cài đặt, 8: Khác
 *
 * @author vantrang
 */
public enum ResultCode {

    /**
     * Mã trạng thái thành công
     */
    SUCCESS(200, "Thành công"),

    /**
     * Tham số bất thường
     */
    PARAMS_ERROR(4002, "Tham số bất thường"),

    /**
     * Mã lỗi trả về
     */
    DEMO_SITE_EXCEPTION(4001, "Trang web demo không được phép sử dụng"),

    /**
     * Mã lỗi trả về
     */
    ERROR(400, "Máy chủ bận, vui lòng thử lại sau"),

    /**
     * Người dùng
     */
    USER_EDIT_SUCCESS(20001, "Cập nhật thông tin người dùng thành công"),
    USER_NOT_EXIST(20002, "Người dùng không tồn tại"),
    USER_NOT_LOGIN(20003, "Người dùng chưa đăng nhập"),
    USER_AUTH_EXPIRED(20004, "Phiên đăng nhập của người dùng đã hết hạn, vui lòng đăng nhập lại"),
    USER_AUTHORITY_ERROR(20005, "Quyền hạn không đủ"),
    USER_CONNECT_LOGIN_ERROR(20006, "Không tìm thấy thông tin đăng nhập"),
    USER_EXIST(20008, "Tên người dùng hoặc số điện thoại đã được đăng ký"),
    USER_PHONE_NOT_EXIST(20009, "Số điện thoại không tồn tại"),
    USER_PASSWORD_ERROR(20010, "Mật khẩu không chính xác"),
    USER_NOT_PHONE(20011, "Số điện thoại không thuộc về người dùng hiện tại"),
    USER_CONNECT_ERROR(20012, "Đăng nhập liên kết với bên thứ ba, thông tin ủy quyền không chính xác"),
    USER_RECEIPT_REPEAT_ERROR(20013, "Thông tin hóa đơn thành viên trùng lặp"),
    USER_RECEIPT_NOT_EXIST(20014, "Thông tin hóa đơn thành viên không tồn tại"),
    USER_EDIT_ERROR(20015, "Cập nhật thông tin người dùng thất bại"),
    USER_OLD_PASSWORD_ERROR(20016, "Mật khẩu cũ không chính xác"),
    USER_COLLECTION_EXIST(20017, "Không thể thêm vào mục yêu thích"),
    USER_GRADE_IS_DEFAULT(20018, "Cấp độ thành viên là cấp độ mặc định"),
    USER_NOT_BINDING(20020, "Chưa liên kết người dùng"),
    USER_AUTO_REGISTER_ERROR(20021, "Đăng ký tự động thất bại, vui lòng thử lại sau"),
    USER_OVERDUE_CONNECT_ERROR(20022, "Thông tin ủy quyền đã hết hạn, vui lòng ủy quyền/đăng nhập lại"),
    USER_CONNECT_BANDING_ERROR(20023, "Phương thức đăng nhập liên kết hiện tại đã được liên kết với tài khoản khác, cần hủy liên kết"),
    USER_CONNECT_NOT_EXIST_ERROR(20024, "Chưa có thông tin đăng nhập liên kết, không thể thực hiện chức năng đăng ký một cú nhấp chuột, vui lòng nhấp vào đăng nhập bên thứ ba để ủy quyền"),
    USER_POINTS_ERROR(20024, "Số điểm người dùng không đủ"),
    CLERK_SUPPER(20025, "Chủ cửa hàng không thể thao tác"),
    CLERK_SAVE_ERROR(20026, "Lưu thông tin nhân viên thất bại"),
    CLERK_NOT_FOUND_ERROR(20027, "Nhân viên không tồn tại"),
    USER_STATUS_ERROR(20028, "Người dùng đã bị vô hiệu hóa"),
    CLERK_USER_ERROR(20029, "Tài khoản này đã được liên kết với cửa hàng khác"),
    CLERK_ALREADY_EXIT_ERROR(20030, "Nhân viên đã tồn tại"),
    CLERK_DISABLED_ERROR(20031, "Nhân viên đã bị vô hiệu hóa"),
    CLERK_CURRENT_SUPPER(20032, "Không thể xóa nhân viên đang đăng nhập"),
    CANT_EDIT_CLERK_SHOPKEEPER(20033, "Không thể chỉnh sửa thông tin nhân viên trong quản lý nhân viên"),
    USER_MOBILE_REPEATABLE_ERROR(20034, "Số điện thoại này đã tồn tại");


    private final Integer code;
    private final String message;


    ResultCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer code() {
        return this.code;
    }

    public String message() {
        return this.message;
    }
}
