public class NoRepeatPlaylist {
    static final int MD = 1000000007;
    public int numPlaylists(int N, int M, int P) {
        long[] nn = new long[N+1];
        nn[0] = 1;
        for (int p = N; p <= P; p++) {
            for (int n = 1; n <= N; n++) {
                nn[n] = nn[n] * Math.max((n - M), 0) + nn[n-1] * n;
                nn[n] %= MD;
            }
            nn[0] = 0;
        }
        return (int)nn[N];
    }
}
