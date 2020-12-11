package com.company;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;

public class Main {

    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(3, true);
        CountDownLatch countDownLatch = new CountDownLatch(10);
        CountDownLatch countDownLatch1 = new CountDownLatch(1);

        Uploader uploader = new Uploader(countDownLatch1);
        uploader.start();
        try {
            uploader.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for (int i = 1; i <= 10; i++) {
            new Downloader("Downloader " + i, countDownLatch, semaphore).start();
        }

        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Файл был удален с облака ");
    }
}
