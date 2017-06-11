package com.chen.hystrix.demo;

import com.netflix.hystrix.*;
import rx.Observable;

/**
 * @author chenshenghong
 * @version 1.0
 * @created 2017/6/11
 * @time 下午3:40
 */
public class HelloWorldObservableCommand extends HystrixObservableCommand<String> {
    protected HelloWorldObservableCommand() {
        super(HystrixObservableCommand.Setter
                //最少配置,指定命令组名(CommandGroup)。用于对依赖操作分组,便于统计,汇总等.
                //在不指定ThreadPoolKey的情况下，字面值用于对不同依赖的线程池/信号区分.
                .withGroupKey(HystrixCommandGroupKey.Factory.asKey("ExampleGroup"))

                // 每个CommandKey代表一个依赖抽象,相同的依赖要使用相同的CommandKey名称。
                // 依赖隔离的根本就是对相同CommandKey的依赖做隔离.
                .andCommandKey(HystrixCommandKey.Factory.asKey("HelloWorldCommand"))

                //公共配置
                .andCommandPropertiesDefaults(HystrixCommandProperties.Setter()
                        // 执行超时时间
                        .withExecutionTimeoutInMilliseconds(150)
                )

        );
    }

    @Override
    protected Observable<String> construct() {
        return null;
    }

}
