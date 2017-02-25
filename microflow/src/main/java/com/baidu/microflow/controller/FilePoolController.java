package com.baidu.microflow.controller;

import com.baidu.microflow.config.JsonResult;
import com.baidu.microflow.config.ResultCode;
import com.baidu.microflow.dao.FilePoolDao;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.java.Log;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

/**
 * Created by zhangcuibao on 2016/11/24.
 */

@Log
@Api(value = "文件池", description = "文件池新增、修改等相关API")
@RestController
@RequestMapping(value = "/file")
public class FilePoolController {
    @Resource
    FilePoolDao filePoolDao;

    @ApiOperation("文件添加")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public JsonResult addFile(@RequestParam("name") String name, @RequestParam("file") MultipartFile file) {
        log.info("add microflow file");
        try {
            if (!file.isEmpty()) {
                byte[] bytes = file.getBytes();
                BufferedOutputStream stream =
                        new BufferedOutputStream(new FileOutputStream(new File(name + "-uploaded")));
                stream.write(bytes);
                stream.close();
            }
            // 处理文件blob
            // filePoolDao.save(file);
        } catch (Exception e) {
            return new JsonResult(ResultCode.ERROR, e.getMessage());
        }
        return new JsonResult(ResultCode.SUCCESS);
    }
}
