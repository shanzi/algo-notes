import java.util.Arrays;

public class CircuitsConstruction {
    private static class Res {
        int leftCount;
        int totalCount;
        
        Res(int left, int total) {
            leftCount = left;
            totalCount = total;
        }
    }
    private int countLeaves(String s) {
        Res res = countLeaves(s, 0);
        return res.leftCount;
    }

    private Res countLeaves(String s, int pos) {
        char ch = s.charAt(pos);
        if (ch == 'X') {
            return new Res(1, 1);
        }

        Res left = countLeaves(s, pos + 1);
        Res right = countLeaves(s, pos + 1 + left.totalCount);

        int total = left.totalCount + right.totalCount + 1;

        if (ch == 'A') {
            return new Res(left.leftCount + right.leftCount, total);
        } else {
            return new Res(
                    Math.max(left.leftCount, right.leftCount), total);
        }
    }

    public int maximizeResistance(String circuit,
                                  int[] conductors) {
        int count = countLeaves(circuit);
        System.out.println(count);
        int sum = 0;
        Arrays.sort(conductors);
        for (int i = conductors.length - count; i < conductors.length; i++) {
            sum += conductors[i];
        }
        return sum;
    }
}
