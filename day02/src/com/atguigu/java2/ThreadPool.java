package com.atguigu.java2;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @title: ThreadPool
 * @projectName JavaSenior
 * @description: 创建线程池
 *
 *      好处：1.提高响应速度（减少了创建新线程的时间）
 *           2.降低资源消耗（重复利用线程池中线程，不需要每次都创建）
 *           3.便于线程管理 corePoolSize：核心池的大小 maximumPoolSize：最大线程数 keepAliveTime：线程没有任务时最多保持多长时间后会终止
 *
 *      面试题：创建多线程有几种方式？四种
 *          继承Thread类
 *          实现Runnable接口
 *          实现Callable接口
 *          线程池
 *
 * Executors：工具类、线程池的工厂类，用于创建并返回不同类型的线程池
 *  Executors.newCachedThreadPool()：创建一个可根据需要创建新线程的线程池
 *  Executors.newFixedThreadPool(n); 创建一个可重用固定线程数的线程池
 *  Executors.newSingleThreadExecutor() ：创建一个只有一个线程的线程池
 *  Executors.newScheduledThreadPool(n)：创建一个线程池，它可安排在给定延迟后运行命令或者定期地执行。
 *
 * @author kbmgs
 * @date 2022/2/19 17:45
 */
class NumberThread implements Runnable {

    @Override
    public void run() {
        for (int i = 0; i <= 100; i++) {
            if (i % 2 == 0) {
                System.out.println(Thread.currentThread().getName()+":"+i);
            }
        }
    }
}

class NumberThread1 implements Runnable {

    @Override
    public void run() {
        for (int i = 0; i <= 100; i++) {
            if (i % 2 != 0) {
                System.out.println(Thread.currentThread().getName()+":"+i);
            }
        }
    }
}

public class ThreadPool {
    public static void main(String[] args) {
        // 1.提供指定线程数量的线程池 ExecutorService
        // ExecutorService 是个接口，newFixedThreadPool()为静态方法
        // 返回了一个对象，赋给了一个接口
        ExecutorService service = Executors.newFixedThreadPool(10);
        //service向下强转
        ThreadPoolExecutor service1 = (ThreadPoolExecutor) service;

        service1.setCorePoolSize(15);
//        service1.getKeepAliveTime();

        // 设置线程池的属性(在接口的实现类中设置)
        // getClass()为Object类中的对象，可获取对象的类 --- class java.util.concurrent.ThreadPoolExecutor
        System.out.println(service.getClass());

        // 2.执行指定的现成的操作。需要提供实现Runnable接口或Callable接口的实现类的对象
        service.execute(new NumberThread()); // 适用于Runnable
        service.execute(new NumberThread1()); // 适用于Runnable
//        service.submit(); // 适用于Callable

        // 3.线程池的关闭操作
        service.shutdown();
    }
}
