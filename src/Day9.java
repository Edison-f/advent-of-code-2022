import java.beans.XMLDecoder;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Day9 {

    static int headX = 0;
    static int headY = 0;

    static int[] prevX;
    static int[] prevY;

    static ArrayList<int[]> result;

    public static void main(String[] args) throws FileNotFoundException {
        FileInputStream fs = new FileInputStream("./src/day9.txt");
        Scanner in = new Scanner(fs);
        result = new ArrayList<>();
        prevX = new int[9];
        prevY = new int[9];
        result.add(new int[]{0, 0});

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

    public static void cycle(int x, int y) {
//        if(prevX[0] + 1 < headX || prevX[0] - 1 > headX || prevY[0] + 1 < headY || prevX[0] - 1 > headY) {

        boolean xDiff = Math.abs(prevX[0] - headX) > 1;
        boolean yDiff = Math.abs(prevY[0] - headY) > 1;

        if (prevX[0] == headX && prevY[0] != headY) { // Up / Down
            if (yDiff && prevY[0] < headY) { // Up
                prevY[0]++;
            } else if (yDiff) {
                prevY[0]--;
            }
        } else if (prevX[0] != headX && prevY[0] == headY) { // Left / Right
            if (xDiff && prevX[0] < headX) { // Right
                prevX[0]++;
            } else if (xDiff) {
                prevX[0]--;
            }
        } else if (headX > prevX[0]) { // Right
            if (headY > prevY[0] && (yDiff || xDiff)) { // Up
                prevX[0]++;
                prevY[0]++;
            } else if (yDiff || xDiff) { // Down
                prevX[0]++;
                prevY[0]--;
            }
        } else { // Left
            if (headY > prevY[0] && (yDiff || xDiff)) { // Up
                prevX[0]--;
                prevY[0]++;
            } else if (yDiff || xDiff) { // Down
                prevX[0]--;
                prevY[0]--;
            }
        }

        for (int i = 1; i < prevX.length; i++) {
            xDiff = Math.abs(prevX[i] - prevX[i - 1]) > 1;
            yDiff = Math.abs(prevY[i] - prevY[i - 1]) > 1;
            if (prevX[i] == prevX[i - 1] && prevY[i] != prevY[i - 1]) { // Up / Down
                if (yDiff && prevY[i] < prevY[i - 1]) { // Up
                    prevY[i]++;
                } else if (yDiff) {
                    prevY[i]--;
                }
            } else if (prevX[i] != prevX[i - 1] && prevY[i] == prevY[i - 1]) { // Left / Down
                if (xDiff && prevX[i] < prevX[i - 1]) { // Right
                    prevX[i]++;
                } else if (xDiff) {
                    prevX[i]--;
                }
            } else if (prevX[i - 1] > prevX[i]) { // Right
                if (prevY[i - 1] > prevY[i] && (yDiff || xDiff)) { // Up
                    prevX[i]++;
                    prevY[i]++;
                } else if (yDiff || xDiff) { // Down
                    prevX[i]++;
                    prevY[i]--;
                }
            } else { // Left
                if (prevY[i - 1] > prevY[i] && (yDiff || xDiff)) { // Up
                    prevX[i]--;
                    prevY[i]++;
                } else if (yDiff || xDiff) { // Down
                    prevX[i]--;
                    prevY[i]--;
                }
            }
        }
    }

    public static void move(String s, int count) {
        for (int i = 0; i < count; i++) {
            int[] last = new int[]{headX, headY};
            System.out.println("Head: " + headX + " " + headY + "\tTail: " + prevX[8] + " " + prevY[8]);
            for (int j = 0; j < prevX.length; j++) {
                System.out.print(j + 1 + ": (" + prevX[j] + ", " + prevY[j] + ") ");
            }
            System.out.println("\n");
            int[] coords;
            switch (s.charAt(0)) {
                case 'U':
                    headY++;
                    break;
                case 'D':
                    headY--;
                    break;
                case 'L':
                    headX--;
                    break;
                case 'R':
                    headX++;
                    break;
            }
            cycle(last[0], last[1]);
            coords = new int[]{prevX[8], prevY[8]};
            if (!contains(result, coords)) {
                result.add(coords);
            }
        }
    }
}
