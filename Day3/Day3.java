package Day3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Day3 {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(new File("Day3/day3.txt"));
        ArrayList<String> arr = new ArrayList<>();
        while (sc.hasNext()) {
            arr.add(sc.nextLine());
        }

        part1(arr);
        part2(arr);
    }

    private static void part1(ArrayList<String> input) {
        String gammaRate = "";
        String epsilonRate = "";
        for (int i=0; i<input.get(0).length(); i++) {
            int count = 0;
            for (int j=0; j<input.size(); j++) {
                count += (input.get(j).charAt(i) == '1') ? 1 : -1;
            }
            gammaRate += (count < 0) ? "0" : "1";
            epsilonRate += (count < 0) ? "1" : "0";
        }
        System.out.printf("Part 1 Solution: %d\n", Integer.parseInt(gammaRate, 2) * Integer.parseInt(epsilonRate, 2));
    }

    private static void part2(ArrayList<String> input) {
        System.out.printf("Part 1 Solution: %d\n", calculateOxygenRating(input) * calculateCO2Rating(input));
    }

    private static int calculateOxygenRating(ArrayList<String> input) {
        return calculateOxygenRating(input, 0);
    }

    private static int calculateOxygenRating(ArrayList<String> input, int depth) {
        if (input.size() == 1) {
            return Integer.parseInt(input.get(0), 2);
        } else {
            ArrayList<String> ones = new ArrayList<>();
            ArrayList<String> zeros = new ArrayList<>();
            for (String s: input) {
                if (s.charAt(depth) == '1') {ones.add(s);}
                else {zeros.add(s);}
            }
            if (ones.size() >= zeros.size()) {return calculateOxygenRating(ones, depth+1);}
            else {return calculateOxygenRating(zeros, depth+1);}
        }
    }

    private static int calculateCO2Rating(ArrayList<String> input) {
        return calculateCO2Rating(input, 0);
    }

    private static int calculateCO2Rating(ArrayList<String> input, int depth) {
        if (input.size() == 1) {
            return Integer.parseInt(input.get(0), 2);
        } else {
            ArrayList<String> ones = new ArrayList<>();
            ArrayList<String> zeros = new ArrayList<>();
            for (String s: input) {
                if (s.charAt(depth) == '1') {ones.add(s);}
                else {zeros.add(s);}
            }
            if (ones.size() < zeros.size()) {return calculateCO2Rating(ones, depth+1);}
            else {return calculateCO2Rating(zeros, depth+1);}
        }
    }
}
