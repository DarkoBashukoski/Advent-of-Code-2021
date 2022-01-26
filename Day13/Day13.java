package Day13;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Day13 {
    public static int paths;

    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(new File("Day13/day13.txt"));
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

    }

    private static void part2(Map<String, List<String>> map) {

    }
}