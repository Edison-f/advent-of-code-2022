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

        for (int i :
                queue) {
            if((cycle - 20) % 40 == 0) {
                System.out.println(x + " cycle: " + cycle);
                signalStrengths.add(x * cycle);
            }
            x += i;
            System.out.print(x + " ");

            cycle++;
        }

        for (int i :
                signalStrengths) {
            sum += i;
        }
        System.out.println(sum);
    }
}
