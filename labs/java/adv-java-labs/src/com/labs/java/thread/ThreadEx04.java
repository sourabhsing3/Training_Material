package com.labs.java.thread;

import java.util.concurrent.*;

/**
 * Creates Thread by extending Thread class using Runnable
 */
public class ThreadEx04 {
    public static void main(String[] args) throws InterruptedException ,ExecutionException{
        System.out.println(Thread.currentThread().getName() + " >>> entering into main thread");

//        ExecutorService es = Executors.newFixedThreadPool(2);
        ExecutorService es = Executors.newCachedThreadPool();
        Worker w1 = new Worker();
        Future f1 = es.submit(w1);
        System.out.println("Sum f1.get "+f1.get());


        Worker w2 = new Worker();
        Future f2 =es.submit(w2);

        System.out.println("Sum f2.get "+f2.get());
        Worker w3 = new Worker();
        es.submit(w3);
//        Worker w4 = new Worker();



        Thread.sleep(100);
        System.out.println(Thread.currentThread().getName() + " <<< exiting from main thread");
    }

    public static class Worker implements Callable<Integer> {
        int sum = 0;

        // main => 0 + 0 = 0
        // main => 0 + 1 = 1
        // main => 1 + 2 = 3
        // main => 3 + 3 = 6

        // T1 => 0 + 0 = 0
        // T1 => 0 + 1 = 1
        // T1 => 1 + 2 = 3
        // T1 => 3 + 3 = 6

        public Integer call() {
            System.out.println(Thread.currentThread().getName() + " >>> entering into worker thread");
            int s =print(); // called from worker thread
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " <<< exiting from worker thread");
            return s;
        }

        public synchronized Integer print() {
            sum = 0;
            for (int i = 0; i < 10; i++) {
                System.out.println(Thread.currentThread().getName() + " - " + i);
//                try {
//                    Thread.sleep(1000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
                sum += i;
            }
            System.out.println(Thread.currentThread().getName() + " - Sum is " + sum);
            return sum;
        }

        public static class Master extends Thread {
            public void run() {
                System.out.println(Thread.currentThread().getName() + " >>> entering into master thread");
                print(); // called from worker thread
                System.out.println(Thread.currentThread().getName() + " <<< exiting from master thread");
            }

            public void print() {
                for (int i = 0; i < 10; i++) {
                    System.out.println(Thread.currentThread().getName() + " - " + i);
                }

            }
        }
    }
}
