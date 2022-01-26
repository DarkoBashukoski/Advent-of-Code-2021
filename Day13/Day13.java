package Day13;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Day13 {
    public static int paths;

    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(new File("Day13/day13.txt"));
        int maxX = 0;
        int maxY = 0;
        List<String> list = new ArrayList<>();
        List<String> folds = new ArrayList<>();
        while (sc.hasNext()) {
            String line = sc.nextLine();
            if (line.isEmpty()) {break;}
            String[] l = line.split(",");
            maxX = Math.max(Integer.parseInt(l[0]), maxX);
            maxY = Math.max(Integer.parseInt(l[1]), maxY);
            list.add(line);
        }
        while (sc.hasNext()) {
            folds.add(sc.nextLine());
        }
        boolean[][] arr = new boolean[maxY+1][maxX+1];
        for (String s: list) {
            String[] l = s.split(",");
            arr[Integer.parseInt(l[1])][Integer.parseInt(l[0])] = true;
        }

        part1(arr, folds);
        part2(arr, folds);
    }

    private static void part1(boolean[][] arr, List<String> folds) {
        arr = fold(arr, folds.get(0));
        System.out.printf("Part 1 Solution: %d\n", count(arr));
    }

    private static void part2(boolean[][] arr, List<String> folds) {
        for (String fold: folds) {
            arr = fold(arr, fold);
        }
        System.out.println("Part 2 Solution: ");
        print(arr);
    }

    private static boolean[][] fold(boolean[][] arr, String fold) {
        String[] f = fold.split("=");
        boolean[][] newArr;
        if (f[0].equals("fold along x")) {
            newArr = new boolean[arr.length][Integer.parseInt(f[1])];
            for (int i=0; i<newArr.length; i++) {
                for (int j=0; j<newArr[i].length; j++) {
                    newArr[i][j] = arr[i][j] | arr[i][newArr[i].length*2 - j];
                }
            }
        } else {
            newArr = new boolean[Integer.parseInt(f[1])][arr[0].length];
            for (int i=0; i<newArr.length; i++) {
                for (int j=0; j<newArr[i].length; j++) {
                    newArr[i][j] = arr[i][j] | arr[newArr.length*2 - i][j];
                }
            }
        }
        return newArr;
    }

    private static int count(boolean[][] arr) {
        int output = 0;
        for (int i=0; i<arr.length; i++) {
            for (int j=0; j<arr[i].length; j++) {
                if (arr[i][j]) {output++;}
            }
        }
        return output;
    }

    private static void print(boolean[][] arr) {
        for (boolean[] a: arr) {
            for (boolean b: a) {
                System.out.print((b) ? "#" : ".");
            }
            System.out.println();
        }
        System.out.println();
    }
}