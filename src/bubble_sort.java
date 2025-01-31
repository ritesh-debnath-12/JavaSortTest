public class bubble_sort {
    public static void bubble(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n-1; i++) {
            for (int j = 0; j < n-i-1; j++) {
                if (arr[j] > arr[j+1]) {
                    // Swap arr[j] and arr[j+1]
                    int temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
            }
        }
    }

    // 1 File = 539 seconds
    public static void main(String[] args) {
        long startTimeFull = System.nanoTime();
        
        for (int i = 1; i <= 10; i++) { //This will probably take a while...actually, it will take a long while, a very fcking long time bruh
            int[][] arr = utils.parseArrayFromFile("input" + i + ".txt");
            long startTime = System.nanoTime();
            for (int j = 0; j < arr.length; j++) {
                bubble(arr[j]);
                utils.writeArrayToFile(arr[j], "bubble/output" + i + ".txt");
            }
            System.out.println("Output File " + i + " populated.");
            System.out.println("Processing Time(ns): " + (System.nanoTime() - startTime) + " ns");
        }

        System.out.println("Full Processing Time(ns): " + (System.nanoTime() - startTimeFull) + " ns");
        System.out.println("Full Processing Time(s): " + (System.nanoTime() - startTimeFull) / 1e9 + " s");
    }
}
