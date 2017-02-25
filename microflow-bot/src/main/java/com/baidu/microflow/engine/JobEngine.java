package com.baidu.microflow.engine;

import com.baidu.microflow.database.JobTraceContext;
import com.baidu.microflow.exceptions.CycleException;
import com.baidu.microflow.models.ITask;
import com.baidu.microflow.models.Job;
import com.baidu.microflow.models.algorithm.Topology;
import lombok.extern.java.Log;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;

/**
 * Created by eddix on 2016/11/2.
 */
@Log
public class JobEngine {
    private Topology topology;
    private Job job;
    private HashSet<ITask> taskToRun;

    JobEngine(Job job) {
        this.job = job;
        topology = new Topology(job);
    }

    /**
     * Run this job
     *
     * @throws Exception CycleException
     */
    public void run() throws CycleException {
        topology.sort();
        if (topology.getHasCycle()) {
            throw new CycleException("CycleException: Find Cycle " + topology.getCycle().toString());
        }
        log.info("Job run: " + job.getName());
        LocalDateTime startTime = LocalDateTime.now();

        taskToRun = new HashSet<>();
        taskToRun.addAll(topology.getOrderedTask());
        boolean jobSuccess = true;
        while (!taskToRun.isEmpty()) {
            ArrayList<ITask> tasksReady = new ArrayList<>();
            // tasks traversal
            for (ITask t : taskToRun) {
                if (taskReady(t)) {
                    tasksReady.add(t);
                }
            }
            // run tasks int TaskEngine
            for (ITask t : tasksReady) {
                TaskEngine taskEngine = new TaskEngine(t);
                if (!taskEngine.run()) {
                    jobSuccess = false;
                    break;
                }
                taskToRun.remove(t);
            }
            if (!jobSuccess) {
                break;
            }
        }
        LocalDateTime endTime = LocalDateTime.now();
        JobTraceContext jobTraceContext = new JobTraceContext();
        jobTraceContext.createJobTrace(job.getName(), job.getScript(), startTime, endTime, jobSuccess ? 0 : 1);
    }

    private boolean taskReady(ITask t) {
        if (job.getTasks().get(t) == null) {
            return true;
        }
        for (ITask p : job.getTasks().get(t)) {
            if (taskToRun.contains(p)) {
                return false;
            }
        }
        return true;
    }
}
