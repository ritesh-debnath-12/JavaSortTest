import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class quick_sort {
    private static final int THREAD_COUNT = Runtime.getRuntime().availableProcessors();
    public static int partition(int[] arr, int low, int high)
    {
        int pivot = arr[high];
        int i = (low - 1);
        for (int j = low; j < high; j++) {
            if (arr[j] < pivot) {
                i++;
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;
        return i + 1;
    }

    public static void quick(int[] arr, int low, int high) {
        if (low < high) {
            int pi = partition(arr, low, high);
            quick(arr, low, pi - 1);
            quick(arr, pi + 1, high);
        }
    }
    /*
     * Implemented Multithreading :)(03/02/2025)(@ a very young evening)
     */

    //1 File = 490 seconds (Single Threaded)
    //10 Files = 32.29 seconds (Multithreaded + BufferedWriter)
    public static void main(String[] args) {
        long startTimeFull = System.nanoTime();
        ExecutorService executor = Executors.newFixedThreadPool(THREAD_COUNT);
        for (int i = 1; i <= 10; i++) { 
            int fileIndex = i;
            executor.execute(() -> utils.processFile(fileIndex, quick_sort::quick));
        }
        executor.shutdown();
        try {
            executor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (InterruptedException e) {
            System.out.println("Executor Interrupted!");
            e.printStackTrace();
        }
        Runtime.getRuntime().gc();
        System.out.println("Full Processing Time(ns): " + (System.nanoTime() - startTimeFull) + " ns");
        System.out.println("Full Processing Time(s): " + (System.nanoTime() - startTimeFull) / 1e9 + " s");
    }
}
