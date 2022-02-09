package com.atguigu.java;

/**
 * @title: ThreadMethodTest
 * @projectName JavaSenior
 * @description: 测试Thread类中的常用方法
 *      1.start():启动当前线程；调用当前线程的run()方法
 *      2.run():同厂需要重写Thread中的此方法，将创建线程的执行操作声明在方法中
 *      3.currentThread():静态方法，返回执行当前代码的线程
 *      4.getName():获取当前线程的名字
 *      5.setName():设置当前线程的名字
 *      6.yield():释放当前cpu的执行权
 *      7.join():在线程A中调用线程B的join()方法，此时线程A进入阻塞状态，直到线程B完全执行完以后，线程A才结束阻塞状态
 *      8.stop():已过时。执行此方法是强制结束当前线程
 *      9.sleep(long millitime):让当前线程”睡眠“指定的millitime毫秒。在指定的millitime毫秒内，当前线程是阻塞状态
 *      10.isAlive():判断当前线程是否存活
 *
 *      线程的优先级：
 *      1.MAX_PRIORITY：10
 *        MIN_PRIORITY：1
 *        NORM_PRIORITY：5
 *      2.如何获取和设置当前线程的优先级：
 *          getPriority():获取线程的优先级
 *          setPriority(int p):设置线程的优先级
 *
 *          说明：高优先级线程要抢占低优先级线程cpu的执行权，但是只是从概率上来讲，高优先级线程较高概率被执行。并不意味只有当高优先级线程执行完以后低优先级才执行。
 *
 *
 *
 * @author kbmgs
 * @date 2022/1/31 21:52
 */

class HelloThread extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            if (i % 2 == 0) {
                //线程执行至此时，阻塞参数毫秒
//                try {
//                    sleep(10);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }

                System.out.println(getName() + ":" + getPriority() + ":" + i);
            }
            if (i % 20 == 0) {
                this.yield();//释放当前CPU的执行权
            }
        }
    }

    //重写一个子类的带参构造器
    public HelloThread(String name) {
        super(name);
    }
}

public class ThreadMethodTest {
    public static void main(String[] args) {
        HelloThread h1 = new HelloThread("Thread--1");
//        h1.setName("线程一");

        //设置分线程的优先级
        h1.setPriority(Thread.MAX_PRIORITY);
        h1.start();

        //给主线程命名
        Thread.currentThread().setName("主线程");
        //设置主线程的优先级
        Thread.currentThread().setPriority(Thread.MIN_PRIORITY);
        for (int i = 0; i < 100; i++) {
            if (i % 2 == 0) {
                System.out.println(Thread.currentThread().getName() + ":" + Thread.currentThread().getPriority() + ":" + i);
            }

//            if (i == 20) {
//                try {
//                    h1.join();
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
        }
        System.out.println(h1.isAlive());
    }
}
