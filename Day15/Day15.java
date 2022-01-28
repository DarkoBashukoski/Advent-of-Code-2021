package Day15;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Day15 {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(new File("Day15/day15.txt"));
        List<String> list = new ArrayList<>();
        while (sc.hasNext()) {
            list.add(sc.nextLine());
        }
        int[][] map = new int[list.size()][list.get(0).length()];
        for (int i=0; i<map.length; i++) {
            for (int j=0; j<map[i].length; j++) {
                map[i][j] = list.get(i).charAt(j) - 48;
            }
        }

        part1(map);
        part2(map);
    }

    private static void part1(int[][] map) {
        Position end = new Position(map.length-1, map[0].length-1, 0, null, null);
        Position start = new Position(0, 0, map[0][0], null, end);
        System.out.printf("Part 1 Solution: %d\n", aStar(map, start, end));
    }

    private static void part2(int[][] map) {
        int[][] largeMap = new int[map.length*5][map[0].length*5];
        for (int i=0; i<largeMap.length; i++) {
            for (int j=0; j<largeMap[i].length; j++) {
                largeMap[i][j] = ((map[i%map.length][j%map[0].length] + i/map.length + j/map[0].length - 1) % 9) + 1;
            }
        }
        Position end = new Position(largeMap.length-1, largeMap[0].length-1, 0, null, null);
        Position start = new Position(0, 0, largeMap[0][0], null, end);
        System.out.printf("Part 2 Solution: %d\n", aStar(largeMap, start, end));
    }

    private static int aStar(int[][] map, Position start, Position end) {
        boolean[][] visited = new boolean[map.length][map[0].length];
        PriorityQueue<Position> priorityQueue = new PriorityQueue<>();
        visited[start.x][start.y] = true;
        priorityQueue.add(start);

        while (!priorityQueue.isEmpty()) {
            Position p = priorityQueue.poll();
            if (p.equals(end)) {
                return p.cost - start.cost;
            }
            if (p.x > 0 && !visited[p.x-1][p.y]) {
                Position newPos = new Position(p.x-1, p.y, map[p.x-1][p.y], p, end);
                visited[p.x-1][p.y] = true;
                priorityQueue.add(newPos);
            }
            if (p.x < map.length-1 && !visited[p.x+1][p.y]) {
                Position newPos = new Position(p.x+1, p.y, map[p.x+1][p.y], p, end);
                visited[p.x+1][p.y] = true;
                priorityQueue.add(newPos);
            }
            if (p.y > 0 && !visited[p.x][p.y-1]) {
                Position newPos = new Position(p.x, p.y-1, map[p.x][p.y-1], p, end);
                visited[p.x][p.y-1] = true;
                priorityQueue.add(newPos);
            }
            if (p.y < map[p.x].length-1 && !visited[p.x][p.y+1]) {
                Position newPos = new Position(p.x, p.y+1, map[p.x][p.y+1], p, end);
                visited[p.x][p.y+1] = true;
                priorityQueue.add(newPos);
            }
        }
        return -1;
    }

    private static class Position implements Comparable<Position> {
        int x;
        int y;
        int cost;
        Integer fCost;

        public Position(int x, int y, int cost, Position parent, Position end) {
            this.x = x;
            this.y = y;
            this.cost = (parent == null) ? cost : parent.cost + cost;
            fCost = ((end == null) ? 0 : this.cost*10 + (Math.abs(x - end.x) + Math.abs(y - end.y)));
        }

        public boolean equals(Position other) {
            return x == other.x && y == other.y;
        }

        @Override
        public int compareTo(Position o) {
            return fCost.compareTo(o.fCost);
        }
    }
}
