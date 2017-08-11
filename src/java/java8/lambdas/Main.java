package java8.lambdas;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Arrays;
import java.util.regex.Pattern;

public class Main {

    static File[] e2(String path) {
        File file = new File(path);
        File[] files = null;
        if (file.isDirectory()) {
             files = file.listFiles(f -> f.getName().length() < 30);
        }
        return files;
    }

    static File[] e3(String path, String extension) {
        File file = new File(path);
        Pattern pattern = Pattern.compile(".*\\." + extension);
        return file.listFiles((dir, name) -> pattern.asPredicate().test(name));
    }

    static File[] e4(File[] files) {
        Arrays.sort(files, (f1, f2) -> {
            if ((f1.isDirectory() && f2.isDirectory()) || (!f1.isDirectory() && !f2.isDirectory())) {
                return f1.getName().compareTo(f2.getName());
            } else {
                return f1.isDirectory() && !f2.isDirectory() ? -1 : !f1.isDirectory() && f2.isDirectory() ? 1 : 0;
            }
        });
        return files;
    }

    public static void main(String[] args) {
        File[] files = e2("c:\\Users\\aberezovski.ARTGROUP.000\\Downloads\\New folder\\");
        System.out.println(Arrays.toString(files));

        System.out.println(Arrays.toString(files));
    }

}
