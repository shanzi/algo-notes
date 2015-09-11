import java.io.*;
import java.util.*;

class Line {
    int W;
    int freeCount;
    boolean[] occupy;
    public Line(int W) { 
        this.W = W;
        this.freeCount = W;
        occupy = new boolean[W];
    }
    public boolean canRemove() {
        return freeCount == 0 || freeCount == W;
    }
    public boolean get(int x) {
        return occupy[x];
    }
    public void put(int x) {
        occupy[x] = true;
        freeCount--;
    }
    public void print() {
        for (boolean c : occupy) {
            if (c) System.out.print('x');
            else System.out.print('.');
        }
        System.out.println();
    }
}

class Point implements Comparable<Point>{
    int x;
    int y;
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public int compareTo(Point that) {
        Point a = this;
        Point b = that;
        if (a.x < b.x) return -1;
        else if (a.x > b.x) return 1;
        else if (a.y < b.y) return -1;
        else if (a.y > b.y) return 1;
        else return 0;
    }
}

class Block {
    Point[] points;
    public Block(int x1, int y1, int x2, int y2, int x3, int y3, int x4, int y4) { 
        points = new Point[4];
        points[0] = new Point(x1, y1);
        points[1] = new Point(x2, y2);
        points[2] = new Point(x3, y3);
        points[3] = new Point(x4, y4);
        arrange();
    }
    public Block(Point[] points) {
        this.points = points;
        arrange();
    }
    public Block rotate(int r) {
        Point[] ps = new Point[4]; 
        if (r == 0) {
            for (int i = 0; i < 4; i++) ps[i] = new Point(points[i].x, points[i].y);
        } else if (r == 1) {
            for (int i = 0; i < 4; i++) ps[i] = new Point(points[i].y, -points[i].x);
        } else if (r == 2) {
            for (int i = 0; i < 4; i++) ps[i] = new Point(-points[i].x, -points[i].y);
        } else {
            for (int i = 0; i < 4; i++) ps[i] = new Point(-points[i].y, points[i].x);
        }
        return new Block(ps);
    }
    private void arrange() {
        Arrays.sort(points);
        int minx = 0;
        int miny = 0;
        for (Point p : points) {
            minx = Math.min(minx, p.x);
            miny = Math.min(miny, p.y);
        }
        for (Point p : points) {
            p.x -= minx;
            p.y -= miny;
        }
    }
}
class TerisEmulator {

    private int W;
    private int H;
    private boolean gameover = false;
    private LinkedList<Line> lines = new LinkedList<Line>();

    public TerisEmulator(int W, int H) { 
        this.W = W;
        this.H = H;
    }
    private static boolean canPut(List<Line> q, int left, Block b) {
        for (Point p : b.points) {
            if (q.get(p.x).get(left + p.y)) return false;
        }
        return true;
    }
    public void addBlock(Block b, int x) {
        if (gameover) return;
        for (int i = 0; i < 5; i++) {
            lines.addFirst(new Line(W));
        }
        LinkedList<Line> queue = new LinkedList<Line>();
        for (Line l : lines) {
            queue.addFirst(l);
            if (queue.size() >= 5) {
                if (!canPut(queue, x, b)) {
                    queue.removeFirst();
                    break;
                }
                queue.removeLast();
            }
        }
        for (Point p : b.points) {
            queue.get(p.x).put(x + p.y);
        }
        Iterator<Line> iter = lines.iterator();
        while (iter.hasNext()) {
            Line l = iter.next();
            if (l.freeCount == l.W) iter.remove();
        }
        if (lines.size() > this.H) {
            gameover = true;
            return;
        }
        iter = lines.iterator();
        while (iter.hasNext()) {
            Line l = iter.next();
            if (l.canRemove()) iter.remove();
        }
    }
    public void print() {
        if (gameover) System.out.println("Game Over!");
        else {
            for (int i = 0; i < H - lines.size(); i++) {
                for (int j = 0; j < W; j++) {
                    System.out.print('.');
                }
                System.out.println();
            }
            for (Line l : lines) {
                l.print();
            }
        }
    }
}
public class Teris {
    private static Block[] blocks = new Block[7];
    private static void makeBlocks() {
        blocks[0] = new Block(0, 1, 1, 0, 1, 1, 2, 0);
        blocks[1] = new Block(0, 0, 1, 0, 1, 1, 2, 1);
        blocks[2] = new Block(0, 0, 0, 1, 1, 0, 2, 0);
        blocks[3] = new Block(0, 0, 0, 1, 1, 1, 2, 1);
        blocks[4] = new Block(0, 0, 0, 1, 1, 0, 1, 1);
        blocks[5] = new Block(0, 0, 1, 0, 2, 0, 3, 0);
        blocks[6] = new Block(0, 0, 0, 1, 0, 2, 1, 1);
    }
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int T = in.nextInt();
        makeBlocks();
        for (int t = 0; t < T; t++) {
            int W = in.nextInt(), H = in.nextInt(), N = in.nextInt();
            TerisEmulator emu = new TerisEmulator(W, H);
            System.out.printf("Case #%d:\n", t + 1);
            for (int i = 0; i < N; i++) {
                int b = in.nextInt(), r = in.nextInt(), x = in.nextInt();
                Block block = blocks[b - 1].rotate(r);
                emu.addBlock(block, x);
            }
            emu.print();
        }
    }
}
