import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

public class Day11 {
    public static void main(String[] args) throws FileNotFoundException {

//        ArrayInteger a1 = new ArrayInteger(3);
//        ArrayInteger a2 = new ArrayInteger(8);
//        a1.times(a2);
//        System.out.print(a1);
//        if(true)
//            return;

        FileInputStream fs = new FileInputStream("./src/day11.txt");
        Scanner in = new Scanner(fs);
        ArrayList<Monkey> monkeyArray = new ArrayList<>();

        while(in.hasNextLine()) {
            long id = Integer.parseInt(in.nextLine().split(" ")[1].charAt(0) + "");
            String[] itemStrings = in.nextLine().trim().split(" ", 3)[2].split(", ");
            ArrayList<ArrayInteger> items = new ArrayList<>();
            for (String s :
                    itemStrings) {
                items.add(new ArrayInteger(Integer.parseInt(s)));
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

        long roundCount = 20;
        for (long i = 0; i < roundCount; i++) {
            for (Monkey monkey :
                    monkeyArray) {
                while(!monkey.items.isEmpty()) {
                    ArrayInteger inspected = monkey.inspect();
                    if(monkey.isDivisible(inspected)) {
                        monkeyArray.get(monkey.throwTargets[0]).items.add(inspected);
                    } else {
                        monkeyArray.get(monkey.throwTargets[1]).items.add(inspected);
                    }
                }
            }
            if(i == 19) {
                for (Monkey monkey:
                     monkeyArray) {
                    System.out.print(monkey.id + ": " + monkey.inspectCounter + "\t");
                }
            }
        }

        ArrayInteger first = new ArrayInteger(0);
        ArrayInteger second = new ArrayInteger(0);
        for (Monkey monkey :
                monkeyArray) {
            if(monkey.inspectCounter.greaterThan(first)) {
                second = first;
                first = monkey.inspectCounter;
            } else if(!monkey.inspectCounter.greaterThan(second)) {
                second = monkey.inspectCounter;
            }
        }
        first.times(second);
        System.out.println(first);
    }

    public static class Monkey {
        public ArrayList<ArrayInteger> items;
        public char operator;
        public String[] operands;
        public int divisor;
        public long id;
        public int[] throwTargets;
        public ArrayInteger inspectCounter;

        public Monkey(ArrayList<ArrayInteger> items, char operator, String[] operands, int divisor, long id, int[] throwTargets) {
            this.items = items;
            this.operator = operator;
            this.operands = operands;
            this.divisor = divisor;
            this.id = id;
            this.throwTargets = throwTargets;

            inspectCounter = new ArrayInteger(0);
        }

        public ArrayInteger inspect() {
            inspectCounter.add(new ArrayInteger(1));
            ArrayInteger item = items.remove(0);
            boolean second = operands[1].equals("old");
            if(second) {
                if(operator == '*') {
                    item.times(item);
                } else {
                    item.times(new ArrayInteger(2));
                }
            } else {
                if(operator == '*') {
                    item.times(new ArrayInteger(Integer.parseInt(operands[1])));
                } else {
                    item.add(new ArrayInteger(Integer.parseInt(operands[1])));
                }
            }
            return item;
        }

        public boolean isDivisible(ArrayInteger n) {
            return n.divisibleBy(divisor);
        }
    }

    public static class ArrayInteger {
        public ArrayList<Integer> list;

        public ArrayInteger(int initVal) {
            list = new ArrayList<>();
            createList(initVal);
        }

        public ArrayInteger(ArrayList<Integer> arr) {
            list = new ArrayList<>();
            list.addAll(arr);
        }

        public void createList(int initVal) {
            String intString = initVal + "";
            for (int i = 0; i < intString.length(); i++) {
                list.add(Integer.parseInt(intString.substring(i, i + 1)));
            }
        }

        @Override
        public String toString() {
            String result = "";
            for (int i :
                    list) {
                result += i + "";
            }
            return result;
        }

        public void add(ArrayInteger n) {

            ArrayInteger copy = n;
            while(copy.list.size() < list.size()) {
                copy.list.add(0, 0);
            }
            for (int i = 0; i < copy.list.size(); i++) {
                if(i == list.size()) {
                    list.add(0);
                }
                list.set(i, copy.list.get(i) + list.get(i));
            }
            carry();
        }

        public void times(ArrayInteger n) {
            ArrayInteger one = new ArrayInteger(1);
            ArrayInteger copy = new ArrayInteger(list);
            for(ArrayInteger i = new ArrayInteger(1); n.greaterThan(i); i.add(one)) {
                add(copy);
            }
        }

        public void carry() {
            for (int i = 0; i < list.size(); i++) {
                if(list.get(i) >= 10) {
                    list.set(i, list.get(i) - 10);
                    if(i == 0) {
                        list.add(0, 0);
                        list.set(0, list.get(0) + 1);
                    } else {
                        list.set(i - 1, list.get(i - 1) + 1);
                    }
                }
            }
            if(needsCarry()) {
                carry();
            }
        }

        public boolean needsCarry() {
            for (int i :
                    list) {
                if (i >= 10) {
                    return true;
                }
            }
            return false;
        }

        public boolean greaterThan(ArrayInteger n) {
            if(n.list.size() < list.size()) {
                return true;
            } else if(n.list.size() > list.size()) {
                return false;
            }
            for (int i = 0; i < list.size(); i++) {
                if(list.get(i) > n.list.get(i)) {
                    return true;
                }
            }
            return false;
        }

        public boolean equals(ArrayInteger n) {
            if(n.list.size() != list.size()) {
                return false;
            }
            for (int i = 0; i < list.size(); i++) {
                if(list.get(i) != n.list.get(i)) {
                    return false;
                }
            }
            return true;
        }

        public boolean divisibleBy(int n) {
            ArrayInteger base = new ArrayInteger(n);
            ArrayInteger curr = new ArrayInteger(n);
            while(greaterThan(curr)) {
                curr.add(base);
            }
            return equals(curr);
        }
    }
}
