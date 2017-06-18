package com.dongly.web.base;

import com.dongly.web.exception.EnumStatus;

import java.io.Serializable;

/**
 * 异步响应实体
 */
public class WebResultVo<T> implements Serializable{
    private static final long serialVersionUID = 4911772700873097117L;

    // 响应码
    private Integer status;
    // 响应信息
    private String message;
    // 响应数据
    private T data;

    private WebResultVo(Integer status, String message, T data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public static <T> WebResultVo<T> error() {
        return build(EnumStatus.UNKNOWN_ERROR);
    }

    public static <T> WebResultVo<T> error(EnumStatus status) {
        return build(status);
    }

    public static <T> WebResultVo<T> error(Integer status, String message) {
        return build(status, message, null);
    }

    public static <T> WebResultVo<T> success() {
        return build(EnumStatus.SUCCESS);
    }

    public static <T> WebResultVo<T> success(T data) {
        return build(EnumStatus.SUCCESS, data);
    }

    public static <T> WebResultVo<T> build(EnumStatus status) {
        return build(status.getStatus(), status.getMessage(), null);
    }

    public static <T> WebResultVo<T> build(EnumStatus status, T data) {
        return build(status.getStatus(), status.getMessage(), data);
    }

    public static <T> WebResultVo<T> build(Integer status, String message, T data) {
        return new WebResultVo<>(status, message, data);
    }



    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
