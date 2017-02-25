package com.baidu.microflow.controller;

import com.baidu.microflow.config.JsonResult;
import com.baidu.microflow.config.ResultCode;
import com.baidu.microflow.dao.JobTraceDao;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.java.Log;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;

/**
 * Created by zhangcuibao on 2016/11/24.
 */

@Log
@Api(value = "任务执行记录", description = "任务执行记录查询等相关API")
@RestController
@RequestMapping(value = "/jobTrace")
public class JobTraceController {
    @Resource
    JobTraceDao jobTraceDao;

    @ApiOperation("根据id查找")
    @RequestMapping(value = "/findById", method = RequestMethod.POST)
    public JsonResult findById(@RequestParam(value = "id") int id) {
        log.info("get microflow job trace by job id");
        try {
            jobTraceDao.findOne(id);
        } catch (Exception e) {
            return new JsonResult(ResultCode.ERROR, e.getMessage());
        }
        return new JsonResult(ResultCode.SUCCESS);
    }
}
