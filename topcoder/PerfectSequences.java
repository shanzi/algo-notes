public class PerfectSequences {
    public String fixIt(int[] seq) {
        long B = 0;
        long C = 1;
        for (int i : seq) {
            B += i;
            C *= i;
        }

        if (B == 0 && C == 0) {
            if (seq.length == 1) return "Yes";
            else return "No";
        }

        if (C == 0) {
            C = 1;
            int zeron = 0;
            for (int i : seq) {
                if (i == 0) zeron++;
                else C *= i;
            }

            if (seq.length - zeron == 1) return "Yes";
            if (zeron > 1) return "No";
            if (C > 1 && B % (C - 1) == 0) return "Yes";
            else return "No";
        }

        for (int b : seq) {
            long u = b * (B - b);
            long d = C - b;
            if ((u == 0 && d == 0) || (d > 0 && u % d == 0 && u / d != b)) return "Yes";
        }

        return "No";
    }
}
