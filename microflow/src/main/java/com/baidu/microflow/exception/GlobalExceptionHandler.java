package com.baidu.microflow.exception;

import com.baidu.microflow.config.JsonResult;
import com.baidu.microflow.config.ResultCode;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by zhangcuibao on 2016/11/3.
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = MyException.class)
    @ResponseBody
    public JsonResult jsonErrorHandler(HttpServletRequest req, MyException e) throws Exception {
        JsonResult r = new JsonResult();
        r.setCode(ResultCode.ERROR);
        r.setMessage(e.getMessage());
        r.setData("");
        return r;
    }
}
