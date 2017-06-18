package com.dongly.web.exception;

/**
 * 异常基类
 */
public class BaseException extends Exception {

    private Integer code;

    public BaseException() {}

    public BaseException(Throwable cause) {
        super(cause);
    }

    public BaseException(EnumStatus es) {
        this(es.getStatus(), es.getMessage());
    }

    public BaseException(EnumStatus es, Throwable cause) {
        this(es.getStatus(), es.getMessage(), cause);
    }

    public BaseException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    public BaseException(Integer code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
    }

    /**
     *
     * @param message
     * @param cause
     * @param enableSuppression true:开启异常挂起,既保存当前异常之前的异常信息
     * @param writableStackTrace true:写入栈,将丢失原来异常发生点的信息,然后抛出一个全新的异常
     * @param code
     */
    public BaseException(Integer code, String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.code = code;
    }


    public int getCode() {
        return code;
    }

    @Override
    public String toString() {
        return new StringBuilder(getClass().getName())
                .append(": {").append(getCode())
                .append(":").append(getMessage())
                .append("}").toString();
    }
}
