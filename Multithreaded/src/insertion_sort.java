//package Multithreaded.src; // how come this is not working? why does SingleThreaded need this then?

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class insertion_sort {
    private static final int THREAD_COUNT = Runtime.getRuntime().availableProcessors();
    public static void insert(int[] arr){
        int i = 1, j = 0;
        while(i < arr.length){
            j = i;
            while(j>0 && arr[j-1]>arr[j]){
                int tmp = arr[j];
                arr[j] = arr[j - 1];
                arr[j-1] = tmp;
            }
            i++;
        }
    }

    public static void main(String[] args){
        long startTimeFull = System.nanoTime();
        ExecutorService executor = Executors.newFixedThreadPool(THREAD_COUNT);
        for(int i=1; i<=10; i++){
            final int fileIndex = i;
            executor.execute(() -> utils.processFile(fileIndex, insertion_sort::insert));
        }
        executor.shutdown();
        try{
            executor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (InterruptedException e){
            e.printStackTrace();
        }
        Runtime.getRuntime().gc();
        System.out.println("Full Processing Time(ns): " + (System.nanoTime() - startTimeFull) + " ns");
        System.out.println("Full Processing Time(s): " + (System.nanoTime() - startTimeFull) / 1e9 + " s");
    }
}
