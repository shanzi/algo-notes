import java.io.*;
import java.util.*;

class Block implements Comparable<Block>{
    int altitude;
    int height;
    int count;
    public Block(int altitude, int height, int count) {
        this.altitude = altitude;
        this.height = height;
        this.count = count;
    }
    public int compareTo(Block that) {
        Block a = this;
        Block b = that;
        if (a.altitude < b.altitude) return -1;
        else if (a.altitude > b.altitude) return 1;
        else if (a.height < b.height) return -1;
        else if (a.height > b.height) return 1;
        else return 0;
    }
}

public class SpaceElevator {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        Block[] blocks = new Block[N];
        for (int i = 0; i < N; i++) {
            int h = in.nextInt(), a = in.nextInt(), c = in.nextInt();
            blocks[i] = new Block(a, h, c);
        }
        Arrays.sort(blocks);
        int heightLimit = blocks[blocks.length - 1].altitude;
        int[] dp = new int[heightLimit + 1];
        Arrays.fill(dp, 100);
        dp[0] = 0;

        for (int i = 0; i < N; i++) {
            Block b = blocks[i];
            for (int j = b.height; j <= b.altitude; j++) {
                if (dp[j - b.height] < b.count) dp[j] = Math.min(dp[j], dp[j - b.height] + 1);
            }
            for (int j = 0; j <= b.altitude; j++) {
                if (dp[j] <= b.count) dp[j] = 0;
            }
        }

        int max = 0;
        for (int i = 0; i <= heightLimit; i++) {
            if (dp[i] == 0) max = i;
        }
        System.out.println(max);
    }
}
