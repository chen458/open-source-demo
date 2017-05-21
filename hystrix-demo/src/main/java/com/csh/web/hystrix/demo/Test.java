package com.csh.web.hystrix.demo;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;
import com.netflix.hystrix.strategy.concurrency.HystrixRequestVariableDefault;

/**
 * @author chenshenghong
 * @version 1.0
 * @created 2017/1/19
 * @time 下午5:39
 */
public class Test {

    public static void main(String[] args) throws Exception {
        HystrixRequestContext.initializeContext();
        Test2.variableDefault.set("fuck");
        System.out.println(new Command1(10).execute());
        System.out.println(new Command2(20).execute());
    }

}

class Command1 extends HystrixCommand<String> {

    private final int id;

    protected Command1(int id) {
        super(HystrixCommandGroupKey.Factory.asKey("Command1"));
        this.id = id;
    }

    protected String run() throws Exception {
        return Thread.currentThread().getName() + Test2.variableDefault.get();
    }
}

class Command2 extends HystrixCommand<String> {

    private final int id;

    protected Command2(int id) {
        super(HystrixCommandGroupKey.Factory.asKey("Command1"));
        this.id = id;
    }

    protected String run() throws Exception {
        return Thread.currentThread().getName() + Test2.variableDefault.get();
    }
}
