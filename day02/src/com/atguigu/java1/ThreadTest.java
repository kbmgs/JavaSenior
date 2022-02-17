package com.atguigu.java1;

/**
 * @title: ThreadTest
 * @projectName JavaSenior
 * @description: 演示线程的死锁问题
 *  1.死锁的理解：不同的线程分别占用对方需要的同步资源不放弃，
 *              都在等待对方放弃自己需要的同步资源，就形成了线程的死锁
 *
 *  2.说明：
 *      1）出现死锁时，不会出现提示，只是所有的线程都处于阻塞状态，无法继续
 *      2）我们使用同步时，要避免出现死锁
 *
 * @author kbmgs
 * @date 2022/2/17 17:38
 */
public class ThreadTest {

    public static void main(String[] args) {

        StringBuffer s1 = new StringBuffer();
        StringBuffer s2 = new StringBuffer();

        // Thread的匿名子类的匿名对象
        new Thread() {
            @Override
            public void run() {
                synchronized (s1) {
                    s1.append("a"); // 字符串后连接"a"
                    s2.append("1");

                    // 给线程添加sleep
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(s1);
                    System.out.println(s2);
                    synchronized (s2) {
                        s1.append("b");
                        s2.append("2");

                        System.out.println(s1);
                        System.out.println(s2);
                    }
                }
            }
        }.start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (s2) {
                    s1.append("c"); // 字符串后连接了一个"a"
                    s2.append("3");

                    // 给线程添加sleep
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(s1);
                    System.out.println(s2);
                    synchronized (s1) {
                        s1.append("d");
                        s2.append("4");

                        System.out.println(s1);
                        System.out.println(s2);
                    }
                }
            }
        }).start();

    }


}
