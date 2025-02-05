import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class bubble_sort {
    private static final int THREAD_COUNT = Runtime.getRuntime().availableProcessors();
    public static void bubble(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n-1; i++) {
            for (int j = 0; j < n-i-1; j++) {
                if (arr[j] > arr[j+1]) {
                    int temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
            }
        }
    }

    // 1 File = 539 seconds(Single Thread)
    // 10 Files = 37.624 second(Multithread(16 cores) + BufferedWriter)
    public static void main(String[] args) {
        long startTimeFull = System.nanoTime();
        ExecutorService executor = Executors.newFixedThreadPool(THREAD_COUNT);
        
        for(int i = 1; i<=10; i++){
            int fileIndex = i;
            executor.execute(() -> utils.processFile(fileIndex, bubble_sort::bubble));
        }

        executor.shutdown();
        try{
            executor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        }catch(Exception e){
            System.out.println("An error occurred :(");
            e.printStackTrace();
        }
        Runtime.getRuntime().gc();
        System.out.println("Full Processing Time(ns): " + (System.nanoTime() - startTimeFull) + " ns");
        System.out.println("Full Processing Time(s): " + (System.nanoTime() - startTimeFull) / 1e9 + " s");
    }
}
