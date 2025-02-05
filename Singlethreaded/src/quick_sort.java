public class quick_sort {
    
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
    public static void main(String[] args) {
        long startTimeFull = System.nanoTime();
        
        /*
         * I understand that Quicksort is faster than Bubble sort, but 1e6 arrays, nahh, that's a lot of arrays to sort
         * at this point, multithreading is the only way to go, but since I am as illiterate as a rock, I won't( can't, *ahem*)
         * 
         * MORAL: Learning algos is cool, but god damn, I am not sorting 1e6 arrays with quicksort single threaded IRL 
         */

        for (int i = 1; i <= 10; i++) { 
            int[][] arr = utils.parseArrayFromFile("input" + i + ".txt");
            long startTime = System.nanoTime();
            for (int j = 0; j < arr.length; j++) {
                quick(arr[j], 0, arr[j].length - 1);
                utils.writeArrayToFile(arr[j], "quick/ouput" + i + ".txt");
            }
            System.out.println("Output File " + i + " populated.");
            System.out.println("Processing Time(ns): " + (System.nanoTime() - startTime) + " ns");
        }

        System.out.println("Full Processing Time(ns): " + (System.nanoTime() - startTimeFull) + " ns");
        System.out.println("Full Processing Time(s): " + (System.nanoTime() - startTimeFull) / 1e9 + " s");
    }
}
