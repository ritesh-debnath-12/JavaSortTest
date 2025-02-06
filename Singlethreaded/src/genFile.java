import java.io.File;
import java.io.FileWriter;
import java.util.Random;

public class genFile {
    static Random rand = new Random();

    private static int[] generateArray() {
        int size = rand.nextInt(55, 100);
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            int num = rand.nextInt(1, 999);
            arr[i] = num;
        }
        return arr;
    }

    private static void generateSubdirectories(){
        String subdirs[] = {"output/quick", "output/merge", "output/insert", "output/bubble"};
        for(String subdir : subdirs){
            new File(subdir).mkdir();
            System.out.println(subdir + " folder generated");
        }
    }

    private static void generateFolderHierarchy(){
        if(new File("input").mkdir()){
            System.out.println("input folder generated");
        }
        else{
            System.out.println("input folder already exists, skipping");
        }

        if(new File("output").mkdir()){
            System.out.println("output folder generated");
            generateSubdirectories();
        }
        else{
            System.out.println("output folder already exists, skipping");
        }
    }

    public static void generateInputFiles(String fileName) {
        try {
            long startTime = System.nanoTime();
            File file = new File("input/" + fileName);
            if (file.createNewFile()) {
                System.out.println("File created: " + file.getName());
                FileWriter writer = new FileWriter(file);
                for (int i = 0; i < 1e6; i++) {
                    int[] arr = generateArray();
                    writer.write("[");
                    for (int j = 0; j < arr.length; j++) {
                        writer.write(arr[j] + " ");
                    }
                    writer.write("]\n");
                }
                System.out.println("File populated: " + file.getName());
                System.out.println("Processing Time(s): " + (System.nanoTime() - startTime) / 1e9 + " s");
                writer.close();
            } else {
                System.out.println("File already exists.");
            }
        } catch (Exception e) {
            System.out.println("An error occurred :(");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        long startTime = System.nanoTime();
        generateFolderHierarchy();
        for (int i = 0; i < 10; i++) {
            generateInputFiles("input" + (i + 1) + ".txt");
        }
        System.out.println("Total Processing Time(s): " + (System.nanoTime() - startTime) / 1e9 + " s");
    }
}