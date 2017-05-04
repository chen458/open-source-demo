package com.chen.demo.thrift.service;

import com.chen.demo.thrift.IHelloWorldService;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;

/**
 * @author chenshenghong
 * @version 1.0
 * @created 2017/5/3
 * @time 上午12:22
 */
public class ThriftClientTest {
    public static void main(String[] args) {
        try {
            TTransport transport = new TSocket("localhost", 9090);
            transport.open();
            TProtocol protocol = new TBinaryProtocol(transport);

            IHelloWorldService.Client client = new IHelloWorldService.Client(protocol);
            System.out.println(client);
            int max = 10;
            Long start = System.currentTimeMillis();
            for (int i = 0; i < max; i++) {
                 call(client);
             }
            Long end = System.currentTimeMillis();
            Long elapse = end - start;
            int perform = Double.valueOf(max / (elapse / 1d)).intValue();
            System.out.print("thrift " + max + " 次RPC调用，耗时：" + elapse + "毫秒，平均" + perform + "次/秒");
            transport.close();
        } catch (TException x) {
            x.printStackTrace();
        }
    }

    private static void call(IHelloWorldService.Client client) throws TException {
        System.out.println(client.sayHello("测试"));
    }
}
