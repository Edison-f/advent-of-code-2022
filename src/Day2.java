import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Day2 {
    public static void main(String[] args) throws FileNotFoundException {
        int score = 0;
        /**
         * A / X- Rock
         * B / Y - Paper
         * C / Z - Scissors
         */
        FileInputStream fs = new FileInputStream("./src/day2.txt");
        Scanner in = new Scanner(fs);

        String next;
        while(in.hasNext()) {
            next = in.nextLine();
            char opponent = next.charAt(0);
            char mine = next.charAt(2);
            switch (opponent) {
                case 'A':
                    switch (mine) {
                        case 'X' -> score += 4;
                        case 'Y' -> score += 8;
                        case 'Z' -> score += 3;
                    }
                    break;
                case 'B':
                    switch (mine) {
                        case 'X' -> score += 1;
                        case 'Y' -> score += 5;
                        case 'Z' -> score += 9;
                    }
                    break;
                case 'C':
                    switch (mine) {
                        case 'X' -> score += 7;
                        case 'Y' -> score += 2;
                        case 'Z' -> score += 6;
                    }
                    break;
            }
        }
        System.out.println(score);
    }

}
