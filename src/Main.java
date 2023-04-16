import java.io.File;
import java.util.HashMap;
import java.util.concurrent.ForkJoinPool;

public class Main {

    public static void main(String[] args) {
//        MyThread thread = new MyThread(1);
//        MyThread thread2 = new MyThread(2);
//         thread.start();
//         thread2.start();
//        System.out.println(getHumanReadableSize(240640));
//        System.exit(0);
//        System.out.println(getSizeFromHumanReadable("235K"));
//        System.exit(0);
        String folderPath = "D:\\Программы\\вивер";
        File file = new File(folderPath);
        Node root = new Node(file);
         long start = System.currentTimeMillis();
//        System.out.println(getFolderSize(file));
        FolderSizeCalculator calculator = new FolderSizeCalculator(root);
        ForkJoinPool pool = new ForkJoinPool();
        pool.invoke(calculator);
        System.out.println(root);

        long duration = (System.currentTimeMillis() - start)/1000;
        System.out.println(duration + " seconds");

    }
}