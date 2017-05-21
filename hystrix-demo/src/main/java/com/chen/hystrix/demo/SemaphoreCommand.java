package com.chen.hystrix.demo;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandProperties;

/**
 * 使用信号量线程隔离
 * @author chenshenghong
 * @version 1.0
 * @created 2017/5/21
 * @time 下午10:44
 */
public class SemaphoreCommand extends HystrixCommand<String> {
    private final String name;

    public SemaphoreCommand(String name) {
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("SemaphoreGroup"))
                .andCommandPropertiesDefaults(HystrixCommandProperties.Setter()
                        //配置信号量隔离方式,默认采用线程池隔离
                        .withExecutionIsolationStrategy(
                                HystrixCommandProperties.ExecutionIsolationStrategy.SEMAPHORE)));
        this.name = name;
    }

    @Override
    protected String run() throws Exception {
        return "HystrixThread:" + Thread.currentThread().getName();
    }
    public static void main(String[] args) throws Exception{
        SemaphoreCommand command = new SemaphoreCommand("semaphore");
        String result = command.execute();
        System.out.println(result);
        System.out.println("MainThread:" + Thread.currentThread().getName());
    }
}
