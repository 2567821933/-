package com.zhang.usercenter.exception;


import com.zhang.usercenter.common.BaseResponse;
import com.zhang.usercenter.common.ErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理类
 * @author zhang
 */
@Slf4j
@RestControllerAdvice //切面功能，可以在任意代码前做额外处理
public class GlobalExceptionHandler {

    @ExceptionHandler(BusinessException.class) //表示这个方法只去捕获这个异常。
    public BaseResponse businessExceptionHandler(BusinessException e) {
        log.error("businessException:"+ e.getMessage());
        return new BaseResponse(e.getCode(),e.getMessage(),e.getDescription());
    }

    @ExceptionHandler(RuntimeException.class) //表示捕获java产生的RuntimeException异常
    public BaseResponse runtimeExceptionHandler(RuntimeException e) {
        log.error("runtimeException:"+ e.getMessage());
        return new BaseResponse(ErrorCode.SYSTEM_ERROR, e.getMessage());
    }
}
