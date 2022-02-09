package com.atguigu.java;

/**
 * @author kbmgs
 * @title: ThreadTest
 * @projectName JavaSenior
 * @description: 多线程的创建
 *      方式一：继承于Thread类的子类
 *          1.创建一个继承于Thread类的子类
 *          2.重写Thread类的run() --> 将此线程执行的操作声明在run()中
 *          3.创建Thread类的子类的对象
 *          4.通过此对象调用start()
 *
 * 例子：遍历100内所有偶数
 *
 * @date 2022/1/31 20:19
 */

//1.创建一个继承于Thread类的子类
class MyThread extends Thread {
    //2.重写run()方法
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            if (i % 2 == 0) {
                System.out.println(Thread.currentThread().getName() + ":" + i);
            }
        }
    }
}

public class ThreadTest {
    public static void main(String[] args) {
        //3.创建Thread类的子类的对象
        MyThread t1 = new MyThread();
        MyThread t2 = new MyThread();
        //4.通过此对象调用start()：启动当前线程/调用当前线程的run()
        t1.start(); //Thread-0

        //问题1：我们不能通过直接调用run()的方式启动线程
        //t1.run();  //直接执行t1.run()则并不会启动新线程

        //问题2：再启动一个线程，遍历100以内的偶数。不可以还让已经start()的线程去执行，会报异常。
        //t1.start();
        t2.start(); //Thread-1

        //如下操作仍然是在main主线程中执行的
        for (int i = 0; i < 100; i++) { //main
            if (i % 2 == 0) {
                System.out.println(Thread.currentThread().getName() + ":" + i + "*************main()***************");
            }
        }
    }
}
