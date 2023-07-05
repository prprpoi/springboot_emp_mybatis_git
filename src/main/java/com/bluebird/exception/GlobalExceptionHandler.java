package com.bluebird.exception;

import com.bluebird.po.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)//捕获所有的异常
    public Result ex(Exception ex) {
        ex.printStackTrace();
        return Result.error("对不起,操作失败，请重新尝试");
    }
}
