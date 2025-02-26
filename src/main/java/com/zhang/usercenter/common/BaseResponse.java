package com.zhang.usercenter.common;

import lombok.Data;

import javax.management.monitor.StringMonitor;
import java.io.Serializable;


/**
 * 通用返回类
 * @param <T>
 * @author zhang
 */
@Data//生成 get set 方法
public class BaseResponse<T> implements Serializable {//序列化
    private int code;
    private T data;
    private String message;
    private String description;

    public BaseResponse(int code, T data, String message, String description) {
        this.code = code;
        this.data = data;
        this.message = message;
        this.description = description;
    }

    public BaseResponse(int code, T data, String message) {
        this.code = code;
        this.data = data;
        this.message = message;
    }

    public BaseResponse(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public BaseResponse(int code, String message, String description) {
        this.code = code;
        this.message = message;
        this.description = description;
    }
    public BaseResponse(ErrorCode errorCode) {
        this(errorCode.getCode(),null, errorCode.getMessage(),errorCode.getDescription());
    }
    public BaseResponse(ErrorCode errorCode, String description) {
        this(errorCode.getCode(),null, errorCode.getMessage(),description);
    }
}
