import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Day5 {
    public static void main(String[] args) throws FileNotFoundException {
        FileInputStream fs = new FileInputStream("./src/day5.txt");
        Scanner in = new Scanner(fs);

        ArrayList<ArrayList<Character>> arr = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            arr.add(new ArrayList<>());
        }

        for (int i = 0; i < 8; i++) {
            String input = in.nextLine();
            int index = 0;
            for (int j = 1; j < input.length(); j += 4) {
                if (input.charAt(j) != ' ') {
                    arr.get(index).add(input.charAt(j));
                }
                index++;
            }
        }

        in.nextLine();
        in.nextLine();
        while (in.hasNext()) {
            for (ArrayList<Character> characterArr :
                    arr) {
                if (characterArr.size() != 0) {
                    System.out.print(characterArr.get(0));
                } else {
                    System.out.print(" ");
                }
            }
            String input = in.nextLine();
            String[] split = input.split(" ");
            int n = Integer.parseInt(split[1]);
            for (int i = 0; i < n; i++) {
                int to = Integer.parseInt(split[5]);
                int from = Integer.parseInt(split[3]);
                arr.get(to - 1).
                        add(0, arr.get(from - 1).remove(0));
            }
        }

        for (ArrayList<Character> characterArr :
                arr) {
            if (characterArr.size() != 0) {
                System.out.print(characterArr.get(0));
            } else {
                System.out.print(" ");
            }
        }
    }
}
