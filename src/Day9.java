import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

public class Day9 {

    static int headX = 0;
    static int headY = 0;

    static int tailX = 0;
    static int tailY = 0;

    static ArrayList<int[]> result;

    public static void main(String[] args) throws FileNotFoundException {
        FileInputStream fs = new FileInputStream("./src/day9.txt");
        Scanner in = new Scanner(fs);
        result = new ArrayList<>();
        result.add(new int[]{tailX, tailY});

        while (in.hasNextLine()) {
            String input = in.nextLine();
            String[] split = input.split(" ");

            move(split[0], Integer.parseInt(split[1]));
        }

        System.out.println(result.size());
    }

    public static boolean contains(ArrayList<int[]> arr, int[] target) {
        for (int[] itr :
                arr) {
            if (itr[0] == target[0] && itr[1] == target[1]) {
                return true;
            }
        }
//        System.out.println(target[0] + " " + target[1]);
        return false;
    }


    public static void move(String s, int count) {
        for (int i = 0; i < count; i++) {
            int[] last = new int[] {headX, headY};
            System.out.println("Head: " + headX + " " + headY + "\tTail: " + tailX + " " + tailY);
            int[] coords;
            switch (s.charAt(0)) {
                case 'U':
                    headY++;
                    if (tailY + 1 < headY) {
                        tailX = last[0];
                        tailY = last[1];
                    }
                    coords = new int[]{tailX, tailY};
                    if (!contains(result, coords)) {
                        result.add(coords);
                    }
                    break;
                case 'D':
                    headY--;
                    if (tailY - 1 > headY) {
                        tailX = last[0];
                        tailY = last[1];
                    }
                    coords = new int[]{tailX, tailY};
                    if (!contains(result, coords)) {
                        result.add(coords);
                    }
                    break;
                case 'L':
                    headX--;
                    if (tailX - 1 > headX) {
                        tailX = last[0];
                        tailY = last[1];
                    }
                    coords = new int[]{tailX, tailY};
                    if (!contains(result, coords)) {
                        result.add(coords);
                    }
                    break;
                case 'R':
                    headX++;
                    if (tailX + 1 < headX) {
                        tailX = last[0];
                        tailY = last[1];
                    }
                    coords = new int[]{tailX, tailY};
                    if (!contains(result, coords)) {
                        result.add(coords);
                    }
                    break;
            }
        }
    }
}
