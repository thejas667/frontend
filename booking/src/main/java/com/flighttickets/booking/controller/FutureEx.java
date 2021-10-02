package com.flighttickets.booking.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class FutureEx {
    public static void main(String[] args) throws InterruptedException, ExecutionException {

        System.out.println(Runtime.getRuntime().availableProcessors());
        ExecutorService executorService= Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        List<Callable<Integer>> callables = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            int finalI = i;
            callables.add(new Callable<Integer>() {
                @Override
                public Integer call() throws Exception {
                    System.out.print(", "+finalI);
                    return finalI;
                }
            });
        }
        List<Future<Integer>> futures = executorService.invokeAll(callables);
        executorService.shutdown();
    }
}
