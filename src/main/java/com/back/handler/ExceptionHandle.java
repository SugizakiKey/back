package com.back.handler;



import com.back.response.Result;
import lombok.extern.slf4j.Slf4j;

import org.apache.shiro.authc.ExpiredCredentialsException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;






@Slf4j
@RestControllerAdvice
public class ExceptionHandle {

    /**
     * 异常捕获入口方法
     *
     * @param e Exception
     * @return Result
     */
    @ExceptionHandler(value = UnauthorizedException.class)
    public Result handler(UnauthorizedException e) {
        log.error("运行时异常：----------------{}", e.getMessage());
        return new Result().setCode(403).setMessage("您无权访问！！！");
    }

    @ExceptionHandler(value = ExpiredCredentialsException.class)
    public Result handler(ExpiredCredentialsException e) {
        log.error("运行时异常：----------------{}", e.getMessage());
        return new Result().setCode(400).setMessage("身份已过期，请重新登录！！！");
    }



}
