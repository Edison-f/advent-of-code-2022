import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Day6 {
    public static void main(String[] args) throws FileNotFoundException {
        FileInputStream fs = new FileInputStream("./src/day6.txt");
        Scanner in = new Scanner(fs);
        String input = in.next();
        for (int i = 0; i < input.length() - 4; i++) {
            char first = input.charAt(i);
            char second = input.charAt(i + 1);
            char third = input.charAt(i + 2);
            char fourth = input.charAt(i + 3);
            if (first != second && first != third && first != fourth) {
                if(second != third && second != fourth) {
                    if(third != fourth) {
                        System.out.print(i + 4);
                        break;
                    }
                }
            }
        }
    }
}
