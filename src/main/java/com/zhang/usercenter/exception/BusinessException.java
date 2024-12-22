package com.zhang.usercenter.exception;

import com.zhang.usercenter.common.ErrorCode;

/**
 * 自定义异常
 * @author zhang
 */
public class BusinessException extends RuntimeException{//继承运行时异常，不用try catch捕获的异常。
    private static final long serialVersionUID = -6101381466048732483L;//序列化号
    private final int code;
    private final String description;

    public BusinessException(int code, String massage, String description) {
        super(massage);
        this.code = code;
        this.description = description;
    }

    public BusinessException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.code = errorCode.getCode();
        this.description = errorCode.getDescription();
    }

    public BusinessException (ErrorCode errorCode, String description) {
        super((errorCode.getMessage()));
        this.code = errorCode.getCode();
        this.description = description;
    }

    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }
}
