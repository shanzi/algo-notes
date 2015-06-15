public class BigFatInteger2 {
    public String isDivisible(int A, int B, int C, int D) {
        return solve(A, B, C, D) ? "divisible" : "not divisible";
    }

    private boolean solve(int A, int B, int C, int D) {
        for (int i = 2; i * i <= C; i++) {
            long p = 0; long q = 0;
            while (A % i == 0) {
                p++;
                A /= i;
            }
            while (C % i == 0) {
                q++;
                C /= i;
            }

            if (p * B < q * D) return false;
        }

        if (C > 1) {
            long p = 0;

            while (A % C == 0) {
                p++;
                A /= C;
            }

            if (p * B < D) return false;
        }

        return true;
    }
}

