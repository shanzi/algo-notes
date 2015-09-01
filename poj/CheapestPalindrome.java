import java.io.*;
import java.util.*;

public class CheapestPalindrome {
    private static void solve(char[] str, Map<Character, Integer> cost) {
        int M = str.length;
        int[] dp = new int[M];
        int[] last = new int[M];
        for (int j = 1; j < M; j++) {
            for (int i = j - 1; i >= 0; i--) {
                if (str[i] == str[j]) {
                    dp[i] = last[i + 1];
                } else {
                    dp[i] = Math.min(dp[i + 1] + cost.get(str[i]), last[i] + cost.get(str[j]));
                }
            }
            int[] tmp = dp;
            dp = last;
            last = tmp;
        }
        System.out.println(last[0]);
    }
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String[] str;
        str = in.readLine().split(" ");
        int N = Integer.parseInt(str[0]), M = Integer.parseInt(str[1]);
        char[] chars = in.readLine().toCharArray();
        HashMap<Character, Integer> cost = new HashMap<Character, Integer>();
        for (int i = 0; i < N; i++) {
            str = in.readLine().split(" ");
            char c = str[0].charAt(0);
            int deletion = Integer.parseInt(str[1]), addition = Integer.parseInt(str[2]);
            cost.put(c, Math.min(deletion, addition));
        }
        solve(chars, cost);
    }
}
