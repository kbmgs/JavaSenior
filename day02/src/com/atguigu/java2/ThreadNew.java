package com.atguigu.java2;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @title: ThreadNew
 * @projectName JavaSenior
 * @description: 创建线程的方式三：实现Callable接口。  --- jdk5.0新增
 *
 *      如何理解实现Callable的方式创建多线程，比实现Runnable接口创建更强大？
 *          1.call()方法可以有返回值。
 *          2.call()方法可以抛出异常。被外面的操作捕获，获取异常的信息。
 *          3.Callable时支持泛型的。
 *
 * @author kbmgs
 * @date 2022/2/19 16:49
 */

// 1.创建一个实现Callable接口的实现类
class NumThread implements Callable {
    // 新建类实现Callable接口，重写call()方法。（有返回值）
    // 遍历100以内偶数，并且返回所有偶数的和

    // 2.实现call()方法，将此线程需要做的操作，声明在call()方法中
    @Override
    public Object call() throws Exception { //call()方法的返回值，需要在FutureTask类对象的get()方法中获取
        int sum = 0;
        for (int i = 1; i <= 100; i++) {
            if (i % 2 == 0) {
                System.out.println(i);
                sum += i;
            }
        }
        return sum;
    }
}

public class ThreadNew {
    public static void main(String[] args) {
        // 3.创建callable接口实现类的对象
        NumThread numThread = new NumThread();

        // 4.将此callable接口实现类的对象传入FutureTask构造器中，创建FutureTask的对象
        FutureTask futureTask = new FutureTask(numThread);

        // 5.将FutureTask的对象作为参数，传入Thread类的构造器中，启动线程（或创建Thread的对象，再调用start()）
        //FutureTask同时实现了Runnable，Future接口。它可以作为Runnable线程被执行，又可以作为Future得到Callable的返回值
        //public class FutureTask<V> implements RunnableFuture<V>
        //public interface RunnableFuture<V> extends Runnable,Future<V>
        new Thread(futureTask).start(); //启动线程

        try {
            // 6.可获取Callable中call方法的返回值
            // get()方法的返回值，即为FutureTask构造器参数Callable对象重写的call()方法的返回值
            Object sum = futureTask.get(); //.get()得到NumThread定义的sum值
            System.out.println("总和为：" + sum);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }


    }
}
