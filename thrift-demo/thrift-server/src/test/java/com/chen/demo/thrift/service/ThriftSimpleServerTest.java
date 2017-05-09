package com.chen.demo.thrift.service;

import com.chen.demo.thrift.IHelloWorldService;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TSimpleServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TServerTransport;

/**
 * @author chenshenghong
 * @version 1.0
 * @created 2017/5/2
 * @time 下午11:34
 */
public class ThriftSimpleServerTest {

    public static IHelloWorldService.Iface service;
    public static IHelloWorldService.Processor processor;

    public static void main(String[] args) {
        try {
            service = new HelloWordServiceImpl();
            processor = new IHelloWorldService.Processor(service);
            Runnable simple = new Runnable(){
                @Override
                public void run() {
                    simple(processor);
                }
            };
            new Thread(simple).start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void simple(IHelloWorldService.Processor processor) {
        try {
             TServerTransport serverTransport = new TServerSocket(9090);
             TServer server = new TSimpleServer(new TServer.Args(serverTransport).processor(processor));
             System.out.println("Starting the simple server...");
             server.serve();
         } catch (Exception e) {
             e.printStackTrace();
         }
     }
}
