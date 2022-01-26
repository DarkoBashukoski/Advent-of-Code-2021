package Day11;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Day11 {
    private static int flashes = 0;
    private static int flashesInOneStep = 0;

    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(new File("Day11/day11.txt"));
        ArrayList<String> arr = new ArrayList<>();
        while (sc.hasNext()) {
            arr.add(sc.nextLine());
        }

        int[][] input = new int[arr.size()][arr.get(0).length()];

        for (int i=0; i<arr.size(); i++) {
            for (int j=0; j<arr.get(i).length(); j++) {
                input[i][j] = arr.get(i).charAt(j) - 48;
            }
        }

        part1(input);
        part2(input);
    }

    private static void part1(int[][] input) {
        for (int i=0; i<100; i++) {
            step(input);
        }
        System.out.printf("Part 1 Solution: %d\n", flashes);
    }

    private static void part2(int[][] input) {
        int stepNum=101; //100 steps already passed for part 1
        while (!step(input)) {
            stepNum++;
        }
        System.out.printf("Part 1 Solution: %d\n", stepNum);
    }

    private static boolean step(int[][] input) {
        flashesInOneStep = 0;
        for (int i=0; i<input.length; i++) {
            for (int j=0; j<input.length; j++) {
                input[i][j]++;
            }
        }
        for (int i=0; i<input.length; i++) {
            for (int j=0; j<input.length; j++) {
                if (input[i][j] >= 10) {
                    flash(input, i, j);
                    flashesInOneStep++;
                }
            }
        }
        return flashesInOneStep == input.length * input[0].length;
    }

    private static void flash(int[][] input, int i, int j) {
        input[i][j] = 0;
        flashes++;
        for (int x=-1; x<=1; x++) {
            for (int y=-1; y<=1; y++) {
                try {
                    if (input[x+i][y+j] != 0) {
                        input[x+i][y+j]++;
                        if (input[x+i][y+j] >= 10) {
                            flash(input, x+i, y+j);
                            flashesInOneStep++;
                        } 
                    }
                } catch (IndexOutOfBoundsException e) {}
            }
        }
    }
}