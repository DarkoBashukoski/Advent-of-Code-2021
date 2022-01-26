package Day07;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Day07 {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(new File("Day07/day07.txt"));
        ArrayList<Integer> arr = new ArrayList<>();
        for (String s: sc.nextLine().split(",")) {
            arr.add(Integer.parseInt(s));
        }
        part1(arr);
        part2(arr);
    }

    private static void part1(ArrayList<Integer> input) {   
        int median = median(input);   
        int output = 0;
        for (int i: input) {output += Math.abs(i - median);}
        System.out.printf("Part 1 Solution: %d\n", output);
    }

    private static void part2(ArrayList<Integer> input) {
        int average = average(input);
        int output = 0;
        for (int i: input) {output += fuelPart2(Math.abs(i - average));}
        System.out.printf("Part 2 Solution: %d\n", output);
    }

    private static int fuelPart2(int d) {
        return (d*(d+1)) / 2;
    }

    private static int median(ArrayList<Integer> input) {
        Collections.sort(input);
        if (input.size() % 2 == 0) {return (input.get(input.size()/2) + input.get(input.size()/2-1)) / 2;}
        else {return input.get(input.size()/2);}
    }

    private static int average(ArrayList<Integer> input) {
        int average = 0;
        for (int i: input) {
            average += i;
        }
        return average / input.size();
    }
}