package Day02;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Day02 {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(new File("Day02/day02.txt"));
        ArrayList<String> arr = new ArrayList<>();
        while (sc.hasNext()) {
            arr.add(sc.nextLine());
        }

        part1(arr);
        part2(arr);
    }

    private static void part1(ArrayList<String> input) {
        int length = 0;
        int depth = 0;

        for (String s: input) {
            switch (s.split(" ")[0]) {
                case "forward": length += Integer.parseInt(s.split(" ")[1]); break;
                case "down": depth += Integer.parseInt(s.split(" ")[1]); break;
                case "up": depth -= Integer.parseInt(s.split(" ")[1]); break;
            }
        }

        System.out.printf("Part 1 Solution: %d\n", length*depth);
    }

    private static void part2(ArrayList<String> input) {
        int length = 0;
        int depth = 0;
        int aim = 0;

        for (String s: input) {
            switch (s.split(" ")[0]) {
                case "down": aim += Integer.parseInt(s.split(" ")[1]); break;
                case "up": aim -= Integer.parseInt(s.split(" ")[1]); break;

                case "forward":
                    length += Integer.parseInt(s.split(" ")[1]);
                    depth += aim * Integer.parseInt(s.split(" ")[1]);
                    break;
            }
        }

        System.out.printf("Part 2 Solution: %d\n", length*depth);
    }
}
