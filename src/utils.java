import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.Scanner;
import java.util.ArrayList;

public class utils {

    public static int[][] parseArrayFromFile(String fileName) {
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

    public static void writeArrayToFile(int[] arr, String fileName) {
        try {
            File file = new File("output/" + fileName);
            if (file.createNewFile()) {
                System.out.println("File created: " + file.getName());
            }
            FileWriter writer = new FileWriter(file, true);
            writer.write("[");
            for (int i = 0; i < arr.length; i++) {
                writer.write(arr[i] + " ");
            }
            writer.write("]\n");
            writer.close();
        } catch (Exception e) {
            System.out.println("An error occurred :(");
            e.printStackTrace();
        }
    }
}
