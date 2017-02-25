package com.baidu.microflow.generator;

import com.baidu.microflow.dao.JobScriptDictRepository;
import com.baidu.microflow.dao.ScriptResourceRepository;
import com.baidu.microflow.entity.JobScriptDict;
import com.baidu.microflow.entity.ScriptDict;
import com.baidu.microflow.entity.ScriptResource;
import com.baidu.microflow.generator.bo.Script;
import com.baidu.microflow.generator.bo.TaskParam;
import com.baidu.microflow.models.BashTask;
import com.baidu.microflow.models.Job;
import com.baidu.microflow.utils.CmdHelper;
import com.baidu.microflow.utils.CommandResult;
import com.baidu.microflow.utils.Constant;
import com.baidu.microflow.utils.SpringUtils;
import com.google.common.io.Files;
import lombok.extern.java.Log;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;

/**
 * Created by wangxiaoxi03 on 2017/2/22.
 */
@Log
public class BashFileGenerator {

    private final Job job;
    private final TaskParam taskParam;
    private final String bashFileDirpath;
    private final BashTask bashTask;

    private final static String NEXT_LINE = System.lineSeparator();
    private final static String EQ = "=";
    private final static String QUOTATION = "\"";

    private final static ScriptResourceRepository scriptResourceRepository =
            SpringUtils.getBean(ScriptResourceRepository.class);

    private final static JobScriptDictRepository jobScriptDictRepository =
            SpringUtils.getBean(JobScriptDictRepository.class);

    public BashFileGenerator(final Job job, final TaskParam params, final BashTask bashTask) {
        this.job = job;
        this.taskParam = params;
        this.bashTask = bashTask;
        this.bashFileDirpath = job.getName() + "/" + job.getId();
    }

    private String getBashFileName() {
        return bashFileDirpath + "/" + bashTask.getName();
    }

    private Map<String, String> getParams() {
        Map<String, String> params = new HashMap<>();
        List<JobScriptDict> dicts = jobScriptDictRepository.findByJobCode(job.getName());
        if (dicts != null) {
            for (JobScriptDict dict : dicts) {
                ScriptDict scriptDict = dict.getScriptDict();
                params.put(scriptDict.getDictKey(), scriptDict.getDictValue());
            }
        }
        if (taskParam.getEnvConext() != null) {
            params.putAll(taskParam.getEnvConext());
        }
        return params;
    }

    private String getScriptContent(Script script) {
        switch (script.getScriptType()) {
            case Code:
                return script.getOriginalScript();
            case Resource:
                ScriptResource sr = scriptResourceRepository.findOneByName(script.getName());
                if (sr == null) {
                    throw new IllegalArgumentException("不存在该脚本资源：" + script.getName());
                }
                return sr.getOriginalScript();
            default:
                throw new IllegalArgumentException("不支持该类型的脚本：" + script.getScriptType());
        }
    }

    private List<String> getOtherScript(List<Script> scripts) {
        List<String> scriptContents = new ArrayList<>();
        for (Script s : scripts) {
            scriptContents.add(getScriptContent(s));
        }
        return scriptContents;
    }

    private List<String> getPreScript() {
        List<String> scriptContents = getOtherScript(taskParam.getPreScripts());
        // 加入固定路径的命令
        scriptContents.add("cd `dirname $0`");
        return scriptContents;
    }

    private String getOriginalScript() {
        return getScriptContent(taskParam.getOriginalScript());
    }

    private List<String> getPostScript() {
        return getOtherScript(taskParam.getPostScripts());
    }

    /**
     * 在当前的任务下生成脚本文件
     *
     * @return
     */
    public String generatorBashFile(final String parentPath) throws InterruptedException {

        String bashFilePath = parentPath + getBashFileName();

        File parentFile = new File(parentPath + bashFileDirpath);
        if (!parentFile.exists() && !parentFile.mkdirs()) {// 判断目标文件所在的目录是否存在,判断创建目录是否成功
            log.log(Level.WARNING, "创建目标文件所在的目录失败！路径：" + bashFileDirpath);
        }

        // 添加文件头
        StringBuilder sb = new StringBuilder("#!/bin/bash").append(NEXT_LINE);

        // 添加资源文件，写入key="ss" 的格式
        if (getParams() != null) {
            for (Map.Entry<String, String> entry : getParams().entrySet()) {
                sb.append(entry.getKey()).append(EQ).
                        append(QUOTATION).append(entry.getValue()).append(QUOTATION).
                        append(NEXT_LINE);
            }
        }

        // 添加前置脚本
        if (getPreScript() != null) {
            for (String scriptContent : getPreScript()) {
                sb.append(scriptContent).append(NEXT_LINE);
            }
        }

        // 添加脚本
        sb.append(getOriginalScript());

        // 添加后置脚本
        if (getPostScript() != null) {
            for (String scriptContent : getPostScript()) {
                sb.append(scriptContent).append(NEXT_LINE);
            }
        }
        // 生成文件
        try {
            Files.write(sb.toString(), new File(bashFilePath), Charset.forName(Constant.DEFAULT_ENCODING));
            // 设置可执行权限
            log.info("====================授权脚本文件：" + bashFilePath + "==================================================");
            CommandResult result = CmdHelper.exec("chmod 777 " + bashFilePath);
            log.info("====================授权脚本文件 ERROR：" + result.getError() + "==================================================");
            log.info("====================授权脚本文件 OUTPUT：" + result.getOutput() + "==================================================");
            log.info("====================授权脚本文件 EXITVALUE：" + result.getExitValue() + "==================================================");
        } catch (IOException e) {
            log.log(Level.WARNING, "文件:" + bashFilePath + "不存在！ " + e.getMessage(), e);
        }

        // 返回脚本路径
        return bashFilePath;
    }

}
