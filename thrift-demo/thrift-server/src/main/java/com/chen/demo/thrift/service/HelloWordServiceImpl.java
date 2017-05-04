package com.chen.demo.thrift.service;

import com.chen.demo.thrift.IHelloWorldService;
import org.apache.thrift.TException;

import java.util.concurrent.TimeUnit;

/**
 * @author chenshenghong
 * @version 1.0
 * @created 2017/5/2
 * @time 下午11:30
 */
public class HelloWordServiceImpl implements IHelloWorldService.Iface {

    public String sayHello(String name) throws TException {
        try {
            TimeUnit.MILLISECONDS.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "测试通过, name = " + name;
    }
}
