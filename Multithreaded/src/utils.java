import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.Scanner;
import java.util.ArrayList;

public class utils {

    private static int[][] parseArrayFromFile(String fileName) {
        ArrayList<int[]> arrayList = new ArrayList<>();
        try {
            File file = new File("input/" + fileName);
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();

                if (line.startsWith("[") && line.endsWith("]")) {
                    line = line.substring(1, line.length() - 1).trim();
                }

                String[] elements = line.split("\\s+");
                int[] arr = new int[elements.length];

                for (int i = 0; i < elements.length; i++) {
                    arr[i] = Integer.parseInt(elements[i]);
                }

                arrayList.add(arr);
            }

            scanner.close();
            return arrayList.toArray(new int[arrayList.size()][]);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    @FunctionalInterface
    interface ThreeArgsSort {
        void sort(int[] arr, int low, int high);
    }

    @FunctionalInterface
    interface OneArgSort {
        void sort(int[] arr);
    }

    public static void processFile(String outputPath, int fileIndex, ThreeArgsSort sorting_function) {
        try {
            long startTime = System.nanoTime();
            int[][] arr = utils.parseArrayFromFile("input" + fileIndex + ".txt");

            File file = new File(outputPath + fileIndex + ".txt");
            System.out.println("Processing File " + fileIndex + "...");
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, false))) {
                for (int i = 0; i < arr.length; i++) {
                    sorting_function.sort(arr[i], 0, arr[i].length - 1);
                    writer.write("[");
                    for (int num : arr[i]) {
                        writer.write(num + " ");
                    }
                    writer.write("]\n");
                }
            }
            System.out.println("Output File " + fileIndex + " populated.");
            System.out.println("Processing Time(ns): " + (System.nanoTime() - startTime) + " ns");
        } catch (Exception e) {
            System.out.println("File Processing Issue Detected! Terminating :(");
            e.printStackTrace();
        }
    }

    public static void processFile(String outputPath, int fileIndex, OneArgSort sorting_function) {
        try {
            long startTime = System.nanoTime();
            int[][] arr = utils.parseArrayFromFile("input" + fileIndex + ".txt");

            File file = new File(outputPath + fileIndex + ".txt");
            System.out.println("Processing File " + fileIndex + "...");
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, false))) {
                for (int i = 0; i < arr.length; i++) {
                    sorting_function.sort(arr[i]);
                    writer.write("[");
                    for (int num : arr[i]) {
                        writer.write(num + " ");
                    }
                    writer.write("]\n");
                }
            }
            System.out.println("Output File " + fileIndex + " populated.");
            System.out.println("Processing Time(ns): " + (System.nanoTime() - startTime) + " ns");
        } catch (Exception e) {
            System.out.println("File Processing Issue Detected! Terminating :(");
            e.printStackTrace();
        }
    }
}
