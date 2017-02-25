package com.baidu.microflow.controller;

import com.baidu.microflow.config.JsonResult;
import com.baidu.microflow.config.ResultCode;
import com.baidu.microflow.mq.MqReceiverManager;
import com.baidu.microflow.mq.MqSenderManager;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.java.Log;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

/**
 * Created by wangxiaoxi03 on 2017/2/20.
 */
@Log
@Api(value = "消息队列测试", description = "发送、接收消息等相关API")
@RestController
@RequestMapping(value = "/mq")
public class AmqDocController extends BaseController {

    @Resource
    MqSenderManager mqSenderManager;

    @ApiOperation("发送消息")
    @RequestMapping(value = "/send", method = RequestMethod.GET)
    public void addFile(@RequestParam("msg") String msg) {
        mqSenderManager.sendTest(msg);
    }
}
