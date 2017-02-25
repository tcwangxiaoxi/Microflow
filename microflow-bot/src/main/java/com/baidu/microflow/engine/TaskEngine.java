package com.baidu.microflow.engine;

import com.baidu.microflow.database.JobTraceContext;
import com.baidu.microflow.models.ITask;

import java.time.LocalDateTime;

/**
 * Created by eddix on 2016/11/2.
 */
public class TaskEngine {
    private final ITask task;
    private int retriesCount;

    public TaskEngine(ITask task) {
        this.task = task;
        this.retriesCount = 0;
    }

    public boolean run() {
        LocalDateTime startTime = LocalDateTime.now();
        while (retriesCount < task.getRetries()) {
            if (task.run() == 0) {
                break;
            }
            ++retriesCount;
        }
        LocalDateTime endTime = LocalDateTime.now();
        JobTraceContext jobTraceContext = new JobTraceContext();
        jobTraceContext.createTaskTrace(task.getName(), retriesCount, startTime, endTime,
                retriesCount < task.getRetries() ? 0 : 1);
        return retriesCount < task.getRetries();
    }
}
