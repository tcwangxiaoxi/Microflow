package com.baidu.microflow.mq;

import com.baidu.microflow.generated.thrift.MFMessage;
import org.apache.thrift.TDeserializer;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.springframework.jms.support.converter.MessageConversionException;
import org.springframework.jms.support.converter.MessageConverter;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

/**
 * Created by wangxiaoxi03 on 2017/2/20.
 */
public class Thrift2MessageConverter implements MessageConverter {

    @Override
    public Message toMessage(Object object, Session session) throws JMSException, MessageConversionException {
        if(object instanceof MFMessage){
//            TDeserializer deserializer = new TDeserializer(new TBinaryProtocol.Factory());
//new Message()
        }
//        log.info(message.getJMSDestination().toString() + " got new message, length: " + len);
        return null;
    }

    @Override
    public Object fromMessage(Message message) throws JMSException, MessageConversionException {
        return null;
    }
}
