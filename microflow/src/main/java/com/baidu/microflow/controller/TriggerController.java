package com.baidu.microflow.controller;

import com.baidu.microflow.config.JsonResult;
import com.baidu.microflow.config.ResultCode;
import com.baidu.microflow.dao.TriggerDao;
import com.baidu.microflow.entity.Trigger;
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

/**
 * Created by zhangcuibao on 2016/11/4.
 */
@Log
@Api(value = "触发规则相关", description = "触发规则创建、修改相关API")
@RestController
@RequestMapping(value = "/trigger")
public class TriggerController {

    @Resource
    TriggerDao triggerDao;

    @ApiOperation("触发规则添加")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public JsonResult triggerAdd(@ModelAttribute Trigger trigger) {
        log.info("add microflow trigger");
        try {
            trigger.setCreateTime(new Date());
            triggerDao.save(trigger);
        } catch (Exception e) {
            return new JsonResult(ResultCode.ERROR, e.getMessage());
        }
        return new JsonResult(ResultCode.SUCCESS);
    }

    @ApiOperation("根据id获取触发规则详情")
    @RequestMapping(value = "/getById", method = {RequestMethod.POST, RequestMethod.GET})
    public JsonResult triggerGetById(@RequestParam(value = "id") int id) {
        log.info("get microflow trigger by id:" + id);
        try {
            Trigger trigger = triggerDao.findOne(id);
            if (trigger == null) {
                return new JsonResult(ResultCode.NOTFOUND);
            }
            return new JsonResult(ResultCode.SUCCESS, trigger);
        } catch (Exception e) {
            return new JsonResult(ResultCode.ERROR, e.getMessage());
        }
    }

    @ApiOperation("触发规则修改")
    @RequestMapping(value = "/modify", method = RequestMethod.POST)
    public JsonResult triggerModify(@ModelAttribute Trigger trigger) {
        log.info("modify microflow trigger");
        try {
            trigger.setUpdateTime(new Date());
            triggerDao.save(trigger);
        } catch (Exception e) {
            return new JsonResult(ResultCode.ERROR, e.getMessage());
        }
        return new JsonResult(ResultCode.SUCCESS);
    }
}
