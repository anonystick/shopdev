package com.myshop.common.utils;


import org.springframework.beans.BeanUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Sao chép thuộc tính đối tượng
 */
public class BeanUtil {

    /**
     * Sao chép thuộc tính
     *
     * @param objectFrom Đối tượng nguồn
     * @param objectTo   Đối tượng đích để sao chép
     */
    public static void copyProperties(Object objectFrom, Object objectTo) {
        // Sử dụng phương thức copyProperties của lớp BeanUtils để sao chép các thuộc tính
        BeanUtils.copyProperties(objectFrom, objectTo);
    }


    /**
     * Lấy mảng tên thuộc tính
     *
     * @param o Đối tượng để lấy trường
     * @return Mảng tên trường
     */
    public static String[] getFiledName(Object o) {
        // Lấy danh sách các trường được khai báo trong lớp của đối tượng o
        Field[] fields = o.getClass().getDeclaredFields();
        // Lấy danh sách các trường được khai báo trong lớp cha của đối tượng o
        Field[] superFields = o.getClass().getSuperclass().getDeclaredFields();
        // Khởi tạo mảng string có kích thước bằng tổng số trường của lớp và lớp cha
        String[] fieldNames = new String[fields.length + superFields.length];
        int index = 0;
        for (int i = 0; i < fields.length; i++) {
            fieldNames[index] = fields[i].getName();
            index++;
        }
        for (int i = 0; i < superFields.length; i++) {
            if ("id".equals(superFields[i].getName())) {
                continue;
            }
            fieldNames[index] = superFields[i].getName();
            index++;
        }
        return fieldNames;
    }

    /**
     * Lấy giá trị thuộc tính theo tên thuộc tính
     *
     * @param fieldName Tên thuộc tính
     * @param o         Đối tượng
     * @return Giá trị thuộc tính
     */
    public static Object getFieldValueByName(String fieldName, Object o) {
        try {
            // Lấy ký tự đầu tiên của tên trường và chuyển đổi thành chữ hoa
            String firstLetter = fieldName.substring(0, 1).toUpperCase();
            // Tạo tên phương thức getter dựa trên tên trường, ví dụ: "getName", "getAge", ...
            String getter = "get" + firstLetter + fieldName.substring(1);
            // Lấy phương thức getter tương ứng từ lớp của đối tượng o
            Method method = o.getClass().getMethod(getter, new Class[]{});
            // Gọi phương thức getter để lấy giá trị của trường và trả về
            Object value = method.invoke(o, new Object[]{});
            return value;
        } catch (Exception e) {
            return null;
        }
    }


    /**
     * Chuyển đổi đối tượng thành cặp key-value
     * Định dạng A=a&B=b&C=c
     *
     * @param object Đối tượng
     * @return Kết quả định dạng
     */
    public static String formatKeyValuePair(Object object) {
        // Chuẩn bị chuỗi nhận
        StringBuilder stringBuffer = new StringBuilder();
        // Lấy các trường của đối tượng
        String[] fieldNames = BeanUtil.getFiledName(object);
        // Duyệt qua tất cả các thuộc tính
        for (int j = 0; j < fieldNames.length; j++) {
            // Không phải là phần tử đầu tiên và cũng không phải là phần tử cuối cùng, nối "&"
            if (j != 0) {
                stringBuffer.append("&");
            }
            // Lấy tên thuộc tính
            String key = fieldNames[j];
            // Lấy giá trị
            Object value = BeanUtil.getFieldValueByName(key, object);
            assert value != null;
            stringBuffer.append(key).append("=").append(value.toString());
        }
        return stringBuffer.toString();
    }

    /**
     * Chuyển đổi cặp key-value thành đối tượng
     * Định dạng A=a&B=b&C=c thành đối tượng
     *
     * @param str Chuỗi đối tượng
     * @param t   Loại đối tượng
     * @param <T> Loại đối tượng
     * @return Kết quả định dạng
     */
    public static <T> T formatKeyValuePair(String str, T t) {
        // Điền cặp key-value cho tham số
        String[] params = str.split("&");

        // Lấy các trường của đối tượng
        String[] fieldNames = BeanUtil.getFiledName(t);

        try {
            // Lặp qua mỗi tham số
            for (String param : params) {
                String[] keyValues = param.split("=");
                for (int i = 0; i < fieldNames.length; i++) {
                    if (fieldNames[i].equals(keyValues[0])) {
                        Field f = t.getClass().getDeclaredField(fieldNames[i]);
                        f.setAccessible(true);
                        // Chỉ chuyển đổi khi độ dài là 2, nếu không thì không chuyển đổi
                        if (keyValues.length == 2) {
                            f.set(t, keyValues[1]);
                        }
                    }
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return t;
    }

}
