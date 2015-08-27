import java.io.*;
import java.util.*;

public class Packets {
    private static int putPackets(int[] packets, int width, int height) {
        int w = Math.min(width, height);
        int h = Math.max(width, height);
        for (int i = w; i > 0; i--) {
            if (packets[i - 1] == 0) continue;
            int res = Math.min(packets[i - 1], h / i);
            packets[i - 1] -= res;
            if (i == 3 && res == 1 && w == 6) {
                return res + putPackets(packets, 2, 6) + putPackets(packets, 3, 4) + putPackets(packets, 1, 3);
            } else {
                return res + putPackets(packets, w - i, h) + putPackets(packets, i, h - res * i);
            }
        }
        return 0;
    }
    private static void solve(int[] packets, int count) {
        int res = 0;
        while (count > 0) {
            count -= putPackets(packets, 6, 6);
            res++;
        }
        System.out.println(res);
    }
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String[] str;
        while (true) {
            int[] packets = new int[6];
            str = in.readLine().split(" ");
            int count = 0;
            for (int i = 0; i < 6; i++) {
                packets[i] = Integer.parseInt(str[i]);
                count += packets[i];
            }
            if (count == 0) return;
            solve(packets, count);
        }
    }
}
