package Day1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Day1 {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(new File("Day1/day1.txt"));
        ArrayList<Integer> arr = new ArrayList<>();
        while (sc.hasNext()) {
            arr.add(sc.nextInt());
        }

        part1(arr);
        part2(arr);
    }

    private static void part1(ArrayList<Integer> input) {
        int count = 0;
        for (int i=1; i<input.size(); i++) {
            if (input.get(i-1) < input.get(i)) {
                count++;
            }
        }
        System.out.printf("Part 1 Solution: %d\n", count);
    }

    private static void part2(ArrayList<Integer> input) {
        int count = 0;
        for (int i=3; i<input.size(); i++) {
            if (input.get(i-3) < input.get(i)) {
                count++;
            }
        }
        System.out.printf("Part 2 Solution: %d\n", count);
    }
}