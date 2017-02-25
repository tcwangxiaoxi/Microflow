package com.baidu.microflow.controller;

import com.baidu.microflow.dao.ScriptResourceRepository;
import com.baidu.microflow.entity.ScriptResource;
import com.baidu.microflow.util.Constant;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.java.Log;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * Created by wangxiaoxi03 on 2017/2/21.
 */
@Log
@Api(value = "脚本资源", description = "脚本文件上传的相关API")
@RestController
@RequestMapping(value = "/script")
public class ScriptResourceController extends BaseController {

    @Resource
    private ScriptResourceRepository scriptResourceRepository;

    @ApiOperation("文件添加")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addFile(@RequestParam("name") String name, @RequestParam("version") String version, @RequestParam("file") MultipartFile file) throws IOException {

        byte[] fileBytes = file.getBytes();
        long size = fileBytes.length;
        if (size > propConfiguration.getLimitScriptSize()) {
            throw new IllegalArgumentException("文件（" + file.getOriginalFilename() + "）大小（" +
                    fileBytes.length + "）超过了限制，目前只支持大小在" + propConfiguration.getLimitScriptSizeStr() + "以下的脚本文件上传。");
        }
        ScriptResource scriptResource = new ScriptResource();
        scriptResource.setOriginalScript(new String(fileBytes, Constant.DEFAULT_ENCODING));
        scriptResource.setName(name);
        scriptResource.setVersion(version);
        scriptResource.setSize(size);
        try {
            scriptResourceRepository.save(scriptResource);
            return "success";
        } catch (DataIntegrityViolationException exception) {
            throw new IllegalArgumentException("提交了重复的名称，脚本资源创建失败。重复脚本名称为：" + name, exception);
        }
    }
}
