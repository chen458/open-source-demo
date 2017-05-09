package com.chen.demo.thrift.service;

import com.chen.demo.thrift.IHelloWorldService;
import com.chen.demo.thrift.model.Person;
import com.chen.demo.thrift.model.QueryParameter;
import org.apache.thrift.TException;

import java.util.ArrayList;
import java.util.List;
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


    public List<Person> getPersonList(QueryParameter parmeter) throws TException {
        short ageStart = parmeter.getAgeStart();
        short ageEnd = parmeter.getAgeEnd();
        List<Person> list = new ArrayList<>();
        if (ageStart >= ageEnd) {
            list.add(new Person("测试1", ageStart, true, 100.01));
            return list;
        }
        int index = 1;
        boolean sex = true;
        for (int age = ageStart; age <= ageEnd; age ++ ) {
            list.add(new Person("测试"+index, ageStart, sex, 100.00 + index));
            index ++;
            if (sex) {
                sex = false;
            } else {
                sex = true;
            }
        }
        return list;
    }
}
