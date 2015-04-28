public class AnEasyProblem {
    public int solve(long sum) {
        long tempSum = 0;
        long i = 1;
        for (; tempSum < sum; i++) {
            tempSum += i;
        }

        i--;

        if (tempSum == sum) {
            return (int) i;
        }

        long R = 0;
        long r = i - 2;
        while (true) {
            tempSum -= i;
            i--;
            long H = tempSum;

            while (r >= 1) {
                R += r;
                r--;
                if (H + R >= sum) break;
            }

            if (H + R == sum) {
                return (int)(i + i - r - 1);
            }

            if (H + R < sum) {
                return -1;
            }
            R -= (i - 1);
        }
    }
}
