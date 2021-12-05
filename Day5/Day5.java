package Day5;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day5 {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(new File("Day5/day5.txt"));
        ArrayList<String> arr = new ArrayList<>();
        while (sc.hasNext()) {
            arr.add(sc.nextLine());
        }

        part1(arr);
        part2(arr);
    }

    private static void part1(ArrayList<String> vents) {
        VentMap map = new VentMap(vents, false);
        System.out.printf("Part 1 Solution: %d\n", map.getScore());
    }

    private static void part2(ArrayList<String> vents) {
        VentMap map = new VentMap(vents, true);
        System.out.printf("Part 2 Solution: %d", map.getScore());
    }
}

class VentMap {
    private int[][] map;

    public VentMap(ArrayList<String> vents, boolean includeDiagonals) {
        map = new int[1000][1000];
        Pattern pat = Pattern.compile("([0-9]+),([0-9]+) -> ([0-9]+),([0-9]+)");

        for (String s: vents) {
            Matcher mat = pat.matcher(s);
            if (mat.find()) {
                if (includeDiagonals || isStraightLine(Integer.parseInt(mat.group(1)), Integer.parseInt(mat.group(2)), Integer.parseInt(mat.group(3)), Integer.parseInt(mat.group(4)))) {
                    drawLineOnMap(Integer.parseInt(mat.group(1)), Integer.parseInt(mat.group(2)), Integer.parseInt(mat.group(3)), Integer.parseInt(mat.group(4)));
                }
            }
        }
    }

    public int getScore() {
        int score = 0;
        for (int i=0; i<map.length; i++) {
            for (int j=0; j<map.length; j++) {
                if (map[i][j] >= 2) {score++;}
            }
        }
        return score;
    }

    private static boolean isStraightLine(int x1, int y1, int x2, int y2) {
        return x1 == x2 || y1 == y2;
    }

    private void drawLineOnMap(int x1, int y1, int x2, int y2) {
        int dx = 0;
        int dy = 0;
        if (x1 != x2) {dx = (x1 < x2) ? 1 : -1;}
        if (y1 != y2) {dy = (y1 < y2) ? 1 : -1;}
        while (x1 != x2 || y1 != y2) {
            map[x1][y1]++;
            x1 += dx;
            y1 += dy;
        }
        map[x1][y1]++;
    }
}