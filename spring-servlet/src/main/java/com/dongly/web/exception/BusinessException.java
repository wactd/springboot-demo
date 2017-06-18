package com.dongly.web.exception;

import java.util.Objects;

/**
 * Created by tiger on 2017/6/18.
 */
public class BusinessException extends BaseException {

    private EnumStatus enumStatus;

    public BusinessException(EnumStatus enumStatus) {
        super(enumStatus.getStatus(), enumStatus.getMessage());
        this.enumStatus = enumStatus;
    }
    public BusinessException(EnumStatus error,  Object value) {
        this(error);
    }
    public BusinessException(EnumStatus enumStatus, String key, Object value) {
        this(enumStatus, value);
    }

    public BusinessException(EnumStatus enumStatus, Throwable cause) {
        super(enumStatus.getStatus(), enumStatus.getMessage(), cause);
        this.enumStatus = enumStatus;
    }

    public BusinessException(EnumStatus enumStatus, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(enumStatus.getStatus(), enumStatus.getMessage(), cause, enableSuppression, writableStackTrace);
        this.enumStatus = enumStatus;
    }

    public EnumStatus getEnumStatus() {
        return enumStatus;
    }

    @Override
    public String toString() {
        if (Objects.nonNull(enumStatus)) {
            final StringBuilder sb = new StringBuilder(getClass().getName());
            sb.append(": [").append(enumStatus.name()).append("][")
                    .append(enumStatus.getStatus()).append("][");
            if (Objects.nonNull(this.getCause())) {
                sb.append(this.getCause());
            } else {
                sb.append(enumStatus.getMessage());
            }
            return sb.append("]").toString();
        } else {
            return super.toString();
        }
    }
}
