package com.baidu.microflow.mq;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Component;

import javax.jms.Queue;

/**
 * Created by wangxiaoxi03 on 2017/2/20.
 */
@Component
public class MqSenderManager {

    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

    @Autowired
    private Queue queue;

    public void sendTest(String msg) {
        jmsMessagingTemplate.convertAndSend(queue, msg);
    }
}
