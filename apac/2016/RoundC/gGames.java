import java.io.*;
import java.util.*;

public class gGames {
    private static int[] levels = new int[1 << 4];
    private static HashMap<Integer, Set<Integer>> friends = new HashMap<Integer, Set<Integer>>(1 << 4);
    private static int countBit(int i) {
        int count = 0;
        while (i > 0) {
            count ++;
            i -= (i & -i);
        }
        return count;
    }
    private static boolean solve(List<Integer> elves, int level) {
        if (elves.size() <= 1) return true;
        for (int i = 0; i < levels.length; i++) {
            if (levels[i] == level && elves.contains(i)) {
                for (int friend : friends.get(i)) {
                    if (elves.contains(friend)) return false;
                }
            }
        }
        ArrayList<Integer> left = new ArrayList<Integer>();
        ArrayList<Integer> right = new ArrayList<Integer>();
        for (int i = 0; i < (1 << elves.size()); i++) {
            if (countBit(i) == elves.size() / 2) {
                left.clear();
                right.clear();
                for (int j = 0; j < elves.size(); j++) {
                    if (((i >> j) & 1) == 1) {
                        left.add(elves.get(j));
                    } else {
                        right.add(elves.get(j));
                    }
                }
                if (solve(left, level + 1) && solve(right, level + 1)) return true;
            }
        }
        return false;
    }
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int T = in.nextInt();
        for (int t = 0; t < T; t++) {
            int N = in.nextInt(), M = in.nextInt();
            Arrays.fill(levels, -1);
            friends.clear();
            for (int i = 0; i < M; i++) {
                int E = in.nextInt() - 1, K = N - in.nextInt(), B = in.nextInt();
                levels[E] = K;
                HashSet<Integer> set = new HashSet<Integer>(B);
                for (int j = 0; j < B; j++) {
                    set.add(in.nextInt() - 1);
                }
                friends.put(E, set);
            }
            ArrayList<Integer> elves = new ArrayList<Integer>(1 << N);
            for (int i = 0; i < (1 << N); i++) {
                elves.add(i);
            }
            System.out.printf("Case #%d: %s\n", t + 1, (solve(elves, 0) ? "YES": "NO"));
        }
    }
}
