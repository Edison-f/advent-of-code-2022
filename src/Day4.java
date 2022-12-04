import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Day4 {
    public static void main(String[] args) throws FileNotFoundException {
        FileInputStream fs = new FileInputStream("./src/day4.txt");
        Scanner in = new Scanner(fs);
        int result = 0;
        while(in.hasNextLine()) {
            String[] split = in.nextLine().split(",");
            String[] first = split[0].split("-");
            String[] second = split[1].split("-");
            int[] firstInts = new int[] {Integer.parseInt(first[0]), Integer.parseInt(second[0])};
            int[] secondInts = new int[] {Integer.parseInt(first[1]), Integer.parseInt(second[1])};
            int firstCompare = firstInts[0] - firstInts[1];
            int secondCompare = secondInts[0] - secondInts[1];
            System.out.println(firstCompare + " " + secondCompare);
            if(firstCompare >= 0 && secondCompare <= 0) {
                result++;
            } else if(firstCompare <= 0 && secondCompare >= 0 ) {
                result++;
            }
        }
        System.out.println(result);
    }
}
