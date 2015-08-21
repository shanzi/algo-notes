import java.io.*;
import java.util.*;

class Jewel implements Comparable<Jewel> {

	int i;
	double v;
	double w;
    double r = 0.0;

    public Jewel(int i, double v, double w) { 
		this.i = i;
		this.v = v;
		this.w = w;
    }

    public int compareTo(Jewel b) {
        return (b.r - this.r > 0) ? 1 : -1;
    }
}

public class KBest {
    private static boolean valid(Jewel[] jewels, double avg, int k) {
        for (Jewel j: jewels) j.r = j.v - j.w * avg;
        Arrays.sort(jewels);
        double sum = 0;
        for (int i = 0; i < k; i++) sum += jewels[i].r;
        return sum >= 0;
    }
    private static void solve(Jewel[] jewels, int k) {
        double l = 0, u = 1000000.0;
        for (int i = 0; i < 50; i++) {
            double av = (l + u) / 2;
            if (valid(jewels, av, k)) l = av;
            else u = av;
        }
        int[] result = new int[k];
        for (int i = 0; i < k; i++) result[i] = jewels[i].i;
        Arrays.sort(result);
        System.out.printf("%d", result[0]);
        for (int i = 1; i < k; i++) System.out.printf(" %d", result[i]);
        System.out.println();
    }
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String[] str = in.readLine().split(" ");
        int n = Integer.parseInt(str[0]), k = Integer.parseInt(str[1]);
        Jewel[] jewels = new Jewel[n];
        for (int i = 1; i <= n; i++) {
            str = in.readLine().split(" ");
            int v = Integer.parseInt(str[0]), w = Integer.parseInt(str[1]);
            jewels[i - 1] = new Jewel(i, v, w);
        }
        solve(jewels, k);
    }
}
