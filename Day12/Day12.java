package Day12;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Day12 {
    public static int paths;

    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(new File("Day12/day12.txt"));
        Map<String, List<String>> map = new HashMap<>();
        while (sc.hasNext()) {
            String[] line = sc.nextLine().split("-");
            for (String s: line) {
                if (!map.containsKey(s)) {
                    map.put(s, new ArrayList<>());
                } 
            }
            if (!line[1].equals("start")) {
                map.get(line[0]).add(line[1]);
            }
            if (!line[0].equals("start")) {
                map.get(line[1]).add(line[0]);
            }
        }

        part1(map);
        part2(map);
    }

    private static void part1(Map<String, List<String>> map) {
        paths = 0;
        traverse(map, "start", "end", new HashSet<>(), new HashSet<>(), true);
        System.out.printf("Part 1 Solution: %d\n", paths);
    }

    private static void part2(Map<String, List<String>> map) {
        paths = 0;
        traverse(map, "start", "end", new HashSet<>(), new HashSet<>(), false);
        System.out.printf("Part 2 Solution: %d\n", paths);
    }

    private static boolean isSmallCave(String cave) {
        return cave.equals(cave.toLowerCase());
    }

    private static void traverse(Map<String, List<String>> map, String start, String end, Set<String> visited, Set<String> visitedSmallCaves, boolean visitedTwice) {
        if (start.equals(end)) {
            paths++;
            return;
        }
        if (isSmallCave(start)) {
            if (visitedTwice) {
                visited.add(start);
            } else {
                if (visitedSmallCaves.contains(start)) {
                    visited.add(start);
                    visitedTwice = true;
                    for (String s: visitedSmallCaves) {
                        visited.add(s);
                    }
                } else {
                    visitedSmallCaves.add(start);
                }
            }
        }
        
        for (String s: map.get(start)) {
            if (!visited.contains(s)) {
                traverse(map, s, end, new HashSet<>(visited), new HashSet<>(visitedSmallCaves), visitedTwice);
            }
        }
    }
}