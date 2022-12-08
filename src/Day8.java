import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Day8 {
    public static void main(String[] args) throws FileNotFoundException {
        FileInputStream fs = new FileInputStream("./src/day8.txt");
        Scanner in = new Scanner(fs);

        ArrayList<String> input = new ArrayList<>();
        while (in.hasNextLine()) {
            input.add(in.nextLine());
        }
        ArrayList<int[]> grid = new ArrayList<>();
        for (int i = 0; i < input.size(); i++) {
            String[] split = input.get(i).split("");
            int[] convert = new int[split.length];
            for (int j = 0; j < split.length; j++) {
                convert[j] = Integer.parseInt(split[j]);
            }
            grid.add(convert);
        }

        int result = 0;
        for (int i = 1; i < input.size() - 1; i++) {
            for (int j = 1; j < input.get(i).length() - 1; j++) {
                int curr = grid.get(i)[j];
                int accum = visibleUp(grid, i, j) * visibleDown(grid, i, j)
                        * visibleLeft(grid, i, j) * visibleRight(grid, i, j);
                if(result < accum) {
                    result = accum;
                    System.out.println(i + " " + j + " " + accum);
                }
            }
        }

        System.out.println(result);
        /*
        for (int[] arr :
                grid) {
            for (int i :
                    arr) {
                System.out.print(i + " ");
            }
            System.out.println();
        }
         */
    }

    public static int visibleUp(ArrayList<int[]> grid, int i, int j) {
        int curr = grid.get(i)[j];
        int result = 1;
        for (int k = i - 1; k > 0; k--) {
            if(grid.get(k)[j] < curr) {
//                System.out.println(curr + " i: " + i + "\t j:" + j + "\t target:" + grid.get(i)[k]);
                result++;
            } else {
                break;
            }
        }
        return result;
    }

    public static int visibleDown(ArrayList<int[]> grid, int i, int j) {
        int curr = grid.get(i)[j];
        int result = 1;
        for (int k = i + 1; k < grid.size() - 1; k++) {
            if(grid.get(k)[j] < curr) {
//                System.out.println(curr + " i: " + i + "\t j:" + j + "\t target:" + grid.get(i)[k]);
                result++;
            } else {
                break;
            }
        }
        return result;
    }

    public static int visibleLeft(ArrayList<int[]> grid, int i, int j) {
        int curr = grid.get(i)[j];
        int result = 1;
        for (int k = j - 1; k > 0; k--) {
            if(grid.get(i)[k] < curr) {
//                System.out.println(curr + " i: " + i + "\t j:" + j + "\t target:" + grid.get(i)[k]);
                result++;
            } else {
                break;
            }
        }
        return result;
    }

    public static int visibleRight(ArrayList<int[]> grid, int i, int j) {
        int curr = grid.get(i)[j];
        int result = 1;
        for (int k = j + 1; k < grid.size() - 1; k++) {
            if(grid.get(i)[k] < curr) {
//                System.out.println(curr + " i: " + i + "\t j:" + j + "\t target:" + grid.get(i)[k]);
                result++;
            } else {
                break;
            }
        }
        return result;
    }
}
