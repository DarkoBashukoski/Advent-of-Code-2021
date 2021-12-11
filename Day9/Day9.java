package Day9;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Day9 {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(new File("Day9/day9.txt"));
        List<List<Character>> arr = new ArrayList<>();
        while (sc.hasNext()) {
            arr.add(sc.nextLine().chars().mapToObj((i) -> Character.valueOf((char)i)).collect(Collectors.toList()));
        }

        part1(arr);
        part2(arr);
    }

    private static void part1(List<List<Character>> input) {
        int output = 0;
        for (int i=0; i<input.size(); i++) {
            for (int j=0; j<input.get(i).size(); j++) {
                char c = input.get(i).get(j);
                if (i > 0) {
                    if (input.get(i-1).get(j) <= c) {continue;}
                }
                if (i < input.size() - 1) {
                    if (input.get(i+1).get(j) <= c) {continue;}
                }
                if (j > 0) {
                    if (input.get(i).get(j-1) <= c) {continue;}
                }
                if (j < input.get(i).size() - 1) {
                    if (input.get(i).get(j+1) <= c) {continue;}
                }
                output += (int) c - 47;
            }
        }
        System.out.printf("Part 1 Solution: %d\n", output);
    }

    private static void part2(List<List<Character>> input) {
        PriorityQueue<Integer> pq = new PriorityQueue<>((a,b) -> b.compareTo(a));
        for (int i=0; i<input.size(); i++) {
            for (int j=0; j<input.get(i).size(); j++) {
                char c = input.get(i).get(j);
                if (i > 0) {
                    if (input.get(i-1).get(j) <= c) {continue;}
                }
                if (i < input.size() - 1) {
                    if (input.get(i+1).get(j) <= c) {continue;}
                }
                if (j > 0) {
                    if (input.get(i).get(j-1) <= c) {continue;}
                }
                if (j < input.get(i).size() - 1) {
                    if (input.get(i).get(j+1) <= c) {continue;}
                };
                pq.add(basinSize(i, j, input));
            }
        }
        System.out.printf("Part 2 Solution: %d\n", pq.poll() * pq.poll() * pq.poll());
    }

    private static int basinSize(int i, int j, List<List<Character>> map) {
        try {
            if (map.get(i).get(j) == '9') {return 0;}
        } catch (IndexOutOfBoundsException e) {
            return 0;
        }
        map.get(i).set(j, '9');
        return 1 + basinSize(i+1, j, map) + basinSize(i-1, j, map) + basinSize(i, j+1, map) + basinSize(i, j-1, map);
    }
}