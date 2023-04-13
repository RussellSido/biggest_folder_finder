import java.io.File;
import java.util.HashMap;
import java.util.Set;
import java.util.concurrent.ForkJoinPool;

public class Main {
    public static void main(String[] args) {
//        MyThread thread = new MyThread(1);
//        MyThread thread2 = new MyThread(2);
//         thread.start();
//         thread2.start();
        System.out.println(getSizeFromHumanReadable("235K"));
        System.exit(0);
        String folderPath = "D:\\Программы";
        File file = new File(folderPath);
         long start = System.currentTimeMillis();
//        System.out.println(getFolderSize(file));
        FolderSizeCalculator calculator = new FolderSizeCalculator(file);
        ForkJoinPool pool = new ForkJoinPool();
        long size = pool.invoke(calculator);
        System.out.println(size);

        long duration = (System.currentTimeMillis() - start)/1000;
        System.out.println(duration + " seconds");

    }
    public  static long getFolderSize(File folder)
    {
        if (folder.isFile())
        {
            return folder.length();
        }
        File[] files = folder.listFiles();
        long sum =0;
        for(File file :files)
        {
            sum += getFolderSize(file);
        }
        return  sum;
    }
    // TODO: 24B, 234K, 36M, 2G, 12T;
    //  235K => 240640;
    public  static long getSizeFromHumanReadable(String size){
        HashMap<Character, Integer> char2multiplier = getMultipliers();
        char sizeFactor = size.
                replaceAll("[0-9\\s+]+", "").charAt(0);

        int multiplier = char2multiplier.get(sizeFactor);
        long length = multiplier * Long.valueOf(size.replaceAll("[^0-9]", ""));

        return  length;
    }
     private static HashMap<Character, Integer> getMultipliers(){
        char[] multipliers = {'B', 'K', 'M', 'G', 'T'};
        HashMap<Character, Integer> char2multiplier = new HashMap<>();
       for(int i = 0;i < multipliers.length; i++){
           char2multiplier.put(multipliers[i],(int)Math.pow(1024,i));
       }
       return char2multiplier;
     }
}