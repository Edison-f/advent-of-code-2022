import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Day10 {
    public static void main(String[] args) throws FileNotFoundException {
        int x = 1;
        int cycle = 1;
        ArrayList<Integer> signalStrengths = new ArrayList<>();
        ArrayList<Integer> queue = new ArrayList<>();
        int sum = 0;

        FileInputStream fs = new FileInputStream("./src/day10.txt");
        Scanner in = new Scanner(fs);

        while(in.hasNextLine()) {
            String input = in.nextLine();
            String[] split = input.split(" ");


            if(split.length == 1) { // no operation
                queue.add(0);
            } else { // Add to x;
                queue.add(0);
                queue.add(Integer.parseInt(split[1]));
            }



        }
        int position = 0;
        for (int i :
                queue) {
            if(position >= x - 1 && position <= x + 1) {
                System.out.print("#");
            } else {
                System.out.print(".");
            }
            x += i;
            position++;
            if(position == 40) {
                System.out.println();
                position = 0;
            }

        }


    }
}
