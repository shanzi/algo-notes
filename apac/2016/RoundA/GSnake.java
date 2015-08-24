import java.io.*;
import java.util.*;

class Position {

	int x;
	int y;

    public Position(int x, int y) { 
		this.x = x;
		this.y = y;
    }

    public int hashCode() {
        return this.x * 997 + this.y;
    }

    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (!(obj instanceof Position)) return false;
        if (obj == this) return true;
        Position  b = (Position)obj;
        return this.x == b.x && this.y == b.y;
    }
}

class SnakeSimulator {
    final static int[] dx = {-1, 1, 0, 0};
    final static int[] dy = {0, 0, -1, 1};

    final static int T = 0;
    final static int B = 1;
    final static int L = 2;
    final static int R = 3;

    int direction = R;
    int ROW, COL;
    long currentTime = 0;
    boolean valid = true;
    LinkedList<Position> que = new LinkedList<Position>();
    HashSet<Position> set = new HashSet<Position>();
    HashSet<Position> eatenSet = new HashSet<Position>();

    public SnakeSimulator(int row, int col) { 
        que.add(new Position(1, 1));
        this.ROW = row;
        this.COL = col;
    }

    public void turnLeft() {
        switch (direction) {
            case T: 
                direction = L;
                break;
            case B: 
                direction = R;
                break;
            case L: 
                direction = B;
                break;
            case R: 
                direction = T;
                break;
        }
    }

    public void turnRight() {
        switch (direction) {
            case T: 
                direction = R;
                break;
            case B: 
                direction = L;
                break;
            case L: 
                direction = T;
                break;
            case R: 
                direction = B;
                break;
        }
    }
    public void simulateStep() {
        Position current = que.getFirst();
        Position newpos = new Position(current.x + dx[direction], current.y + dy[direction]);

        if (newpos.x < 1) newpos.x = ROW;
        else if (newpos.x > ROW) newpos.x = 1;
        if (newpos.y < 1) newpos.y = COL;
        else if (newpos.y > COL) newpos.y = 1;

        if ((newpos.x + newpos.y) % 2 == 0 || eatenSet.contains(newpos)) {
            set.remove(que.getLast());
            if (set.contains(newpos)) {
                valid = false;
                return;
            } else {
                que.removeLast();
            }
        } else {
            if (set.contains(newpos)) {
                valid = false;
                return;
            }
            eatenSet.add(newpos);
        }

        que.addFirst(newpos);
        set.add(newpos);
    }

    public void simulateToTime(long t) {
        while (valid && currentTime < t) {
            simulateStep();
            currentTime++;
        }
    }

    public void simulateToEnd() {
        simulateToTime(currentTime + Math.max(ROW, COL));
    }

    public int length() {
        return que.size();
    }
}

public class GSnake {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int T = in.nextInt();
        for (int t = 0; t < T; t++) {
            int S = in.nextInt(), R = in.nextInt(), C = in.nextInt();
            SnakeSimulator simu = new SnakeSimulator(R, C);
            for (int i = 0; i < S; i++) {
                simu.simulateToTime(in.nextInt());
                if (in.next().equals("L")) simu.turnLeft();
                else simu.turnRight();
            }
            simu.simulateToEnd();
            System.out.printf("Case #%d: %d\n", t + 1, simu.length());
        }
    }
}
