package Day10;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.Stack;

public class Day10 {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(new File("Day10/day10.txt"));
        ArrayList<String> arr = new ArrayList<>();
        while (sc.hasNext()) {
            arr.add(sc.nextLine());
        }

        part1(arr);
        part2(arr);
    }

    private static void part1(ArrayList<String> input) {
        int output = 0;
        ArrayList<String> forRemoval = new ArrayList<>();
        for (String str: input) {
            boolean coruption = false;
            Stack<Character> stack = new Stack<>();
            for (char c: str.toCharArray()) {
                switch (c) {
                    case '(', '[', '{', '<': stack.push(c); break;
                    case ')': if (stack.pop() != '(') {coruption = true; output += 3;} break;
                    case ']': if (stack.pop() != '[') {coruption = true; output += 57;} break;
                    case '}': if (stack.pop() != '{') {coruption = true; output += 1197;} break;
                    case '>': if (stack.pop() != '<') {coruption = true; output += 25137;} break;
                }
                if (coruption) {forRemoval.add(str); break;}
            }
        }
        input.removeAll(forRemoval);
        System.out.printf("Part 1 Solution: %d\n", output);
    }

    private static void part2(ArrayList<String> input) {
        ArrayList<Long> scores = new ArrayList<>();
        for (String str: input) {
            long score = 0;
            Stack<Character> stack = new Stack<>();
            for (char c: str.toCharArray()) {
                switch (c) {
                    case '(', '[', '{', '<': stack.push(c); break;
                    case ')', ']', '}', '>': stack.pop(); break;
                }
            }
            while (!stack.isEmpty()) {
                score *= 5;
                switch (stack.pop()) {
                    case '(': score += 1; break;
                    case '[': score += 2; break;
                    case '{': score += 3; break;
                    case '<': score += 4; break;
                }
            }
            scores.add(score);
        }
        Collections.sort(scores);
        System.out.printf("Part 2 Solution: %d\n", scores.get(scores.size()/2));
    }
}