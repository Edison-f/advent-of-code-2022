import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Day2 {
    public static void main(String[] args) throws FileNotFoundException {
        int score = 0;
        /**
         * A / X- Rock / Lose
         * B / Y - Paper / Draw
         * C / Z - Scissors / Win
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
                        case 'X' -> score += 3;
                        case 'Y' -> score += 4;
                        case 'Z' -> score += 8;
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
                        case 'X' -> score += 2;
                        case 'Y' -> score += 6;
                        case 'Z' -> score += 7;
                    }
                    break;
            }
        }
        System.out.println(score);
    }

}
