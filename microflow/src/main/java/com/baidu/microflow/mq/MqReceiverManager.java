package com.baidu.microflow.mq;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/**
 * Created by wangxiaoxi03 on 2017/2/20.
 */
@Component
public class MqReceiverManager {

    @JmsListener(destination = "sample.queue")
    public void receiveMessage(String email) {
        System.out.println("Received <" + email + ">");
    }

}
