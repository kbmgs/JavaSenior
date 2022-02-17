package com.atguigu.java1;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @title: LockTest
 * @projectName JavaSenior
 * @description: 解决线程安全问题的方式三：lock锁 --- jdk5.0新增
 *              （同步方法与同步代码块之外的第三个方法）
 *   1.面试题1：synchronized与lock的异同？
 *   同：二者都可以解决线程的安全问题
 *   不同：synchronized机制在执行完相应的同步代码后，会自动的释放同步监视器  ---隐式锁
 *        lock需要手动的启动同步，结束同步(lock(),unlock()) ---显式锁
 *        lock锁性能更好，jvm将花费较少的时间来调度线程。且具有更好的扩展性（提供更多的子类）
 *
 *     面试题2：如何解决线程安全问题
 *     。。。
 *
 *    2.优先使用顺序：
 *    lock -> 同步代码块（已经进入了方法体，分配了相应资源） -> 同步方法（在方法体之外）
 *
 * @author kbmgs
 * @date 2022/2/17 18:55
 */

class Window implements Runnable {

    private int ticket = 100;

    // 1.实例化，造一个ReentrantLock对象
    private ReentrantLock lock = new ReentrantLock(true); // fair:公平锁，有先进先出的顺序

    @Override
    public void run() {
        while (true) {
            try {
                // 2.调用lock()方法，保证lock()之后的操作是单线程的
                lock.lock();

                if (ticket > 0) {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + ":售票，票号为：" + ticket);
                    ticket--;
                } else {
                    break;
                }
            } finally {
                // 3.finally中调用解锁的方法unlock()
                lock.unlock();
            }
        }
    }
}

public class LockTest {
    public static void main(String[] args) {
        Window w = new Window();
        Thread t1 = new Thread(w);
        Thread t2 = new Thread(w);
        Thread t3 = new Thread(w);

        t1.setName("窗口1");
        t2.setName("窗口2");
        t3.setName("窗口3");

        t1.start();
        t2.start();
        t3.start();

    }
}
