package com.baidu.microflow.server;

import org.apache.thrift.TException;

import com.baidu.microflow.generated.thrift.MicroflowBotService;

/**
 * Created by eddix on 2016/11/3.
 */
public class ServiceHandler implements MicroflowBotService.Iface {
    @Override
    public String ruok() throws TException {
        return "imok";
    }

    @Override
    public boolean cancelJob(long jobId) throws TException {
        return true;
    }
}
