package com.baidu.microflow.controller;

import com.baidu.microflow.config.PropConfiguration;
import lombok.extern.java.Log;
import org.apache.tomcat.util.http.fileupload.FileUploadBase;
import org.apache.tomcat.util.http.fileupload.MultipartStream;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.logging.Level;

/**
 * Created by wangxiaoxi03 on 2017/2/20.
 */
@Log
public class BaseController {

    @Resource
    protected PropConfiguration propConfiguration;

    /**
     * 基于@ExceptionHandler异常处理
     */
    @ExceptionHandler
    public String exp(HttpServletRequest request, HttpServletResponse response, Exception ex) {
        log.log(Level.WARNING, "Handler端未捕获异常！", ex);
        throw new RuntimeException("系统异常:" + ex.getMessage());
    }
}
