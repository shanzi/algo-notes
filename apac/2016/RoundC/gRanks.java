import java.io.*;
import java.util.*;

class Rank implements Comparable<Rank>{
    int score;
    String name;
    public Rank(int score, String name) {
        this.score = score;
        this.name = name;
    }
    public int compareTo(Rank that) {
        Rank a = this;
        Rank b = that;
        if (a.score < b.score) return 1;
        else if (a.score > b.score) return -1;
        else return a.name.compareTo(b.name);
    }
}

public class gRanks {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int T = in.nextInt();
        for (int t = 0; t < T; t++) {
            int P = in.nextInt();
            int[] PWeights = new int[P];
            for (int i = 0; i < P; i++) PWeights[i] = in.nextInt();

            int N = in.nextInt();
            HashMap<String, List<Integer>> athletes = new HashMap<String, List<Integer>>();
            for (int i = 0; i < N; i++) {
                int CWeight = in.nextInt();
                for (int j = 0; j < P; j++) {
                    String name = in.next();
                    if (!athletes.containsKey(name)) {
                        athletes.put(name, new ArrayList<Integer>());
                    }
                    athletes.get(name).add(CWeight * PWeights[j]);
                }
            }
            int M = in.nextInt();
            ArrayList<Rank> ranks = new ArrayList<Rank>(athletes.size());
            for (String name : athletes.keySet()) {
                List<Integer> scores = athletes.get(name);
                Collections.sort(scores);
                Collections.reverse(scores);
                int sum = 0;
                for (int i = 0; i < Math.min(scores.size(), M); i++) {
                    sum += scores.get(i);
                }
                ranks.add(new Rank(sum, name));
            }
            Collections.sort(ranks);
            System.out.printf("Case #%d:\n", t + 1);
            int lastRank = 0;
            int lastScore = -1;
            for (int i = 0; i < ranks.size(); i++) {
                Rank r = ranks.get(i);
                if (lastScore != r.score) {
                    lastRank = i + 1;
                    lastScore = r.score;
                }
                System.out.printf("%d: %s\n", lastRank, r.name);
            }
        }
    }
}
