package com.chen.rxjava.demo;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * 调度器，解决Android主线程问题
 *
 * @author chenshenghong
 * @version 1.0
 * @created 2017/5/30
 * @time 下午2:59
 *
 * Schedulers 类型:
 *  1、Schedulers.immediate()
 *      允许你立即在当前线程执行你指定的工作。它是timeout()，timeInterval()，以及timestamp()方法默认的调度器。
 *  2、Schedulers.newThread()
 *      总是启用新线程，并在新线程执行操作
 *  3、Schedulers.io()
 *      用于I/O操作（读写文件、读写数据库、网络信息交互等）;
 *      io() 的内部实现是是用一个无数量上限的线程池，可以重用空闲的线程，因此多数情况下 io() 比 newThread() 更有效率。;
 *  4、Schedulers.computation()
 *      计算工作使用调度器。指 CPU 密集型计算,不涉及 I/O 等限制性能的操作;
 *      使用的固定的线程池，大小为 CPU 核数。
 *  5、Schedulers.trampoline()
 *      会处理它的队列并且按序运行队列中每一个任务。它是repeat()和retry()方法默认的调度器。
 *
 * Schedulers 方法:
 *  1、subscribeOn()
 *      指定 subscribe() 所发生的线程，即 Observable.OnSubscribe 被激活时所处的线程。或者叫做事件产生的线程。
 *  2、observeOn()
 *      指定 Subscriber 所运行在的线程。或者叫做事件消费的线程。
 */
public class RxJavaSchedulers {

    private CompositeSubscription compositeSubscription;

    public static void main(String[] args) {
        ioSchedulers();


    }

    private static void ioSchedulers(){
        Observable
                .create(new Observable.OnSubscribe<Integer>() {
                    @Override
                    public void call(Subscriber<? super Integer> subscriber) {
                        subscriber.onNext(getNumber());
                        subscriber.onNext(getNumber());
                        subscriber.onNext(getNumber());
                        subscriber.onCompleted();
                    }
                })
                .subscribeOn(Schedulers.io())//指定 subscribe() 发生在 IO 线程
                .observeOn(Schedulers.immediate())//指定 Subscriber 的回调发生在主线程
                .subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer number) {
                        System.out.println("Schedulers.io() runing! number = " + number);
                    }
                });
        System.out.println("mainThread done");
    }

    /**
     * 模拟取数据
     * @return
     */
    private static int getNumber(){
        int num = new Random().nextInt(1000);
        try {
            TimeUnit.MILLISECONDS.sleep(num * 1L);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("getNumber done。 number = " + num);
        return num;
    }


}
