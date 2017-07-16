package com.fuyzh.netty.test.concurrent;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


/**
 * Created by zhaoss on 2017/7/14.
 */
/*
* java条件变量学习（条件变量与锁是一一对应的）
* */
public class TestCondition {
    Lock lock = new ReentrantLock();
    Condition condition = lock.newCondition();
    private boolean flag = false;

    public static void main(String[] args) throws InterruptedException {
        TestCondition tc = new TestCondition();
        Consumer cs = tc.new Consumer();
        Producer pd = tc.new Producer();
        cs.start();
        Thread.sleep(500);
        pd.start();

    }


    /*
    * 消费者消费数据，如果没有，则一直等待，知道生产者生产出数据
    * */
    class Consumer extends Thread {

        @Override
        public void run() {
            try {
                lock.lock();
                System.out.println("我是" + Thread.currentThread().getName() + ",我要吃肉");
                if (flag) {
                    System.out.println("正好有肉，吃之。。。");
                } else {
                    System.out.println("没肉，等等(╯3╰)");

                    condition.await();
                    System.out.println("有肉了，吃之。。。");
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }

    /*
    * 生产者，生产数据，通知消费者继续消费
    * */
    class Producer extends Thread {
        @Override
        public void run() {
            try {
                lock.lock();
                System.out.println("我是" + Thread.currentThread().getName() + ",我是屠夫，生产肉的");
                //生产肉，耗时1s钟
                sleep(1000);
                flag = true;
                condition.signal();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }
}
