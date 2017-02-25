package com.baidu.microflow.models;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import com.baidu.microflow.Application;
import com.baidu.microflow.config.Constant;
import com.baidu.microflow.generator.*;
import com.baidu.microflow.utils.SpringUtils;
import lombok.Data;
import lombok.extern.java.Log;
import scala.collection.Iterator;
import scala.collection.JavaConverters;

/**
 * A job is a DAG
 */
@Data
@Log
public class Job {
    private String id;
    private String name;
    private String script;
    // 存放调度相关脚本的路径
    private final String bashFileParentPath;

    private HashMap<ITask, Set<ITask>> tasks;

    public Job(String name, String script) {
        this.name = name;
        this.script = script;
        tasks = new HashMap<>();
        this.bashFileParentPath = SpringUtils.getBean(Constant.class).getBashFileParentPath();
    }

    public void addTask(ITask task) {
        log.info("Add Task " + task.getName() + ", HashCode: " + task.hashCode());
        tasks.put(task, null);
    }

    /**
     * Add new task
     */
    public void addTask(ITask task, ITask dependence) {
        log.info("Add Task " + task.getName() + ", HashCode: " + task.hashCode());
        Set<ITask> ts = new HashSet<>();
        ts.add(dependence);
        tasks.put(task, ts);
    }

    /**
     * Add new task
     *
     * @param task         the task
     * @param predecessors will run correctly before this task
     */
    public void addTask(ITask task, scala.collection.Set<ITask> predecessors) {
        log.info("Add Task " + task.getName() + ", HashCode: " + task.hashCode());
        if (predecessors == null) {
            tasks.put(task, null);
            return;
        }
        Set<ITask> ts = new HashSet<>();
        Iterator<ITask> iterator = predecessors.iterator();
        while (iterator.hasNext()) {
            ts.add(iterator.next());
        }
        tasks.put(task, ts);
    }
}
