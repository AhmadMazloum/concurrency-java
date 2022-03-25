package com.example.concurrency;

import java.util.concurrent.CountDownLatch;

public class Concurrency{
    private static int balance = 0;
    static int TRANSACTIONS_PER_MACHINE  = 100000000;
    private static void deposit() {
        balance = balance + 1;
    }
    private static void withdraw() {
        balance = balance - 1;
    }
    public void machine() {
        synchronized(this) {
            for (int i = 0; i < TRANSACTIONS_PER_MACHINE; ++i) {
                deposit(); // put a dollar in
                withdraw(); // take it back out
            }
        }
    }
    public static void main(String[] args) throws InterruptedException {
        Concurrency c = new Concurrency();
        Thread firstThread = new Thread(() -> {
           c.machine();
        });
        Thread secondThread = new Thread(() -> {
            c.machine();
        });
        Thread thirdThread = new Thread(() -> {
            c.machine();
        });

        firstThread.start();
        secondThread.start();
        thirdThread.start();
        firstThread.join();
        secondThread.join();
        thirdThread.join();

        System.out.println(balance);
    }
}
