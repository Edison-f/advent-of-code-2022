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
                if(visibleUp(grid, i, j) ||
                    visibleDown(grid, i, j) ||
                    visibleLeft(grid, i, j) ||
                    visibleRight(grid, i ,j)) {
                    System.out.println(curr + " i: " + i + "\t j:" + j);
                    result++;
                }
            }
        }

        System.out.println(result + (grid.size() * 2) + (grid.get(0).length * 2) - 4);
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

    public static boolean visibleUp(ArrayList<int[]> grid, int i, int j) {
        int curr = grid.get(i)[j];
        for (int k = i - 1; k >= 0; k--) {
            if(grid.get(k)[j] >= curr) {
//                System.out.println(curr + " i: " + i + "\t j:" + j + "\t target:" + grid.get(i)[k]);
                return false;
            }
        }
        return true;
    }

    public static boolean visibleDown(ArrayList<int[]> grid, int i, int j) {
        int curr = grid.get(i)[j];
        for (int k = i + 1; k < grid.size(); k++) {
            if(grid.get(k)[j] >= curr) {
//                System.out.println(curr + " i: " + i + "\t j:" + j + "\t target:" + grid.get(i)[k]);
                return false;
            }
        }
        return true;
    }

    public static boolean visibleLeft(ArrayList<int[]> grid, int i, int j) {
        int curr = grid.get(i)[j];
        for (int k = j - 1; k >= 0; k--) {
            if(grid.get(i)[k] >= curr) {
//                System.out.println(curr + " i: " + i + "\t j:" + j + "\t target:" + grid.get(i)[k]);
                return false;
            }
        }
        return true;
    }

    public static boolean visibleRight(ArrayList<int[]> grid, int i, int j) {
        int curr = grid.get(i)[j];
        for (int k = j + 1; k < grid.size(); k++) {
            if(grid.get(i)[k] >= curr) {
//                System.out.println(curr + " i: " + i + "\t j:" + j + "\t target:" + grid.get(i)[k]);
                return false;
            }
        }
        return true;
    }
}
