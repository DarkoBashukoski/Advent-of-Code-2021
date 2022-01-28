package Day14;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Day14 {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(new File("Day14/day14.txt"));
        String word = sc.nextLine();
        Map<String, String> map = new HashMap<>();
        sc.nextLine();
        while (sc.hasNext()) {
            String[] line = sc.nextLine().split(" -> ");
            map.put(line[0], line[1]);
        }
        Map<String, Long> wordPairs = new HashMap<>();
        for (int i=0; i<word.length() - 1; i++) {
            String pair = word.substring(i, i+2);
            if (wordPairs.containsKey(pair)) {
                wordPairs.put(pair, wordPairs.get(pair) + 1);
            } else {
                wordPairs.put(pair, 1L);
            }
        }

        part1(wordPairs, map, word);
        part2(wordPairs, map, word);
    }

    private static void part1(Map<String, Long> wordPairs, Map<String, String> map, String word) {
        for (int i=0; i<10; i++) {
            wordPairs = polymerize(wordPairs, map);
        }
        System.out.printf("Part 1 Solution: %d\n", count(word, wordPairs));
    }

    private static void part2(Map<String, Long> wordPairs, Map<String, String> map, String word) {
        for (int i=0; i<40; i++) {
            wordPairs = polymerize(wordPairs, map);
        }
        System.out.printf("Part 2 Solution: %d\n", count(word, wordPairs));
    }

    private static Map<String, Long> polymerize(Map<String, Long> wordPairs, Map<String, String> map) {
        Map<String, Long> output = new HashMap<>();
        for (String pair: wordPairs.keySet()) {
            String[] newPairs = new String[] {pair.charAt(0) + map.get(pair), map.get(pair) + pair.charAt(1)};
            for (String p: newPairs) {
                if (output.containsKey(p)) {
                    output.put(p, output.get(p) + ((wordPairs.get(pair) != null) ? wordPairs.get(pair): 0));
                } else {
                    output.put(p, wordPairs.get(pair));
                }
            }
        }
        return output;
    }

    private static long count(String word, Map<String, Long> wordPairs) {
        Map<Character, Long> counts = new HashMap<>();
        for (String s: wordPairs.keySet()) {
            for (char c: s.toCharArray()) {
                if (counts.containsKey(c)) {
                    counts.put(c, counts.get(c) + wordPairs.get(s));
                } else {
                    counts.put(c, wordPairs.get(s));
                }
            }
        }
        counts.replaceAll((c, v) -> v / 2);
        counts.put(word.charAt(0), counts.get(word.charAt(0)) + 1);
        counts.put(word.charAt(word.length()-1), counts.get(word.charAt(word.length()-1)) + 1);
        return Collections.max(counts.values()) - Collections.min(counts.values());
    }
}
