package org.example;

import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) {
    /*
        ExecutorService executor= Executors.newFixedThreadPool(2);
        executor.execute(
                ()->{
                    System.out.println(Thread.currentThread().getName()+"Herbeat");

                }

        );
        ScheduledExecutorService scheduler=Executors.newScheduledThreadPool(1);
        scheduler.schedule(
                ()->{
                    System.out.println("Hearbet");
                },2,TimeUnit.SECONDS
        );
        ThreadPoolExecutor manualPool=new ThreadPoolExecutor(2,4,60,TimeUnit.SECONDS,new LinkedBlockingDeque<>());
        manualPool.execute(() -> {System.out.println("[Manual Cooking with a custom pool!");});

        scheduler.shutdown();
        manualPool.shutdown();
        executor.shutdown();


     */
        ExecutorService service= Executors.newFixedThreadPool(3);
        long start1=System.currentTimeMillis();
        String first1=Asynchronous_Processing_Pipeline.fetchPrice();
        String second1=Asynchronous_Processing_Pipeline.fetchInventory();
        String third1=Asynchronous_Processing_Pipeline.fetchShippingDate();
        long end1=System.currentTimeMillis();
        System.out.println("Result: "+first1+"-"+second1+"-"+third1);
        System.out.println("Time: "+(end1-start1));
        //Asynchronous
        long start2=System.currentTimeMillis();
        CompletableFuture<String> PriceFuture=CompletableFuture.supplyAsync(()->Asynchronous_Processing_Pipeline.fetchPrice(),service);
        CompletableFuture<String> InventoryFuture=CompletableFuture.supplyAsync(()->Asynchronous_Processing_Pipeline.fetchInventory(),service);
        CompletableFuture<String> DateFuture=CompletableFuture.supplyAsync(()->Asynchronous_Processing_Pipeline.fetchShippingDate(),service);
        CompletableFuture<String>FinalReport=CompletableFuture.allOf(PriceFuture,InventoryFuture,DateFuture)
                .thenApply(data->{
                    return data+"\uD83D\uDEDF";
                }).thenRun(()->{
            String first2=PriceFuture.join();
            String second2=InventoryFuture.join();
            String third2=DateFuture.join();
            System.out.println("Result: "+first2+"-"+second2+"-"+third2);
            long end2=System.currentTimeMillis();
            System.out.println("Time: "+(end2-start2));
        });
        FinalReport.join();
    }
}