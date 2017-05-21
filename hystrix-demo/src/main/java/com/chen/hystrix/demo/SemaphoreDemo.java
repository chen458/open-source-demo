package com.chen.hystrix.demo;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * 信号量 demo
 *
 * @author chenshenghong
 * @version 1.0
 * @created 2017/5/21
 * @time 下午9:24
 */
public class SemaphoreDemo {
    public static void main(String[] args) {
        final Semaphore semaphore = new Semaphore(5);//定义信号量
        ExecutorService executor = Executors.newCachedThreadPool();
        for (int i = 0; i < 20; i ++) {
            final int n = i;
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    try {
                        semaphore.acquire();//获取许可
                        System.out.println("Accessing: " + n);
                        TimeUnit.MILLISECONDS.sleep((long) (Math.random() * 10000));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        semaphore.release();//释放许可
                        System.out.println("-----------------"+semaphore.availablePermits());
                    }
                }
            };
            executor.execute(runnable);
        }

        executor.shutdown();
    }
}
