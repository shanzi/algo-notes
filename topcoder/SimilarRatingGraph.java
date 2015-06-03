public class SimilarRatingGraph {
    public double maxLength(int[] date, int[] rating) {
        double max = 0;
        for (int i = 0; i < date.length - 2; i++) {
            for (int j = i + 1; j < date.length - 1; j++) {
                double partial = 0;
                for (int k = 1; j + k < date.length; k++) {
                    long a = ((long)rating[i + k] - rating[i]) * (date[j + k] - date[j]);
                    long b = ((long)rating[j + k] - rating[j]) * (date[i + k] - date[i]);
                    long c = ((long)rating[i + k] - rating[i]) * (rating[j + 1] - rating[j]);
                    long d = ((long)rating[j + k] - rating[j]) * (rating[i + 1] - rating[i]);
                    long e = ((long)date[i + k] - date[i]) * (date[j + 1] - date[j]);
                    long f = ((long)date[j + k] - date[j]) * (date[i + 1] - date[i]);

                    if (a == b && c == d && e == f) {
                        double h1 = rating[i + k] - rating[i + k - 1];
                        double h2 = rating[j + k] - rating[j + k - 1];
                        double d1 = date[i + k] - date[i + k - 1];
                        double d2 = date[j + k] - date[j + k - 1];
                        partial += Math.sqrt(Math.max(h1 * h1 + d1 * d1, h2 * h2 + d2 * d2));
                    } else {
                        break;
                    }
                }

                if (partial > max) max = partial;
            }
        }
        return max;
    }
}
