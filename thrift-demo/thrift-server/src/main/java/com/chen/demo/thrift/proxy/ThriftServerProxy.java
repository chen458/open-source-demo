package com.chen.demo.thrift.proxy;

import org.apache.thrift.TProcessor;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.server.TThreadPoolServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TServerTransport;

import javax.servlet.http.HttpServlet;
import java.lang.reflect.Constructor;

/**
 * @author chenshenghong
 * @version 1.0
 * @created 2017/5/10
 * @time 下午7:49
 */
public class ThriftServerProxy extends HttpServlet {
    private int port;
    private String serviceInterface;
    private Object serviceImpl;//实现类

    public ThriftServerProxy(int port, String serviceInterface, Object serviceImpl) {
        this.port = port;
        this.serviceInterface = serviceInterface;
        this.serviceImpl = serviceImpl;
    }

    public void start(){
        try {
            Class Iface = Class.forName(getServiceInterface() + "$Iface");
            Class Processor = Class.forName(getServiceInterface() + "$Processor");
            Constructor constructor = Processor.getConstructor(Iface);
            TProcessor tp = (TProcessor) constructor.newInstance(serviceImpl);
            TServerTransport serverTransport = new TServerSocket(port);
            TThreadPoolServer.Args args = new TThreadPoolServer.Args(serverTransport);
            args.inputProtocolFactory(new TBinaryProtocol.Factory());
            args.outputProtocolFactory(new TBinaryProtocol.Factory());
            TThreadPoolServer server = new TThreadPoolServer(args.processor(tp));
            server.serve();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getServiceInterface() {
        return serviceInterface;
    }

    public void setServiceInterface(String serviceInterface) {
        this.serviceInterface = serviceInterface;
    }

    public Object getServiceImpl() {
        return serviceImpl;
    }

    public void setServiceImpl(Object serviceImpl) {
        this.serviceImpl = serviceImpl;
    }
}
