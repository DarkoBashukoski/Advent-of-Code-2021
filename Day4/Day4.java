package Day4;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Day4 {
    public static void main(String[] args) throws FileNotFoundException {
        ArrayList<Integer> draws = new ArrayList<>();
        ArrayList<Bingo> bingos = new ArrayList<>();

        Scanner sc = new Scanner(new File("Day4/day4.txt"));
        for (String s: sc.nextLine().split(",")) {
            draws.add(Integer.parseInt(s));
        }
        sc.nextLine();
        ArrayList<Integer> lines = new ArrayList<>();
        while (sc.hasNext()) {
            String line = sc.nextLine();
            if (line.equals("")) {
                bingos.add(new Bingo(lines.stream().mapToInt(i -> i).toArray()));
                lines.clear();
            } else {
                for (String s: line.trim().split("\\s+")) {
                    lines.add(Integer.parseInt(s));
                }
            }
        }
        bingos.add(new Bingo(lines.stream().mapToInt(i -> i).toArray()));

        part1(draws, bingos); 
        part2(draws, bingos);
    }

    private static void part1(ArrayList<Integer> draws, ArrayList<Bingo> bingos) {
        for (int draw: draws) {
            for (Bingo bingo: bingos) {
                if (bingo.checkNumber(draw)) {
                    if (bingo.isWin()) {
                        System.out.printf("Part 1 Solution: %d\n", bingo.getScore());
                        return;
                    }
                }
            }
        }
    }

    private static void part2(ArrayList<Integer> draws, ArrayList<Bingo> bingos) {
        ArrayList<Bingo> forRemoval = new ArrayList<>();
        for (int draw: draws) {
            for (Bingo bingo: bingos) {
                if (bingo.checkNumber(draw)) {
                    if (bingo.isWin()) {
                        forRemoval.add(bingo);
                    }
                }
            }
            bingos.removeAll(forRemoval);
            if (bingos.isEmpty()) {
                System.out.printf("Part 2 Solution: %d", forRemoval.get(forRemoval.size()-1).getScore());
                return;
            }
        }
    }
}

class Bingo {
    int[][] board;
    boolean[][] marked;
    int lastAdded = 0;

    public Bingo(int [] lines) {
        board = new int[5][5];
        marked = new boolean[5][5];
        for (int i=0; i<5; i++) {
            for (int j=0; j<5; j++) {
                board[i][j] = lines[5*i + j];
                marked[i][j] = false;
            }
        }
    }

    public boolean checkNumber(int num) {
        for (int i=0; i<5; i++) {
            for (int j=0; j<5; j++) {
                if (board[i][j] == num) {
                    marked[i][j] = true;
                    lastAdded = num;
                    return true;
                }
            }
        }
        return false;
    }

    public boolean isWin() {
        int d1 = 0;
        int d2 = 0;
        for (int i=0; i<5; i++) {
            int row = 0;
            int col = 0;
            for (int j=0; j<5; j++) {
                if (marked[i][j]) {row++;}
                if (marked[j][i]) {col++;}
            }
            if (row == 5 || col == 5) {return true;}

            if (marked[i][i]) {d1++;}
            if (marked[i][4-i]) {d2++;}
        }
        return d1 == 5 || d2 == 5;
    }

    public int getScore() {
        int unmarkedSum = 0;
        for (int i=0; i<5; i++) {
            for (int j=0; j<5; j++) {
                if (!marked[i][j]) {unmarkedSum += board[i][j];}
            }
        }
        return unmarkedSum*lastAdded;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i=0; i<5; i++) {
            sb.append(Arrays.toString(board[i])).append("\n");
        }
        return sb.toString();
    }
}