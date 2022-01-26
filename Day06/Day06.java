package Day06;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Day06 {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(new File("Day06/day06.txt"));
        ArrayList<Integer> arr = new ArrayList<>();
        for (String s: sc.nextLine().split(",")) {
            arr.add(Integer.parseInt(s));
        }
        
        part1(arr);
        part2(arr);
    }

    private static void part1(ArrayList<Integer> input) {
        FishSchool fish = new FishSchool(input);
        fish.passDays(80);
        System.out.printf("Part 1 Solution: %d\n", fish.getFishCount());
    }

    private static void part2(ArrayList<Integer> input) {
        FishSchool fish = new FishSchool(input);
        fish.passDays(256);
        System.out.printf("Part 2 Solution: %d\n", fish.getFishCount());
    }
}

class FishSchool {
    private long[] fish;

    public FishSchool(ArrayList<Integer> input) {
        fish = new long[9];
        for (int i: input) {fish[i]++;}
    }

    public void passDays(int days) {
        for (int d=0; d<days; d++) {
            long temp = fish[0];
            for (int i=0; i<8; i++) {fish[i] = fish[i+1];}
            fish[8] = temp;
            fish[6] += temp;
        }
    }

    public long getFishCount() {
        long sum = 0;
        for (long l: fish) {sum += l;}
        return sum;
    }
}