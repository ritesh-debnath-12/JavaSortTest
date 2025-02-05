import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class genFile {
    static Random rand = new Random();
    private static final int THREAD_COUNT = Runtime.getRuntime().availableProcessors();

    private static int[] generateArray() {
        int size = rand.nextInt(55, 100);
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            int num = rand.nextInt(1, 999);
            arr[i] = num;
        }
        return arr;
    }

    public static void generateInputFiles(String fileName) {
        try {
            long startTime = System.nanoTime();
            File file = new File("input/" + fileName);
            if (file.createNewFile()) {
                System.out.println("File created: " + file.getName());
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                    for (int i = 0; i < 1e6; i++) {
                        int[] arr = generateArray();
                        writer.write("[");
                        for (int j = 0; j < arr.length; j++) {
                            writer.write(arr[j] + " ");
                        }
                        writer.write("]\n");
                    }
                }
                catch(Exception e){
                    System.out.println("An error occurred :(");
                    e.printStackTrace();
                }
            } else {
                System.out.println("File already exists.");
            }
            System.out.println("File generation time(s): " + (System.nanoTime() - startTime) / 1e9 + " s");
        } catch (Exception e) {
            System.out.println("An error occurred :(");
            e.printStackTrace();
        }
    }

    // 10 Files = 112 seconds(Multithreaded + BufferedWriter)
    public static void main(String[] args) {
        long startTime = System.nanoTime();
        ExecutorService executor = Executors.newFixedThreadPool(THREAD_COUNT);
        for (int i = 0; i < 10; i++) {
            String fileName = "input" + (i + 1) + ".txt";
            executor.execute(() -> generateInputFiles(fileName));
        }
        executor.shutdown();
        try {
            executor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (Exception e) {
            System.out.println("An error occurred :(");
            e.printStackTrace();
        }
        System.out.println("Total Processing Time(ns):" + (System.nanoTime() - startTime) + " ns");
        System.out.println("Total Processing Time(s): " + (System.nanoTime() - startTime) / 1e9 + " s");
    }
}