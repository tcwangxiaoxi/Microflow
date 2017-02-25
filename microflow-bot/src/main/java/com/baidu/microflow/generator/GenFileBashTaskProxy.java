package com.baidu.microflow.generator;

import com.baidu.microflow.generator.bo.TaskParam;
import com.baidu.microflow.models.BashTask;
import com.baidu.microflow.models.ITask;
import com.baidu.microflow.models.Job;
import lombok.extern.java.Log;

import java.util.logging.Level;

/**
 * Created by wangxiaoxi03 on 2017/2/22.
 * <p>
 * BashTaskProxy调用流程：
 * 1、根据type（来自工厂参数），生成Shell脚本。如果是ByCode的就直接使用，如果是ByResource的就查询数据库
 * 2、在最前面加入变量的赋值
 * 3、在原始脚本文件中拼接引用的前置脚本（包括保证文件路径统一的脚本）
 * 4、在原始脚本文件中拼接引用的后置脚本（处理时间等可以统一配置在这里）
 * 5、生成脚本文件
 * 6、根据生成的脚本文件的路径调用原有的bashTask.bash(String bashcmd)方法设置脚本命令
 * 7、调用bashTask.run()执行命令
 */
@Log
public class GenFileBashTaskProxy implements ITask {

    private BashTask bashTask;

    private Job job;

    private BashFileGenerator generator;

    private String genBashFilePath;

    public GenFileBashTaskProxy(Job job, TaskParam params) {
        this.job = job;

        String scriptCode = params.getName();
        if (scriptCode == null || scriptCode.length() == 0) {
            scriptCode = params.getOriginalScript().getName();
        }

        if (params.getRetries() != 0) {
            this.bashTask = new BashTask(scriptCode, params.getRetries());
        } else {
            this.bashTask = new BashTask(scriptCode);
        }
        this.generator = new BashFileGenerator(job, params, bashTask);
        // 生成文件
        generateFile();
    }

    private void generateFile() {
        String parentPath = job.getBashFileParentPath();
        try {
            genBashFilePath = generator.generatorBashFile(parentPath);
            // 设置执行脚本的路径
            bashTask.bash(genBashFilePath);
        } catch (InterruptedException e) {
            log.log(Level.WARNING, "任务线程被终止！(JobName/Id/TaskName：" + job.getName() + "/" + job.getId() + "/" + bashTask.getName() + ")" + e.getMessage(), e);
        }
    }

    @Override
    public int run() {
        return bashTask.run();
    }

    @Override
    public int getRetries() {
        return bashTask.getRetries();
    }

    @Override
    public String getName() {
        return bashTask.getName();
    }

    @Override
    public boolean equalsTo(Object obj) {
        return bashTask.equalsTo(obj);
    }
}
