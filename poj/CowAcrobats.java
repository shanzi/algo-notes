import java.io.*;
import java.util.*;

class Cow implements Comparable<Cow> {

	int weight;
	int strength;
    int sum;

    public Cow(int weight, int strength) { 
        this.weight = weight;
        this.strength = strength;
        sum = weight + strength;
    }
    public int compareTo(Cow b) {
        return this.sum - b.sum;
    }
}

public class CowAcrobats {
    private static boolean canMake(Cow[] cows, int risk, int weight) {
        LinkedList<Cow> que = new LinkedList<Cow>();
        for (int i = cows.length - 1; i >= 0; i--) {
            while (weight - cows[i].sum > risk) {
                if (que.isEmpty()) return false;
                else weight -= que.pollFirst().weight;
            }
            que.add(cows[i]);
        }
        return true;
    }
    private static void solve(Cow[] cows, int weight) {
        int l = -1000000000, u = 500000000;
        while (l <= u) {
            int maxrisk = (l + u) / 2;
            if (canMake(cows, maxrisk, weight)) u = maxrisk - 1;
            else l = maxrisk + 1;
        }
        System.out.println(l);
    }
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        Cow[] cows = new Cow[N];
        int weight = 0;
        for (int i = 0; i < N; i++) {
            cows[i] = new Cow(in.nextInt(), in.nextInt());
            weight += cows[i].weight;
        }
        Arrays.sort(cows);
        solve(cows, weight);
    }
}
