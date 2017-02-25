package com.baidu.microflow.engine;

import java.util.ArrayList;
import java.util.Iterator;

import com.baidu.microflow.generated.thrift.MFJob;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.java.Log;

/**
 * Auther: chenxin<chenxin@baidu.com>
 */
@Setter
@Getter
@Log
public class JobPool {
    private Integer capacity;
    private Integer jobCount;
    private ArrayList<Thread> threads;

    public JobPool(Integer capacity) {
        this.capacity = capacity;
        this.jobCount = 0;
        this.threads = new ArrayList<>();
    }

    public boolean capable() {
        Iterator<Thread> it = threads.iterator();
        while (it.hasNext()) {
            Thread t = it.next();
            if (!t.isAlive()) {
                log.warning(t.getName() + " is exit. State: " + t.getState());
                try {
                    t.join();
                } catch (InterruptedException e) {
                    log.warning("Cannot join thread " + t.getName());
                }
                jobCount--;
                it.remove();
            }
        }
        return jobCount < capacity;
    }

    public void addMFJob(MFJob mfJob) {
        ScalaJobRunnable scalaJobRunnable = new ScalaJobRunnable(
                mfJob.getJobName(), mfJob.getJobScript());
        Thread scalaJobThread = new Thread(scalaJobRunnable);
        scalaJobThread.setName(mfJob.getJobName());
        threads.add(scalaJobThread);
        scalaJobThread.start();

        jobCount++;
    }
}
