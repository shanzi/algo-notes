public class ScoringSystems {
    public String isEquivalent(int[] c, int[] s) {
        int sumc = 0;
        int sums = 0;
        for (int i : c) {
            sumc += i;
        }
        for (int i : s) {
            sums += i;
        }
        int[] dp = new int[sumc + 1];
        dp[sumc] = sums;

        for (int i = 0; i < c.length; i++) {
            int a = c[i];
            int b = s[i];
            for (int j = sumc; j >= a; j--) {
                if (j > a && dp[j - a] == 0) continue;
                if (dp[j] == 0) {
                    dp[j] = dp[j - a] + b;
                } else if (dp[j] != dp[j - a] + b) {
                    return "Not Equivalent";
                }
            }
        }

        int max = 0;
        for (int i : dp) {
            if (i == 0) continue;
            if (i <= max) return "Not Equivalent";
            max = i;
        }

        return "Equivalent";
    }
}
