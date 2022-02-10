package com.atguigu.java;

/**
 * @title: WindowTest1
 * @projectName JavaSenior
 * @description: 例子，创建三个窗口卖票。总票数为100张。使用实现Runnable接口的方式。
 *              1.问题：卖票过程中出现了重票，错票-->出现了线程安全问题
 *              2.问题出现的原因：当某个线程操作车票的过程中，尚未操作完成时，其他线程参与进来，也操作车票。
 *              3.如何解决：当一个线程a在操作ticket的时候，其他线程不能参与进来，直到线程a操作完ticket时，其他线程才可开始操作
 *              这种情况，即使线程a出现了阻塞，也不能被改变
 *              4.在java中，通过同步机制，来解决线程的安全问题
 *
 *         方式一：同步代码块
 *         synchronized(同步监视器){
 *             //需要被同步的代码
 *         }
 *         说明：1.操作共享数据的代码，即为需要被同步的代码
 *              2.共享数据：多个线程共同操作的变量。比如ticket。
 *              3.同步监视器，俗称：锁。任何一个类的对象，都可以充当锁。
 *                  要求：多个线程必须要公用同一把锁。
 *
 *         方式二：同步方法
 *
 *              5.同步的方式，解决了线程的安全问题。---好处
 *                操作同步代码时，只能有一个线程参与，其他线程等待。相当于是一个单线程的过程，效率低。---坏处
 *
 * @author kbmgs
 * @date 2022/2/2 18:14
 */

class Window1 implements Runnable {

    private int ticket = 100;
//    Object obj = new Object();  //同步监视器的对象，只能造一个（多个线程共用同一个锁）

    @Override
    public void run() {
        while (true) {
            synchronized (this) {//此时this为Window1的当前对象 w（唯一）
                if (ticket > 0) {
                    try {
                        Thread.sleep(100);  // 没有锁时，会出现0，-1号
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + "：卖票，票号为：" + ticket);
                    ticket--;
                } else {
                    break;
                }
            }
        }
    }
}

public class WindowTest1 {
    public static void main(String[] args) {
        //继承了Runnable接口的类Window1的实例对象
        Window1 w = new Window1();

        //一个对象放到三个进程中运行，为同一个ticket，不需要加static
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
