package com.dongly.web.exception;

import org.apache.tomcat.util.buf.StringUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * 错误码
 * 所有的错误码都定义在这里
 * 错误码划分：
 * 0 - 操作成功
 * [1, 100) - Api错误码
 * [1000, 1100) - web错误码
 */
public enum EnumStatus {

    UNKNOWN_ERROR(-1, "未知错误"),
    SUCCESS(0, "success");


    // 状态码
    private Integer status;
    // 状态描述
    private String message;

    EnumStatus(Integer status, String message) {
        this.status = status;
        this.message = message;
    }

    public Integer getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    // 从错误码反查
    private static Map<Integer, EnumStatus> mapStatus = new HashMap<>();

    static {
        for (EnumStatus e : EnumStatus.values()) {
            mapStatus.put(e.getStatus(), e);
        }
    }

    /**
     * 通过状态码获取枚举
     *
     * @param status
     * @return null 说明不存在
     */
    public static EnumStatus statusOf(Integer status) {
        return mapStatus.get(status);
    }

    /**
     * 通过指定名称获取枚举
     *
     * @param name
     * @return null 说明不存在
     */
    public static EnumStatus nameOf(String name) {
        String newName;
        if (Objects.isNull(name) || (newName = name.trim()).isEmpty()) {
            return null;
        }
        newName = newName.toUpperCase();
        try {
            return EnumStatus.valueOf(newName);
        } catch (IllegalArgumentException e) {
            return null;
        }
    }
}
