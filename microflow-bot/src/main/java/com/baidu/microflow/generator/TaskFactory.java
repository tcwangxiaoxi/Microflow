package com.baidu.microflow.generator;

import com.baidu.microflow.generator.bo.Script;
import com.baidu.microflow.generator.bo.TaskParam;
import com.baidu.microflow.models.ITask;
import com.baidu.microflow.models.Job;

/**
 * Created by wangxiaoxi03 on 2017/2/22.
 */
public class TaskFactory {

    /**
     * 该任务工厂所属的Job
     */
    private Job job;

    public TaskFactory(Job job) {
        this.job = job;
    }

    public ITask createTask(TaskParam params) {
        return new GenFileBashTaskProxy(job, params);
    }

    private ITask createTask(String name, Script script, int retries) {
        TaskParam taskParam = new TaskParam();
        taskParam.setOriginalScript(script);
        taskParam.setRetries(retries);
        taskParam.setName(name);
        return createTask(taskParam);
    }

    public ITask cmd(String name, String cmd, int retries) {
        return createTask(name, new Script(Script.ScriptType.Code, cmd), retries);
    }

    public ITask execRS(String name, String resourceName, int retries) {
        return createTask(name, new Script(Script.ScriptType.Resource, resourceName), retries);
    }

    public ITask cmd(String name, String cmd) {
        return cmd(name, cmd, 0);
    }

    public ITask cmd(String cmd, int retries) {
        return cmd(null, cmd, retries);
    }

    public ITask cmd(String cmd) {
        return cmd(cmd, 0);
    }

    public ITask execRS(String name, String resourceName) {
        return execRS(null, resourceName, 0);
    }

    public ITask execRS(String resourceName, int retries) {
        return execRS(null, resourceName, retries);
    }

    public ITask execRS(String resourceName) {
        return execRS(resourceName, 0);
    }
}
