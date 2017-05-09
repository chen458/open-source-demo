package com.chen.demo.thrift.service;

import com.chen.demo.thrift.IHelloWorldService;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.server.TThreadPoolServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TServerTransport;

/**
 * @author chenshenghong
 * @version 1.0
 * @created 2017/5/2
 * @time 下午11:34
 */
public class ThriftThreadPoolServerTest {

    public static IHelloWorldService.Iface service;
    public static IHelloWorldService.Processor processor;

    public static void main(String[] args) {
        try {
            service = new HelloWordServiceImpl();
            processor = new IHelloWorldService.Processor(service);
            Runnable simple = new Runnable(){
                @Override
                public void run() {
                    tpServer(processor);
                }
            };
            new Thread(simple).start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 服务类型 TThreadPoolServer
     * 传输内容 TBinaryProtocol
     * 传输形式
     * @param processor
     */
    public static void tpServer(IHelloWorldService.Processor processor) {
        try {
            TServerTransport serverTransport = new TServerSocket(9090);
            TThreadPoolServer.Args args = new TThreadPoolServer.Args(serverTransport);
            args.inputProtocolFactory(new TBinaryProtocol.Factory());
            args.outputProtocolFactory(new TBinaryProtocol.Factory());
            TThreadPoolServer server = new TThreadPoolServer(args.processor(processor));
            System.out.println("Starting the simple server...");
            server.serve();
         } catch (Exception e) {
             e.printStackTrace();
         }
     }
}
