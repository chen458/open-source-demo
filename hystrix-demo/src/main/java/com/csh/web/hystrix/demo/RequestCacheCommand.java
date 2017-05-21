package com.csh.web.hystrix.demo;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;
import com.sun.tools.javac.util.Assert;


/**
 * @author chenshenghong
 * @version 1.0
 * @created 2017/1/19
 * @time 上午11:12
 */
public class RequestCacheCommand extends HystrixCommand<String> {

    private final int id;
    public RequestCacheCommand( int id) {
        super(HystrixCommandGroupKey.Factory.asKey("RequestCacheCommand"));
        this.id = id;
    }
    @Override
    protected String run() throws Exception {
        System.out.println(Thread.currentThread().getName() + " execute id=" + id);
        return "executed=" + id;
    }

    @Override
    protected String getCacheKey() {
        return String.valueOf(id);
    }

    public static void main(String[] args) {
        HystrixRequestContext context = HystrixRequestContext.initializeContext();

        try {
            RequestCacheCommand command1 = new RequestCacheCommand(2);
            RequestCacheCommand command2 = new RequestCacheCommand(2);
            RequestCacheCommand command3 = new RequestCacheCommand(2);

            System.out.println("1 " + command1.execute());

            System.out.println("2 " + command1.isResponseFromCache);
            System.out.println("3 " + command2.execute());
            System.out.println("4 " + command2.isResponseFromCache);
            System.out.println("5 " + command3.execute());
            System.out.println("6 " + command3.isResponseFromCache);
        } finally {
            context.shutdown();
        }

        context = HystrixRequestContext.initializeContext();

        try {
            RequestCacheCommand command3 = new RequestCacheCommand(2);
            System.out.println("5 " + command3.execute());
            System.out.println("6 " + command3.isResponseFromCache);
        } finally {
            context.shutdown();
        }

    }
}
