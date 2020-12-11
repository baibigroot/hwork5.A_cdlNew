package com.company;

import java.util.concurrent.CountDownLatch;

public class Uploader extends Thread {

    private CountDownLatch cdl;

    public Uploader(CountDownLatch cdl){
        this.cdl = cdl;
    }

    public void run(){
        try {
            for (int i = 0; i < 7; i++) {
                System.out.println(" ⌛");
                sleep(1000);
            }
            System.out.println(" Файл загружен в облако ");
            cdl.countDown();
        } catch (Exception e){
            System.out.println(" Ошибка загрузки ");
        }
    }



}
