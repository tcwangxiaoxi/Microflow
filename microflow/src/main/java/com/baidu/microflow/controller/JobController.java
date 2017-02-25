package com.baidu.microflow.controller;

import com.baidu.microflow.config.JsonResult;
import com.baidu.microflow.config.ResultCode;
import com.baidu.microflow.dao.JobDao;
import com.baidu.microflow.entity.Job;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.java.Log;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * Created by zhangcuibao on 2016/11/3.
 */

@Log
@Api(value = "任务相关", description = "任务创建、修改相关API")
@RestController
@RequestMapping(value = "/job")
public class JobController extends BaseController {

    @Resource
    JobDao jobDao;

    @ApiOperation("任务添加")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public JsonResult addJob(@ModelAttribute Job job) {
        log.info("add microflow job");
        try {
            jobDao.save(job);
        } catch (Exception e) {
            return new JsonResult(ResultCode.ERROR, e.getMessage());
        }
        return new JsonResult(ResultCode.SUCCESS);
    }

    @ApiOperation("根据id获取任务详情")
    @RequestMapping(value = "/getById", method = {RequestMethod.POST, RequestMethod.GET})
    public JsonResult jobGetById(@RequestParam(value = "id") int id) {
        log.info("get microflow job by id:" + id);
        try {
            Job job = jobDao.findOne(id);
            if (job == null) {
                return new JsonResult(ResultCode.NOTFOUND);
            }

            return new JsonResult(ResultCode.SUCCESS, job);
        } catch (Exception e) {
            return new JsonResult(ResultCode.ERROR, e.getMessage());
        }
    }

    @ApiOperation("根据任务名称获取任务详情")
    @RequestMapping(value = "/getByJobname", method = {RequestMethod.POST, RequestMethod.GET})
    public JsonResult jobGetByName(
            @RequestParam(value = "job_name", defaultValue = "") String name) {
        log.info("get microflow job by name:" + name);
        try {
            List<Job> jobs = jobDao.findByJobName(name);
            if (jobs == null || jobs.isEmpty()) {
                return new JsonResult(ResultCode.NOTFOUND);
            }
            return new JsonResult(ResultCode.SUCCESS, jobs);
        } catch (Exception e) {
            return new JsonResult(ResultCode.ERROR, e.getMessage());
        }
    }

    @ApiOperation("任务修改")
    @RequestMapping(value = "/modify", method = RequestMethod.POST)
    public JsonResult modifyJob(@ModelAttribute Job job) {
        log.info("modify microflow job");
        try {
            // 验证记录是否存在
            Boolean isExist = jobDao.exists(job.getId());
            if (!isExist) {
                return new JsonResult(ResultCode.NOTFOUND);
            }
            job.setUpdateTime(new Date());
            jobDao.save(job);
        } catch (Exception e) {
            return new JsonResult(ResultCode.ERROR, e.getMessage());
        }
        return new JsonResult(ResultCode.SUCCESS);
    }

    @ApiOperation("根据id修改任务状态")
    @RequestMapping(value = "/modifyById", method = RequestMethod.POST)
    public JsonResult modifyJobById(
            @RequestParam(value = "active") int active,
            @RequestParam(value = "id") int id) {
        try {
            // 验证记录是否存在
            Boolean isExist = jobDao.exists(id);
            if (!isExist) {
                return new JsonResult(ResultCode.NOTFOUND);
            }
            // 根据id更新状态
            Date updateDate = new Date();
            jobDao.updateActiveById(active, updateDate, id);
            return new JsonResult(ResultCode.SUCCESS);
        } catch (Exception e) {
            return new JsonResult(ResultCode.ERROR, e.getMessage());
        }
    }
}
