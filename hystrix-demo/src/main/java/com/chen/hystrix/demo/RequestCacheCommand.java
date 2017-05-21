package com.chen.hystrix.demo;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;

/**
 * 请求缓存 Request-Cache demo
 * 请求缓存可以让(CommandKey/CommandGroup)相同的情况下,直接共享结果，降低依赖调用次数，
 * 在高并发和CacheKey碰撞率高场景下可以提升性能.
 * Servlet容器中，可以直接实用Filter机制Hystrix请求上下文
 *
 * 要实现 Request-Cache, 需:
 * 1、重写 getCacheKey 方法,实现区分不同请求的逻辑
 *
 * @author chenshenghong
 * @version 1.0
 * @created 2017/5/21
 * @time 下午5:43
 */
public class RequestCacheCommand extends HystrixCommand<String>{

    private final int id;

    public RequestCacheCommand(int id) {
        super(Setter
                .withGroupKey(HystrixCommandGroupKey.Factory.asKey("ExampleGroup"))
        );
        this.id = id;
    }

    @Override
    protected String run() throws Exception {
        System.out.println(Thread.currentThread().getName() + " execute id=" + id);
        return "executed=" + id;
    }

    /**
     * 重写getCacheKey方法,实现区分不同请求的逻辑
     * @return
     */
    @Override
    protected String getCacheKey() {
        return "RequestCacheCommand_" + id;
    }

    public static void main(String[] args){
        HystrixRequestContext context = HystrixRequestContext.initializeContext();
        try {
            RequestCacheCommand command2a = new RequestCacheCommand(2);
            RequestCacheCommand command2b = new RequestCacheCommand(2);
            System.out.println(command2a.execute());
            //isResponseFromCache判定是否是在缓存中获取结果
            System.out.println(command2a.isResponseFromCache());
            System.out.println(command2b.execute());
            System.out.println(command2b.isResponseFromCache());
        } finally {
            context.shutdown();
        }
        context = HystrixRequestContext.initializeContext();
        try {
            RequestCacheCommand command3b = new RequestCacheCommand(2);
            System.out.println(command3b.execute());
            System.out.println(command3b.isResponseFromCache());
        } finally {
            context.shutdown();
        }
    }
}
