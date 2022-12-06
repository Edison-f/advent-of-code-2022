import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;

public class Day6 {
    public static void main(String[] args) throws FileNotFoundException {
        FileInputStream fs = new FileInputStream("./src/day6.txt");
        Scanner in = new Scanner(fs);
        String input = in.next();
        for (int i = 0; i < input.length() - 14; i++) {
            HashSet<Character> set = new HashSet<>();
            for (int j = i; j < i + 14; j++) {
                set.add(input.charAt(j));
            }
            if(set.size() == 14) {
                System.out.print(i + 14);
                break;
            }
        }
    }
}
