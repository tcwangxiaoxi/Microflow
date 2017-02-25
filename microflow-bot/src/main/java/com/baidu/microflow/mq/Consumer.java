package com.baidu.microflow.mq;

import java.util.ArrayList;

import javax.jms.BytesMessage;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.Queue;
import javax.jms.Session;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.thrift.TDeserializer;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;

import lombok.extern.java.Log;

import com.baidu.microflow.config.ApplicationConfig;
import com.baidu.microflow.generated.thrift.MFMessage;

@Log
public class Consumer {
    private String clientId;
    private Connection connection;
    private Session session;
    private ArrayList<MessageConsumer> messageConsumers;

    public void create(String clientId, String[] queueNames) throws JMSException {
        this.clientId = clientId;

        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(ApplicationConfig.sharedInstance()
                                                                                    .getString("consumer.broker"));

        connection = connectionFactory.createConnection();
        log.info("ActiveMQ connected!");

        connection.setClientID(this.clientId);

        session = connection.createSession(false, Session.CLIENT_ACKNOWLEDGE);

        messageConsumers = new ArrayList<>();
        for (String queueName : queueNames) {
            Queue queue = session.createQueue(queueName);
            MessageConsumer messageConsumer = session.createConsumer(queue);
            messageConsumers.add(messageConsumer);
        }

        connection.start();
    }

    public void closeConnection() throws JMSException {
        connection.close();
    }

    public MFMessage receive(int timeout, boolean acknowledge) throws JMSException {
        MFMessage mfMessage = null;

        for (MessageConsumer messageConsumer : messageConsumers) {
            BytesMessage message = (BytesMessage) messageConsumer.receive(timeout);

            if (message != null) {
                byte[] buf = new byte[1024];
                int len = message.readBytes(buf);
                log.info(message.getJMSDestination().toString() + " got new message, length: " + len);
                TDeserializer deserializer = new TDeserializer(new TBinaryProtocol.Factory());
                mfMessage = new MFMessage();

                try {
                    deserializer.deserialize(mfMessage, buf);
                    if (acknowledge) {
                        message.acknowledge();
                    }
                } catch (TException e) {
                    mfMessage = null;
                    System.out.println(e.getMessage());
                }
                break;
            }
        }
        return mfMessage;
    }
}
