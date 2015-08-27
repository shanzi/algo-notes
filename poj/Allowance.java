import java.io.*;
import java.util.*;

class Coin implements Comparable<Coin>{
    int x;
    int y;
    public Coin(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public int compareTo(Coin that) {
        Coin a = this;
        Coin b = that;
        return ((a.x == b.x) ? a.y - b.y : a.x - b.x);
    }
}

public class Allowance {
    private static boolean giveAllowance(Coin[] coins, int C) {
        for (int i = coins.length - 1; i >= 0; i--) {
            Coin c = coins[i];
            int spend = Math.min(c.y, C / c.x);
            c.y -= spend;
            C -= spend * c.x;
            if (C <= 0) return true;
        }
        for (int i = 0; i < coins.length; i++) {
            Coin c = coins[i];
            int spend = Math.min(c.y, C / c.x + ((C % c.x == 0) ? 0 : 1));
            c.y -= spend;
            C -= spend * c.x;
            if (C <= 0) return true;
        }
        return C <= 0;
    }
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt(), C = in.nextInt();
        Coin[] coins = new Coin[N];
        for (int i = 0; i < N; i++) {
            coins[i] = new Coin(in.nextInt(), in.nextInt());
        }
        Arrays.sort(coins);
        int count = 0;
        while (giveAllowance(coins, C)) count++;
        System.out.println(count);
    }
}
