public class merge_sort {
    //Copied this straight up from Multithreaded.
    public static int merge(int[] arr, int left_index, int mid_index, int right_index) {
        //This algorithm makes me create variables as if Christmas came early and I am Santa
        
        int left_frag_len = mid_index - left_index + 1;
        int right_frag_len = right_index - mid_index;

        int left[] = new int[left_frag_len];
        int right[] = new int[right_frag_len];

        for (int i = 0; i < left_frag_len; i++)
        {
            left[i] = arr[left_index + i];
        }

        for (int j = 0; j < right_frag_len; j++){
            right[j] = arr[mid_index + 1 + j];
        }
        
        int i = 0, j = 0;
        int bigBoy = left_index;
        while (i < left_frag_len && j < right_frag_len) {
            if (left[i] <= right[j]) {
                arr[bigBoy] = left[i];
                i++;
            } else {
                arr[bigBoy] = right[j]; //he grows up so fast :')
                j++;
            }
            bigBoy++;
        }
        while (i < left_frag_len) {
            arr[bigBoy] = left[i];
            i++;
            bigBoy++;
        }
        while (j < right_frag_len) {
            arr[bigBoy] = right[j];
            j++;
            bigBoy++;
        }
        return bigBoy; // bro thought he was big, but he was just a number in the grand scheme of things....shit that was way too real, am I a poet?
    }

    public static void mergeSort(int[] arr, int left_index, int right_index) {
        if (left_index < right_index) {
            int mid_index = (left_index + right_index) / 2;
            mergeSort(arr, left_index, mid_index);
            mergeSort(arr, mid_index + 1, right_index);
            merge(arr, left_index, mid_index, right_index);
        }
    }
    public static void main(String[] args){
        long startTimeFull = System.nanoTime();
        for(int i=1; i<=10; i++){
            long startTime = System.nanoTime();
            int arr[][] = utils.parseArrayFromFile("input"+(i)+".txt");
            for(int j=0; j<arr.length;j++){
                mergeSort(arr[j], 0, arr[j].length - 1);
                utils.writeArrayToFile(arr[j], "merge/output" + i + ".txt");
            }
            System.out.println("Output File " + i + " populated.");
            System.out.println("Processing Time(ns): " + (System.nanoTime() - startTime) + " ns");
        }
        System.out.println("Full Processing Time(ns): " + (System.nanoTime() - startTimeFull) + " ns");
        System.out.println("Full Processing Time(s): " + (System.nanoTime() - startTimeFull) / 1e9 + " s");
    }
}
