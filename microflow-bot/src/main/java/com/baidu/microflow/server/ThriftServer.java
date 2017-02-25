package com.baidu.microflow.server;

import org.apache.thrift.TProcessor;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TSimpleServer;
import org.apache.thrift.transport.TServerSocket;

import com.baidu.microflow.config.ApplicationConfig;
import com.baidu.microflow.generated.thrift.MicroflowBotService;

import lombok.Getter;
import lombok.extern.java.Log;

@Log
@Getter
public class ThriftServer implements Runnable {

    private TServer server;

    public void run() {
        try {
            log.info("Microflow Bot Server start ...");

            ApplicationConfig applicationConfig = ApplicationConfig.sharedInstance();
            Integer tserverPort = (Integer) applicationConfig.get("server.listen");

            TProcessor tprocessor = new MicroflowBotService.Processor<>(
                    new ServiceHandler());

            TServerSocket tserverSocket = new TServerSocket(tserverPort);
            TServer.Args tserverArgs = new TServer.Args(tserverSocket);
            tserverArgs.processor(tprocessor);
            tserverArgs.protocolFactory(new TBinaryProtocol.Factory());

            server = new TSimpleServer(tserverArgs);
            server.serve();
        } catch (Exception e) {
            log.warning(e.getMessage());
            e.printStackTrace();
        }
    }
}
