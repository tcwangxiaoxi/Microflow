package com.baidu.microflow.models.algorithm;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

import com.baidu.microflow.models.ITask;
import com.baidu.microflow.models.Job;
import com.baidu.microflow.models.Task;

public class Topology {
    private Job job;

    private Set<ITask> visited;
    private Stack<ITask> orderedTask;
    private Set<ITask> onStack;
    private HashMap<ITask, ITask> edges;
    private Stack<ITask> cycle;
    private boolean hasCycle = false;


    public Topology(Job job) {
        this.job = job;

        visited = new HashSet<>();
        orderedTask = new Stack<>();
        onStack = new HashSet<>();
        edges = new HashMap<>();
    }

    /**
     * Topological sort
     */
    public void sort() {
        visited.clear();
        orderedTask.clear();
        for (ITask t : job.getTasks().keySet()) {
            if (!hasCycle && !visited.contains(t)) {
                dfs(t);
            }
        }
    }

    public Stack<ITask> getOrderedTask() {
        return orderedTask;
    }

    public boolean  getHasCycle() {
        return hasCycle;
    }

    public Stack<ITask> getCycle() {
        return cycle;
    }

    /**
     * DFS
     * @param task task to search
     */
    private void dfs(ITask task) {
        visited.add(task);
        onStack.add(task);
        if (job.getTasks().get(task) != null) {
            for (ITask t : job.getTasks().get(task)) {
                if (!visited.contains(t)) {
                    edges.put(t, task);
                    dfs(t);
                } else if (onStack.contains(t)) {
                    hasCycle = true;
                    cycle = new Stack<>();
                    for (ITask it = task; !it.equalsTo(t); it = edges.get(it)) {
                        cycle.push(it);
                    }
                    cycle.push(t);
                    cycle.push(task);
                }
            }
        }
        onStack.remove(task);
        orderedTask.push(task);
    }
}
