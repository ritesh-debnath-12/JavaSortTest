public class insertion_sort {
    public static void insert(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int key = arr[i];
            int j = i - 1;
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j = j - 1;
            }
            arr[j + 1] = key;
        }
    }
    public static void main(String[] args) {
        long startTimeFull = System.nanoTime();
        for(int i=1; i<=10; i++){
            long startTime = System.nanoTime();
            int arr[][] = utils.parseArrayFromFile("input"+(i)+".txt");
            for(int j=0; j<arr.length;j++){
                insert(arr[j]);
                utils.writeArrayToFile(arr[j], "insert/output" + i + ".txt");
            }
            System.out.println("Output File " + i + " populated.");
            System.out.println("Processing Time(ns): " + (System.nanoTime() - startTime) + " ns");
        }
        System.out.println("Full Processing Time(ns): " + (System.nanoTime() - startTimeFull) + " ns");
        System.out.println("Full Processing Time(s): " + (System.nanoTime() - startTimeFull) / 1e9 + " s");
    }
}