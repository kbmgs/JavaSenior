package com.atguigu.java;

/**
 * @title: WindowTest
 * @projectName JavaSenior
 * @description: 使用同步代码块解决继承Thread类的线程安全问题
 *               例子，创建三个窗口卖票。总票数为100张。使用继承Thread类的方式。
 *
 *
 *
 * @author kbmgs
 * @date 2022/2/2 17:31
 */

class Window2 extends Thread {
    private static int ticket = 100;
    private static Object obj = new Object(); //需加static静态，使之唯一

    @Override
    public void run() {
        while (true) {
            //正确的
            synchronized (obj) {
                //错误的方式：此时this代表t1,t2,t3三个对象
//            synchronized (this) {
                if (ticket > 0) {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(getName() + ":卖票，票号为：" + ticket);
                    ticket--;
                } else {
                    break;
                }
            }
        }
    }
}

public class WindowTest2 {
    public static void main(String[] args) {
        Window2 t1 = new Window2();
        Window2 t2 = new Window2();
        Window2 t3 = new Window2();

        t1.setName("窗口一");
        t2.setName("窗口二");
        t3.setName("窗口三");

        t1.start();
        t2.start();
        t3.start();
    }
}

