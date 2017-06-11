package com.chen.rxjava.demo;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

import java.util.concurrent.TimeUnit;

/**
 * RX 操作符之辅助操作
 *
 * @author chenshenghong
 * @version 1.0
 * @created 2017/6/10
 * @time 下午4:11
 */
public class RxJavaAuxiliary {

    public static void main(String[] args) {
        System.out.println("<=========== doOnUnSubscribe ===========>");
        doOnUnSubscribe();

        System.out.println("<=========== doOnCompleted ===========>");
        doOnCompleted();

        System.out.println("<=========== doOnError ===========>");
        doOnError();

        System.out.println("<=========== delay ===========>");
        delay();

    }

    /**
     * doOnUnSubscribe 取消订阅时的监听
     * 订阅取消后,调用 其 Action0 call() 方法
     */
    private static void doOnUnSubscribe(){
        Observable
                .just(1,2,3,4,5)
                .doOnUnsubscribe(new Action0() {
                    @Override
                    public void call() {
                        System.out.println("doOnUnSubscribe : Observable 取消订阅生成!");
                    }
                })
                .subscribe(new Subscriber<Integer>() {
                    @Override
                    public void onCompleted() {
                        System.out.println("doOnUnSubscribe onCompleted !");
                    }

                    @Override
                    public void onError(Throwable e) {
                        System.out.println(e.getMessage());
                    }

                    @Override
                    public void onNext(Integer integer) {
                        System.out.println("doOnUnSubscribe onNext : " + integer);
                    }
                });
    }

    /**
     * Observable 正常终止时监听,在 onCompleted()方法之前执行
     */
    private static void doOnCompleted(){
        Observable
                .just(1,2,3,4,5)
                .doOnCompleted(new Action0() {
                    @Override
                    public void call() {
                        System.out.println("doOnCompleted : Observable 正常终止!");
                    }
                })
                .subscribe(new Subscriber<Integer>() {
                    @Override
                    public void onCompleted() {
                        System.out.println("doOnCompleted onCompleted !");
                    }

                    @Override
                    public void onError(Throwable e) {
                        System.out.println(e.getMessage());
                    }

                    @Override
                    public void onNext(Integer integer) {
                        System.out.println("doOnCompleted onNext : " + integer);
                    }
                });
    }

    /**
     * 出错监听
     * 出错后不执行 onCompleted 方法
     */
    private static void doOnError(){
        Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                for (int i = 0; i < 5; i++) {
                    if (3 == i) {
                        subscriber.onError(new Throwable("test " + i));
                    } else {
                        subscriber.onNext("test " + i);
                    }
                }

                try {
                    TimeUnit.MILLISECONDS.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                subscriber.onCompleted();

            }
        }).doOnCompleted(new Action0() {
            @Override
            public void call() {
                System.out.println("doOnCompleted : Observable 正常终止!");
            }
        }).doOnUnsubscribe(new Action0() {
            @Override
            public void call() {
                System.out.println("doOnUnSubscribe : Observable 取消订阅生成!");
            }
        }).doOnError(new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                System.out.println("doOnError : Observable 异常 " + throwable.getMessage());
            }
        }).doOnTerminate(new Action0() {
            //订阅即将被终止时的监听，无论是正常终止还是异常终止
            @Override
            public void call() {
                System.out.println("doOnTerminate : Observable 订阅终止(正常终止/异常)生成!");
            }
        }).subscribe(new Subscriber<String>() {
            @Override
            public void onCompleted() {
                System.out.println("doOnError onCompleted ");
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("doOnError onError : " + e.getMessage());
            }

            @Override
            public void onNext(String s) {
                System.out.println("doOnError  onNext : " + s);
            }
        });
    }

    /**
     * 延迟一段指定的时间再发射来自Observable的发射物
     */
    private static void delay(){
        Observable
                .just(1,2,3,4,5,6)
                .delay(1000, TimeUnit.MILLISECONDS)
                .observeOn(Schedulers.newThread())
                .subscribeOn(Schedulers.newThread())
                .subscribe(new Subscriber<Integer>() {
                    @Override
                    public void onCompleted() {
                        System.out.println("delay onCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {
                        System.out.println("delay onError : " + e.getMessage());
                    }

                    @Override
                    public void onNext(Integer integer) {
                        System.out.println("delay onNext : " + integer);
                    }
                });
    }





}
