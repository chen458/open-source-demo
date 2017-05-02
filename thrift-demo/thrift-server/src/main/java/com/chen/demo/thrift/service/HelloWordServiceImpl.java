package com.chen.demo.thrift.service;

import com.chen.demo.thrift.IHelloWorldService;
import org.apache.thrift.TException;

/**
 * @author chenshenghong
 * @version 1.0
 * @created 2017/5/2
 * @time 下午11:30
 */
public class HelloWordServiceImpl implements IHelloWorldService.Iface {

    public String sayHello(String name) throws TException {
        return "测试通过, name = " + name;
    }
}
