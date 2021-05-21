package path_practice;

import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        File path = new File("");

        System.out.println(path.getAbsolutePath());
        System.out.println(path.getCanonicalPath());
    }
}
