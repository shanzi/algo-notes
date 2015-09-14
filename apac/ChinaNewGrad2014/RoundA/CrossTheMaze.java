import java.io.*;
import java.util.*;

class Edge implements Comparable<Edge> {
    int u;
    int v;
    int cost;
    public Edge(int u, int v, int cost) {
        this.u = u;
        this.v = v;
        this.cost = cost;
    }
    public int compareTo(Edge b) {
        return this.cost - b.cost;
    }
}

public class CrossTheMaze {
    final static private int NORTH = 0;
    final static private int WEST = 1;
    final static private int SOUTH = 2;
    final static private int EAST = 3;
    final static private int[] dx = {-1, 0, 1, 0};
    final static private int[] dy = {0, -1, 0, 1};

    static private char[] D = {'N', 'W', 'S', 'E'};
    static private char[][] maze = new char[110][110];
    static private int N = 0;
    private static boolean leftHand(int x, int y, int direction) {
        int left = (direction + 1) % 4;
        return canGo(x + dx[direction], y + dy[direction]) && !canGo(x + dx[left], y + dy[left]);
    }
    private static boolean canGo(int x, int y) {
        return x >= 0 && y >= 0 && x < N && y < N && maze[x][y] != '#';
    }
    private static int nextDirection(int x, int y, int direction) {
        int forward = direction;
        int leftward = (direction + 1) % 4;
        int rightward = (direction + 3) % 4;
        int backward = (direction + 2) % 4;

        if (leftHand(x, y, forward)) return forward;
        else if (canGo(x + dx[leftward], y + dy[leftward])) return leftward;
        else if (leftHand(x, y, rightward)) return rightward;
        else if (leftHand(x, y, backward)) return backward;
        else return -1;
    }
    private static int initialDirection(int sx, int sy) {
        for (int i = 0; i < 4; i++) {
            if (leftHand(sx, sy, i)) return i;
        }
        return -1;
    }
    private static void solve(int sx, int sy, int ex, int ey) {
        int direction = initialDirection(sx, sy);
        StringBuilder res = new StringBuilder(10001);
        while (res.length() < 10001) {
            if (sx == ex && sy == ey) break;
            if (direction < 0) {
                res.setLength(10001);
                break;
            }
            res.append(D[direction]);
            sx += dx[direction];
            sy += dy[direction];
            direction = nextDirection(sx, sy, direction);
        }

        if (res.length() < 10001) {
            System.out.println(res.length());
            System.out.println(res.toString());
        } else {
            System.out.println("Edison ran out of energy.");
        }
    }
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int T = in.nextInt();
        for (int t = 0; t < T; t++) {
            N = in.nextInt();
            for (int i = 0; i < N; i++) {
                String line = in.next();
                for (int j = 0; j < N; j++) {
                    maze[i][j] = line.charAt(j);
                }
            }
            int sx = in.nextInt() - 1, sy = in.nextInt() - 1;
            int ex = in.nextInt() - 1, ey = in.nextInt() - 1;
            System.out.printf("Case #%d: ", t + 1);
            solve(sx, sy, ex, ey);
        }
    }
}
