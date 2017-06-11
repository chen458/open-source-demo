package com.chen.hystrix.demo;

import com.netflix.hystrix.*;
import rx.Observable;
import rx.Observer;
import rx.functions.Action1;

import java.io.Serializable;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * 简单调用 demo
 * 每个 command 只能调用一次。第二次调用需要重新 new
 * 否则报错 lang.IllegalStateException:This instance can only be executed once. Please instantiate a new instance.
 *
 * @author chenshenghong
 * @version 1.0
 * @created 2017/5/21
 * @time 下午3:57
 */
public class HelloWorldCommand extends HystrixCommand<Result> {

    private final String name;

    public HelloWorldCommand(String name) {
        super(Setter
                //最少配置,指定命令组名(CommandGroup)。用于对依赖操作分组,便于统计,汇总等.
                //在不指定ThreadPoolKey的情况下，字面值用于对不同依赖的线程池/信号区分.
                .withGroupKey(HystrixCommandGroupKey.Factory.asKey("ExampleGroup"))

                // 每个CommandKey代表一个依赖抽象,相同的依赖要使用相同的CommandKey名称。
                // 依赖隔离的根本就是对相同CommandKey的依赖做隔离.
                .andCommandKey(HystrixCommandKey.Factory.asKey("HelloWorldCommand"))

                //同一依赖的不同远程调用如,可以使用HystrixThreadPoolKey做隔离区分
                //在业务上都是相同的组，但是需要在资源上做隔离时(一个是redis 一个是http)，可以使用HystrixThreadPoolKey区分
                .andThreadPoolKey(HystrixThreadPoolKey.Factory.asKey("HelloWorldPool"))

                //公共配置
                .andCommandPropertiesDefaults(HystrixCommandProperties.Setter()
                        // 执行超时时间
                        .withExecutionTimeoutInMilliseconds(150)
                )

                //线程池配置
                .andThreadPoolPropertiesDefaults(HystrixThreadPoolProperties.Setter()
                        //线程池大小
                        .withCoreSize(10)
                )
        );
        this.name = name;
    }

    protected Result run() throws Exception {
        TimeUnit.MILLISECONDS.sleep(100);
        return new Result("Hello " + name +" thread:" + Thread.currentThread().getName());
    }

    /**
     * 异常、超时、线程拒绝、熔断 后执行方法
     * HystrixBadRequestException 用在非法参数或非系统故障异常等不应触发回退逻辑的场景。
     * @return
     */
    @Override
    protected Result getFallback() {
        System.out.println("HelloWorldCommand Fallback");
        return new Result(" Fallback");
    }


    /**
     * 同步调用
     * 主线程阻塞、等待任务线程执行直接返回数据
     */
    private static void executeMethod(){
        System.out.println("======== execute start =========");
        HelloWorldCommand syn_command = new HelloWorldCommand("Synchronous-Hystrix");
        Result syn_result = syn_command.execute();
        System.out.println("syn_result=" + syn_result.getMessage());
        System.out.println("======== execute end =========");
    }

    /**
     * 异步调用
     * 主线程不阻塞,任务线程直接返回 Future 对象
     */
    private static void queueMethod(){
        System.out.println("======== queue start =========");
        HelloWorldCommand asyn_command = new HelloWorldCommand("Asynchronous-Hystrix");
        Future<Result> asyn_future = asyn_command.queue();
        Result asyn_result = null;
        try {
            //Future get timeout 时间尽量 大于或等于 command.run 的执行时间
            asyn_result = asyn_future.get(110, TimeUnit.MILLISECONDS);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("asyn_result=" + asyn_result.getMessage());
        System.out.println("======== queue end =========");
    }

    /**
     * 注册异步事件回调执行
     */
    private static void observeMethod(){
        System.out.println("======== observe start =========");
        HelloWorldCommand command = new HelloWorldCommand("Observe-Hystrix");
        Observable<Result> observe = command.observe();

        //注册结果回调事件
        observe.subscribe(new Action1<Result>() {
            @Override
            public void call(Result result) {
                result.setMessage(result.getMessage() + " observe.subscribe.aciton1");
            }
        });

        //注册完整执行生命周期事件
        observe.subscribe(new Observer<Result>() {
            /**
             * onNext/onError完成之后最后回调
             */
            @Override
            public void onCompleted() {
                System.out.println("execute onCompleted");
            }

            /**
             *  当产生异常时回调
             * @param e
             */
            @Override
            public void onError(Throwable e) {
                System.out.println("onError : " + e.getMessage() );
                e.printStackTrace();
            }

            /**
             * 获取结果后回调
             * @param result
             */
            @Override
            public void onNext(Result result) {
                result.setMessage(result.getMessage() + " Observer.onNext");
            }
        });

        //获取 Future 对象
        Future<Result> future = observe.toBlocking().toFuture();
        Result observe_result = null;
        try {
            //Future get timeout 时间尽量 大于或等于 command.run 的执行时间
            observe_result = future.get(110, TimeUnit.MILLISECONDS);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("observe_result=" + observe_result.getMessage());

        System.out.println("======== observe end =========");
    }

    public static void main(String[] args) {

        System.out.println("mainThread=" + Thread.currentThread().getName());
        //同步
        executeMethod();
        //异步
//        queueMethod();
        // 异步注册
//        observeMethod();

    }

}

class Result implements Serializable{
    private String message;

    public Result(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "Result{" +
                "message='" + message + '\'' +
                '}';
    }
}
