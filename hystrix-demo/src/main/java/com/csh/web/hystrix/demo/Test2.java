package com.csh.web.hystrix.demo;

import com.netflix.hystrix.strategy.concurrency.HystrixRequestVariableDefault;

/**
 * @author chenshenghong
 * @version 1.0
 * @created 2017/1/19
 * @time 下午5:56
 */
public class Test2 {
    public static HystrixRequestVariableDefault<String> variableDefault = new HystrixRequestVariableDefault<String>();
}
