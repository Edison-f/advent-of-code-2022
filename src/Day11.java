import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Day11 {
    public static void main(String[] args) throws FileNotFoundException {
        FileInputStream fs = new FileInputStream("./src/day11.txt");
        Scanner in = new Scanner(fs);
        ArrayList<Monkey> monkeyArray = new ArrayList<>();

        while(in.hasNextLine()) {
            int id = Integer.parseInt(in.nextLine().split(" ")[1].charAt(0) + "");
            String[] itemStrings = in.nextLine().trim().split(" ", 3)[2].split(", ");
            ArrayList<Integer> items = new ArrayList<>();
            for (String s :
                    itemStrings) {
                items.add(Integer.parseInt(s));
            }
            String operationString = in.nextLine().trim();
            String[] operands = new String[] {operationString.split(" ")[3], operationString.split(" ")[5]};
            char operator = operationString.split(" ")[4].charAt(0);
            int divisor = Integer.parseInt(in.nextLine().trim().split(" ")[3]);
            int[] throwTargets = new int[] {Integer.parseInt(in.nextLine().trim().split(" ")[5]),
                    Integer.parseInt(in.nextLine().trim().split(" ")[5])};
            monkeyArray.add(new Monkey(items, operator, operands, divisor, id, throwTargets));
            if(in.hasNextLine()) {
                in.nextLine();
            }
        }

        int roundCount = 20;
        for (int i = 0; i < roundCount; i++) {
            for (Monkey monkey :
                    monkeyArray) {
                while(!monkey.items.isEmpty()) {
                    int inspected = monkey.inspect();
                    if(monkey.isDivisible(inspected)) {
                        monkeyArray.get(monkey.throwTargets[0]).items.add(inspected);
                    } else {
                        monkeyArray.get(monkey.throwTargets[1]).items.add(inspected);
                    }
                }
            }
        }

        int first = 0;
        int second = 0;
        for (Monkey monkey :
                monkeyArray) {
            if(monkey.inspectCounter > first) {
                second = first;
                first = monkey.inspectCounter;
            } else if(monkey.inspectCounter > second) {
                second = monkey.inspectCounter;
            }
        }
        System.out.println(first * second);
    }

    public static class Monkey {
        public ArrayList<Integer> items;
        public char operator;
        public String[] operands;
        public int divisor;
        public int id;
        public int[] throwTargets;
        public int inspectCounter;

        public Monkey(ArrayList<Integer> items, char operator, String[] operands, int divisor, int id, int[] throwTargets) {
            this.items = items;
            this.operator = operator;
            this.operands = operands;
            this.divisor = divisor;
            this.id = id;
            this.throwTargets = throwTargets;

            inspectCounter = 0;
        }

        public int inspect() {
            inspectCounter++;
            int item = items.remove(0);
            boolean first = operands[0].equals("old");
            boolean second = operands[1].equals("old");
            if(first && second) {
                if(operator == '*') {
                    item *= item;
                } else {
                    item *= 2;
                }
            } else {
                if(operator == '*') {
                    item *= Integer.parseInt(operands[1]);
                } else {
                    item += Integer.parseInt(operands[1]);
                }
            }
            return item / 3;
        }

        public boolean isDivisible(int n) {
            return (n % divisor == 0);
        }
    }
}
