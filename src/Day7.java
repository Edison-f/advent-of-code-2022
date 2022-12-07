import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;
import java.util.Stack;

public class Day7 {
    public static void main(String[] args) throws FileNotFoundException {
        FileInputStream fs = new FileInputStream("./src/day7.txt");
        Scanner in = new Scanner(fs);
        boolean listMode = false;
        Directory root = new Directory(null, "/");
        in.nextLine();
        Directory curr = root;
        while (in.hasNext()) {
            String[] split = in.nextLine().split(" ");
            String type = split[0];
            String name = split[1];
            if (type.equals("$")) { // Is command
                if (name.equals("cd")) { // Is Change Directory
                    listMode = false;
                    String dirName = split[2];
                    if (dirName.equals("..")) { // Is go back
                        curr = curr.prev;
                    } else { // Is go forwards
                        curr = curr.getDirectory(dirName);
                    }
                } else { // Is list directory
                    listMode = true;
                }
            } else { // Is listing
                if(type.equals("dir")) { // List directory
                    if(!curr.hasDirectory(name)) { // Directory
                        curr.addDir(new Directory(curr, name));
                    }
                } else { // List file
                    if(!curr.hasItem(name)) {
                        curr.addItem(new Item(Integer.parseInt(type), name));
                    }
                }
            }
        }
        final int SIZE_AVAILABLE = 70000000;
        final int USED_SPACE = root.recurseSizeCalc();
        final int REQUIRED_FREE_SPACE = 30000000;
        int min = Integer.MAX_VALUE;
        Stack<Directory> directoryStack = new Stack<>();
        directoryStack.add(root);
        int accum = 0;
        while(!directoryStack.empty()) {
            Directory temp = directoryStack.pop();
            directoryStack.addAll(temp.dirs);
            int sizeCalc = temp.recurseSizeCalc();
            if(USED_SPACE - sizeCalc <= SIZE_AVAILABLE - REQUIRED_FREE_SPACE) {
                min = Math.min(min, sizeCalc);
                System.out.println(temp.name + "\t\t" + sizeCalc);
            }
        }
        System.out.println("\n" + min);
    }

    public static class Directory {
        public int size;
        public String name;
        public ArrayList<Directory> dirs;
        public ArrayList<Item> items;
        public Directory prev;

        public Directory(Directory prev, String name) {
            dirs = new ArrayList<>();
            items = new ArrayList<>();
            this.name = name;
            this.prev = prev;
        }

        public int recurseSizeCalc() {
            int result = 0;
            for (Item i :
                    items) {
                result += i.size;
            }
            for (Directory d :
                    dirs) {
                result += d.recurseSizeCalc();
            }
            size = result;
            return result;
        }

        public void addDir(Directory d) {
            dirs.add(d);
        }

        public Directory getDirectory(String name) {
            for (Directory d :
                    dirs) {
                if (d.name.equals(name)) {
                    return d;
                }
            }
            return null;
        }

        public void addItem(Item item) {
            items.add(item);
        }

        public boolean hasDirectory(String name) {
            for (Directory d :
                    dirs) {
                if(d.name.equals(name)) {
                    return true;
                }
            }
            return false;
        }

        public boolean hasItem(String name) {
            for (Item item:
                 items) {
                if(item.name.equals(name)) {
                    return true;
                }
            }
            return false;
        }
    }

    public static class Item {
        public int size;
        public String name;

        public Item(int size, String name) {
            this.size = size;
            this.name = name;
        }
    }
}
