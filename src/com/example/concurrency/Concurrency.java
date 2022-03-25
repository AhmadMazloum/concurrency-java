package com.example.concurrency;

public class Concurrency extends  Thread{
    private static int balance = 0;
    static int TRANSACTIONS_PER_MACHINE  = 100000000;
    private static void deposit() {
        balance = balance + 1;
    }
    private static void withdraw() {
        balance = balance - 1;
    }

    public void run() {
        synchronized(this) {
            for (int i = 0; i < TRANSACTIONS_PER_MACHINE; ++i) {
                deposit(); // put a dollar in
                withdraw(); // take it back out
            }
        }
    }
    public static void main(String[] args){
        Concurrency c1 = new Concurrency();
        Concurrency c2 = new Concurrency();
        Concurrency c3 = new Concurrency();
        c1.start();
        c2.start();
        c3.start();
        System.out.println(balance);
    }
}
