package com.baidu.microflow;

import java.util.List;

import javax.jms.JMSException;

import com.baidu.microflow.utils.SpringUtils;
import lombok.extern.java.Log;

import com.baidu.microflow.engine.JobPool;
import com.baidu.microflow.mq.Consumer;
import com.baidu.microflow.generated.thrift.MFJob;
import com.baidu.microflow.generated.thrift.MFMessage;

import com.baidu.microflow.config.ApplicationConfig;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Log
@SpringBootApplication
public class Application {

    private static Consumer initConsumer(ApplicationConfig applicationConfig) {
        Consumer consumer = new Consumer();
        String consumerName = applicationConfig.getString("consumer.name");
        List queue = applicationConfig.getList("consumer.queue");
        String[] queueNames = new String[queue.size()];
        for (int i = 0; i < queue.size(); i++) {
            queueNames[i] = (String) queue.get(i);
        }
        try {
            consumer.create(consumerName, queueNames);
        } catch (JMSException e) {
            log.warning("Create consumer failed: " + e.getMessage());
            return null;
        }
        return consumer;
    }

    private static JobPool initJobPool(ApplicationConfig applicationConfig) {
        return new JobPool(applicationConfig.getInteger("jobpool.capacity"));
    }

    public static void main(String[] args) throws InterruptedException {
        // Initial Spring Application Context
        SpringUtils.initSpringApplicationContext(Application.class, args);

        // Initial application config
        ApplicationConfig applicationConfig = ApplicationConfig.sharedInstance();
        if (applicationConfig == null) {
            log.warning("Invalid ApplicationConfig, application exit.");
            System.exit(-1);
        }
        // Initial Message Queue Consumer
        Consumer consumer = initConsumer(applicationConfig);
        if (consumer == null) {
            log.warning("Invalid Consumer, application exit.");
            System.exit(-1);
        }
        // Initial Job Pool
        JobPool jobPool = initJobPool(applicationConfig);
        // Main loop
        while (true) {
            if (jobPool.capable()) {
                try {
                    MFMessage mfMessage = consumer.receive(100, true);
                    if (mfMessage != null) {
                        MFJob mfJob = mfMessage.getJob();
                        if (mfJob != null) {
                            log.info("Receive job " + mfJob.getJobName());
                            jobPool.addMFJob(mfJob);
                        }
                    }
                } catch (JMSException e) {
                    e.printStackTrace();
                    break;
                }
            }
            Thread.sleep(1000);
        }

        // Start ThriftServer And Listen -- Wait to complete
        //        ThriftServer tserver = new ThriftServer();
        //        Thread tsThread = new Thread(tserver);
        //        tsThread.start();

    }
}
