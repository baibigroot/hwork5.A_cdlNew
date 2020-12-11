package com.company;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;

public class Downloader extends Thread{

    private Semaphore semaphore;
    private CountDownLatch cdl;

    public Downloader(String name, CountDownLatch cdl,Semaphore semaphore){
        super(name);
        this.cdl = cdl;
        this.semaphore = semaphore;
    }

    public void run(){
        try {
            System.out.println(" Ожидание загрузки  "+ this.getName());
            semaphore.acquire();
            System.out.println(" Загрузка началась "+ this.getName());
            sleep(2005);
            System.out.println( "Загрузка завершилась "+ this.getName() );
           semaphore.release();

           cdl.countDown();
           cdl.await();

        } catch (Exception e){
            System.out.println(" Ошибка загрузки ");
        }
    }

}
