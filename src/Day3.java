import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Day3 {
    public static void main(String[] args) throws FileNotFoundException {
        FileInputStream fs = new FileInputStream("./src/day3.txt");
        Scanner in = new Scanner(fs);
        String[] next = new String[3];
        int result = 0;
        while(in.hasNext()) {
            for(int i = 0; i < 3; i++) {
                next[i] = in.next();
            }

            for (int i = 0; i < next[0].length(); i++) {
                char curr = next[0].charAt(i);
                if(next[1].indexOf(curr) != -1 && next[2].indexOf(curr) != -1) {
                    if(Character.toLowerCase(curr) == curr) {
                        result += curr - 96;
                        next[0] = next[0].replaceAll(curr + "", "");
                        System.out.println(curr - 98 + " " + curr + " " + i);
                    } else {
                        result += curr - 38;
                        next[0] = next[0].replaceAll(curr + "", "");
                        System.out.println(curr - 38 + " " + curr + " " + i);
                    }
                }
            }
        }
        System.out.println(result);
    }
}
