package com.chen.rxjava.demo;


import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.functions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * RxJava simple demo
 * @author chenshenghong
 * @version 1.0
 * @created 2017/5/26
 * @time 下午10:36
 */
public class RxJavaMethod {
    public static void main(String[] args) {
        System.out.println("======= create demo ======");
        create();

        System.out.println("======= from demo ======");
        from("小明", "小红");

        System.out.println("======= just demo ======");
        just();

        System.out.println("======= defer demo ======");
        defer();

        System.out.println("======= map demo ======");
        map();

        System.out.println("======= flatMap demo ======");
        flatMap();

        System.out.println("======= scan demo ======");
        scan();

        System.out.println("======= groupByFilter demo ======");
        groupByFilter();

        System.out.println("======= groupByTake demo ======");
        groupByTake();

        System.out.println("======= groupByDistinct demo ======");
        groupByDistinct();

        System.out.println("======= sample demo ======");
        sample();

        System.out.println("======= merge demo ======");
        merge();

        System.out.println("======= merge demo ======");
        zip();
    }


    /**
     * create demo
     * 1、Observable.create(OnSubscribe) 返回一个 Observable 实例。OnSubscribe 继承 Action1<Subscriber<T>>
     * 2、observable.subscribe(observer) 或 observable.subscribe(subscriber)。核心逻辑如下:
     *
     *  public Subscription subscribe(Subscriber subscriber) {
     *      subscriber.onStart();
     *      onSubscribe.call(subscriber);
     *      return subscriber;
     *  }
     *
     *  Subscriber<T> implements Observer<T>
     */
    private static void create() {
        // subscribe(Observer)
        Observable.create(new Observable.OnSubscribe<String>(){
            @Override
            public void call(Subscriber<? super String> subscriber) {
                subscriber.onNext("test01");
                subscriber.onNext("test02");
                subscriber.onCompleted();
            }
        }).subscribe(new Observer<String>() {
            @Override
            public void onCompleted() {
                System.out.println("Observer onCompleted !");
            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
            }

            @Override
            public void onNext(String s) {
                System.out.println("Observer onNext:" + s);
            }
        });

        // subscribe(Subscriber)
        Observable.create(new Observable.OnSubscribe<String>(){
            @Override
            public void call(Subscriber<? super String> subscriber) {
                subscriber.onNext("test01");
                subscriber.onNext("test02");
                subscriber.onCompleted();
            }
        }).subscribe(new Subscriber<String>() {
            /**
             * Subscriber 较 Observer 新增的方法
             * 在 subscribe 刚开始，而事件还未发送之前被调用，可以用于做一些准备工作
             */
            @Override
            public void onStart() {
                System.out.println("清空数据");
            }

            @Override
            public void onCompleted() {
                System.out.println("Subscriber onCompleted !");
            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
            }

            @Override
            public void onNext(String s) {
                System.out.println("Subscriber onNext:" + s);
            }
        });

        //subscribe 不完整定义回调
        Observable<String> observable = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                subscriber.onNext("test01");
                subscriber.onNext("test02");
                subscriber.onCompleted();
            }
        });

        //Subscriber.onNext(String s)
        Action1<String> onNextAction = new Action1<String>() {
            @Override
            public void call(String s) {
                System.out.println("不完整 onNext:" + s);
            }
        };

        //Subscriber.onError(Throwable e)
        Action1<Throwable> onErrorAction = new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                throwable.printStackTrace();
            }
        };
        //Subscriber.onCompleted()
        Action0 onCompletedAction = new Action0() {
            @Override
            public void call() {
                System.out.println("不完整 onCompleted !");
            }
        };

        // 自动创建 Subscriber ，并使用 onNextAction 来定义 onNext()
        observable.subscribe(onNextAction);
        // 自动创建 Subscriber ，并使用 onNextAction 和 onErrorAction 来定义 onNext() 和 onError()
        observable.subscribe(onNextAction, onErrorAction);
        //// 自动创建 Subscriber ，并使用 onNextAction、 onErrorAction 和 onCompletedAction 来定义 onNext()、 onError() 和 onCompleted()
        observable.subscribe(onNextAction,onErrorAction, onCompletedAction);



    }

    /**
     * from 方法接受数据,并按顺序返回这些数据
     * Observable.from() 内部创建 OnSubscribeFromArray 对象
     * @param names
     */
    private static void from(String ... names) {
        Observable.from(names).subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                System.out.println("from hello " + s );
            }
        });
    }

    /**
     * just函数，它接受一至九个参数，返回一个按参数列表顺序发射这些数据的Observable
     */
    private static void just(){
        Observable.just("Hello", "RxJava").subscribe(new Observer<String>() {
            @Override
            public void onCompleted() {
                System.out.println("just onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
            }

            @Override
            public void onNext(String s) {
                System.out.println("just onNext : " + s);
            }
        });
    }

    /**
     * Defer操作符会一直等待直到有观察者订阅它，
     */
    private static void defer(){
        final String s1 = "test1";
        Observable<String> defer = Observable.defer(new Func0<Observable<String>>() {
            @Override
            public Observable<String> call() {
                return Observable.just(s1);
            }
        });

        defer.subscribe(new Observer<String>() {
            @Override
            public void onCompleted() {
                System.out.println("defer onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
            }

            @Override
            public void onNext(String s) {
                System.out.println("defer defer");
            }
        });
    }

    /**
     * 操作符对原始Observable发射的每一项数据应用一个你选择的函数，然后返回一个发射这些结果。
     */
    private static void map(){
        Observable
                .just("test01","test02")
                .map(new Func1<String, String>() {
                    @Override
                    public String call(String s) {
                        return s.toUpperCase();
                    }
                })
                .subscribe(new Observer<String>() {
                    @Override
                    public void onCompleted() {
                        System.out.println("just onCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(String s) {
                        System.out.println("just onNext : " + s);
                    }
                });
    }

    /**
     * flatMap()接收一个Observable的输出作为输入，然后作为一个新的Observable再发射。
     * 理解flatMap的关键点在于，flatMap输出的新的Observable正是我们在Subscriber想要接收的，比如这里输出单个的字符串。
     */
    private static void flatMap(){
        List<String> list = new ArrayList<>();
        list.add("flatMap test01");
        list.add("flatMap test02");
        Observable
                .from(list)
                .flatMap(new Func1<String, Observable<String>>() {
                    @Override
                    public Observable<String> call(String s) {
                        return Observable.just(s.toLowerCase());
                    }
                })
                .subscribe(new Observer<String>() {
                    @Override
                    public void onCompleted() {
                        System.out.println("just onCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(String s) {
                        System.out.println("just onNext : " + s);
                    }
                });
    }

    /**
     * 一个累加器函数，操作符对原始Observable发射的第一项数据应用一个函数，然后将那个函数的结果作为自己的第一项数据发射。
     * 一个累加器函数，操作符对原始Observable发射的第一项数据应用一个函数，然后将那个函数的结果作为自己的第一项数据发射。
     * 第一次发射得到1，作为结果与2相加；发射得到3，作为结果与3相加
     */
    private static void scan(){
        Observable
                .just(1, 2, 3, 4)
                .scan(new Func2<Integer, Integer, Integer>() {
                    @Override
                    public Integer call(Integer integer, Integer integer2) {
                        return integer + integer2;
                    }
                }).subscribe(new Observer<Integer>() {
                    @Override
                    public void onCompleted() {
                        System.out.println("scan onCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(Integer integer) {
                        System.out.println("scan onNext :" + integer);
                    }
                });
    }

    /**
     * 按照指定的规则来分组元素
     * demo : 过滤小于等于 3 的
     */
    private static void groupByFilter(){
        Observable
                .just(9, 4, 8, 6, 3, 2, 1, 1)
                .filter(new Func1<Integer, Boolean>() {
                    @Override
                    public Boolean call(Integer integer) {
                        return integer > 3;
                    }
                })
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onCompleted() {
                        System.out.println("groupByFilter onCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(Integer integer) {
                        System.out.println("groupByFilter onNext :" + integer);
                    }
                });
    }

    /**
     * take(N) -- 只发射前 N 个元素
     * takeLast(N) 只发射最后 N 个元素
     * first() -- 只发射第一个元素
     * last() -- 只发射最后一个元素
     * skip(N) -- 跳过前 N 个元素发射
     * skipLast(N) -- 跳过最后 N 个元素
     * ElementAt(N) -- 仅发射第 N 个元素,从 0 开始
     *
     *
     * demo : 发射 5
     */
    private static void groupByTake(){
        Observable
                .just(1,2,3,4,5,6,7,8,9)
                .take(7)
                .takeLast(3)
                .first()
                .last()
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onCompleted() {
                        System.out.println("groupByFilter onCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(Integer integer) {
                        System.out.println("groupByTake onNext :" + integer);
                    }
                });

    }

    /**
     * distinct 仅处理一次，可以处理去除重复的数据
     * 输出 1,3,4,5,2
     */
    private static void groupByDistinct(){
        Observable
                .just(1,3,4,3,1,3,4,5,2)
                .distinct()
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onCompleted() {
                        System.out.println("groupByDistinct onCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(Integer integer) {
                        System.out.println("groupByDistinct onNext :" + integer);
                    }
                });
    }

    /**
     * sample() 定期发射Observable最近发射的数据项
     *
     */
    private static void sample(){
        Observable
                .just(1,2,3,4,5)
                .sample(1, TimeUnit.MILLISECONDS)
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onCompleted() {
                        System.out.println("sample onCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(Integer integer) {
                        System.out.println("sample onNext :" + integer);
                    }
                });
    }

    /**
     * 合并多个Observables的发射物，多输入，单输出
     */
    private static void merge(){
        Observable<Integer> observable1 = Observable.just(1, 3, 5);
        Observable<Integer> observable2 = Observable.just(2, 4, 6);
        Observable
                .merge(observable1, observable2)
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onCompleted() {
                        System.out.println("merge onCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(Integer integer) {
                        System.out.println("merge onNext :" + integer);
                    }
                });
    }

    /**
     * 合并两个或者多个Observables发射出的数据项，根据指定的函数Func2变换它们，并发射一个新值。
     * 如:
     * observable1 依次发射: 1,3,5
     * observable2 依次发射: 2,4,6,9
     * 合并后的结果 3,7,11
     */
    private static void zip(){
        Observable<Integer> observable1 = Observable.just(1, 3, 5);
        Observable<Integer> observable2 = Observable.just(2, 4, 6, 9);
        Observable
                .zip(observable1, observable2, new Func2<Integer, Integer, Integer>() {
                    @Override
                    public Integer call(Integer integer, Integer integer2) {
                        return integer + integer2;
                    }
                })
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onCompleted() {
                        System.out.println("zip onCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(Integer integer) {
                        System.out.println("zip onNext :" + integer);
                    }
                });
    }

    /**
     * lift 形式代理模式
     */
    private static void lift(){
        Observable
                .just(1,2,3,4,5)
                .lift(new Observable.Operator<Integer, Integer>() {
                    @Override
                    public Subscriber<? super Integer> call(final Subscriber<? super Integer> subscriber) {
                        return new Subscriber<Integer>() {
                            @Override
                            public void onCompleted() {
                                subscriber.onCompleted();
                            }

                            @Override
                            public void onError(Throwable e) {
                                e.printStackTrace();
                            }

                            @Override
                            public void onNext(Integer integer) {
                                System.out.println("lift " + integer);
                            }
                        };
                    }
                });
    }



}


