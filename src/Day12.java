import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Queue;
import java.util.Scanner;
import java.util.SortedSet;

public class Day12 {

    static int[][] processed;
    static int[] start;
    static  int[] end;

    public static void main(String[] args) throws FileNotFoundException {
        FileInputStream fs = new FileInputStream("./src/day12.txt");
        Scanner in = new Scanner(fs);

        ArrayList<String> rawInput = new ArrayList<>();
        while(in.hasNextLine()) {
            rawInput.add(in.nextLine());
        }

        start = new int[2];
        ArrayList<int[]> starts = new ArrayList<>();
        end = new int[2];

        processed = new int[rawInput.size()][rawInput.get(0).length()]; // Y, X
        for (int i = 0; i < rawInput.size(); i++) {
            for (int j = 0; j < rawInput.get(i).length(); j++) {
                char charAt = rawInput.get(i).charAt(j);
                if(charAt == 'S' || charAt == 'a') {
                    start = new int[] {i, j};
                    starts.add(start);
                    processed[i][j] = 'a';
                } else if (charAt == 'E') {
                    end = new int[] {i, j};
                    processed[i][j] = 'z';
                } else {
                    processed[i][j] = charAt;
                }
            }
        }

        int[][] visited = new int[rawInput.size()][rawInput.get(0).length()];
        for (int i = 0; i < visited.length; i++) {
            for (int j = 0; j < visited[i].length; j++) {
                visited[i][j] = Integer.MAX_VALUE;
            }
        }
        visited[start[0]][start[1]] = 0;

//        int result = recursivePathfinder(start[0], start[1], visited, 0); //, new ArrayList<>());
        int result = Integer.MAX_VALUE;
        for (int[] startPos :
                starts) {
            int len = queuePathfinder(startPos[1], startPos[0]);
            if(len > -1) {
                result = Math.min(result, len);
            }
        }
        System.out.println(result);
    }

    public static int recursivePathfinder(int y, int x, int[][] visited, int count) {
        System.out.println(y + " " + x + " " + count);
//        }
        int result = Integer.MAX_VALUE;
        visited[y][x] = count;
//        ArrayList<String> traceCopy = new ArrayList<>(trace);

        if(y == end[0] && x == end[1]) {
            System.out.println(count);
            return count;
        }
        for (int i = y - 1; i < processed.length && (i <= y + 1); i += 2) {
            if(i < 0) {
                continue;
            }
            if(count == 0) {
                System.out.println("1st");
            }

            if(visited[i][x] >= count && (processed[y][x] + 1 == processed[i][x] || processed[y][x] == processed[i][x])) {
//                if(i == y - 1) {
//                    traceCopy.add("Up " + i + " " + x + " " + count);
//                } else {
//                    traceCopy.add("Down " + i + " " + x + " " + count);
//                }
                result = Math.min(result, recursivePathfinder(i, x, visited, count + 1)); //, new ArrayList<>(traceCopy)));
//                traceCopy.remove(0);
            }
        }

        for (int i = x - 1; i < processed[0].length && (i <= x + 1); i += 2) {
            if(i < 0) {
                continue;
            }
            if(count == 0) {
                System.out.println("2nd");
            }
            if(visited[y][i] >= count && (processed[y][x] + 1 == processed[y][i] || processed[y][x] == processed[y][i])) {
//                if(i == x - 1) {
//                    traceCopy.add("Left " + y + " " + i + " " + count);
//                } else {
//                    traceCopy.add("Right " + y + " " + i + " " + count);
//                }
                result = Math.min(result, recursivePathfinder(y, i, visited, count + 1)); //, new ArrayList<>(traceCopy)));
//                traceCopy.remove(0);
            }
        }
        return result;
    }

    public static int queuePathfinder(int y, int x) {
        ArrayList<Datapoint> queue = new ArrayList<>();
        queue.add(new Datapoint(x, y, 0));
        int result = -1;
        int[][] visited = new int[processed.length][processed[0].length];
        for (int i = 0; i < visited.length; i++) {
            for (int j = 0; j < visited[i].length; j++) {
                visited[i][j] = Integer.MAX_VALUE;
            }
        }
        while(queue.size() != 0) {
            Datapoint curr = queue.remove(0);
            if(curr.x == end[1] && curr.y == end[0]) {
                result = curr.count;
                break;
            }
            if(curr.count >= visited[curr.y][curr.x]) {
                continue;
            } else {
                visited[curr.y][curr.x] = curr.count;
            }
            for (int i = curr.y - 1; i < processed.length && (i <= curr.y + 1); i += 2) {
                if(i < 0) {

                } else if((processed[curr.y][curr.x] + 1 >= processed[i][curr.x]) && curr.count + 1 < visited[i][curr.x]) {
                    queue.add(new Datapoint(i, curr.x, curr.count + 1));
                }
            }

            for (int i = curr.x - 1; i < processed[0].length && (i <= curr.x + 1); i += 2) {
                if (i < 0) {

                } else if ((processed[curr.y][curr.x] + 1 >= processed[curr.y][i]) && curr.count + 1 < visited[curr.y][i]) {
                    queue.add(new Datapoint(curr.y, i, curr.count + 1));
                }
            }
        }

        /*
        for (int[] arr :
                visited) {
            for (int i :
                    arr) {
                if(i == 2147483647) {
                    System.out.print(-1 + "\t");
                } else {
                    System.out.print(i + "\t");
                }
            }
            System.out.println();
        }
        */

        return result;
    }

    public static class Datapoint {
        public int x;
        public int y;
        public int count;
        public Datapoint(int y, int x, int count) {
            this.x = x;
            this.y = y;
            this.count = count;
        }
    }
}
