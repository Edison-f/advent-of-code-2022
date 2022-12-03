import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Day1 {
    public static void main(String[] args) throws FileNotFoundException {
        FileInputStream fs = new FileInputStream("./src/day1.txt");
        Scanner in = new Scanner(fs);

        ArrayList<Integer> result = new ArrayList<>();
        int i = 0;
        String next;
        while(in.hasNext()) {
            next = in.nextLine();
            if(next.equals("")) {
                i++;
                next = in.nextLine();
            }
            if(i > result.size() - 1) {
                result.add(Integer.parseInt(next));
            } else {
                result.set(i, result.get(i) + Integer.parseInt(next));
            }
            next.length();
        }
        int max1 = 0;
        int max2 = 0;
        int max3 = 0;
        for(int n : result) {
            if(n > max1) {
                max3 = max2;
                max2 = max1;
                max1 = n;
            } else if(n > max2) {
                max3 = max2;
                max2 = n;
            } else if(n > max3) {
                max3 = n;
            }
        }
        System.out.println(max1 + max2 + max3);
    }
}
