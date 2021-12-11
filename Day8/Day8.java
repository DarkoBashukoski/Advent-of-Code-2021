package Day8;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class Day8 {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(new File("Day8/day8.txt"));
        ArrayList<String> arr = new ArrayList<>();
        while (sc.hasNext()) {
            arr.add(sc.nextLine());
        }

        part1(arr);
        part2(arr);
    }

    private static void part1(ArrayList<String> input) {
        int output = 0;
        for (String str: input) {
            for (String word: str.split(" \\| ")[1].split(" ")) {
                if (Arrays.asList(2, 3, 4, 7).contains(word.length())) {
                    output++;
                }
            }
        }
        System.out.printf("Part 1 Solution: %d\n", output);
    }

    private static void part2(ArrayList<String> input) {
        int output = 0;
        for (String str: input) {
            output += decode(str.split(" \\| ")[0].split(" "), str.split(" \\| ")[1].split(" "));
        }
        System.out.printf("Part 2 Solution: %d\n", output);
    }

    private static int decode(String[] codes, String[] values) {
        char[] signals = new char[7]; //top, topLeft, topRight, mid, botLeft, botRight, bot
        HashMap<Character, Integer> counts = new HashMap<>();
        String one = "", seven = "";
        ArrayList<String> lengthSix = new ArrayList<>();
        for (String str: codes) {
            for (char c: str.toCharArray()) {
                if (counts.containsKey(c)) {
                    counts.put(c, counts.get(c)+1);
                } else {
                    counts.put(c, 1);
                }
            }
            if (str.length() == 2) {one = str;}
            else if (str.length() == 3) {seven = str;}
            else if (str.length() == 6) {lengthSix.add(str);}
        }

        signals[0] = seven.replaceAll("[" + one + "]", "").charAt(0);

        for (char c: counts.keySet()) {
            switch (counts.get(c)) {
                case 4: signals[4] = c; break;
                case 6: signals[1] = c; break;
                case 9: signals[5] = c; break;
                case 8: if (c != signals[0]) {signals[2] = c;} break;
            }
        }

        for (String str: lengthSix) {
            String s = str.replaceAll("[" + new String(signals) + "]", "");
            if (s.length() == 1) {
                signals[6] = s.charAt(0);
                break;
            }
        }

        signals[3] = "abcdefg".replaceAll("[" + new String(signals) + "]", "").charAt(0);
        HashMap<Character, Character> map = new HashMap<>();
        for (int i=0; i<signals.length; i++) {map.put(signals[i], (char) (97+i));}
        return 1000 * map(map, values[0]) + 100 * map(map, values[1]) + 10 * map(map, values[2]) + map(map, values[3]);
    }

    private static int map(HashMap<Character, Character> map, String value) {
        StringBuilder sb = new StringBuilder();
        for (char c: value.toCharArray()) {
            sb.append(map.get(c));
        }
        char[] temp = sb.toString().toCharArray();
        Arrays.sort(temp);
        value = new String(temp);

        return switch (value) {
            case "abcefg" -> 0;
            case "cf" -> 1;
            case "acdeg" -> 2;
            case "acdfg" -> 3;
            case "bcdf" -> 4;
            case "abdfg" -> 5;
            case "abdefg" -> 6;
            case "acf" -> 7;
            case "abcdefg" -> 8;
            default -> 9;
        };
    }
}