import java.util.*;

public class PackingBallsDiv1 {
    public int minPacks(int K, int A, int B, int C, int D) {
        /* At first every package can only contain K balls, to achieve smallest number of packages
         * we should let as many packages as possible to contain K balls.
         *
         * There are only K colors, so in fact we can aways got a valid partition that each
         * package contains different balls, the number of packages will equal to the most number of
         * balls of the same color. But we can also put K balls of the same color into one packages
         * if there are more than K balls of this color.
         *
         * So one solution of greedy strategy are at first we put balls of the same color into packages
         * by a group of K and then only consider the balls left. Then we pick a color, put each balls
         * of this color into different packages. After doing so, if there are colors with number of balls 
         * less than the packages, then balls of these color can always be arranged into these packages.
         * For the colors left, we always put the balls of the same color into a package.
         *
         * Iterate over all possible number of packages contains different colors of balls, we can
         * get the smallest possible packages that need to be used.
         */
        int[] X = new int[K];
        X[0] = A;
        for (int i = 1; i < K; i++) {
            X[i] = (int)((X[i - 1] * (long)B + C) % (long)D + 1);
        }

        int sum = 0;

        for (int i = 0; i < K; i++) {
            sum += X[i] / K;
            X[i] %= K;
        }

        Arrays.sort(X);

        int ret = sum + K;

        for (int i = 0; i < K; i++) {
            ret = Math.min(ret, sum + X[i] + K - i - 1);
        }
        return ret;
    }
}

