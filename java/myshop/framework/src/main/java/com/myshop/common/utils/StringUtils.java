package com.myshop.common.utils;


import cn.hutool.core.util.StrUtil;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * String utils
 *
 * @author vantrang
 */
public class StringUtils extends StrUtil {

    /**
     * Phương thức mã hóa MD5
     *
     * @param str Chuỗi
     * @return Chuỗi
     */
    public static String md5(String str) {
        // Khai báo biến messageDigest để lưu trữ đối tượng MessageDigest, khởi tạo bằng null
        MessageDigest messageDigest = null;
        try {
            messageDigest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException ex) {
            ex.printStackTrace();
            return null;
        }
        //// Mã hóa chuỗi bằng MD5 và lưu kết quả vào mảng byte
        byte[] resultByte = messageDigest.digest(str.getBytes());
        StringBuffer result = new StringBuffer();
        for (int i = 0; i < resultByte.length; ++i) {
            int v = 0xFF & resultByte[i];
            if (v < 16) {
                result.append("0");
            }
            result.append(Integer.toHexString(v));
        }
        return result.toString();
    }

    /**
     * Lấy số ngẫu nhiên
     *
     * @param n Số lần ngẫu nhiên
     * @return
     */
    public static String getRandStr(int n) {
        // Khởi tạo đối tượng Random để tạo ra các số ngẫu nhiên
        Random random = new Random();
        String sRand = "";
        for (int i = 0; i < n; i++) {
            String rand = String.valueOf(random.nextInt(10));
            sRand += rand;
        }
        //Trả về chuỗi ngẫu nhiên đã được tạo
        return sRand;
    }

    /**
     * Cắt chuỗi, nếu vượt quá độ dài thì cắt
     *
     * @param var  Chuỗi
     * @param size Độ dài
     * @return Chuỗi được xử lý
     */
    public static String subStringLength(String var, Integer size) {
        // Kiểm tra xem độ dài của chuỗi var có lớn hơn size hay không
        if (var.length() > size) {
            return var.substring(0, (size - 4)).toString() + "...";
        }
        // Nếu độ dài chuỗi var nhỏ hơn hoặc bằng size, trả về chuỗi var nguyên bản
        return var;
    }

    /**
     * Chuyển đổi đối tượng thành map
     *
     * @param obj
     * @return
     * @throws Exception
     */
    public static Map<String, Object> objectToMap(Object obj) throws Exception {
        // Kiểm tra xem đối tượng có null hay không
        if (obj == null) {
            return null;
        }
        Map<String, Object> map = new HashMap<String, Object>(16);

        BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
        for (PropertyDescriptor property : propertyDescriptors) {
            String key = property.getName();
            if (key.compareToIgnoreCase("class") == 0) {
                continue;
            }
            Method getter = property.getReadMethod();
            Object value = getter != null ? getter.invoke(obj) : null;
            // Thêm cặp key-value vào Map
            map.put(key, value);
        }

        return map;
    }


    /**
     * Chuyển đổi camel case thành dấu gạch dưới
     */
    public static String camel2Underline(String str) {

        // Kiểm tra xem chuỗi có rỗng hay không
        if (StrUtil.isBlank(str)) {
            return "";
        }
        if (str.length() == 1) {
            return str.toLowerCase();
        }
        StringBuffer sb = new StringBuffer();
        for (int i = 1; i < str.length(); i++) {
            if (Character.isUpperCase(str.charAt(i))) {
                sb.append("_" + Character.toLowerCase(str.charAt(i)));
            } else {
                // Nối thêm ký tự hiện tại vào StringBuffer
                sb.append(str.charAt(i));
            }
        }
        return (str.charAt(0) + sb.toString()).toLowerCase();
    }

    /**
     * Nếu chuỗi {@code str} đã cho không chứa {@code appendStr}, thì nối {@code appendStr} vào cuối {@code str};
     * Nếu đã chứa {@code appendStr}, thì nối {@code otherwise} vào cuối {@code str}.
     *
     * @param str       Chuỗi đã cho
     * @param appendStr Nội dung cần nối
     * @param otherwise Nội dung nối vào cuối {@code str} khi {@code appendStr} không thỏa mãn
     * @return Chuỗi đã nối
     */
    public static String appendIfNotContain(String str, String appendStr, String otherwise) {
        // Kiểm tra xem chuỗi str hoặc appendStr có rỗng hay không
        if (isEmpty(str) || isEmpty(appendStr)) {
            return str;
        }
        // Kiểm tra xem chuỗi str có chứa chuỗi appendStr hay không
        if (str.contains(appendStr)) {
            return str.concat(otherwise);
        }
        return str.concat(appendStr);
    }

    /**
     * Cắt chuỗi
     *
     * @param str    Chuỗi
     * @param length Độ dài
     * @return Chuỗi được xử lý
     */
    public static String sub(String str, Integer length) {
        // Kiểm tra xem độ dài của chuỗi str có nhỏ hơn length hay không
        if (str.length() < length) {
            return str;
        }
        return str.substring(0, length);
    }

    /**
     * Lọc các ký tự đặc biệt
     *
     * @param str
     * @return
     */
    public static String filterSpecialChart(String str) {
        // Khai báo chuỗi regular expression (regEx) để xác định các ký tự đặc biệt cần lọc bỏ
        String regEx = "[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);
        return m.replaceAll("").trim();
    }

    /**
     * double to price string
     *
     * @return
     */
    public static String toFen(Double doubleValue) {
        // Chuyển đổi số double thành chuỗi
        String str = doubleValue.toString();

        if (!str.contains(".")) {
            str = str + ".00";
        } else if (str.substring(str.indexOf(".")).length() == 2) {
            str = str + "0";
        }
        return str;
    }

}